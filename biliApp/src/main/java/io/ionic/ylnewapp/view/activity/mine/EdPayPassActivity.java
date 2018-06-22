package io.ionic.ylnewapp.view.activity.mine;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;


//修改支付密码
public class EdPayPassActivity extends BaseActivity {


    @ViewInject(R.id.pay_edit)
    PwdEditText pwd;
    @ViewInject(R.id.tv_title)
    TextView title;
    @ViewInject(R.id.tip)
    TextView tip;
    @ViewInject(R.id.next)
    Button next;


    int count = 0;

    @Event(type = View.OnClickListener.class, value = {R.id.tv_back, R.id.next})
    private void click(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.next:
                if(pwd.getText().toString().trim().equals("")){
                    T.showShort("不可输入空密码");
                    return;
                }
                count ++ ;
                switch (count){
                    case 1:
                        if(check())
                            PreferenceUtils.setPrefString(mContext, "one", pwd.getText().toString().trim());
                            TwoClick();
                        break;
                    case 2:
                        if(check()) {
                            PreferenceUtils.setPrefString(mContext, "two", pwd.getText().toString().trim());
                            ThreeClick();
                        }
                        break;
                    case 3:
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
        setContentView(R.layout.activity_pay_pass);
        init();

    }

    /**
     * 初始化
     */
    private void init() {
        title.setText("修改支付密码");
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary), 225);
    }


    private void editPayPass() {
        String oldPay = PreferenceUtils.getPrefString(mContext,"one","");
        String payPwd = PreferenceUtils.getPrefString(mContext,"two","");
        String confirm = PreferenceUtils.getPrefString(mContext,"three","");
        mBuilder.setTitle("请稍候...").show();
        OkGo.<String>post(Constants.URL_BASE + "user/payPwd")//
                .tag(this)//
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .params("oldPay", MD5Util.encrypt(oldPay))
                .params("payPwd", MD5Util.encrypt(payPwd))
                .params("confirm", MD5Util.encrypt(confirm))
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
                                ActivityUtils.toLogin(EdPayPassActivity.this,0);
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


    private void TwoClick() {
        tip.setText("请设置支付密码，用于支付验证");
        pwd.setText("");
    }

    private void ThreeClick() {
        tip.setText("请再次输入密码");
        pwd.setText("");
        next.setText("完成");
        pwd.setOnInputFinishListener(new PwdEditText.OnInputFinishListener() {

            @Override
            public void onInputFinish(String password) {
                Log.i("000000",PreferenceUtils.getPrefString(mContext, "one", "")
                        + PreferenceUtils.getPrefString(mContext, "two", "")+
                        pwd.getText().toString().trim());
                if (!pwd.getText().toString().trim().equals(PreferenceUtils.getPrefString(mContext, "two", ""))) {
                    T.showShort("两次输入的密码不一致，请重新输入");
                    next.setVisibility(View.GONE);
                }else {
                    next.setVisibility(View.VISIBLE);
                    PreferenceUtils.setPrefString(mContext, "three", pwd.getText().toString().trim());
                }
            }
        });

    }

    boolean check(){
        if(ActivityUtils.getView(pwd).equals("")){
            T.showShort("密码不能为空");
            return false;
        }
        else return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceUtils.setPrefString(mContext, "one","");
        PreferenceUtils.setPrefString(mContext, "two","");
        PreferenceUtils.setPrefString(mContext, "three","");
    }
}
