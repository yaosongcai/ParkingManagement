package com.tcc.parkingmanagement.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 项目名称：    com.cpz.ys.model.bean
 * 类描述        用户信息表
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/8/21
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/8/21
 * 修改备注：
 */
@Entity
public class UserInfoTable {

    @Id
    private Long id;

    /** 用户ID */
    @Property(nameInDb = "USER_ID")
    private String userId;

    /** 用户姓名 */
    @Property(nameInDb = "USER_NAME")
    private String userName;

    /** 停车场地址 */
    @Property(nameInDb = "ADDRESS")
    private String address;

    /** 联系电话 */
    @Property(nameInDb = "TEL_PHONE")
    private String telPhone;

    /** 停车场名称 */
    @Property(nameInDb = "PARK_NAME")
    private String parkName;

    /** 停车场id */
    @Property(nameInDb = "PARKMANAGER_ID")
    private String parkManagerId;

    /** 微信收款二维码图片地址 */
    @Property(nameInDb = "WX_PAY_CODE")
    private String wxPayCode;

    /** 支付宝收款二维码图片地址 */
    @Property(nameInDb = "ZFB_PAY_CODE")
    private String zfbPayCode;

    @Generated(hash = 2060777890)
    public UserInfoTable(Long id, String userId, String userName, String address,
            String telPhone, String parkName, String parkManagerId,
            String wxPayCode, String zfbPayCode) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.address = address;
        this.telPhone = telPhone;
        this.parkName = parkName;
        this.parkManagerId = parkManagerId;
        this.wxPayCode = wxPayCode;
        this.zfbPayCode = zfbPayCode;
    }

    @Generated(hash = 1354492153)
    public UserInfoTable() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getParkManagerId() {
        return parkManagerId;
    }

    public void setParkManagerId(String parkManagerId) {
        this.parkManagerId = parkManagerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getWxPayCode() {
        return wxPayCode;
    }

    public void setWxPayCode(String wxPayCode) {
        this.wxPayCode = wxPayCode;
    }

    public String getZfbPayCode() {
        return zfbPayCode;
    }

    public void setZfbPayCode(String zfbPayCode) {
        this.zfbPayCode = zfbPayCode;
    }
}
