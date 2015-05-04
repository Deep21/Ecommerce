package dw.fdb.com.fdbapp.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dw.fdb.com.fdbapp.adapter.CustomListAdapter;

public interface Item {

    public View getView(LayoutInflater inflator, View convertView, ViewGroup parent, int position, CustomListAdapter.AdapterOnClickListner adapterOnClickListner);

    public int getId(int position);

    public int getViewType();

}
