package dw.fdb.com.fdbapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import dw.fdb.com.fdbapp.R;
import dw.fdb.com.fdbapp.activitie.MyApplication;
import dw.fdb.com.fdbapp.db.model.DBCartDao;
import dw.fdb.com.fdbapp.db.model.DaoSession;
import dw.fdb.com.fdbapp.model.cart.Cart;
import dw.fdb.com.fdbapp.model.Item;

;

public class CartRelativeView extends RelativeLayout implements Item {
	LayoutInflater inflater;
	@InjectView(R.id.product_image) ImageView product_image;
	@InjectView(R.id.libelle_produit) TextView libelle_produit;
	@InjectView(R.id.description_short) TextView description_short;
	@InjectView(R.id.prix_ttc) TextView prix_ttc;
	@InjectView(R.id.stock) TextView stock;
	@InjectView(R.id.btn_qte) Button btn_qte;
	@InjectView(R.id.delete_product) Button delete_product;
	DaoSession daoSession;
	private Cart cart;
	
	
	public CartRelativeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.cart_list_layout, this , true);
		ButterKnife.inject(this);

	}

	public CartRelativeView(Context context, AttributeSet attrs,int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.cart_list_layout, this , true);
		ButterKnife.inject(this);
	
	}

	public CartRelativeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.cart_list_layout, this , true);
		ButterKnife.inject(this);
		daoSession = MyApplication.getDaoSession();
		
	}

	public CartRelativeView(Context context) {
		super(context);
		inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.cart_list_layout, this , true);
		ButterKnife.inject(this);
	
	}
	
	public static CartRelativeView inflate(Context c){
		CartRelativeView cartRelativeView = (CartRelativeView)LayoutInflater.from(c).inflate(R.layout.cart_relativelayout_container, null, false);
		return cartRelativeView;
	}

	public void setData(Cart cart) {
		//libelle_produit.setText(cart.getLibelle_produit());
		//prix_ttc.setText(String.valueOf(cart.getPrix_ttc()));
		this.cart = cart;
	}
	
	@OnClick(R.id.delete_product)
	public void deleteProduct() {
		EventBus.getDefault().post(cart);
		
	}
	
	@OnClick(R.id.btn_qte)
	public void btnQte() {
        DBCartDao cartDao = daoSession.getDBCartDao();
		//cart.setLibelle_produit("eazf");
		//cartDao.update(cart);
		EventBus.getDefault().post(cart);
		
	}

	@Override
	public View getView(LayoutInflater inflator, View convertView, ViewGroup parent, int position) {
		return this;
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
