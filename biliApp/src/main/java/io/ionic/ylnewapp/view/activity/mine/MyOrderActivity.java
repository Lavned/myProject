package io.ionic.ylnewapp.view.activity.mine;

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
import io.ionic.ylnewapp.adpater.MyOrderAdapter;
import io.ionic.ylnewapp.bean.CoumonsBean;
import io.ionic.ylnewapp.bean.OrderBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class MyOrderActivity extends BaseActivity implements  SwipeRefreshLayout.OnRefreshListener{

    @ViewInject(R.id.tv_title)
    TextView title;
    @ViewInject(R.id.tv_1)
    TextView tv_1;
    @ViewInject(R.id.tv_2)
    TextView tv_2;
    @ViewInject(R.id.tv_3)
    TextView tv_3;
    @ViewInject(R.id.tv_4)
    TextView tv_4;
    @ViewInject(R.id.tv_5)
    TextView tv_5;
    @ViewInject(R.id.order_empty)
    TextView order_empty;



    @ViewInject(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @ViewInject(R.id.lv_order)
    RecyclerView lv_order;
    private GridLayoutManager mLayoutManager;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    MyOrderAdapter adapter;
    List<OrderBean.BodyBean> mData;

    @Event(type = View.OnClickListener.class,value ={ R.id.tv_back,R.id.tv_1,R.id.tv_2,R.id.tv_3,R.id.tv_4,R.id.tv_5})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back :
                finish();
                break;
            case R.id.tv_1 :
                setSelectView(tv_1);
                loadData(5);
                break;
            case R.id.tv_2 :
                setSelectView(tv_2);
                loadData(1);
                break;
            case R.id.tv_3 :
                setSelectView(tv_3);
                loadData(0);
                break;
            case R.id.tv_4 :
                setSelectView(tv_4);
                loadData(3);
                break;
            case R.id.tv_5 :
                setSelectView(tv_5);
                loadData(2);
                break;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        init();
    }


    /**
     *初始化
     */
    private void init() {
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
        title.setText("我的订单");
        initRefreshLayout();
        Intent intent = getIntent();
        int type = intent.getIntExtra("status",5);
        switch (type){
            case 1://已付款
                setSelectView(tv_2);
                break;
            case 2://待赎回
                setSelectView(tv_5);
                break;
            case 3://已赎回
                setSelectView(tv_4);
                break;
            case 0://未付款
                setSelectView(tv_3);
                break;
            case 5://全部
                setSelectView(tv_1);
                break;
        }
        loadData(type);
    }


    private void setSelectView(TextView tv){
        clearbg();
        tv.setTextColor(getColor(R.color.main));
        tv.setBackgroundResource(R.mipmap.selecttext);
    }
    /**
     * 初始化list
     */
    private void initRefreshLayout() {
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light);
        refreshLayout.setOnRefreshListener(this);
        mLayoutManager = new GridLayoutManager(mContext, 1);
        lv_order.setLayoutManager(mLayoutManager);
    }



    /**
     * 获取网络数据
     */
    private void loadData(final int type) {
        mBuilder.setTitle("加载中...").show();
        String url;
        if(type == 5){
            url = Constants.URL_BASE + "order/orderList";
        }else {
            url = Constants.URL_BASE + "order/orderList?status="+type;
        }
        //加载数据
        OkGo.<String>get(url)//
                .tag(this)//
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        order_empty.setVisibility(View.GONE);
                        Gson gson = new Gson();
                        OrderBean javaBean =gson.fromJson(response.body().toString(),OrderBean.class);
                        if(javaBean.getStatus() == 401){
                            ActivityUtils.toLogin(MyOrderActivity.this,0);
                        }
                        mData = javaBean.getBody();
                        if(mData!=null) {
                            initView();
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
     * 绑定数据
     */
    private void initView() {
        if(mData.size() == 0 ) {
            T.showShort("暂无数据");
            order_empty.setVisibility(View.VISIBLE);
        }
        adapter = new MyOrderAdapter(mContext,mData);
        adapter.notifyDataSetChanged();
        lv_order.setAdapter(adapter);
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
        tv_5.setTextColor(getColor(R.color.black_1));
        tv_5.setBackground(null);
    }


    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(true);
        loadData(5);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        }, 1000);
    }
}
