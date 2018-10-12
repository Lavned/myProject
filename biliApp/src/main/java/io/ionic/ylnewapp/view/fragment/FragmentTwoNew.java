package io.ionic.ylnewapp.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.jiangyy.easydialog.LoadingDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.adpater.market.MarketFragAdapter3;
import io.ionic.ylnewapp.adpater.products.NewOTCAdapter;
import io.ionic.ylnewapp.adpater.products.OTCAdapter;
import io.ionic.ylnewapp.bean.market.CapitalFlowsBean;
import io.ionic.ylnewapp.bean.products.BETBean;
import io.ionic.ylnewapp.bean.products.NewOTCBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.T;

import static io.ionic.ylnewapp.constants.Constants.MARKRT_URL_BASEHTTP;

/**
 * Created by mogojing on 2018/6/12/0012.
 */
@ContentView(R.layout.product_frag_new)//加载布局
public class FragmentTwoNew extends Fragment implements PullLoadMoreRecyclerView.PullLoadMoreListener {

    @ViewInject(R.id.pullProductView)
    PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private RecyclerView mRecyclerView;
    public LoadingDialog.Builder mBuilder;
    private NewOTCAdapter adapter;

    List<NewOTCBean.OTCBean>  newList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = x.view().inject(this, inflater, container);
        initView();
        return view;
    }

    //数据初始化
    private void initView() {
        mBuilder = new LoadingDialog.Builder(getActivity());
        initRefrensh();
        initData();
    }

    /**
     * 初始化刷新布局
     */
    private void initRefrensh() {
        //获取mRecyclerView对象
        mRecyclerView = mPullLoadMoreRecyclerView.getRecyclerView();
        //代码设置scrollbar无效？未解决！
        mRecyclerView.setVerticalScrollBarEnabled(true);
        //显示下拉刷新
        mPullLoadMoreRecyclerView.setRefreshing(true);
        //设置上拉刷新文字
        mPullLoadMoreRecyclerView.setFooterViewText("刷新中...");
        //设置上拉刷新文字颜色
        //mPullLoadMoreRecyclerView.setFooterViewTextColor(R.color.white);
        //设置加载更多背景色
        //mPullLoadMoreRecyclerView.setFooterViewBackgroundColor(R.color.colorBackground);
        mPullLoadMoreRecyclerView.setLinearLayout();
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        adapter = new NewOTCAdapter(getActivity());
        mPullLoadMoreRecyclerView.setAdapter(adapter);
    }


    /**
     * 获取数据
     * @param list
     */
    private void getData(final List<NewOTCBean.OTCBean> list) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addAllData(setList(list));
                        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                    }
                });

            }
        }, 500);

    }


    /**
     * 数据源
     * @param list
     * @return
     */
    private List<NewOTCBean.OTCBean> setList(List<NewOTCBean.OTCBean> list) {
        List<NewOTCBean.OTCBean> dataList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            dataList.add(list.get(i));
        }
        return dataList;

    }

    @Override
    public void onRefresh() {
        setRefresh();
        initData();
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                    }
                });

            }
        }, 500);
    }

    private void setRefresh() {
        adapter.clearData();
    }

    /**
     * 网络请求
     */
        private void initData() {
            //加载数据
            mBuilder.setTitle("加载中...").show();
            OkGo.<String>get(Constants.URL_BASE + "product/products?type=OTC")//
                    .tag(this)//
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            String data = response.body();//
                            Gson gson = new Gson();
                            NewOTCBean javaBean =gson.fromJson(data.toString(),NewOTCBean.class);
                            newList = javaBean.getOTC();
                            if(newList!=null)
                                if(newList.size()>0){
                                    getData(newList);
                                }
                        }

                        @Override
                        public void onError(Response<String> response) {
                            super.onError(response);
                            T.showNetworkError(getActivity());
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
}