package io.ionic.ylnewapp.view.activity.mine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class PassManaActivity extends BaseActivity {

    @ViewInject(R.id.tv_title)
    TextView title;

    @Event(type = View.OnClickListener.class,value = {R.id.tv_back,R.id.exit_login,R.id.edit_pay,R.id.edit_login,R.id.forget_pay})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.edit_pay :
                startActivity(new Intent(mContext,PayPassActivity.class));
                finish();
                break;
            case R.id.edit_login :
                startActivity(new Intent(mContext,EdLoginPassActivity.class));
                finish();
                break;
            case R.id.forget_pay :
                startActivity(new Intent(mContext,ForgetPayActivity.class));
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_mana);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        title.setText("密码管理");
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
    }

}
