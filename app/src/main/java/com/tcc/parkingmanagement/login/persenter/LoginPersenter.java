package com.tcc.parkingmanagement.login.persenter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;

import com.tcc.parkingmanagement.login.contracts.LoginContract;

import butterknife.ButterKnife;

/**
 * Created by admin on 2018/8/18 10:17
 * 邮箱：yaosongcai@ujifu.com
 */

public class LoginPersenter implements LoginContract.Persenter {

    private LoginContract.Views views;

    private Context context;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 200) {
                views.loginSuccer();
            } else if (msg.what == 100) {
                views.loginError((String) msg.obj);
            }
        }
    };

    public LoginPersenter(LoginContract.Views views, Activity context) {
        this.views = views;
        this.context = context;

        initButterknife(context);
        views.initView();
    }

    @Override
    public void login(final String userName, final String password) {

        if (userName == null || userName.isEmpty()) {
            return;
        }

        if (userName.equals("admin") && password.equals("123456")) {
            handler.sendEmptyMessageDelayed(200,2000);
            SharedPreferences sharedPreferences = context.getSharedPreferences("NAME",Context.MODE_PRIVATE);
            SharedPreferences.Editor et = sharedPreferences.edit();
            et.putString("name",userName);
            et.putString("pass",password);
            et.commit();
        } else {
            Message msg = new Message();
            msg.what = 100;
            msg.obj = "服务器异常";
            handler.sendMessageDelayed(msg,2000);
        }

    }

    @Override
    public void versionCodes() {

    }

    @Override
    public void initButterknife(Activity activity) {
        ButterKnife.bind(activity);
    }
}
