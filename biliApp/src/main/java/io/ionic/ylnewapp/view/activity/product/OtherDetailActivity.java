package io.ionic.ylnewapp.view.activity.product;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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



    @ViewInject(R.id.tz_number)
    EditText editText;
    @ViewInject(R.id.tz_rate)
    TextView rate;
    @ViewInject(R.id.tz_week)
    TextView week;

    @ViewInject(R.id.cb_agreed)
    CheckBox cbAgreed;
//    @ViewInject(R.id.cb_card)
//    CheckBox cbCard;
    @ViewInject(R.id.cb_money)
    CheckBox cbMoney;
    @ViewInject(R.id.title_name)
    TextView title_name;
    @ViewInject(R.id.ag_1)
    TextView ag1;
    @ViewInject(R.id.ag_2)
    TextView ag2;
    @ViewInject(R.id.ag_3)
    TextView ag3;
    @ViewInject(R.id.hb)
    RelativeLayout hb;
    @ViewInject(R.id.hb_tip)
    TextView hbTip;



    String url ="";
//    int payType =1;
    PayFragment payFragment = new PayFragment();

    @Event(type = View.OnClickListener.class,value = {R.id.pay_btn,R.id.cb_money,R.id.cb_card,R.id.cb_agreed,R.id.back_3
                    ,R.id.hb,R.id.ag_1,R.id.ag_2,R.id.ag_3})
    private void click(View v){
        switch (v.getId()){
            case R.id.pay_btn :
                if(editText.getText().toString().trim().equals(""))
                    T.showShort("起购金额必须大于1000");
                else {
                    if(PreferenceUtils.getPrefString(mContext,"hasPay","").equals("true"))
                        checkAuth();
                    else
                        ActivityUtils.hasPay(OtherDetailActivity.this);
                }
                break;
            case R.id.cb_card :
                cbMoney.setChecked(false);
//                cbCard.setChecked(true);
//                payType = 0;
                break;
            case R.id.back_3:
                finish();
                break;
            case R.id.hb:
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
        StatusBarUtil.setTranslucentForImageViewInFragment(this, 0,null);
    }

    /**
     * 用户权限检测
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
                                    ActivityUtils.toLogin(OtherDetailActivity.this,0);
                                    break;
                                case "200":
                                    payFragment.dismiss();
                                    selectHttp();
                                    break;
                                case "400":
                                    payFragment.dismiss();
                                    new CommonDialog.Builder(OtherDetailActivity.this)
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
                                            ActivityUtils.toForgetPwd(OtherDetailActivity.this);
                                        }
                                    },R.color.main).show();
                                    break;
                                case "403":
                                    T.showShort(jsonObject.getString("msg"));
                                    payFragment.dismiss();
                                    ActivityUtils.toEdPwd(OtherDetailActivity.this);
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
     *  TETF 和TBTC 还有其他的子界面是不一样的
     */
    private void selectHttp(){
        switch (PreferenceUtils.getPrefString(mContext,"KEY","")){
            case "TETF":
                newOrder();
                break;
            case "TBTC" :
                newOrder();
                break;
            case "OTC" :
                newOrderOtc();
                break;
            default:
                newOrderOther();
                break;
        }
    }

    /**
     * 下单
     */
    private void newOrder() {
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
     *  非TBTC与TETF的下单
     */
    private void newOrderOther() {
        mBuilder.setTitle("请求中...").show();
        OkGo.<String>put(Constants.URL_BASE + "order/newOrder")//
                .tag(this)
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .params("pid",PreferenceUtils.getPrefString(mContext,"pid",""))
                .params("payAmount",editText.getText().toString().trim())
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
     * otc的下单
     */
    private void newOrderOtc() {
        String packet =PreferenceUtils.getPrefString(mContext,"couid","");
        mBuilder.setTitle("请求中...").show();
        HttpParams params = new HttpParams();
        if(packet.equals("")){//不携带红包参数
            params.put("pid",PreferenceUtils.getPrefString(mContext,"pid",""));
            params.put("payAmount",editText.getText().toString().trim());
        }else {
            params.put("pid",PreferenceUtils.getPrefString(mContext,"pid",""));
            params.put("payAmount",editText.getText().toString().trim());
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
                                ActivityUtils.toLogin(OtherDetailActivity.this,0);
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
            hb.setEnabled(false);
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
        title_name.setText(PreferenceUtils.getPrefString(mContext,"oname",""));
        week.setText(PreferenceUtils.getPrefString(mContext,"oweek",""));
        rate.setText(PreferenceUtils.getPrefString(mContext,"orate",""));
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
                hb.setVisibility(View.VISIBLE);
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
                editText.setText(PreferenceUtils.getPrefString(mContext,"moneys",""));
                editText.setEnabled(false);
                ag3.setVisibility(View.GONE);
                break;
            case "TBTC":
                editText.setText(PreferenceUtils.getPrefString(mContext,"moneys",""));
                editText.setEnabled(false);
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
}
