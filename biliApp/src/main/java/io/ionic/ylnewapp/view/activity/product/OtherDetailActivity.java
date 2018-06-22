package io.ionic.ylnewapp.view.activity.product;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.jiangyy.easydialog.CommonDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

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


    Context context;
    private int index = 0;//textview上下滚动下标
    private Handler handler = new Handler();
    private boolean isFlipping = false; // 是否启用预警信息轮播
    private List<String> mWarningTextList = new ArrayList<>();
    @ViewInject(R.id.text_switcher)
    TextSwitcher mTextSwitcher;


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
        setView();
        mWarningTextList.add("请在有效期内及时付款，超时订单将自动取消");
        setTextSwitcher();
        setData();
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
                            if(jsonObject.getString("status").equals("401"))
                                ActivityUtils.toLogin(OtherDetailActivity.this,0);
                            if(jsonObject.getString("status").equals("200")){
                                  nowPay(jsonObject.getJSONObject("body").getString("orderid"));
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
                            if(jsonObject.getString("status").equals("401"))
                                ActivityUtils.toLogin(OtherDetailActivity.this,0);
                            if(jsonObject.getString("status").equals("200")){
                                nowPay(jsonObject.getJSONObject("body").getString("orderid"));
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
        mBuilder.setTitle("请求中...").show();
        OkGo.<String>put(Constants.URL_BASE + "order/newOrder")//
                .tag(this)
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .params("pid",PreferenceUtils.getPrefString(mContext,"pid",""))
                .params("payAmount",editText.getText().toString().trim())
                .params("packetid",PreferenceUtils.getPrefString(mContext,"couid",""))
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
                                  nowPay(jsonObject.getJSONObject("body").getString("orderid"));
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
                ag3.setVisibility(View.GONE);
                break;
            case "TBTC":
                ag3.setVisibility(View.GONE);
                break;
        }
    }



    //跑马灯设置
    private void setTextSwitcher() {
        mTextSwitcher.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_in_bottom));
        mTextSwitcher.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_out_top));
        mTextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(mContext);
                textView.setSingleLine();
                textView.setTextSize(12);//字号
                textView.setTextColor(getColor(R.color.main));
                textView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
                textView.setSingleLine();
                textView.setText("请在有效期内及时付款，超时订单将自动取消");
                textView.setGravity(Gravity.CENTER);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                params.gravity = Gravity.CENTER;
                textView.setLayoutParams(params);
                textView.setPadding(25, 0, 25, 0);
                return textView;
            }
        });
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (!isFlipping) {
                return;
            }
            index++;
            mTextSwitcher.setText(mWarningTextList.get(index % mWarningTextList.size()));
            if (index == mWarningTextList.size()) {
                index = 0;
            }
            startFlipping();
        }
    };

    //开启信息轮播
    public void startFlipping() {
        if (mWarningTextList.size() > 0) {
            handler.removeCallbacks(runnable);
            isFlipping = true;
            handler.postDelayed(runnable, 3000);
        }
    }

    //关闭信息轮播
    public void stopFlipping() {
        if (mWarningTextList.size() > 0) {
            isFlipping = false;
            handler.removeCallbacks(runnable);
        }
    }

    //设置数据
    private void setData() {
        if (mWarningTextList.size() == 0) {
            mTextSwitcher.setText(mWarningTextList.get(0));
            index = 0;
        }
        if (mWarningTextList.size() > 0) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mTextSwitcher.setText(mWarningTextList.get(0));
                    index = 0;
                }
            }, 1000);
            mTextSwitcher.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_in_bottom));
            mTextSwitcher.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_out_top));
            startFlipping();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        startFlipping();
    }

    @Override
    public void onStop() {
        super.onStop();
        stopFlipping();
        PreferenceUtils.setPrefString(mContext, "coumoney", "");
    }


    @Override
    protected void onDestroy() {
        PreferenceUtils.setPrefString(mContext, "coumoney", "");
        super.onDestroy();
    }
}
