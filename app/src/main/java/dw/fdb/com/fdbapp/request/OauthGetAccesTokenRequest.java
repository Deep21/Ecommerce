package dw.fdb.com.fdbapp.request;


import dw.fdb.com.fdbapp.model.Token;
import dw.fdb.com.fdbapp.ws.WSOauth;

public class OauthGetAccesTokenRequest extends BaseRequest<Token, WSOauth>{
	Token token;
	int refresh;
	
	public OauthGetAccesTokenRequest() {
		super(Token.class, WSOauth.class);

	}
	
	public OauthGetAccesTokenRequest(Token token) {
		super(Token.class, WSOauth.class);
		this.token = token;
	}
	
	public OauthGetAccesTokenRequest(Token token, int refresh) {
		super(Token.class, WSOauth.class);
		this.token = token;
		this.refresh = refresh;
	}

	@Override
	public Token loadDataFromNetwork() throws Exception {
		return getService().getAccesToken(token, refresh);

	}

	public String createCacheKey() {
		return "OauthGetAccesTokenRequest";
	}
	
	

}
