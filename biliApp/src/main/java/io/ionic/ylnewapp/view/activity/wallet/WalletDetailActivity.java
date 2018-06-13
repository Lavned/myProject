package io.ionic.ylnewapp.view.activity.wallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.adpater.WalletDetailAdapater;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class WalletDetailActivity extends BaseActivity {


    @ViewInject(R.id.tv_title)
    TextView title;
    @ViewInject(R.id.detailLv)
    ListView lvDetail;

    List<String> mData;
    WalletDetailAdapater adapater;
    String titleName ="";

    public static WalletDetailActivity activity;

    @Event(type = View.OnClickListener.class,value = {R.id.tv_back,R.id.add_btn,R.id.get_btn})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.add_btn:
                startActivityTo(1);
                break;
            case R.id.get_btn:
                startActivityTo(2);
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_detail);
        init();
        initView();
        activity = this;
    }

    private void initView() {
        mData = new ArrayList<>();
        mData.add("kkk");
        mData.add("lll");
        adapater = new WalletDetailAdapater(mContext,mData);
        lvDetail.setAdapter(adapater);

    }

    /**
     *
     */
    private void init() {
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
        Intent intent = getIntent();
        titleName = intent.getStringExtra("name");
        int i = titleName.indexOf("|");
        title.setText(titleName.substring(0,i));
    }

    void startActivityTo(int type){
        Intent intent;
        switch (type){
            case 1:
                intent = new Intent(mContext,WalletAddMoneyActivity.class);
                intent.putExtra("name",titleName);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(mContext,WalletOutMoneyActivity.class);
                intent.putExtra("name",titleName);
                startActivity(intent);
                break;
        }
    }
}
