package io.ionic.ylnewapp.adpater;

import android.content.Context;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.CoumonsBean;
import io.ionic.ylnewapp.utils.DateUtil;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.view.activity.mine.CouponsActivity;
import io.ionic.ylnewapp.view.main.MainActivity;

/**
 * Created by mogojing on 2018/5/30/0030.
 */

public class CouponsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<CoumonsBean.BodyBean> mData;

    public CouponsAdapter(Context mContext, List<CoumonsBean.BodyBean> mData) {
        mLayoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.mData = mData;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.coupons_items, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((ViewHolder) holder).name.setText(mData.get(position).getName());
        String time = DateUtil.getYmdforJson(mData.get(position).getStarted())+"~"+DateUtil.getYmdforJson(mData.get(position).getEnded());
        ((ViewHolder) holder).date.setText(time);
        ((ViewHolder) holder).money.setText("￥" + mData.get(position).getAmount()+"");
        switch (mData.get(position).getStatus()){
            case "1"://可使用
                if(CouponsActivity.type ==1){
                    ((ViewHolder) holder).reCon.setBackgroundResource(R.mipmap.yhq_bg_2x);
                    ((ViewHolder) holder).use.setVisibility(View.VISIBLE);
                }else if(CouponsActivity.type ==2){
                    if(mData.get(position).getAmount() == 588){
                        ((ViewHolder) holder).reCon.setBackgroundResource(R.mipmap.yhq_bg_2x);
                        ((ViewHolder) holder).use.setVisibility(View.VISIBLE);
                    }else {
                        ((ViewHolder) holder).reCon.setBackgroundResource(R.mipmap.yhq_bg2_2x);
                        ((ViewHolder) holder).use.setVisibility(View.GONE);
                        ((ViewHolder) holder).reCon.setEnabled(false);
                    }
                }
                else {
                    ((ViewHolder) holder).reCon.setBackgroundResource(R.mipmap.yhq_bg_2x);
                    ((ViewHolder) holder).use.setVisibility(View.VISIBLE);
                }
                break;
            case "2": // 已使用
                ((ViewHolder) holder).reCon.setBackgroundResource(R.mipmap.yhq_bg2_2x);
                ((ViewHolder) holder).use.setVisibility(View.GONE);
                break;
            case "0": //失效
                ((ViewHolder) holder).reCon.setBackgroundResource(R.mipmap.yhq_bg2_2x);
                ((ViewHolder) holder).use.setVisibility(View.GONE);
                break;
        }
        ((ViewHolder) holder).use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CouponsActivity.type ==1 || CouponsActivity.type == 2){
                    PreferenceUtils.setPrefString(mContext,"coumoney",mData.get(position).getAmount()+"");
                    PreferenceUtils.setPrefString(mContext,"couid",mData.get(position).getPacketid()+"");
                    CouponsActivity.activity.finish();
                }else {
                    Message msg = new Message();
                    msg.what =1;
                    MainActivity.NOHandler myHandler = new MainActivity.NOHandler(mContext);
                    myHandler.sendMessage(msg);
                    CouponsActivity.activity.finish();
                }
            }
        });
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
        private TextView name;
        private TextView money;
        private RelativeLayout reCon;
        private TextView use;
        private TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cou_name);
            money = itemView.findViewById(R.id.cou_money);
            use = itemView.findViewById(R.id.cou_use);
            date = itemView.findViewById(R.id.cou_date);
            reCon = itemView.findViewById(R.id.cou_re);
        }
    }

}
