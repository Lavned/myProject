package io.ionic.ylnewapp.view.activity;

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
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class ForgetResetPwdActivity extends BaseActivity {



    //控件声明
    @ViewInject(R.id.pwd)
    EditText pwd;
    @ViewInject(R.id.repwd)
    EditText repwd;

    /***
     * 点击事件
     * @param
     */
    @Event(type = View.OnClickListener.class,value = R.id.commit_btn)
    private void click(View view){
        initView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_reset);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary),225);
    }


    /**
     * 设置view
     */
    private void initView() {
        if(checkEmpty()){
            if(!repwd.getText().toString().trim().equals(pwd.getText().toString().trim())){
                T.showShort("两次输入的密码不一致，请重新输入");
                return;
            }
            submitData();
        }
    }
    /**
     * 提交新密码
     */
    private void submitData() {
        OkGo.<String>post(Constants.URL_BASE + "user/forgetPwd")//
                .tag(this)
                .params("username", PreferenceUtils.getPrefString(ForgetResetPwdActivity.this,"username",""))
                .params("password", pwd.getText().toString().trim())
                .params("passwordConfirm", repwd.getText().toString().trim())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            T.showShort(jsonObject.getString("msg"));
                            if(jsonObject.getString("status").equals("200")){
                                finish();
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


    /**
     * 非空验证
     * @return
     */
    boolean checkEmpty(){
        if(pwd.getText().toString().trim().equals("")){
            T.showShort("密码不可为空");
            return false;
        }
        if(repwd.getText().toString().trim().equals("")){
            T.showShort("再次输入的密码不可为空");
            return false;
        }
        return true;
    }

}
