package io.ionic.ylnewapp.adpater.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.order.AllOrderBean;

/**
 * Created by mogojing on 2018/6/12/0012.
 */


//订单的内部类
public class OtherListAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<AllOrderBean.BodyBean.OrdersBean.ListBean.RateBean> lsData;

    public OtherListAdapter(Context mContext, List<AllOrderBean.BodyBean.OrdersBean.ListBean.RateBean> lsData) {
        mLayoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.lsData = lsData;
    }

    @Override
    public int getCount() {
        return lsData == null ? 0 : lsData.size();
    }

    @Override
    public Object getItem(int position) {
        return lsData.get(position);
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
            convertView = mLayoutInflater.inflate(R.layout.order_iv_items, parent, false);
            holder.key = convertView.findViewById(R.id.order_key);
            holder.val = convertView.findViewById(R.id.order_val);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.key.setText(lsData.get(position).getKey()+"：");
        holder.val.setText(lsData.get(position).getVal());

        return convertView;
    }

    //holder
    class ViewHolder {
        private TextView key,val;
    }
}