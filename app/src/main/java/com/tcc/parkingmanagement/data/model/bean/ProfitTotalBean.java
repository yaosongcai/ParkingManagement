package com.tcc.parkingmanagement.data.model.bean;

import java.util.List;

/**
 * 项目名称：    com.tcc.parkingmanagement.data.model.bean
 * 类描述        统计数据bean
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/8/24
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/8/24
 * 修改备注：
 */
public class ProfitTotalBean {

    private String totalMoney;

    private List<ProfitBean> list;

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public List<ProfitBean> getList() {
        return list;
    }

    public void setList(List<ProfitBean> list) {
        this.list = list;
    }
}
