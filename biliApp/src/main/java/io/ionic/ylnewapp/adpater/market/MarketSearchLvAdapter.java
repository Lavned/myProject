package io.ionic.ylnewapp.adpater.market;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.CoinBean;
import io.ionic.ylnewapp.bean.market.SearchBean;

/**
 * Created by mogojing on 2018/6/5/0005.
 */

public class MarketSearchLvAdapter extends BaseAdapter {

    /**
     * 内部适配器
     */
        private LayoutInflater mLayoutInflater;
        private Context mContext;
        private List<SearchBean.DataBean> mData;

        public MarketSearchLvAdapter(Context mContext, List<SearchBean.DataBean> mData) {
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
            SearchBean.DataBean itemInfo = mData.get(position);
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mLayoutInflater.inflate(R.layout.market_lv_items, parent, false);
                holder.name = convertView.findViewById(R.id.name);
                holder.add = convertView.findViewById(R.id.add);
                holder.jy_name = convertView.findViewById(R.id.jy_name);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.name.setText( itemInfo.getCurrencyPair()+"");
            holder.jy_name.setText(itemInfo.getExchangeName() + "" );
            return convertView;
        }

        //holder
        class ViewHolder {
            private TextView name,jy_name;
            private TextView add;

        }
    
}
