package io.ionic.ylnewapp.adpater;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.AccountBean;
import io.ionic.ylnewapp.utils.DateUtil;
import io.ionic.ylnewapp.view.activity.mine.MyCountCoinActivity;

/**
 * Created by mogojing on 2018/6/12/0012.
 */

public class FourCoinAdapter extends BaseAdapter {
        private LayoutInflater mLayoutInflater;
        private Context mContext;
        private List<AccountBean.BodyBean.CoinsBean> lsData;

        public FourCoinAdapter(Context mContext, List<AccountBean.BodyBean.CoinsBean> lsData) {
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
                convertView = mLayoutInflater.inflate(R.layout.my_num_items, parent, false);
                holder.coin_count_money = convertView.findViewById(R.id.coin_count_money);
                holder.coin_count_icon = convertView.findViewById(R.id.coin_count_icon);
                holder.item = convertView.findViewById(R.id.item);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.coin_count_money.setText(lsData.get(position).getName()+" "+lsData.get(position).getCmount()+"");
            Glide.with(mContext).load(lsData.get(position).getIcon()).into(holder.coin_count_icon);
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext,MyCountCoinActivity.class));
                }
            });
            return convertView;
        }

        //holder
        class ViewHolder {
            private TextView coin_count_money;
            private ImageView coin_count_icon;
            private LinearLayout item;
        }
}
