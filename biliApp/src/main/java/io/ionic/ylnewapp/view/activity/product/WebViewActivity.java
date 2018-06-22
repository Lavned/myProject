package io.ionic.ylnewapp.view.activity.product;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jaeger.library.StatusBarUtil;

import org.xutils.view.annotation.ViewInject;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class WebViewActivity extends BaseActivity {

    @ViewInject(R.id.all_webview)
    WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary),225);
        Intent intent = getIntent();
//        chooseWeb(intent.getStringExtra("url"));
        Log.i("-----------",intent.getStringExtra("url")+"oo");
        loadView(intent.getStringExtra("url"));
    }

//    private void chooseWeb(String url) {
//        switch (url){
//            case "":
//                loadView("");
//                break;
//            case "":
//                loadView("");
//                break;
//            case "":
//                loadView("");
//                break;
//            case "":
//                loadView("");
//                break;
//            case "":
//                loadView("");
//                break;
//            case "":
//                loadView("");
//                break;
//        }
//    }


    private void loadView(String url) {
        myWebView.loadUrl(url);//加载本地中的html
        //myWebView.loadUrl("file:///android_asset/www/test2.html");
        //加上下面这段代码可以使网页中的链接不以浏览器的方式打开
        myWebView.setWebViewClient(new WebViewClient());
        //得到webview设置
        WebSettings webSettings = myWebView.getSettings();
        //允许使用javascript
        webSettings.setJavaScriptEnabled(true);
        //将WebAppInterface于javascript绑定
//        myWebView.addJavascriptInterface(new ProductAIActivity.WebAppInterface(this), "Android");
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
