package dw.fdb.com.fdbapp.request;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import dw.fdb.com.fdbapp.model.cart.Cart;
import dw.fdb.com.fdbapp.ws.WSCart;


public class CartEditRequest extends RetrofitSpiceRequest<Cart, WSCart> {
	int id_cart;
	Cart cart;
	
	public CartEditRequest(int id_cart, Cart c) {
		super(Cart.class, WSCart.class);
		this.id_cart = id_cart;
		this.cart = c;
	}
	
	public CartEditRequest() {
		super(Cart.class, WSCart.class);

	}

	@Override
	public Cart loadDataFromNetwork() throws Exception {
		return getService().editProductCartById(id_cart, cart);
	}

	public String createCacheKey() {
		return "key";
	}
	
	

}
