package io.ionic.ylnewapp.view.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiangyy.easydialog.CommonDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.umeng.analytics.MobclickAgent;

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

public class WithdrawalActivity extends BaseActivity {




    @ViewInject(R.id.tv_title)
    TextView title;// base 标题
    @ViewInject(R.id.paymoney)
    EditText paymoney;
    @ViewInject(R.id.money_all)
    TextView moneyAll;
    @ViewInject(R.id.money_get)
    TextView getMoney;
    @ViewInject(R.id.icon)
    ImageView icon;
    @ViewInject(R.id.name)
    TextView name;
    @ViewInject(R.id.text1)
    TextView text1;
    @ViewInject(R.id.text2)
    TextView text2;
    @ViewInject(R.id.text3)
    TextView text3;

    String money ="0.00";


    PayFragment payFragment = new PayFragment();

    @Event(type = View.OnClickListener.class,value ={ R.id.tv_back,R.id.sub_btn,R.id.my_address})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back :
                finish();
                break;
            case R.id.sub_btn:
                if(checkEmpty()){
                    if(Double.parseDouble(ActivityUtils.getView(paymoney)) > Double.parseDouble(money) ){
                        T.showShort("提现金额不能大于剩余金额");
                    }else if(PreferenceUtils.getPrefString(mContext,"hasPay","").equals("true"))
                        checkAuth();
                    else
                        ActivityUtils.hasPay(WithdrawalActivity.this);
                }
                break;
            case R.id.my_address :
                startActivity(new Intent(mContext,SelectBankActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal);
        init();
        MobclickAgent.onEvent(mContext, "Withdraw2");
    }


    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    /**
     * chushihua
     */
    private void init() {
        title.setText("提现");
        money = PreferenceUtils.getPrefString(mContext,"money","");
        moneyAll.setText("¥"+PreferenceUtils.getPrefString(mContext,"moneyall",""));
        getMoney.setText("¥"+money);
    }


    /**
     * 数据复制
     */
    String toFinshName ,bankCard;
    private void initData() {
        bankCard = PreferenceUtils.getPrefString(mContext,"bank","");
        String names = PreferenceUtils.getPrefString(mContext,"name","");
        if(!names.equals("")){
            Glide.with(mContext)
                    .load(PreferenceUtils.getPrefString(mContext,"icon",""))
                    .into(icon);
            name.setText(PreferenceUtils.getPrefString(mContext,"name",""));
            int j = names.indexOf("银行");
            toFinshName = names.substring(0,j+2);
            text1.setText(toFinshName);
            text2.setText(names.substring(j+2,name.length()));
            text3.setText(PreferenceUtils.getPrefString(mContext,"bank",""));

        }

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
                                    ActivityUtils.toLogin(WithdrawalActivity.this,0);
                                    break;
                                case "200":
                                    payFragment.dismiss();
                                    loadData();
                                    break;
                                case "400":
                                    payFragment.dismiss();
                                    new CommonDialog.Builder(WithdrawalActivity.this)
                                            .setMessage(jsonObject.getString("msg")).setTitle("提示")
                                            .setPositiveButton("重试", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    payFragment.show(getSupportFragmentManager(), "pay");
                                                }
                                            }, R.color.main).setNegativeButton("忘记密码", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            ActivityUtils.toForgetPwd(WithdrawalActivity.this);
                                        }
                                    },R.color.main).show();
                                    break;
                                case "403":
                                    T.showShort(jsonObject.getString("msg"));
                                    ActivityUtils.toEdPwd(WithdrawalActivity.this);
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

    //关闭界面
    private void tofinish() {
        Intent intent =new Intent(mContext,WithdrawSuccessActivity.class);
        intent.putExtra("card",toFinshName
                +"("+bankCard.substring(bankCard.length()-4,bankCard.length()) +")");
        startActivity(intent);
        finish();
    }

    /**
     * 提交
     */
    private void loadData() {
        mBuilder.setTitle("提现中...").show();
        OkGo.<String>put(Constants.URL_BASE + "user/cash")//
                .tag(this)//
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .params("payAmount", ActivityUtils.getView(paymoney))
                .params("bankId", PreferenceUtils.getPrefString(mContext,"id",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            T.showShort(jsonObject.getString("msg"));
                            if(jsonObject.getString("status").equals("401"))
                                ActivityUtils.toLogin(WithdrawalActivity.this,0);
                            if(jsonObject.getString("status").equals("200")){
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

    /**
     * feikongyanzheng
     * @return
     */
    public boolean checkEmpty() {
        if (ActivityUtils.getView(paymoney).equals("")) {
            T.showShort("提现数量不可为空");
            return false;
        }
        if (text1.getText().toString().trim().equals("")) {
            T.showShort("请选择银行卡");
            return false;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceUtils.setPrefString(mContext,"name","");
        PreferenceUtils.setPrefString(mContext,"icon","");
        PreferenceUtils.setPrefString(mContext,"bank","");
        PreferenceUtils.setPrefString(mContext,"id","");
    }
}
