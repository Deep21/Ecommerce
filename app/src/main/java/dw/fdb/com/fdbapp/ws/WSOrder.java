package dw.fdb.com.fdbapp.ws;

import dw.fdb.com.fdbapp.model.order.OrderHistory;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;


public interface WSOrder {

    @GET("/order/{id_order}/get")
    public OrderHistory getOrderById(@Path("id_order") int id_order, @Header("Authorization") String token);

    @GET("/order/customer/{id_customer}/get")
    public OrderHistory.OrderList getOrderByCustomerId(@Path("id_customer") int id_order, @Header("Authorization") String token);


}
