package io.ionic.ylnewapp.view.activity.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
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
import io.ionic.ylnewapp.adpater.MyNewAdapter;
import io.ionic.ylnewapp.bean.response.CoinBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class SelectCurrency extends BaseActivity implements  SwipeRefreshLayout.OnRefreshListener{

    @ViewInject(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @ViewInject(R.id.recyclerView)
    RecyclerView recyclerView;
    @ViewInject(R.id.tv_title)
    TextView title;
    @ViewInject(R.id.textView3)
    ImageView textView3;

    List<CoinBean.BodyBean>mData;
    MyNewAdapter adapter;

    private int lastVisibleItem = 0;
    private final int PAGE_COUNT = 10;
    private GridLayoutManager mLayoutManager;
    private Handler mHandler = new Handler(Looper.getMainLooper());


    @Event(type = View.OnClickListener.class,value = {R.id.tv_back,R.id.textView3})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.textView3:
                startActivity(new Intent(mContext,CurrencySearchActivity.class));
                finish();
                break;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_currency);
        init();
        getList();
    }

    /**
     *初始化
     */
    private void init() {
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
        title.setText("选择币种");
        textView3.setVisibility(View.VISIBLE);
        textView3.setImageResource(R.mipmap.f_search2x);
        initRefreshLayout();
    }

    //view参数设置
    private void initRefreshLayout() {
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light);
        refreshLayout.setOnRefreshListener(this);
    }

    private void initView(List<CoinBean.BodyBean> mData) {
        if(mData.size() ==0) {
            T.showShort("暂无数据");
        }
        adapter = new MyNewAdapter(getDatas(0, PAGE_COUNT), mContext, getDatas(0,PAGE_COUNT ).size() > 0 ? true : false);
        mLayoutManager = new GridLayoutManager(mContext, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (adapter.isFadeTips() == false && lastVisibleItem + 1 == adapter.getItemCount()) {
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                updateRecyclerView(adapter.getRealLastPosition(), adapter.getRealLastPosition() + PAGE_COUNT);
                            }
                        }, 500);
                    }

                    if (adapter.isFadeTips() == true && lastVisibleItem + 2 == adapter.getItemCount()) {
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                updateRecyclerView(adapter.getRealLastPosition(), adapter.getRealLastPosition() + PAGE_COUNT);
                            }
                        }, 500);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });
    }


    private List<CoinBean.BodyBean> getDatas(final int firstIndex, final int lastIndex) {
        List<CoinBean.BodyBean> resList = new ArrayList<>();
        for (int i = firstIndex; i < lastIndex; i++) {
            if (i < mData.size()) {
                resList.add(mData.get(i));
            }
        }
        return resList;
    }


    /**
     * 提交信息
     */
    private void getList() {
        mBuilder.setTitle("加载中...").show();
        OkGo.<String>get(Constants.URL_BASE + "coins/coin" )
                .tag(this)
                .cacheTime(1000)
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        CoinBean javaBean =gson.fromJson(response.body().toString(),CoinBean.class);
                        if(javaBean.getStatus() == 401){
                            ActivityUtils.toLogin(SelectCurrency.this,0);
                        }
                        mData = javaBean.getBody();
                        if(mData!= null){
                            initView(mData);
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


    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(true);
//        initData("");
        if(adapter!=null){
            adapter.resetDatas();
            updateRecyclerView(0, PAGE_COUNT);
        }

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        }, 1000);
    }

    private void updateRecyclerView(int fromIndex, int toIndex) {
        List<CoinBean.BodyBean> newDatas = getDatas(fromIndex, toIndex);
        if (newDatas.size() > 0) {
            adapter.updateList(newDatas, true);
        } else {
            adapter.updateList(null, false);
        }
    }


}

