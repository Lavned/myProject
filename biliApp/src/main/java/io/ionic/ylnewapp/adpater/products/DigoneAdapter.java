package io.ionic.ylnewapp.adpater.products;

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
 * Created by mogojing on 2018/6/14/0014.
 */

public class DigoneAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<String> mData;

    public DigoneAdapter(Context mContext, List<String> mData) {
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
            holder.tv_btn = convertView.findViewById(R.id.btnmoney);
            holder.tv_img = convertView.findViewById(R.id.img_photo);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//            holder.tv_btn.setText(mData.get(position));
        if (position == 2){
            holder.tv_img.setImageResource(R.mipmap.dig_p4_2x);
            holder.tv_btn.setBackgroundResource(R.drawable.green10bg);
        }
        return convertView;
    }

    //holder
    class ViewHolder {
        private TextView tv_btn;
        private ImageView tv_img;
    }
}