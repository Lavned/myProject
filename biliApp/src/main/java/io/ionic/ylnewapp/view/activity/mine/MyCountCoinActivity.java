package io.ionic.ylnewapp.view.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.umeng.analytics.MobclickAgent;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.adpater.order.MyDigOrderAdapter;
import io.ionic.ylnewapp.bean.order.MyDIGBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.custom.ViewpagerTransformAnim;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.activity.order.MyOrderActivity;
import io.ionic.ylnewapp.view.activity.wallet.WalletAddMoneyActivity;
import io.ionic.ylnewapp.view.activity.wallet.WalletOutMoneyActivity;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class MyCountCoinActivity extends BaseActivity {

    @ViewInject(R.id.tv_title)
    TextView title;

    @ViewInject(R.id.cardViewPager)
    ViewPager mViewPager;
//    @ViewInject(R.id.num_lv)
//    ListView num_lv;

//    @ViewInject(R.id.refreshLayout)
//    SwipeRefreshLayout refreshLayout;
//    @ViewInject(R.id.lv_num)
//    RecyclerView lv_num;
    private GridLayoutManager mLayoutManager;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    MyDigOrderAdapter orderAdapter;

    //背景图
    int [] bg = new int[]{R.mipmap.me_zc_bg1_2x,R.mipmap.me_zc_bg2_2x,R.mipmap.me_zc_bg3_2x,R.mipmap.me_zc_bg2_2x};

    List<MyDIGBean.BodyBean> myData ;

    @Event(type = View.OnClickListener.class,value ={ R.id.tv_back,R.id.tv_ysh})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back :
                finish();
                break;
            case R.id.tv_ysh:
                Intent intent = new Intent(mContext, MyOrderActivity.class);
                intent.putExtra("status",3);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_count_coin);
        init();
        loadData();
    }

    private void init() {
        MobclickAgent.onEvent(mContext, "Balance2");
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary),225);
        title.setText("我的数字资产");
//        initRefreshLayout();
    }

    /**
     * 初始化list
     */
    private void initRefreshLayout(final SwipeRefreshLayout refreshLayout,RecyclerView lv) {
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(true);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
        mLayoutManager = new GridLayoutManager(mContext, 1);
        lv.setLayoutManager(mLayoutManager);
    }

    /**
     * 网络请求
     */
    private void loadData() {
        mBuilder.setTitle("加载中...").show();
        OkGo.<String>get(Constants.URL_BASE + "order/orderList?type=DIG")//
                .tag(this)//
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext, "token", ""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        MyDIGBean javaBean =gson.fromJson(response.body().toString(),MyDIGBean.class);
                        if(javaBean.getStatus() == 401)
                            ActivityUtils.toLogin(MyCountCoinActivity.this,0);

                            myData = javaBean.getBody();
                            if(myData!=null && myData.size() >0){
                                initViewpager(myData);
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
     * lunbo
     * @param myData
     */
    private void initViewpager(List<MyDIGBean.BodyBean> myData) {
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(new MyPagerAdapter(myData,this));
        mViewPager.setPageMargin(-60);
        mViewPager.setPageTransformer(true, new ViewpagerTransformAnim());
    }


    /**
     * 适配器
     */
    List<MyDIGBean.BodyBean.ListsBean> lists;
    class MyPagerAdapter extends PagerAdapter {
        private List<MyDIGBean.BodyBean> mData;
        private Context mContext;
        public MyPagerAdapter( List<MyDIGBean.BodyBean> data,Context context) {
            this.mContext = context;
            this.mData = data;
        }

//        @Override
//        public float getPageWidth(int position) {
//            return 0.9f;
//        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View inflate = LayoutInflater.from(container.getContext()).inflate(R.layout.number_items, container, false);
            LinearLayout lin_num = inflate.findViewById(R.id.lin_num);
            TextView num_name =  inflate.findViewById(R.id.num_name);
            TextView num_total =  inflate.findViewById(R.id.num_total);
            TextView num_mount =  inflate.findViewById(R.id.num_mount);
            TextView num_in =  inflate.findViewById(R.id.num_in);
            TextView num_out =  inflate.findViewById(R.id.num_out);
            ImageView lv_empty = inflate.findViewById(R.id.lv_empty);

            SwipeRefreshLayout refreshLayout =  inflate.findViewById(R.id.refreshLayout);
            RecyclerView lv_num =  inflate.findViewById(R.id.lv_num);
            initRefreshLayout(refreshLayout,lv_num);

            final int i  = position % mData.size();

            lin_num.setBackgroundResource(bg[i]);
            num_name.setText(mData.get(i).getType().toUpperCase()+"余额");
            num_total.setText(mData.get(i).getTotal()+""+mData.get(i).getType().toUpperCase());//mData.get(i).getType().toUpperCase()
            num_mount.setText(mData.get(i).getMount()+"");

            lists = mData.get(i).getLists();
            if(lists!=null && lists.size() > 0){
                orderAdapter = new MyDigOrderAdapter(mContext,lists);
                orderAdapter.notifyDataSetChanged();
                lv_num.setAdapter(orderAdapter);
            }else {
                lv_empty.setVisibility(View.VISIBLE);
            }




            num_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,WalletAddMoneyActivity.class);
                    intent.putExtra("name",mData.get(i).getType());
                    startActivity(intent);
                }
            });
            num_out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,WalletOutMoneyActivity.class);
                    intent.putExtra("name",mData.get(i).getType());
                    startActivity(intent);
                }
            });

            container.addView(inflate);
            return inflate;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(((View) object));
            lists = null;

        }
    }

    /**
     * dingdanshuju
     * @param lists
     */
    private void initListView(List<MyDIGBean.BodyBean.ListsBean> lists) {
//        orderAdapter = new MyDigOrderAdapter(mContext,lists);
//        num_lv.setAdapter(orderAdapter);
//        orderAdapter = new MyDigOrderAdapter(mContext,lists);
//        orderAdapter.notifyDataSetChanged();
//        lv_num.setAdapter(orderAdapter);
    }


}
