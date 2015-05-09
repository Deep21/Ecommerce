package dw.fdb.com.fdbapp.model.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import dw.fdb.com.fdbapp.R;
import dw.fdb.com.fdbapp.adapter.CustomListAdapter;
import dw.fdb.com.fdbapp.model.Item;

/**
 * Created by Starlight on 09/05/2015.
 */
public class Payment implements Item {

    @Override
    public View getView(LayoutInflater inflator, View convertView, ViewGroup parent, int position, CustomListAdapter.AdapterOnClickListner adapterOnClickListner) {
        ViewHolder holder;
        System.out.println(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflator.inflate(R.layout.payment_layout_fragment, parent, false);
            holder.libelle_produit = (TextView) convertView.findViewById(R.id.libelle_produit);
            holder.qty_edit_text = (EditText) convertView.findViewById(R.id.qty_edit_text);
            holder.description_product = (TextView) convertView.findViewById(R.id.description_product);
            holder.prix_ttc = (TextView) convertView.findViewById(R.id.address1);
            holder.product_image = (ImageView) convertView.findViewById(R.id.product_image);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.libelle_produit.setText("e");
        holder.prix_ttc.setText(Integer.toString(55));
        holder.qty_edit_text.setText(""+5);
        //Picasso.with(parent.getContext()).load("");

        return convertView;
    }
    public static class ViewHolder {
        TextView libelle_produit;
        TextView description_product;
        TextView prix_ttc;
        EditText qty_edit_text;
        ImageView product_image;
    }

    @Override
    public int getId(int position) {
        return 0;
    }

    @Override
    public int getViewType() {
        return 0;
    }
}
