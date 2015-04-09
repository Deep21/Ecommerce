package dw.fdb.com.fdbapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import dw.fdb.com.fdbapp.model.Item;
import dw.fdb.com.fdbapp.views.CartRelativeView;

public class CustomViewListAdapter extends BaseAdapter {

    LayoutInflater inflator;
    View v;
    private List<Item> items;
    private List<CartRelativeView> views;

    public CustomViewListAdapter(Context context, List<Item> items) {
        this.items = items;

    }

    public List<? extends Item> getCustomItems() {
        return items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

        }
        return items.get(position).getView(inflator, convertView, parent, position);


    }


}
