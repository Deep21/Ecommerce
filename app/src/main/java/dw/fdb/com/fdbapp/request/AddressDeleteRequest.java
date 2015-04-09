package dw.fdb.com.fdbapp.request;


import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import dw.fdb.com.fdbapp.model.OrderHistory;
import dw.fdb.com.fdbapp.ws.WSAddress;

public class AddressDeleteRequest extends RetrofitSpiceRequest<OrderHistory, WSAddress> {
	int id;
	
	public AddressDeleteRequest() {
		super(OrderHistory.class, WSAddress.class);

	}

	public AddressDeleteRequest(int id) {
		super(OrderHistory.class, WSAddress.class);
		this.id = id;
	}

	@Override
	public OrderHistory loadDataFromNetwork() throws Exception {
		return getService().deleteAddressById(id);
	}

}
