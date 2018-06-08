package io.ionic.ylnewapp.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiangyy.easydialog.LoadingDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.response.AccountBean;
import io.ionic.ylnewapp.bean.response.BankBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.activity.mine.BankListActivity;
import io.ionic.ylnewapp.view.activity.mine.FeedbackActivity;
import io.ionic.ylnewapp.view.activity.mine.MoneyDetailActivity;
import io.ionic.ylnewapp.view.activity.mine.MyActiActivity;
import io.ionic.ylnewapp.view.activity.mine.NotificationActivity;
import io.ionic.ylnewapp.view.activity.NumberCurrencyWalltActivity;
import io.ionic.ylnewapp.view.activity.mine.OrderActivity;
import io.ionic.ylnewapp.view.activity.mine.RechargeActivity;
import io.ionic.ylnewapp.view.activity.mine.SettingActivity;
import io.ionic.ylnewapp.view.activity.mine.UerTificationActivity;
import io.ionic.ylnewapp.view.activity.mine.CouponsActivity;
import io.ionic.ylnewapp.view.activity.mine.WithdrawalActivity;
import io.ionic.ylnewapp.view.base.BaseFragment;
import q.rorbin.badgeview.QBadgeView;

@ContentView(R.layout.fragment_four)
public class FragmentFour extends BaseFragment {



    @ViewInject(R.id.count_money)
    TextView count;
    @ViewInject(R.id.money)
    TextView money;
    @ViewInject(R.id.username)
    TextView username;

    Context context;
    List<BankBean.BodyBean> mData = new ArrayList<>();
    String allMoney;


    /**
     * 点击
     * @param view
     */
    @Event(type = View.OnClickListener.class,value = {R.id.user_certification,R.id.mine_notification,R.id.re_bank,R.id.re_coupons,
            R.id.re_wallet ,R.id.money_in,R.id.monet_out,R.id.my_order,R.id.my_setting,R.id.my_activity,R.id.text_feed
                ,R.id.money_detail})
    private void click(View view){
        switch (view.getId()){
            case R.id.user_certification:
                startActivity(new Intent(getActivity(),UerTificationActivity.class));
                break;
            case R.id.mine_notification :
                startActivity(new Intent(getActivity(),NotificationActivity.class));
                break;
            case R.id.re_bank :
                startActivity(new Intent(getActivity(),BankListActivity.class));
                break;
            case R.id.re_coupons :
                startActivity(new Intent(getActivity(),CouponsActivity.class));
                break;
            case R.id.re_wallet :
                startActivity(new Intent(getActivity(),NumberCurrencyWalltActivity.class));
                break;
            case R.id.money_in :
                getBankList(1);
                break;
            case R.id.monet_out :
                getBankList(2);
                break;
            case R.id.my_order :
                startActivity(new Intent(getActivity(), OrderActivity.class));
                break;
            case R.id.my_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.my_activity:
                startActivity(new Intent(getActivity(), MyActiActivity.class));
                break;
            case R.id.text_feed :
                startActivity(new Intent(getActivity(), FeedbackActivity.class));
                break;
            case R.id.money_detail:
                startActivity(new Intent(getActivity(), MoneyDetailActivity.class));
                break;

        }

    }

    @ViewInject(R.id.tv_type1)
    TextView type1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = x.view().inject(this, inflater, container);
        context = getActivity();
        new QBadgeView(context).bindTarget(type1).setBadgeNumber(5)
                .setBadgeGravity(Gravity.TOP | Gravity.END).
                setBadgeTextSize(9, true).setBadgePadding(1, true);
        init();
        return view;
    }

    /**
     * 初始化
     */
    private void init() {
        getAccount();
    }


    /**
     * 获取用户信息
     */
    private void getAccount() {
        OkGo.<String>get(Constants.URL_BASE + "user/account" )
                .tag(this)
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(getActivity(),"token",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        AccountBean javaBean =gson.fromJson(response.body().toString(),AccountBean.class);
                        if(javaBean.getStatus() == 401){
                            ActivityUtils.toLogin(getActivity());
                        }
                        count.setText(javaBean.getBody().getCount()+"");
                        money.setText(javaBean.getBody().getQianbao()+"");
                        username.setText(javaBean.getBody().getUsername()+"");
                        PreferenceUtils.setPrefString(getActivity(),"money",javaBean.getBody().getQianbao()+"");
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showNetworkError(getActivity());
                    }

                });
    }



    /**
     * 判断银行卡
     */
    LoadingDialog.Builder mBuilder;
    private void getBankList(final int type) {
        mBuilder =  new LoadingDialog.Builder(getActivity());
        mBuilder.setTitle("正在加载银行卡信息").show();
        OkGo.<String>get(Constants.URL_BASE + "user/bank" )
                .tag(this)
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(getActivity(),"token",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        BankBean javaBean =gson.fromJson(response.body().toString(),BankBean.class);
                        if(javaBean.getStatus() == 401){
                            ActivityUtils.toLogin(getActivity());
                        }else if(javaBean.getStatus() == 500){
                            T.showShort("服务异常");
                        }
                        mData = javaBean.getBody();
                        if(mData!=null && mData.size() > 0){
                            if(type == 1)
                                startActivity(new Intent(getActivity(), RechargeActivity.class));
                            else
                                startActivity(new Intent(getActivity(), WithdrawalActivity.class));
                        }else {
                            T.showShort("请先绑定银行卡");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showNetworkError(getActivity());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        mBuilder.dismiss();
                    }
                });
    }


}
