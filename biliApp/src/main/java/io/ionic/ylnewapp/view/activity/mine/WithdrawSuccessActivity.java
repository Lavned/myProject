package io.ionic.ylnewapp.view.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.utils.DateUtil;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class WithdrawSuccessActivity extends BaseActivity {

    @ViewInject(R.id.one_time)
    TextView one_time;
    @ViewInject(R.id.idcard)
    TextView card;

    @Event(type = View.OnClickListener.class,value = {R.id.tv_back,R.id.textView3})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.textView3:
                finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_success);
        init();
    }

    /**
     *
     */
    private void init() {
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
        one_time.setText(DateUtil.getDateAndTime());
        one_time.setText("预计到账时间："+DateUtil.getDateAndTimeLazy());
        Intent intent = getIntent();
        String cardName = intent.getStringExtra("card");
        card.setText(cardName);
    }

}
