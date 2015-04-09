package dw.fdb.com.fdbapp.request;


import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import dw.fdb.com.fdbapp.model.Product;
import dw.fdb.com.fdbapp.ws.WSProduct;

public class ProductDetailRequest extends RetrofitSpiceRequest<Product, WSProduct> {
	String sort;
	int id_product;
	
	public ProductDetailRequest(String sort) {
		super(Product.class, WSProduct.class);
		this.sort = sort;
	}
	
	public ProductDetailRequest(int id_product) {
		super(Product.class, WSProduct.class);
		this.id_product = id_product;
	}
	

	@Override
	public Product loadDataFromNetwork() throws Exception {
		try {
			return getService().getProductDetailListByProductId(id_product);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public String createCacheKey() {
		return "ProductRequest";
	}
	
	

}
