package dw.fdb.com.fdbapp.request;


import java.util.Map;

import dw.fdb.com.fdbapp.model.ProductModel;
import dw.fdb.com.fdbapp.ws.WSProduct;


public class ProductListGetRequest extends BaseRequest<ProductModel, WSProduct>{
	String sort;
	int id_category;
	Map<String, String> filter;
	
	public ProductListGetRequest(String sort) {
		super(ProductModel.class, WSProduct.class);
		this.sort = sort;
	}
	
	public ProductListGetRequest(int id_category) {
		super(ProductModel.class, WSProduct.class);
		this.id_category = id_category;
	}
	
	public ProductListGetRequest(int id_category, Map<String, String> filter) {
		super(ProductModel.class, WSProduct.class);
		this.id_category = id_category;
		this.filter = filter;
	}
	

	@Override
	public ProductModel loadDataFromNetwork() throws Exception {
		return getService().getProductListByCategoryId(id_category, filter);
	
	}

	public String createCacheKey() {
		return "ProductListGetRequest";
	}
	
	

}
