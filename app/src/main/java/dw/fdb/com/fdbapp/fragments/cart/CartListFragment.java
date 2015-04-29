package dw.fdb.com.fdbapp.fragments.cart;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import dw.fdb.com.fdbapp.R;
import dw.fdb.com.fdbapp.db.DaoSession;
import dw.fdb.com.fdbapp.fragments.BaseListFragment;
import dw.fdb.com.fdbapp.fragments.FragmentListner;
import dw.fdb.com.fdbapp.listner.BaseRequestLisner;
import dw.fdb.com.fdbapp.model.AuthTokenException;
import dw.fdb.com.fdbapp.model.CartProduct;
import dw.fdb.com.fdbapp.model.Product;
import dw.fdb.com.fdbapp.model.Token;
import dw.fdb.com.fdbapp.request.CartAddProductPostRequest;
import dw.fdb.com.fdbapp.request.CartGetProductRequest;
import dw.fdb.com.fdbapp.request.OauthGetAccesTokenRequest;

public class CartListFragment extends BaseListFragment {

    public static final String TAG = "CartListFragment";

    private static final int URL_LOADER = 0;
    long countItems;
    DaoSession daoSession;

    private FragmentListner fragmentSwitcherListner;
    private LoaderManager.LoaderCallbacks<Cursor> mCallbacks;

    public static CartListFragment newInstance() {
        CartListFragment cartListFragment = new CartListFragment();
        return cartListFragment;
    }

    //simule la lecture de la base de donn�e
    private List<CartProduct> getLocalCart() {
        List<CartProduct> cartProductList = new ArrayList<CartProduct>();
        for (int i = 0; i < 5; i++) {
            CartProduct cartProduct = new CartProduct();
            cartProduct.setIdAddressDelivery(0);
            cartProduct.setIdProductAttribute(18);
            cartProduct.setIdProduct(1);
            cartProduct.setIdCart(23);
            cartProduct.setIdShop(1);
            cartProduct.setQuantity(5);
            cartProductList.add(cartProduct);
        }

        return cartProductList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_list_fragment, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    public void productRequest() {
//		if (getArguments() != null) {
        SharedPreferences preferences = getActivity().getSharedPreferences("customer", Context.MODE_PRIVATE);
        int id_cart = preferences.getInt("id_cart", 0);
        CartGetProductRequest cartGetProductRequest = new CartGetProductRequest(id_cart, preferences.getString(Token.BEARER_TOKEN, ""));
        //getSpiceManager().execute(cartGetProductRequest, new CartProductRequestListner());
        //}
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        fragmentSwitcherListner = (FragmentListner) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // productRequest();
       // addProductToCartById();
        /*daoSession = MyApplication.getDaoSession();
		countItems = daoSession.getCartDao().count();*/

    }


    private void addProductToCartById() {
        SharedPreferences preferences = getActivity().getSharedPreferences("customer", Context.MODE_PRIVATE);
        List<CartProduct> list = getLocalCart();
        for (CartProduct cartProduct : list) {
            CartAddProductPostRequest addProductPostRequest = new CartAddProductPostRequest(cartProduct, "up", preferences.getString(Token.BEARER_TOKEN, ""));
            getSpiceManager().execute(addProductPostRequest, new CartProductSaveRequestListner());
        }


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }


    public class CartProductSaveRequestListner extends BaseRequestLisner<CartProduct> {
        @Override
        public void onInvalidToken(AuthTokenException authTokenException) {
            super.onInvalidToken(authTokenException);
        }

        @Override
        public void onExpiredToken(AuthTokenException authTokenException) {
            super.onExpiredToken(authTokenException);
        }

        @Override
        public void onRequestSuccess(CartProduct t) {
            super.onRequestSuccess(null);
        }


    }

    public class CartProductRequestListner extends BaseRequestLisner<Product.ProductList> {

        @Override
        public void onInvalidToken(AuthTokenException spiceException) {
        }


        @Override
        public void onExpiredToken(AuthTokenException authTokenException) {
            super.onExpiredToken(authTokenException);
            SharedPreferences preferences = getActivity().getSharedPreferences("customer", Context.MODE_PRIVATE);
            Token token = new Token();
            token.setClientId("testclient");
            token.setClientSecret("clientsecret");
            token.setGrantType("refresh_token");
            String refreshToken = preferences.getString(Token.OAUTH_REFRESH_TOKEN, "");
            token.setRefreshToken("998c4b82ce81dbe58150316e79568e185867ff6d");
            OauthGetAccesTokenRequest accesTokenRequest = new OauthGetAccesTokenRequest(token, 1);

            getSpiceManager().execute(accesTokenRequest, new BaseRequestLisner<Token>() {

                @Override
                public void onRequestSuccess(Token t) {
                    System.out.println(t);
                    //super.onRequestSuccess(t);
                }

                @Override
                public void onInvalidToken(AuthTokenException authTokenException) {
                    //super.onInvalidToken(authTokenException);
                }
            });

        }
    }


}
