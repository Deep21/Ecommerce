package dw.fdb.com.fdbapp.request;


import dw.fdb.com.fdbapp.model.CartProduct;
import dw.fdb.com.fdbapp.ws.WSCart;

public class CartAddProductPostRequest extends BaseRequest<CartProduct, WSCart>{
	CartProduct cart;
	String param;
	private String token;
	
	public CartAddProductPostRequest(CartProduct cart, String param, String token) {
		super(CartProduct.class, WSCart.class);
		this.cart = cart;
		this.param = param;
		this.token = token;
	}
	
	public CartAddProductPostRequest() {
		super(CartProduct.class, WSCart.class);

	}

	@Override
	public CartProduct loadDataFromNetwork() throws Exception {
		return getService().addProductToCartById(token, cart, param);


	}

	public String createCacheKey() {
		return "CartAddProductPostRequest";
	}
	
	

}
