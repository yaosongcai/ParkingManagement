package com.tcc.parkingmanagement.data.model.bean;

/**
 * 项目名称：    com.tcc.parkingmanagement.data.model.bean
 * 类描述        阶段收益bean
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/8/22
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/8/22
 * 修改备注：
 */
public class ProfitBean {

    //时间
    private String time;

    //总收益
    private String totalAmount;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
