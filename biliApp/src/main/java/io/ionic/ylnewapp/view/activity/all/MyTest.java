package io.ionic.ylnewapp.view.activity.all;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
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

import org.xutils.view.annotation.ViewInject;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class MyTest extends BaseActivity {



    @ViewInject(R.id.wcWeb)
    WebView wcWeb;

    @ViewInject(R.id.textView3)
    ImageView textView;
    @ViewInject(R.id.tv_back)
    ImageView back;
    @ViewInject(R.id.tv_title)
    TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_test);
        loadView("http://cplus.club/#/main/home");
        title.setText("望潮计划");
//        textView.setVisibility(View.VISIBLE);
//        textView.setImageResource(R.mipmap.shareicon);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                shareWeb();
//                shareMehtod();
//            }
//        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }




    //分享的方法
    public void shareMehtod() {

        new ShareAction(MyTest.this)
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
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
                    cm.setText("http://192.168.1.143/#/main/share");
                    T.showShort("链接已复制成功");
                }

            }
            else {//社交平台的分享行为
                //分享的图片
                UMImage thumb = new UMImage(MyTest.this, R.mipmap.app_logo);
                //分享链接
                UMWeb web = new UMWeb("http://cplus.club/#/main/home");
                web.setTitle("");//标题
                web.setThumb(thumb);  //缩略图
                web.setDescription("");//描述
                new ShareAction(MyTest.this)
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


    /**
     * 分享
     */
    public void shareWeb() {
        UMWeb web = new UMWeb("http://cplus.club/#/main/home");//连接地址
        web.setTitle("望潮计划");//标题
        web.setDescription("望潮计划来啦");//描述
        if (TextUtils.isEmpty("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1533290641757&di=cc47b5b772557138cfac7b9997b46f89&imgtype=0&src=http%3A%2F%2Fn5.cmsfile.pg0.cn%2Fgroup1%2FM00%2FBA%2FD0%2FCgqg11gzmM-AaPOsAAPULs20lSI886.png")) {
            web.setThumb(new UMImage(mContext, R.mipmap.ic_launcher));  //本地缩略图
        } else {
            web.setThumb(new UMImage(mContext,  R.mipmap.ic_launcher));  //网络缩略图
        }
        new ShareAction(MyTest.this)
                .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
                .withMedia(web)
                .addButton("umeng_sharebutton_custom","umeng_sharebutton_custom","app_logo","app_logo")
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
                .open();
        //open()是打开面板,和.setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)一起使用
        //.share();如果是调用share方法的时候不会使用面板,,,setPlatform()一起使用

        //新浪微博中图文+链接
        /*new ShareAction(activity)
                .setPlatform(platform)
                .withText(description + " " + WebUrl)
                .withMedia(new UMImage(activity,imageID))
                .share();*/
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
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

}
