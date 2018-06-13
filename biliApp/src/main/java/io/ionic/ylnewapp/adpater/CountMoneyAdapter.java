package io.ionic.ylnewapp.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.ionic.ylnewapp.R;

/**
 * Created by mogojing on 2018/6/12/0012.
 */

public class CountMoneyAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<String> mData;

    public CountMoneyAdapter(Context mContext, List<String> mData) {
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
            convertView = mLayoutInflater.inflate(R.layout.count_money_items, parent, false);
            holder.name = convertView.findViewById(R.id.money_name);
            holder.icon = convertView.findViewById(R.id.money_icon);
            holder.num  = convertView.findViewById(R.id.money_num);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(mData.get(position));
        return convertView;
    }

    //holder
    class ViewHolder {
        private TextView name;
        private ImageView icon;
        private TextView num;
    }
}

