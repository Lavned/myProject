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
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.jiangyy.easydialog.CommonDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.AddressBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;


//钱包管理
public class WalletManaActivity extends BaseActivity {


    @ViewInject(R.id.tv_title)
    TextView title;
    @ViewInject(R.id.textView3)
    ImageView textView3;

    @ViewInject(R.id.wal_lv)
    ListView wallLv;

    List<AddressBean.BodyBean> mData;
    WalletAdapter adapter;

    int[] imgs = {R.mipmap.qb_head_icon2_2x, R.mipmap.qb_head_icon3_2x, R.mipmap.qb_head_icon4_2x, R.mipmap.qb_head_icon5_2x,
            R.mipmap.qb_head_icon6_2x,R.mipmap.qb_head_icon7_2x};


    @Event(type = View.OnClickListener.class,value = {R.id.tv_back,R.id.textView3})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.textView3:
                startActivity(new Intent(mContext,WalletAdAddActivity.class));
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_mana);
        init();
        getList();
    }

    /**
     *初始化
     */
    private void init() {
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
        title.setText("钱包地址管理");
        MobclickAgent.onEvent(mContext, "Wallet");
        textView3.setVisibility(View.VISIBLE);
        textView3.setImageResource(R.mipmap.qb_icon10_2x);
        mData = new ArrayList();
    }


    @Override
    protected void onResume() {
        super.onResume();
        getList();
    }


    /**
     * chushihuaview
     * @param mData
     */
    private void initView(List<AddressBean.BodyBean> mData) {
        if(mData.size() ==0) {
            T.showShort("暂无数据");
        }
        adapter = new WalletAdapter(mContext,mData);
        adapter.notifyDataSetChanged();
        wallLv.setAdapter(adapter);
    }


    /**
     * 获取数据
     */
    private void getList() {
        mBuilder.setTitle("请稍候..").show();
        OkGo.<String>get(Constants.URL_BASE + "user/address" )
                .tag(this)
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        AddressBean javaBean =gson.fromJson(response.body().toString(),AddressBean.class);
                        if (javaBean.getStatus() == 401)
                                ActivityUtils.toLogin(WalletManaActivity.this,0);
                        mData = javaBean.getBody();
                        if(mData!= null){
                            initView(mData);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showShort(response.toString());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        mBuilder.dismiss();
                    }
                });
    }

    HashMap<Integer, Boolean> states = new HashMap<>();  //在这里要做判断保证只有一个RadioButton被选中
    /**
     * 内部适配器
     */
    class WalletAdapter extends BaseAdapter {
        private LayoutInflater mLayoutInflater;
        private Context mContext;
        private List<AddressBean.BodyBean> mData;

        public WalletAdapter(Context mContext, List<AddressBean.BodyBean> mData) {
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
            final ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mLayoutInflater.inflate(R.layout.wal_mana_items, parent, false);
                holder.name = convertView.findViewById(R.id.item_name);
                holder.name.setText(mData.get(position).getType()+"");
                holder.address = convertView.findViewById(R.id.item_address);
                holder.address.setText(mData.get(position).getAddress());
                holder.icon = convertView.findViewById(R.id.item_icon);
                holder.del = convertView.findViewById(R.id.wal_del);
                holder.chDefault = convertView.findViewById(R.id.cb_default);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            setHead(holder.icon,position);
            if(mData.get(position).getDefult()){
                for (int i = 0; i < getCount(); i++) {  //把所有的按钮的状态设置为没选中
                    states.put(i, false);
                }
                //然后设置点击的那个按钮设置状态为选中
                states.put(position, true);    //这样所有的条目中只有一个被选中！
            }else {
//                holder.chDefault.setChecked(false);
            }
            holder.del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new CommonDialog.Builder(WalletManaActivity.this)
                            .setMessage("钱包删除无法找回，确定要删除吗").setTitle("删除提示")
                            .setPositiveButton("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    deleteData(mData.get(position).getId());
                                }
                            }, R.color.main).setNegativeButton("取消",null).show();
                }
            });
            holder.chDefault.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new CommonDialog.Builder(WalletManaActivity.this)
                            .setMessage("确定将改地址设置为默认地址吗？").setTitle("提示")
                            .setPositiveButton("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    setDefault(mData.get(position).getId());
                                }
                            }, R.color.main).setNegativeButton("取消",null).show();
                }
            });
            //上面是点击后设置状态，但是也是需要设置显示样式,通过判断状态设置显示的样式
            if (states.get( position) == null || states.get( position) == false) {  //true说明没有被选中
                holder.chDefault.setChecked(false);
            } else {
                holder.chDefault.setChecked(true);
            }

            return convertView;
        }

        //holder
        class ViewHolder {
            private TextView name;
            private ImageView icon;
            private TextView address;
            private ImageView del;
            private RadioButton chDefault;
        }


        /**
         * 设置默认
         */
        private void setDefault(String id) {
            OkGo.<String>post(Constants.URL_BASE + "user/address" )
                    .tag(this)
                    .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                    .params("id",id)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.body());
                                T.showShort(jsonObject.getString("msg"));
                                if(jsonObject.getString("status").equals("200"))
                                    getList();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(Response<String> response) {
                            super.onError(response);
                            T.showShort(response.toString());
                        }
                    });
        }

    }

    /**
     * 随机头像
     */
    private void setHead(ImageView icon,int i) {
        if(i > 6){
            i = 0;
        }
        icon.setImageResource(imgs[i]);
    }


    /**
     * 删除钱包
     */
    private void deleteData(String id) {
        mBuilder.setTitle("请稍候..").show();
        OkGo.<String>delete(Constants.URL_BASE + "user/address?id="+id )
                .tag(this)
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            T.showShort(jsonObject.getString("msg"));
                            if(jsonObject.getString("status").equals("200"))
                                getList();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showShort(response.toString());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        mBuilder.dismiss();
                    }
                });
    }

}
