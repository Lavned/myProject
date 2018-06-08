package io.ionic.ylnewapp.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.response.ActivitiesDetailBean;

/**
 * Created by mogojing on 2018/6/7/0007.
 */

public class ActivityDetailAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<ActivitiesDetailBean.BodyBean> mData;

    public ActivityDetailAdapter(Context mContext, List<ActivitiesDetailBean.BodyBean> mData) {
        mLayoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.acti_detail_items, parent, false);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name = convertView.findViewById(R.id.icon_name);
        holder.num = convertView.findViewById(R.id.icon_num);
        holder.icon = convertView.findViewById(R.id.icon_bg);
        String datas = mData.get(position).getName();
        int index = datas.indexOf("优");
        holder.num.setText(datas.substring(0,index));
        holder.name.setText(datas.substring(index+1,datas.length()));
        if(mData.get(position).isStatu() ==true){
            holder.icon.setImageResource(R.mipmap.yhj_yes);
        }else {
            holder.icon.setImageResource(R.mipmap.yhj_no);
        }
        return convertView;
    }

    //holder
    class ViewHolder {
        private TextView name;
        private TextView num;
        private ImageView icon;
    }
}
