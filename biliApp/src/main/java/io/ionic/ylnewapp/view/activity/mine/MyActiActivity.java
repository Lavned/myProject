package io.ionic.ylnewapp.view.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import io.ionic.ylnewapp.adpater.MyActiviAdapter;
import io.ionic.ylnewapp.bean.ActivityBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class MyActiActivity extends BaseActivity {


    @ViewInject(R.id.tv_title)
    TextView title;
    @ViewInject(R.id.noti_lv)
    ListView lv;


    List<ActivityBean.BodyBean> mData;
    public  static Activity activity;

    @Event(type = View.OnClickListener.class,value = {R.id.tv_back})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        init();
        loadData();
        activity = this;
    }

    /**
     * 初始化
     */
    private void init() {
        title.setText("活动中心");
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
    }

    /**
     * 获取通知列表
     */
    private void loadData() {
        mBuilder.setTitle("加载中...").show();
        OkGo.<String>get(Constants.URL_BASE + "user/activitys")//
                .tag(this)//
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//
                        Gson gson = new Gson();
                        ActivityBean javaBean =gson.fromJson(data.toString(),ActivityBean.class);
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

    private void  initView(final List<ActivityBean.BodyBean>mData){
        lv.setDividerHeight(0);
        lv.setAdapter(new MyActiviAdapter(mContext,mData));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (mData.get(position).getId()){
                    case "bncn" :
                        startActivity(new Intent(mContext,ActivitesDetail2.class));
                        break;
                    case "packet" :
                        startActivity(new Intent(mContext,ActivitesDetail.class));
                        break;
                }
            }
        });
    }

}
