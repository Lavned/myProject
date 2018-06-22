package io.ionic.ylnewapp.view.activity.mine;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import io.ionic.ylnewapp.custom.PwdEditText;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.MD5Util;
import io.ionic.ylnewapp.utils.PhoneUtil;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class ForgetPayActivity extends BaseActivity {


    @ViewInject(R.id.tv_title)
    TextView title;
    @ViewInject(R.id.phone_num)
    EditText phone_num;
    @ViewInject(R.id.phone_code)
    EditText phone_code;
    @ViewInject(R.id.lin_get)
    LinearLayout lin_get;
    @ViewInject(R.id.lin_pass)
    LinearLayout lin_pass;
    @ViewInject(R.id.pay_edit)
    PwdEditText pay_edit;
    @ViewInject(R.id.pay_tip)
    TextView pay_tip;
    @ViewInject(R.id.pay_next)
    Button pay_next;

    int count =0;

    @Event(type = View.OnClickListener.class, value = {R.id.tv_back, R.id.phone_get_code, R.id.pay_next,R.id.phone_submit})
    private void click(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.phone_get_code:
                if(checkEmpty()){
                    if(!phone_num.getText().toString().equals(PreferenceUtils.getPrefString(mContext,"account",""))){
                        T.showShort("该号码非目前登录号码");
                        return;
                    }else
                        getCode();
                }
                break;
            case R.id.phone_submit:
                next();
                break;
            case R.id.pay_next:
                if(pay_edit.getText().toString().trim().equals("")){
                    T.showShort("不可输入空密码");
                    return;
                }
                count ++ ;
                switch (count){
                    case 1:
                        if(!ActivityUtils.getView(pay_edit).equals(""))
                            PreferenceUtils.setPrefString(mContext, "one", pay_edit.getText().toString().trim());
                        ThreeClick();
                        break;
                    case 2:
                        editPayPass();
                        break;
                    default:
                        editPayPass();
                        break;
                }
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pay);
        init();
    }


    private void showPass(){
        lin_get.setVisibility(View.GONE);
        lin_pass.setVisibility(View.VISIBLE);
    }

    private void ThreeClick() {
        pay_tip.setText("请再次输入密码");
        pay_edit.setText("");
        pay_next.setText("完成");
        pay_edit.setOnInputFinishListener(new PwdEditText.OnInputFinishListener() {

            @Override
            public void onInputFinish(String password) {
                if (!pay_edit.getText().toString().trim().equals(PreferenceUtils.getPrefString(mContext, "one", ""))) {
                    T.showShort("两次输入的密码不一致，请重新输入");
                    pay_next.setVisibility(View.GONE);
                }else {
                    pay_next.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void editPayPass() {
        Log.i("000000",
                pay_edit.getText().toString().trim());
        String payPwd = PreferenceUtils.getPrefString(mContext,"one","");
        mBuilder.setTitle("请稍候...").show();
        OkGo.<String>put(Constants.URL_BASE + "user/payPwd")//
                .tag(this)//
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .params("payPwd", MD5Util.encrypt(payPwd))
                .params("confirm", MD5Util.encrypt(pay_edit.getText().toString().trim()))
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
                                ActivityUtils.toLogin(ForgetPayActivity.this,0);
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


    /**
     * 初始化
     */
    private void init() {
        title.setText("忘记支付密码");
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary), 225);
    }

    //获取验证码
    private void getCode() {
        OkGo.<String>post(Constants.URL_BASE + "user/sms")//
                .tag(this)//
                .params("username", phone_num.getText().toString().trim())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        try {
                            JSONObject jsonObject = new JSONObject(data);
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
    private void next() {
        OkGo.<String>post(Constants.URL_BASE + "user/pwdNext")//
                .tag(this)//
                .params("username", phone_num.getText().toString().trim())
                .params("smscode", phone_code.getText().toString().trim())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            T.showShort(jsonObject.getString("msg"));
                            if(jsonObject.getInt("status") == 401){
                                ActivityUtils.toLogin(ForgetPayActivity.this,0);
                            }
                            if(jsonObject.getString("status").equals("200")){
                                showPass();
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


    //注册的非空验证
   private  boolean checkEmpty(){
        if(ActivityUtils.getView(phone_num).equals("")){
            T.showShort("手机号不可为空");
            return false;
        }
       if(!PhoneUtil.isPhoneNo(phone_num.getText().toString().trim())){
           T.showShort("请输入合法手机号");
           return false;
       }
        else{
            return true;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceUtils.setPrefString(mContext, "one","");
        PreferenceUtils.setPrefString(mContext, "two","");
        PreferenceUtils.setPrefString(mContext, "three","");
    }


}
