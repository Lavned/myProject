package io.ionic.ylnewapp.view.activity.market;

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
import io.ionic.ylnewapp.bean.market.CapitalFlowsBean;
import io.ionic.ylnewapp.utils.T;

import static io.ionic.ylnewapp.constants.Constants.MARKRT_URL_BASEHTTP;

/**
 * Created by mogojing on 2018/6/12/0012.
 */
@ContentView(R.layout.market_frag_03)//加载布局
public class MarketFragment3 extends Fragment implements PullLoadMoreRecyclerView.PullLoadMoreListener {

    @ViewInject(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private RecyclerView mRecyclerView;
    public LoadingDialog.Builder mBuilder;
    private MarketFragAdapter3 adapter;


    public int pageSize = 20;
    public int pageNum = 1;
    int count;

    @ViewInject(R.id.szImg)
    ImageView szImg;
    @ViewInject(R.id.jlImg)
    ImageView jlImg;
    @ViewInject(R.id.jlbImg)
    ImageView jlbImg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = x.view().inject(this, inflater, container);
        initView();
        return view;
    }



    @Event(type = View.OnClickListener.class, value = {R.id.condition1, R.id.condition2, R.id.condition3, R.id.condition4})
    private void allClick(View view) {
        switch (view.getId()) {
            case R.id.condition1:
                break;
            case R.id.condition2:
                jlbImg.setImageResource(R.mipmap.hq_icon3_default_2x);
                jlImg.setImageResource(R.mipmap.hq_icon3_default_2x);
                sortData(szImg,"marketcap");
                break;
            case R.id.condition3:
                jlbImg.setImageResource(R.mipmap.hq_icon3_default_2x);
                szImg.setImageResource(R.mipmap.hq_icon3_default_2x);
                sortData(jlImg,"netFlow");
                break;
            case R.id.condition4:
                szImg.setImageResource(R.mipmap.hq_icon3_default_2x);
                jlImg.setImageResource(R.mipmap.hq_icon3_default_2x);
                sortData(jlbImg,"netPercent");
                break;
        }
    }


    /**
     * 排序数据
     */
    public void sortData(ImageView view,String sort){
        count ++;
        if(count % 2 == 1){
            view.setImageResource(R.mipmap.hq_icon1_down_2x);
            adapter.clearData();
            adapter.notifyDataSetChanged();
            loadData(pageSize,1,sort + ",desc");
        }else {
            view.setImageResource(R.mipmap.hq_icon2_on_2x);
            adapter.clearData();
            adapter.notifyDataSetChanged();
            loadData(pageSize,1,sort + ",asc");
        }
    }
    //数据初始化
    private void initView() {
        mBuilder = new LoadingDialog.Builder(getActivity());
        initRefrensh();
        loadData(pageSize,pageNum,"");
    }

    /**
     * 初始化刷新布局
     */
    private void initRefrensh() {
        //获取mRecyclerView对象
        mRecyclerView = mPullLoadMoreRecyclerView.getRecyclerView();
        //代码设置scrollbar无效？未解决！
        mRecyclerView.setVerticalScrollBarEnabled(true);
        //设置下拉刷新是否可见
        //mPullLoadMoreRecyclerView.setRefreshing(true);
        //设置是否可以下拉刷新
        //mPullLoadMoreRecyclerView.setPullRefreshEnable(true);
        //设置是否可以上拉刷新
        //mPullLoadMoreRecyclerView.setPushRefreshEnable(false);
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
        adapter = new MarketFragAdapter3(getActivity());
        mPullLoadMoreRecyclerView.setAdapter(adapter);
    }


    /**
     * 获取数据
     * @param list
     */
    private void getData(final List<CapitalFlowsBean.DataBean> list) {
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
        }, 1000);

    }


    /**
     * 数据源
     * @param list
     * @return
     */
    private List<CapitalFlowsBean.DataBean> setList(List<CapitalFlowsBean.DataBean> list) {
        List<CapitalFlowsBean.DataBean> dataList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            dataList.add(list.get(i));
        }
        return dataList;

    }

    @Override
    public void onRefresh() {
        setRefresh();
        loadData(pageSize, pageNum,"");
    }

    @Override
    public void onLoadMore() {
        pageNum = pageNum + 1;
        loadData(pageSize, pageNum,"");
    }

    private void setRefresh() {
        adapter.clearData();
        pageNum = 1;
    }

    /**
     * 网络请求
     *
     * @param pageSize
     * @param pageNum
     * //排序字段，默认按照市值倒序排列，可排序的字段有 marketcap:市值 netPercent:净流入百分比 netFlow:净流入 格式：字段名,升序\降序(asc|desc)
     */
    public void loadData(int pageSize, int pageNum,String sort) {
        mBuilder.setTitle("加载中...").show();
        OkGo.<String>get( MARKRT_URL_BASEHTTP + "marketData/capitalFlows?offset="+pageNum
                +"&limit="+pageSize+"&sort=" + sort)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        CapitalFlowsBean notiBean = gson.fromJson(response.body(), CapitalFlowsBean.class);
                        if (notiBean.getData() != null && notiBean.getData().size() > 0) {
                            getData(notiBean.getData());
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
                        mBuilder.dismiss();
                    }
                });
    }
}