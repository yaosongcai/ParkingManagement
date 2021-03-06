package com.tcc.parkingmanagement.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.tcc.parkingmanagement.database.PlateInfoTable;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PLATE_INFO_TABLE".
*/
public class PlateInfoTableDao extends AbstractDao<PlateInfoTable, Long> {

    public static final String TABLENAME = "PLATE_INFO_TABLE";

    /**
     * Properties of entity PlateInfoTable.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property WarehousingId = new Property(1, String.class, "warehousingId", false, "WAREHOUSING_ID");
        public final static Property PlateNo = new Property(2, String.class, "plateNo", false, "PLATE_NO");
        public final static Property WarehousingTime = new Property(3, String.class, "warehousingTime", false, "WAREHOUSING_DATE");
        public final static Property IsMonthlyTicket = new Property(4, String.class, "isMonthlyTicket", false, "MONTHLY_TICKET");
        public final static Property ParkManagerId = new Property(5, String.class, "parkManagerId", false, "PARKMANAGER_ID");
        public final static Property ParkName = new Property(6, String.class, "parkName", false, "PARK_NAME");
    }


    public PlateInfoTableDao(DaoConfig config) {
        super(config);
    }
    
    public PlateInfoTableDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PLATE_INFO_TABLE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"WAREHOUSING_ID\" TEXT," + // 1: warehousingId
                "\"PLATE_NO\" TEXT," + // 2: plateNo
                "\"WAREHOUSING_DATE\" TEXT," + // 3: warehousingTime
                "\"MONTHLY_TICKET\" TEXT," + // 4: isMonthlyTicket
                "\"PARKMANAGER_ID\" TEXT," + // 5: parkManagerId
                "\"PARK_NAME\" TEXT);"); // 6: parkName
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PLATE_INFO_TABLE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PlateInfoTable entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String warehousingId = entity.getWarehousingId();
        if (warehousingId != null) {
            stmt.bindString(2, warehousingId);
        }
 
        String plateNo = entity.getPlateNo();
        if (plateNo != null) {
            stmt.bindString(3, plateNo);
        }
 
        String warehousingTime = entity.getWarehousingTime();
        if (warehousingTime != null) {
            stmt.bindString(4, warehousingTime);
        }
 
        String isMonthlyTicket = entity.getIsMonthlyTicket();
        if (isMonthlyTicket != null) {
            stmt.bindString(5, isMonthlyTicket);
        }
 
        String parkManagerId = entity.getParkManagerId();
        if (parkManagerId != null) {
            stmt.bindString(6, parkManagerId);
        }
 
        String parkName = entity.getParkName();
        if (parkName != null) {
            stmt.bindString(7, parkName);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PlateInfoTable entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String warehousingId = entity.getWarehousingId();
        if (warehousingId != null) {
            stmt.bindString(2, warehousingId);
        }
 
        String plateNo = entity.getPlateNo();
        if (plateNo != null) {
            stmt.bindString(3, plateNo);
        }
 
        String warehousingTime = entity.getWarehousingTime();
        if (warehousingTime != null) {
            stmt.bindString(4, warehousingTime);
        }
 
        String isMonthlyTicket = entity.getIsMonthlyTicket();
        if (isMonthlyTicket != null) {
            stmt.bindString(5, isMonthlyTicket);
        }
 
        String parkManagerId = entity.getParkManagerId();
        if (parkManagerId != null) {
            stmt.bindString(6, parkManagerId);
        }
 
        String parkName = entity.getParkName();
        if (parkName != null) {
            stmt.bindString(7, parkName);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public PlateInfoTable readEntity(Cursor cursor, int offset) {
        PlateInfoTable entity = new PlateInfoTable( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // warehousingId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // plateNo
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // warehousingTime
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // isMonthlyTicket
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // parkManagerId
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // parkName
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PlateInfoTable entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setWarehousingId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPlateNo(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setWarehousingTime(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setIsMonthlyTicket(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setParkManagerId(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setParkName(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(PlateInfoTable entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(PlateInfoTable entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(PlateInfoTable entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
