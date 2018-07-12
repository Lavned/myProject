package io.ionic.ylnewapp.view.activity.user;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;

import org.xutils.view.annotation.ViewInject;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.view.base.BaseActivity;
import io.ionic.ylnewapp.view.main.MainActivity;

public class GuideActivity extends BaseActivity {

    private final int[] mData = {R.mipmap.img0, R.mipmap.img1,R.mipmap.img2};


    @ViewInject(R.id.GuideviewPager)
    ViewPager GuideviewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ImmersionBar.with(this).init();
        PreferenceUtils.setPrefString(GuideActivity.this,"firstStatus","isShow");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        initViewPager();
    }



    //初始化中间轮播控件
    private void initViewPager() {
        ViewPagerAdapter vpAdapter = new ViewPagerAdapter();
        //获取ViewPage,设置适配器;
        GuideviewPager.setAdapter(vpAdapter);
//        GuideviewPager.setPageTransformer(true, new ViewpagerTransformAnim());
    }


    /**
     * 适配器
     */
    class ViewPagerAdapter extends PagerAdapter {
        public ViewPagerAdapter() {}

        @Override
        public int getCount() {
            return mData.length;
        }
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.guide_items , null);
            ImageView textView = v.findViewById(R.id.items_bg);
            TextView button = v.findViewById(R.id.button);
            textView.setImageResource(mData[position]);
            if(position == mData.length-1){
                button.setVisibility(View.VISIBLE);
            }
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(mContext, MainActivity.class));
                    finish();
                }
            });
            container.addView(v);
            return v;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return (arg0 == arg1);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy(); //释放沉浸式Title,必须调用该方法，防止内存泄漏
    }
}
