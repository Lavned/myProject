package io.ionic.ylnewapp.adpater.market;

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
import io.ionic.ylnewapp.bean.market.OtherExchangeBBean;

/**
 * Created by mogojing on 2018/6/12/0012.
 */

public class ZxAdapter extends BaseAdapter {
        private LayoutInflater mLayoutInflater;
        private Context mContext;
        private List<OtherExchangeBBean.DataBean> lsData;

        public ZxAdapter(Context mContext, List<OtherExchangeBBean.DataBean> lsData) {
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
            OtherExchangeBBean.DataBean item = lsData.get(position);
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mLayoutInflater.inflate(R.layout.mark_fragment1_items, parent, false);
                holder.tv_img = convertView.findViewById(R.id.img1_photo);
                holder.tv_name = convertView.findViewById(R.id.textName1);
                holder.tv_no = convertView.findViewById(R.id.textNum1);
                holder.tv_name2 = convertView.findViewById(R.id.textName2);
                holder.btnmoney = convertView.findViewById(R.id.btnmoney);
                holder.del = convertView.findViewById(R.id.del_btn);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv_no.setText("¥"+item.getPrices().getCny());
            holder.tv_name.setText(item.getCurrencyPair()+"");
            holder.btnmoney.setText(item.getChange()+"%");
            String change = item.getChange()+"";
            if(change.contains("-")){
                holder.btnmoney.setBackgroundResource(R.drawable.red10bg);
            }else {
                holder.btnmoney.setBackgroundResource(R.drawable.green10bg);
            }
            holder.tv_name2.setText(item.getExchange()+"");
            Glide.with(mContext).load(item.getIconUrl()).into(holder.tv_img);

            return convertView;
        }

        //holder
        class ViewHolder {
            private ImageView tv_img;
            private TextView tv_name;
            private TextView btnmoney,tv_name2;
            private TextView tv_no;
            private ImageView del;
        }
}
