package com.example.rd.baomingxitong.GreenDao;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import com.example.rd.baomingxitong.entity.LiaoTian.LTMsg;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LTMSG".
*/
public class LTMsgDao extends AbstractDao<LTMsg, Long> {

    public static final String TABLENAME = "LTMSG";

    /**
     * Properties of entity LTMsg.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Sender = new Property(1, String.class, "sender", false, "SENDER");
        public final static Property XiangmuId = new Property(2, Long.class, "xiangmuId", false, "XIANGMU_ID");
        public final static Property Msg = new Property(3, String.class, "msg", false, "MSG");
        public final static Property Time = new Property(4, String.class, "time", false, "TIME");
        public final static Property Xingming = new Property(5, String.class, "xingming", false, "XINGMING");
    }

    private Query<LTMsg> lTXiangmu_MsgListQuery;

    public LTMsgDao(DaoConfig config) {
        super(config);
    }
    
    public LTMsgDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LTMSG\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"SENDER\" TEXT," + // 1: sender
                "\"XIANGMU_ID\" INTEGER," + // 2: xiangmuId
                "\"MSG\" TEXT," + // 3: msg
                "\"TIME\" TEXT," + // 4: time
                "\"XINGMING\" TEXT);"); // 5: xingming
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LTMSG\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LTMsg entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String sender = entity.getSender();
        if (sender != null) {
            stmt.bindString(2, sender);
        }
 
        Long xiangmuId = entity.getXiangmuId();
        if (xiangmuId != null) {
            stmt.bindLong(3, xiangmuId);
        }
 
        String msg = entity.getMsg();
        if (msg != null) {
            stmt.bindString(4, msg);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(5, time);
        }
 
        String xingming = entity.getXingming();
        if (xingming != null) {
            stmt.bindString(6, xingming);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LTMsg entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String sender = entity.getSender();
        if (sender != null) {
            stmt.bindString(2, sender);
        }
 
        Long xiangmuId = entity.getXiangmuId();
        if (xiangmuId != null) {
            stmt.bindLong(3, xiangmuId);
        }
 
        String msg = entity.getMsg();
        if (msg != null) {
            stmt.bindString(4, msg);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(5, time);
        }
 
        String xingming = entity.getXingming();
        if (xingming != null) {
            stmt.bindString(6, xingming);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public LTMsg readEntity(Cursor cursor, int offset) {
        LTMsg entity = new LTMsg( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // sender
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // xiangmuId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // msg
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // time
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // xingming
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LTMsg entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setSender(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setXiangmuId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setMsg(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTime(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setXingming(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(LTMsg entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(LTMsg entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(LTMsg entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "MsgList" to-many relationship of LTXiangmu. */
    public List<LTMsg> _queryLTXiangmu_MsgList(Long xiangmuId) {
        synchronized (this) {
            if (lTXiangmu_MsgListQuery == null) {
                QueryBuilder<LTMsg> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.XiangmuId.eq(null));
                lTXiangmu_MsgListQuery = queryBuilder.build();
            }
        }
        Query<LTMsg> query = lTXiangmu_MsgListQuery.forCurrentThread();
        query.setParameter(0, xiangmuId);
        return query.list();
    }

}
