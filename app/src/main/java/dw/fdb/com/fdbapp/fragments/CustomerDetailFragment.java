package dw.fdb.com.fdbapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import dw.fdb.com.fdbapp.R;
import dw.fdb.com.fdbapp.model.Token;

public class CustomerDetailFragment extends BaseFragment {
    public static final String TAG = "CustomerDetailFragment";
    public static final int TAG_ID = 2;
    @InjectView(R.id.idententifiez_vous)
    TextView h1;

    public static CustomerDetailFragment getInstance() {
        CustomerDetailFragment connexionFragment = new CustomerDetailFragment();
        return connexionFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_account_fragment, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        h1.setText("Donn�es personelles");
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

    private void perform_request(Token token) {

    }


}
