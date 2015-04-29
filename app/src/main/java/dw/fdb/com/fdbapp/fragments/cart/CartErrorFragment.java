package dw.fdb.com.fdbapp.fragments.cart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import dw.fdb.com.fdbapp.R;

public class CartErrorFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_error_fragment, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

}
