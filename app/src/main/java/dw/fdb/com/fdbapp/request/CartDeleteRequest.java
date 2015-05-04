package dw.fdb.com.fdbapp.request;


import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import dw.fdb.com.fdbapp.model.cart.Cart;
import dw.fdb.com.fdbapp.ws.WSCart;

public class CartDeleteRequest extends RetrofitSpiceRequest<Cart, WSCart> {
    int id_cart;
    int id_product;
    int id_product_attribute;
    int id_address;
	
	public CartDeleteRequest(int id_cart, int id_product, int id_product_attribute,int id_address ) {
		super(Cart.class, WSCart.class);
        this.id_cart = id_cart;
        this.id_product = id_product;
        this.id_product_attribute = id_product_attribute;
        this.id_address = id_address;
	}
	
	public CartDeleteRequest() {
		super(Cart.class, WSCart.class);

	}

	@Override
	public Cart loadDataFromNetwork() throws Exception {
		return getService().deleteProductCartById(id_cart, id_product, id_product_attribute, id_address);
	}

	public String createCacheKey() {
		return "key";
	}
	
	

}
