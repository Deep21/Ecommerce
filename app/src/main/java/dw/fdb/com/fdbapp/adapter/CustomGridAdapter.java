package dw.fdb.com.fdbapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import dw.fdb.com.fdbapp.model.Item;

public class CustomGridAdapter extends BaseAdapter {
    List<? extends Item> items;
    LayoutInflater inflator;
    AdapterOnClickListner adapterOnClickListner;

    public interface AdapterOnClickListner{
        public void buttonClicked(View v, int i);
    }

    public void setOnClickListner(AdapterOnClickListner buttonListner){
        this.adapterOnClickListner = buttonListner;
    }


    public CustomGridAdapter(Context context, List<? extends Item> items) {
        this.items = items;
        inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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
        return items.get(position).getView(inflator, convertView, parent, position, null);
    }


}
