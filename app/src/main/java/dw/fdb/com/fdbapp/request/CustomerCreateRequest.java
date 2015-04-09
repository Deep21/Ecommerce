package dw.fdb.com.fdbapp.request;


import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import dw.fdb.com.fdbapp.model.Customer;
import dw.fdb.com.fdbapp.ws.WSCustomer;

public class CustomerCreateRequest extends RetrofitSpiceRequest<Customer, WSCustomer> {
	Customer customer;
	
	public CustomerCreateRequest(Customer customer) {
		super(Customer.class, WSCustomer.class);
		this.customer = customer;
	}
	
	public CustomerCreateRequest() {
		super(Customer.class, WSCustomer.class);

	}

	@Override
	public Customer loadDataFromNetwork() throws Exception {
		try {
			return getService().createCustomerAccount(customer);
		} catch (Exception e) {
			
		}
		return null;
		
	}

	public String createCacheKey() {
		return "key";
	}
	
	

}
