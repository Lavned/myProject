package io.ionic.ylnewapp.view.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.umeng.analytics.MobclickAgent;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.BankBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
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
                if(feed_edit.getText().toString().trim().equals("")){
                    T.showShort("请输入您的意见");
                }else if(feed_num.getText().toString().trim().equals("")){
                    T.showShort("请输入您的联系方式");
                }else
                    loadData();
                break;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        init();
        MobclickAgent.onEvent(mContext, "Contactus");
    }

    /**
     * 初始化
     */
    private void init() {
        title.setText("意见反馈");
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
    }

        private void loadData() {
            mBuilder.setTitle("加载中...").show();
            OkGo.<String>put(Constants.URL_BASE + "user/advice")
                    .tag(this)
                    .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext, "token", ""))
                    .params("touchId",feed_num.getText().toString().trim())
                    .params("content",feed_edit.getText().toString().trim())
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            Gson gson = new Gson();
                            BankBean javaBean = gson.fromJson(response.body().toString(), BankBean.class);
                            if (javaBean.getStatus() == 401) {
                                ActivityUtils.toLogin(FeedbackActivity.this, 0);
                            }
                            if (javaBean.getStatus() == 200) {
                                T.showShort("我们已收到反馈，谢谢您的参与");
                                finish();
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

}

