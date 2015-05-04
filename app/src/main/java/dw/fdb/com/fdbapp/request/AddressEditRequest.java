package dw.fdb.com.fdbapp.request;


import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import dw.fdb.com.fdbapp.model.order.OrderHistory;
import dw.fdb.com.fdbapp.ws.WSAddress;

public class AddressEditRequest extends RetrofitSpiceRequest<OrderHistory, WSAddress> {
	
	public AddressEditRequest() {
		super(OrderHistory.class, WSAddress.class);
	}
	
	public AddressEditRequest(int id, OrderHistory address) {
		super(OrderHistory.class, WSAddress.class);
	}

	@Override
	public OrderHistory loadDataFromNetwork() throws Exception {
		return getService().editAddressById(1, null);
	}
}
