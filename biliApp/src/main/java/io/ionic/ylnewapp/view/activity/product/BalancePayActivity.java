package io.ionic.ylnewapp.view.activity.product;

import android.content.Intent;
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

public class BalancePayActivity extends BaseActivity {


    @ViewInject(R.id.tv_title)
    TextView title;
    @ViewInject(R.id.orderName)
    TextView orderName;
    @ViewInject(R.id.orderMoney)
    TextView orderMoney;
    @ViewInject(R.id.orderNum)
    TextView orderNum;

    String orderId="";
    String payType="";
    PayFragment payFragment = new PayFragment();

    @Event(type = View.OnClickListener.class,value ={ R.id.tv_back,R.id.clickPay})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back :
                finish();
                break;
            case R.id.clickPay:
                balancePay();
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_pay);
        init();
        getData();
    }

    /**
     * 获取传递数据
     */
    private void getData() {
        Intent intent = getIntent();
        Bundle bundle =intent.getExtras();
        payType = bundle.getString("type");
        orderId = bundle.getString("orderId");
        orderMoney.setText(bundle.getString("orderMoney")+"");
        orderNum.setText(orderId+"");
        orderName.setText(bundle.getString("orderName"));
    }


    /**
     *初始化
     */
    private void init() {
        title.setText("支付详情");
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary), 225);
    }


    /**
     * 余额支付操作
     */
    private void balancePay(){
        if(PreferenceUtils.getPrefString(mContext,"hasPay","").equals("true"))
            checkAuth();
        else
            ActivityUtils.hasPay(BalancePayActivity.this);
    }

    /**
     * 支付鉴权
     */
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
     * 验证密码
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
                                    ActivityUtils.toLogin(BalancePayActivity.this,0);
                                    break;
                                case "200":
                                    payFragment.dismiss();
                                    if (payType.equals("other"))
                                        nowPayOfBalance(orderId);
                                    else
                                        nowPay(orderId);
                                    break;
                                case "400":
                                    payFragment.dismiss();
                                    new CommonDialog.Builder(BalancePayActivity.this)
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
                                            ActivityUtils.toForgetPwd(BalancePayActivity.this);
                                        }
                                    },R.color.main).show();
                                    break;
                                case "403":
                                    T.showShort(jsonObject.getString("msg"));
                                    payFragment.dismiss();
                                    ActivityUtils.toForgetPwd(BalancePayActivity.this);
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
     * 订单支付
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
                                ActivityUtils.toLogin(BalancePayActivity.this,0);
                            if(jsonObject.getString("status").equals("200")){
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

    /**
     * 用钱支付的订单
     * @param id
     */
    private void nowPayOfBalance(String id) {
        OkGo.<String>post(Constants.URL_BASE + "order/payOrder")//
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
                                ActivityUtils.toLogin(BalancePayActivity.this,0);
                            if(jsonObject.getString("status").equals("200")){
                                finish();
                                ProductAIActivity.activity.finish();
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

}

