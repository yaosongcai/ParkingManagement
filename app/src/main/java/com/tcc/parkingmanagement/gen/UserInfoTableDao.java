package com.tcc.parkingmanagement.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.tcc.parkingmanagement.database.UserInfoTable;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER_INFO_TABLE".
*/
public class UserInfoTableDao extends AbstractDao<UserInfoTable, Long> {

    public static final String TABLENAME = "USER_INFO_TABLE";

    /**
     * Properties of entity UserInfoTable.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property UserId = new Property(1, String.class, "userId", false, "USER_ID");
        public final static Property UserName = new Property(2, String.class, "userName", false, "USER_NAME");
        public final static Property Address = new Property(3, String.class, "address", false, "ADDRESS");
        public final static Property TelPhone = new Property(4, String.class, "telPhone", false, "TEL_PHONE");
        public final static Property ParkName = new Property(5, String.class, "parkName", false, "PARK_NAME");
        public final static Property ParkManagerId = new Property(6, String.class, "parkManagerId", false, "PARKMANAGER_ID");
        public final static Property WxPayCode = new Property(7, String.class, "wxPayCode", false, "WX_PAY_CODE");
        public final static Property ZfbPayCode = new Property(8, String.class, "zfbPayCode", false, "ZFB_PAY_CODE");
    }


    public UserInfoTableDao(DaoConfig config) {
        super(config);
    }
    
    public UserInfoTableDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_INFO_TABLE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"USER_ID\" TEXT," + // 1: userId
                "\"USER_NAME\" TEXT," + // 2: userName
                "\"ADDRESS\" TEXT," + // 3: address
                "\"TEL_PHONE\" TEXT," + // 4: telPhone
                "\"PARK_NAME\" TEXT," + // 5: parkName
                "\"PARKMANAGER_ID\" TEXT," + // 6: parkManagerId
                "\"WX_PAY_CODE\" TEXT," + // 7: wxPayCode
                "\"ZFB_PAY_CODE\" TEXT);"); // 8: zfbPayCode
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_INFO_TABLE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserInfoTable entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(2, userId);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(3, userName);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(4, address);
        }
 
        String telPhone = entity.getTelPhone();
        if (telPhone != null) {
            stmt.bindString(5, telPhone);
        }
 
        String parkName = entity.getParkName();
        if (parkName != null) {
            stmt.bindString(6, parkName);
        }
 
        String parkManagerId = entity.getParkManagerId();
        if (parkManagerId != null) {
            stmt.bindString(7, parkManagerId);
        }
 
        String wxPayCode = entity.getWxPayCode();
        if (wxPayCode != null) {
            stmt.bindString(8, wxPayCode);
        }
 
        String zfbPayCode = entity.getZfbPayCode();
        if (zfbPayCode != null) {
            stmt.bindString(9, zfbPayCode);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserInfoTable entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(2, userId);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(3, userName);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(4, address);
        }
 
        String telPhone = entity.getTelPhone();
        if (telPhone != null) {
            stmt.bindString(5, telPhone);
        }
 
        String parkName = entity.getParkName();
        if (parkName != null) {
            stmt.bindString(6, parkName);
        }
 
        String parkManagerId = entity.getParkManagerId();
        if (parkManagerId != null) {
            stmt.bindString(7, parkManagerId);
        }
 
        String wxPayCode = entity.getWxPayCode();
        if (wxPayCode != null) {
            stmt.bindString(8, wxPayCode);
        }
 
        String zfbPayCode = entity.getZfbPayCode();
        if (zfbPayCode != null) {
            stmt.bindString(9, zfbPayCode);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public UserInfoTable readEntity(Cursor cursor, int offset) {
        UserInfoTable entity = new UserInfoTable( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // userId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // userName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // address
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // telPhone
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // parkName
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // parkManagerId
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // wxPayCode
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // zfbPayCode
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserInfoTable entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUserId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setUserName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setAddress(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTelPhone(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setParkName(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setParkManagerId(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setWxPayCode(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setZfbPayCode(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UserInfoTable entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UserInfoTable entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserInfoTable entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
