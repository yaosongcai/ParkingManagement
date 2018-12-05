package com.tcc.parkingmanagement.data.model.manager;

import android.support.annotation.IntDef;

import com.tcc.parkingmanagement.data.model.bean.ProfitTotalBean;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 项目名称：    com.tcc.parkingmanagement.data.model.manager
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/8/22
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/8/22
 * 修改备注：
 */
public interface ProfitService {

    public static final int
            QUERY_DAY = 0x9527,//按天查询
            QUERY_WEEK = 0x9528,//按周查询
            QUERY_MONTH = 0x9529,//按月查询
            QUERY_SEASON = 0x9530,//按季查询
            QUERY_HAFFYEAR = 0x9531,//按半年查询
            QUERY_YEAR = 0x9532;//按年查询

    @IntDef({QUERY_DAY, QUERY_WEEK, QUERY_MONTH, QUERY_SEASON, QUERY_HAFFYEAR, QUERY_YEAR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {
    }

    /**
     * 通过时间段查询总收益
     *
     * @param date 时间段
     * @param page 页
     * @param rows 每页条数
     * @author ysc
     * @time 2018/8/22 16:38
     */
    ProfitTotalBean queryProfitByDate(String date, int page, int rows);

    /**
     * 通过时间段查询总收益
     *
     * @param type 时间段
     * @param page 页
     * @param rows 每页条数
     * @author ysc
     * @time 2018/8/22 16:38
     */
    ProfitTotalBean queryProfitByDate(@Duration int type, int page, int rows);
}
