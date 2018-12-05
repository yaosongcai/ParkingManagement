package com.tcc.parkingmanagement.home.contracts;

import com.tcc.parkingmanagement.base.basePersenter.BasePersenter;
import com.tcc.parkingmanagement.base.baseView.BaseView;
import com.tcc.parkingmanagement.database.ParkManagerTable;

/**
 * 首页管理器
 * Created by admin on 2018/8/18 13:55
 * 邮箱：yaosongcai@ujifu.com
 */

public interface HomeContract {

    public interface Views extends BaseView{

        /**
         * 初始化视图
         */
        void initViews();

        /**
         * 设置今日总收益
         * @param totalMoney
         */
        void setTotalIncome(String totalMoney);

        /**
         * 设置当前车位信息
         * @param bean
         */
        void setParkingLot(ParkManagerTable bean);
    }

    public interface Persenter extends BasePersenter{

        /**
         * 查询当前车位信息
         */
        void queryParkInfo();

        /**
         * 查询当前停车场收益情况
         */
        void queryTotalIncome();
    }
}
