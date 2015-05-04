package dw.fdb.com.fdbapp.request;


import dw.fdb.com.fdbapp.model.order.OrderHistory;
import dw.fdb.com.fdbapp.ws.WSOrder;

public class OrderGetByCustomerRequest extends BaseRequest<OrderHistory.OrderList, WSOrder>{
	int id_customer;
	String token;
	
	public OrderGetByCustomerRequest() {
		super(OrderHistory.OrderList.class, WSOrder.class);

	}
	
	public OrderGetByCustomerRequest(int id_customer, String token) {
		super(OrderHistory.OrderList.class, WSOrder.class);
		this.id_customer = id_customer;
		this.token = token;
	}

	@Override
	public OrderHistory.OrderList loadDataFromNetwork() throws Exception {
		return getService().getOrderByCustomerId(id_customer, token);

	}



}
