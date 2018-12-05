package com.tcc.parkingmanagement.database;

import com.tcc.parkingmanagement.gen.DaoSession;
import com.tcc.parkingmanagement.gen.HistoryInfoTableDao;
import com.tcc.parkingmanagement.gen.ParkManagerTableDao;
import com.tcc.parkingmanagement.gen.PlateInfoTableDao;
import com.tcc.parkingmanagement.gen.UserInfoTableDao;
import com.tcc.parkingmanagement.home.model.bean.ParkInfoBean;

/**
 * Created by jackychiang on 2017/8/1.
 */

public class SQLHelper {
    private static SQLHelper sqlHelper;
    private PlateInfoTableDao plateInfoTableDao;
    private HistoryInfoTableDao historyInfoTableDao;
    private UserInfoTableDao userInfoTableDao;
    private ParkManagerTableDao parkManagerTableDao;

    /**
     * 创建User表实例
     *
     * @return
     */
    public PlateInfoTableDao getPlateInfoTableDao() {
        DaoManager daoManager = DaoManager.getInstance();
        DaoSession daoSession = daoManager.getSession();
        plateInfoTableDao = daoSession.getPlateInfoTableDao();
        return plateInfoTableDao;
    }

    public HistoryInfoTableDao getHistoryInfoTableDao() {
        historyInfoTableDao = DaoManager.getInstance().getSession().getHistoryInfoTableDao();
        return historyInfoTableDao;
    }

    public UserInfoTableDao getUserInfoTableDao() {
        userInfoTableDao = DaoManager.getInstance().getSession().getUserInfoTableDao();
        return userInfoTableDao;
    }

    public ParkManagerTableDao getParkManagerTableDao() {
        parkManagerTableDao = DaoManager.getInstance().getSession().getParkManagerTableDao();
        return parkManagerTableDao;
    }

    /**
     * 创建单例
     *
     * @return
     */
    public static SQLHelper getInstance() {
        if (sqlHelper == null) {
            sqlHelper = new SQLHelper();
        }
        return sqlHelper;
    }
}
