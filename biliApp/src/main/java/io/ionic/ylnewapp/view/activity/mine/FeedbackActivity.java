package io.ionic.ylnewapp.view.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.response.ActivityBean;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class FeedbackActivity extends BaseActivity {


    @ViewInject(R.id.tv_title)
    TextView title;
    @ViewInject(R.id.feed_edit)
    TextView feed_edit;
    @ViewInject(R.id.feed_num)
    TextView feed_num;



    @Event(type = View.OnClickListener.class,value = {R.id.tv_back,R.id.select_pic,R.id.feed_submit})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.select_pic :
                break;
            case R.id.feed_submit:
                break;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        title.setText("意见反馈");
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
    }
}
