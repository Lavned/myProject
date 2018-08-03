package io.ionic.ylnewapp.view.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaeger.library.StatusBarUtil;
import com.umeng.analytics.MobclickAgent;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.view.base.BaseFragment;
import io.ionic.ylnewapp.view.twofragment.Tab1Fragment;
import io.ionic.ylnewapp.view.twofragment.Tab2Fragment;
import io.ionic.ylnewapp.view.twofragment.Tab3Fragment;
import io.ionic.ylnewapp.view.twofragment.Tab4Fragment;
import io.ionic.ylnewapp.view.twofragment.Tab5Fragment;
import io.ionic.ylnewapp.view.twofragment.Tab6Fragment;
import io.ionic.ylnewapp.view.twofragment.Tab7Fragment;
import io.ionic.ylnewapp.view.twofragment.Tab8Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_two)
public class FragmentTwo extends BaseFragment{



    public static TabLayout mTabLayout;
    public static ViewPager mViewPager;
     ViewPagerAdapter mAdapter;
    private List<Fragment> mFragments;

    private List<String > mTabTitles;
    FragmentTwo fragment;
    boolean isClck ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  x.view().inject(this,inflater,container);
        mTabLayout = view.findViewById(R.id.id_tabLayout);
        mViewPager = view.findViewById(R.id.id_viewPager);
        fragment = this;
        initTab();
        return view;
    }

    /**
     * 视图是否已经对用户可见，系统的方法
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
//            initTab();

        }else {

        }
    }

    //用来更新
    public static class MyHandler extends Handler {
        private Context context;

        public MyHandler(Context context) {
            this.context = context;
        }

        // 子类必须重写此方法,接受数据
        @Override
        public void handleMessage(Message msg) {
            try{
//                if(msg.arg1 ==1){
//                    mViewPager.setCurrentItem(3,false);
//                    mTabLayout.getTabAt(3).select();
//                }
                switch (msg.what){
                    case 0:
                        mViewPager.setCurrentItem(0,false);
                        mTabLayout.getTabAt(0).select();
                        break;
                    case 1:
                        mViewPager.setCurrentItem(1,false);
                        mTabLayout.getTabAt(1).select();
                        break;
                    case 2:
                        mViewPager.setCurrentItem(2,false);
                        mTabLayout.getTabAt(2).select();
                        break;
                    case 3:
                        mViewPager.setCurrentItem(3,false);
                        mTabLayout.getTabAt(3).select();
                        break;
                    case 4:
                        mViewPager.setCurrentItem(4,false);
                        mTabLayout.getTabAt(4).select();
                        break;
                    case 5:
                        mViewPager.setCurrentItem(5,false);
                        mTabLayout.getTabAt(5).select();
                        break;
                    case 6:
                        mViewPager.setCurrentItem(6,false);
                        mTabLayout.getTabAt(6).select();
                        break;
                    case 7:
                        mViewPager.setCurrentItem(7,false);
                        mTabLayout.getTabAt(7).select();
                        break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        PreferenceUtils.setPrefString(getActivity(),"test","");
    }

    //初始化Tab
    private void initTab() {
        mFragments = new ArrayList<>();
        mTabTitles = new ArrayList<>();
        mTabTitles.clear();
        mTabLayout.removeAllTabs();
        mTabTitles.add("币余宝");
        mTabTitles.add("智能投顾");
        mTabTitles.add("ETF指数");
        mTabTitles.add("OTC套利");
        mTabTitles.add("BTC指数");
        mTabTitles.add("ICO基金");
        mTabTitles.add("ETF交易");
        mTabTitles.add("BTC交易");
        mFragments.add(new Tab1Fragment());
        mFragments.add(new Tab2Fragment());
        mFragments.add(new Tab3Fragment());
        mFragments.add(new Tab4Fragment());
        mFragments.add(new Tab5Fragment());
        mFragments.add(new Tab6Fragment());
        mFragments.add(new Tab7Fragment());
        mFragments.add(new Tab8Fragment());
        //设置TabLayout的一系列属性
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        mTabLayout.setTabTextColors(Color.parseColor("#333333"), Color.parseColor("#FEA620"));
        mTabLayout.removeAllTabs();

        mTabLayout.addTab(mTabLayout.newTab().setText(mTabTitles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabTitles.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabTitles.get(2)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabTitles.get(3)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabTitles.get(4)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabTitles.get(5)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabTitles.get(6)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabTitles.get(7)));

            mAdapter = new ViewPagerAdapter(getChildFragmentManager());
            if(mViewPager.getAdapter() == null)
                mViewPager.setAdapter(mAdapter);
            mTabLayout.setupWithViewPager(mViewPager);
        yMEvent(mTabTitles.get(0),0);

        mTabLayout.setTabsFromPagerAdapter(mAdapter);

        //设置Tab监听
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                yMEvent(mTabTitles.get(tab.getPosition()),tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.e("TAG", "Unselected Tab Index为：" + tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.e("TAG", "Reselected Tab Index为：" + tab.getPosition());
            }
        });
    }


    /**
     * youmengtongji
     * @param key
     * @param i
     */
    private void yMEvent(String key,int i){
        HashMap<String,String> map = new HashMap<>();
        map.put("key"+key,"1");
        MobclickAgent.onEvent(getActivity(), "Financetab"+(i+1), map);
    }


    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        FragmentManager fm;
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            this.fm = fm;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            fm.beginTransaction().show(fragment).commit();
            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Fragment fragment = mFragments.get(position);
            fm.beginTransaction().hide(fragment).commit();
        }



        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitles.get(position);
        }
        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
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
