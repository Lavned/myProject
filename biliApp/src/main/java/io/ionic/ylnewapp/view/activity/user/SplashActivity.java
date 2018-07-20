package io.ionic.ylnewapp.view.activity.user;

import android.content.Intent;
import android.os.Bundle;

import com.gyf.barlibrary.ImmersionBar;

import java.util.Timer;
import java.util.TimerTask;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.view.main.MainActivity;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    /**
     * 欢迎
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImmersionBar.with(this).transparentNavigationBar().init();
        if(PreferenceUtils.getPrefString(mContext,"firstStatus","").equals("isShow")){
//            if(PreferenceUtils.getPrefString(mContext,"login","").equals("login")){
                intentClass(1); //主页面
//            }else {
//                intentClass(3);//登录页
//            }
        } else{
            intentClass(2);//引导页
        }

    }

    /**
     * 选择跳转
     * @param type
     */
    private void intentClass(final int type) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(type ==1){
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else if(type == 2){
                    Intent intent = new Intent(SplashActivity.this, GuideActivity.class);//login
                    startActivity(intent);
                    finish();
                }
//                else {
//                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);//login
//                    startActivity(intent);
//                    finish();
//                }

            }
        }, 1500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy(); //释放沉浸式Title,必须调用该方法，防止内存泄漏
    }
}
