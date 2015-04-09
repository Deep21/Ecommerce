package dw.fdb.com.fdbapp.activitie;


import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import dw.fdb.com.fdbapp.db.DaoMaster;
import dw.fdb.com.fdbapp.db.DaoSession;


public class MyApplication extends Application {
    public static final String DB_NAME = "my-db.db";
    private static DaoSession daoSession;
    public MyApplication application;

    //return the session of DAO
    public static DaoSession getDaoSession() {
        return daoSession;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        setupDB();
    }

    public MyApplication getApplication() {
        return application;
    }

    //create a DB
    private void setupDB() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "my-db.db", null);
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

}
