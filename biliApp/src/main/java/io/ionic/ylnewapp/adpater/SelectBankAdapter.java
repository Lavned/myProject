package io.ionic.ylnewapp.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.suke.widget.SwitchButton;

import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.BankBean;

/**
 * Created by mogojing on 2018/6/6/0006.
 */

public class SelectBankAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<BankBean.BodyBean> mData;

    public SelectBankAdapter(Context mContext, List<BankBean.BodyBean> mData) {
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
            convertView = mLayoutInflater.inflate(R.layout.currency_items, parent, false);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name = convertView.findViewById(R.id.it_name);
        holder.icon = convertView.findViewById(R.id.it_icon);
        holder.switchButton  = convertView.findViewById(R.id.switch_button);
        holder.switchButton.setVisibility(View.GONE);
        holder.name.setText(mData.get(position).getName());
        Glide.with(mContext)
                .load(mData.get(position).getUrl().get(3))
                .into(holder.icon);

        return convertView;
    }

    //holder
    class ViewHolder {
        private TextView name;
        private ImageView icon;
        private SwitchButton switchButton;
    }
}
