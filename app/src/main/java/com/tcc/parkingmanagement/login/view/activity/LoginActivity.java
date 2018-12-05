package com.tcc.parkingmanagement.login.view.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tcc.parkingmanagement.R;
import com.tcc.parkingmanagement.base.baseView.BaseActivity;
import com.tcc.parkingmanagement.home.view.activity.MainActivity;
import com.tcc.parkingmanagement.login.contracts.LoginContract;
import com.tcc.parkingmanagement.login.persenter.LoginPersenter;
import com.tcc.parkingmanagement.util.SettingUtils;
import com.tcc.parkingmanagement.view.HintView;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.Views{

    private LoginContract.Persenter mPersenter;

    private HintView mHint;

    private boolean haveInstallPermission;

    private String[] mPersion = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };
    private static final int REQUEST_READ = 60,
            INSTALL_PERMISS_CODE = 70;


    @BindView(R.id.et_name)
    EditText et_name;

    @BindView(R.id.et_pass)
    EditText et_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPersenter = new LoginPersenter(this,this);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
//            findViewById(R.id.view_status).getLayoutParams().height = ScreenUtils.getStatusHeight(this);
    }

    @TargetApi(26)
    @Override
    protected void onResume() {
        super.onResume();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            haveInstallPermission = getPackageManager().canRequestPackageInstalls();
            if (!haveInstallPermission) {
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("安装权限")
                        .setMessage("需要打开允许来自此来源，请去设置中开启此权限")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setNeutralButton("去设置", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                toInstallPermissionSettingIntent();
                                dialog.dismiss();
                            }
                        })
                        .create();
                dialog.show();

                return;
            }
        }

        if (ContextCompat.checkSelfPermission(this, mPersion[0]) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, mPersion[0])) {
                settingDialog("读取文件");
            } else {

                ActivityCompat.requestPermissions(this, mPersion, REQUEST_READ);
            }
            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INSTALL_PERMISS_CODE){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                haveInstallPermission = true;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case INSTALL_PERMISS_CODE:
                if (ContextCompat.checkSelfPermission(this, mPersion[0]) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, mPersion[0])) {
                        settingDialog("读取文件");
                    } else {

                        ActivityCompat.requestPermissions(this, mPersion, REQUEST_READ);
                    }
                    return;
                }
                break;
            case REQUEST_READ:
                if (ContextCompat.checkSelfPermission(this, permissions[0]) != PackageManager.PERMISSION_GRANTED) {
                    settingDialog("读取文件");
                    return;
                }
                break;
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
                        SettingUtils.getInstance(LoginActivity.this).gotoSetting();
                    }
                })
                .create();
        dialog.show();
    }


    @OnClick({R.id.login})
    void click(View view){
        switch (view.getId()){
            case R.id.login:
                startLogin();
                break;
        }
    }

    private void startLogin(){
        if (TextUtils.isEmpty(et_name.getText())){
            Toast.makeText(this,"用户名不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(et_pass.getText())){
            Toast.makeText(this,"密码不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }

        mPersenter.login(et_name.getText().toString(),et_pass.getText().toString());
    }

    @Override
    public void initView() {
        mHint = new HintView(this);

        SharedPreferences sp = getSharedPreferences("NAME", Context.MODE_PRIVATE);
        et_name.setText(sp.getString("name",""));
        et_pass.setText(sp.getString("pass",""));
    }

    @Override
    public void loginSuccer() {
        mHint.dismiss();
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    @Override
    public void loginError(String msg) {
        mHint.dismiss();
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    /**
     * 开启安装未知来源权限
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void toInstallPermissionSettingIntent() {
        Uri packageURI = Uri.parse("package:" + getPackageName());
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
        startActivityForResult(intent, INSTALL_PERMISS_CODE);
    }
}
