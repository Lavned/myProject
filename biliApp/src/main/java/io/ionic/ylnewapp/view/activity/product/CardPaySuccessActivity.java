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

public class CardPaySuccessActivity extends BaseActivity {


    @ViewInject(R.id.tv_title)
    TextView title;
    @ViewInject(R.id.cardNum)
    TextView cardNum;
    @ViewInject(R.id.orderCard)
    TextView orderCard;
    @ViewInject(R.id.orderMoney)
    TextView orderMoney;
    @ViewInject(R.id.orderNum)
    TextView orderNum;

    String orderId,money="";

    @Event(type = View.OnClickListener.class,value ={ R.id.tv_back,R.id.copyCard,R.id.paycomplete})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back :
                finish();
                break;
            case R.id.copyCard:
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);// 将文本内容放到系统剪贴板里。
                cm.setText(cardNum.getText());
                T.showShort("复制成功");
                break;
            case R.id.paycomplete:
                payComplete();
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_pay_success);
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
        orderCard.setText(bundle.getString("cardId"));
    }

    /**
     *初始化
     */
    private void init() {
        title.setText("支付详情");
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary), 225);
    }

    /**
     * 提醒管理员充值确认
     */
    private void payComplete() {
        OkGo.<String>put(Constants.URL_BASE + "order/sms/admin")//
                .tag(this)//
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(mContext,"token",""))
                .params("orderid", orderId)
                .params("money",money +" 元")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        JSONObject jsonObject ;
                        try {
                            jsonObject= new JSONObject(response.body());
                            T.showShort(jsonObject.getString("msg"));
                            if(jsonObject.getString("status").equals("401"))
                                ActivityUtils.toLogin(CardPaySuccessActivity.this,0);
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

}
