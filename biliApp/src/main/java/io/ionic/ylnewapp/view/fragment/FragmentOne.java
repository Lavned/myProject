package io.ionic.ylnewapp.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.google.gson.Gson;
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
import io.ionic.ylnewapp.bean.NotifiBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.custom.MyViewPager;
import io.ionic.ylnewapp.custom.ViewpagerTransformAnim;
import io.ionic.ylnewapp.custom.customViewpagerView;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.activity.all.BitAssesActivity;
import io.ionic.ylnewapp.view.activity.mine.CountMoneyActivity;
import io.ionic.ylnewapp.view.activity.mine.CouponsActivity;
import io.ionic.ylnewapp.view.activity.mine.FriendActivity;
import io.ionic.ylnewapp.view.activity.mine.MyActiActivity;
import io.ionic.ylnewapp.view.activity.mine.NotificationActivity;
import io.ionic.ylnewapp.view.activity.user.LoginActivity;
import io.ionic.ylnewapp.view.base.BaseFragment;
import io.ionic.ylnewapp.view.main.MainActivity;


/**
 * A simple {@link Fragment} subclass.
 */

@ContentView(R.layout.fragment_one)//加载布局
public class FragmentOne extends BaseFragment {


    private int index = 0;//textview上下滚动下标
    private Handler handler = new Handler();
    private boolean isFlipping = false; // 是否启用预警信息轮播
    private List<String> mWarningTextList = new ArrayList<>();
    List<NotifiBean.BodyBean> mData ;
    @ViewInject(R.id.text_switch)
    TextSwitcher mTextSwitcher;

    public List<View> views;
    private ViewPagerAdapter vpAdapter;

    @ViewInject(R.id.one_viewpager)
    customViewpagerView one_viewpager;

    @ViewInject(R.id.hm_one)
    TextView hm_friend;
    @ViewInject(R.id.hm_two)
    TextView hm_money;
    @ViewInject(R.id.hm_three)
    TextView hm_reward;
    @ViewInject(R.id.hm_four)
    TextView hm_see;

    @Event(type = View.OnClickListener.class ,value = {R.id.hm_one,R.id.hm_two,R.id.hm_three,R.id.hm_four,R.id.text_switch})
    private void click(View view){
        switch (view.getId()){
            case R.id.hm_one :
                startActivity(new Intent(getActivity(),BitAssesActivity.class));
                break;
            case R.id.hm_two :
                startActivity(new Intent(getActivity(),CouponsActivity.class));
                break;
            case R.id.hm_three :
                startActivity(new Intent(getActivity(),MyActiActivity.class));
                break;
            case R.id.hm_four :
                startActivity(new Intent(getActivity(),FriendActivity.class));
                break;
            case R.id.text_switch :
                startActivity(new Intent(getActivity(),LoginActivity.class));
                break;
        }
    }


    /**
     * 跳转到Fragment 与MAinActivity中的方法对接
     * @param i
     */
    private void toFragment(final int i) {
        final MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setFragment2Fragment(new MainActivity.Fragment2Fragment() {
            @Override
            public void gotoFragment(MyViewPager viewPager) {
                viewPager.setCurrentItem(i);
            }
        });
        mainActivity.forSkip();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = x.view().inject(this, inflater, container);
        initViewPager();
        getNotifiData();
        return view;//fragment注解;
    }



    private void initText(List<NotifiBean.BodyBean> datas) {
        setTextSwitcher();
        mWarningTextList.add(mData.get(0).getTitle());
        if(mData.size()>2){
            mWarningTextList.add(mData.get(1).getTitle());
        }
        setData();
    }


    /**
     * 获取通知列表
     */
    private void getNotifiData() {
        OkGo.<String>get(Constants.URL_BASE + "home/notices")//
                .tag(this)//
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//
                        Gson gson = new Gson();
                        NotifiBean javaBean =gson.fromJson(data.toString(),NotifiBean.class);
                        mData = javaBean.getBody();
                        if(mData!=null)
                            initText(mData);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showNetworkError(getActivity());
                    }

                });
    }


    @Override
    public void onResume() {
        super.onResume();
        startFlipping();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        stopFlipping();
    }


    int currentPosition = 0;
    //初始化中间轮播控件
    private void initViewPager() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        views = new ArrayList<View>();
        //获取View对象
        View view1 = inflater.inflate(R.layout.main_view_items, null);
        View view2 = inflater.inflate(R.layout.main_view_items, null);
        View view3 = inflater.inflate(R.layout.main_view_items, null);
        //view对象放在List<view>中
        views.add(view1);
        views.add(view2);
        views.add(view3);
        one_viewpager.setOffscreenPageLimit(3);
        one_viewpager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.page_margin));
        //List<view>放在适配器ViewPagerAdapter中
        vpAdapter = new ViewPagerAdapter(views, getActivity());
        //获取ViewPage,设置适配器;
        one_viewpager.setAdapter(vpAdapter);
        one_viewpager.setPageTransformer(true, new ViewpagerTransformAnim());

        one_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
                Log.i("--------9999",position+"---");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
               // 若viewpager滑动未停止，直接返回
                if (state != ViewPager.SCROLL_STATE_IDLE) return;
//        若当前为第一张，设置页面为倒数第二张
                if (currentPosition == 0) {
                    one_viewpager.setCurrentItem(views.size()-2,false);
                } else if (currentPosition == views.size()-1) {
//        若当前为倒数第一张，设置页面为第二张
                    one_viewpager.setCurrentItem(0,false);
                }
            }
        });
    }


    class ViewPagerAdapter extends PagerAdapter {
        private Context context;
        private List<View> viewlist;
        public ViewPagerAdapter( List<View> views,Context context) {
            this.context = context;
            this.viewlist = views;
        }
        /**
         * 页面宽度所占ViewPager测量宽度的权重比例，默认为1
         */
//        @Override
//        public float getPageWidth(int position) {
//            return (float) 0.9;
//        }
        //重新4个方法
        @Override
        public int getCount() {
            return views.size();
        }
        //instantiateItem()：将当前view添加到ViewGroup中，并返回当前View
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }
        //destroyItem()：删除当前的View;
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }
        //isViewFromObject判断当前的View 和 我们想要的Object(值为View) 是否一样;返回 trun / false;
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return (arg0 == arg1);
        }

    }


    private void setTextSwitcher() {
        mTextSwitcher.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_bottom));
        mTextSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_top));
        mTextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(getActivity());
                textView.setSingleLine();
                textView.setTextSize(12);//字号
                textView.setTextColor(getActivity().getColor(R.color.black_1));
                textView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
                textView.setSingleLine();
                textView.setText("最新通知！");
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                params.gravity = Gravity.CENTER_VERTICAL;
                textView.setLayoutParams(params);
                textView.setPadding(25, 0, 25, 0);
                return textView;
            }
        });
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (!isFlipping) {
                return;
            }
            index++;
            mTextSwitcher.setText(mWarningTextList.get(index % mWarningTextList.size()));
            if (index == mWarningTextList.size()) {
                index = 0;
            }
            startFlipping();
        }
    };

    //开启信息轮播
    public void startFlipping() {
        if (mWarningTextList.size() > 0) {
            handler.removeCallbacks(runnable);
            isFlipping = true;
            handler.postDelayed(runnable, 3000);
        }
    }

    //关闭信息轮播
    public void stopFlipping() {
        if (mWarningTextList.size() > 0) {
            isFlipping = false;
            handler.removeCallbacks(runnable);
        }
    }

    //设置数据
    private void setData() {
        if (mWarningTextList.size() == 0) {
            mTextSwitcher.setText(mWarningTextList.get(0));
            index = 0;
        }
        if (mWarningTextList.size() > 0) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    for (int i =0; i < mWarningTextList.size();i++){
                        mTextSwitcher.setText(mWarningTextList.get(i));
                        index =i;
                    }

                }
            }, 1000);
            mTextSwitcher.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_bottom));
            mTextSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_top));
            startFlipping();
        }
    }



}
