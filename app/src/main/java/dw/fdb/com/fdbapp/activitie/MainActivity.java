package dw.fdb.com.fdbapp.activitie;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import de.greenrobot.event.EventBus;
import dw.fdb.com.fdbapp.R;
import dw.fdb.com.fdbapp.fragments.CartListFragment;
import dw.fdb.com.fdbapp.fragments.CategoryListFragment;
import dw.fdb.com.fdbapp.fragments.ConnexionFragment;
import dw.fdb.com.fdbapp.fragments.CustomerHomeFragment;
import dw.fdb.com.fdbapp.fragments.FragmentListner;
import dw.fdb.com.fdbapp.fragments.OrderDetailScrollFragment;
import dw.fdb.com.fdbapp.fragments.OrderListFragment;
import dw.fdb.com.fdbapp.fragments.ProductDetailScrollFragment;
import dw.fdb.com.fdbapp.fragments.ProductListFragment;
import dw.fdb.com.fdbapp.listner.OauthListner;
import dw.fdb.com.fdbapp.model.AuthTokenException;
import dw.fdb.com.fdbapp.model.Token;


public class MainActivity extends BaseAbstractActivity implements CategoryListFragment.Icommunicator, FragmentListner, OauthListner {

    CartListFragment cartListFragment;
    ConnexionFragment connexionFragment;
    CategoryListFragment categoryListFragment;
    ProductListFragment productFragment;
    ProductDetailScrollFragment detailScrollFragment;
    OrderDetailScrollFragment orderScrollFragment;
    OrderListFragment listFragment;
    CustomerHomeFragment customerHomeFragment;
    ProductListFragment productListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        View v = getLayoutInflater().inflate(R.layout.test, null, false);
        getSupportActionBar().setCustomView(v);
        EventBus.getDefault().register(this);
        getSupportActionBar().getCustomView().setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                cartListFragment = (CartListFragment) getSupportFragmentManager().findFragmentByTag(CartListFragment.TAG);
                if (cartListFragment == null) {
                    cartListFragment = CartListFragment.newInstance();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, cartListFragment, CartListFragment.TAG).addToBackStack(null).commit();
                }

            }
        });
        //addOrderListFragment();
        //addHomeCustomerFragment();

       // addConnexionFragment();

        //addConnexionFragment();

        addCategoryFragment();
        //addProductListFragment();
        //addProductDetailFragment();
    }

    public void onEventMainThread(AuthTokenException event) {
        Toast.makeText(this, "Voulez-vous gardez votre session active ?", Toast.LENGTH_SHORT).show();
        //replaceFragment(ConnexionFragment.getInstance(), null);
    }


    private void addCategoryFragment() {
        categoryListFragment = (CategoryListFragment) getSupportFragmentManager().findFragmentByTag(CategoryListFragment.TAG);
        if (categoryListFragment == null) {
            categoryListFragment = CategoryListFragment.newInstance(2);
            getSupportFragmentManager().beginTransaction().add(R.id.frame, categoryListFragment, CategoryListFragment.TAG).commit();
        }
    }

    private void addProductDetailFragment() {
        detailScrollFragment = (ProductDetailScrollFragment) getSupportFragmentManager().findFragmentByTag(ProductDetailScrollFragment.TAG);
        if (detailScrollFragment == null) {
            detailScrollFragment = ProductDetailScrollFragment.newInstance(2);
            getSupportFragmentManager().beginTransaction().add(R.id.frame, detailScrollFragment, ProductDetailScrollFragment.TAG).commit();
        }
    }

    private void addProductListFragment() {
        productFragment = (ProductListFragment) getSupportFragmentManager().findFragmentByTag(ProductListFragment.TAG);
        if (productFragment == null) {
            productFragment = ProductListFragment.newInstance(2);
            getSupportFragmentManager().beginTransaction().add(R.id.frame, productFragment, ProductListFragment.TAG).commit();
        }
    }

    private void addConnexionFragment() {
        connexionFragment = (ConnexionFragment) getSupportFragmentManager().findFragmentByTag(ConnexionFragment.TAG);
        if (connexionFragment == null) {
            connexionFragment = ConnexionFragment.newInstance(1);
            getSupportFragmentManager().beginTransaction().add(R.id.frame, connexionFragment, ConnexionFragment.TAG).commit();
        }
    }

    private void addHomeCustomerFragment() {
        customerHomeFragment = (CustomerHomeFragment) getSupportFragmentManager().findFragmentByTag(CustomerHomeFragment.TAG);
        if (customerHomeFragment == null) {
            customerHomeFragment = CustomerHomeFragment.getInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.frame, customerHomeFragment, CustomerHomeFragment.TAG).commit();
        }
    }

    private void addOrderListFragment() {
        listFragment = (OrderListFragment) getSupportFragmentManager().findFragmentByTag(OrderListFragment.TAG);
        if (listFragment == null) {
            listFragment = OrderListFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.frame, listFragment, OrderListFragment.TAG).commit();
        }
    }

    private void addOrderFragment() {
        orderScrollFragment = (OrderDetailScrollFragment) getSupportFragmentManager().findFragmentByTag(OrderDetailScrollFragment.TAG);
        if (orderScrollFragment == null) {
            orderScrollFragment = OrderDetailScrollFragment.newInstance(1);
            getSupportFragmentManager().beginTransaction().add(R.id.frame, orderScrollFragment, OrderDetailScrollFragment.TAG).commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        spiceManager.start(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //Toast.makeText(MainActivity.this, "onRestart", Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        //Toast.makeText(MainActivity.this, "onDestroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
        //Toast.makeText(MainActivity.this, "onStop", Toast.LENGTH_SHORT).show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void switchFragment(int id, String fragmentTag) {
        if (fragmentTag.equals(ProductListFragment.TAG)) {
            productFragment = (ProductListFragment) getSupportFragmentManager().findFragmentByTag(ProductListFragment.TAG);
            if (productFragment == null) {
                productFragment = ProductListFragment.newInstance(id);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, productFragment, ProductListFragment.TAG).addToBackStack(null).commit();
            }

        }

    }

    @Override
    public void replaceFragment(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragmentt = fragmentManager.findFragmentByTag(tag);
        if (fragmentt == null) {
            fragmentManager.beginTransaction().replace(R.id.frame, fragment, tag).addToBackStack(null).commit();
        }


    }

    @Override
    public String getAccessTokenFromPref() {
        return getSharedPreferences("userdetails", MODE_PRIVATE).getString(Token.BEARER_TOKEN, null);

    }

    @Override
    public void storeTokenAccessPref(Token token) {
        SharedPreferences prefs = getSharedPreferences("customer", MODE_PRIVATE);
        Editor edit = prefs.edit();
        edit.putString(Token.OAUTH_ACCES_TOKEN, token.getAccessToken());
        edit.putString(Token.BEARER_TOKEN, token.getTokenType() + " " + token.getAccessToken());
        edit.commit();

    }

    @Override
    public void removeFragment(Fragment f) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().remove(f).commit();
        fragmentManager.popBackStack();

    }


}
