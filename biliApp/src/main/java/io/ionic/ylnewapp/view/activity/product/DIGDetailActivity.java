package io.ionic.ylnewapp.view.activity.product;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.jiangyy.easydialog.CommonDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.MD5Util;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.activity.custompwd.PayFragment;
import io.ionic.ylnewapp.view.activity.custompwd.PayPwdView;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class DIGDetailActivity extends BaseActivity {


//    @ViewInject(R.id.text_switcher)
//    TextSwitcher mTextSwitcher;

    @ViewInject(R.id.bName)
    TextView bName;
    @ViewInject(R.id.bNum)
    TextView bNum;
    @ViewInject(R.id.bRate)
    TextView bRate;
    @ViewInject(R.id.btname)
    TextView btname;

    PayFragment payFragment = new PayFragment();

    @Event(type = View.OnClickListener.class,value ={ R.id.back_3,R.id.dig_pay})
    private void click(View v){
        switch (v.getId()){
            case R.id.back_3 :
                finish();
                break;
            case R.id.dig_pay:
                if(PreferenceUtils.getPrefString(mContext,"hasPay","").equals("true"))
                    checkAuth();
                else
                    ActivityUtils.hasPay(DIGDetailActivity.this);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digdetail);
        init();
    }

    private void init() {
        StatusBarUtil.setTranslucentForImageViewInFragment(this, 0,null);
        setView();
    }

    //赋值
    private void setView(){
        bRate.setText(PreferenceUtils.getPrefString(mContext,"btname",""));
        btname.setText(PreferenceUtils.getPrefString(mContext,"btname",""));
        bName.setText(PreferenceUtils.getPrefString(mContext,"bz",""));
        bNum.setText(PreferenceUtils.getPrefString(mContext,"bnum",""));
        bRate.setText(PreferenceUtils.getPrefString(mContext,"brate",""));
    }


    private void checkAuth() {
        payFragment.show(getSupportFragmentManager(), "pay");
        payFragment.setPaySuccessCallBack(new PayPwdView.InputCallBack() {
            @Override
            public void onInputFinish(String result) {
                userAuth(result);
            }
        });
    }



    /**
     * 用户鉴权
     */
    private void userAuth(String payPwd) {
        mBuilder.setTitle("请稍候...").show();
        OkGo.<String>post(Constants.URL_BASE + "user/authPay")//
                .tag(this)//
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .params("payPwd", MD5Util.encrypt(payPwd))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            switch (jsonObject.getString("status")){
                                case "401":
                                    ActivityUtils.toLogin(DIGDetailActivity.this,0);
                                    break;
                                case "200":
                                    payFragment.dismiss();
                                    newOrder();
                                    break;
                                case "400":
                                    payFragment.dismiss();
                                    new CommonDialog.Builder(DIGDetailActivity.this)
                                        .setMessage(jsonObject.getString("msg")).setTitle("提示")
                                        .setPositiveButton("重试", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                payFragment.show(getSupportFragmentManager(), "pay");
                                            }
                                        }, R.color.main).setNegativeButton("忘记密码", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            payFragment.dismiss();
                                            ActivityUtils.toForgetPwd(DIGDetailActivity.this);
                                        }
                                    },R.color.main).show();
                                    break;
                                case "403":
                                    T.showShort(jsonObject.getString("msg"));
                                    payFragment.dismiss();
                                    ActivityUtils.toForgetPwd(DIGDetailActivity.this);
                                    break;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showNetworkError(mContext);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        mBuilder.dismiss();
                    }
                });
    }


    /**
     * 请求网络
     */
    private void newOrder() {
        mBuilder.setTitle("请求中...").show();
        OkGo.<String>put(Constants.URL_BASE + "order/newOrder")//
                .tag(this)
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .params("pid",PreferenceUtils.getPrefString(mContext,"pid",""))
                .params("payAmount",PreferenceUtils.getPrefString(mContext,"bnum",""))
                .params("key",PreferenceUtils.getPrefString(mContext,"bz",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        JSONObject jsonObject ;
                        try {
                            jsonObject= new JSONObject(response.body());
                            T.showShort(jsonObject.getString("msg"));
                            switch (jsonObject.getString("status")){
                                case "401":
                                    ActivityUtils.toLogin(DIGDetailActivity.this,0);
                                    break;
                                case "402":
                                    ActivityUtils.noB(DIGDetailActivity.this,PreferenceUtils.getPrefString(mContext,"bz",""));
                                    break;
                                case "200":
                                    nowPay(jsonObject.getJSONObject("body").getString("orderid"));
                                    break;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showNetworkError(mContext);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        if(mBuilder!=null){
                            mBuilder.dismiss();
                        }
                    }
                });
    }

    /**
     * 支付
     */
    private void nowPay(String id) {
        OkGo.<String>post(Constants.URL_BASE + "coins/pay")//
                .tag(this)//
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .params("orderid", id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        JSONObject jsonObject ;
                        try {
                            jsonObject= new JSONObject(response.body());
                            T.showShort(jsonObject.getString("msg"));
                            if(jsonObject.getString("status").equals("401"))
                                ActivityUtils.toLogin(DIGDetailActivity.this,0);
                            if(jsonObject.getString("status").equals("200")){
                                ProductsDIGActivity.activity.finish();
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showNetworkError(mContext);
                    }

                });

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


}
