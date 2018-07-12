package io.ionic.ylnewapp.view.activity.wallet;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
import io.ionic.ylnewapp.view.activity.mine.RechargeActivity;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class WalletAddMoneyActivity extends BaseActivity {

    @ViewInject(R.id.tv_title)
    TextView title;
    @ViewInject(R.id.title_name)
    TextView tName;
    @ViewInject(R.id.title_icon)
    ImageView tImg;
    @ViewInject(R.id.pay_amount)
    EditText pay_amount;
    @ViewInject(R.id.wl_name)
    TextView name;
    @ViewInject(R.id.address_text)
    TextView address_text;


    PayFragment payFragment = new PayFragment();

    @Event(type = View.OnClickListener.class,value = {R.id.tv_back,R.id.my_address,R.id.submit_btn,R.id.address_copy})
    private void click(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.my_address:
                startActivity(new Intent(mContext, MyWaletAddressActivity.class));
                break;
            case R.id.submit_btn:
                if (checkEmpty())
                    if(PreferenceUtils.getPrefString(mContext,"hasPay","").equals("true"))
                        checkAuth();
                    else
                        ActivityUtils.hasPay(WalletAddMoneyActivity.this);
                break;
            case R.id.address_copy :
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);// 将文本内容放到系统剪贴板里。
                cm.setText(address_text.getText().toString().trim());
                T.showShort("复制成功，可以发给朋友们了");
                break;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_add_money);
        init();
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
                        String data = response.body();
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            switch (jsonObject.getString("status")){
                                case "401":
                                    ActivityUtils.toLogin(WalletAddMoneyActivity.this,0);
                                    break;
                                case "200":
                                    payFragment.dismiss();
                                    sumbitData();
                                    break;
                                case "400":
                                    payFragment.dismiss();
                                    new CommonDialog.Builder(WalletAddMoneyActivity.this)
                                            .setMessage(jsonObject.getString("msg")).setTitle("提示")
                                            .setPositiveButton("重试", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    payFragment.show(getSupportFragmentManager(), "pay");
                                                }
                                            }, R.color.main).setNegativeButton("忘记密码", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            ActivityUtils.toForgetPwd(WalletAddMoneyActivity.this);
                                        }
                                    },R.color.main).show();
                                    break;
                                case "403":
                                    T.showShort(jsonObject.getString("msg"));
                                    ActivityUtils.toEdPwd(WalletAddMoneyActivity.this);
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
                        mBuilder.dismiss();
                    }
                });
    }



    private void sumbitData() {
        mBuilder.setTitle("充值中...").show();
        OkGo.<String>put(Constants.URL_BASE + "user/coin/charge")//
                .tag(this)//
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext, "token", ""))
                .params("payAmount", ActivityUtils.getView(pay_amount))
                .params("name", tName.getText().toString())
                .params("id", PreferenceUtils.getPrefString(mContext, "walid", ""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            T.showShort(jsonObject.getString("msg"));
                            if (jsonObject.getString("status").equals("401"))
                                ActivityUtils.toLogin(WalletAddMoneyActivity.this,0);
                            if (jsonObject.getString("status").equals("200")) {
                                tofinish();
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
                        mBuilder.dismiss();
                    }
                });
    }


    @Override
    protected void onResume() {
        super.onResume();
        initResume();
    }

    /**
     * 数据复制
     */
    private void initResume() {
        String names = PreferenceUtils.getPrefString(mContext,"wlname","");
        if(!names.equals("")){
            name.setText(names);
        }
    }


    //关闭界面
    private void tofinish() {
        Intent intent =new Intent(mContext,WalletOptionSuccessActivity.class);
        intent.putExtra("name","1");
        startActivity(intent);
        finish();
    }

    /**
     *
     */
    private void init() {
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
        Intent intent = getIntent();
        title.setText(intent.getStringExtra("name"));
        tName.setText(intent.getStringExtra("name"));
        if(tName.getText().toString().equals("btc")){
            address_text.setText("1QGDMGUWDTYDcMFufuAHAzx3gZLWKWGVhD");
        }
    }

    /**
     * feikongyanzheng
     * @return
     */
    public boolean checkEmpty() {
        if (ActivityUtils.getView(pay_amount).equals("")) {
            T.showShort("充值金额不可为空");
            return false;
        }
        if(name.getText().toString().equals("我的地址")){
            T.showShort("请选择钱包");
            return false;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceUtils.setPrefString(mContext,"wlname","");
        PreferenceUtils.setPrefString(mContext, "walid", "");
    }

}
