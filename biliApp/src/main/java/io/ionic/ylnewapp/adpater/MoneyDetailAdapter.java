package io.ionic.ylnewapp.adpater;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.ionic.ylnewapp.R;

/**
 * Created by mogojing on 2018/6/7/0007.
 */

public class MoneyDetailAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<String> mData;

    public MoneyDetailAdapter(Context mContext, List<String> mData) {
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
            convertView = mLayoutInflater.inflate(R.layout.money_detail_items, parent, false);
            holder.tv_date = convertView.findViewById(R.id.detail_date);
            holder.tv_name = convertView.findViewById(R.id.detail_name);
            holder.tv_no = convertView.findViewById(R.id.detail_no);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//        holder.tv_text.setText(mData.get(position).getTitle());
        return convertView;
    }

    //holder
    class ViewHolder {
        private TextView tv_date;
        private TextView tv_name;
        private TextView tv_no;
    }
}