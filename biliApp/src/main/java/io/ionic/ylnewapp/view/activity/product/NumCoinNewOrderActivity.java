package io.ionic.ylnewapp.view.activity.product;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.math.BigDecimal;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.activity.mine.CouponsActivity;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class NumCoinNewOrderActivity extends BaseActivity {



    @ViewInject(R.id.moneyNum)
    EditText moneyNum; //投资金额
    @ViewInject(R.id.tv_title)
    TextView title_name; //标题
    @ViewInject(R.id.btname)
    TextView btname; //投资项目名称
    @ViewInject(R.id.balance)
    TextView balance;  //余额显示

    //三中支付
    @ViewInject(R.id.payBTC)
    CheckBox payBTC;
    @ViewInject(R.id.payETH)
    CheckBox payETH;
    @ViewInject(R.id.payRMB)
    CheckBox payRMB;

    //三个换算
    @ViewInject(R.id.payBTCEd)
    EditText payBTCEd;
    @ViewInject(R.id.payETHEd)
    EditText payETHEd;
    @ViewInject(R.id.payRMBEd)
    EditText payRMBEd;
    @ViewInject(R.id.showFB)
    LinearLayout showFB;

    @ViewInject(R.id.cb_card)
    CheckBox cbCard;
    @ViewInject(R.id.cb_money)
    CheckBox cbMoney;
    //三个协议
    @ViewInject(R.id.ag_1)
    TextView ag1;
    @ViewInject(R.id.ag_2)
    TextView ag2;
    @ViewInject(R.id.ag_3)
    TextView ag3;
    @ViewInject(R.id.hb_tip)
    TextView hbTip;
    @ViewInject(R.id.coupons)
    LinearLayout coupons;

    private String url ="";
    private  String balanceNum ="";
    private String cardNum="";
    private int type = 0;
    private  String coin,coinNum="";
    private String coinType ="BTC";

    String btnPrice,ethPrice;

    @Event(type = View.OnClickListener.class,value = {R.id.pay_btn,R.id.cb_money,R.id.cb_card,R.id.cb_agreed,R.id.tv_back
                    ,R.id.coupons,R.id.ag_1,R.id.ag_2,R.id.ag_3,R.id.payBTC,R.id.payETH,R.id.payRMB})
    private void click(View v){
        switch (v.getId()){
            case R.id.pay_btn :
                if(moneyNum.getText().toString().trim().equals(""))
                    T.showShort("起购金额必须大于1000");
                else {
                    if(payRMB.isChecked()){
                        if(cbMoney.isChecked()){
                            if (Double.parseDouble(moneyNum.getText().toString().trim()) > Double.parseDouble(balanceNum)) {
                                balance.setText("(您的余额为" + balanceNum + "，不足于支付)");
                                cbMoney.setChecked(false);
                                cbMoney.setEnabled(false);
                            }else {
                                newOrderOtc(1);//余额足够直接下单
                            }
                        }else if(cbCard.isChecked())//银行卡下单不支付
                            newOrderOtc(2);
                        else
                            T.showShort("请选择支付方式");
                    } else if(payBTC.isChecked()){//BTC下单
                        coinNum = payBTCEd.getText().toString();
                        coin = payBTCEd.getText().toString().trim() + "BTC";
                        newOrderOtc(3);
                    } else if(payETH.isChecked()){//ETH下单{}
                        coinNum = payETHEd.getText().toString();
                        coin = payETHEd.getText().toString().trim() + "ETH";
                        newOrderOtc(4);
                    } else {
                        T.showShort("请选择支付方式");
                    }

                }
                break;
            case R.id.cb_card :
                type=1;
                cbMoney.setChecked(false);
                cbCard.setChecked(true);
                myMoneyCard();
                break;
            case R.id.cb_money :
                type=2;
                cbMoney.setChecked(true);
                cbCard.setChecked(false);
                break;
            case R.id.tv_back:
                finish();
                break;
            case R.id.ag_1:
                toWeb(1);
                break;
            case R.id.ag_2:
                toWeb(2);
                break;
            case R.id.payETH:
                coinType = "ETH";
                coin = payETHEd.getText().toString().trim() + "ETH";
                coinNum = payETHEd.getText().toString().trim();
                payETH.setChecked(true);
                payBTC.setChecked(false);
                payRMB.setChecked(false);
                showFB.setVisibility(View.GONE);
                break;
            case R.id.payBTC:
                coinType = "BTC";
                coinNum = payBTCEd.getText().toString().trim();
                coin = payBTCEd.getText().toString().trim()  + "BTC";
                payETH.setChecked(false);
                payBTC.setChecked(true);
                payRMB.setChecked(false);
                showFB.setVisibility(View.GONE);
                break;
            case R.id.payRMB:
                coin = "";
                payETH.setChecked(false);
                payBTC.setChecked(false);
                payRMB.setChecked(true);
                showFB.setVisibility(View.VISIBLE);
                break;
            case R.id.coupons:
                if(moneyNum.getText().toString().trim().isEmpty()){
                    coupons.setEnabled(false);
                }else{
                    coupons.setEnabled(true);
                    startCoupons();
                }
                break;
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_detail);
        init();
    }




    /**
     * 跳转至红包领取
     */
    private void startCoupons() {
        //得到新打开Activity关闭后返回的数据
        //第二个参数为请求码，可以根据业务需求自己编号
        startActivityForResult(new Intent(mContext, CouponsActivity.class), 1);
    }


    /**
     * 为了得到传回的数据，必须在前面的Activity中（指MainActivity类）重写onActivityResult方法
     *
     * requestCode 请求码，即调用startActivityForResult()传递过去的值
     * resultCode 结果码，结果码用于标识返回数据来自哪个新Activity
     */

    Double moneyCou = 0.0;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == 4) {
            String money =data.getStringExtra("money");
            hbTip.setVisibility(View.VISIBLE);
            hbTip.setText("红包已抵扣 "+ money + "元");
            moneyCou = Double.parseDouble(money);
            showBtc(moneyNum.getText().toString().trim() +"");
        }
    }

    /**
     * 初始化
     */
    private void init() {
        coupons.setEnabled(false);
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary), 225);
        title_name.setText("订单详情");
        ag3.setVisibility(View.GONE);
        moneyNum.setHint("该项目起投金额为" + PreferenceUtils.getPrefString(mContext, "join", "") + "元     ");
        btname.setText(PreferenceUtils.getPrefString(mContext, "oname", ""));
        myMoneyBalance();
        fbTobtc();
        moneyNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(delayRunnable!=null){
                    handler.removeCallbacks(delayRunnable);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                handler.postDelayed(delayRunnable, 800);
            }
        });

    }

    private Handler handler = new Handler();
    private Runnable delayRunnable = new Runnable() {
        @Override
        public void run() {
            if(!moneyNum.getText().toString().trim().isEmpty()){
                Double num = Double.parseDouble(moneyNum.getText().toString().trim());
                if(num >= 1000){
                    coupons.setEnabled(true);
                    showBtc(moneyNum.getText().toString().trim());
                }else{
                    T.showShort("该项目起投金额不能小于1000");
                    coupons.setEnabled(false);
                }
            }
        }
    };

    /**
     * 计算
     */
    private void showBtc(String money){
        if(!money.isEmpty()){
            Double num = Double.parseDouble(money);
            Double btc = num / Double.parseDouble(btnPrice);
            Double eth = num / Double.parseDouble(ethPrice);
            String btcVal = btc+"";
            int index =btcVal.indexOf(".");
            String ethVal = eth+"";
            int ind =btcVal.indexOf(".");
            payBTCEd.setText(btcVal.substring(0,index + 9)+"");
            payETHEd.setText(ethVal.substring(0,ind + 9)+"");
            if(moneyCou > 0.1)
                payRMBEd.setText(num - moneyCou +"RMB");
            else
                payRMBEd.setText(moneyNum.getText().toString() +"RMB");
        }
    }
    /**
     * 发币换算
     */
    public void fbTobtc(){
        OkGo.<String>get(Constants.MARKRT_URL_BASEHTTP +
                "marketData/copycat/lastPrice")// 发币换算
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        JSONObject jsonObject ;
                        try {
                            jsonObject= new JSONObject(response.body());
                            if(jsonObject.getBoolean("success")){
                                btnPrice = jsonObject.getJSONObject("data").getString("btcPrice");
                                ethPrice = jsonObject.getJSONObject("data").getString("ethPrice");
                            }else T.showShort(jsonObject.getString("message"));
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
     * 检测我的余额
     */
    public void myMoneyBalance(){
        OkGo.<String>get(Constants.URL_BASE + "order/enough")//查询我的余额
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
                                    ActivityUtils.toLogin(NumCoinNewOrderActivity.this,0);
                                    break;
                                case "200":
                                    balanceNum = jsonObject.getString("body");
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
     * 检测我的余额银行卡
     */
    public void myMoneyCard(){
        OkGo.<String>get(Constants.URL_BASE + "user/bank?type=defult")//查询我的银行卡
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
                                    ActivityUtils.toLogin(NumCoinNewOrderActivity.this,0);
                                    break;
                                case "200":
                                    if(jsonObject.toString().contains("count"))
                                        cardNum = jsonObject.getJSONObject("body").getString("count");
                                    else
                                        ActivityUtils.noCard(NumCoinNewOrderActivity.this);
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
     * 钱包支付界面
     */
    private void startPayWallet(String orderid) {
        Intent intent = new Intent(mContext,WalletPaySuccessActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("orderId",orderid);
        bundle.putString("money",coinNum);
        bundle.putString("type",coinType);
        intent.putExtras(bundle);
        startActivity(intent);
        if(ProductAIActivity.activity!=null)
            ProductAIActivity.activity.finish();
        finish();
    }

    /**
     * 银行卡支付界面
     */
    private void startPayCard(String orderid) {
        Intent intent = new Intent(mContext,CardPaySuccessActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("orderId",orderid);
        bundle.putString("cardId",cardNum);
        bundle.putString("money",moneyNum.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);
        if(ProductAIActivity.activity!=null)
        ProductAIActivity.activity.finish();
        finish();
    }


    /**
     * 余额卡支付界面
     */
    private void startPayBalance(String orderid) {
        Intent intent = new Intent(mContext,BalancePayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("orderId",orderid);
        bundle.putString("type","other");
        bundle.putString("orderName",PreferenceUtils.getPrefString(mContext,"oname",""));
        bundle.putString("orderMoney",moneyNum.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);
        if(ProductAIActivity.activity!=null)
        ProductAIActivity.activity.finish();
        finish();
    }



    /**
     * otc的下单
     *
     *
     *  String packet =PreferenceUtils.getPrefString(mContext,"couid","");
         HttpParams params = new HttpParams();
         if(packet.equals("")){//不携带红包参数
         params.put("pid",PreferenceUtils.getPrefString(mContext,"pid",""));
         params.put("payAmount",moneyNum.getText().toString().trim());
         }else {
         params.put("pid",PreferenceUtils.getPrefString(mContext,"pid",""));
         params.put("payAmount",moneyNum.getText().toString().trim());
         params.put("packetid",packet);
     }
     *
     */
    private void newOrderOtc(final int type) {
        Log.i("-------------",coin+"=====");
        mBuilder.setTitle("请求中...").show();
        HttpParams params = new HttpParams();
        params.put("pid",PreferenceUtils.getPrefString(mContext,"pid",""));
        params.put("payAmount",moneyNum.getText().toString().trim());
        params.put("coin",coin);
        OkGo.<String>put(Constants.URL_BASE + "order/newOrder")//
                .tag(this)
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        JSONObject jsonObject ;
                        try {
                            jsonObject= new JSONObject(response.body());
                            T.showShort(jsonObject.getString("msg"));
                            switch (jsonObject.getString("status")){
                                case "401":
                                    ActivityUtils.toLogin(NumCoinNewOrderActivity.this,0);
                                    break;
                                case "402":
                                    ActivityUtils.noMoney(NumCoinNewOrderActivity.this);
                                    break;
                                case "200":
                                    if(type ==1)
                                        startPayBalance(jsonObject.getJSONObject("body").getString("orderid"));
                                    else if(type == 2)
                                        startPayCard(jsonObject.getJSONObject("body").getString("orderid"));
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
     * 跳转到网页协议
     */
    private void toWeb(int i){
        String urls ;
        Intent intent = new Intent(mContext,WebViewActivity.class);
        if(i ==1){
            urls = Constants.WbUrl.webInvestmentRisk;
        }else if(i==2){
            urls = Constants.WbUrl.webAssetDelegation;
        }else {
            urls = url;
        }
        intent.putExtra("url",urls);
        startActivity(intent);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }




//    /**
//     * 用户权限检测
//     */
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
//                                    ActivityUtils.toLogin(OtherDetailActivity.this,0);
//                                    break;
//                                case "200":
//                                    payFragment.dismiss();
//                                    break;
//                                case "400":
//                                    payFragment.dismiss();
//                                    new CommonDialog.Builder(OtherDetailActivity.this)
//                                            .setMessage(jsonObject.getString("msg")).setTitle("提示")
//                                            .setPositiveButton("重试", new View.OnClickListener() {
//                                                @Override
//                                                public void onClick(View view) {
//                                                    payFragment.show(getSupportFragmentManager(), "pay");
//                                                }
//                                            }, R.color.main).setNegativeButton("忘记密码", new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                            payFragment.dismiss();
//                                            ActivityUtils.toForgetPwd(OtherDetailActivity.this);
//                                        }
//                                    },R.color.main).show();
//                                    break;
//                                case "403":
//                                    T.showShort(jsonObject.getString("msg"));
//                                    payFragment.dismiss();
//                                    ActivityUtils.toEdPwd(OtherDetailActivity.this);
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
    /**
     * 支付
     */
//    private void nowPay(String id) {
//        OkGo.<String>post(Constants.URL_BASE + "order/payOrder")//
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
//                                ActivityUtils.toLogin(OtherDetailActivity.this,0);
//                            if(jsonObject.getString("status").equals("200")){
//                                finish();
//                                ProductAIActivity.activity.finish();
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
//
//    }

}
