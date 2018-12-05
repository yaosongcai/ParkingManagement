package com.tcc.parkingmanagement.home.model.bean;

/**
 * Created by admin on 2018/8/18 14:03
 * 邮箱：yaosongcai@ujifu.com
 */

public class ParkInfoBean {

    /** 总车位 */
    private String totalPark;

    /** 已停车位 */
    private String parkingSpace;

    /** 剩余车位 */
    private String residualPark;

    public String getTotalPark() {
        return totalPark;
    }

    public void setTotalPark(String totalPark) {
        this.totalPark = totalPark;
    }

    public String getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(String parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public String getResidualPark() {
        return residualPark;
    }

    public void setResidualPark(String residualPark) {
        this.residualPark = residualPark;
    }
}
