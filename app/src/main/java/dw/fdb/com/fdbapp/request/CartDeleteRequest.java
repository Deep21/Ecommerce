package dw.fdb.com.fdbapp.request;


import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import dw.fdb.com.fdbapp.model.cart.Cart;
import dw.fdb.com.fdbapp.ws.WSCart;

public class CartDeleteRequest extends RetrofitSpiceRequest<Cart, WSCart> {
	int id_cart;
	
	public CartDeleteRequest(int id_cart) {
		super(Cart.class, WSCart.class);
		this.id_cart = id_cart;
	}
	
	public CartDeleteRequest() {
		super(Cart.class, WSCart.class);

	}

	@Override
	public Cart loadDataFromNetwork() throws Exception {
		return getService().deleteProductCartById(id_cart, 0);
	}

	public String createCacheKey() {
		return "key";
	}
	
	

}
