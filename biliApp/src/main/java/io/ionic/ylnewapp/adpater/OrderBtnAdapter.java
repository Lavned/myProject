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
import io.ionic.ylnewapp.httpOrder.OrderUtils;

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
        switch (mData.get(position)){
//             1 继续支付 2 取消订单 3 我要挂单 4 修改挂单 5 撤销挂单 6 续投 7 赎回
            case "1" :
                holder.tv.setText("继续支付");
                holder.tv.setBackgroundResource(R.mipmap.order_button_2x);
                break;
            case "2" :
                holder.tv.setText("取消订单");
                holder.tv.setBackgroundResource(R.mipmap.order_button2_2x);
                holder.tv.setTextColor(mContext.getColor(R.color.black_1));
                break;
            case "3" :
                holder.tv.setText("我要挂单");
                holder.tv.setBackgroundResource(R.mipmap.order_button_3);
                break;
            case "4" :
                holder.tv.setText("修改挂单");
                holder.tv.setBackgroundResource(R.mipmap.order_button_2x);
                break;
            case "5" :
                holder.tv.setText("撤销挂单");
                holder.tv.setBackgroundResource(R.mipmap.order_button_2x);
                break;
            case "6" :
                holder.tv.setText("续投");
                holder.tv.setBackgroundResource(R.mipmap.order_button_2x);
                break;
            case "7" :
                holder.tv.setText("赎回");
                holder.tv.setBackgroundResource(R.mipmap.order_button_2x);
                break;
        }
        return convertView;
    }

    //holder
  public static  class ViewHolder {
        public TextView tv;
    }



}
