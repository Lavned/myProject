package io.ionic.ylnewapp.view.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.jaeger.library.StatusBarUtil;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.custom.MyViewPager;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;
import io.ionic.ylnewapp.view.fragment.FragmentFour;
import io.ionic.ylnewapp.view.fragment.FragmentOne;
import io.ionic.ylnewapp.view.fragment.FragmentThree;
import io.ionic.ylnewapp.view.fragment.FragmentTwo;


public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {


    private long mExitTime;//退出计时


    @ViewInject(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;
//    @ViewInject(R.id.vp_home)
    public static MyViewPager mVpHome;

    private ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private boolean isFullScreen = false;


    Intent intent;
    String name = "";
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVpHome = findViewById(R.id.vp_home);
        initFragment();
        initNavigatoinBar();
    }



//    @Subscribe(threadMode = ThreadMode.MAIN )
//    public void Event(MessageEvent messageEvent) {
//        name = messageEvent.getMessage();
//        mVpHome.setCurrentItem(Integer.parseInt(messageEvent.getMessage()),false);
//    }


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
//                    case 3:
//                        isFullScreen = true;
//                        StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, 0,null);
//                        break;
                    case 4:
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
//                        .setBadgeItem(badgeItem))//设置提醒
                ).addItem(new BottomNavigationItem(R.drawable.h_financial, getString(R.string.item_financial)).setInactiveIconResource(R.drawable.h_financial_no).setActiveColorResource(R.color.main).setInActiveColorResource(R.color.black_1))
                .addItem(new BottomNavigationItem(R.drawable.h_market, getString(R.string.item_market)).setInactiveIconResource(R.drawable.h_market_no).setActiveColorResource(R.color.main).setInActiveColorResource(R.color.black_1))
//                .addItem(new BottomNavigationItem(R.drawable.h_wallet, getString(R.string.item_wallet)).setInactiveIconResource(R.drawable.h_wallet_no).setActiveColorResource(R.color.main).setInActiveColorResource(R.color.black_1))
                .addItem(new BottomNavigationItem(R.drawable.h_mine, getString(R.string.item_mine)).setInactiveIconResource(R.drawable.h_mine_no).setActiveColorResource(R.color.main).setInActiveColorResource(R.color.black_1))
                .setFirstSelectedPosition(0)
                .initialise();

        mBottomNavigationBar.setTabSelectedListener(this);
        mFragmentList.add(new FragmentOne());
        mFragmentList.add(new FragmentTwo());
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

    private static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }


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
                    mVpHome.setCurrentItem(1);
                    PreferenceUtils.setPrefString(context,"test","test");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
