package com.tcc.parkingmanagement.util;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by admin on 2018/8/20 21:11
 * 邮箱：yaosongcai@ujifu.com
 */

public class TimeUtil {

    @SuppressLint("SimpleDateFormat")
    public static String getCurrentDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }

    @SuppressLint("SimpleDateFormat")
    public static String getCurrentHous(){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(new Date());
    }

    /**
     * 获取当前日期
     *
     * @param format 日期格式
     * @return 当前日期
     */
    public static String getCurrentDay(String format) {
        SimpleDateFormat format1 = new SimpleDateFormat(format);
        return format1.format(new Date());
    }

    /**
     * 根据输入的日期date，由day确定增加day天，或者减少day天
     *
     * @param date 输入一个日期
     * @param day  增加或减少的天数
     * @return
     */
    public static String dateIncreaseAndDecrease(String date, int day) {

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            d = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (day == 0) {
            return sdf.format(new Date(d.getTime()));
        }
        return sdf.format(new Date(d.getTime() + day * 24 * 60 * 60 * 1000));
    }

    /**
     * 根据yyyy-MM-dd获取毫秒
     *
     * @param time
     * @return 0 解析失败
     */
    public static long getMillisByYmd(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        long millis = 0;
        try {
            Date parse = format.parse(time);
            millis = parse.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            millis = 0;
        }
        return millis;
    }

    /**
     * 得到几天前的时间(yyyy-MM-dd)
     * @param d
     * @param day
     * @return
     */
    public static String getDateBefore(String d,int day){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar now =Calendar.getInstance();
        try {
            now.setTime(simpleDateFormat1.parse(d));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
        return simpleDateFormat.format(now.getTime());
    }

    /**
     * 得到几天后的时间(yyyy-MM-dd)
     * @param d
     * @param day
     * @return
     */
    public static String getDateAfter(String d,int day){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar now =Calendar.getInstance();
        try {
            now.setTime(simpleDateFormat1.parse(d));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        now.set(Calendar.DATE,now.get(Calendar.DATE)+day);
        return simpleDateFormat.format(now.getTime());
    }


    /**
     * 根据date获取毫秒
     *
     * @param time
     * @return 0 解析失败
     */
    public static long getMillisByDate(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long millis = 0;
        try {
            Date parse = format.parse(time);
            millis = parse.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            millis = 0;
        }
        return millis;
    }

    /**
     * @param time "HH:mm:ss"
     * @return millis of time
     */
    public static long getMillis(String time) {
        if (TextUtils.isEmpty(time))
            return 0;
        if (!time.contains(":"))
            return 0;
        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0]);
        int min = Integer.parseInt(times[1]);
        // 02:02:47.00
        String millis = times[2];
        if (millis != null && millis.length() > 2) {
            millis = millis.substring(0, 2);
        }
        int sec = Integer.parseInt(millis);
        long length = hour * 3600 + min * 60 + sec;
        return length = length * 1000;
    }

    /**
     * @param millis second * 1000
     * @return time "HH:mm:ss"
     */
    @SuppressLint("DefaultLocale")
    public static String getTime(long millis) {

        long length = millis / 1000;
        long hour = length / 3600;
        long min = (length - hour * 3600) / 60;
        long sec = length - hour * 3600 - min * 60;
        String time = String.format("%02d:%02d:%02d", hour, min, sec);
        return time;

    }

}
