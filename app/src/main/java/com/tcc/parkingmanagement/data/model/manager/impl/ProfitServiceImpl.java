package com.tcc.parkingmanagement.data.model.manager.impl;

import android.database.Cursor;

import com.tcc.parkingmanagement.data.model.bean.ProfitBean;
import com.tcc.parkingmanagement.data.model.bean.ProfitTotalBean;
import com.tcc.parkingmanagement.data.model.manager.ProfitService;
import com.tcc.parkingmanagement.database.HistoryInfoTable;
import com.tcc.parkingmanagement.database.SQLHelper;
import com.tcc.parkingmanagement.gen.HistoryInfoTableDao;
import com.tcc.parkingmanagement.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

import static com.tcc.parkingmanagement.util.TimeUtil.getCurrentDay;

/**
 * 项目名称：    com.tcc.parkingmanagement.data.model.manager.impl
 * 类描述        查询收益管理具体实现
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/8/22
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/8/22
 * 修改备注：
 */
public class ProfitServiceImpl implements ProfitService {

    @Override
    public ProfitTotalBean queryProfitByDate(String date, int page, int rows) {
        String tableName = SQLHelper.getInstance().getHistoryInfoTableDao().getTablename();
        ProfitTotalBean bean = new ProfitTotalBean();
        try{
            Cursor cursor = SQLHelper.getInstance().getHistoryInfoTableDao().getDatabase().rawQuery("SELECT SUM(TOTAL_AMOUT) FROM "+tableName+" WHERE OUT_TIMES = '"+date+"'",null);
            while (cursor.moveToNext()){
                bean.setTotalMoney(cursor.getString(0));
            }
            cursor.close();
            List<HistoryInfoTable> list = SQLHelper.getInstance().getHistoryInfoTableDao().queryBuilder()
                    .where(HistoryInfoTableDao.Properties.OutTimes.eq(date))
                    .limit(rows)
                    .offset(page*rows)
                    .orderDesc(HistoryInfoTableDao.Properties.OutTime)
                    .list();

            List<ProfitBean> profitBeen = new ArrayList<>();
            for (HistoryInfoTable table:list){
                ProfitBean profitBean = new ProfitBean();
                profitBean.setTime(table.getOutTime());
                profitBean.setTotalAmount(table.getTotalAmout());
                profitBeen.add(profitBean);
            }
            bean.setList(profitBeen);
        }catch (Exception e){
            e.printStackTrace();
        }
        return bean;
    }

    @Override
    public ProfitTotalBean queryProfitByDate(@Duration int type, int page, int rows) {
        switch (type){
            case ProfitService.QUERY_DAY:
                return queryProfitByDate(getCurrentDay().replaceAll("-",""),page,rows);
            case ProfitService.QUERY_WEEK:
                ProfitTotalBean bean = new ProfitTotalBean();
                try {
                    String tableName = SQLHelper.getInstance().getHistoryInfoTableDao().getTablename();
                    String currentday = TimeUtil.getCurrentDay().replaceAll("-","");
                    String weekTime = TimeUtil.getDateBefore(currentday,7);
                    Cursor cursor = SQLHelper.getInstance().getHistoryInfoTableDao().getDatabase().rawQuery("SELECT SUM(TOTAL_AMOUT) FROM '"+tableName+"' WHERE OUT_TIMES <= '"+currentday+"' and OUT_TIMES >= '"+weekTime+"'",null);
                    while (cursor.moveToNext()){
                        bean.setTotalMoney(cursor.getString(0));
                    }
                    //分组求和查询  且降序
                    cursor = SQLHelper.getInstance().getHistoryInfoTableDao().getDatabase().rawQuery("SELECT OUT_TIMES, SUM(TOTAL_AMOUT) FROM '"+tableName+"' WHERE OUT_TIMES <= '"+currentday+"' and OUT_TIMES >= '"+weekTime+"' GROUP BY OUT_TIMES ORDER BY OUT_TIMES DESC",null);
                    List<ProfitBean> profitBeen = new ArrayList<>();
                    while (cursor.moveToNext()){
                        ProfitBean profitBean = new ProfitBean();
                        profitBean.setTime(cursor.getString(0));
                        profitBean.setTotalAmount(cursor.getString(1));
                        profitBeen.add(profitBean);
                    }
                    cursor.close();
                    bean.setList(profitBeen);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return bean;
        }
        return null;
    }
}
