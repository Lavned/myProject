package io.ionic.ylnewapp.view.activity.order;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class MoreOrderActivity extends BaseActivity implements  SwipeRefreshLayout.OnRefreshListener{

    @ViewInject(R.id.tv_title)
    TextView title;
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

    @Event(type = View.OnClickListener.class,value ={ R.id.tv_back})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back :
                finish();
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
        title.setText("我的订单");
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
        initRefreshLayout();
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        if(type!=null){
            switch (type){
                case "OTC"://
                    title.setText("我的订单");
                    break;
                case "AI"://
                    title.setText("我的订单");
                    break;
                case "ICO"://
                    title.setText("我的订单");
                    break;
                case "ETF"://
                    title.setText("我的订单");
                    break;
                case "BTC"://
                    title.setText("我的订单");
                    break;
            }
            loadData(1,type);
        }

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
    private void loadData(final  int conditions,final String conditionsName) {
        mBuilder.setTitle("加载中...").show();
        String url;
        if(conditions == 0){
            url = Constants.URL_BASE + "order/orderList?status="+conditionsName;
        }else {
            url = Constants.URL_BASE + "order/orderList?type="+conditionsName;
        }
        Log.i("---------",url);
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
                            ActivityUtils.toLogin(MoreOrderActivity.this,0);
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
