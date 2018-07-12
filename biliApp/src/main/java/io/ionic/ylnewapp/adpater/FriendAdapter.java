package io.ionic.ylnewapp.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.FirendBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.DateUtil;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;

/**
 * Created by mogojing on 2018/6/12/0012.
 */

public class FriendAdapter extends BaseAdapter {
        private LayoutInflater mLayoutInflater;
        private Context mContext;
        private List<FirendBean.BodyBean.ListBean> lsData;

        public FriendAdapter(Context mContext, List<FirendBean.BodyBean.ListBean> lsData) {
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
            holder.fri_money.setText("¥"+lsData.get(position).getMoney());
            holder.fri_date.setText(DateUtil.getYmdforJson(lsData.get(position).getCreated()));
            holder.fri_phone.setText(lsData.get(position).getUserid()+"");
            holder.fri_money.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cashData(lsData.get(position).getOrderid());
                }
            });
            return convertView;
        }

    //holder
    class ViewHolder {
        private TextView fri_phone,fri_date,fri_money;
    }

    private void cashData(String orderid) {
        OkGo.<String>post(Constants.URL_BASE + "invite/cash")//
                .tag(this)//
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext, "token", ""))
                .params("orderid",orderid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            T.showShort(jsonObject.getString("msg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showNetworkError(mContext);
                    }
                });
    }

}
