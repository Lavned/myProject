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
import io.ionic.ylnewapp.bean.market.MarketBean;

/**
 * Created by mogojing on 2018/6/7/0007.
 */

public class MarketFragAdapter2 extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<MarketBean> mData;

    public MarketFragAdapter2(Context mContext, List<MarketBean> mData) {
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
        MarketBean item = mData.get(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.mark_fragment2_items, parent, false);
            holder.tv_img = convertView.findViewById(R.id.itemPhoto);
            holder.tv_name = convertView.findViewById(R.id.textName);
            holder.tv_no = convertView.findViewById(R.id.textNum);
            holder.topNum = convertView.findViewById(R.id.top_num);
            holder.bottomNum = convertView.findViewById(R.id.bottom_num);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_name.setText(item.getCurrencyPair()+"");
        holder.topNum.setText("￥"+item.getPrices().getCny()+"");
        holder.bottomNum.setText("~="+item.getPrices().getUsd());
        holder.tv_no.setText(item.getMarketCap()+"亿");
        Glide.with(mContext).load(item.getIconUrl()).into(holder.tv_img);

        return convertView;
    }

    //holder
    class ViewHolder {
        private ImageView tv_img;
        private TextView tv_name;
        private TextView tv_no,topNum,bottomNum;
    }
}