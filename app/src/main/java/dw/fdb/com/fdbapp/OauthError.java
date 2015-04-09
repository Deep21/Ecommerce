package dw.fdb.com.fdbapp;


import dw.fdb.com.fdbapp.model.AuthTokenException;

public interface OauthError {
    //ne doit �tre pas modifi�
    public static final String EXPIRED_MSG = "The access token provided has expired";

    public void onExpiredToken(AuthTokenException spiceException);

    public void onInvalidToken(AuthTokenException spiceException);
}