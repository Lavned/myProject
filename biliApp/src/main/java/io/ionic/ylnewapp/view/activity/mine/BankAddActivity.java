package io.ionic.ylnewapp.view.activity.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
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
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class BankAddActivity extends BaseActivity {

    @ViewInject(R.id.list_bank)
    ListView listBank;
    @ViewInject(R.id.tv_title)
    TextView title;

    @ViewInject(R.id.ebank_num)
    EditText bankNum;
    @ViewInject(R.id.ebank_khhname)
    EditText bankKhh;
    @ViewInject(R.id.ebank_code)
    EditText bankCode;
    @ViewInject(R.id.bank_get_code)
    TextView bank_get_code;

    TimeCount timer;


    @Event(type = View.OnClickListener.class,value = {R.id.tv_back,R.id.add_btn,R.id.bank_get_code})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.add_btn:
                if(ActivityUtils.getView(bankNum).equals("")){
                    T.showShort("请输入银行卡");
                    return;
                }else if(ActivityUtils.getView(bankKhh).equals("")){
                    T.showShort("请输入开户行信息");
                    return;
                }else if(ActivityUtils.getView(bankCode).equals("")){
                    T.showShort("请输入验证码");
                    return;
                }
                addBank();
                break;
            case R.id.bank_get_code:
                timer.start();
                getCode();
                break;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_add);
        init();
        timer = new TimeCount(60000, 1000);
    }

    /**
     * 初始化
     */
    private void init() {
        title.setText("添加银行卡");
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);

    }

    /**
     * 提交信息
     */
    private void addBank() {
        OkGo.<String>put(Constants.URL_BASE + "user/bank" )
                .tag(this)
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .params("name", ActivityUtils.getView(bankKhh))
                .params("count", ActivityUtils.getView(bankNum))
                .params("smscode",ActivityUtils.getView(bankCode))
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

    //获取验证码
    private void getCode() {
        OkGo.<String>post(Constants.URL_BASE + "user/sms")//
                .tag(this)//
                .params("username",  PreferenceUtils.getPrefString(mContext,"account",""))
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


    //倒计时定时器
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            bank_get_code.setBackgroundColor(Color.parseColor("#666666"));
            bank_get_code.setClickable(false);
            bank_get_code.setText("("+millisUntilFinished / 1000 +") 秒后可重新发送");
        }

        @Override
        public void onFinish() {
            bank_get_code.setText("重新获取验证码");
            bank_get_code.setClickable(true);
            bank_get_code.setBackgroundColor(Color.parseColor("#FEA620"));

        }
    }

}
