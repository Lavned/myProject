package io.ionic.ylnewapp.utils;

import android.content.Context;

import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;

/**
 * Created by mogojing on 2018/7/13/0013.
 */

public class YMEventUtil {

    public static void yMEvent(String key, int i, Context context){
        HashMap<String,String> map = new HashMap<>();
        map.put("key"+key,"1");
        MobclickAgent.onEvent(context, "Financetab"+i+1, map);
    }

}
