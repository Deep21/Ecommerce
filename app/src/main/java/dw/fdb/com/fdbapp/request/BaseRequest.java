package dw.fdb.com.fdbapp.request;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

public class BaseRequest<M, W> extends RetrofitSpiceRequest<M, W>{

	public BaseRequest(Class<M> clazz, Class<W> retrofitedInterfaceClass) {
		super(clazz, retrofitedInterfaceClass);
		setRetryPolicy(null);	
	}

	@Override
	public M loadDataFromNetwork() throws Exception {
		return null;
	}
	


}
