package dw.fdb.com.fdbapp.fragments;

import android.support.v4.app.ListFragment;

import com.octo.android.robospice.SpiceManager;

import dw.fdb.com.fdbapp.RestService;

public class BaseListFragment extends ListFragment {
    protected SpiceManager spiceManager = new SpiceManager(RestService.class);

    public SpiceManager getSpiceManager() {
        return spiceManager;
    }

    @Override
    public void onStart() {
        super.onStart();
        spiceManager.start(getActivity());

    }


    @Override
    public void onStop() {
        if (spiceManager.isStarted()) {
            spiceManager.shouldStop();
        }
        super.onStop();
    }
}
