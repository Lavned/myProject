package io.ionic.ylnewapp.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.CoinBean;

/**
 * Created by mogojing on 2018/6/5/0005.
 */

public class CurrencyLvAdapter extends BaseAdapter {

    /**
     * 内部适配器
     */
        private LayoutInflater mLayoutInflater;
        private Context mContext;
        private List<CoinBean.BodyBean> mData;

        public CurrencyLvAdapter(Context mContext, List< CoinBean.BodyBean> mData) {
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
            CoinBean.BodyBean itemInfo = mData.get(position);
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mLayoutInflater.inflate(R.layout.currencylv_items, parent, false);
                holder.name = convertView.findViewById(R.id.name);
                holder.add = convertView.findViewById(R.id.add);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.name.setText(itemInfo.getKey());
            return convertView;
        }

        //holder
        class ViewHolder {
            private TextView name;
            private TextView add;

        }
    
}
