package io.ionic.ylnewapp.adpater.products;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.products.NewOTCBean;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.view.activity.product.ProductNumCoinDetatilActivity;

/**
 * Created by mogojing on 2018/9/4/0004.
 */

public class NewOTCAdapter  extends RecyclerView.Adapter<NewOTCAdapter.ViewHolder> {

    private Context mContext;
    private List<NewOTCBean.OTCBean> dataList = new ArrayList<>();


    public void addAllData(List<NewOTCBean.OTCBean> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.dataList.clear();
    }

    public NewOTCAdapter(Context context) {
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name,content1,content2,number,day,btnVal,content3,content4;

        public ViewHolder(View itemView) {
            super(itemView);
            name =  itemView.findViewById(R.id.item_name);
            content1 =  itemView.findViewById(R.id.item_content_1);
            content2 =  itemView.findViewById(R.id.item_content_2);
            number =  itemView.findViewById(R.id.item_number);
            day =  itemView.findViewById(R.id.tv_day);
            btnVal = itemView.findViewById(R.id.item_btn);
            content3 =  itemView.findViewById(R.id.content_3);
            content4 =  itemView.findViewById(R.id.content_4);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.two_recycle_items, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final NewOTCBean.OTCBean item   = dataList.get(position);
        if(item.isState()){
            holder.btnVal.setEnabled(false);
            holder.btnVal.setBackgroundResource(R.mipmap.lockbtn);
        }else {
            holder.btnVal.setEnabled(true);
            holder.btnVal.setBackgroundResource(R.mipmap.main_btn);
        }
        holder.name.setText(item.getName());
        holder.content1.setText(item.getContent().get(0));
        holder.content2.setText(item.getContent().get(1));
        holder.number.setText(item.getRate());
        holder.day.setText(item.getWeek());
        holder.btnVal.setText(""+item.getBtn());
        holder.content3.setText(item.getTitleRate());
        holder.content4.setText(""+item.getTitleWeek());
        holder.btnVal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceUtils.setPrefString(mContext,"pid",item.getPid());
                PreferenceUtils.setPrefString(mContext,"orate",item.getRate());
                PreferenceUtils.setPrefString(mContext,"oname",item.getName());
                PreferenceUtils.setPrefString(mContext,"oweek",item.getWeek());
                PreferenceUtils.setPrefString(mContext,"join",item.getJoin());
                PreferenceUtils.setPrefString(mContext,"KEY",item.getKey());
                mContext.startActivity(new Intent(mContext, ProductNumCoinDetatilActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
