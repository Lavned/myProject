package io.ionic.ylnewapp.adpater.market;

import android.content.Context;
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

public class MarketFragAdapter3 extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<String> mData;

    public MarketFragAdapter3(Context mContext, List<String> mData) {
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
        String item = mData.get(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.mark_fragment3_items, parent, false);
            holder.tv_one = convertView.findViewById(R.id.text_one);
            holder.tv_two = convertView.findViewById(R.id.text_two);
            holder.tv_three = convertView.findViewById(R.id.text_three);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_three.setText(item+"");

        return convertView;
    }

    //holder
    class ViewHolder {
        private TextView tv_one;
        private TextView tv_two;
        private TextView tv_three;
    }
}