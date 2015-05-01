package dw.fdb.com.fdbapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.CursorLoader;

import dw.fdb.com.fdbapp.activitie.MyApplication;
import dw.fdb.com.fdbapp.db.model.DaoSession;


public class MyCursorLoader extends CursorLoader {
    SQLiteDatabase db;
    DaoSession daoSession;


    public MyCursorLoader(Context context) {
        super(context);
        daoSession = MyApplication.getDaoSession();
        db = MyApplication.getDaoSession().getDatabase();
    }

    @Override
    public Cursor loadInBackground() {
        return db.query(daoSession.getDBCartDao().getTablename(), daoSession.getDBCartDao().getAllColumns(), null, null, null, null, null);
    }

}
