package io.ionic.ylnewapp.view.activity.product;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.jaeger.library.StatusBarUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class OtherDetailActivity extends BaseActivity {


    Context context;

    @ViewInject(R.id.tz_number)
    EditText editText;

    @ViewInject(R.id.cb_agreed)
    CheckBox cbAgreed;
    @ViewInject(R.id.cb_card)
    CheckBox cbCard;
    @ViewInject(R.id.cb_money)
    CheckBox cbMoney;


    @Event(type = View.OnClickListener.class,value = {R.id.pay_btn,R.id.cb_money,R.id.cb_card,R.id.cb_agreed})
    private void click(View v){
        switch (v.getId()){
            case R.id.pay_btn :
                if(editText.getText().toString().trim().equals("")){
                    T.showShort("起购金额必须大于1000");
                    return;
                }
//                if(!cbAgreed.isChecked()){
//                    T.showShort("请先确认同意协议说明");
//                    return;
//                }
                startActivity(new Intent(OtherDetailActivity.this,PaySuccessActivity.class));
                break;
            case R.id.cb_money:
                cbMoney.setChecked(true);
                cbCard.setChecked(false);
                break;
            case R.id.cb_card :
                cbMoney.setChecked(false);
                cbCard.setChecked(true);
                T.showLong("请");
                break;
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_detail);
        StatusBarUtil.setTranslucentForImageViewInFragment(this, 0,null);
        context = this;
    }
}
