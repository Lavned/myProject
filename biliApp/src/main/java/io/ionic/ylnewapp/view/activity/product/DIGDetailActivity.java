package io.ionic.ylnewapp.view.activity.product;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
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
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.activity.custompwd.PayFragment;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class DIGDetailActivity extends BaseActivity {


//    @ViewInject(R.id.text_switcher)
//    TextSwitcher mTextSwitcher;

    @ViewInject(R.id.tv_title)
    TextView title;
    @ViewInject(R.id.moneynum)
    EditText bNum;
    @ViewInject(R.id.btname)
    TextView btname;
    @ViewInject(R.id.cb_money)
    CheckBox cb_money;
    @ViewInject(R.id.cb_wallet)
    CheckBox cb_wallet;
    @ViewInject(R.id.balance)
    TextView balance;
    String num,type ="";//投资金额
    boolean noPay;//余额不足
    String walletNum="";

//    PayFragment payFragment = new PayFragment();

    @Event(type = View.OnClickListener.class,value ={ R.id.tv_back,R.id.dig_pay,R.id.cb_wallet,R.id.cb_money})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back :
                finish();
                break;
            case R.id.cb_money :
                cb_wallet.setChecked(false);
                cb_money.setChecked(true);
                break;
            case R.id.cb_wallet :
                cb_wallet.setChecked(true);
                cb_money.setChecked(false);
                myCountWallet();
                break;
            case R.id.dig_pay:
                if(cb_money.isChecked()) {
                    if (noPay) //余额不够
                        ActivityUtils.noB(DIGDetailActivity.this,PreferenceUtils.getPrefString(mContext,"bz",""));
                    else
                        newOrder(1);//余额足够，直接下单
                }else
                    newOrder(2); //使用钱包，只下单不支付
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digdetail);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
        setView();
    }

    //赋值
    private void setView(){
        title.setText("订单详情");
        type = PreferenceUtils.getPrefString(mContext,"bz","");
        num = PreferenceUtils.getPrefString(mContext,"bnum","");
        bNum.setHint("该项目起投金额为"+num+type);
        bNum.setEnabled(false);
        btname.setText(PreferenceUtils.getPrefString(mContext,"btname",""));
        myMoneySelect(type.toUpperCase());
    }


    /**
     * 检测我的钱包
     */
    public void myCountWallet(){
        OkGo.<String>get(Constants.URL_BASE + "user/address?type=defult")//查询我的银行卡
                .tag(this)
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        JSONObject jsonObject ;
                        try {
                            jsonObject= new JSONObject(response.body());
                            switch (jsonObject.getString("status")){
                                case "401":
                                    ActivityUtils.toLogin(DIGDetailActivity.this,0);
                                    break;
                                case "200":
                                    if(jsonObject.toString().contains("address"))
                                        walletNum = jsonObject.getJSONObject("body").getString("address");
                                    else
                                        ActivityUtils.noWallet(DIGDetailActivity.this);
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
                });
    }


    /**
     * 选择余额支付
     * @param name
     */
    public void myMoneySelect(String name){
        OkGo.<String>get(Constants.URL_BASE + "order/enough?name="+name)//
                .tag(this)
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        JSONObject jsonObject ;
                        try {
                            jsonObject= new JSONObject(response.body());
                            switch (jsonObject.getString("status")){
                                case "401":
                                    ActivityUtils.toLogin(DIGDetailActivity.this,0);
                                    break;
                                case "200":
                                    String balanceNum = jsonObject.getString("body");
                                    if(Double.parseDouble(balanceNum) < Double.parseDouble(num)) {
                                        balance.setText("(您的余额为" + balanceNum + "，不足于支付)");
                                        noPay = true;
                                    }
                                    else
                                        balance.setText("(您的余额为"+balanceNum+")");
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
                });
    }


    /**
     * 请求网络 下单
     */
    private void newOrder(final int type) {  //1 是余额，2是钱包
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
                                    if(type ==1)
                                        startPayBalance(jsonObject.getJSONObject("body").getString("orderid"));
                                    else
                                        startPayWallet(jsonObject.getJSONObject("body").getString("orderid"));
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
     * 钱包支付界面
     */
    private void startPayWallet(String orderid) {
        Intent intent = new Intent(mContext,WalletPaySuccessActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("orderId",orderid);
        bundle.putString("walletNum",walletNum);
        bundle.putString("money",num);
        bundle.putString("type",type);
        intent.putExtras(bundle);
        startActivity(intent);
        ProductsDIGActivity.activity.finish();
        finish();
    }


    /**
     * 余额卡支付界面
     */
    private void startPayBalance(String orderid) {
        Intent intent = new Intent(mContext,BalancePayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("orderId",orderid);
        bundle.putString("type","coin");
        bundle.putString("orderName",PreferenceUtils.getPrefString(mContext,"btname",""));
        bundle.putString("orderMoney",num);
        intent.putExtras(bundle);
        startActivity(intent);
        ProductsDIGActivity.activity.finish();
        finish();
    }





    /**
     * 支付鉴权
     */
//    private void checkAuth() {
//        payFragment.show(getSupportFragmentManager(), "pay");
//        payFragment.setPaySuccessCallBack(new PayPwdView.InputCallBack() {
//            @Override
//            public void onInputFinish(String result) {
//                userAuth(result);
//            }
//        });
//    }


//    /**
//     * 余额支付操作
//     */
//    private void balancePay(){
//        if(PreferenceUtils.getPrefString(mContext,"hasPay","").equals("true"))
//            checkAuth();
//        else
//            ActivityUtils.hasPay(DIGDetailActivity.this);
//    }
//
//    /**
//     * 用户鉴权
//     */
//    private void userAuth(String payPwd) {
//        mBuilder.setTitle("请稍候...").show();
//        OkGo.<String>post(Constants.URL_BASE + "user/authPay")//
//                .tag(this)//
//                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
//                .params("payPwd", MD5Util.encrypt(payPwd))
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        String data = response.body();//这个就是返回来的结果
//                        try {
//                            JSONObject jsonObject = new JSONObject(data);
//                            switch (jsonObject.getString("status")){
//                                case "401":
//                                    ActivityUtils.toLogin(DIGDetailActivity.this,0);
//                                    break;
//                                case "200":
//                                    payFragment.dismiss();
//                                    newOrder(1);
//                                    break;
//                                case "400":
//                                    payFragment.dismiss();
//                                    new CommonDialog.Builder(DIGDetailActivity.this)
//                                        .setMessage(jsonObject.getString("msg")).setTitle("提示")
//                                        .setPositiveButton("重试", new View.OnClickListener() {
//                                            @Override
//                                            public void onClick(View view) {
//                                                payFragment.show(getSupportFragmentManager(), "pay");
//                                            }
//                                        }, R.color.main).setNegativeButton("忘记密码", new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                            payFragment.dismiss();
//                                            ActivityUtils.toForgetPwd(DIGDetailActivity.this);
//                                        }
//                                    },R.color.main).show();
//                                    break;
//                                case "403":
//                                    T.showShort(jsonObject.getString("msg"));
//                                    payFragment.dismiss();
//                                    ActivityUtils.toForgetPwd(DIGDetailActivity.this);
//                                    break;
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Response<String> response) {
//                        super.onError(response);
//                        T.showNetworkError(mContext);
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        super.onFinish();
//                        mBuilder.dismiss();
//                    }
//                });
//    }

//    /**
//     * 订单支付
//     */
//    private void nowPay(String id) {
//        OkGo.<String>post(Constants.URL_BASE + "coins/pay")//
//                .tag(this)//
//                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
//                .params("orderid", id)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        JSONObject jsonObject ;
//                        try {
//                            jsonObject= new JSONObject(response.body());
//                            T.showShort(jsonObject.getString("msg"));
//                            if(jsonObject.getString("status").equals("401"))
//                                ActivityUtils.toLogin(DIGDetailActivity.this,0);
//                            if(jsonObject.getString("status").equals("200")){
//                                ProductsDIGActivity.activity.finish();
//                                finish();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Response<String> response) {
//                        super.onError(response);
//                        T.showNetworkError(mContext);
//                    }
//
//                });
//    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


}
