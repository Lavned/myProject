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
import io.ionic.ylnewapp.bean.response.ActivityBean;

/**
 * Created by mogojing on 2018/6/6/0006.
 */

public class MyActiviAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<ActivityBean.BodyBean> mData;

    public MyActiviAdapter(Context mContext, List<ActivityBean.BodyBean> mData) {
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
            convertView = mLayoutInflater.inflate(R.layout.activity_items, parent, false);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_text = convertView.findViewById(R.id.tv_text);
        holder.tv_img = convertView.findViewById(R.id.tv_img);
        holder.tv_date = convertView.findViewById(R.id.tv_date);
        Glide.with(mContext).load(mData.get(position).getUrl().get(0)).into(holder.tv_img);
        holder.tv_text.setText(mData.get(position).getTitle()+"");
        holder.tv_date.setText(mData.get(position).getDate()+"");
        return convertView;
    }

    //holder
    class ViewHolder {
        private TextView tv_text;
        private TextView tv_date;
        private ImageView tv_img;
    }
}
