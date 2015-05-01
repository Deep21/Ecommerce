package dw.fdb.com.fdbapp.request;

import java.util.Map;

import dw.fdb.com.fdbapp.model.cart.CartModel;
import dw.fdb.com.fdbapp.ws.WSCart;


public class CartGetProductRequest extends BaseRequest<CartModel, WSCart>{
	int id_cart;
	Map<String, String> params;
	String token;
	
	public CartGetProductRequest(int id_cart, String token) {
		super(CartModel.class, WSCart.class);
		this.id_cart = id_cart;
		this.token = token;
	}
	
	public CartGetProductRequest() {
        super(CartModel.class, WSCart.class);
	}

	@Override
	public CartModel loadDataFromNetwork() throws Exception {
		return getService().getProductCartById(id_cart, token);
	}

	public String createCacheKey() {
		return "CartGetProductRequest";
	}
	
	

}
