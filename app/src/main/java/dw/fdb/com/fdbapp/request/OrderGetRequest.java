package dw.fdb.com.fdbapp.request;


import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import dw.fdb.com.fdbapp.model.OrderHistory;
import dw.fdb.com.fdbapp.ws.WSOrder;

public class OrderGetRequest extends RetrofitSpiceRequest<OrderHistory, WSOrder> {
	int id_order;
	String token;
	
	public OrderGetRequest() {
		super(OrderHistory.class, WSOrder.class);

	}
	
	public OrderGetRequest(int id_order, String token) {
		super(OrderHistory.class, WSOrder.class);
		this.id_order = id_order;
		this.token = token;
	}

	@Override
	public OrderHistory loadDataFromNetwork() throws Exception {
		return getService().getOrderById(id_order, token);
	}



}
