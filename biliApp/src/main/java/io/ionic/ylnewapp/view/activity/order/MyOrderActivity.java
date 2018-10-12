package io.ionic.ylnewapp.view.activity.order;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.adpater.MyOrderAdapter;
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
    @ViewInject(R.id.untips)
    TextView untips;



    @ViewInject(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @ViewInject(R.id.lv_order)
    RecyclerView lv_order;
    private GridLayoutManager mLayoutManager;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    MyOrderAdapter adapter;
    List<OrderBean.BodyBean> mData;

    @Event(type = View.OnClickListener.class,value ={ R.id.tv_back})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back :
                finish();
        }
    }



    String url;

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
        title.setText("我的订单");
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
        initRefreshLayout();
        Intent intent = getIntent();
        int status = intent.getIntExtra("status",1);
        if(status == 3){//赎回数字资产的订单
            title.setText("已赎回数字资产");
            url =  Constants.URL_BASE + "order/orderList?status="+status+"&type=DIG";
        }else if(status == 0 ){
            untips.setVisibility(View.VISIBLE);
            url = Constants.URL_BASE + "order/orderList?status="+status;
        }else if(status == 33 ){
            title.setText("已回款订单");
            url =  Constants.URL_BASE + "order/orderList?status="+3+"";
        }
        String type = intent.getStringExtra("type");
        if(type!=null){
            url = Constants.URL_BASE + "order/orderList?type="+type;
        }
        loadData(url);
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
    private void loadData(String url) {
        mBuilder.setTitle("加载中...").show();
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
//        loadData(5);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        }, 1000);
    }
}
