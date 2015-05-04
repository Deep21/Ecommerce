package dw.fdb.com.fdbapp.request;


import dw.fdb.com.fdbapp.model.cart.CartProduct;
import dw.fdb.com.fdbapp.ws.WSCart;

public class CartEditProductRequest extends BaseRequest<CartProduct, WSCart>{
	private CartProduct cart;
    private String param;
    private int id_cart;
	private String token;

	public CartEditProductRequest(int id_cart, CartProduct cart, String param, String token) {
		super(CartProduct.class, WSCart.class);
		this.cart = cart;
		this.param = param;
		this.token = token;
        this.id_cart = id_cart;
	}

	public CartEditProductRequest() {
		super(CartProduct.class, WSCart.class);

	}

	@Override
	public CartProduct loadDataFromNetwork() throws Exception {
		return getService().editProductCartById(id_cart, cart, param);
	}

	public String createCacheKey() {
		return "CartEditProductRequest";
	}
	
	

}
