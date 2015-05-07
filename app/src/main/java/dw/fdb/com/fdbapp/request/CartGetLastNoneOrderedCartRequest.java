package dw.fdb.com.fdbapp.request;


import dw.fdb.com.fdbapp.model.cart.Cart;
import dw.fdb.com.fdbapp.ws.WSCart;

public class CartGetLastNoneOrderedCartRequest extends BaseRequest<Cart, WSCart> {
	int id_customer;
    String token;

	public CartGetLastNoneOrderedCartRequest(int id_customer, String token) {
		super(Cart.class, WSCart.class);
		this.id_customer = id_customer;
        this.token = token;
	}

	public CartGetLastNoneOrderedCartRequest() {
		super(Cart.class, WSCart.class);

	}

	@Override
	public Cart loadDataFromNetwork() throws Exception {
		return getService().getLastNoneOrderedCart(id_customer, "Bearer"+" "  + token);
	}

	public String createCacheKey() {
		return "CartGetLastCartRequest";
	}

}
