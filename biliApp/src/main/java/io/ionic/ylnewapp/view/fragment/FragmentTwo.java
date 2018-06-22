package io.ionic.ylnewapp.view.fragment;


import android.graphics.Color;
import android.os.Bundle;
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

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import java.util.ArrayList;
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



    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter mAdapter;
    private List<Fragment> mFragments;

    private List<String > mTabTitles;
    FragmentTwo fragment;

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


//        mTabLayout.setTabsFromPagerAdapter(mAdapter);
//
//        //设置Tab监听
//        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                mViewPager.setCurrentItem(tab.getPosition());
//                Log.e("TAG", "Selected Tab Index为：" + tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                Log.e("TAG", "Unselected Tab Index为：" + tab.getPosition());
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//                Log.e("TAG", "Reselected Tab Index为：" + tab.getPosition());
//            }
//        });


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

}
