package io.ionic.ylnewapp.view.activity.mine;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.net.URL;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class NotifiCaDetailActivity extends BaseActivity {

    @ViewInject(R.id.tv_title)
    TextView noTi;
    @ViewInject(R.id.no_content)
    TextView noCo;

    @ViewInject(R.id.webView)
    WebView webView;

    @Event(type = View.OnClickListener.class,value = R.id.tv_back)
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back :
                finish();
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifi_ca_detail);
        StatusBarUtil.setColor(this, getColor(R.color.colorPrimary),225);
        noTi.setText("通知");
        Intent intent = getIntent();




        webView.loadData(intent.getStringExtra("content"), "text/html; charset=UTF-8", null);



//        Html.ImageGetter imgGetter = new Html.ImageGetter() {
//            public Drawable getDrawable(String source) {
//                Drawable drawable = null;
//                URL url;
//                try {
//                    url = new URL(source);
//                    drawable = Drawable.createFromStream(url.openStream(), "");  //获取网路图片
//                } catch (Exception e) {
//                    return null;
//                }
//                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//                return drawable;
//            }
//        };
//        final Spanned html = Html.fromHtml(intent.getStringExtra("content"),imgGetter, null);
//
//
//        runOnUiThread(new Runnable() {
//                         public void run() {
//                             noCo.setText(html);
//                         }
//
//                     });
//
//        noCo.setText(Html.fromHtml(intent.getStringExtra("content")));

    }


}
