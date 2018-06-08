package io.ionic.ylnewapp.view.activity.wallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.adpater.WalletDetailAdapater;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class WalletOutMoneyActivity extends BaseActivity {

    @ViewInject(R.id.tv_title)
    TextView title;

    @Event(type = View.OnClickListener.class,value = {R.id.tv_back,R.id.submit_btn})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.submit_btn:
                Intent intent =new Intent(mContext,WalletOptionSuccessActivity.class);
                intent.putExtra("name","2");
                startActivity(intent);
                finish();
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_out_money);
        init();
    }

    /**
     *
     */
    private void init() {
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
        title.setText("BBB");
    }

}
