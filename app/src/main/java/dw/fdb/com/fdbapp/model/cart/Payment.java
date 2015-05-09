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

    private String libelle_produit;
    private String description;
    private String url;
    private int qty;
    private double prix_ttc;


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
            holder.prix_ttc = (TextView) convertView.findViewById(R.id.prix_ttc);
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

    public String getLibelle_produit() {
        return libelle_produit;
    }

    public void setLibelle_produit(String libelle_produit) {
        this.libelle_produit = libelle_produit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrix_ttc() {
        return prix_ttc;
    }

    public void setPrix_ttc(double prix_ttc) {
        this.prix_ttc = prix_ttc;
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
