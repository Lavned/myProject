package io.ionic.ylnewapp.view.activity.wallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.adpater.WalletDetailAdapater;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class WalletOutMoneyActivity extends BaseActivity {

    @ViewInject(R.id.tv_title)
    TextView title;
    @ViewInject(R.id.ttitle_name)
    TextView tName;
    @ViewInject(R.id.ttitle_img)
    ImageView tImg;
    @ViewInject(R.id.pay_Amount)
    EditText payMount;
    @ViewInject(R.id.ma_walName)
    TextView ma_walName;

    @Event(type = View.OnClickListener.class,value = {R.id.tv_back,R.id.submit_btn,R.id.my_address})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.my_address:
                startActivity(new Intent(mContext, MyWaletAddressActivity.class));
                break;
            case R.id.submit_btn:
                if (checkEmpty())
                    submitData();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_out_money);
        init();
    }

    /**
     * 网络操作
     */
    private void submitData() {
        mBuilder.setTitle("操作中...").show();
        OkGo.<String>put(Constants.URL_BASE + "user/coin/cash")//
                .tag(this)//
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext, "token", ""))
                .params("payAmount", ActivityUtils.getView(payMount))
                .params("name", tName.getText().toString())
                .params("id", PreferenceUtils.getPrefString(mContext, "walid", ""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            T.showShort(jsonObject.getString("msg"));
                            if (jsonObject.getString("status").equals("401"))
                                ActivityUtils.toLogin(WalletOutMoneyActivity.this,0);
                            if (jsonObject.getString("status").equals("200")) {
                                tofinish();
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

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        mBuilder.dismiss();
                    }
                });

    }

    @Override
    protected void onResume() {
        super.onResume();
        initResume();
    }

    /**
     * 数据复制
     */
    private void initResume() {
        String names = PreferenceUtils.getPrefString(mContext,"wlname","");
        if(!names.equals("")){
            ma_walName.setText(names);
        }
    }


    //关闭界面
    private void tofinish() {
        Intent intent =new Intent(mContext,WalletOptionSuccessActivity.class);
        intent.putExtra("name","2");
        startActivity(intent);
        finish();
    }

    /**
     *
     */
    private void init() {
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
        Intent intent = getIntent();
        String titleName = intent.getStringExtra("name");
        int i = titleName.indexOf("|");
        title.setText( titleName.substring(0,i));
        tName.setText( titleName.substring(0,i));
        Glide.with(mContext).load(titleName.substring(i+1,titleName.length())).into(tImg);
    }


    /**
     * feikongyanzheng
     * @return
     */
    public boolean checkEmpty() {
        if (ActivityUtils.getView(payMount).equals("")) {
            T.showShort("金额不可为空");
            return false;
        }
        if(ma_walName.getText().toString().equals("我的地址")){
            T.showShort("请选择钱包");
            return false;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceUtils.setPrefString(mContext,"wlname","");
        PreferenceUtils.setPrefString(mContext, "walid", "");
    }

}
