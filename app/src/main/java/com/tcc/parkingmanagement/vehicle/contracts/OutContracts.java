package com.tcc.parkingmanagement.vehicle.contracts;

import android.graphics.Bitmap;

import com.tcc.parkingmanagement.base.basePersenter.BasePersenter;
import com.tcc.parkingmanagement.base.baseView.BaseView;

/**
 * 车辆入库管理器
 * Created by admin on 2018/8/18 14:25
 * 邮箱：yaosongcai@ujifu.com
 */

public interface OutContracts {

    public interface Views extends BaseView{

        /**
         * 初始化视图
         */
        void initView();

        /**
         * 识别成功
         * @param plateNo       车牌号
         * @param time_y        年月日
         * @param time_h        时分秒
         */
        void updateView(String plateNo, String time_y, String time_h);

        /**
         *  识别失败
         */
        void Identificationfailure();
    }

    public interface Persenter extends BasePersenter{

        /**
         * 初始化openCV
         */
        void initOpenCV();

        /**
         * 识别车牌
         * @param bitmap    车牌图片
         */
        void verifyPlateNo(Bitmap bitmap);

        /**
         * 确认入库
         */
        void enterRK();
    }
}
