package dw.fdb.com.fdbapp.ws;

import dw.fdb.com.fdbapp.model.Token;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.Query;


public interface WSOauth {

    @POST("/token/access-token")
    public Token getAccesToken(@Body Token token, @Query("refresh") int refresh);

    @POST("/token/refresh-token")
    public Token refreshToken(@Body Token token);


}
