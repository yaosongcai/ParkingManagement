package com.tcc.parkingmanagement.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 项目名称：    com.cpz.ys.model.bean
 * 类描述        车辆入库信息表
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/8/21
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/8/21
 * 修改备注：
 */
@Entity
public class PlateInfoTable {

    @Id(autoincrement = true)
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

    /** 是否是月票车 */
    @Property(nameInDb = "MONTHLY_TICKET")
    private String isMonthlyTicket;

    /** 停车场id */
    @Property(nameInDb = "PARKMANAGER_ID")
    private String parkManagerId;

    /** 停车场名称 */
    @Property(nameInDb = "PARK_NAME")
    private String parkName;

    @Generated(hash = 559054683)
    public PlateInfoTable(Long id, String warehousingId, String plateNo,
            String warehousingTime, String isMonthlyTicket, String parkManagerId,
            String parkName) {
        this.id = id;
        this.warehousingId = warehousingId;
        this.plateNo = plateNo;
        this.warehousingTime = warehousingTime;
        this.isMonthlyTicket = isMonthlyTicket;
        this.parkManagerId = parkManagerId;
        this.parkName = parkName;
    }

    @Generated(hash = 1588966082)
    public PlateInfoTable() {
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
        return this.parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }
}
