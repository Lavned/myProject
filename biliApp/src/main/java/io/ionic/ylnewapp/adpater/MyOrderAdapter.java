package io.ionic.ylnewapp.adpater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.qqtheme.framework.widget.HorizontalListView;
import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.CoumonsBean;
import io.ionic.ylnewapp.bean.OrderBean;
import io.ionic.ylnewapp.httpOrder.OrderUtils;
import io.ionic.ylnewapp.utils.DateUtil;
import io.ionic.ylnewapp.utils.T;

/**
 * Created by mogojing on 2018/5/30/0030.
 */

public class MyOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<OrderBean.BodyBean> mData;

    private List<OrderBean.BodyBean.RateBean> lsData;
    private List<String> gvData;
    private MyListAdapter myAdapter;
    private OrderBtnAdapter btnAdapter;

    public MyOrderAdapter(Context mContext, List<OrderBean.BodyBean> mData) {
        mLayoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.mData = mData;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.order_items, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final OrderBean.BodyBean item = mData.get(position);
        ((ViewHolder) holder).order_name.setText(item.getName());
        ((ViewHolder) holder).order_date.setText("购买时间："+item.getCreated());
        ((ViewHolder) holder).order_endDate.setText("到期时间："+item.getOpen());
        ((ViewHolder) holder).order_money.setText("" + item.getPayAmount());
        ((ViewHolder) holder).order_num.setText("订单号：" + item.getOrderid());
        ((ViewHolder) holder).order_status.setText(item.getStatus());
        if(item!=null && item.getStatus() !=null){
            if(item.getStatus().equals("已赎回") || item.getStatus().equals("已付款")){
                ((ViewHolder) holder).order_status.setTextColor(mContext.getColor(R.color.green));
            }else{
                ((ViewHolder) holder).order_status.setTextColor(mContext.getColor(R.color.red));
            }
        }
        lsData = item.getRate();
        myAdapter = new MyListAdapter(mContext,lsData);
        ((ViewHolder) holder).order_items_lv.setAdapter(myAdapter);
        gvData = item.getBtn();
        if(gvData!=null){
            switch (gvData.size()){
                case 0:
                    ((ViewHolder) holder).allBtn.setVisibility(View.GONE);
                    break;
                case 1:
                    ((ViewHolder) holder).order_btn.setNumColumns(1);
                    break;
                case 2:
                    ((ViewHolder) holder).order_btn.setNumColumns(2);
                    break;
                case 3:
                    ((ViewHolder) holder).order_btn.setNumColumns(3);
                    break;
            }
            btnAdapter = new OrderBtnAdapter(mContext,gvData);
            ((ViewHolder) holder).order_btn.setAdapter(btnAdapter);
            ((ViewHolder) holder).order_btn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    T.showShort("kk"+parent.getAdapter().getItem(position));
                    switch (parent.getAdapter().getItem(position)+""){
                        case "1" :
                            OrderUtils.payOrder(mContext,item.getOrderid());
//                            holder.tv.setText("继续支付");
                            break;
                        case "2" :
//                            holder.tv.setText("取消订单");
                            T.showShort("订单很快就更新了");
                            break;
                        case "3" :
//                            holder.tv.setText("我要挂单");
                            OrderUtils.putOrder(mContext,item.getOrderid());
                            break;
                        case "4" :
//                            holder.tv.setText("修改挂单");
                            break;
                        case "5" :
//                            holder.tv.setText("撤销挂单");
                            OrderUtils.cancelPut(mContext,item.getOrderid());
                            break;
                        case "6" :
//                            holder.tv.setText("续投");
                            OrderUtils.goOrder(mContext,item.getOrderid());
                            break;
                        case "7" :
//                            holder.tv.setText("赎回");
                            OrderUtils.backOrder(mContext,item.getOrderid());
                            break;
                    }
                }
            });
        }else{
            ((ViewHolder) holder).allBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
         return mData == null ? 0 : mData.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView order_name;
        private TextView order_num,order_money;
        private LinearLayout allBtn;
        private TextView order_status;
        private TextView order_date,order_endDate;
        private ListView order_items_lv;
        private GridView order_btn;

        public ViewHolder(View itemView) {
            super(itemView);
            order_name = itemView.findViewById(R.id.order_name);
            order_num = itemView.findViewById(R.id.order_num);
            order_money = itemView.findViewById(R.id.order_benjin);
            order_status = itemView.findViewById(R.id.order_status);
            order_date = itemView.findViewById(R.id.order_date);
            order_endDate = itemView.findViewById(R.id.order_enddate);
            allBtn = itemView.findViewById(R.id.all_btn);
            order_items_lv = itemView.findViewById(R.id.order_items_lv);
            order_btn = itemView.findViewById(R.id.gv_btn);
        }
    }

}
