package io.ionic.ylnewapp.adpater;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.TotalBean;

/**
 * Created by mogojing on 2018/6/12/0012.
 */

public class CountMoneyAdapterRight extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private  List<TotalBean.BodyBean.OrdersBean> mData;

    public CountMoneyAdapterRight(Context mContext, List<TotalBean.BodyBean.OrdersBean> mData) {
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
            convertView = mLayoutInflater.inflate(R.layout.count_money_right_items, parent, false);
            holder.cm_name = convertView.findViewById(R.id.cm_name);
            holder.cm_img = convertView.findViewById(R.id.cm_img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.cm_name.setText(mData.get(position).getType()+"");
        holder.cm_img.setBackgroundColor(Color.parseColor(mData.get(position).getColor()));
        return convertView;
    }

    //holder
    class ViewHolder {
        private TextView cm_name;
        private ImageView cm_img;
    }
}

