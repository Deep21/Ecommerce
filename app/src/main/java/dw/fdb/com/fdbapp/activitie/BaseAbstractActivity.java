package dw.fdb.com.fdbapp.activitie;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.SpiceService;

import dw.fdb.com.fdbapp.RestService;
import roboguice.util.temp.Ln;

public abstract class BaseAbstractActivity extends ActionBarActivity {

    protected MySpice spiceManager = new MySpice(RestService.class);

    public SpiceManager getSpiceManager() {
        return spiceManager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    class MySpice extends SpiceManager {

        public MySpice(Class<? extends SpiceService> spiceServiceClass) {
            super(spiceServiceClass);
            Ln.getConfig().setLoggingLevel(Log.ERROR);
        }

    }
}

