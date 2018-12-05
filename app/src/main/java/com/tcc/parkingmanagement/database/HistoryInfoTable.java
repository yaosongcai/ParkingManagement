package com.tcc.parkingmanagement.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 项目名称：    com.cpz.ys.model.bean
 * 类描述        历史数据表（出库数据）
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/8/21
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/8/21
 * 修改备注：
 */
@Entity
public class HistoryInfoTable {

    @Id
    private Long id;
    /** 入库ID */
    @Property(nameInDb = "WAREHOUSING_ID")
    private String warehousingId;

    /** 车牌号 */
    @Property(nameInDb = "PLATE_NO")
    private String plateNo;

    /** 入库时间 */
    @Property(nameInDb = "WAREHOUSING_DATE")
    private String warehousingTime;

    /** 出库时间 */
    @Property(nameInDb = "OUT_TIME")
    private String outTime;

    /** 出库时间（年月日）用于索引 */
    @Property(nameInDb = "OUT_TIMES")
    private String outTimes;

    /**  总金额 */
    @Property(nameInDb = "TOTAL_AMOUT")
    private String totalAmout;

    /** 总时长 */
    @Property(nameInDb = "TOTAL_DATE")
    private String totalTime;

    /** 是否是月票车 */
    @Property(nameInDb = "MONTHLY_TICKET")
    private String isMonthlyTicket;

    /** 停车场id */
    @Property(nameInDb = "PARKMANAGER_ID")
    private String parkManagerId;

    /** 停车场名称 */
    @Property(nameInDb = "PARK_NAME")
    private String parkName;

    @Generated(hash = 949967353)
    public HistoryInfoTable(Long id, String warehousingId, String plateNo,
            String warehousingTime, String outTime, String outTimes,
            String totalAmout, String totalTime, String isMonthlyTicket,
            String parkManagerId, String parkName) {
        this.id = id;
        this.warehousingId = warehousingId;
        this.plateNo = plateNo;
        this.warehousingTime = warehousingTime;
        this.outTime = outTime;
        this.outTimes = outTimes;
        this.totalAmout = totalAmout;
        this.totalTime = totalTime;
        this.isMonthlyTicket = isMonthlyTicket;
        this.parkManagerId = parkManagerId;
        this.parkName = parkName;
    }

    @Generated(hash = 1713194820)
    public HistoryInfoTable() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWarehousingId() {
        return warehousingId;
    }

    public void setWarehousingId(String warehousingId) {
        this.warehousingId = warehousingId;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getWarehousingTime() {
        return warehousingTime;
    }

    public void setWarehousingTime(String warehousingTime) {
        this.warehousingTime = warehousingTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getTotalAmout() {
        return totalAmout;
    }

    public void setTotalAmout(String totalAmout) {
        this.totalAmout = totalAmout;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getIsMonthlyTicket() {
        return isMonthlyTicket;
    }

    public void setIsMonthlyTicket(String isMonthlyTicket) {
        this.isMonthlyTicket = isMonthlyTicket;
    }

    public String getParkManagerId() {
        return parkManagerId;
    }

    public void setParkManagerId(String parkManagerId) {
        this.parkManagerId = parkManagerId;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getOutTimes() {
        return this.outTimes;
    }

    public void setOutTimes(String outTimes) {
        this.outTimes = outTimes;
    }
}
