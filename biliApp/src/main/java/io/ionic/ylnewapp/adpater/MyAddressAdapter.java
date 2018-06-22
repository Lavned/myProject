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
import io.ionic.ylnewapp.bean.AddressBean;

/**
 * Created by mogojing on 2018/6/5/0005.
 */

public class MyAddressAdapter  extends BaseAdapter {

    /**
     * 内部适配器
     */
        private LayoutInflater mLayoutInflater;
        private Context mContext;
        private List<AddressBean.BodyBean> mData;

        public MyAddressAdapter(Context mContext, List<AddressBean.BodyBean> mData) {
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
                convertView = mLayoutInflater.inflate(R.layout.addres_items, parent, false);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.name = convertView.findViewById(R.id.item_name);
            holder.name.setText(mData.get(position).getType()+"");
            holder.icon = convertView.findViewById(R.id.item_icon);
            return convertView;
        }

        //holder
        class ViewHolder {
            private TextView name;
            private ImageView icon;
        }
}
