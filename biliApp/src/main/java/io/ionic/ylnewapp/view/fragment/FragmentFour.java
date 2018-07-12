package io.ionic.ylnewapp.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.umeng.analytics.MobclickAgent;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.adpater.FourCoinAdapter;
import io.ionic.ylnewapp.bean.AccountBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.custom.MyListView;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.activity.mine.BankListActivity;
import io.ionic.ylnewapp.view.activity.mine.CouponsActivity;
import io.ionic.ylnewapp.view.activity.mine.FeedbackActivity;
import io.ionic.ylnewapp.view.activity.mine.HelpActivity;
import io.ionic.ylnewapp.view.activity.mine.MoneyDetailActivity;
import io.ionic.ylnewapp.view.activity.mine.MyCountCoinActivity;
import io.ionic.ylnewapp.view.activity.mine.MyCountMoneyActivity;
import io.ionic.ylnewapp.view.activity.order.MyOrderActivity;
import io.ionic.ylnewapp.view.activity.mine.NotificationActivity;
import io.ionic.ylnewapp.view.activity.mine.SettingActivity;
import io.ionic.ylnewapp.view.activity.mine.UerTificationActivity;
import io.ionic.ylnewapp.view.activity.user.LoginActivity;
import io.ionic.ylnewapp.view.activity.wallet.WalletManaActivity;
import io.ionic.ylnewapp.view.base.BaseFragment;

@ContentView(R.layout.fragment_four)
public class FragmentFour extends BaseFragment {



    @ViewInject(R.id.count_money)
    TextView count;
    @ViewInject(R.id.my_num)
    MyListView my_num;
    @ViewInject(R.id.username)
    TextView username;
    @ViewInject(R.id.unOrder)
    TextView unOrder;
    Context context;

    List<AccountBean.BodyBean.CoinsBean> item;//数字资产

    /**
     * 点击
     * @param view
     */
    @Event(type = View.OnClickListener.class,value = {R.id.user_certification,R.id.mine_notification,R.id.re_bank,R.id.re_coupons,
            R.id.my_setting,R.id.text_feed,R.id.certification,R.id.username,R.id.my_coin_count,
            R.id.re_wallet ,R.id.money_detail,R.id.my_money_count,R.id.help,R.id.qq,R.id.unOrder})
    private void click(View view){
        switch (view.getId()){
            case R.id.user_certification:
                startActivity(new Intent(getActivity(),UerTificationActivity.class));
                break;
            case R.id.mine_notification :
                startActivity(new Intent(getActivity(),NotificationActivity.class));
                break;
            case R.id.certification :
                startActivity(new Intent(getActivity(),UerTificationActivity.class));
                break;
            case R.id.username :
//                startActivity(new Intent(getActivity(),LoginActivity.class));
                break;
            case R.id.re_bank :
                startActivity(new Intent(getActivity(),BankListActivity.class));
                break;
            case R.id.re_coupons :
                startActivity(new Intent(getActivity(),CouponsActivity.class));
                break;
            case R.id.my_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.text_feed :
                startActivity(new Intent(getActivity(), FeedbackActivity.class));
                break;
            case R.id.money_detail:
                startActivity(new Intent(getActivity(), MoneyDetailActivity.class));
                break;
            case R.id.re_wallet :
                startActivity(new Intent(getActivity(), WalletManaActivity.class));
                break;
            case R.id.my_money_count :
                startActivity(new Intent(getActivity(),MyCountMoneyActivity.class));
                break;
            case R.id.my_coin_count:
                if(item!=null && item.size() >0)
                    startActivity(new Intent(getActivity(),MyCountCoinActivity.class));
                break;
            case R.id.unOrder :
                toOrder(0);
                break;
            case R.id.help :
                startActivity(new Intent(getActivity(),HelpActivity.class));
                break;
            case R.id.qq:
                String qqNum = "2852370178";
                if (checkApkExist(getActivity(), "com.tencent.mobileqq")){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin="+qqNum+"&version=1")));
                }else{
                   T.showShort("本机未安装QQ应用");
                }
                break;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = x.view().inject(this, inflater, container);
        context = getActivity();
        init();
        return view;
    }


    private void toOrder(int type){
        Intent intent = new Intent(getActivity(), MyOrderActivity.class);
        switch (type){
            case 1://已付款
                intent.putExtra("status",type);
                startActivity(intent);
                break;
            case 2://待赎回
                intent.putExtra("status",type);
                startActivity(intent);
                break;
            case 3://已赎回
                intent.putExtra("status",type);
                startActivity(intent);
                break;
            case 0://未付款
                intent.putExtra("status",type);
                startActivity(intent);
                break;
            case 5://全部
                intent.putExtra("status",type);
                startActivity(intent);
                break;
        }
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
                        if(javaBean.getStatus() == 401)
                            ActivityUtils.toLogin(getActivity(),1);
                        if(javaBean.getBody()!=null){
                            count.setText("总资产"+javaBean.getBody().getCount()+"");
                            username.setText(javaBean.getBody().getUsername()+"");
                            item = javaBean.getBody().getCoins();
                            initCoinList(item);
                            if(javaBean.getBody().getUnPay() ==1){
                                unOrder.setVisibility(View.VISIBLE);
                            }
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showNetworkError(getActivity());
                    }

                });
    }

    /**
     * 初始化我的数字资产
     * @param item
     */
    private void initCoinList(List<AccountBean.BodyBean.CoinsBean> item) {
        FourCoinAdapter adapter = new FourCoinAdapter(getActivity(),item);
        my_num.setAdapter(adapter);
    }



    /**
     * 检测是否安装QQ
     * @param context
     * @param packageName
     * @return
     */
    public boolean checkApkExist(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName,
                    PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(getActivity());
    }

}
