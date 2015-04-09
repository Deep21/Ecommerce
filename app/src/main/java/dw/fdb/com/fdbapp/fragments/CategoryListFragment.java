package dw.fdb.com.fdbapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.octo.android.robospice.persistence.exception.SpiceException;

import java.util.List;

import dw.fdb.com.fdbapp.R;
import dw.fdb.com.fdbapp.adapter.CustomListAdapter;
import dw.fdb.com.fdbapp.listner.BaseRequestLisner;
import dw.fdb.com.fdbapp.model.Category;
import dw.fdb.com.fdbapp.request.CategoryGetRequest;

public class CategoryListFragment extends BaseListFragment {

    public static final String TAG = "CartListFragment";
    public static final String CAT_Id = "category_id";
    public Icommunicator icommunicator;
    public FragmentListner fragmentSwitcherListner;

    public List<Category> l;

    public static CategoryListFragment newInstance(int id) {
        CategoryListFragment categoryListFragment = new CategoryListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CAT_Id, id);
        categoryListFragment.setArguments(bundle);
        return categoryListFragment;
    }

    public List<Category> getL() {
        return l;
    }

    public void setL(List<Category> l) {
        this.l = l;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        fragmentSwitcherListner = (FragmentListner) activity;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Log.e("CategoryListFragment", "onCreate");
        request_caregory();
        Toast.makeText(getActivity(), "onCreate CategoryListFragment", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Toast.makeText(getActivity(), "onActivityCreated CategoryListFragment",Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_fragment, container, false);
        Log.e("CategoryListFragment", "onCreateView");
        Toast.makeText(getActivity(), "onCreateView CategoryListFragment", Toast.LENGTH_SHORT).show();
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("CategoryListFragment", "onDestroy");
        //Toast.makeText(getActivity(), "onDestroy CategoryListFragment",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("CategoryListFragment", "onStop");
        //Toast.makeText(getActivity(), "onStop CategoryListFragment",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("CategoryListFragment", "onResume");
        //Toast.makeText(getActivity(), "onResume CategoryListFragment", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("CategoryListFragment", "onDestroyView");
        Toast.makeText(getActivity(), "onDestroyView CategoryListFragment", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("CategoryListFragment", "onStart");
        Toast.makeText(getActivity(), "onStart CategoryListFragment", Toast.LENGTH_SHORT).show();
    }

    public void request_caregory() {
        if (getArguments() != null) {
            int id_category = getArguments().getInt(CAT_Id);
            CategoryGetRequest categoryRequest = new CategoryGetRequest(id_category);
            getSpiceManager().execute(categoryRequest, new CategoryRequestListner());
        }

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        int id_product = getL().get(position).getIdCategory();
        System.out.println(id_product);
        ProductListFragment productListFragment = ProductListFragment.newInstance(id_product);
        fragmentSwitcherListner.replaceFragment(productListFragment, null);


    }

    public interface Icommunicator {
        public void switchFragment(int id, String fragmentTag);
    }

    public class CategoryRequestListner extends BaseRequestLisner<Category.List> {

        @Override
        public void onRequestFailure(SpiceException e) {
            super.onRequestFailure(e);
        }

        @Override
        public void onRequestSuccess(Category.List category) {
            setL(category);
            CustomListAdapter customListAdapter = new CustomListAdapter(getActivity(), category);
            setListAdapter(customListAdapter);
        }

    }

}
