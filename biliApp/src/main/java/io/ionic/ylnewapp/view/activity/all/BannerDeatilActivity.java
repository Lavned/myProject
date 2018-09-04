package io.ionic.ylnewapp.view.activity.all;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.umeng.analytics.MobclickAgent;

import org.xutils.view.annotation.ViewInject;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.view.activity.mine.HelpActivity;
import io.ionic.ylnewapp.view.base.BaseActivity;

import static io.ionic.ylnewapp.constants.Constants.WbUrl.webeHelp;

public class BannerDeatilActivity extends BaseActivity {

    @ViewInject(R.id.banner_webview)
    WebView myWebView;
    @ViewInject(R.id.finshs)
    TextView finshs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_deatil);
        init();
        Intent intent = getIntent();
        loadView(intent.getStringExtra("url"));
        String action = intent.getAction();
        if(Intent.ACTION_VIEW.equals(action)){
            Uri uri = intent.getData();
            if(uri != null){
                String id = uri.getQueryParameter("id");
                Toast.makeText(this,id,Toast.LENGTH_LONG).show();
            }
        }
    }

    private void init() {
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary), 225);
        mBuilder.setTitle("加载中...").show();
        finshs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        MobclickAgent.onEvent(mContext, "");
    }


    /**
     * a加载网页
     *
     * @param url
     */
    private void loadView(String url) {
        myWebView.loadUrl(url);
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
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                String scheme = Uri.parse(url).getScheme();//还需要判断host
                if (TextUtils.equals("myapp", scheme)) {
                    PreferenceUtils.setPrefString(mContext,"item","2");
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    finish();
                    return true;
                }
                return false;
            }
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