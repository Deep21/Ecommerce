package dw.fdb.com.fdbapp.request;


import dw.fdb.com.fdbapp.model.Customer;
import dw.fdb.com.fdbapp.ws.WSCustomer;

public class CustomerGetContextRequest extends BaseRequest<Customer, WSCustomer>{
	String token;
	
	public CustomerGetContextRequest(String token) {
		super(Customer.class, WSCustomer.class);
		this.token = token;
	}
	
	public CustomerGetContextRequest() {
		super(Customer.class, WSCustomer.class);

	}

	@Override
	public Customer loadDataFromNetwork() throws Exception {
		return getService().getCustomerContext("Bearer " + token);

		
	}

	public String createCacheKey() {
		return "key";
	}
	
	

}
