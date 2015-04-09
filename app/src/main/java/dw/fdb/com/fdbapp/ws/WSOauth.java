package dw.fdb.com.fdbapp.ws;

import dw.fdb.com.fdbapp.model.Token;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.Query;


public interface WSOauth {

    @POST("/auth/access-token")
    public Token getAccesToken(@Body Token token, @Query("refresh") int refresh);

    @POST("/auth/refresh-token")
    public Token refreshToken(@Body Token token);


}
