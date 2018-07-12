package io.ionic.ylnewapp.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.FirendBean;
import io.ionic.ylnewapp.bean.HomeBean;
import io.ionic.ylnewapp.utils.DateUtil;

/**
 * Created by mogojing on 2018/6/12/0012.
 */

public class HotAdapter extends BaseAdapter {
        private LayoutInflater mLayoutInflater;
        private Context mContext;
        private List<HomeBean.BodyBean.HotBean> lsData;

        public HotAdapter(Context mContext, List<HomeBean.BodyBean.HotBean> lsData) {
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
            final HomeBean.BodyBean.HotBean item = lsData.get(position);
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mLayoutInflater.inflate(R.layout.one_hot_item, parent, false);
                holder.one_1 = convertView.findViewById(R.id.one_1);
                holder.one_2 = convertView.findViewById(R.id.one_2);
                holder.one_3 = convertView.findViewById(R.id.one_3);
                holder.one_4 = convertView.findViewById(R.id.one_4);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.one_1.setText(item.getRate()+"");
            holder.one_2.setText(item.getName());
            holder.one_3.setText(item.getText().get(0));
            if(item.getText().size()>1)
                holder.one_4.setText(item.getText().get(1));
            return convertView;
        }

    //holder
    class ViewHolder {
        private TextView one_1,one_2,one_3,one_4;
    }
}
