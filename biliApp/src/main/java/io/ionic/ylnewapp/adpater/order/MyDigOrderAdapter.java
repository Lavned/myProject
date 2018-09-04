package io.ionic.ylnewapp.adpater.order;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jiangyy.easydialog.InputDialog;

import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.adpater.OrderBtnAdapter;
import io.ionic.ylnewapp.bean.order.MyDIGBean;
import io.ionic.ylnewapp.httpOrder.OrderUtils;
import io.ionic.ylnewapp.utils.T;

/**
 * Created by mogojing on 2018/6/27/0027.
 */

public class MyDigOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<MyDIGBean.BodyBean.ListsBean> mData;

    private List<MyDIGBean.BodyBean.ListsBean.RateBean> lsData;
    private List<String> gvData;
    private DigListAdapter myAdapter;
    private OrderBtnAdapter btnAdapter;

    public MyDigOrderAdapter(Context mContext, List<MyDIGBean.BodyBean.ListsBean> mData) {
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
        final MyDIGBean.BodyBean.ListsBean item = mData.get(position);
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
        myAdapter = new DigListAdapter(mContext,lsData);
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
                    switch (parent.getAdapter().getItem(position)+""){
                        case "1" :
                            OrderUtils.payOrder(mContext,item.getOrderid());
//                            holder.tv.setText("继续支付");
                            break;
                        case "2" :
//                            holder.tv.setText("取消订单");
                            OrderUtils.delOrder(mContext,item.getOrderid());
                            notifyItemRemoved(position);//
                            notifyDataSetChanged();
                            break;
                        case "3" :
//                            holder.tv.setText("我要挂单");
                            new InputDialog.Builder((Activity) mContext)
                                    .setTitle("挂单金额")
                                    .setHint("请谨慎输入挂单金额")
                                    .setInputType(EditorInfo.TYPE_CLASS_NUMBER)
                                    .setLines(1)
                                    .setPositiveButton("确定", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            T.showShort(view.getTag().toString());
                                            OrderUtils.putOrder(mContext,item.getOrderid(),view.getTag().toString());
                                        }
                                    },R.color.main).setNegativeButton("取消", null).show();
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
//                            mData.remove(position);
//                            notifyItemRemoved(position);//
//                            notifyDataSetChanged();
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
//        extends BaseAdapter {
//    private LayoutInflater mLayoutInflater;
//    private Context mContext;
//
//    private List<MyDIGBean.BodyBean.ListsBean> mData;
//    private List<MyDIGBean.BodyBean.ListsBean.RateBean> lsData;
//    private List<String> gvData;
//    private DigListAdapter myAdapter;
//    private OrderBtnAdapter btnAdapter;
//
//    public MyDigOrderAdapter(Context mContext, List<MyDIGBean.BodyBean.ListsBean> mData) {
//        mLayoutInflater = LayoutInflater.from(mContext);
//        this.mContext = mContext;
//        this.mData = mData;
//    }
//
//
//    @Override
//    public int getCount() {
//        return mData == null ? 0 : mData.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return mData.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        ViewHolder holder;
//        if (convertView == null) {
//            holder = new ViewHolder();
//            convertView = mLayoutInflater.inflate(R.layout.order_items, parent, false);
//            holder.order_name = convertView.findViewById(R.id.order_name);
//            holder.order_num = convertView.findViewById(R.id.order_num);
//            holder.order_money = convertView.findViewById(R.id.order_benjin);
//            holder.order_status = convertView.findViewById(R.id.order_status);
//            holder.order_date = convertView.findViewById(R.id.order_date);
//            holder.order_endDate = convertView.findViewById(R.id.order_enddate);
//            holder.allBtn = convertView.findViewById(R.id.all_btn);
//            holder.order_items_lv = convertView.findViewById(R.id.order_items_lv);
//            holder.order_btn = convertView.findViewById(R.id.gv_btn);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//
//        final MyDIGBean.BodyBean.ListsBean item = mData.get(position);
//        holder.order_name.setText(item.getName());
//        holder.order_date.setText("购买时间：" + item.getCreated());
//        holder.order_endDate.setText("到期时间：" + item.getOpen());
//        holder.order_money.setText("" + item.getPayAmount());
//        holder.order_num.setText("订单号：" + item.getOrderid());
//        holder.order_status.setText(item.getStatus());
//        if (item != null && item.getStatus() != null) {
//            if (item.getStatus().equals("已赎回") || item.getStatus().equals("已付款")) {
//                holder.order_status.setTextColor(mContext.getColor(R.color.green));
//            } else {
//                holder.order_status.setTextColor(mContext.getColor(R.color.red));
//            }
//        }
//        lsData = item.getRate();
//        myAdapter = new DigListAdapter(mContext, lsData);
//        holder.order_items_lv.setAdapter(myAdapter);
//
//        gvData = item.getBtn();
//        if (gvData != null) {
//            holder.allBtn.setVisibility(View.VISIBLE);
//            switch (gvData.size()) {
//                case 0:
//                    holder.allBtn.setVisibility(View.GONE);
//                    break;
//                case 1:
//                    holder.order_btn.setNumColumns(1);
//                    break;
//                case 2:
//                    holder.order_btn.setNumColumns(2);
//                    break;
//                case 3:
//                    holder.order_btn.setNumColumns(3);
//                    break;
//            }
//            btnAdapter = new OrderBtnAdapter(mContext, gvData);
//            holder.order_btn.setAdapter(btnAdapter);
//            holder.order_btn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    switch (parent.getAdapter().getItem(position) + "") {
//                        case "1":
//                            OrderUtils.payOrder(mContext, item.getOrderid());
////                            holder.tv.setText("继续支付");
//                            break;
//                        case "2":
////                            holder.tv.setText("取消订单");
//                            T.showShort("订单很快就更新了");
//                            break;
//                        case "3":
////                            holder.tv.setText("我要挂单");
//                            OrderUtils.putOrder(mContext, item.getOrderid());
//                            break;
//                        case "4":
////                            holder.tv.setText("修改挂单");
//                            break;
//                        case "5":
////                            holder.tv.setText("撤销挂单");
//                            OrderUtils.cancelPut(mContext, item.getOrderid());
//                            break;
//                        case "6":
////                            holder.tv.setText("续投");
//                            OrderUtils.goOrder(mContext, item.getOrderid());
//                            break;
//                        case "7":
////                            holder.tv.setText("赎回");
//                            OrderUtils.backOrder(mContext, item.getOrderid());
//                            break;
//                    }
//                }
//            });
//        } else {
//            holder.allBtn.setVisibility(View.GONE);
//        }
//
//        return convertView;
//    }
//
//    //holder
//    class ViewHolder {
//        private TextView order_name;
//        private TextView order_num, order_money;
//        private LinearLayout allBtn;
//        private TextView order_status;
//        private TextView order_date, order_endDate;
//        private ListView order_items_lv;
//        private MyGridView order_btn;
//
//    }

