package dw.fdb.com.fdbapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.devspark.appmsg.AppMsg;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import dw.fdb.com.fdbapp.R;
import dw.fdb.com.fdbapp.activitie.MyApplication;
import dw.fdb.com.fdbapp.adapter.CartCursorAdapter;
import dw.fdb.com.fdbapp.db.CartDao;
import dw.fdb.com.fdbapp.db.DaoSession;
import dw.fdb.com.fdbapp.model.Cart;
import dw.fdb.com.fdbapp.request.CartGetLastNoneOrderedCartRequest;

public class CartDisplayListFragment extends BaseListFragment {
    public static final String TAG = "CartDisplayListFragment";
    private static final int LOADER_ID = 1;
    DaoSession daoSession;
    CartCursorAdapter cursorAdapter;

    public static CartDisplayListFragment newInstance() {
        CartDisplayListFragment cartListFragment = new CartDisplayListFragment();
        return cartListFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_list_fragment, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("onStop");
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        DaoSession daoSession = MyApplication.getDaoSession();
        CartDao cartDao = daoSession.getCartDao();
        //Cart cart = new Cart(null,"Ipod","Description courte.","Description longue",null,2, Double.valueOf(15.40), null, null);
        //cartDao.insert(cart);
        Vibrator vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(100);
        AppMsg.makeText(getActivity(), "Votre produit a �t� ajout� au panier", AppMsg.STYLE_CONFIRM).show();
        //Toast.makeText(getActivity(), ""+count, Toast.LENGTH_SHORT).show();

//        getActivity().getSupportLoaderManager().initLoader(LOADER_ID, null, mCallbacks);
//        cursorAdapter = new CartCursorAdapter(getActivity(), null, 0);
        setListAdapter(cursorAdapter);
        perform_request();
    }

    public void onEvent(Cart cart) {
        /*MyApplication.getDaoSession().getCartDao().deleteByKey(cart.getId());
		getActivity().getSupportLoaderManager().restartLoader(LOADER_ID, null, mCallbacks);*/
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void perform_request() {
        CartGetLastNoneOrderedCartRequest cartGetLastNoneOrderedCartRequest = new CartGetLastNoneOrderedCartRequest(2);

        getSpiceManager().execute(cartGetLastNoneOrderedCartRequest, new GetCartProductListner());
    }

    public class GetCartProductListner implements RequestListener<Cart> {

        @Override
        public void onRequestFailure(SpiceException e) {

        }

        @Override
        public void onRequestSuccess(Cart c) {
            try {
                System.out.println(c.getId_cart());
            } catch (NullPointerException e) {

            }

        }

    }

}
