package io.ionic.ylnewapp.view.main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.custom.MyViewPager;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;
import io.ionic.ylnewapp.view.fragment.FragmentFour;
import io.ionic.ylnewapp.view.fragment.FragmentOne;
import io.ionic.ylnewapp.view.fragment.FragmentThree;
import io.ionic.ylnewapp.view.fragment.FragmentTwo;
import io.ionic.ylnewapp.view.fragment.FragmentTwoNew;
import util.UpdateAppUtils;


public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {


    private long mExitTime;//退出计时
    @ViewInject(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;
//    @ViewInject(R.id.vp_home)
    public static MyViewPager mVpHome;
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private boolean isFullScreen = false;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.SET_DEBUG_APP,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.GET_ACCOUNTS,
                    Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }

        mVpHome = findViewById(R.id.vp_home);
        initFragment();
        initNavigatoinBar();
        checkUpdate();//更新
//
        if(PreferenceUtils.getPrefString(mContext,"item","").equals("1")) {
            Message msg = new Message();
            NOHandler myHandler = new NOHandler(mContext);
            msg.arg1 = 1;
            myHandler.sendMessage(msg);
        }


        if(PreferenceUtils.getPrefString(mContext,"item","").equals("2")) {
            Message msg = new Message();
            NOHandler myHandler = new NOHandler(mContext);
            msg.arg1 = 2;
            myHandler.sendMessage(msg);
        }
    }



    //fragment重写
    private void initFragment() {
        mVpHome.setScanScroll(false);
        mVpHome.setOnPageChangeListener(new MyViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mBottomNavigationBar.selectTab(position);
                switch (position) {
                    case 0:
                        isFullScreen = true;
                        StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, 0,null);
                        break;
                    default:
                        if (isFullScreen) {
                            resetFragmentView(mFragmentList.get(position));
                        }
                        StatusBarUtil.setColor(MainActivity.this, Color.parseColor("#ffffff"), 225);//
                        break;
                    case 3:
                        isFullScreen = true;
                        StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, 0,null);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
        mVpHome.setOffscreenPageLimit(0);
        mVpHome.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }

            @Override
            public int getItemPosition(Object object) {
                //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
                // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
                return PagerAdapter.POSITION_NONE;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                mFragmentList.get(position).onDestroyView();
                super.destroyItem(container, position, object);
            }
        });
    }


    //底部导航栏
    private void initNavigatoinBar() {
        TextBadgeItem badgeItem = new TextBadgeItem();//添加标记
        badgeItem.setHideOnSelect(false)
                .setText("10")
                .setBorderWidth(0);

        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.h_home, getString(R.string.item_home)).setInactiveIconResource(R.drawable.h_home_no).setActiveColorResource(R.color.main).setInActiveColorResource(R.color.black_1)
                ).addItem(new BottomNavigationItem(R.drawable.h_financial, getString(R.string.item_financial)).setInactiveIconResource(R.drawable.h_financial_no).setActiveColorResource(R.color.main).setInActiveColorResource(R.color.black_1))
                .addItem(new BottomNavigationItem(R.drawable.h_market, getString(R.string.item_market)).setInactiveIconResource(R.drawable.h_market_no).setActiveColorResource(R.color.main).setInActiveColorResource(R.color.black_1))
//                .addItem(new BottomNavigationItem(R.drawable.h_wallet, getString(R.string.item_wallet)).setInactiveIconResource(R.drawable.h_wallet_no).setActiveColorResource(R.color.main).setInActiveColorResource(R.color.black_1))
                .addItem(new BottomNavigationItem(R.drawable.h_mine, getString(R.string.item_mine)).setInactiveIconResource(R.drawable.h_mine_no).setActiveColorResource(R.color.main).setInActiveColorResource(R.color.black_1))
                .setFirstSelectedPosition(0)
                .initialise();

        mBottomNavigationBar.setTabSelectedListener(this);
        mFragmentList.add(new FragmentOne());
        mFragmentList.add(new FragmentTwoNew());
        mFragmentList.add(new FragmentThree());
//        mFragmentList.add(new FragmentWal());
        mFragmentList.add(new FragmentFour());
        intent = getIntent();
    }


    @Override
    public void onTabSelected(int position) {
        mVpHome.setCurrentItem(position,false);
    }

    @Override
    public void onTabUnselected(int position) {}

    @Override
    public void onTabReselected(int position) {}

    @Override
    protected void setStatusBar() {
        isFullScreen = true;
        StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, 0,null);
    }


    /**
     * 检测更新
      */
    String name;
    String apk;
    int v;
    private void checkUpdate() {
        OkGo.<String>get(Constants.URL_BASE + "home/version")
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        JSONObject gson = null;
                        try {
                            gson = new JSONObject(response.body());
                            JSONObject item = gson.getJSONObject("body").getJSONObject("android");
                            v = item.getInt("code");
                            name = item.getString("name");
                            apk = item.getString("apk");
                            if(v > Integer.parseInt(ActivityUtils.getVersion(mContext)) ){
                                checkAndUpdate();
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
     * 7.0权限检测
     */
    private void checkAndUpdate() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            update();
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                update();
            } else {//申请权限
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
    }
//
//
    //基本更新
    private void update() {
//            UpdateAppUtils.from(this)
//                .serverVersionCode(v)
//                .apkPath(apk)
//                .isForce(true)
//                .updateInfo("1.修复若干bug\n2.美化部分页面")
//                .showNotification(true)
//                .update();
//        1：完善产品体系
//        2：优化客户端，提升服务品质
//        3：新增邀请奖励功能
            UpdateAppUtils.from(this)
                    .serverVersionCode(v)
                    .apkPath(apk)
                    .updateInfo("1.完善产品体系\n2.优化客户端，提升服务品质\n3.新增邀请奖励功能")
                    .downloadBy(UpdateAppUtils.DOWNLOAD_BY_BROWSER)
                    .isForce(true)
                    .update();

    }

    //权限请求结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    update();
                } else {
//                    new ConfirmDialog(this, new Callback() {
//                        @Override
//                        public void callback(int position) {
//                            if (position==1){
//                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                                intent.setData(Uri.parse("package:" + getPackageName())); // 根据包名打开对应的设置界面
//                                startActivity(intent);
//                            }
//                        }
//                    }).setContent("暂无读写SD卡权限\n是否前往设置？").show();
                }
                break;
        }

    }


    //跳转到其他的fragment页面
    public interface Fragment2Fragment{
        public void gotoFragment(MyViewPager viewPager);
    }
    private  Fragment2Fragment fragment2Fragment;
    public void setFragment2Fragment(Fragment2Fragment fragment2Fragment){
        this.fragment2Fragment = fragment2Fragment;
    }

    public void forSkip(){
        if(fragment2Fragment!=null){
            fragment2Fragment.gotoFragment(mVpHome);
        }
    }


    //沉浸式重绘
    public void resetFragmentView(Fragment fragment) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View contentView = findViewById(android.R.id.content);
            if (contentView != null) {
                View rootView;
                rootView =  ((ViewGroup) contentView).getChildAt(0);
                if (rootView.getPaddingTop() != 0) {
                    rootView.setPadding(0, 0, 0, 0);
                }
            }
            if (fragment.getView() != null) fragment.getView().setPadding(0, getStatusBarHeight(this), 0, 0);
        }
    }
    // 获得状态栏高度
    private static int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }


    //销毁
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ImmersionBar.with(this).destroy(); //释放沉浸式Title,必须调用该方法，防止内存泄漏
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                T.showShort(R.string.app_exit_prompt);
                mExitTime = System.currentTimeMillis();
                return true;
            } else {
                ((BLApplication) getApplication()).finishStack();
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    //用来更新
    public static class NOHandler extends Handler {
        private Context context;

        public NOHandler(Context context) {
            this.context = context;
        }

        // 子类必须重写此方法,接受数据
        @Override
        public void handleMessage(Message msg) {
            try{
                if(msg.what ==1){
                    mVpHome.setCurrentItem(1,false);
                    PreferenceUtils.setPrefString(context,"test","test");

                    Message message = new Message();
                    message.what = 4;
                    FragmentTwo.MyHandler myHandler = new FragmentTwo.MyHandler(context);
                    myHandler.sendMessage(message);
                }
                if(msg.what ==2){
                    mVpHome.setCurrentItem(1,false);
                }
                if(msg.arg1 == 1){
                    mVpHome.setCurrentItem(1,false);
                    PreferenceUtils.setPrefString(context,"item","");
                }
                if(msg.arg1 == 2){
                    mVpHome.setCurrentItem(1,false);
                    PreferenceUtils.setPrefString(context,"item","");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
