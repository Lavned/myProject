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

/**
 * Created by mogojing on 2018/6/5/0005.
 */

public class WalletDetailAdapater extends BaseAdapter {

    /**
     * 适配器
     */
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<String> mData;

    public WalletDetailAdapater(Context mContext, List<String> mData) {
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
            convertView = mLayoutInflater.inflate(R.layout.wallet_detail_items, parent, false);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.icon_detail = convertView.findViewById(R.id.icon_detail);
        holder.date_detail = convertView.findViewById(R.id.date_detail);
        holder.num_detail = convertView.findViewById(R.id.num_detail);
        holder.rgnum_detail = convertView.findViewById(R.id.rgnum_detail);
        holder.date_detail.setText(mData.get(position));
        return convertView;
    }

    //holder
    class ViewHolder {
        private ImageView icon_detail;
        private TextView date_detail;
        private TextView num_detail;
        private TextView rgnum_detail;

    }

}