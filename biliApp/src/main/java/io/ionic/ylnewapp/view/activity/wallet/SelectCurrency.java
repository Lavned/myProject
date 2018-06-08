package io.ionic.ylnewapp.view.activity.wallet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.suke.widget.SwitchButton;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.response.CoinBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class SelectCurrency extends BaseActivity {

    @ViewInject(R.id.SelectCurrency_lv)
    ListView SelectCurrency_lv;
    @ViewInject(R.id.tv_title)
    TextView title;
    @ViewInject(R.id.textView3)
    ImageView textView3;

    List<CoinBean.BodyBean>mData;
    SelectCurrenyAdapter adapter;



    @Event(type = View.OnClickListener.class,value = {R.id.tv_back,R.id.textView3})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.textView3:
                startActivity(new Intent(mContext,CurrencySearchActivity.class));
                finish();
                break;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_currency);
        init();
        getList();
    }

    /**
     *初始化
     */
    private void init() {
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
        title.setText("选择币种");
        textView3.setVisibility(View.VISIBLE);
        textView3.setImageResource(R.mipmap.f_search2x);
    }

    private void initView(List<CoinBean.BodyBean> mData) {
        if(mData.size() ==0) {
            T.showShort("暂无数据");
            return;
        }
        adapter = new SelectCurrenyAdapter(mContext,mData);
        adapter.notifyDataSetChanged();
        SelectCurrency_lv.setAdapter(adapter);
    }


    /**
     * 提交信息
     */
    private void getList() {
        mBuilder.setTitle("加载中...").show();
        OkGo.<String>get(Constants.URL_BASE + "coins/coin" )
                .tag(this)
                .cacheTime(1000)
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        CoinBean javaBean =gson.fromJson(response.body().toString(),CoinBean.class);
                        if(javaBean.getStatus() == 401){
                            ActivityUtils.toLogin(SelectCurrency.this);
                        }
                        mData = javaBean.getBody();
                        if(mData!= null){
                            initView(mData);
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
                        mBuilder.dismiss();
                    }
                });
    }


    /**
     * 提交信息
     */
    private void addCoin(String index) {
        mBuilder.setTitle("加载中...").show();
        OkGo.<String>put(Constants.URL_BASE + "coins/coin" )
                .tag(this)
                .cacheTime(1000)
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .params("name",index)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(response.body().toString());
                            if(jsonObject.getInt("status") == 401){
                                ActivityUtils.toLogin(SelectCurrency.this);
                            }else if(jsonObject.getInt("status") == 200){
                                finish();
                            }
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

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        mBuilder.dismiss();
                    }
                });
    }


    /**
     * 适配器
     */
    class SelectCurrenyAdapter extends BaseAdapter {
        HashMap<Integer,Boolean> isCheck;
        private LayoutInflater mLayoutInflater;
        private Context mContext;
        private List< CoinBean.BodyBean> mData;

        public SelectCurrenyAdapter(Context mContext, List< CoinBean.BodyBean> mData) {
            mLayoutInflater = LayoutInflater.from(mContext);
            this.mContext = mContext;
            this.mData = mData;
            isCheck = new HashMap<>();
            initData();
        }

        private void initData() {
            for(int i = 0;i< mData.size();i++){
                if(mData.get(i).isStatu() == true)
                    isCheck.put(i,true);
                else isCheck.put(i,false);
            }
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
            final ViewHolder holder;
            final CoinBean.BodyBean coin = mData.get(position);
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mLayoutInflater.inflate(R.layout.currency_items, parent, false);
                holder.name = convertView.findViewById(R.id.it_name);
                holder.icon = convertView.findViewById(R.id.it_icon);
                holder.switchButton  = convertView.findViewById(R.id.switch_button);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.name.setText(coin.getKey()+"");
            Glide.with(mContext).load(coin.getIcon()).into(holder.icon);

            holder.switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                    if (isChecked){
                        isCheck.put(position,true);
                        addCoin(coin.getKey());
                    }else {
                        isCheck.put(position,false);
                    }
                }

            });
            if(isCheck.get(position)){
                holder.switchButton.setChecked(true);
                holder.switchButton.setEnabled(false);
            }else{
                holder.switchButton.setChecked(false);
            }
            return convertView;
        }

        //holder
        class ViewHolder {
            private TextView name;
            private ImageView icon;
            private SwitchButton switchButton;

        }

    }

}
