package io.ionic.ylnewapp.adpater.products;

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
import io.ionic.ylnewapp.bean.products.DIGDetailBean;

/**
 * Created by mogojing on 2018/6/14/0014.
 */

public class DigoneAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<DIGDetailBean.DataBean> mData;

    public DigoneAdapter(Context mContext, List<DIGDetailBean.DataBean> mData) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.item_listview, parent, false);
            holder.tv_img = convertView.findViewById(R.id.img_photo);
            holder.textname = convertView.findViewById(R.id.textBTC);
            holder.textename2 = convertView.findViewById(R.id.textEXName);
            holder.usd = convertView.findViewById(R.id.text_usd);
            holder.cny = convertView.findViewById(R.id.text_cny);
            holder.change = convertView.findViewById(R.id.btnmoney);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textname.setText(mData.get(position).getCurrencyPair());
        holder.textename2.setText(mData.get(position).getExchange());
        holder.cny.setText("￥"+mData.get(position).getPrices().getCny()+"");
        holder.usd.setText(mData.get(position).getPrices().getUsd()+"");
        holder.change.setText(mData.get(position).getChange()+"%");
        String change = mData.get(position).getChange()+"";
        if (change.contains("-")){
            holder.change.setBackgroundResource(R.drawable.red10bg);
        }else {
            holder.change.setBackgroundResource(R.drawable.green10bg);
        }
        Glide.with(mContext).load(mData.get(position).getIconUrl()).into(holder.tv_img);
        return convertView;
    }

    //holder
    class ViewHolder {
        private TextView textname,textename2,usd,cny,change;
        private ImageView tv_img;
    }
}