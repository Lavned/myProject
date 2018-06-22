package io.ionic.ylnewapp.view.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.adpater.CouponsAdapter;
import io.ionic.ylnewapp.bean.CoumonsBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class CouponsActivity extends BaseActivity  implements  SwipeRefreshLayout.OnRefreshListener{


    @ViewInject(R.id.tv_title)
    TextView title;
    @ViewInject(R.id.coupons_lin)
    LinearLayout coupons_lin;
    @ViewInject(R.id.tv_1)
    TextView tv_1;
    @ViewInject(R.id.tv_2)
    TextView tv_2;
    @ViewInject(R.id.tv_3)
    TextView tv_3;
    @ViewInject(R.id.tv_4)
    TextView tv_4;
    @ViewInject(R.id.cou_empty)
    TextView cou_empty;



    @ViewInject(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @ViewInject(R.id.lv_coupons)
    RecyclerView lvCoupons;
    private GridLayoutManager mLayoutManager;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    CouponsAdapter adapter;
    List<CoumonsBean.BodyBean> mData;

    List<CoumonsBean.BodyBean> mData0;
    List<CoumonsBean.BodyBean> mData2;
    List<CoumonsBean.BodyBean> mData1;


   public static Activity activity;


    @Event(type = View.OnClickListener.class,value ={ R.id.tv_back,R.id.tv_1,R.id.tv_2,R.id.tv_3,R.id.tv_4})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back :
                finish();
                break;
            case R.id.tv_1 :
                showView(1);
                tv_1.setTextColor(getColor(R.color.main));
                tv_1.setBackgroundResource(R.mipmap.selecttext);
                loadData(1);
                break;
            case R.id.tv_2 :
                showView(1);
                tv_2.setTextColor(getColor(R.color.main));
                tv_2.setBackgroundResource(R.mipmap.selecttext);
                loadData(2);
                break;
            case R.id.tv_3 :
                showView(1);
                tv_3.setTextColor(getColor(R.color.main));
                tv_3.setBackgroundResource(R.mipmap.selecttext);
                loadData(0);
                break;
            case R.id.tv_4 :
                showView(2);
                tv_4.setTextColor(getColor(R.color.main));
                tv_4.setBackgroundResource(R.mipmap.selecttext);
                break;
        }
    }


    public static int type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons);
        activity = this;
        init();
        Intent intent = getIntent();
        if (intent!=null){
            type = intent.getIntExtra("type",0);
        }
        loadData(1);
    }


    /**
     * 获取网络数据
     */
    private void loadData(final int type) {
        mBuilder.setTitle("加载中...").show();
        //加载数据
        OkGo.<String>get(Constants.URL_BASE + "user/packet")//
                .tag(this)//
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        cou_empty.setVisibility(View.GONE);
                        Gson gson = new Gson();
                        CoumonsBean javaBean =gson.fromJson(response.body().toString(),CoumonsBean.class);
                        if(javaBean.getStatus() == 401){
                            ActivityUtils.toLogin(CouponsActivity.this,0);
                        }
                        mData = javaBean.getBody();
                        if(mData!=null) {
                            switch (type){
                                case 1:
                                    mData1 = new ArrayList<>();
                                    for (int i = 0; i < mData.size() ;i++){
                                        if(mData.get(i).getStatus().equals("1")){
                                            mData1.add(mData.get(i));
                                        }
                                    }
                                    if(mData1.size() == 0){
                                        cou_empty.setVisibility(View.VISIBLE);
                                        refreshLayout.setVisibility(View.GONE);
                                        return;
                                    }
                                    adapter = new CouponsAdapter(mContext, mData1);
                                    break;
                                case 2:
                                    mData2 = new ArrayList<>();
                                    for (int i = 0; i < mData.size() ;i++){
                                        if(mData.get(i).getStatus().equals("2")){
                                            mData2.add(mData.get(i));
                                        }
                                    }
                                    if(mData2.size() == 0){
                                        cou_empty.setVisibility(View.VISIBLE);
                                        refreshLayout.setVisibility(View.GONE);
                                        return;
                                    }
                                    adapter = new CouponsAdapter(mContext, mData2);
                                    break;
                                case 0:
                                    mData0 = new ArrayList<>();
                                    for (int i = 0; i < mData.size() ;i++){
                                        if(mData.get(i).getStatus().equals("0")){
                                            mData0.add(mData.get(i));
                                        }
                                    }
                                    if(mData0.size() == 0){
                                        cou_empty.setVisibility(View.VISIBLE);
                                        refreshLayout.setVisibility(View.GONE);
                                        return;
                                    }
                                    adapter = new CouponsAdapter(mContext, mData0);
                                    break;
                            }
                            adapter.notifyDataSetChanged();
                            lvCoupons.setAdapter(adapter);
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
     * 初始化list
     */
    private void initRefreshLayout() {
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light);
        refreshLayout.setOnRefreshListener(this);
        mLayoutManager = new GridLayoutManager(mContext, 1);
        lvCoupons.setLayoutManager(mLayoutManager);
    }

    /**
     *初始化
     */
    private void init() {
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
        title.setText("我的优惠券");
        initRefreshLayout();
    }

    private void showView(int type) {
        clearbg();
        if(type ==1 ){
            coupons_lin.setVisibility(View.GONE);
            lvCoupons.setVisibility(View.VISIBLE);
            refreshLayout.setVisibility(View.VISIBLE);
        }else {
            coupons_lin.setVisibility(View.VISIBLE);
            refreshLayout.setVisibility(View.GONE);
            lvCoupons.setVisibility(View.GONE);
            cou_empty.setVisibility(View.GONE);
        }

    }

    //控制页面展示
    private void clearbg() {
        tv_1.setTextColor(getColor(R.color.black_1));
        tv_1.setBackground(null);
        tv_2.setTextColor(getColor(R.color.black_1));
        tv_2.setBackground(null);
        tv_3.setTextColor(getColor(R.color.black_1));
        tv_3.setBackground(null);
        tv_4.setTextColor(getColor(R.color.black_1));
        tv_4.setBackground(null);

    }

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(true);
        loadData(1);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        }, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
