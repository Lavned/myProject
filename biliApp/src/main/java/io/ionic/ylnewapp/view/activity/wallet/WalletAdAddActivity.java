package io.ionic.ylnewapp.view.activity.wallet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import io.ionic.ylnewapp.view.activity.mine.BankListActivity;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class WalletAdAddActivity extends BaseActivity {


    @ViewInject(R.id.tv_title)
    TextView title;

    @ViewInject(R.id.walletname)
    EditText walletname;
    @ViewInject(R.id.walletaddress)
    EditText walletaddress;
    @ViewInject(R.id.walletcode)
    EditText walletcode;



    @Event(type = View.OnClickListener.class,value = {R.id.tv_back,R.id.add_btn,R.id.get_code})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.add_btn:
                if(ActivityUtils.getView(walletname).equals("")){
                    T.showShort("请输入钱包名称");
                    return;
                }else if(ActivityUtils.getView(walletaddress).equals("")){
                    T.showShort("请输入钱包地址");
                    return;
                }else if(ActivityUtils.getView(walletcode).equals("")){
                    T.showShort("请输入验证码");
                    return;
                }
                addWallet();
                break;
            case R.id.get_code:
                getCode();
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_address);
        init();
    }


    /**
     * 添加
     */
    private void addWallet() {
        mBuilder.setTitle("请稍候").show();
        OkGo.<String>put(Constants.URL_BASE + "user/address")//
                .tag(this)//
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .params("type",  ActivityUtils.getView(walletname))
                .params("address",   ActivityUtils.getView(walletaddress))
                .params("smscode",  ActivityUtils.getView(walletcode))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            T.showShort(jsonObject.getString("msg"));
                            switch (jsonObject.getInt("status")){
                                case 200:
                                    finish();
                                    break;
                                case 401:
                                    ActivityUtils.toLogin(WalletAdAddActivity.this);
                                    break;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showShort(response.toString());
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
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
        title.setText("添加钱包地址");
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
                        T.showShort(response.toString());
                    }
                });
    }

}
