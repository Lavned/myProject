package io.ionic.ylnewapp.adpater.products;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.InviteSuccessBean;
import io.ionic.ylnewapp.utils.DateUtil;
import io.ionic.ylnewapp.utils.StringUtils;

/**
 * Created by mogojing on 2018/9/4/0004.
 */



/**
 * Created by mogojing on 2018/9/4/0004.
 */

public class InviteAdapter  extends RecyclerView.Adapter<InviteAdapter.ViewHolder> {

    private Context mContext;
    private List<InviteSuccessBean.BodyBean.ListsBean> dataList = new ArrayList<>();


    public void addAllData(List<InviteSuccessBean.BodyBean.ListsBean> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.dataList.clear();
    }

    public InviteAdapter(Context context) {
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView inTime,inNumber,inType,inRate,inAmount,inType2;

        public ViewHolder(View itemView) {
            super(itemView);
            inTime =  itemView.findViewById(R.id.inTime);
            inNumber =  itemView.findViewById(R.id.inNumber);
            inType =  itemView.findViewById(R.id.inType);
            inAmount =  itemView.findViewById(R.id.inAmount);
            inRate =  itemView.findViewById(R.id.inRate);
            inType2 = itemView.findViewById(R.id.inType2);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.invite_lv_items, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final InviteSuccessBean.BodyBean.ListsBean item   = dataList.get(position);
        holder.inTime.setText(DateUtil.getYmdHMSforJson(item.getCreated()).replace("T"," "));
        holder.inNumber.setText(StringUtils.getPhone(item.getUsername())+"");
        holder.inType.setText(item.getStage()+"");
        holder.inRate.setText(item.getRate()+"");
        holder.inType2.setText(item.getKind());
        holder.inAmount.setText(""+item.getPayAmount());
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
