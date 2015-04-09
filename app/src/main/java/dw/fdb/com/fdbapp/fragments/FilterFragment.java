package dw.fdb.com.fdbapp.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dw.fdb.com.fdbapp.R;

public class FilterFragment extends BaseFragment {

    public static final String TAG = "FilterFragment";

    public static FilterFragment getInstance() {
        FilterFragment filterFragment = new FilterFragment();
        return filterFragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @OnClick(R.id.valider)
    public void filter(View v) {
        hideFragment();
    }

    @OnClick(R.id.brand)
    public void brand(View v) {
        showEditDialog();
    }

    private void showEditDialog() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        BrandDialog editNameDialog = new BrandDialog();
        editNameDialog.show(fm, "fragment_edit_name");
    }

    void hideFragment() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.beginTransaction().hide(this).commit();
        fm.popBackStack();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_filter_fragment,
                container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
