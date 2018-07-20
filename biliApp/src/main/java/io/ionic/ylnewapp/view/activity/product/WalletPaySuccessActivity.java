package io.ionic.ylnewapp.view.activity.product;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class WalletPaySuccessActivity extends BaseActivity {

    @ViewInject(R.id.tv_title)
    TextView title;
    @ViewInject(R.id.walletNum)
    TextView walletNum;
    @ViewInject(R.id.walletAddress)
    TextView walletAddress;
    @ViewInject(R.id.orderMoney)
    TextView orderMoney;
    @ViewInject(R.id.orderNum)
    TextView orderNum;

    String orderId,money="";

    @Event(type = View.OnClickListener.class,value ={ R.id.tv_back,R.id.copyWallet,R.id.paycomplete})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back :
                finish();
                break;
            case R.id.copyWallet:
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);// 将文本内容放到系统剪贴板里。
                cm.setText(walletNum.getText());
                T.showShort("复制成功");
                break;
            case R.id.paycomplete:
                payComplete(orderId);
                break;
        }
    }

    /**
     * 提醒管理员充值确认
     */
    private void payComplete(String id) {
        OkGo.<String>put(Constants.URL_BASE + "order/sms/admin")//
                .tag(this)//
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .params("orderid", id)
                .params("money",money +" " +PreferenceUtils.getPrefString(mContext,"bz",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        JSONObject jsonObject ;
                        try {
                            jsonObject= new JSONObject(response.body());
                            T.showShort(jsonObject.getString("msg"));
                            if(jsonObject.getString("status").equals("401"))
                                ActivityUtils.toLogin(WalletPaySuccessActivity.this,0);
                            if(jsonObject.getString("status").equals("200"))
                                finish();
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_pay_success);
        init();
        getData();
    }

    /**
     * 获取传递数据
     */
    private void getData() {
        Intent intent = getIntent();
        Bundle bundle =intent.getExtras();
        orderId = bundle.getString("orderId");
        money = bundle.getString("money");
        orderMoney.setText(money);
        orderNum.setText(orderId);
        walletAddress.setText(bundle.getString("walletNum"));
        if(bundle.getString("type").equals("BTC"))
            walletNum.setText("1QGDMGUWDTYDcMFufuAHAzx3gZLWKWGVhD");
        else
            walletNum.setText("0x65e7Fd5479beB0D11C0073259E22809E2e3BEa6e");
    }


    /**
     *初始化
     */
    private void init() {
        title.setText("支付详情");
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary), 225);
    }

}
