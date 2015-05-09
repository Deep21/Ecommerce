package dw.fdb.com.fdbapp.model.address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import dw.fdb.com.fdbapp.model.carrier.Carrier;

/**
 * Created by Starlight on 08/05/2015.
 */
public class AddressModel {
    @Expose
    @SerializedName("address_invoice")
    private AddressInvoice addressInvoice;
    @Expose
    @SerializedName("carrier")
    private List<Carrier> carrier;

    @Expose
    @SerializedName("address_delivery")
    private AddressDelivery address_delivery;

    public AddressDelivery getAddress_delivery() {
        return address_delivery;
    }

    public void setAddress_delivery(AddressDelivery address_delivery) {
        this.address_delivery = address_delivery;
    }

    public AddressInvoice getAddressInvoice() {
        return addressInvoice;
    }

    public void setAddressInvoice(AddressInvoice addressInvoice) {
        this.addressInvoice = addressInvoice;
    }


    public List<Carrier> getCarrier() {
        return carrier;
    }

    public void setCarrier(List<Carrier> carrier) {
        this.carrier = carrier;
    }
}