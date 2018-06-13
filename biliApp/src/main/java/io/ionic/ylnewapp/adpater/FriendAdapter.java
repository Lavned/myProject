package io.ionic.ylnewapp.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import io.ionic.ylnewapp.R;

/**
 * Created by mogojing on 2018/6/12/0012.
 */

public class FriendAdapter extends BaseAdapter {
        private LayoutInflater mLayoutInflater;
        private Context mContext;
        private List<String> lsData;

        public FriendAdapter(Context mContext, List<String> lsData) {
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
                convertView = mLayoutInflater.inflate(R.layout.friend_items, parent, false);
                holder.fri_phone = convertView.findViewById(R.id.fri_phone);
                holder.fri_date = convertView.findViewById(R.id.fri_date);
                holder.fri_money = convertView.findViewById(R.id.fri_money);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.fri_money.setText(lsData.get(position));

            return convertView;
        }

        //holder
        class ViewHolder {
            private TextView fri_phone,fri_date,fri_money;
        }
}
