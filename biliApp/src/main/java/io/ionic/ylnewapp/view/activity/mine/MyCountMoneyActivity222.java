package io.ionic.ylnewapp.view.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.diegocarloslima.fgelv.lib.FloatingGroupExpandableListView;
import com.diegocarloslima.fgelv.lib.WrapperExpandableListAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.adpater.CountMoneyAdapterRight;
import io.ionic.ylnewapp.adpater.order.AllMyOrderAdapter;
import io.ionic.ylnewapp.bean.order.AllOrderBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.activity.order.MyOrderActivity;
import io.ionic.ylnewapp.view.base.BaseActivity;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class MyCountMoneyActivity222 extends BaseActivity {


    private PieChartView chart;
    //数据
    private PieChartData pieChardata;
    List<SliceValue> values = new ArrayList<>();

    private boolean hasLabels = true;
    private boolean hasLabelsOutside = true;
    private boolean hasCenterCircle = false;
    private boolean isExploded = false;
    private boolean hasLabelForSelected = false;
    List<AllOrderBean.BodyBean.OrdersBean> mData;
    List<AllOrderBean.BodyBean.OrdersBean.ListBean> listData;
    private List<Double> countData;
    private List<Integer> colorData;

    @ViewInject(R.id.my_list)
    FloatingGroupExpandableListView myList;
    @ViewInject(R.id.count)
    TextView count;
    @ViewInject(R.id.right_lv)
    ListView lvRight;
    CountMoneyAdapterRight adapterRight;


    @Event(type = View.OnClickListener.class, value = {R.id.tv_back})
    private void click(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_count_money);
        loadData();
    }


    /**
     * 网络请求
     */
    private void loadData() {
        mBuilder.setTitle("加载中...").show();
        OkGo.<String>get(Constants.URL_BASE + "order/orders")//
                .tag(this)//
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext, "token", ""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//
                        Gson gson = new Gson();
                        AllOrderBean javaBean = gson.fromJson(data.toString(), AllOrderBean.class);
                        if (javaBean.getStatus() == 401) {
                            ActivityUtils.toLogin(MyCountMoneyActivity222.this, 0);
                        }
                        if(javaBean.getBody() !=null){
                            mData = javaBean.getBody().getOrders();
                            if (mData != null) {
                                count.setText(javaBean.getBody().getAll() + "");
                                initCharts(mData);
                                initList(mData);
                            }
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
     * 设置饼图
     *
     * @param
     */
    private void initCharts(List<AllOrderBean.BodyBean.OrdersBean> mData) {
        chart = findViewById(R.id.chart);
        countData = new ArrayList<>();
        colorData = new ArrayList<>();
        for (int i = 0; i < mData.size(); i++) {
            countData.add(mData.get(i).getCount());
            colorData.add(Color.parseColor(mData.get(i).getColor()));
        }
        generateData();
    }

    /**
     * 获取数据
     */
    private void setPieChartData() {
        for (int i = 0; i < countData.size(); i++) {
            SliceValue sliceValue = new SliceValue(Float.parseFloat(""+ countData.get(i)), colorData.get(i));// 修改过数据类型
            values.add(sliceValue);
        }
    }

    /**
     * chart数据设置
     */
    private void generateData() {
        setPieChartData();
        pieChardata = new PieChartData();
        chart.setViewportCalculationEnabled(true);//设置饼图自动适应大小
        pieChardata.setHasLabels(true);//显示表情
        pieChardata.setValues(values);//填充数据
        pieChardata.setHasLabels(hasLabels);//是否展示lable
        pieChardata.setValueLabelTextSize(8);//lable字体大小
        pieChardata.setHasLabelsOnlyForSelected(hasLabelForSelected);
        pieChardata.setHasLabelsOutside(hasLabelsOutside);
        pieChardata.setHasCenterCircle(hasCenterCircle);
        pieChardata.setCenterCircleScale(0.5f);//设置环形的大小级别
        chart.setPieChartData(pieChardata);
        chart.setCircleFillRatio(0.9f);//如果chart挤压lable显示不完全，设置这个属性
        chart.setValueSelectionEnabled(false);//选择饼图某一块变大
        chart.setAlpha(0.9f);//设置透明度
        if (isExploded) {
            pieChardata.setSlicesSpacing(24);
        }
        chart.setPieChartData(pieChardata);
    }


    /**
     * 列表数据初始化
     * @param mData
     */
    private void initList(final List<AllOrderBean.BodyBean.OrdersBean> mData) {
        BaseExpandableListAdapter myAdapter = new SampleAdapter(mContext,mData);
        WrapperExpandableListAdapter wrapperAdapter = new WrapperExpandableListAdapter(myAdapter);
        myList.setAdapter(wrapperAdapter);
        myList.expandGroup(0);

        adapterRight = new CountMoneyAdapterRight(mContext,mData);
        lvRight.setAdapter(adapterRight);

        myList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                int count = myList.getExpandableListAdapter().getGroupCount();
                for (int j = 0; j < count; j++) {
                    if (j != groupPosition) {
                        myList.collapseGroup(j);
                    }
                }
            }
        });
    }

    class SampleAdapter extends BaseExpandableListAdapter {

        private final Context mContext;
        private List<AllOrderBean.BodyBean.OrdersBean> myData;
        private final LayoutInflater mLayoutInflater;


        public SampleAdapter(Context context,List<AllOrderBean.BodyBean.OrdersBean> mData) {
            myData = mData;
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getGroupCount() {
            return myData.size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return myData.get(groupPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mLayoutInflater.inflate(R.layout.expandlv_activity_list_group_item, parent, false);
            }

            TextView money_name =  convertView.findViewById(R.id.money_name);
            ImageView money_icon =  convertView.findViewById(R.id.money_icon);
            TextView money_num =  convertView.findViewById(R.id.money_num);
            money_num.setText(myData.get(groupPosition).getCount()+"");
            money_name.setText(myData.get(groupPosition).getType()+"");
            money_icon.setBackgroundColor(Color.parseColor(myData.get(groupPosition).getColor()));

//            ImageView expandedImage = convertView.findViewById(R.id.more);
//            int resId = isExpanded ? R.mipmap.dig_top : R.mipmap.dig_down_2x;
//            expandedImage.setImageResource(resId);

            return convertView;
        }



        @Override
        public int getChildrenCount(int groupPosition) {
            return myData.get(groupPosition).getList().size();
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
              return myData.get(groupPosition).getList().get(childPosition);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//            SwipeRefreshLayout refreshLayout = convertView.findViewById(R.id. refreshLayout);
//            RecyclerView lv = convertView.findViewById(R.id.ex_lv_order);
            ViewHolder holder;
            if (convertView == null) {
                holder =  new ViewHolder();
                convertView = mLayoutInflater.inflate(R.layout.expandlv_activity_list_child_item, parent, false);
                holder.refreshLayout = convertView.findViewById(R.id. refreshLayout);
//                holder.lv = convertView.findViewById(R.id.ex_lv_order);
//                holder.lv = convertView.findViewById(R.id.ex_lv_order);
                holder.selectMore= convertView.findViewById(R.id.selectMore);
                holder.in = convertView.findViewById(R.id.money_in);
                holder.out = convertView.findViewById(R.id.money_out);
                holder.fb = convertView.findViewById(R.id.fb);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }

            if(groupPosition == 0){
                holder.fb.setVisibility(View.VISIBLE);
                holder.lv.setVisibility(View.GONE);
            }else {
                holder.fb.setVisibility(View.GONE);
                holder.lv.setVisibility(View.VISIBLE);
            }

            if(childPosition > 3 ){
                holder.selectMore.setVisibility(View.VISIBLE);
            }else {
                holder.selectMore.setVisibility(View.GONE);
            }

            GridLayoutManager mLayoutManager;
            Handler mHandler = new Handler(Looper.getMainLooper());
            holder.refreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light,
                    android.R.color.holo_orange_light, android.R.color.holo_green_light);
            holder.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {

                }
            });
            mLayoutManager = new GridLayoutManager(mContext, 1);
            holder.lv.setLayoutManager(mLayoutManager);



            listData = null;
            listData =  mData.get(groupPosition).getList();
            Log.i("===",mData.get(groupPosition).getList().get(childPosition).getOrderid());
            AllMyOrderAdapter adapter = new AllMyOrderAdapter(mContext,listData);
            adapter.notifyDataSetChanged();
            holder.lv.setAdapter(adapter);

            holder.selectMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,MyOrderActivity.class);
                    intent.putExtra("type",mData.get(groupPosition).getKey());
                    startActivity(intent);
                }
            });

            holder.out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PreferenceUtils.setPrefString(mContext,"money",mData.get(groupPosition).getCount()+"");
                    startActivity(new Intent(mContext,WithdrawalActivity.class));
                }
            });
            holder.in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(mContext,RechargeActivity.class));
                }
            });

            return convertView;
        }

       private class  ViewHolder{
//            ListView lv;
            TextView selectMore;
            TextView in ;
            TextView out ;
            LinearLayout fb;
           SwipeRefreshLayout refreshLayout ;
           RecyclerView lv;
        }


        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }


    }


}
