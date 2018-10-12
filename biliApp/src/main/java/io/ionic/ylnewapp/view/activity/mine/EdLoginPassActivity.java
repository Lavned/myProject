package io.ionic.ylnewapp.view.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.MD5Util;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class EdLoginPassActivity extends BaseActivity {



    @ViewInject(R.id.pass_old_pass)
    EditText pass_old_pass;
    @ViewInject(R.id.pass_one)
    EditText pass_one;
    @ViewInject(R.id.pass_confim)
    EditText pass_confim;
    @ViewInject(R.id.tv_title)
    TextView title;


    @Event(type = View.OnClickListener.class, value = {R.id.tv_back, R.id.add_pass})
    private void click(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.add_pass:
                if(checkEmpty()){
                    if(!ActivityUtils.getView(pass_confim).equals(ActivityUtils.getView(pass_one)))
                        T.showShort("两次新密码输入不一致");
                    else addPass();
                }
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ed_login_pass);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        title.setText("修改登录密码");
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary), 225);
    }


    /**
     * 登录
     */
    private void addPass() {
        mBuilder.setTitle("请稍候...").show();
        OkGo.<String>post(Constants.URL_BASE + "user/pwd")//
                .tag(this)//
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .params("oldPwd", MD5Util.encrypt(pass_old_pass.getText().toString().trim()))//
                .params("newPwd", MD5Util.encrypt(pass_one.getText().toString().trim()))
                .params("confirm", MD5Util.encrypt(pass_confim.getText().toString().trim()))//
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//
                        JSONObject jsonObject ;
                        try {
                            jsonObject = new JSONObject(data);
                            T.showShort(jsonObject.getString("msg"));
                            if(jsonObject.getInt("status") == 200)
                                finish();
                            else if(jsonObject.getInt("status") == 401)
                                ActivityUtils.toLogin(EdLoginPassActivity.this,0);

                        } catch (JSONException e) {
                            e.printStackTrace();
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

    //注册的非空验证
    boolean checkEmpty(){
        if(ActivityUtils.getView(pass_old_pass).equals("")){
            T.showShort("旧密码不可为空");
            return false;
        }
        if(ActivityUtils.getView(pass_one).equals("")){
            T.showShort("新密码不可为空");
            return false;
        }
        if(ActivityUtils.getView(pass_confim).equals("")){
            T.showShort("请再次输入密码");
            return false;
        }
        if(ActivityUtils.getView(pass_old_pass).length() < 6){
            T.showShort("密码不能少于6位大于16位");
            return false;
        }
        if(ActivityUtils.getView(pass_one).length() < 6){
            T.showShort("密码不能少于6位大于16位");
            return false;
        }
        if(ActivityUtils.getView(pass_confim).length() < 6){
            T.showShort("密码不能少于6位大于16位");
            return false;
        }
        else{
            return true;
        }
    }


}
