package dw.fdb.com.fdbapp.request;


import dw.fdb.com.fdbapp.model.cart.Cart;
import dw.fdb.com.fdbapp.ws.WSCart;

public class CartCreatePostRequest extends BaseRequest<Cart, WSCart>{


	public CartCreatePostRequest() {
		super(Cart.class, WSCart.class);

	}

	@Override
	public Cart loadDataFromNetwork() throws Exception {
		return getService().createCart();

	}

	public String createCacheKey() {
        return "CartCreatePostRequest";
	}
	
	

}
