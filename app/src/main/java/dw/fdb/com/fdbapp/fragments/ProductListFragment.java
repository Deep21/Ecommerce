package dw.fdb.com.fdbapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.ViewSwitcher;

import com.octo.android.robospice.persistence.exception.SpiceException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import dw.fdb.com.fdbapp.R;
import dw.fdb.com.fdbapp.adapter.CustommListAdapter;
import dw.fdb.com.fdbapp.listner.BaseRequestLisner;
import dw.fdb.com.fdbapp.model.Category;
import dw.fdb.com.fdbapp.model.Item;
import dw.fdb.com.fdbapp.model.Product;
import dw.fdb.com.fdbapp.model.ProductModel;
import dw.fdb.com.fdbapp.request.ProductListGetRequest;
import dw.fdb.com.fdbapp.views.EndlessListView;

public class ProductListFragment extends BaseListFragment implements EndlessListView.EndlessListner {
    public static final String TAG = "ProductListFragment";
    public FragmentListner fragmentSwitcherListner;
    ViewSwitcher switcher;
    ToggleButton toggleButton1;
    GridView gridView;
    EndlessListView endlessListView = null;
    @InjectView(R.id.nb_product)
    TextView nb_product;

    private ProductModel productModel;

    public static ProductListFragment newInstance(int id) {
        ProductListFragment fragment = new ProductListFragment();
        Bundle b = new Bundle();
        b.putInt(Category.ARGS_CATEGORY_ID, id);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            fragmentSwitcherListner = (FragmentListner) activity;
        } catch (ClassCastException castException) {

        }

    }

    @OnClick(R.id.filter)
    public void filter(View v) {
        FilterFragment filterFragment = FilterFragment.getInstance();
        fragmentSwitcherListner.replaceFragment(filterFragment, FilterFragment.TAG);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        CustommListAdapter adapter = (CustommListAdapter) getListAdapter();
        if (adapter != null) {
            Product p = (Product) adapter.getItem(position);
            ProductDetailScrollFragment productDetailFragment = ProductDetailScrollFragment.newInstance(p.getIdProduct());
            fragmentSwitcherListner.replaceFragment(productDetailFragment, null);
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        productRequest();
        Log.e("ProductListFragment", "onCreate");
        // Toast.makeText(getActivity(), // "onCreate ProductFragment",Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.product_list_fragment, container, false);
        ButterKnife.inject(this, v);
        /*switcher = (ViewSwitcher) v.findViewById(R.id.viewswitcher);
        toggleButton1 = (ToggleButton) v.findViewById(R.id.toggle);
		gridView = (GridView) v.findViewById(R.id.gridView1);
		Log.e("ProductListFragment", "onCreateView");*/
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getListAdapter() != null && endlessListView != null) {
            ((EndlessListView) getListView()).setListner(ProductListFragment.this);
            CustommListAdapter customListAdapter = (CustommListAdapter) getListAdapter();
            List<Item> i = customListAdapter.getCustomItems();
            i.addAll(getProductModel().getProductList());
            customListAdapter.notifyDataSetChanged();

        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("ProductListFragment", "onStart");
        // Toast.makeText(getActivity(),
        // "onStart ProductFragment",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        Log.e("ProductListFragment", "onStop");
        // Toast.makeText(getActivity(),
        // "onStop ProductFragment",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
        Log.e("ProductListFragment", "onDestroyView");
        // Toast.makeText(getActivity(),
        // "onDestroyView ProductFragment",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("ProductListFragment", "onDestroy");
        // Toast.makeText(getActivity(),
        // "onDestroy ProductFragment",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("ProductListFragment", "onResume");
    }

    public void productRequest() {
        if (getArguments() != null) {
            Map<String, String> filter = new HashMap<String, String>();
            filter.put("filter", "manufacturer");
            filter.put("value", "[3]");
            int id_category = getArguments().getInt(Category.ARGS_CATEGORY_ID);
            ProductListGetRequest productListGetRequest = new ProductListGetRequest(id_category, filter);
            getSpiceManager().execute(productListGetRequest, new ProductRequestListner());
        }

    }

    @Override
    public void onLoadMore() {
        Map<String, String> filter = new HashMap<String, String>();
        Integer i = getProductModel().getNext();
        if (i != 0) {
            filter.put("per_page", "" + getProductModel().getNext());
            int id_category = getArguments().getInt(Category.ARGS_CATEGORY_ID);
            ProductListGetRequest productListGetRequest = new ProductListGetRequest(id_category, filter);
            getSpiceManager().execute(productListGetRequest, new ProductRequestListner());


        }


    }

    ProductModel getProductModel() {
        return productModel;
    }

    void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }

    public class ProductRequestListner extends BaseRequestLisner<ProductModel> {

        @Override
        public void onRequestFailure(SpiceException fail) {
            System.out.println(fail);
            Toast.makeText(getActivity(), "FAIL", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onRequestSuccess(ProductModel product) {
            setProductModel(product);
            nb_product.setText("" + product.getNbProducts());
            if (getListAdapter() != null) {
                EndlessListView endlessListView = (EndlessListView) getListView();
                endlessListView.setListner(ProductListFragment.this);
                CustommListAdapter customListAdapter = (CustommListAdapter) endlessListView.getAdapter();
                List<Item> i = customListAdapter.getCustomItems();
                i.addAll(getProductModel().getProductList());
                customListAdapter.notifyDataSetChanged();

            } else {
                List<Product> products = product.getProductList();
                endlessListView = (EndlessListView) getListView();
                endlessListView.setListner(ProductListFragment.this);
                List<Item> it = new ArrayList<Item>();
                it.addAll(products);
                CustommListAdapter adapter = new CustommListAdapter(getActivity(), it);
                setListAdapter(adapter);

            }

        }

    }

}
