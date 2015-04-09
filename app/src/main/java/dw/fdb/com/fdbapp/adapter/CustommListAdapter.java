package dw.fdb.com.fdbapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import dw.fdb.com.fdbapp.model.Item;

public class CustommListAdapter extends BaseAdapter {
    List<Item> items;
    LayoutInflater inflator;
    Context context;
    public CustommListAdapter(Context context, List<Item> items) {
        this.items = items;
        this.context = context;


    }

    public List<Item> getCustomItems() {
        return items;
    }

    public void setCustomItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        return RowType.values().length;
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
            inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            return items.get(position).getView(inflator, convertView, parent, 0);
        }
        return items.get(position).getView(inflator, convertView, parent, 0);
    }

    public enum RowType {
        CATEGORY_MORE_PRODUCT_LAYOUT, SIMPLE_CATEGORY_LAYOUT
    }


}
