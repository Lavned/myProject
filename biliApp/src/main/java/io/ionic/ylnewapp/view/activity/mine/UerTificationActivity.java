package io.ionic.ylnewapp.view.activity.mine;

import android.content.ClipboardManager;
import android.content.Context;
import android.media.effect.Effect;
import android.support.v7.app.AppCompatActivity;
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
import io.ionic.ylnewapp.custom.MyDialog;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class UerTificationActivity extends BaseActivity {


    @ViewInject(R.id.tv_title)
    TextView title;// base 标题
    @ViewInject(R.id.user_name)
    EditText userName;//
    @ViewInject(R.id.user_idcard)
    EditText userIDcard;//
    @ViewInject(R.id.user_email)
    EditText userEmail;//
    @ViewInject(R.id.user_zfb)
    EditText userZfb;//
    @ViewInject(R.id.user_wx)
    EditText userWx;//
    @ViewInject(R.id.user_qq)
    EditText userQq;//

    @Event(type = View.OnClickListener.class,value ={ R.id.tv_back,R.id.btn_sumbit})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back :
                finish();
                break;
            case R.id.btn_sumbit:
                if(checkEmpty()){
                    tificationData();
                }
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uer_tification);
        init();
    }


    /**
     * 提交实名信息
     */
    private void tificationData() {
        OkGo.<String>put(Constants.URL_BASE + "user/verify")//
                .tag(this)//
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .params("name", getView(userName))
                .params("zhifuPay", getView(userZfb))
                .params("email",getView(userEmail))
                .params("qq", getView(userQq))
                .params("weixin", getView(userWx))
                .params("idcard", getView(userIDcard))
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
     *初始化
     */
    private void init() {
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
        title.setText("实名认证");
    }


    /**
     * feikongyanzheng
     * @return
     */
    public boolean checkEmpty() {
        if (getView(userName).equals("")) {
            T.showShort("用户名不可为空");
            return false;
        }else if (getView(userIDcard).equals("")) {
            T.showShort("身份证号不可为空");
            return false;
        }
        return true;
    }

    /**
     * 获取viewText
     * @param editText
     * @return
     */
    public String getView(EditText editText){
        return editText.getText().toString().trim();
    }
}
