package io.ionic.ylnewapp.view.activity.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.utils.DateUtil;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class WalletOptionSuccessActivity extends BaseActivity {


    @ViewInject(R.id.tv_title)
    TextView title;
    @ViewInject(R.id.tips)
    TextView tips;
    @ViewInject(R.id.one_time)
    TextView one_time;
//    @ViewInject(R.id.two_time)
//    TextView two_time;
    @ViewInject(R.id.succ_icon)
    ImageView succ_icon;

    @Event(type = View.OnClickListener.class,value = {R.id.tv_back,R.id.finish_yes})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.finish_yes:
                if(WalletDetailActivity.activity!=null)
                    WalletDetailActivity.activity.finish();
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_add_success);
        init();
    }

    /**
     *
     */
    private void init() {
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
        Intent intent = getIntent();
        String type = intent.getStringExtra("name");
        switch (type){
            case "1":
                title.setText("充值申请");
//                tips.setText("充值申请已提交，等待工审核");
                break;
            case "2" :
                title.setText("提现申请");
                tips.setText("提现申请已提交，等待人工审核");
                break;
            case "3" :
                title.setText("充值申请");
                tips.setText("请通过银行官方渠道进行充值");
                succ_icon.setImageResource(R.mipmap.cz_icon_2x);
                break;
        }
        one_time.setText(DateUtil.getDateAndTime());
//        two_time.setText("预计到账时间："+DateUtil.getDateAndTimeLazy());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
