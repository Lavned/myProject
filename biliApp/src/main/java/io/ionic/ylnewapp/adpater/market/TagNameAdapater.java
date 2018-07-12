package io.ionic.ylnewapp.adpater.market;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.market.SeachGrid;

/**
 * Created by mogojing on 2018/6/5/0005.
 */

public class TagNameAdapater extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List< SeachGrid.DataBean> mData;

    public TagNameAdapater(Context mContext, List< SeachGrid.DataBean> mData) {
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
        TextView tv;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.currencyname_items, parent, false);
            TextView name = convertView.findViewById(R.id.name);
            name.setText(mData.get(position).getValue());
        } else {
            convertView.getTag();
        }
        return convertView;
    }

}