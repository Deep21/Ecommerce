package dw.fdb.com.fdbapp.fragments.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import dw.fdb.com.fdbapp.R;
import dw.fdb.com.fdbapp.activitie.MainActivity;
import dw.fdb.com.fdbapp.adapter.CustomListAdapter;
import dw.fdb.com.fdbapp.fragments.BaseListFragment;
import dw.fdb.com.fdbapp.model.Item;
import dw.fdb.com.fdbapp.model.cart.CartModel;
import dw.fdb.com.fdbapp.model.cart.CartProductList;
import dw.fdb.com.fdbapp.model.cart.Payment;

public class PaymentListFragment extends BaseListFragment {

    public static final String TAG = "PaymentListFragment";


    public static PaymentListFragment newInstance() {
        PaymentListFragment paymentListFragment = new PaymentListFragment();
        return paymentListFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payment_list_fragment, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MainActivity mainActivity = (MainActivity)getActivity();
        CartModel cartModel = (CartModel)mainActivity.getCartModel();
        List<Item> items = new ArrayList<Item>();
        if(cartModel !=null){
            List<CartProductList> cartProductList = cartModel.getProductList();
            for(CartProductList cart : cartProductList){
                Payment payment = new Payment();
                payment.setDescription(cart.getDescription());
                payment.setLibelle_produit(cart.getProduit());
                payment.setPrix_ttc(cart.getPriceTtc());
                payment.setQty(cart.getQuantity());
                payment.setUrl(cart.getUrlImage());
                items.add(payment);
            }
        }
        CustomListAdapter customListAdapter = new CustomListAdapter(getActivity(), items);
        setListAdapter(customListAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }


}
