package io.ionic.ylnewapp.view.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class PassManaActivity extends BaseActivity {

    @ViewInject(R.id.tv_title)
    TextView title;
    @ViewInject(R.id.add_pay)
    TextView add_pay;
    @ViewInject(R.id.edit_pay)
    TextView edit_pay;


    @Event(type = View.OnClickListener.class,value = {R.id.tv_back,R.id.exit_login,R.id.edit_pay,R.id.edit_login,R.id.forget_pay,R.id.add_pay})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.edit_pay :
                startActivity(new Intent(mContext,EdPayPassActivity.class));
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
            case R.id.add_pay :
                startActivity(new Intent(mContext,AddPayPassActivity.class));
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_mana);
        init();
        hasPay();
    }

    /**
     * 初始化
     */
    private void init() {
        title.setText("密码管理");
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
    }


    /**
     * 获取用户信息
     */
    private void hasPay() {
        OkGo.<String>post(Constants.URL_BASE + "user/hasPay")
                .tag(this)
                .params("username", PreferenceUtils.getPrefString(mContext, "account", ""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        JSONObject gson = null;
                        try {
                            gson = new JSONObject(response.body());
                            PreferenceUtils.setPrefString(mContext, "hasPay", gson.getString("body") + "");
                            if(gson.getString("body").equals("true")){
                                add_pay.setVisibility(View.GONE);
                            }else {
                                edit_pay.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showNetworkError(mContext);
                    }
                });
    }

}
