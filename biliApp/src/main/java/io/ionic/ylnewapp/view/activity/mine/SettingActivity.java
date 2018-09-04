package io.ionic.ylnewapp.view.activity.mine;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.jiangyy.easydialog.CommonDialog;
import com.umeng.analytics.MobclickAgent;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.activity.user.LoginActivity;
import io.ionic.ylnewapp.view.base.BaseActivity;
import io.ionic.ylnewapp.view.main.BLApplication;

public class SettingActivity extends BaseActivity {

    @ViewInject(R.id.tv_title)
    TextView title;

    @ViewInject(R.id.version)
    TextView verison;


    @Event(type = View.OnClickListener.class,value = {R.id.tv_back,R.id.exit_login,R.id.re_version,R.id.re_about,R.id.re_pass})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.exit_login:
                new CommonDialog.Builder(SettingActivity.this)
                        .setTitle("温馨提示",R.color.main)
                        .setMessage("确定要退出登录吗？",R.color.main)
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                PreferenceUtils.setPrefString(mContext,"token","");
                                PreferenceUtils.setPrefString(mContext,"loginIn","");
                                startActivity(new Intent(mContext, LoginActivity.class));
                                finish();
                                BLApplication.finishStack();
                            }
                        },R.color.main).setNegativeButton("取消", null).show();
                break;
            case R.id.re_version:
                T.showShort("当前版本号为" + getVersion());
                break;
            case R.id.re_about:
                startActivity(new Intent(mContext,AboutActivity.class));
                break;
            case R.id.re_pass:
                startActivity(new Intent(mContext,PassManaActivity.class));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        init();
        MobclickAgent.onEvent(mContext, "Setting");
        verison.setText("V" + getVersion());
    }

    /**
     * 初始化
     */
    private void init() {
        title.setText("设置");
        StatusBarUtil.setColor(this, mContext.getColor(R.color.colorPrimary),225);
    }

    /**
     * 获取版本号
     * @return 当前应用的版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            return  version ;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
