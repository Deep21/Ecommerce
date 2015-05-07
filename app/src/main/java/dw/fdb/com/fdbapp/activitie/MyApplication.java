package dw.fdb.com.fdbapp.activitie;


import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

import dw.fdb.com.fdbapp.db.model.DaoMaster;
import dw.fdb.com.fdbapp.db.model.DaoSession;


public class MyApplication extends Application {
    public static final String DB_NAME = "prestashop.db";
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

    public static CookieManager getCookieManager(){
        CookieManager cookiemanager = new CookieManager();
        cookiemanager.setCookiePolicy(CookiePolicy.ACCEPT_NONE);
        CookieHandler.setDefault(cookiemanager);
        return cookiemanager;
    }

    //create a DB
    private void setupDB() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DB_NAME, null);
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

}
