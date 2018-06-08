package io.ionic.ylnewapp.view.activity.mine;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
import io.ionic.ylnewapp.view.activity.wallet.WalletOptionSuccessActivity;
import io.ionic.ylnewapp.view.base.BaseActivity;

//用户充值
public class RechargeActivity extends BaseActivity {




    @ViewInject(R.id.tv_title)
    TextView title;// base 标题
    @ViewInject(R.id.paymoney)
    EditText paymoney;
    @ViewInject(R.id.icon)
    ImageView icon;
    @ViewInject(R.id.name)
    TextView name;


    @Event(type = View.OnClickListener.class,value ={ R.id.tv_back,R.id.sub_btn,R.id.num_copy,R.id.my_address})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back :
                finish();
                break;
            case R.id.sub_btn:
                tofinish();
//                if(checkEmpty())
//                    loadData();
                break;
            case R.id.num_copy:
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);// 将文本内容放到系统剪贴板里。
                cm.setText("6217001210029664974");
                T.showShort("复制成功，可以发给朋友们了");
                break;
            case R.id.my_address :
                startActivity(new Intent(mContext,SelectBankActivity.class));
        }
    }

    //关闭界面
    private void tofinish() {
        PreferenceUtils.setPrefString(mContext,"bank","");
        PreferenceUtils.setPrefString(mContext,"name","");
        PreferenceUtils.setPrefString(mContext,"icon","");
        Intent intent =new Intent(mContext,WalletOptionSuccessActivity.class);
        intent.putExtra("name","3");
        startActivity(intent);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        title.setText("充值");
    }


    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    /**
     * 数据复制
     */
    private void init() {
        String names = PreferenceUtils.getPrefString(mContext,"name","");
        if(!names.equals("")){
            Glide.with(mContext)
                    .load(PreferenceUtils.getPrefString(mContext,"icon",""))
                    .into(icon);
            name.setText(PreferenceUtils.getPrefString(mContext,"name",""));
        }

    }

    /**
     * 提交
     */
    private void loadData() {
        OkGo.<String>put(Constants.URL_BASE + "user/charge")//
                .tag(this)//
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .params("payAmount", ActivityUtils.getView(paymoney))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            T.showShort(jsonObject.getString("msg"));
                            if(jsonObject.getString("status").equals("200")){
                                Intent intent =new Intent(mContext,WalletOptionSuccessActivity.class);
                                intent.putExtra("name","3");
                                startActivity(intent);
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
     * feikongyanzheng
     * @return
     */
    public boolean checkEmpty() {
        if (ActivityUtils.getView(paymoney).equals("")) {
            T.showShort("用户名不可为空");
            return false;
        }
        return true;
    }

}
