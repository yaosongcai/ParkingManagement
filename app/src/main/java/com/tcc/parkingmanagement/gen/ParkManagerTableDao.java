package com.tcc.parkingmanagement.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.tcc.parkingmanagement.database.ParkManagerTable;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PARK_MANAGER_TABLE".
*/
public class ParkManagerTableDao extends AbstractDao<ParkManagerTable, Long> {

    public static final String TABLENAME = "PARK_MANAGER_TABLE";

    /**
     * Properties of entity ParkManagerTable.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ParkManagerId = new Property(1, String.class, "parkManagerId", false, "PARKMANAGER_ID");
        public final static Property ParkName = new Property(2, String.class, "parkName", false, "PARK_NAME");
        public final static Property TotalParkSpace = new Property(3, Integer.class, "totalParkSpace", false, "TOTAL_PARK_SPACE");
        public final static Property UsedParkSpace = new Property(4, Integer.class, "usedParkSpace", false, "USED_PARK_SPACE");
        public final static Property Amout = new Property(5, String.class, "amout", false, "AMOUT");
        public final static Property Time = new Property(6, String.class, "time", false, "TIME");
    }


    public ParkManagerTableDao(DaoConfig config) {
        super(config);
    }
    
    public ParkManagerTableDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PARK_MANAGER_TABLE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"PARKMANAGER_ID\" TEXT," + // 1: parkManagerId
                "\"PARK_NAME\" TEXT," + // 2: parkName
                "\"TOTAL_PARK_SPACE\" INTEGER," + // 3: totalParkSpace
                "\"USED_PARK_SPACE\" INTEGER," + // 4: usedParkSpace
                "\"AMOUT\" TEXT," + // 5: amout
                "\"TIME\" TEXT);"); // 6: time
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PARK_MANAGER_TABLE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ParkManagerTable entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String parkManagerId = entity.getParkManagerId();
        if (parkManagerId != null) {
            stmt.bindString(2, parkManagerId);
        }
 
        String parkName = entity.getParkName();
        if (parkName != null) {
            stmt.bindString(3, parkName);
        }
 
        Integer totalParkSpace = entity.getTotalParkSpace();
        if (totalParkSpace != null) {
            stmt.bindLong(4, totalParkSpace);
        }
 
        Integer usedParkSpace = entity.getUsedParkSpace();
        if (usedParkSpace != null) {
            stmt.bindLong(5, usedParkSpace);
        }
 
        String amout = entity.getAmout();
        if (amout != null) {
            stmt.bindString(6, amout);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(7, time);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ParkManagerTable entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String parkManagerId = entity.getParkManagerId();
        if (parkManagerId != null) {
            stmt.bindString(2, parkManagerId);
        }
 
        String parkName = entity.getParkName();
        if (parkName != null) {
            stmt.bindString(3, parkName);
        }
 
        Integer totalParkSpace = entity.getTotalParkSpace();
        if (totalParkSpace != null) {
            stmt.bindLong(4, totalParkSpace);
        }
 
        Integer usedParkSpace = entity.getUsedParkSpace();
        if (usedParkSpace != null) {
            stmt.bindLong(5, usedParkSpace);
        }
 
        String amout = entity.getAmout();
        if (amout != null) {
            stmt.bindString(6, amout);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(7, time);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ParkManagerTable readEntity(Cursor cursor, int offset) {
        ParkManagerTable entity = new ParkManagerTable( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // parkManagerId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // parkName
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // totalParkSpace
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // usedParkSpace
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // amout
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // time
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ParkManagerTable entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setParkManagerId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setParkName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTotalParkSpace(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setUsedParkSpace(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setAmout(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setTime(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ParkManagerTable entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ParkManagerTable entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ParkManagerTable entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
