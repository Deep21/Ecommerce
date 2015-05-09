package dw.fdb.com.fdbapp.request;


import dw.fdb.com.fdbapp.model.address.AddressModel;
import dw.fdb.com.fdbapp.ws.WSAddress;


public class AddressGetRequest extends BaseRequest<AddressModel, WSAddress> {
	private int id;
	
	public AddressGetRequest() {
		super(AddressModel.class, WSAddress.class);

	}
	
	public AddressGetRequest(int id) {
		super(AddressModel.class, WSAddress.class);
		this.id = id;
	}

	@Override
	public AddressModel loadDataFromNetwork() throws Exception {
		return getService().getAddress();
	}


}
