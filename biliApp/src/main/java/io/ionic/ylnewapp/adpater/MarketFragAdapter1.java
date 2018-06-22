package io.ionic.ylnewapp.adpater;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.market.MarketBean;
import io.ionic.ylnewapp.bean.market.ToMarketBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.activity.market.MarketFragment1;

/**
 * Created by mogojing on 2018/6/7/0007.
 */

public class MarketFragAdapter1 extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<MarketBean> mData;
    List<ToMarketBean.BodyBean> mDatas;

    private boolean mIsShowDelete;

    public MarketFragAdapter1(Context mContext, List<MarketBean> mData,List<ToMarketBean.BodyBean> mDatas) {
        mLayoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.mData = mData;
        this.mDatas = mDatas;
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
        final MarketBean item = mData.get(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.mark_fragment1_items, parent, false);
            holder.tv_img = convertView.findViewById(R.id.img1_photo);
            holder.tv_name = convertView.findViewById(R.id.textName1);
            holder.tv_no = convertView.findViewById(R.id.textNum1);
            holder.del = convertView.findViewById(R.id.del_btn);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (mIsShowDelete) {
            holder.del.setVisibility(View.VISIBLE);
        } else {
            holder.del.setVisibility(View.GONE);
        }

        holder.tv_no.setText(item.getCurrencyPair()+"");
        holder.tv_name.setText(item.getExchange()+"");
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delChoose(mDatas.get(position).get_id(),position);
            }
        });

        return convertView;
    }

    private void delChoose(String id,final int position) {
        OkGo.<String>delete(Constants.URL_BASE + "market/choose?id=" + id )
                .tag(this)
                .cacheTime(1000)
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(response.body().toString());
                            T.showShort(jsonObject.getString("msg"));
                            if(jsonObject.getString("status").equals("200")){
                                Gson gson= new Gson();
                                MarketFragment1.mSocket.emit("delete", gson.toJson(mDatas.get(position)));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showNetworkError(mContext);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    //holder
    class ViewHolder {
        private ImageView tv_img;
        private TextView tv_name;
        private TextView tv_no;
        private ImageView del;
    }


    public void showDeleteCheck() {
        mIsShowDelete = true;
        notifyDataSetChanged();
    }

    public void hideDeleteCheck() {
        mIsShowDelete = false;
        notifyDataSetChanged();
    }

    public boolean getDeleteCheckState() {
        return mIsShowDelete;
    }
}