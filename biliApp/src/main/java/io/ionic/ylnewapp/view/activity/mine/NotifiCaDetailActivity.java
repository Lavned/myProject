package io.ionic.ylnewapp.view.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class NotifiCaDetailActivity extends BaseActivity {

    @ViewInject(R.id.tv_title)
    TextView noTi;
    @ViewInject(R.id.no_content)
    TextView noCo;

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
        setContentView(R.layout.activity_notifi_ca_detail);
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
        noTi.setText("通知");
        Intent intent = getIntent();
        noCo.setText(Html.fromHtml(intent.getStringExtra("content")));

    }


}
