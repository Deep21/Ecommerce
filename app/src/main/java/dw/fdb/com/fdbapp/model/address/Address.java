package dw.fdb.com.fdbapp.model.address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;


public class Address {

    @SerializedName("address_delivery")
    @Expose
    private List<AddressDelivery> addressDelivery = new ArrayList<AddressDelivery>();
    @SerializedName("address_invoice")
    @Expose
    private List<AddressInvoice> addressInvoice = new ArrayList<AddressInvoice>();

    /**
     * @return The addressDelivery
     */
    public List<AddressDelivery> getAddressDelivery() {
        return addressDelivery;
    }

    /**
     * @param addressDelivery The address_delivery
     */
    public void setAddressDelivery(List<AddressDelivery> addressDelivery) {
        this.addressDelivery = addressDelivery;
    }

    /**
     * @return The addressInvoice
     */
    public List<AddressInvoice> getAddressInvoice() {
        return addressInvoice;
    }

    /**
     * @param addressInvoice The address_invoice
     */
    public void setAddressInvoice(List<AddressInvoice> addressInvoice) {
        this.addressInvoice = addressInvoice;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(addressDelivery).append(addressInvoice).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Address) == false) {
            return false;
        }
        Address rhs = ((Address) other);
        return new EqualsBuilder().append(addressDelivery, rhs.addressDelivery).append(addressInvoice, rhs.addressInvoice).isEquals();
    }

}
