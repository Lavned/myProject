package io.ionic.ylnewapp.view.main;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.DBCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.socialize.PlatformConfig;

//import org.android.agoo.huawei.HuaWeiRegister;
//import org.android.agoo.mezu.MeizuRegister;
//import org.android.agoo.xiaomi.MiPushRegistar;
import org.android.agoo.huawei.HuaWeiRegister;
import org.android.agoo.mezu.MeizuRegister;
import org.android.agoo.xiaomi.MiPushRegistar;
import org.xutils.x;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import io.ionic.ylnewapp.utils.T;
import okhttp3.OkHttpClient;

import static com.umeng.socialize.utils.DeviceConfig.context;

//import static com.umeng.socialize.utils.DeviceConfig.context;


/**
 * Created by mango on 2017/10/28.
 */

public class BLApplication extends Application {

    public static Context applicationContext;
    private static BLApplication instance;

    private static final String TAG = "JYApplication";

    public static BLApplication getInstance() {
        return instance;
    }

    PushAgent mPushAgent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        instance = this;
        T.init(this);
        x.Ext.init(this);
//        OkGo.getInstance().init(this);//默认调用
        initOkGo();//自定义
//        x.Ext.setDebug(true);//是否输出Debug日志
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(getApplicationContext());
        /**
         * 初始化common库
         * 参数1:上下文，必须的参数，不能为空
         * 参数2:友盟 app key，非必须参数，如果Manifest文件中已配置app key，该参数可以传空，则使用Manifest中配置的app key，否则该参数必须传入
         * 参数3:友盟 channel，非必须参数，如果Manifest文件中已配置channel，该参数可以传空，则使用Manifest中配置的channel，否则该参数必须传入，channel命名请详见channel渠道命名规范
         * 参数4:设备类型，必须参数，传参数为UMConfigure.DEVICE_TYPE_PHONE则表示手机；传参数为UMConfigure.DEVICE_TYPE_BOX则表示盒子；默认为手机
         * 参数5:Push推送业务的secret，需要集成Push功能时必须传入Push的secret，否则传空
         */
        //如果AndroidManifest.xml清单配置中没有设置appkey和channel，则可以在这里设置
        //UMConfigure.init(this, "58edcfeb310c93091c000be2", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "1fe6a20054bcef865eeb0991ee84525b");
//        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE,"");
        UMConfigure.init(this,"5b47285ef43e4873bd000066","pugongying",UMConfigure.DEVICE_TYPE_PHONE,"e7a4adae5dc32e891afd5376f3791bfb");
        /**
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */
//        UMConfigure.setLogEnabled(true);
        PlatformConfig.setWeixin("wxe9f4faf295dfea1c", "cf5741e27c610aacb04d2a597112089e");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com");
        PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");

         mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                addAlias(deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });

        MiPushRegistar.register(applicationContext, "2882303761517711462", "5181771112462");  //小米id &&  小米key
        HuaWeiRegister.register(applicationContext);
        MeizuRegister.register( applicationContext, "1001489", "4412f08803f54c78befb76788fa8d4d2");

    }

    /**
     * 设置别名
     *
     * @param deviceToken
     */
    private void addAlias(String deviceToken) {
        mPushAgent.setAlias(deviceToken, "userID",
                new UTrack.ICallBack() {
                    @Override
                    public void onMessage(boolean isSuccess, String message) {
                        Log.i("------",message+"chenggongle");
                    }
                });
    }



    private void initOkGo() {
        //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
        HttpHeaders headers = new HttpHeaders();
        headers.put("commonHeaderKey1", "commonHeaderValue1");    //header不支持中文，不允许有特殊字符
        headers.put("commonHeaderKey2", "commonHeaderValue2");
        HttpParams params = new HttpParams();
        params.put("commonParamsKey1", "commonParamsValue1");     //param支持中文,直接传,不要自己编码
        params.put("commonParamsKey2", "这里支持中文参数");
        //----------------------------------------------------------------------------------------//

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //log相关
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO);                               //log颜色级别，决定了log在控制台显示的颜色
        builder.addInterceptor(loggingInterceptor);                                 //添加OkGo默认debug日志
        //第三方的开源库，使用通知显示当前请求的log，不过在做文件下载的时候，这个库好像有问题，对文件判断不准确
        //builder.addInterceptor(new ChuckInterceptor(this));

        //超时时间设置，默认60秒
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);      //全局的读取超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);     //全局的写入超时时间
        builder.connectTimeout(1000, TimeUnit.MILLISECONDS);   //全局的连接超时时间

        //自动管理cookie（或者叫session的保持），以下几种任选其一就行
        //builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));            //使用sp保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(new CookieJarImpl(new DBCookieStore(this)));              //使用数据库保持cookie，如果cookie不过期，则一直有效
        //builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));            //使用内存保持cookie，app退出后，cookie消失

        //https相关设置，以下几种方案根据需要自己设置
        //方法一：信任所有证书,不安全有风险
//        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
        //方法二：自定义信任规则，校验服务端证书
//        HttpsUtils.SSLParams sslParams2 = HttpsUtils.getSslSocketFactory(new SafeTrustManager());
        //方法三：使用预埋证书，校验服务端证书（自签名证书）
        //HttpsUtils.SSLParams sslParams3 = HttpsUtils.getSslSocketFactory(getAssets().open("srca.cer"));
        //方法四：使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
        //HttpsUtils.SSLParams sslParams4 = HttpsUtils.getSslSocketFactory(getAssets().open("xxx.bks"), "123456", getAssets().open("yyy.cer"));
//        builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
        //配置https的域名匹配规则，详细看demo的初始化介绍，不需要就不要加入，使用不当会导致https握手失败
//        builder.hostnameVerifier(new SafeHostnameVerifier());

        // 其他统一的配置
        // 详细说明看GitHub文档：https://github.com/jeasonlzy/
        OkGo.getInstance().init(this)                           //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置会使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3)                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                .addCommonHeaders(headers)                      //全局公共头
                .addCommonParams(params);                       //全局公共参数
    }



    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
            }
        }
        return processName;
    }


    private static Stack<Activity> mActivityStack = new Stack<Activity>();

    /**
     * 添加Activity至堆栈
     *
     * @param activity
     */
    public synchronized void addActivity(Activity activity)
    {
        mActivityStack.add(activity);
    }

    /**
     * 删除堆栈中Activity
     *
     * @param activity
     */
    public synchronized void removeActivty(Activity activity)
    {
        mActivityStack.remove(activity);
    }

    /**
     * 清空堆栈
     */
    public synchronized void cleanStack()
    {
        mActivityStack.clear();
    }

    /**
     * 清除堆栈，忽略ignoreList中对应的Activity
     *
     * @param ignoreList
     */
    public synchronized void cleanStack(List<Class<?>> ignoreList)
    {
        final int size = mActivityStack.size();
        for (int i = size - 1; i >= 0; i--)
        {
            Activity activity = mActivityStack.get(i);
            for (Class<?> classz : ignoreList)
            {
                if (!classz.isInstance(activity))
                {
                    Log.d(TAG, "Finish Activity = "
                            + activity.getClass().getName());
                    mActivityStack.remove(activity);
                    activity.finish();
                }
            }
        }
    }

    /**
     * 清除堆栈，忽略ignoreList中对应的Activity
     *
     * @param
     */
    public synchronized void cleanStack(Class<?> ignored)
    {
        final int size = mActivityStack.size();
        for (int i = size - 1; i >= 0; i--)
        {
            Activity activity = mActivityStack.get(i);
            if (!ignored.isInstance(activity))
            {
                Log.e(TAG, "Finish Activity = " + activity.getClass().getName());
                mActivityStack.remove(activity);
                activity.finish();
            }
        }
    }

    /**
     * 清除堆栈，关闭所有activity
     *
     * @param
     */
    public static void finishStack()
    {
        final int size = mActivityStack.size();
        for (int i = size - 1; i >= 0; i--)
        {
            Activity activity = mActivityStack.get(i);
            Log.e(TAG, "Finish Activity = " + activity.getClass().getName());
            mActivityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 获取指定的activity
     *
     * @param activityClass
     * @return
     */
    public Activity getActivity(Class<?> activityClass)
    {
        for (int i = 0; i < mActivityStack.size(); i++)
        {
            if (activityClass.isInstance(mActivityStack.get(i)))
            {
                return mActivityStack.get(i);
            }
        }
        return null;
    }
    /**
     * 获取当前activity
     *
     * @param
     * @return
     */
    public Activity getCurrentActivity()
    {
        return mActivityStack.lastElement();
    }

    /**
     * 获取上级activity
     * @return
     */
    public Activity getSuperActivity()
    {
        if(mActivityStack.size() >= 2)
        {
            return mActivityStack.get(mActivityStack.size() - 2);
        }
        return null;
    }



}

