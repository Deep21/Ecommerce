package dw.fdb.com.fdbapp.ws;

import java.util.Map;

import dw.fdb.com.fdbapp.model.Product;
import dw.fdb.com.fdbapp.model.ProductModel;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.QueryMap;


public interface WSProduct {

    @GET("/category/{id_category}/product/get")
    public ProductModel getProductListByCategoryId(@Path("id_category") int id_category, @QueryMap Map<String, String> filter);

    @GET("/product/{id_product}/get")
    public Product getProductDetailListByProductId(@Path("id_product") int id_product);

}
