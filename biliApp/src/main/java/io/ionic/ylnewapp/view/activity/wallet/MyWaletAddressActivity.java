package io.ionic.ylnewapp.view.activity.wallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.adpater.MyAddressAdapter;
import io.ionic.ylnewapp.bean.response.AddressBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class MyWaletAddressActivity extends BaseActivity {

    @ViewInject(R.id.tv_title)
    TextView title;
    @ViewInject(R.id.lv_address)
    ListView lv_address;

    List<AddressBean.BodyBean> mData;
    MyAddressAdapter adapter;

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
        setContentView(R.layout.activity_my_walet_address);
        init();
        getList();

    }

    /**
     *
     */
    private void init() {
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
        title.setText("选择钱包");

    }

    private void  initView(List<AddressBean.BodyBean> mDatas){
        adapter = new MyAddressAdapter(mContext,mDatas);
        lv_address.setAdapter(adapter);
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
                            ActivityUtils.toLogin(MyWaletAddressActivity.this);
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



}
