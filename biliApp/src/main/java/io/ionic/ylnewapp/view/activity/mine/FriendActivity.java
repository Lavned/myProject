package io.ionic.ylnewapp.view.activity.mine;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import org.xutils.view.annotation.ViewInject;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class FriendActivity extends BaseActivity {

    @ViewInject(R.id.all_webview)
    WebView myWebView;
    @ViewInject(R.id.finshs)
    TextView finshs;
    @ViewInject(R.id.share)
    TextView share;

    String url = Constants.URL_BASE2 + "main/share";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary), 225);
        init();
        share.setVisibility(View.VISIBLE);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareMehtod();
            }
        });
        loadView(url + "?username=" + PreferenceUtils.getPrefString(mContext, "account", ""));

    }





    //分享的方法
    public void shareMehtod() {
        new ShareAction(FriendActivity.this)
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                //分享平台
                .addButton("复制链接","umeng_sharebutton_custom","umeng_socialize_copyurl","复制链接")
                // 分享面板添加自定义按钮
                .setShareboardclickCallback(shareBoardlistener)
                //面板点击监听器
                .open();
    }



    //分享的监听
    private ShareBoardlistener shareBoardlistener = new  ShareBoardlistener() {

        @Override
        public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
            if (share_media==null){
                //根据key来区分自定义按钮的类型，并进行对应的操作
                if (snsPlatform.mKeyword.equals("umeng_sharebutton_custom")){
                    //点击后复制微信号的逻辑
                    ClipboardManager cm = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                    // 将文本内容放到系统剪贴板里。
                    cm.setText("http://app.bit000.com/#/login?username=" +PreferenceUtils.getPrefString(mContext,"account",""));
                    T.showShort("链接已复制成功");
                }

            }
            else {//社交平台的分享行为
                //分享的图片
                UMImage thumb = new UMImage(FriendActivity.this, R.mipmap.ic_launcher);
                //分享链接
                UMWeb web = new UMWeb("http://app.bit000.com/#/login?username=" +PreferenceUtils.getPrefString(mContext,"account",""));
                web.setTitle("欢迎注册币哩币哩");//标题
                web.setThumb(thumb);  //缩略图
                web.setDescription("欢迎注册币哩币哩");//描述
                new ShareAction(FriendActivity.this)
                        .setPlatform(share_media)
                        .withText("多平台分享")
                        .withMedia(web)
                        .setCallback(new UMShareListener() {
                            @Override
                            public void onStart(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onResult(final SHARE_MEDIA share_media) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(mContext, share_media + " 分享成功", Toast.LENGTH_SHORT).show();
                                        if (share_media.name().equals("WEIXIN_FAVORITE")) {
                                        } else {
                                        }
                                    }
                                });
                            }

                            @Override
                            public void onError(final SHARE_MEDIA share_media, final Throwable throwable) {
                                if (throwable != null) {
                                    Log.d("throw", "throw:" + throwable.getMessage());
                                }
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(mContext, share_media + " 分享失败", Toast.LENGTH_SHORT).show();

                                    }
                                });
                            }

                            @Override
                            public void onCancel(final SHARE_MEDIA share_media) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(mContext, share_media + " 分享取消", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .share();
            }
        }
    };




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }






    //退出事件
    private void init() {
        mBuilder.setTitle("加载中...").show();
        finshs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyActiActivity.activity.finish();
                finish();
            }
        });
    }


    /**
     * a加载网页
     *
     * @param url
     */
    private void loadView(String url) {
        //要加载的H5页面
        myWebView.loadUrl(url);
        WebSettings settings = myWebView.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        //将WebAppInterface于javascript绑定
        myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");
        //不读取缓存
//        myWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mBuilder.dismiss();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mBuilder.setTitle("加载中").show();
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    public class WebAppInterface {
        Context mContext;

        /**
         * Instantiate the interface and set the context
         */
        WebAppInterface(Context c) {
            mContext = c;
        }

        /**
         * Show a toast from the web page
         */
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
    }
}