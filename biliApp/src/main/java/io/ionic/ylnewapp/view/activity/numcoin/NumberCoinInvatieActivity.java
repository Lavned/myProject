package io.ionic.ylnewapp.view.activity.numcoin;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class NumberCoinInvatieActivity extends BaseActivity {


    @ViewInject(R.id.shareView)
    TextView shareView;
    @ViewInject(R.id.all_webview)
    WebView wcWeb;


    @Event(type = View.OnClickListener.class,value = {R.id.shareView,R.id.finshs})
    private void ClickView(View view){
        switch (view.getId()){
            case R.id.shareView:
                shareMehtod();
                break;
            case R.id.finshs:
               finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        shareView.setVisibility(View.VISIBLE);
        if(PreferenceUtils.getPrefString(mContext,"token","").equals("")){
            ActivityUtils.toLogin(NumberCoinInvatieActivity.this,0);
        }else
            loadView(Constants.URL_BASE2 + "main/level?username=" + PreferenceUtils.getPrefString(mContext,"account","") );
    }



    /**
     * a加载网页
     *
     * @param url
     */
    private void loadView(String url) {
        //要加载的H5页面
        wcWeb.loadUrl(url);
        WebSettings settings = wcWeb.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        //将WebAppInterface于javascript绑定
        wcWeb.addJavascriptInterface(new WebAppInterface(this), "Android");
        //不读取缓存
//        myWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        wcWeb.setWebViewClient(new WebViewClient() {
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
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wcWeb.canGoBack()) {
            wcWeb.goBack();
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


    //分享的方法
    public void shareMehtod() {
        new ShareAction(NumberCoinInvatieActivity.this)
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
                    ClipboardManager cm = (ClipboardManager)mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                    // 将文本内容放到系统剪贴板里。
                    cm.setText("http://app.bit000.com/#/login?username=" + PreferenceUtils.getPrefString(mContext,"account",""));
                    T.showShort("链接已复制成功");
                }

            }
            else {//社交平台的分享行为
                //分享的图片
                UMImage thumb = new UMImage(mContext, R.mipmap.ic_launcher);
                //分享链接
                UMWeb web = new UMWeb("http://app.bit000.com/#/login?username=" +PreferenceUtils.getPrefString(mContext,"account",""));
                web.setTitle("欢迎注册币哩币哩");//标题
                web.setThumb(thumb);  //缩略图
                web.setDescription("欢迎注册币哩币哩");//描述
                new ShareAction(NumberCoinInvatieActivity.this)
                        .setPlatform(share_media)
                        .withText("多平台分享")
                        .withMedia(web)
                        .setCallback(new UMShareListener() {
                            @Override
                            public void onStart(SHARE_MEDIA share_media) {}
                            @Override
                            public void onResult(final SHARE_MEDIA share_media) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        T.showShort(share_media + " 分享成功");
                                        if (share_media.name().equals("WEIXIN_FAVORITE")) {} else {}
                                    }
                                });
                            }
                            @Override
                            public void onError(final SHARE_MEDIA share_media, final Throwable throwable) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        T.showShort(share_media + " 分享失败");
                                    }
                                });
                            }
                            @Override
                            public void onCancel(final SHARE_MEDIA share_media) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        T.showShort(share_media + " 分享取消");
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

}
