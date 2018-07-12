package io.ionic.ylnewapp.utils;

import android.app.Activity;
import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.util.ConvertUtils;

/**
 * Created by mogojing on 2018/5/16/0016.
 */

public  class DateUtil {

    public static String getYmdforJson(String time){
       return time.substring(0,10);
    }



    //不知道了
    public static String getDTofSystem(String json){
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");//这个是你要转成后的时间的格式
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(json))));   // 时间戳转换成时间
        return sd;
    }

    //根据时间戳传时间
    public static String getYMDofSystem(){
        String json = System.currentTimeMillis()+"";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//这个是你要转成后的时间的格式
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(json))));   // 时间戳转换成时间
        return sd;
    }
    //日期后延
    public static String getDateAndTimeofMouth() {
        Date date = new Date();//取时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); //需要将date数据转移到Calender对象中操作
        calendar.add(calendar.HOUR_OF_DAY, -6);//把日期往后增加n天.正数往后推,负数往前移动
        date = calendar.getTime();   //这个时间就是日期往后推一天的结果
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(date);
        return dateNowStr;
    }



    //格式化的年月日
    public static String getDateAndTime(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);
        System.out.println("格式化后的日期：" + dateNowStr);
        return dateNowStr;
    }


    //日期后延
    public static String getDateAndTimeLazy() {
        Date date = new Date();//取时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); //需要将date数据转移到Calender对象中操作
        calendar.add(calendar.DATE, 2);//把日期往后增加n天.正数往后推,负数往前移动
        date = calendar.getTime();   //这个时间就是日期往后推一天的结果
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(date);
        return dateNowStr;
    }

    //修改后台时间参数
    public static int vyear, vmonth, vday;
    public static void getTime() {
        long time = System.currentTimeMillis();//long now = android.os.SystemClock.uptimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = new Date(time);
        String t1 = format.format(d1);
        vyear = Integer.parseInt(t1.substring(0, 4));
        vmonth = Integer.parseInt(t1.substring(5, 7));
        vday = Integer.parseInt(t1.substring(8, 10));
    }

    static String time;
    public static String showDateTime(Context context) {
        getTime();
        //时间选择器
        final DatePicker picker = new DatePicker((Activity) context);
        picker.setCanceledOnTouchOutside(true);
        picker.setUseWeight(true);
        picker.setTopPadding(ConvertUtils.toPx(context, 10));
        picker.setRangeEnd(2030, 1, 11);
        picker.setRangeStart(1970, 1, 1);
        picker.setSelectedItem(vyear, vmonth, vday);
        picker.setResetWhileWheel(false);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                T.showShort(year + "-" + month + "-" + day);
                time = year+month+day;
            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
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
        picker.show();
        return time;
    }
}
