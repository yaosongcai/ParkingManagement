package com.tcc.parkingmanagement.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 项目名称：    com.cpz.ys.model.bean
 * 类描述        停车场信息表
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/8/21
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/8/21
 * 修改备注：
 */
@Entity
public class ParkManagerTable {

    @Id
    private Long id;

    /** 停车场id */
    @Property(nameInDb = "PARKMANAGER_ID")
    private String parkManagerId;

    /** 停车场名称 */
    @Property(nameInDb = "PARK_NAME")
    private String parkName;

    /** 总车位数量 */
    @Property(nameInDb = "TOTAL_PARK_SPACE")
    private Integer totalParkSpace;

    /** 当前已使用车位数量 */
    @Property(nameInDb = "USED_PARK_SPACE")
    private Integer usedParkSpace;

    /** 价格 */
    @Property(nameInDb = "AMOUT")
    private String amout;

    /** 时长(公式为 价格/时长) */
    @Property(nameInDb = "TIME")
    private String time;

    @Generated(hash = 308894366)
    public ParkManagerTable(Long id, String parkManagerId, String parkName,
            Integer totalParkSpace, Integer usedParkSpace, String amout,
            String time) {
        this.id = id;
        this.parkManagerId = parkManagerId;
        this.parkName = parkName;
        this.totalParkSpace = totalParkSpace;
        this.usedParkSpace = usedParkSpace;
        this.amout = amout;
        this.time = time;
    }

    @Generated(hash = 1567498427)
    public ParkManagerTable() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getTotalParkSpace() {
        return totalParkSpace;
    }

    public void setTotalParkSpace(Integer totalParkSpace) {
        this.totalParkSpace = totalParkSpace;
    }

    public Integer getUsedParkSpace() {
        return usedParkSpace;
    }

    public void setUsedParkSpace(Integer usedParkSpace) {
        this.usedParkSpace = usedParkSpace;
    }

    public String getAmout() {
        return amout;
    }

    public void setAmout(String amout) {
        this.amout = amout;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
