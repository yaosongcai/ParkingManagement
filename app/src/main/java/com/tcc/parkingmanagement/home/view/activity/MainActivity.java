package com.tcc.parkingmanagement.home.view.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.mapapi.SDKInitializer;
import com.tcc.parkingmanagement.MyApplication;
import com.tcc.parkingmanagement.R;
import com.tcc.parkingmanagement.base.baseView.BaseActivity;
import com.tcc.parkingmanagement.data.view.activity.ProfitActivity;
import com.tcc.parkingmanagement.database.ParkManagerTable;
import com.tcc.parkingmanagement.home.contracts.HomeContract;
import com.tcc.parkingmanagement.home.persenter.HomePersenter;
import com.tcc.parkingmanagement.home.view.widget.ShadowConfig;
import com.tcc.parkingmanagement.home.view.widget.ShadowHelper;
import com.tcc.parkingmanagement.service.LocationService;
import com.tcc.parkingmanagement.util.ScreenUtils;
import com.tcc.parkingmanagement.util.SettingUtils;
import com.tcc.parkingmanagement.vehicle.view.activity.WarehousingActivity;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements HomeContract.Views {

    private HomeContract.Persenter mPersenter;
    private MyLocationListener mListener;
    public LocationService locationService;
    public Vibrator mVibrator;

    //需要 Camera
    String[] permissions = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS};//在SDCard中创建与删除文件权限
    private static final int REQUEST_READ = 60;

    @BindView(R.id.rl)
    RelativeLayout rl;

    @BindView(R.id.tv_name)
    TextView tv_name;//停车场名称

    @BindView(R.id.tv_adress)
    TextView tv_adress;//停车场地址

    @BindView(R.id.tv_inputnum)
    TextView tv_inputnum;//已停车位

    @BindView(R.id.tv_outnum)
    TextView tv_outnum;//剩余车位

    @BindView(R.id.tv_sy)
    TextView tv_sy;//今日总收益

    @BindView(R.id.address)
    TextView address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPersenter = new HomePersenter(this, this);
        initBaiduMap();
        startLocation();

    }

    @OnClick({R.id.tv_input, R.id.tv_out, R.id.setting, R.id.rl_sy})
    void click(View view) {
        switch (view.getId()) {
            case R.id.tv_input:

                if (ContextCompat.checkSelfPermission(this, permissions[0]) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                        settingDialog("读取文件");
                    } else {

                        ActivityCompat.requestPermissions(this, permissions, REQUEST_READ);
                    }
                    return;
                }
                startActivity(new Intent(this, WarehousingActivity.class));
                break;
            case R.id.tv_out:
                break;
            case R.id.setting:
                break;
            case R.id.rl_sy:
                startActivity(new Intent(this, ProfitActivity.class));
                break;
        }
    }

    @Override
    public void initViews() {

        ShadowConfig.Builder config = new ShadowConfig.Builder()
                .setColor(Color.parseColor("#ffffff"))
                .setShadowColor(Color.parseColor("#8d8d8d"))
                .setGradientColorArray(new int[]{Color.parseColor("#ffffff")})
                .setRadius(ScreenUtils.dp2px(this, 5))
                .setOffsetX(ScreenUtils.dp2px(this, 2))
                .setOffsetY(ScreenUtils.dp2px(this, 2));
        ShadowHelper.setShadowBgForView(rl, config);
    }

    @Override
    public void setTotalIncome(String totalMoney) {
        tv_sy.setText("¥ " + totalMoney);
    }

    public void initBaiduMap() {
        locationService = new LocationService(getApplicationContext());
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());
    }

    @Override
    public void setParkingLot(ParkManagerTable bean) {
        tv_inputnum.setText(bean.getUsedParkSpace());
        tv_outnum.setText(bean.getTotalParkSpace() - bean.getUsedParkSpace());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_READ:
                if (ContextCompat.checkSelfPermission(this, permissions[0]) != PackageManager.PERMISSION_GRANTED) {
                    settingDialog("读取文件");
                    return;
                }
                startActivity(new Intent(this, WarehousingActivity.class));
                break;
        }
    }

    public void startLocation() {


        try {
            mListener = new MyLocationListener();

            locationService.registerListener(mListener);

            locationService.setLocationOption(locationService.getOption());

            locationService.start();// 定位SDK
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopLocation() {


        try {
            locationService.unregisterListener(mListener); //注销掉监听
            locationService.stop(); //停止定位服务
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            int type = location.getLocType();
            if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                MyApplication.App_latitude = location.getLatitude();
                MyApplication.App_longitude = location.getLongitude();
                MyApplication.App_addressStr = location.getAddrStr();
                MyApplication.App_cityStr = location.getCity();

                if (MyApplication.App_cityStr != null && MyApplication.App_cityStr.contains("市")) {
                    MyApplication.App_cityStr = MyApplication.App_cityStr.substring(0, MyApplication.App_cityStr.indexOf("市"));
                }

                address.setText(MyApplication.App_cityStr);

                if (mLocationState == LocationState.YES) {
                    stopLocation();
                }
            }
        }

    }

    private void settingDialog(String permissionMsg) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("无法获取" + permissionMsg + "权限，请先开启此权限")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNeutralButton("去设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SettingUtils.getInstance(MainActivity.this).gotoSetting();
                    }
                })
                .create();
        dialog.show();
    }

    /**
     * YES定位完成，自动关闭定位服务
     * NO定位完成，按频率继续定位
     */
    private enum LocationState {
        NO, YES
    }

    /**
     * 默认定位完成后自动停止定位
     */
    public LocationState mLocationState = LocationState.YES;
}