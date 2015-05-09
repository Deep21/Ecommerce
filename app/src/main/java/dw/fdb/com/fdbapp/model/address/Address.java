package dw.fdb.com.fdbapp.model.address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Address {

    @Expose
    private String company;
    @SerializedName("id_address")
    @Expose
    private int idAddress;
    @Expose
    private String alias;
    @Expose
    private String lastname;
    @Expose
    private String firstname;
    @Expose
    private String address1;
    @Expose
    private String address2;
    @Expose
    private int postcode;
    @Expose
    private String city;
    @Expose
    private int phone;
    @SerializedName("phone_mobile")
    @Expose
    private int phoneMobile;

    /**
     *
     * @return
     * The company
     */
    public String getCompany() {
        return company;
    }

    /**
     *
     * @param company
     * The company
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     *
     * @return
     * The idAddress
     */
    public int getIdAddress() {
        return idAddress;
    }

    /**
     *
     * @param idAddress
     * The id_address
     */
    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    /**
     *
     * @return
     * The alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     *
     * @param alias
     * The alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     *
     * @return
     * The lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     *
     * @param lastname
     * The lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     *
     * @return
     * The firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     *
     * @param firstname
     * The firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     *
     * @return
     * The address1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     *
     * @param address1
     * The address1
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     *
     * @return
     * The address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     *
     * @param address2
     * The address2
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     *
     * @return
     * The postcode
     */
    public int getPostcode() {
        return postcode;
    }

    /**
     *
     * @param postcode
     * The postcode
     */
    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    /**
     *
     * @return
     * The city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     * The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     * The phone
     */
    public int getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     * The phone
     */
    public void setPhone(int phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     * The phoneMobile
     */
    public int getPhoneMobile() {
        return phoneMobile;
    }

    /**
     *
     * @param phoneMobile
     * The phone_mobile
     */
    public void setPhoneMobile(int phoneMobile) {
        this.phoneMobile = phoneMobile;
    }



}
