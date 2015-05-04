package dw.fdb.com.fdbapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import dw.fdb.com.fdbapp.model.Item;

public class CustomListAdapter extends BaseAdapter {
    List<? extends Item> items;
    LayoutInflater inflator;
    Context context;
    AdapterOnClickListner adapterOnClickListner;

    public CustomListAdapter(Context context, List<? extends Item> items) {
        this.items = items;
        this.context = context;


    }
    public interface AdapterOnClickListner{
        public void buttonClicked(View v, int i);
    }

    public void setOnClickListner(AdapterOnClickListner buttonListner){
        this.adapterOnClickListner = buttonListner;
    }

    public List<? extends Item> getCustomItems() {
        return items;
    }

    public void setCustomItems(List<? extends Item> items) {
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        return RowType.values().length;
    }

    @Override
    public int getCount() {
        return (items == null) ? 0 : items.size();
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
            return items.get(position).getView(inflator, convertView, parent, position, adapterOnClickListner);
        }
        return items.get(position).getView(inflator, convertView, parent, position, adapterOnClickListner);
    }

    public enum RowType {
        CATEGORY_MORE_PRODUCT_LAYOUT, SIMPLE_CATEGORY_LAYOUT
    }


}
