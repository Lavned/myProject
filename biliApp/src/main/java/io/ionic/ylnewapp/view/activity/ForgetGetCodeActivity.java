package io.ionic.ylnewapp.view.activity;

import android.content.Intent;
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
import io.ionic.ylnewapp.utils.PhoneUtil;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.activity.mine.ForgetPayActivity;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class ForgetGetCodeActivity extends BaseActivity {



    //控件声明
    @ViewInject(R.id.reAccount)
    EditText reAccount;
    @ViewInject(R.id.reCode)
    EditText reCode;

    String msg ="";

    /***
     * 点击事件
     * @param v
     */
    @Event(type = View.OnClickListener.class,value = {R.id.nextBtn,R.id.get_code})
    private void click(View v){
        switch (v.getId()){
            case R.id.nextBtn :
                if(!checkEmpty(reAccount))
                    T.showShort("请输入验证码不能为空");
                else
                    nextStep();
                break;
            case R.id.get_code :
                if(!PhoneUtil.isPhoneNo(reAccount.getText().toString().trim())){
                    T.showShort("请输入合法手机号");
                    return;
                }
                if(!checkEmpty(reAccount)){
                    T.showShort("手机号不能为空");
                    return;
                }
                getCode();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary),225);
    }


    //获取验证码
    private void getCode() {
        OkGo.<String>post(Constants.URL_BASE + "user/sms")//
                .tag(this)//
                .params("username", reAccount.getText().toString().trim())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            msg = jsonObject.getString("status");
                            T.showShort(jsonObject.getString("msg"));
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


    //获取验证码
    private void nextStep() {
        OkGo.<String>post(Constants.URL_BASE + "user/pwdNext")//
                .tag(this)//
                .params("username", reAccount.getText().toString().trim())
                .params("smscode", reCode.getText().toString().trim())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            T.showShort(jsonObject.getString("msg"));
                            if(jsonObject.getInt("status") == 401){
                                ActivityUtils.toLogin(ForgetGetCodeActivity.this);
                            }
                            if(jsonObject.getString("status").equals("200")){
                                PreferenceUtils.setPrefString(ForgetGetCodeActivity.this,"username",reAccount.getText().toString());
                                startActivity(new Intent(ForgetGetCodeActivity.this,ForgetResetPwdActivity.class));
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
    boolean checkEmpty(TextView view){
        if(view.getText().toString().trim().equals("")){
            return false;
        }
        return true;
    }

}
