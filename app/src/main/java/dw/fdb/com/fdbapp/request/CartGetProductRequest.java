package dw.fdb.com.fdbapp.request;

import java.util.Map;

import dw.fdb.com.fdbapp.model.Product;
import dw.fdb.com.fdbapp.ws.WSCart;


public class CartGetProductRequest extends BaseRequest<Product.ProductList, WSCart>{
	int id_cart;
	Map<String, String> params;
	String token;
	
	public CartGetProductRequest(int id_cart, String token) {
		super(Product.ProductList.class, WSCart.class);
		this.id_cart = id_cart;
		this.token = token;
	}
	
	public CartGetProductRequest() {
		super(Product.ProductList.class, WSCart.class);
	}

	@Override
	public Product.ProductList loadDataFromNetwork() throws Exception {
		return getService().getProductCartById(id_cart, token);
	}

	public String createCacheKey() {
		return "CartGetProductRequest";
	}
	
	

}
