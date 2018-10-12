package io.ionic.ylnewapp.view.activity.numcoin;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiangyy.easydialog.LoadingDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.adpater.products.InviteAdapter;
import io.ionic.ylnewapp.adpater.products.NewOTCAdapter;
import io.ionic.ylnewapp.bean.InviteSuccessBean;
import io.ionic.ylnewapp.bean.products.NewOTCBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;

@ContentView(R.layout.product_frag_new)//加载布局
public class FragmentInvite1 extends Fragment implements PullLoadMoreRecyclerView.PullLoadMoreListener{



    @ViewInject(R.id.new2)
    LinearLayout new2;
    @ViewInject(R.id.inviteLL)
    LinearLayout inviteLL;
    @ViewInject(R.id.payAmount)
    TextView payAmount;
    @ViewInject(R.id.people)
    TextView people;

    @ViewInject(R.id.pullProductView)
    PullLoadMoreRecyclerView pullProductView;
    private RecyclerView mRecyclerView;
    public LoadingDialog.Builder mBuilder;
    private InviteAdapter adapter;

    List<InviteSuccessBean.BodyBean.ListsBean> newList;


    public FragmentInvite1() {
    }

    public static FragmentInvite1 newInstance() {
        FragmentInvite1 fragment = new FragmentInvite1();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view =  x.view().inject(this,inflater,container);
        initView();
        return view;
    }


    //数据初始化
    private void initView() {
        if(PreferenceUtils.getPrefString(getActivity(),"token","").equals("")){
            ActivityUtils.toLogin(getActivity(),1);
        }
        mBuilder = new LoadingDialog.Builder(getActivity());
        new2.setVisibility(View.GONE);
        inviteLL.setVisibility(View.VISIBLE);
        payAmount.setVisibility(View.VISIBLE);
        initRefrensh();
        initData();
    }

    /**
     * 初始化刷新布局
     */
    private void initRefrensh() {
        //获取mRecyclerView对象
        mRecyclerView = pullProductView.getRecyclerView();
        //代码设置scrollbar无效？未解决！
        mRecyclerView.setVerticalScrollBarEnabled(true);
        //显示下拉刷新
        pullProductView.setRefreshing(true);
        //设置上拉刷新文字
        pullProductView.setFooterViewText("刷新中...");
        pullProductView.setLinearLayout();
        pullProductView.setOnPullLoadMoreListener(this);
        adapter = new InviteAdapter(getActivity());
        pullProductView.setAdapter(adapter);
    }

    /**
     * 获取数据
     * @param list
     */
    private void getData(final List<InviteSuccessBean.BodyBean.ListsBean> list) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addAllData(setList(list));
                        pullProductView.setPullLoadMoreCompleted();
                    }
                });

            }
        }, 100);

    }


    /**
     * 数据源
     * @param list
     * @return
     */
    private List<InviteSuccessBean.BodyBean.ListsBean> setList(List<InviteSuccessBean.BodyBean.ListsBean> list) {
        List<InviteSuccessBean.BodyBean.ListsBean> dataList = new ArrayList<>();
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
                        pullProductView.setPullLoadMoreCompleted();
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
        OkGo.<String>get(Constants.URL_BASE + "invite/invites?username=" +
                PreferenceUtils.getPrefString(getActivity(),"account",""))//
                .tag(this)//
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//
                        Gson gson = new Gson();
                        InviteSuccessBean javaBean =gson.fromJson(data.toString(),InviteSuccessBean.class);
                        if(javaBean.getStatus() == 401)
                            ActivityUtils.toLogin(getActivity(),1);
                        if(javaBean.getStatus() == 200){
                            newList = javaBean.getBody().getLists();
                            payAmount.setText("奖励金额："+ javaBean.getBody().getMoneys());
                            people.setText("投资人数："+ javaBean.getBody().getPeople());
                            if(newList!=null && newList.size()>0) {
                                getData(newList);
                            }
                            else {
                                pullProductView.setPullLoadMoreCompleted();
                                T.showShort("暂无数据");
                            }
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
