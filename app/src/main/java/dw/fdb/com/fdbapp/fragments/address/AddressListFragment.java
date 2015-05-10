package dw.fdb.com.fdbapp.fragments.address;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dw.fdb.com.fdbapp.R;
import dw.fdb.com.fdbapp.adapter.CustomListAdapter;
import dw.fdb.com.fdbapp.fragments.BaseListFragment;
import dw.fdb.com.fdbapp.fragments.FragmentListner;
import dw.fdb.com.fdbapp.fragments.cart.PaymentListFragment;
import dw.fdb.com.fdbapp.model.Item;
import dw.fdb.com.fdbapp.model.address.AddressDelivery;
import dw.fdb.com.fdbapp.model.address.AddressInvoice;
import dw.fdb.com.fdbapp.model.address.AddressModel;
import dw.fdb.com.fdbapp.model.carrier.Carrier;
import dw.fdb.com.fdbapp.request.AddressGetRequest;

public class AddressListFragment extends BaseListFragment implements CustomListAdapter.AdapterOnClickListner{

    public static final String TAG = "AddressListFragment";
    public static final int TAG_ID = 1;
    CustomListAdapter customListAdapter;
    FragmentListner fragmentSwitcherListner;

    public static AddressListFragment getInstance() {
        AddressListFragment connexionFragment = new AddressListFragment();
        return connexionFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_order_fragment, container, false);
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
        getListView().setDivider(null);
        getListView().setDividerHeight(0);
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
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            fragmentSwitcherListner = (FragmentListner) activity;
        } catch (ClassCastException castException) {

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


    @OnClick(R.id.next)
    public void next(View view) {
        PaymentListFragment paymentListFragment = PaymentListFragment.newInstance();
        fragmentSwitcherListner.replaceFragment(paymentListFragment, PaymentListFragment.TAG);

    }

    @Override
    public void buttonClicked(View v, int i) {
        switch (v.getId()){
            case R.id.edit_address_dellivery:
                System.out.println(v.getTag());
                System.out.println(v.getId());
                System.out.println(i);
                System.out.println("livraison");
                break;

            case R.id.remove_address_dellivery:
                System.out.println(v.getTag());
                System.out.println(v.getId());
                System.out.println(i);
                System.out.println("livraison");
                break;

            case R.id.edit_address_invoice:
                System.out.println(v.getTag());
                System.out.println(v.getId());
                System.out.println(i);
                System.out.println("facturation");
                break;

            case R.id.remove_address_invoice:
                System.out.println(v.getTag());
                System.out.println(v.getId());
                System.out.println(i);
                System.out.println("facturation");
                break;
        }

    }

    class GetAddressRequest implements RequestListener<AddressModel> {

        @Override
        public void onRequestFailure(SpiceException e) {

        }

        @Override
        public void onRequestSuccess(AddressModel address) {
            AddressDelivery addressDelivery = address.getAddress_delivery();
            List<Carrier> carrier = address.getCarrier();
            AddressInvoice addressInvoice = address.getAddressInvoice();
            List<Item> addressItem = new ArrayList<Item>();
            addressItem.add(addressDelivery);
            addressItem.add(addressInvoice);
            for(Carrier c: carrier){
                addressItem.add(c);
            }
            customListAdapter.setOnClickListner(AddressListFragment.this);
            customListAdapter.setCustomItems(addressItem);
            setListAdapter(customListAdapter);
        }
    }

}
