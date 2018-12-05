package com.tcc.parkingmanagement.login.contracts;

import com.tcc.parkingmanagement.base.basePersenter.BasePersenter;
import com.tcc.parkingmanagement.base.baseView.BaseView;

/**
 * 登录管理器
 * Created by admin on 2018/8/18 10:10
 * 邮箱：yaosongcai@ujifu.com
 */

public interface LoginContract {

    public interface Views extends BaseView{

        void initView();

        /**
         * 登录成功
         */
        void loginSuccer();

        /**
         * 登录失败
         * @param msg
         *
         */
        void loginError(String msg);
    }

    public interface Persenter extends BasePersenter{

        /**
         * 登录接口
         * @param userName
         * @param password
         */
        void login(String userName,String password);

        /**
         * 检测更新接口
         */
        void versionCodes();
    }
}
