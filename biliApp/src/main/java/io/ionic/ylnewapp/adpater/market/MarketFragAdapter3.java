package io.ionic.ylnewapp.adpater.market;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.market.CapitalFlowsBean;
import io.ionic.ylnewapp.view.activity.mine.NotificationActivity;

/**
 * Created by mogojing on 2018/6/7/0007.
 */

public class MarketFragAdapter3  extends RecyclerView.Adapter<MarketFragAdapter3.ViewHolder> {

    private Context mContext;
    private List<CapitalFlowsBean.DataBean> dataList = new ArrayList<>();


    public void addAllData(List<CapitalFlowsBean.DataBean> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.dataList.clear();
    }

    public MarketFragAdapter3(Context context) {
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView left1,left2,left3,left4;
        private ImageView leftIcon;
        private TextView center1,center2,center3;
        private TextView right1,right2,right3;

        public ViewHolder(View itemView) {
            super(itemView);
            left1 =  itemView.findViewById(R.id.leftOne);
            left2 =  itemView.findViewById(R.id.leftTwo);
            left3 =  itemView.findViewById(R.id.leftThree);
            left4 =  itemView.findViewById(R.id.leftFour);
            leftIcon =  itemView.findViewById(R.id.leftIcon);
            center1 =  itemView.findViewById(R.id.cenOne);
            center2 =  itemView.findViewById(R.id.cenTwo);
            center3 =  itemView.findViewById(R.id.cenThree);
            right1 =  itemView.findViewById(R.id.rightOne);
            right2 =  itemView.findViewById(R.id.rightTwo);
            right3 =  itemView.findViewById(R.id.rightThree);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mark_fragment3_items, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CapitalFlowsBean.DataBean item = dataList.get(position);
        holder.left1.setText("市值"+item.getSeq()+"# "+item.getMarketcap());
        holder.left2.setText(""+item.getTickerSymbol());
        holder.left3.setText("价格"+item.getPrice());
        holder.left4.setText("(" + item.getChange24h()+")");
        Glide.with(mContext).load(item.getIconUrl()).placeholder(R.mipmap.qb_logo1_2x).into(holder.leftIcon);

        holder.center1.setText("" + item.getInFlow());
        holder.center2.setText("" + item.getOutFlow());
        String test = item.getNetFlow().substring(0,1);
        if (!test.equals("+")){
            holder.center3.setBackgroundResource(R.drawable.red10bg);
            holder.center3.setText("" + dataList.get(position).getNetFlow());
        }else{
            holder.center3.setText("" + dataList.get(position).getNetFlow());
            holder.center3.setBackgroundResource(R.drawable.green10bg);
        }

        holder.right1.setText("" + item.getInFlowPercent());
        holder.right2.setText("" + item.getOutFlowPercent());
        if (item.getNetPercent().contains("-")){
            holder.right3.setBackgroundResource(R.drawable.red10bg);
            holder.right3.setText("" + item.getNetPercent());
        }else{
            holder.right3.setBackgroundResource(R.drawable.green10bg);
            holder.right3.setText("" + item.getNetPercent());
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}