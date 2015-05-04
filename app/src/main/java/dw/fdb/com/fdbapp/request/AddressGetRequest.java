package dw.fdb.com.fdbapp.request;


import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import dw.fdb.com.fdbapp.model.order.OrderHistory;
import dw.fdb.com.fdbapp.ws.WSAddress;


public class AddressGetRequest extends RetrofitSpiceRequest<OrderHistory, WSAddress> {
	int id;
	
	public AddressGetRequest() {
		super(OrderHistory.class, WSAddress.class);

	}
	
	public AddressGetRequest(int id) {
		super(OrderHistory.class, WSAddress.class);
		this.id = id;
	}

	@Override
	public OrderHistory loadDataFromNetwork() throws Exception {
		return getService().getAddressById(id);
	}


}
