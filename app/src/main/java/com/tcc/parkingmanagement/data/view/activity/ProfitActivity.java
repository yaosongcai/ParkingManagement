package com.tcc.parkingmanagement.data.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.tcc.parkingmanagement.R;
import com.tcc.parkingmanagement.base.baseView.BaseActivity;
import com.tcc.parkingmanagement.data.contracts.ProfitContract;
import com.tcc.parkingmanagement.data.model.adapter.ProfitAdapter;
import com.tcc.parkingmanagement.data.model.bean.ProfitBean;
import com.tcc.parkingmanagement.data.persenter.ProfitPersenter;
import com.tcc.parkingmanagement.util.ScreenUtils;

import java.util.List;

import butterknife.BindView;

public class ProfitActivity extends BaseActivity implements ProfitContract.Views {

    private ProfitAdapter adapter;

    private ProfitContract.Persenter mPersenter;

    @BindView(R.id.recycle_view)
    XRecyclerView recycle_view;
    @BindView(R.id.tv_money)
    TextView tv_money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profit);

        mPersenter = new ProfitPersenter(this,this);

    }

    @Override
    public void init() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)

            findViewById(R.id.view_status).getLayoutParams().height = ScreenUtils.getStatusHeight(this);
        recycle_view.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initCurrentDay(String totalMoney) {

    }

    @Override
    public void initViews(int state, String money) {
        tv_money.setText(money);
    }

    @Override
    public void initListView(List<ProfitBean> list) {
        if (adapter == null){
            adapter = new ProfitAdapter(this,list);
            recycle_view.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }
}
