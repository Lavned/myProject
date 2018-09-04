package io.ionic.ylnewapp.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.google.gson.Gson;
import com.jiangyy.easydialog.OtherDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.umeng.analytics.MobclickAgent;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import io.ionic.ylnewapp.BR;
import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.adpater.GlideImageLoader;
import io.ionic.ylnewapp.adpater.HotAdapter;
import io.ionic.ylnewapp.bean.BannerBean;
import io.ionic.ylnewapp.bean.HomeBean;
import io.ionic.ylnewapp.bean.NotifiBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.custom.MyListView;
import io.ionic.ylnewapp.custom.MyViewPager;
import io.ionic.ylnewapp.custom.NewListView;
import io.ionic.ylnewapp.custom.ViewpagerTransformAnim;
import io.ionic.ylnewapp.custom.customViewpagerView;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.activity.all.BannerDeatilActivity;
import io.ionic.ylnewapp.view.activity.all.BitAssesActivity;
import io.ionic.ylnewapp.view.activity.all.MyTest;
import io.ionic.ylnewapp.view.activity.kline.Enable_Refresh_Activity;
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

    List<HomeBean.BodyBean.HotBean> HOTData ;
    List<HomeBean.BodyBean.PushBean> PUSHData ;

    @ViewInject(R.id.text_switch)
    TextSwitcher mTextSwitcher;
    @ViewInject(R.id.onescrollView)
    ScrollView scrollView;

//    public List<View> views;
    private ViewPagerAdapter vpAdapter;

    @ViewInject(R.id.one_viewpager)
    customViewpagerView one_viewpager;

    @ViewInject(R.id.position_num)
    TextView position_num;
    @ViewInject(R.id.position_count)
    TextView position_count;
    @ViewInject(R.id.one_lv)
    NewListView oneLv;
    HotAdapter adapter;

    @ViewInject(R.id.banner)
    Banner banner;
    List<BannerBean.MsgBean> bannerData;

    @Event(type = View.OnClickListener.class ,value = {R.id.hm_one,R.id.hm_two,R.id.hm_three,R.id.hm_four,R.id.text_switch})
    private void click(View view){
        switch (view.getId()){
            case R.id.hm_one :
                MobclickAgent.onEvent(getActivity(), "Homepagetab1");
                startActivity(new Intent(getActivity(),BitAssesActivity.class));
                break;
            case R.id.hm_two :
                MobclickAgent.onEvent(getActivity(), "Homepagetab2");
//                startActivity(new Intent(getActivity(),CouponsActivity.class));
                startActivity(new Intent(getActivity(),MyTest.class));
                break;
            case R.id.hm_three :
                MobclickAgent.onEvent(getActivity(), "Homepagetab3");
                startActivity(new Intent(getActivity(),MyActiActivity.class));
                break;
            case R.id.hm_four :
                MobclickAgent.onEvent(getActivity(), "Homepagetab4");
                startActivity(new Intent(getActivity(),FriendActivity.class));
                break;
            case R.id.text_switch :
                MobclickAgent.onEvent(getActivity(), "Message");
                startActivity(new Intent(getActivity(),NotificationActivity.class));
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
                viewPager.setCurrentItem(i,false);
            }
        });
        mainActivity.forSkip();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = x.view().inject(this, inflater, container);
        scrollView.smoothScrollTo(0,0);
        getTopBanner();
        getHomeData();
        getNotifiData();
        return view;//fragment注解;
    }

    /**
     * 获取banner数据
     */
    private void getTopBanner() {
        OkGo.<String>get(Constants.URL_BASE + "home/banner?type=mbili")//
                .tag(this)//
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        BannerBean date = gson.fromJson(response.body(),BannerBean.class);
                        bannerData = date.getMsg();
                        if(bannerData!=null && bannerData.size() > 0){
                            initBanner(bannerData);
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
     * 初始化banner
     */
    private void initBanner(final List<BannerBean.MsgBean> imageList) {
        List<String> imgList = new ArrayList<>();
        for (int i =0 ;i < bannerData.size();i++){
            imgList.add(bannerData.get(i).getImgUrl());
        }
        List<String> images = imgList;
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
//        banner.setBannerAnimation(Transformer.ZoomOutSlide);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                int num = 0;
                if(!bannerData.get(position).getSubTitle().contains("http://")){
                    switch (bannerData.get(position).getSubTitle()){
                        case "BET":
                            num = 0;
                            break;
                        case "DIG":
                            num = 1;
                            break;
                        case "AI":
                            num = 2;
                            break;
                        case "ETF":
                            num = 3;
                            break;
                        case "OTC":
                            num = 4;
                            break;
                        case "BTC":
                            num = 5;
                            break;
                        case "ICO":
                            num = 6;
                            break;
                        case "TETF":
                            num = 7;
                            break;
                        case "TBTC":
                            num = 8;
                            break;
                    }
                    toFragment(1);
                    Message message = new Message();
                    message.what= num;
                    FragmentTwo.MyHandler myHandler = new FragmentTwo.MyHandler(getActivity());
                    myHandler.sendMessage(message);
                }else {
                    Intent intent = new Intent(getActivity(), BannerDeatilActivity.class);
                    intent.putExtra("url", bannerData.get(position).getSubTitle());
                    startActivity(intent);
                }
            }
        });
    }


    /**
     * 跑马灯
     * @param datas
     */
    private void initText(List<NotifiBean.BodyBean> datas) {
        setTextSwitcher();
        mWarningTextList.add(mData.get(0).getTitle());
        if(mData.size()>2){
            mWarningTextList.add(mData.get(1).getTitle());
        }
        setData();
    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }
    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
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
                        if(mData!=null){
                            initText(mData);
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
     * 获取通知列表
     */
    private void getHomeData() {
        OkGo.<String>get(Constants.URL_BASE + "home/home")//
                .tag(this)//
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//
                        Gson gson = new Gson();
                        HomeBean javaBean =gson.fromJson(data.toString(),HomeBean.class);
                        HOTData = javaBean.getBody().getHot();
                        PUSHData = javaBean.getBody().getPush();
                        initViewPager(PUSHData);
                        initHot(HOTData);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showNetworkError(getActivity());
                    }

                });
    }

    private void  toTwo(List<HomeBean.BodyBean.PushBean>PUSHData,int position){
        MobclickAgent.onEvent(getActivity(), "Recommend");
        int num = 0;
        switch (PUSHData.get(position).getKey()){
            case "DIG":
                num = 0;
                break;
            case "AI":
                num = 1;
                break;
            case "ETF":
                num = 2;
                break;
            case "OTC":
                num = 3;
                break;
            case "BTC":
                num = 4;
                break;
            case "ICO":
                num = 5;
                break;
            case "TETF":
                num = 6;
                break;
            case "TBTC":
                num = 7;
                break;
        }
        toFragment(1);
        Message message = new Message();
        message.what= num;
        FragmentTwo.MyHandler myHandler = new FragmentTwo.MyHandler(getActivity());
        myHandler.sendMessage(message);
    }

    /**
     * 底部list
     * @param hotData
     */
    private void initHot(final List<HomeBean.BodyBean.HotBean> hotData) {
        adapter = new HotAdapter(getActivity(),hotData);
        oneLv.setAdapter(adapter);
        oneLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MobclickAgent.onEvent(getActivity(), "Hot");
                int num = 0;
                switch (hotData.get(position).getKey()){
                    case "DIG":
                        num = 0;
                        break;
                    case "AI":
                        num = 1;
                        break;
                    case "ETF":
                        num = 2;
                        break;
                    case "OTC":
                        num = 3;
                        break;
                    case "BTC":
                        num = 4;
                        break;
                    case "ICO":
                        num = 5;
                        break;
                    case "TETF":
                        num = 6;
                        break;
                    case "TBTC":
                        num = 7;
                        break;
                }
                toFragment(1);
                Message message = new Message();
                message.what= num;
                FragmentTwo.MyHandler myHandler = new FragmentTwo.MyHandler(getActivity());
                myHandler.sendMessage(message);
            }
        });
    }



    //初始化中间轮播控件
    private void initViewPager(final List<HomeBean.BodyBean.PushBean> pushBeans) {
        one_viewpager.setOffscreenPageLimit(3);
        one_viewpager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.page_margin));
        //List<view>放在适配器ViewPagerAdapter中
        vpAdapter = new ViewPagerAdapter( getActivity(),pushBeans);
        //获取ViewPage,设置适配器;
        one_viewpager.setAdapter(vpAdapter);
        one_viewpager.setPageTransformer(true, new ViewpagerTransformAnim());
    }


    /**
     * 适配器
     */
    class ViewPagerAdapter extends PagerAdapter {
        private Context context;
        private List<View> viewlist;
        List<HomeBean.BodyBean.PushBean> pushBeans;
        public ViewPagerAdapter( Context context, List<HomeBean.BodyBean.PushBean> pushBeans) {
            this.context = context;
            this.pushBeans = pushBeans;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View v = LayoutInflater.from(context).inflate(R.layout.main_view_items , null);
            TextView textView = v.findViewById(R.id.view_name);
            TextView view_title = v.findViewById(R.id.view_title);
            TextView text1 = v.findViewById(R.id.view_texte1);
            TextView text2 = v.findViewById(R.id.view_texte2);
            TextView text3 = v.findViewById(R.id.view_texte3);
            TextView text4 = v.findViewById(R.id.view_texte4);
            LinearLayout view_item = v.findViewById(R.id.view_item);

            final int i  = position % pushBeans.size();
            view_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toTwo(pushBeans,i);
                }
            });
            textView.setText(pushBeans.get(i).getName());
            view_title.setText(pushBeans.get(i).getTitle());
            text1.setText(pushBeans.get(i).getRate()+"");
            text2.setText(pushBeans.get(i).getWeek()+"");
            text3.setText(pushBeans.get(i).getTitleRate());
            text4.setText(pushBeans.get(i).getTitleWeek());

            position_count.setText(" / "+pushBeans.size()+"");
            position_num.setText( i+1+"");


            container.addView(v);
            return v;
        }
        //destroyItem()：删除当前的View;
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return (arg0 == arg1);
        }

    }


    /**
     * 设置跑马灯
     */
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
                        if(mTextSwitcher!=null && mWarningTextList!=null) {
                            mTextSwitcher.setText(mWarningTextList.get(i));
                            index = i;
                        }
                    }

                }
            }, 1000);
            mTextSwitcher.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_bottom));
            mTextSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_top));
            startFlipping();
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
        startFlipping();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        stopFlipping();
    }

}
