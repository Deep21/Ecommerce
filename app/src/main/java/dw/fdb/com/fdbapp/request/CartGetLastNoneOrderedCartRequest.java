package dw.fdb.com.fdbapp.request;


import dw.fdb.com.fdbapp.model.cart.Cart;
import dw.fdb.com.fdbapp.ws.WSCart;

public class CartGetLastNoneOrderedCartRequest extends BaseRequest<Cart, WSCart> {
	int id_customer;

	public CartGetLastNoneOrderedCartRequest(int id_customer) {
		super(Cart.class, WSCart.class);
		this.id_customer = id_customer;
	}

	public CartGetLastNoneOrderedCartRequest() {
		super(Cart.class, WSCart.class);

	}

	@Override
	public Cart loadDataFromNetwork() throws Exception {
			return getService().getLastNoneOrderedCart(id_customer);


	}

	public String createCacheKey() {
		return "CartGetLastCartRequest";
	}

}
