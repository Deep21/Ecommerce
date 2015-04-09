package dw.fdb.com.fdbapp.listner;


import dw.fdb.com.fdbapp.model.Token;

public interface OauthListner {

    public String getAccessTokenFromPref();

    public void storeTokenAccessPref(Token token);

}
