package io.ionic.ylnewapp.view.twofragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.adpater.products.ETFAdapter;
import io.ionic.ylnewapp.bean.products.ETFBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.T;

/**
 * Created by cmo on 16-7-21.
 */
public class Tab3Fragment extends Fragment implements  SwipeRefreshLayout.OnRefreshListener{


    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;

    private int lastVisibleItem = 0;
    private final int PAGE_COUNT = 10;
    private GridLayoutManager mLayoutManager;
    private ETFAdapter adapter;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    List<ETFBean> digList;

    Activity context;

    //为获取数据创建布尔值
    private boolean isViewShown = false;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragemt_tab_01, container, false);
        findView(view);
        initRefreshLayout();
        context = getActivity();
        if(!isViewShown){
            initData();
        }
        return view;
    }


    /**
     * 视图是否已经对用户可见，系统的方法
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getView()!= null){
            isViewShown = true;
            // 包含当页面被选择时显示数据的逻辑主要asynctask填充数据
            initData();
            onRefresh();
        } else {
            isViewShown = false;
        }

    }


    private void initData() {
        //加载数据
        OkGo.<String>get(Constants.URL_BASE + "product/products?type=ETF")//
                .tag(this)//
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//
                        Gson gson = new Gson();
                        ETFBean javaBean =gson.fromJson(data.toString(),ETFBean.class);
                        digList = javaBean.getETF();
                        if(digList!=null)
                            if(digList.size()>0){
                                initRecyclerView();
                            }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showNetworkError(getActivity());
                    }

                });

    }


    private void findView(View view) {
        refreshLayout =view.findViewById(R.id.refreshLayout);
        recyclerView = view.findViewById(R.id.recyclerView);

    }

    private void initRefreshLayout() {
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light);
        refreshLayout.setOnRefreshListener(this);
    }

    private void initRecyclerView() {
        adapter = new ETFAdapter(getDatas(0, PAGE_COUNT), getActivity(), getDatas(0, PAGE_COUNT).size() > 0 ? true : false);
        mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
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

    private List<ETFBean> getDatas(final int firstIndex, final int lastIndex) {
        List<ETFBean> resList = new ArrayList<>();
        for (int i = firstIndex; i < lastIndex; i++) {
            if (i < digList.size()) {
                resList.add(digList.get(i));
            }
        }
        return resList;
    }

    private void updateRecyclerView(int fromIndex, int toIndex) {
        List<ETFBean> newDatas = getDatas(fromIndex, toIndex);
        if (newDatas.size() > 0) {
            adapter.updateList(newDatas, true);
        } else {
            adapter.updateList(null, false);
        }
    }

    @Override
    public void onRefresh() {
        if (refreshLayout==null){
            return;
        }
        refreshLayout.setRefreshing(true);
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
}

