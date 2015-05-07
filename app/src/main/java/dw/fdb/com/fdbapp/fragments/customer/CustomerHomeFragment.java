package dw.fdb.com.fdbapp.fragments.customer;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import dw.fdb.com.fdbapp.CustomerHomeMenu;
import dw.fdb.com.fdbapp.R;
import dw.fdb.com.fdbapp.adapter.CustomListAdapter;
import dw.fdb.com.fdbapp.fragments.BaseListFragment;
import dw.fdb.com.fdbapp.fragments.FragmentListner;
import dw.fdb.com.fdbapp.fragments.address.AddressFragment;
import dw.fdb.com.fdbapp.fragments.order.OrderListFragment;
import dw.fdb.com.fdbapp.listner.OauthListner;
import dw.fdb.com.fdbapp.model.Customer;
import dw.fdb.com.fdbapp.model.Item;
import dw.fdb.com.fdbapp.request.CustomerGetContextRequest;

public class CustomerHomeFragment extends BaseListFragment {
    public static final String TAG = "CustomerHomeFragment";
    FragmentListner fragmentSwitcherListner;
    OauthListner listner;

    public static CustomerHomeFragment getInstance() {
        CustomerHomeFragment connexionFragment = new CustomerHomeFragment();
        return connexionFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            fragmentSwitcherListner = (FragmentListner) activity;
            listner = (OauthListner) activity;
        } catch (ClassCastException e) {
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("je suis relanc�");
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        System.out.println("on resume");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        switch (position) {

            case OrderListFragment.TAG_ID:
                fragmentSwitcherListner.replaceFragment(OrderListFragment.newInstance(), null);
                break;

            case AddressFragment.TAG_ID:
                fragmentSwitcherListner.replaceFragment(AddressFragment.getInstance(), null);
                break;

            case CustomerDetailFragment.TAG_ID:
                fragmentSwitcherListner.replaceFragment(CustomerDetailFragment.getInstance(), null);
                break;

            case 3:

                break;
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Item> items = new ArrayList<Item>();

        CustomerHomeMenu customerHomeMenu1 = new CustomerHomeMenu();
        CustomerHomeMenu customerHomeMenu2 = new CustomerHomeMenu();
        CustomerHomeMenu customerHomeMenu3 = new CustomerHomeMenu();
        CustomerHomeMenu customerHomeMenu4 = new CustomerHomeMenu();

        customerHomeMenu1.setImg(R.drawable.pack);
        customerHomeMenu1.setTitle("Commandes effectu�es");
        customerHomeMenu1.setTitle_description("V�rifiez l'�tat de vos commandes");

        customerHomeMenu2.setImg(R.drawable.home);
        customerHomeMenu2.setTitle("Carnet d'adresse");
        customerHomeMenu2.setTitle_description("Enregistrez toutes vos adresses de livraison");

        customerHomeMenu3.setImg(R.drawable.edit);
        customerHomeMenu3.setTitle("Donn�es personnelles");
        customerHomeMenu3.setTitle_description("Editez vos informations personnelles");

        customerHomeMenu4.setImg(R.drawable.data);
        customerHomeMenu4.setTitle("Donn�es d'acc�s");
        customerHomeMenu4.setTitle_description("Modifiez votre adresse e-mail et votre mot de passe");

        items.add(customerHomeMenu1);
        items.add(customerHomeMenu2);
        items.add(customerHomeMenu3);
        items.add(customerHomeMenu4);

        CustomListAdapter customListAdapter = new CustomListAdapter(getActivity(), items);
        setListAdapter(customListAdapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        perform_request();
    }

    private void perform_request() {
        CustomerGetContextRequest contextRequest = new CustomerGetContextRequest(listner.getAccessTokenFromPref());
        getSpiceManager().execute(contextRequest, new CustomerGetDetailRequest());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    public class CustomerGetDetailRequest implements RequestListener<Customer> {

        @Override
        public void onRequestFailure(SpiceException error) {
            System.out.println(error);

        }

        @Override
        public void onRequestSuccess(Customer customer) {


        }

    }


}
