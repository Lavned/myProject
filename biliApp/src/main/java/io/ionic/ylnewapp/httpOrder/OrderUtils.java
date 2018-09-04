package io.ionic.ylnewapp.httpOrder;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import io.ionic.ylnewapp.bean.CommRespBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.T;

/**
 * Created by mogojing on 2018/6/19/0019.
 */

public class OrderUtils {



    //取消挂单
    public  static  void cancelPut(final Context activity , String orderid){
        OkGo.<String>post(Constants.URL_BASE + "order/cancelPut")//
                .tag(activity)//
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(activity,"token",""))
                .params("orderid", orderid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            T.showShort(jsonObject.getString("msg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showNetworkError(activity);
                    }
                });
    }

    //取消
    public  static  void delOrder(final Context activity , String orderid){
        OkGo.<String>delete(Constants.URL_BASE + "order/order?orderid=" + orderid)//
                .tag(activity)//
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(activity,"token",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            T.showShort(jsonObject.getString("msg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showNetworkError(activity);
                    }
                });
    }


    //续投
    public  static  void goOrder(final Context activity , String orderid){
        OkGo.<String>post(Constants.URL_BASE + "order/goOrder")//
                .tag(activity)//
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(activity,"token",""))
                .params("orderid", orderid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            T.showShort(jsonObject.getString("msg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showNetworkError(activity);
                    }
                });
    }

    //挂单
    public  static  void putOrder(final Context activity , String orderid,String putAmount){
        OkGo.<String>post(Constants.URL_BASE + "order/putOrder")//
                .tag(activity)//
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(activity,"token",""))
                .params("orderid", orderid)
                .params("putAmount",putAmount)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            T.showShort(jsonObject.getString("msg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showNetworkError(activity);
                    }
                });
    }

    //赎回
    public  static  void backOrder(final Context activity , String orderid){
        OkGo.<String>post(Constants.URL_BASE + "order/backOrder")//
                .tag(activity)//
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(activity,"token",""))
                .params("orderid", orderid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            T.showShort(jsonObject.getString("msg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showNetworkError(activity);
                    }

                });
    }

    //支付
    public  static  void payOrder(final Context activity , String orderid){
        OkGo.<String>post(Constants.URL_BASE + "order/payOrder")//
                .tag(activity)//
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(activity,"token",""))
                .params("orderid", orderid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            T.showShort(jsonObject.getString("msg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showNetworkError(activity);
                    }
                });
    }

    //赎回
    public  static  void transferOrder(final Context activity , String orderid){
        OkGo.<String>post(Constants.URL_BASE + "order/transfer")//
                .tag(activity)//
                .headers("Authorization", "Bearer " + PreferenceUtils.getPrefString(activity,"token",""))
                .params("orderid", orderid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            T.showShort(jsonObject.getString("msg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showNetworkError(activity);
                    }

                });
    }

}