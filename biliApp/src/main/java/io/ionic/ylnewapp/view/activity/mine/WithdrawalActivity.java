package io.ionic.ylnewapp.view.activity.mine;

import android.content.Intent;
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

public class WithdrawalActivity extends BaseActivity {




    @ViewInject(R.id.tv_title)
    TextView title;// base 标题
    @ViewInject(R.id.paymoney)
    EditText paymoney;
    @ViewInject(R.id.money_all)
    TextView moneyAll;
    @ViewInject(R.id.money_get)
    TextView getMoney;
    @ViewInject(R.id.icon)
    ImageView icon;
    @ViewInject(R.id.name)
    TextView name;
    @ViewInject(R.id.text1)
    TextView text1;
    @ViewInject(R.id.text2)
    TextView text2;
    @ViewInject(R.id.text3)
    TextView text3;


    String money ="0.00";


    @Event(type = View.OnClickListener.class,value ={ R.id.tv_back,R.id.sub_btn,R.id.my_address})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back :
                finish();
                break;
            case R.id.sub_btn:
                tofinish();
//                if(checkEmpty()){
//                    if(Double.parseDouble(ActivityUtils.getView(paymoney)) > Double.parseDouble(money) ){
//                        T.showShort("充值金额不能大于剩余金额");
//                    }else
//                        T.showShort("ok");
////                     loadData();
//                }
                break;
            case R.id.my_address :
                startActivity(new Intent(mContext,SelectBankActivity.class));
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal);
        init();
    }


    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    /**
     * chushihua
     */
    private void init() {
        title.setText("提现");
        money = PreferenceUtils.getPrefString(mContext,"money","");
        moneyAll.setText(money);
        getMoney.setText(money);
    }


    /**
     * 数据复制
     */
    private void initData() {
        String names = PreferenceUtils.getPrefString(mContext,"name","");
        if(!names.equals("")){
            Glide.with(mContext)
                    .load(PreferenceUtils.getPrefString(mContext,"icon",""))
                    .into(icon);

            name.setText(PreferenceUtils.getPrefString(mContext,"name",""));
            int j = names.indexOf("银行");
            text1.setText(names.substring(0,j+2));
            text2.setText(names.substring(j+2,name.length()));
            text3.setText(PreferenceUtils.getPrefString(mContext,"bank",""));
        }

    }

    //关闭界面
    private void tofinish() {
        PreferenceUtils.setPrefString(mContext,"name","");
        PreferenceUtils.setPrefString(mContext,"icon","");
        PreferenceUtils.setPrefString(mContext,"bank","");
        Intent intent =new Intent(mContext,WithdrawSuccessActivity.class);
        intent.putExtra("card","3333");
        startActivity(intent);
        finish();
    }

    /**
     * 提交
     */
    private void loadData() {
        OkGo.<String>put(Constants.URL_BASE + "user/cash")//
                .tag(this)//
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .params("payAmount", ActivityUtils.getView(paymoney))
                .params("bankId", ActivityUtils.getView(paymoney))
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
     * feikongyanzheng
     * @return
     */
    public boolean checkEmpty() {
        if (ActivityUtils.getView(paymoney).equals("")) {
            T.showShort("不可为空");
            return false;
        }
        return true;
    }

}
