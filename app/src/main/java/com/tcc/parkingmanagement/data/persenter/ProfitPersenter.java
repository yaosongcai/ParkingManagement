package com.tcc.parkingmanagement.data.persenter;

import android.app.Activity;
import android.content.Context;

import com.tcc.parkingmanagement.data.contracts.ProfitContract;
import com.tcc.parkingmanagement.data.model.bean.ProfitBean;
import com.tcc.parkingmanagement.data.model.bean.ProfitTotalBean;
import com.tcc.parkingmanagement.data.model.manager.ProfitService;
import com.tcc.parkingmanagement.data.model.manager.impl.ProfitServiceImpl;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * 项目名称：    com.tcc.parkingmanagement.data.persenter
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/8/24
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/8/24
 * 修改备注：
 */
public class ProfitPersenter implements ProfitContract.Persenter {

    private Context context;

    private ProfitContract.Views views;

    private ProfitService profitService;

    private int type;

    public ProfitPersenter(Activity context, ProfitContract.Views views) {
        this.context = context;
        this.views = views;

        initButterknife(context);
        views.init();
        initCurrentMoney();
        initDatas(ProfitService.QUERY_WEEK);
    }

    @Override
    public void initButterknife(Activity activity) {

        ButterKnife.bind(activity);

        profitService = new ProfitServiceImpl();
    }

    @Override
    public void initCurrentMoney() {
        ProfitTotalBean total = profitService.queryProfitByDate(ProfitService.QUERY_DAY,1,10);
        if (total != null){
            views.initCurrentDay(total.getTotalMoney() == null?"":total.getTotalMoney());
        } else {
            views.initCurrentDay("0.00");
        }
    }

    @Override
    public void initDatas(int type) {
        ProfitTotalBean total = profitService.queryProfitByDate(type,1,10);
        if (total != null){
            views.initViews(type,total.getTotalMoney() == null?"":total.getTotalMoney());
            views.initListView(total.getList());
        } else {
            views.initViews(type,"0.00");
            views.initListView(new ArrayList<ProfitBean>());
        }
    }

    @Override
    public void initDatas(int page, int rows, int state) {

    }
}
