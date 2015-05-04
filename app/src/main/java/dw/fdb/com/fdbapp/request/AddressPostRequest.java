package dw.fdb.com.fdbapp.request;


import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import dw.fdb.com.fdbapp.model.Model;
import dw.fdb.com.fdbapp.model.order.OrderHistory;
import dw.fdb.com.fdbapp.ws.WSAddress;

public class AddressPostRequest extends RetrofitSpiceRequest<OrderHistory, WSAddress> {
	private OrderHistory address;
	
	public AddressPostRequest(Class<Model> clazz, Class<WSAddress> retrofitedInterfaceClass) {
		super(OrderHistory.class, WSAddress.class);

	}
	
	public AddressPostRequest(OrderHistory adress) {
		super(OrderHistory.class, WSAddress.class);
		this.address = adress;
	}

	@Override
	public OrderHistory loadDataFromNetwork() throws Exception {
		return getService().addAddress(address);
	}

}
