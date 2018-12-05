package com.tcc.parkingmanagement.data.contracts;

import com.tcc.parkingmanagement.base.basePersenter.BasePersenter;
import com.tcc.parkingmanagement.base.baseView.BaseView;
import com.tcc.parkingmanagement.data.model.bean.ProfitBean;
import com.tcc.parkingmanagement.data.model.manager.ProfitService;

import java.util.List;

/**
 * 项目名称：    com.tcc.parkingmanagement.data.contracts
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/8/22
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/8/22
 * 修改备注：
 */
public interface ProfitContract {

    public interface Views extends BaseView{

        /**
         *
         * 初始化
         * @author ysc
         * @time 2018/8/24 18:04
         */
        void init();

        /**
         * 总金额
         * @param totalMoney
         * @author ysc
         * @time 2018/8/24 17:04
         */
        void initCurrentDay(String totalMoney);

        /**
         * 周期性收益视图
         * @param state     0-今日收益  1-最近一周收益    2-最近一个月收益
         * @param money
         * @author ysc
         * @time 2018/8/24 17:06
         */
        void initViews(int state, String money);

        /**
         * 初始化数据列表
         * @param list
         * @author ysc
         * @time 2018/8/24 17:01
         */
        void initListView(List<ProfitBean> list);

    }

    public interface Persenter extends BasePersenter{

        /**
         *
         * 查询今日总收益
         * @author ysc
         * @time 2018/8/24 17:05
         */
        void initCurrentMoney();

        /**
         *
         * 查询当前数据
         * @param type      统计周期
         * @author ysc
         * @time 2018/8/24 17:05
         */
        void initDatas(@ProfitService.Duration int type);

        /**
         *
         * 分页查询
         * @param state     0-普通加载  1-下拉刷新  2-上拉加载更多
         * @param page      页
         * @param rows      每页条数
         * @author ysc
         * @time 2018/8/24 17:08
         */
        void initDatas(int page, int rows, int state);
    }
}
