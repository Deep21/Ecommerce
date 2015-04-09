package dw.fdb.com.fdbapp.fragments;

import android.support.v4.app.Fragment;

public interface FragmentListner {

    void replaceFragment(Fragment f, String tag);

    void removeFragment(Fragment f);

}
