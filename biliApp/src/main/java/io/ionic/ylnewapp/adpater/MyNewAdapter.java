package io.ionic.ylnewapp.adpater;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.suke.widget.SwitchButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.response.CoinBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.activity.wallet.SelectCurrency;

/**
 * Created by mogojing on 2018/6/8/0008.
 */

public class MyNewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CoinBean.BodyBean> datas;
    private Context context;
    private int normalType = 0;
    private int footType = 1;
    private boolean hasMore = true;
    private boolean fadeTips = false;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public MyNewAdapter(List<CoinBean.BodyBean> datas, Context context, boolean hasMore) {
        this.datas = datas;
        this.context = context;
        this.hasMore = hasMore;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == normalType) {
            return new NormalHolder(LayoutInflater.from(context).inflate(R.layout.currency_items, null));
        } else {
            return new FootHolder(LayoutInflater.from(context).inflate(R.layout.footview, null));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalHolder) {
            final CoinBean.BodyBean coin = datas.get(position);
            ((NormalHolder) holder).name.setText(coin.getKey() + "");
            Glide.with(context).load(coin.getIcon()).into(((NormalHolder) holder).icon);
            if (coin.isStatu() == true) {
                ((NormalHolder) holder).switchButton.setChecked(true);
                ((NormalHolder) holder).switchButton.setEnabled(false);
            }
            else {
                ((NormalHolder) holder).switchButton.setChecked(false);
            }

            ((NormalHolder) holder).switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                    addCoin(coin.getKey());
                }
            });

        } else {
            ((FootHolder) holder).tips.setVisibility(View.VISIBLE);
            if (hasMore == true) {
                fadeTips = false;
                if (datas.size() > 0) {
                    ((FootHolder) holder).tips.setText("正在加载更多...");
                }
            } else {
                if (datas.size() > 0) {
                    ((FootHolder) holder).tips.setText("没有更多数据了");
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ((FootHolder) holder).tips.setVisibility(View.GONE);
                            fadeTips = true;
                            hasMore = true;
                        }
                    }, 500);
                }
            }
        }
    }


    @Override
    public int getItemCount() {
        return datas.size() ;
    }

    public int getRealLastPosition() {
        return datas.size();
    }


    public void updateList(List<CoinBean.BodyBean> newDatas, boolean hasMore) {
        if (newDatas != null) {
            datas.addAll(newDatas);
        }
        this.hasMore = hasMore;
        notifyDataSetChanged();
    }


    class NormalHolder extends RecyclerView.ViewHolder {
        private TextView name;
        ImageView icon;
        private SwitchButton switchButton;

        public NormalHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.it_name);
            icon = itemView.findViewById(R.id.it_icon);
            switchButton = itemView.findViewById(R.id.switch_button);
        }
    }

    class FootHolder extends RecyclerView.ViewHolder {
        private TextView tips;

        public FootHolder(View itemView) {
            super(itemView);
            tips = (TextView) itemView.findViewById(R.id.tips);
        }
    }

    public boolean isFadeTips() {
        return fadeTips;
    }

    public void resetDatas() {
        datas = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return footType;
        } else {
            return normalType;
        }
    }

    Activity activity;
    /**
     * 提交信息
     */
    private void addCoin(String index) {
        OkGo.<String>put(Constants.URL_BASE + "coins/coin" )
                .tag(this)
                .cacheTime(1000)
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(context,"token",""))
                .params("name",index)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(response.body().toString());
                            if(jsonObject.getInt("status") == 401){
                                ActivityUtils.toLogin((Activity) context,0);
                            }else if(jsonObject.getInt("status") == 200){
                                if (Activity.class.isInstance(context)) {
                                    activity = (Activity) context;
                                    activity.finish();
                                }
                            }
                            T.showShort(jsonObject.getString("msg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showNetworkError(activity);
                    }
                });
    }

}