package com.tcc.parkingmanagement;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.mapapi.SDKInitializer;
import com.tcc.parkingmanagement.service.LocationService;

/**
 * Created by admin on 2018/8/21 18:57
 * 邮箱：yaosongcai@ujifu.com
 */

public class MyApplication extends Application {

    private static Context context;
    public static double App_latitude = 29.560096;//默认经纬度，渝中区
    public static double App_longitude = 106.568699;
    public static String App_cityStr;
    public static String App_addressStr;

    @Override
    public void onCreate() {
        super.onCreate();

        this.context = this;

    }

    public static Context getContext(){

        return context;
    }


}
