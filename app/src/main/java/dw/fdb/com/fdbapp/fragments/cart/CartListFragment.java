package dw.fdb.com.fdbapp.fragments.cart;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devspark.appmsg.AppMsg;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.Iterator;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import dw.fdb.com.fdbapp.R;
import dw.fdb.com.fdbapp.activitie.MyApplication;
import dw.fdb.com.fdbapp.adapter.CustomListAdapter;
import dw.fdb.com.fdbapp.db.model.DBCart;
import dw.fdb.com.fdbapp.db.model.DaoSession;
import dw.fdb.com.fdbapp.fragments.BaseListFragment;
import dw.fdb.com.fdbapp.fragments.FragmentListner;
import dw.fdb.com.fdbapp.fragments.address.AddressFragment;
import dw.fdb.com.fdbapp.listner.BaseRequestLisner;
import dw.fdb.com.fdbapp.model.AuthTokenException;
import dw.fdb.com.fdbapp.model.Token;
import dw.fdb.com.fdbapp.model.cart.Cart;
import dw.fdb.com.fdbapp.model.cart.CartModel;
import dw.fdb.com.fdbapp.model.cart.CartProduct;
import dw.fdb.com.fdbapp.model.cart.CartProductList;
import dw.fdb.com.fdbapp.model.product.Product;
import dw.fdb.com.fdbapp.request.CartAddProductPostRequest;
import dw.fdb.com.fdbapp.request.CartDeleteRequest;
import dw.fdb.com.fdbapp.request.CartEditProductRequest;
import dw.fdb.com.fdbapp.request.CartGetLastNoneOrderedCartRequest;
import dw.fdb.com.fdbapp.request.CartGetProductRequest;
import dw.fdb.com.fdbapp.request.OauthGetAccesTokenRequest;

public class CartListFragment extends BaseListFragment implements CustomListAdapter.AdapterOnClickListner {

    public static final String TAG = "CartListFragment";

    DaoSession daoSession;
    CustomListAdapter customListAdapter;
    @InjectView(R.id.qte)
    TextView nb_product;

    private FragmentListner fragmentSwitcherListner;


    public static CartListFragment newInstance() {
        CartListFragment cartListFragment = new CartListFragment();
        return cartListFragment;
    }

    @OnClick(R.id.set_order)
    public void set_order(View view) {
        AddressFragment addressFragment = AddressFragment.getInstance();
        fragmentSwitcherListner.replaceFragment(addressFragment, AddressFragment.TAG);
        if (customListAdapter.getCustomItems() != null) {

            //Need Auth

            //Need Auth


            //Auth OK
            //use merge customer cart id

            //Auth OK

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_list_fragment, container, false);
        ButterKnife.inject(this, view);
        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        fragmentSwitcherListner = (FragmentListner) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        daoSession = MyApplication.getDaoSession();
        customListAdapter = new CustomListAdapter(getActivity(), null);
        List<DBCart> cart = daoSession.getDBCartDao().loadAll();
        Iterator<DBCart> c = cart.iterator();
        int id_cart = 0;
        while (c.hasNext()) {
            DBCart dbCart = c.next();
            id_cart = dbCart.getId_cart();
        }
        getProductCartById(id_cart);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void getProductCartById(int id_cart) {
        CartGetProductRequest cartGetProductRequest = new CartGetProductRequest(id_cart, null);
        getSpiceManager().execute(cartGetProductRequest, new CartGetRequestListner());
    }

    private void addProductToCartById(CartProductList cartProductList, String param) {
        int id_product_attribut = cartProductList.getIdProductAttribute();
        int id_product = cartProductList.getIdProduct();
        final int id_cart = cartProductList.getIdCart();
        int id_address_delivery = cartProductList.getId_address_delivery();

        CartProduct cartProduct = new CartProduct();
        cartProduct.setIdCart(id_cart);
        cartProduct.setIdAddressDelivery(id_address_delivery);
        cartProduct.setIdProduct(id_product);
        cartProduct.setIdProductAttribute(id_product_attribut);
        cartProduct.setQuantity(1);

        CartAddProductPostRequest cartAddProductPostRequest = new CartAddProductPostRequest(id_cart, cartProduct, param, null);
        getSpiceManager().execute(cartAddProductPostRequest, new RequestListener<CartProduct>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {

            }

            @Override
            public void onRequestSuccess(CartProduct cartProduct) {
                try {

                    getProductCartById(id_cart);

                } catch (NullPointerException e) {

                }
            }
        });
    }

    private void deleteProductCartById(final int id_cart, int id_product, int id_product_attribute, int id_address) {
        CartDeleteRequest cartDeleteRequest = new CartDeleteRequest(id_cart, id_product, id_product_attribute, id_address);
        getSpiceManager().execute(cartDeleteRequest, new RequestListener<Cart>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                System.out.println(spiceException);
            }

            @Override
            public void onRequestSuccess(Cart cart) {
                AppMsg.makeText(getActivity(), "Votre produit à bien été retiré du panier", AppMsg.STYLE_CONFIRM).show();
                getProductCartById(id_cart);
            }
        });
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

    public void mergeCartWithCustomer() {
        String token = "0c6173ebe084660d7899fb2f2ae5859d0710c67d";
        int customer = 2;
        CartGetLastNoneOrderedCartRequest cartGetLastNoneOrderedCartRequest = new CartGetLastNoneOrderedCartRequest(customer, token);
        getSpiceManager().execute(cartGetLastNoneOrderedCartRequest, new RequestListener<Cart>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {

            }

            @Override
            public void onRequestSuccess(Cart cart) {

                System.out.println(cart.getId_cart());
            }
        });
    }

    public void editProductToCartById(CartProductList cartProductList) {
        int id_product_attribut = cartProductList.getIdProductAttribute();
        int id_product = cartProductList.getIdProduct();
        final int id_cart = cartProductList.getIdCart();
        int id_address_delivery = cartProductList.getId_address_delivery();
        CartProduct cartProduct = new CartProduct();
        cartProduct.setIdAddressDelivery(id_address_delivery);
        cartProduct.setIdProduct(id_product);
        cartProduct.setIdProductAttribute(id_product_attribut);
        cartProduct.setQuantity(1);
        CartEditProductRequest cartEditProductRequest = new CartEditProductRequest(id_cart, cartProduct, "down", null);
        getSpiceManager().execute(cartEditProductRequest, new RequestListener<CartProduct>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {

            }

            @Override
            public void onRequestSuccess(CartProduct cart) {
                getProductCartById(id_cart);
            }
        });
    }

    @Override
    public void buttonClicked(View v, int position) {
        CustomListAdapter customListAdapter = (CustomListAdapter) getListAdapter();
        CartProductList cartProductList = (CartProductList) customListAdapter.getItem(position);
        switch (v.getId()) {
            case R.id.increment:
                if (customListAdapter != null)
                    addProductToCartById(cartProductList, "up");
                break;

            case R.id.decrement:
                if (customListAdapter != null)
                    editProductToCartById(cartProductList);
                break;

            case R.id.delete_cart:
                if (customListAdapter != null) {
                    int id_product_attribut = cartProductList.getIdProductAttribute();
                    int id_product = cartProductList.getIdProduct();
                    int id_cart = cartProductList.getIdCart();
                    int id_address_delivery = cartProductList.getId_address_delivery();
                    deleteProductCartById(id_cart, id_product, id_product_attribut, id_address_delivery);
                }

                break;
        }

    }


    public class CartGetRequestListner implements RequestListener<CartModel> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {

        }

        @Override
        public void onRequestSuccess(CartModel cartProduct) {
            try {
                nb_product.setText(String.valueOf(cartProduct.getNb_product()));
                customListAdapter.setCustomItems(cartProduct.getProductList());
                setListAdapter(customListAdapter);
                customListAdapter.setOnClickListner(CartListFragment.this);
                customListAdapter.setCustomItems(cartProduct.getProductList());
                customListAdapter.notifyDataSetChanged();
            } catch (NullPointerException e) {
                System.out.println(e.fillInStackTrace());
            }

        }
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
