package io.ionic.ylnewapp.view.activity.product;

import android.app.Activity;
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

import com.gyf.barlibrary.ImmersionBar;
import com.jaeger.library.StatusBarUtil;
import com.umeng.analytics.MobclickAgent;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.view.activity.user.LoginActivity;
import io.ionic.ylnewapp.view.base.BaseActivity;

import static io.ionic.ylnewapp.constants.Constants.URL_BASE2;

public class ProductAIActivity extends BaseActivity {

    @ViewInject(R.id.webview)
    WebView myWebView;
    @ViewInject(R.id.backto)
    TextView backto;

    public  static Activity activity;

    @Event(type = View.OnClickListener.class,value = R.id.button)
    private void click(View view){
        if(PreferenceUtils.getPrefString(mContext,"loginIn","").equals("")){
            ActivityUtils.toLogin(ProductAIActivity.this,0);
        }else
            startActivity(new Intent(ProductAIActivity.this,OtherDetailActivity.class));

    }

    String type,pid ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_ai);
        activity = this;
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary),225);
        init();
        backto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void init() {
        pid = PreferenceUtils.getPrefString(mContext,"pid","");
        type = PreferenceUtils.getPrefString(mContext,"KEY","");
        switch (type){
            case "AI":
                loadView(URL_BASE2+"aiDetail?pid="+pid+"&type="+type);
                yMEvent(type,pid);
                break;
            case "ETF":
                loadView(URL_BASE2+"etfDetail?pid="+pid+"&type="+type);
                yMEvent(type,pid);
                break;
            case "OTC":
                loadView(URL_BASE2+"otcDetail?pid="+pid+"&type="+type);
                yMEvent(type,pid);
                break;
            case "BTC":
                loadView(URL_BASE2+"btcDetail?pid="+pid+"&type="+type);
                yMEvent(type,pid);
                break;
            case "ICO":
                loadView(URL_BASE2+"icoDetail?pid="+pid+"&type="+type);
                yMEvent(type,pid);
                break;
        }
    }


    /**
     * 有梦统计
     * @param key
     */
    public static void yMEvent(String key,String id){
        HashMap<String,String> map = new HashMap<>();
        map.put("key"+key,"1");
        map.put("id"+id,"1");
        MobclickAgent.onEvent(activity, "Detailpage", map);
    }

    /**
     * a加载网页
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

        myWebView.setWebViewClient(new WebViewClient(){
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

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        mContext = c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }
}


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy(); //释放沉浸式Title,必须调用该方法，防止内存泄漏
    }
}
