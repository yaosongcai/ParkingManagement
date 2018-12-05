package com.tcc.parkingmanagement.home.persenter;

import android.app.Activity;
import android.content.Context;

import com.tcc.parkingmanagement.data.model.bean.ProfitTotalBean;
import com.tcc.parkingmanagement.data.model.manager.ProfitService;
import com.tcc.parkingmanagement.data.model.manager.impl.ProfitServiceImpl;
import com.tcc.parkingmanagement.database.ParkManagerTable;
import com.tcc.parkingmanagement.database.SQLHelper;
import com.tcc.parkingmanagement.home.contracts.HomeContract;

import butterknife.ButterKnife;

/**
 * Created by admin on 2018/8/18 14:10
 * 邮箱：yaosongcai@ujifu.com
 */

public class HomePersenter implements HomeContract.Persenter {

    private HomeContract.Views views;

    private Context context;

    private ProfitService profitService;

    public HomePersenter(HomeContract.Views views, Activity context) {
        this.views = views;
        this.context = context;

        initButterknife(context);
        views.initViews();
//        queryParkInfo();
        queryTotalIncome();
    }

    @Override
    public void initButterknife(Activity activity) {
        ButterKnife.bind(activity);

        profitService = new ProfitServiceImpl();

//        SQLHelper.getInstance().getHistoryInfoTableDao().deleteAll();
//        for (int i = 0; i < 100; i++) {
//            HistoryInfoTable table = new HistoryInfoTable();
//            table.setWarehousingId(String.valueOf("062325asdw" + i));
//            table.setIsMonthlyTicket("0");
//            if (i < 25) {
//                table.setOutTime("2018082410400" + i);
//                table.setOutTimes("20180824");
//            } else if (i>=25&&i<50){
//                table.setOutTime("2018082310400" + i);
//                table.setOutTimes("20180823");
//            } else {
//                table.setOutTime("2018082210400" + i);
//                table.setOutTimes("20180822");
//            }
//            table.setTotalAmout(String.valueOf(30 + i));
//            SQLHelper.getInstance().getHistoryInfoTableDao().insert(table);
//        }
    }

    @Override
    public void queryParkInfo() {
        ParkManagerTable parkManagerTable = SQLHelper.getInstance().getParkManagerTableDao().queryBuilder().unique();
        views.setParkingLot(parkManagerTable);
    }

    @Override
    public void queryTotalIncome() {
        ProfitTotalBean total = profitService.queryProfitByDate(ProfitService.QUERY_DAY,1,10);
        if (total != null){
            views.setTotalIncome(total.getTotalMoney() == null?"":total.getTotalMoney());
        } else {
            views.setTotalIncome("");
        }
    }
}
