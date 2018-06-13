package io.ionic.ylnewapp.view.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.response.NotifiBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class NotificationActivity extends BaseActivity {

    @ViewInject(R.id.tv_title)
    TextView title;

    @ViewInject(R.id.noti_lv)
    ListView listView;

    List<NotifiBean.BodyBean> mData ;

    @Event(type = View.OnClickListener.class,value = R.id.tv_back)
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back :
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        init();

    }

    /**
     *初始化
     */
    private void init() {
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
        title.setText("通知");
        getNotifiData();
    }


    /**
     * 获取通知列表
     */
    private void getNotifiData() {
        mBuilder.setTitle("加载中...").show();
        OkGo.<String>get(Constants.URL_BASE + "home/notices")//
                .tag(this)//
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//
                        Gson gson = new Gson();
                        NotifiBean  javaBean =gson.fromJson(data.toString(),NotifiBean.class);
                        mData = javaBean.getBody();
                        if(mData!=null)
                           initView(mData);
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


    private void  initView(final List<NotifiBean.BodyBean>mData){
        listView.setAdapter(new ListAdapter(mContext,mData));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext,NotifiCaDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("content",mData.get(position).getContent());
                bundle.putString("title",mData.get(position).getTitle());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    /**
     * 内部适配器
     */
    class ListAdapter extends BaseAdapter {
        private LayoutInflater mLayoutInflater;
        private Context mContext;
        private List<NotifiBean.BodyBean> mData;

        public ListAdapter(Context mContext, List<NotifiBean.BodyBean> mData) {
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
            ListAdapter.ViewHolder holder;
            if (convertView == null) {
                holder = new ListAdapter.ViewHolder();
                convertView = mLayoutInflater.inflate(R.layout.notifi_items, parent, false);

                convertView.setTag(holder);
            } else {
                holder = (ListAdapter.ViewHolder) convertView.getTag();
            }

            holder.tv_text = convertView.findViewById(R.id.tixte);
            holder.tv_img = convertView.findViewById(R.id.text_img);
            holder.tv_text.setText(mData.get(position).getTitle());
//            holder.tv_img.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(mContext,NotifiCaDetailActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("content",mData.get(position).getContent());
//                    bundle.putString("title",mData.get(position).getTitle());
//                    intent.putExtras(bundle);
//                    startActivity(intent);
//                }
//            });
            return convertView;
        }

        //holder
        class ViewHolder {
            private TextView tv_text;
            private ImageView tv_img;
        }
    }


}
