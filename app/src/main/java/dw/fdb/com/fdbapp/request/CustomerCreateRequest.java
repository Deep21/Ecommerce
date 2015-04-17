package dw.fdb.com.fdbapp.request;


import dw.fdb.com.fdbapp.model.Customer;
import dw.fdb.com.fdbapp.ws.WSCustomer;

public class CustomerCreateRequest extends BaseRequest<Customer, WSCustomer> {
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
        return getService().createCustomerAccount(customer);
    }

    public String createCacheKey() {
        return "key";
    }


}
