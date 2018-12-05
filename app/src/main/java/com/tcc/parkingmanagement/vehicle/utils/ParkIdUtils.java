package com.tcc.parkingmanagement.vehicle.utils;

import java.util.UUID;

/**
 * 项目名称：    com.tcc.parkingmanagement.vehicle.utils
 * 类描述        停车场id生成器
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/8/22
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/8/22
 * 修改备注：
 */
public class ParkIdUtils {

    public static String getParkId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    public static String getParkManageId(String parkName,String address){

        return "100005";
    }
}
