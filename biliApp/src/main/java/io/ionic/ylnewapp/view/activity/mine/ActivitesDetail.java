package io.ionic.ylnewapp.view.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import io.ionic.ylnewapp.adpater.ActivityDetailAdapter;
import io.ionic.ylnewapp.bean.ActivitiesDetailBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.custom.MyListView;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class ActivitesDetail extends BaseActivity {


    @ViewInject(R.id.lvs)
    MyListView lvs;
    @ViewInject(R.id.tv_title)
    TextView title;


    ActivityDetailAdapter adapter;
    List<ActivitiesDetailBean.BodyBean> mData;


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
        setContentView(R.layout.activity_activites_detail);
        init();
        loadData();
    }

    private void init() {
        title.setText("领取优惠券");
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
    }

    /**
     * 提交信息
     */
    private void loadData() {
        mBuilder.setTitle("加载中...").show();
        OkGo.<String>get(Constants.URL_BASE + "user/activitys/packet?id=packet" )
                .tag(this)
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        ActivitiesDetailBean javaBean =gson.fromJson(response.body().toString(),ActivitiesDetailBean.class);
                        if(javaBean.getStatus() == 401){
                            ActivityUtils.toLogin(ActivitesDetail.this,0);
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
        adapter = new ActivityDetailAdapter(mContext,data);
        lvs.setAdapter(adapter);
        lvs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mData.get(position).isStatu() ==true){
                    startActivity(new Intent(mContext,CouponsActivity.class));
                    finish();
                    MyActiActivity.activity.finish();
                }else {
                    getCounps(mData.get(position).getType()+"");
                }

            }
        });
    }

    //领取优惠券
    private void getCounps(String  type) {
        mBuilder.setTitle("领取中...").show();
        OkGo.<String>put(Constants.URL_BASE + "user/packet" )
                .tag(this)
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .params("type",type)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        ActivitiesDetailBean javaBean =gson.fromJson(response.body().toString(),ActivitiesDetailBean.class);
                        if(javaBean.getStatus() == 401)
                            ActivityUtils.toLogin(ActivitesDetail.this,0);
                        if(javaBean.getStatus() == 200){
                            T.showShort("领取成功");
                            loadData();
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
