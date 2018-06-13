package io.ionic.ylnewapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;

import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.util.ConvertUtils;
import io.ionic.ylnewapp.view.activity.user.LoginActivity;

/**
 * Created by mogojing on 2018/5/18/0018.
 */

public class ActivityUtils {

    //关闭软键盘
    public static void colseIM(View view){
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
        }
    }

    public static void toLogin(final Activity mContext,final int status){
        T.showShort("登录状态已失效，请重新登录");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent  intent = new Intent(mContext, LoginActivity.class);
                intent.putExtra("status",status);//0是Activity,1是fragment
                mContext.startActivity(intent);
                mContext.finish();

            }
        }, 1500);
    }


    /**
     * 获取viewText
     * @param editText
     * @return
     */
    public static String getView(EditText editText){
        return editText.getText().toString().trim();
    }


    public static  void setWheelStyle(final DatePicker picker,Context context){
        DateUtil.getTime();//获取当前选中日期为默认今日
        picker.setCanceledOnTouchOutside(true);
        picker.setUseWeight(true);
        picker.setTextColor(Color.parseColor("#FEA620"));
        picker.setLabelTextColor(Color.parseColor("#FEA620"));
        picker.setDividerColor(Color.parseColor("#FEA620"));
        picker.setCancelTextColor(Color.parseColor("#FEA620"));
        picker.setSubmitTextColor(Color.parseColor("#FEA620"));
        picker.setTopLineColor(Color.parseColor("#FEA620"));
        picker.setTopPadding(ConvertUtils.toPx(context, 10));
        picker.setRangeEnd(2030, 1, 11);
        picker.setRangeStart(1970, 1, 1);
        picker.setSelectedItem(DateUtil.vyear, DateUtil.vmonth,DateUtil.vday);
        picker.setResetWhileWheel(false);
        picker.setOnWheelListener(new cn.qqtheme.framework.picker.DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int index, String year) {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
            }

            @Override
            public void onMonthWheeled(int index, String month) {
                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
            }

            @Override
            public void onDayWheeled(int index, String day) {
                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
            }
        });
    }

}
