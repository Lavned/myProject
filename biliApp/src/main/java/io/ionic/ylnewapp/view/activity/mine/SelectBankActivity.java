package io.ionic.ylnewapp.view.activity.mine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

import java.util.ArrayList;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.adpater.SelectBankAdapter;
import io.ionic.ylnewapp.bean.response.BankBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.activity.wallet.CurrencySearchActivity;
import io.ionic.ylnewapp.view.activity.wallet.SelectCurrency;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class SelectBankActivity extends BaseActivity {

    @ViewInject(R.id.noti_lv)
    ListView lv;
    @ViewInject(R.id.tv_title)
    TextView title;
    @ViewInject(R.id.textView3)
    ImageView textView3;

    List<BankBean.BodyBean> mData;
    SelectBankAdapter adapter;


    @Event(type = View.OnClickListener.class, value = {R.id.tv_back, R.id.textView3})
    private void click(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.textView3:
                startActivity(new Intent(mContext, BankAddActivity.class));
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getBankList();

    }

    /**
     *初始化
     */
    private void init() {
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
        title.setText("选择银行卡");
        textView3.setVisibility(View.VISIBLE);
        textView3.setImageResource(R.mipmap.qb_icon10_2x);
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
                        if(javaBean.getStatus() != 200){
                            ActivityUtils.toLogin(SelectBankActivity.this);
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
           T.showShort("暂无数据");
            return;
        }
        adapter = new SelectBankAdapter(mContext,data);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PreferenceUtils.setPrefString(mContext,"bank",mData.get(position).getCount());
                PreferenceUtils.setPrefString(mContext,"name",mData.get(position).getName());
                PreferenceUtils.setPrefString(mContext,"icon",mData.get(position).getUrl().get(3));
                finish();
            }
        });
    }


}
