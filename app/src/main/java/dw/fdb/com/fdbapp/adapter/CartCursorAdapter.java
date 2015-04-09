package dw.fdb.com.fdbapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import dw.fdb.com.fdbapp.R;
import dw.fdb.com.fdbapp.activitie.MyApplication;
import dw.fdb.com.fdbapp.db.CartDao;
import dw.fdb.com.fdbapp.db.DaoSession;

public class CartCursorAdapter extends CursorAdapter {
    @InjectView(R.id.product_image)
    ImageView product_image;
    @InjectView(R.id.libelle_produit)
    TextView libelle_produit;
    @InjectView(R.id.description_short)
    TextView description_short;
    @InjectView(R.id.prix_ttc)
    TextView prix_ttc;
    @InjectView(R.id.stock)
    TextView stock;
    @InjectView(R.id.btn_qte)
    Button btn_qte;
    @InjectView(R.id.delete_product)
    Button delete_product;
    DaoSession daoSession;

    public CartCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        daoSession = MyApplication.getDaoSession();

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void bindView(View view, Context context, final Cursor cursor) {
        libelle_produit.setText(cursor.getLong(CartDao.Properties.Id.ordinal)
                + cursor.getString(CartDao.Properties.Libelle_produit.ordinal));
        description_short.setText(cursor
                .getString(CartDao.Properties.Description_short.ordinal));
        prix_ttc.setText(cursor.getString(CartDao.Properties.Prix_ttc.ordinal));
        //System.out.println(cursor.getLong(CartDao.Properties.Id.ordinal));
        delete_product.setTag(cursor.getLong(CartDao.Properties.Id.ordinal));
        delete_product.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Long id = (Long) v.getTag();
                System.out.println(id);
                int id_delete = MyApplication.getDaoSession().getDatabase().delete(CartDao.TABLENAME, CartDao.Properties.Id.columnName + "=" + id, null);
                cursor.requery();
                //System.out.println("==>Supresion<==" + id_delete);
                //EventBus.getDefault().post("");
            }
        });

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.cart_list_layout, viewGroup, false);
        ButterKnife.inject(this, view);
        bindView(view, context, cursor);
        return view;
    }

    @OnClick(R.id.btn_qte)
    public void btnQte(View v) {

    }

	/*@OnClick(R.id.delete_product)
    public void btnDelete(View v) {
		Long id = (Long)v.getTag();
		Cart cart = new Cart(id);
		System.out.println(id);
		//EventBus.getDefault().post(cart);

	}*/


}
