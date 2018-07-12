package io.ionic.ylnewapp.view.activity.mine;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.adpater.ActivityDetailAdapter2;
import io.ionic.ylnewapp.bean.ActivitiesDetailBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.custom.MyListView;
import io.ionic.ylnewapp.custom.NewListView;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;
import io.ionic.ylnewapp.view.main.MainActivity;

public class ActivitesDetail2 extends BaseActivity {


    @ViewInject(R.id.lvss)
    NewListView lvs;
    @ViewInject(R.id.tv_title)
    TextView title;


    ActivityDetailAdapter2 adapter;
    List<ActivitiesDetailBean.BodyBean> mData;


    @Event(type = View.OnClickListener.class,value = {R.id.tv_back,R.id.getBncn})//,
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.getBncn:
                getBncn();
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activites_detail2);
        init();
        loadData();
    }

    private void init() {
        title.setText("领取BNCN");
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
    }

    /**
     * 提交信息
     */
    private void loadData() {
        mBuilder.setTitle("加载中...").show();
        OkGo.<String>get(Constants.URL_BASE + "user/activitys/packet?id=bncn" )
                .tag(this)
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        ActivitiesDetailBean javaBean =gson.fromJson(response.body().toString(),ActivitiesDetailBean.class);
                        if(javaBean.getStatus() == 401){
                            ActivityUtils.toLogin(ActivitesDetail2.this,0);
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
    private void initView(List<ActivitiesDetailBean.BodyBean> data) {
        if(data.size() ==0) {
            T.showShort("暂无数据");
            return;
        }
        adapter = new ActivityDetailAdapter2(mContext,data);
        lvs.setAdapter(adapter);
        lvs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Message msg = new Message();
                msg.what =2;
                MainActivity.NOHandler myHandler = new MainActivity.NOHandler(mContext);
                myHandler.sendMessage(msg);
                finish();
                MyActiActivity.activity.finish();
            }
        });
    }

    //领取优惠券
    private void getBncn() {
        mBuilder.setTitle("领取中...").show();
        OkGo.<String>get(Constants.URL_BASE + "user/getBncn" )
                .tag(this)
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            String jsonStr =  response.body(); // 需要解析json格式的字符串
                            JSONObject jsonObject;
                            jsonObject = new JSONObject(jsonStr);
                            if(jsonObject.getString("status").equals("401"))
                                ActivityUtils.toLogin(ActivitesDetail2.this,0);
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

}
