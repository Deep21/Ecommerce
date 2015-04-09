package dw.fdb.com.fdbapp.ws;

import dw.fdb.com.fdbapp.model.Customer;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;


public interface WSCustomer {

    @GET("/customer/context/get")
    public Customer getCustomerContext(@Header("Authorization") String token);

    @POST("/customer/create")
    public Customer createCustomerAccount(@Body Customer customer);


}
