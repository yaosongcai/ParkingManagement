package com.tcc.parkingmanagement.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.compat.BuildConfig;

/**
 * 跳转到各大手机运营商的手机设置页面
 * <p>
 * Created by Administrator on 2017-08-16 0016.
 */

public class SettingUtils {

    private Context context;

    private static SettingUtils instance = null;

    private SettingUtils(Context context) {
        this.context = context;
    }

    public static SettingUtils getInstance(Context context) {
        if (instance == null)
            instance = new SettingUtils(context);
        return instance;
    }

    /**
     * 去系统设置界面开启权限
     */
    public void gotoSetting() {


        switch (getPhoneType()) {
            case 0:
                context.startActivity(getAppDetailSettingIntent());
                break;
            case 1:
                gotoMiuiPermission_1();
                break;
            case 2:
                gotoMeizuPermission();
                break;
            case 3:
                gotoHuaweiPermission();
                break;
            case 4:
                gotoSony();
                break;
            case 5:
                gotoOPPO();
                break;
            case 6:
                gotoLG();
                break;
            case 7:
                gotoLetv();
                break;
            case 8:
                goto360();
                break;
            case 9:
                gotoVivo();
        }
    }

    private void gotoVivo() {
//        Intent appIntent = context.getPackageManager().getLaunchIntentForPackage("com.iqoo.secure");
//        context.startActivity(appIntent);
//        floatingView = new SettingFloatingView(this, "SETTING", getApplication(), 0);
//        floatingView.createFloatingView();

        try {
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
            ComponentName comp = new ComponentName("com.iqoo.secure", "com.iqoo.secure.permission.PermissionManagerActivity");
            intent.setComponent(comp);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            context.startActivity(getAppDetailSettingIntent());
        }
    }

    private void gotoSettings() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
    }

    /**
     * 跳转到miui的权限管理页面
     */
    private void gotoMiuiPermission_1() {
        try {
            //MIUI 8
            Intent localIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
            localIntent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
            localIntent.putExtra("extra_pkgname", context.getPackageName());
            context.startActivity(localIntent);
        } catch (Exception e) {
            try {
                // MIUI 5/6/7
                Intent localIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
                localIntent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
                localIntent.putExtra("extra_pkgname", context.getPackageName());
                context.startActivity(localIntent);
            } catch (Exception e1) {
                // 否则跳转到应用详情
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                intent.setData(uri);
                context.startActivity(intent);
            }
        }


    }

    /**
     * 跳转到魅族的权限管理系统
     */
    private void gotoMeizuPermission() {
        Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            context.startActivity(getAppDetailSettingIntent());
        }
    }

    /**
     * 华为的权限管理页面
     */
    private void gotoHuaweiPermission() {
        try {
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");//华为权限管理
            intent.setComponent(comp);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            context.startActivity(getAppDetailSettingIntent());
        }

    }

    /**
     * 跳转索尼设置页面
     */
    private void gotoSony() {
        try {
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
            ComponentName comp = new ComponentName("com.sonymobile.cta", "com.sonymobile.cta.SomcCTAMainActivity");
            intent.setComponent(comp);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            context.startActivity(getAppDetailSettingIntent());
        }

    }

    /**
     * 跳转OPPO设置页面
     */
    private void gotoOPPO() {
        try {
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
            ComponentName comp = new ComponentName("com.color.safecenter", "com.color.safecenter.permission.PermissionManagerActivity");
            intent.setComponent(comp);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            context.startActivity(getAppDetailSettingIntent());
        }

    }

    /**
     * 跳转LG设置页面
     */
    private void gotoLG() {
        try {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
            ComponentName comp = new ComponentName("com.android.settings", "com.android.settings.Settings$AccessLockSummaryActivity");
            intent.setComponent(comp);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            context.startActivity(getAppDetailSettingIntent());
        }

    }

    /**
     * 跳转乐视设置页面
     */
    private void gotoLetv() {
        try {
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
            ComponentName comp = new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.PermissionAndApps");
            intent.setComponent(comp);
        } catch (Exception e) {
            e.printStackTrace();
            context.startActivity(getAppDetailSettingIntent());
        }

    }

    private void goto360() {
        try {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
            ComponentName comp = new ComponentName("com.qihoo360.mobilesafe", "com.qihoo360.mobilesafe.ui.index.AppEnterActivity");
            intent.setComponent(comp);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            context.startActivity(getAppDetailSettingIntent());
        }

    }

    /**
     * 获取应用详情页面intent
     *
     * @return
     */
    private Intent getAppDetailSettingIntent() {

        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        return localIntent;
    }

    public int getPhoneType() {
        int phoneType = 0;
        if (SystemUtil.getDeviceBrand().contains("Xiaomi"))
            phoneType = 1;
        if (SystemUtil.getDeviceBrand().contains("Meizu"))
            phoneType = 2;
        if (SystemUtil.getDeviceBrand().contains("Huawei")||SystemUtil.getDeviceBrand().contains("honor"))
            phoneType = 3;
        if (SystemUtil.getDeviceBrand().contains("Sony"))
            phoneType = 4;
        if (SystemUtil.getDeviceBrand().contains("OPPO"))
            phoneType = 5;
        if (SystemUtil.getDeviceBrand().contains("LG"))
            phoneType = 6;
        if (SystemUtil.getDeviceBrand().contains("Letv"))
            phoneType = 7;
        if (SystemUtil.getDeviceBrand().contains("360"))
            phoneType = 8;
//        if (SystemUtil.getDeviceBrand().contains("vivo"))
//            phoneType = 9;
        return phoneType;
    }
}
