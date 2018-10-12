package io.ionic.ylnewapp.adpater.products;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.InviteBean;
import io.ionic.ylnewapp.utils.DateUtil;
import io.ionic.ylnewapp.utils.StringUtils;

/**
 * Created by mogojing on 2018/9/4/0004.
 */


/**
 * Created by mogojing on 2018/9/4/0004.
 */

public class InviteAdapter2 extends RecyclerView.Adapter<InviteAdapter2.ViewHolder> {

    private Context mContext;
    private List<InviteBean.BodyBean.ListsBean> dataList = new ArrayList<>();


    public void addAllData(List<InviteBean.BodyBean.ListsBean> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.dataList.clear();
    }

    public InviteAdapter2(Context context) {
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView inTime,inNumber,inType,inRate,inAmount,inType2,showTime;
        private LinearLayout bottomLL;

        public ViewHolder(View itemView) {
            super(itemView);
            inTime =  itemView.findViewById(R.id.inTime);
            inNumber =  itemView.findViewById(R.id.inNumber);
            inType =  itemView.findViewById(R.id.inType);
            inAmount =  itemView.findViewById(R.id.inAmount);
            inRate =  itemView.findViewById(R.id.inRate);
            inType2 = itemView.findViewById(R.id.inType2);
            bottomLL = itemView.findViewById(R.id.bottomLL);
            showTime = itemView.findViewById(R.id.showTime);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.invite_lv_items, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final InviteBean.BodyBean.ListsBean item   = dataList.get(position);
        holder.showTime.setText("注册时间");
        holder.bottomLL.setVisibility(View.GONE);
        holder.inTime.setText(DateUtil.getYmdHMSforJson(item.getCreated()).replace("T"," "));
        holder.inNumber.setText(StringUtils.getPhone(item.getUserid())+"");
        holder.inType.setText(item.getStage()+"");
        holder.inType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
