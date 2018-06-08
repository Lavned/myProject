package io.ionic.ylnewapp.view.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.adpater.MoneyDetailAdapter;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class MoneyDetailActivity extends BaseActivity {

    @ViewInject(R.id.tv_title)
    TextView title;

    @ViewInject(R.id.detail_lv)
    ListView listView;

    MoneyDetailAdapter adapter ;
    List<String> mData ;

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
        setContentView(R.layout.activity_money_detail);
        init();

    }

    /**
     *初始化
     */
    private void init() {
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
        title.setText("交易明细");
        initView(mData);
//        loadData();
    }


    private void loadData() {
        mBuilder.setTitle("加载中...").show();
        OkGo.<String>get(Constants.URL_BASE + "home/notices")//
                .tag(this)//
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//
//                        Gson gson = new Gson();
//                        NotifiBean  javaBean =gson.fromJson(data.toString(),NotifiBean.class);
//                        mData = javaBean.getBody();
//                        if(mData!=null)
//                            initView(mData);
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


    void  initView(List<String> mData){
        mData = new ArrayList<>();
        mData.add("333333333");
        mData.add("444444444");
        adapter = new MoneyDetailAdapter(mContext,mData);
        listView.setAdapter(adapter);
    }
}
