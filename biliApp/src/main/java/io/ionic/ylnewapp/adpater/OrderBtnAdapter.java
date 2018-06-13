package io.ionic.ylnewapp.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.suke.widget.SwitchButton;

import java.util.List;

import io.ionic.ylnewapp.R;

/**
 * Created by mogojing on 2018/6/12/0012.
 */

public class OrderBtnAdapter  extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<String> mData;

    public OrderBtnAdapter(Context mContext, List< String> mData) {
        mLayoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.order_btn_items, parent, false);
            holder.tv = convertView.findViewById(R.id.order_btn);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(mData.get(position));
        return convertView;
    }

    //holder
  public static  class ViewHolder {
        public TextView tv;
    }

}
