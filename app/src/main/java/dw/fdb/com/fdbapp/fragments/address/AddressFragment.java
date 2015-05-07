package dw.fdb.com.fdbapp.fragments.address;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import butterknife.ButterKnife;
import dw.fdb.com.fdbapp.R;
import dw.fdb.com.fdbapp.adapter.CustomListAdapter;
import dw.fdb.com.fdbapp.fragments.BaseListFragment;
import dw.fdb.com.fdbapp.model.Token;
import dw.fdb.com.fdbapp.model.address.AddressInvoice;
import dw.fdb.com.fdbapp.request.AddressGetRequest;

public class AddressFragment extends BaseListFragment {

    public static final String TAG = "AddressFragment";
    public static final int TAG_ID = 1;
    CustomListAdapter customListAdapter;

    public static AddressFragment getInstance() {
        AddressFragment connexionFragment = new AddressFragment();
        return connexionFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        RelativeLayout relativeLayout = (RelativeLayout)view.findViewById(R.id.mLlayout1);
        relativeLayout.setVisibility(View.GONE);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        customListAdapter = new CustomListAdapter(getActivity(), null);
        perform_request();
//        customListAdapter.setCustomItems(cartProduct.getProductList());
//        setListAdapter(customListAdapter);
//        customListAdapter.setOnClickListner(CartListFragment.this);
//        customListAdapter.setCustomItems(cartProduct.getProductList());
//        customListAdapter.notifyDataSetChanged();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getListAdapter() == null) {

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    private void perform_request() {
        AddressGetRequest addressGetRequest = new AddressGetRequest(1);
        getSpiceManager().execute(addressGetRequest, new GetAddressRequest());
    }

    private void storeInSharedPref(Token token) {
    }

    class GetAddressRequest implements RequestListener<AddressInvoice.AddressList> {

        @Override
        public void onRequestFailure(SpiceException e) {

        }

        @Override
        public void onRequestSuccess(AddressInvoice.AddressList address) {
            customListAdapter.setCustomItems(address);
            setListAdapter(customListAdapter);
        }
    }

}
