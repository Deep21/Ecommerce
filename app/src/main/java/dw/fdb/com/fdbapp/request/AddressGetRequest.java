package dw.fdb.com.fdbapp.request;


import dw.fdb.com.fdbapp.model.address.AddressInvoice;
import dw.fdb.com.fdbapp.ws.WSAddress;


public class AddressGetRequest extends BaseRequest<AddressInvoice.AddressList, WSAddress> {
	private int id;
	
	public AddressGetRequest() {
		super(AddressInvoice.AddressList.class, WSAddress.class);

	}
	
	public AddressGetRequest(int id) {
		super(AddressInvoice.AddressList.class, WSAddress.class);
		this.id = id;
	}

	@Override
	public AddressInvoice.AddressList loadDataFromNetwork() throws Exception {
		return getService().getAddress();
	}


}
