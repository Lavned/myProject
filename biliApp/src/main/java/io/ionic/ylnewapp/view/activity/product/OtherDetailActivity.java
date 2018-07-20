package io.ionic.ylnewapp.view.activity.product;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.jiangyy.easydialog.CommonDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
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
import io.ionic.ylnewapp.view.activity.mine.CouponsActivity;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class OtherDetailActivity extends BaseActivity {



    @ViewInject(R.id.moneyNum)
    EditText moneyNum; //投资金额
    @ViewInject(R.id.tv_title)
    TextView title_name; //标题
    @ViewInject(R.id.btname)
    TextView btname; //投资项目名称
    @ViewInject(R.id.balance)
    TextView balance;  //余额显示

    @ViewInject(R.id.cb_agreed)
    CheckBox cbAgreed;//同意阅读协议
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
    //优惠券
    @ViewInject(R.id.coupons)
    LinearLayout coupons;
    @ViewInject(R.id.hb_tip)
    TextView hbTip;

    String url ="";
    String balanceNum ="";
    String cardNum="";

    @Event(type = View.OnClickListener.class,value = {R.id.pay_btn,R.id.cb_money,R.id.cb_card,R.id.cb_agreed,R.id.tv_back
                    ,R.id.coupons,R.id.ag_1,R.id.ag_2,R.id.ag_3})
    private void click(View v){
        switch (v.getId()){
            case R.id.pay_btn :
                if(moneyNum.getText().toString().trim().equals(""))
                    T.showShort("起购金额必须大于1000");
                else {
                    if (Double.parseDouble(moneyNum.getText().toString().trim()) > Double.parseDouble(balanceNum)) {
                        balance.setText("(您的余额为" + balanceNum + "，不足于支付)");
                    }else{
                        if(cbMoney.isChecked()) { //余额支付
                            selectHttp(1);//余额足够直接下单
                        }else //银行卡下单不支付
                            selectHttp(2);
                    }
                }
                break;
            case R.id.cb_card :
                cbMoney.setChecked(false);
                cbCard.setChecked(true);
                myMoneyCard();
                break;
            case R.id.cb_money :
                cbMoney.setChecked(true);
                cbCard.setChecked(false);
                break;
            case R.id.tv_back:
                finish();
                break;
            case R.id.coupons:
                startCoupons();
                break;
            case R.id.ag_1:
                toWeb(1);
                break;
            case R.id.ag_2:
                toWeb(2);
                break;
            case R.id.ag_3:
                toWeb(0);
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
     * 初始化
     */
    private void init() {
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
        title_name.setText("订单详情");
        myMoneyBalance();
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
                                    ActivityUtils.toLogin(OtherDetailActivity.this,0);
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
                                    ActivityUtils.toLogin(OtherDetailActivity.this,0);
                                    break;
                                case "200":
                                    if(jsonObject.toString().contains("count"))
                                        cardNum = jsonObject.getJSONObject("body").getString("count");
                                    else
                                        ActivityUtils.noCard(OtherDetailActivity.this);
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
        bundle.putString("orderName",PreferenceUtils.getPrefString(mContext,"btname",""));
        bundle.putString("orderMoney",moneyNum.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);
        if(ProductAIActivity.activity!=null)
        ProductAIActivity.activity.finish();
        finish();
    }


    /**
     *  TETF 和TBTC 还有其他的子界面是不一样的
     */
    private void selectHttp(int type){
        switch (PreferenceUtils.getPrefString(mContext,"KEY","")){
            case "TETF":
                newOrder(type);
                break;
            case "TBTC" :
                newOrder(type);
                break;
            case "OTC" :
                newOrderOtc(type);
                break;
            default:
                newOrderOther(type);
                break;
        }
    }

    /**
     * 下单
     */
    private void newOrder(final int type) {
        mBuilder.setTitle("请求中...").show();
        OkGo.<String>put(Constants.URL_BASE + "order/newOrder")//
                .tag(this)//
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .params("orderid",PreferenceUtils.getPrefString(mContext,"orderid",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        JSONObject jsonObject ;
                        try {
                            jsonObject= new JSONObject(response.body());
                            T.showShort(jsonObject.getString("msg"));
                            switch (jsonObject.getString("status")){
                                case "401":
                                    ActivityUtils.toLogin(OtherDetailActivity.this,0);
                                    break;
                                case "402":
                                    ActivityUtils.noMoney(OtherDetailActivity.this);
                                    break;
                                case "200":
                                    if(type ==1)
                                        startPayBalance(jsonObject.getJSONObject("body").getString("orderid"));
                                    else
                                        startPayCard(jsonObject.getJSONObject("body").getString("orderid"));
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
     *  非TBTC与TETF的下单
     */
    private void newOrderOther(final  int type) {
        mBuilder.setTitle("请求中...").show();
        OkGo.<String>put(Constants.URL_BASE + "order/newOrder")//
                .tag(this)
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .params("pid",PreferenceUtils.getPrefString(mContext,"pid",""))
                .params("payAmount",moneyNum.getText().toString().trim())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        JSONObject jsonObject ;
                        try {
                            jsonObject= new JSONObject(response.body());
                            T.showShort(jsonObject.getString("msg"));
                            switch (jsonObject.getString("status")){
                                case "401":
                                    ActivityUtils.toLogin(OtherDetailActivity.this,0);
                                    break;
                                case "402":
                                    ActivityUtils.noMoney(OtherDetailActivity.this);
                                    break;
                                case "200":
                                    if(type ==1)
                                        startPayBalance(jsonObject.getJSONObject("body").getString("orderid"));
                                    else
                                        startPayCard(jsonObject.getJSONObject("body").getString("orderid"));
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
     * otc的下单
     */
    private void newOrderOtc(final  int type) {
        String packet =PreferenceUtils.getPrefString(mContext,"couid","");
        mBuilder.setTitle("请求中...").show();
        HttpParams params = new HttpParams();
        if(packet.equals("")){//不携带红包参数
            params.put("pid",PreferenceUtils.getPrefString(mContext,"pid",""));
            params.put("payAmount",moneyNum.getText().toString().trim());
        }else {
            params.put("pid",PreferenceUtils.getPrefString(mContext,"pid",""));
            params.put("payAmount",moneyNum.getText().toString().trim());
            params.put("packetid",packet);
        }
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
                                    ActivityUtils.toLogin(OtherDetailActivity.this,0);
                                    break;
                                case "402":
                                    ActivityUtils.noMoney(OtherDetailActivity.this);
                                    break;
                                case "200":
                                    if(type ==1)
                                        startPayBalance(jsonObject.getJSONObject("body").getString("orderid"));
                                    else
                                        startPayCard(jsonObject.getJSONObject("body").getString("orderid"));
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
     * 跳转至红包领取
     */
    private void startCoupons() {
        Intent intent = new Intent(mContext, CouponsActivity.class);
        int type;
        if(PreferenceUtils.getPrefString(mContext,"oname","").contains("七日")){
            type = 1;
            intent.putExtra("type",type);
            startActivity(intent);
        }else if(PreferenceUtils.getPrefString(mContext,"oname","").contains("新月")){
            type = 2;
            intent.putExtra("type",type);
            startActivity(intent);
        }else {
            coupons.setEnabled(false);
            hbTip.setVisibility(View.VISIBLE);
        }
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


    /**
     * 控件数据赋值
     */
    private void setView() {
        btname.setText(PreferenceUtils.getPrefString(mContext,"oname",""));
        switch (PreferenceUtils.getPrefString(mContext,"KEY","")){
            case "AI":
                ag3.setText("《智能投顾招募说明》");
                url = Constants.WbUrl.webAiInstruction;
                break;
            case "ETF":
                ag3.setText("《ETF指数基金招募说明书》");
                url = Constants.WbUrl.webeEthAgreement;
                break;
            case "OTC":
                coupons.setVisibility(View.VISIBLE);
                ag3.setVisibility(View.GONE);
                String money = PreferenceUtils.getPrefString(mContext,"coumoney","");
                if(!money.equals("")){
                    hbTip.setVisibility(View.VISIBLE);
                    hbTip.setText("红包已抵扣 "+money + "元");
                }
                break;
            case "BTC":
                ag3.setText("《BTC招募说明书摘要》");
                url = Constants.WbUrl.webBtcRecruit;
                break;
            case "ICO":
                ag3.setText("《ICO协议书》");
                url = Constants.WbUrl.webIcoProtocol;
                break;
            case "TETF":
                moneyNum.setText(PreferenceUtils.getPrefString(mContext,"moneys",""));
                moneyNum.setEnabled(false);
                ag3.setVisibility(View.GONE);
                break;
            case "TBTC":
                moneyNum.setText(PreferenceUtils.getPrefString(mContext,"moneys",""));
                moneyNum.setEnabled(false);
                ag3.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setView();
    }

    @Override
    public void onStop() {
        super.onStop();
        PreferenceUtils.setPrefString(mContext, "coumoney", "");
        PreferenceUtils.setPrefString(mContext, "couid", "");
    }


    @Override
    protected void onDestroy() {
        PreferenceUtils.setPrefString(mContext, "coumoney", "");
        PreferenceUtils.setPrefString(mContext, "couid", "");
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
