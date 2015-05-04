package dw.fdb.com.fdbapp.ws;


import dw.fdb.com.fdbapp.model.cart.Cart;
import dw.fdb.com.fdbapp.model.cart.CartModel;
import dw.fdb.com.fdbapp.model.cart.CartProduct;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

public interface WSCart {

    @GET("/cart/customer/{id_customer}/get")
    public Cart getLastNoneOrderedCart(
            @Path("id_customer") int id_customer);

    @GET("/cart/{id_cart}/product/get")
    public CartModel getProductCartById(
            @Path("id_cart") int id_cart,
            @Header("Authorization") String token);

    @DELETE("/cart/{id_cart}/product/{id_product}/attribute/{id_product_attribute}/address/{id_address}/delete")
    public Cart deleteProductCartById(
            @Path("id_cart") int id_cart,
            @Path("id_product") int id_product,
            @Path("id_product_attribute") int id_product_attribute,
            @Path("id_address") int id_address
    );

    @POST("/cart/create")
    public Cart createCart();


    @POST("/cart/{id_cart}/product/add")
    public CartProduct addProductToCartById(
            @Path("id_cart") int id_cart,
            @Header("Authorization") String token,
            @Body CartProduct cartProduct,
            @Query("cart") String cart);


    @PUT("/cart/{id_cart}/edit")
    public CartProduct editProductCartById(
            @Path("id_cart") int id_cart,
            @Body CartProduct cartProduct,
            @Query("cart") String cart);


}
