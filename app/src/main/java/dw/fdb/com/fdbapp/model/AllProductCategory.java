package dw.fdb.com.fdbapp.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dw.fdb.com.fdbapp.adapter.CustomListAdapter;


public class AllProductCategory implements Item{


	@Override
	public int getId(int position) {
		return 0;
	}

	@Override
	public int getViewType() {
		return CustomListAdapter.RowType.CATEGORY_MORE_PRODUCT_LAYOUT.ordinal();
	}

	@Override
	public View getView(LayoutInflater inflator, View convertView,
			ViewGroup parent, int position) {
		// TODO Auto-generated method stub
		return null;
	}



}
