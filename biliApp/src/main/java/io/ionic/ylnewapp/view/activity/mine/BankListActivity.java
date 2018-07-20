package io.ionic.ylnewapp.view.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

import java.util.HashMap;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.BankBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class BankListActivity extends BaseActivity {

    @ViewInject(R.id.list_bank)
    ListView listBank;
    @ViewInject(R.id.tv_title)
    TextView title;
    @ViewInject(R.id.textView3)
    ImageView textView3;

    @ViewInject(R.id.list_empty)
    LinearLayout list_empty;


    BankListAdapter adapter;
    List<BankBean.BodyBean> mData;


    @Event(type = View.OnClickListener.class,value = {R.id.tv_back,R.id.add_bank,R.id.textView3})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.add_bank:
                startActivity(new Intent(mContext,BankAddActivity.class));
                break;
            case R.id.textView3:
                startActivity(new Intent(mContext,BankAddActivity.class));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_list);
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
        MobclickAgent.onEvent(mContext, "Bank");
        textView3.setVisibility(View.VISIBLE);
        textView3.setImageResource(R.mipmap.qb_icon10_2x);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getBankList();
    }

    /**
     * 提交信息
     */
    private void getBankList() {
        mBuilder.setTitle("加载中...").show();
        OkGo.<String>get(Constants.URL_BASE + "user/bank" )
                .tag(this)
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        BankBean javaBean =gson.fromJson(response.body().toString(),BankBean.class);
                        if(javaBean.getStatus() == 401){
                            ActivityUtils.toLogin(BankListActivity.this,0);
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
     * 初始化界面
     */
    private void initView(List<BankBean.BodyBean> data) {
        title.setText("银行卡管理");
        if(data.size() ==0) {
            listBank.setVisibility(View.GONE);
            list_empty.setVisibility(View.VISIBLE);
            return;
        }
        adapter = new BankListAdapter(mContext,data);
        listBank.setAdapter(adapter);
    }



    /**
     * 内部适配器
     */
    HashMap<Integer, Boolean> states = new HashMap<Integer, Boolean>();  //在这里要做判断保证只有一个RadioButton被选中
    class BankListAdapter extends BaseAdapter {
        private LayoutInflater mLayoutInflater;
        private Context mContext;
        private List<BankBean.BodyBean> mData;

        public BankListAdapter(Context mContext, List<BankBean.BodyBean> mData) {
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
                convertView = mLayoutInflater.inflate(R.layout.bank_items, parent, false);
                holder.bank_name = convertView.findViewById(R.id.bank_khhname);
                holder.bank_detail = convertView.findViewById(R.id.bank_detail);
                holder.bank_no = convertView.findViewById(R.id.bank_no);
                holder.deleteBank = convertView.findViewById(R.id.deleteBank);
                holder.chDefault = convertView.findViewById(R.id.cb_default);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            String count = mData.get(position).getCount();
            String cardNo = count.substring(0,4) + "       ****       ****       "+count.substring(count.length()-4,count.length());
            holder.bank_no.setText(cardNo);
            holder.bank_name.setText(mData.get(position).getName());
            if(mData.get(position).getDefult()){
                for (int i = 0; i < getCount(); i++) {  //把所有的按钮的状态设置为没选中
                    states.put(i, false);
                }
                //然后设置点击的那个按钮设置状态为选中
                states.put(position, true);    //这样所有的条目中只有一个被选中！
            }else {
//                holder.chDefault.setChecked(false);
            }
            Glide.with(mContext)
                    .load(mData.get(position).getUrl().get(1))
                    .into(holder.bank_detail);
            holder.deleteBank.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new CommonDialog.Builder(BankListActivity.this)
                            .setTitle("温馨提示")
                            .setMessage("确定要删除该银行卡吗？")
                            .setPositiveButton("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    deleteData(mData.get(position).getId());
                                }
                            },R.color.main).setNegativeButton("取消", null).show();
                }
            });

            holder.chDefault.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new CommonDialog.Builder(BankListActivity.this)
                            .setMessage("确定将改地址设置为默认地址吗？").setTitle("提示")
                            .setPositiveButton("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    setDefault(mData.get(position).getId());
//                                    for (int i = 0; i < getCount(); i++) {  //把所有的按钮的状态设置为没选中
//                                        states.put(i, false);
//                                    }
//                                    //然后设置点击的那个按钮设置状态为选中
//                                    states.put(position, true);    //这样所有的条目中只有一个被选中！
//                                    notifyDataSetChanged();//刷新适配器
                                }
                            }, R.color.main).setNegativeButton("取消",null).show();
                }
            });
            //上面是点击后设置状态，但是也是需要设置显示样式,通过判断状态设置显示的样式
            if (states.get((Integer) position) == null || states.get((Integer) position) == false) {  //true说明没有被选中
                holder.chDefault.setChecked(false);
            } else {
                holder.chDefault.setChecked(true);
            }

            return convertView;
        }

        //holder
        class ViewHolder {
            private TextView bank_name;
            private TextView bank_no;
            private ImageView bank_detail;
            private ImageView deleteBank;
            private RadioButton chDefault;
        }
    }


    /**
     * 设置默认
     */
    private void setDefault(String id) {
        OkGo.<String>post(Constants.URL_BASE + "user/bank" )
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
                                getBankList();
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


    /**
     * 删除银行卡
     */
    private void deleteData(String id) {
        OkGo.<String>delete(Constants.URL_BASE + "user/bank?id="+id )
                .tag(this)
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            T.showShort(jsonObject.getString("msg"));
                            getBankList();
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
