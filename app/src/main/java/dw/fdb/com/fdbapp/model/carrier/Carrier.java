package dw.fdb.com.fdbapp.model.carrier;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import dw.fdb.com.fdbapp.R;
import dw.fdb.com.fdbapp.adapter.CustomListAdapter;
import dw.fdb.com.fdbapp.model.Item;
import dw.fdb.com.fdbapp.model.address.AddressInvoice;

/**
 * Created by Starlight on 08/05/2015.
 */
public class Carrier implements Item{
    @SerializedName("default_carrier")
    @Expose
    private int defaultCarrier;
    @SerializedName("id_carrier")
    @Expose
    private int idCarrier;
    @SerializedName("id_reference")
    @Expose
    private int idReference;
    @SerializedName("id_tax_rules_group")
    @Expose
    private int idTaxRulesGroup;
    @Expose
    private String name;
    @Expose
    private String url;
    @Expose
    private int active;
    @Expose
    private int deleted;
    @SerializedName("shipping_handling")
    @Expose
    private int shippingHandling;
    @SerializedName("range_behavior")
    @Expose
    private int rangeBehavior;
    @SerializedName("is_module")
    @Expose
    private int isModule;
    @SerializedName("is_free")
    @Expose
    private int isFree;
    @SerializedName("shipping_external")
    @Expose
    private int shippingExternal;
    @SerializedName("need_range")
    @Expose
    private int needRange;
    @SerializedName("external_module_name")
    @Expose
    private String externalModuleName;
    @SerializedName("shipping_method")
    @Expose
    private int shippingMethod;
    @Expose
    private int position;
    @SerializedName("max_width")
    @Expose
    private int maxWidth;
    @SerializedName("max_height")
    @Expose
    private int maxHeight;
    @SerializedName("max_depth")
    @Expose
    private int maxDepth;
    @SerializedName("max_weight")
    @Expose
    private String maxWeight;
    @Expose
    private int grade;
    @SerializedName("id_delivery")
    @Expose
    private int idDelivery;
    @SerializedName("id_shop")
    @Expose
    private int idShop;
    @SerializedName("id_shop_group")
    @Expose
    private int idShopGroup;
    @SerializedName("id_range_price")
    @Expose
    private int idRangePrice;
    @SerializedName("id_range_weight")
    @Expose
    private int idRangeWeight;
    @SerializedName("id_zone")
    @Expose
    private int idZone;
    @Expose
    private int price;
    @SerializedName("id_lang")
    @Expose
    private int idLang;
    @Expose
    private String delay;

    /**
     *
     * @return
     * The defaultCarrier
     */
    public int getDefaultCarrier() {
        return defaultCarrier;
    }

    /**
     *
     * @param defaultCarrier
     * The default_carrier
     */
    public void setDefaultCarrier(int defaultCarrier) {
        this.defaultCarrier = defaultCarrier;
    }

    /**
     *
     * @return
     * The idCarrier
     */
    public int getIdCarrier() {
        return idCarrier;
    }

    /**
     *
     * @param idCarrier
     * The id_carrier
     */
    public void setIdCarrier(int idCarrier) {
        this.idCarrier = idCarrier;
    }

    /**
     *
     * @return
     * The idReference
     */
    public int getIdReference() {
        return idReference;
    }

    /**
     *
     * @param idReference
     * The id_reference
     */
    public void setIdReference(int idReference) {
        this.idReference = idReference;
    }

    /**
     *
     * @return
     * The idTaxRulesGroup
     */
    public int getIdTaxRulesGroup() {
        return idTaxRulesGroup;
    }

    /**
     *
     * @param idTaxRulesGroup
     * The id_tax_rules_group
     */
    public void setIdTaxRulesGroup(int idTaxRulesGroup) {
        this.idTaxRulesGroup = idTaxRulesGroup;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The active
     */
    public int getActive() {
        return active;
    }

    /**
     *
     * @param active
     * The active
     */
    public void setActive(int active) {
        this.active = active;
    }

    /**
     *
     * @return
     * The deleted
     */
    public int getDeleted() {
        return deleted;
    }

    /**
     *
     * @param deleted
     * The deleted
     */
    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    /**
     *
     * @return
     * The shippingHandling
     */
    public int getShippingHandling() {
        return shippingHandling;
    }

    /**
     *
     * @param shippingHandling
     * The shipping_handling
     */
    public void setShippingHandling(int shippingHandling) {
        this.shippingHandling = shippingHandling;
    }

    /**
     *
     * @return
     * The rangeBehavior
     */
    public int getRangeBehavior() {
        return rangeBehavior;
    }

    /**
     *
     * @param rangeBehavior
     * The range_behavior
     */
    public void setRangeBehavior(int rangeBehavior) {
        this.rangeBehavior = rangeBehavior;
    }

    /**
     *
     * @return
     * The isModule
     */
    public int getIsModule() {
        return isModule;
    }

    /**
     *
     * @param isModule
     * The is_module
     */
    public void setIsModule(int isModule) {
        this.isModule = isModule;
    }

    /**
     *
     * @return
     * The isFree
     */
    public int getIsFree() {
        return isFree;
    }

    /**
     *
     * @param isFree
     * The is_free
     */
    public void setIsFree(int isFree) {
        this.isFree = isFree;
    }

    /**
     *
     * @return
     * The shippingExternal
     */
    public int getShippingExternal() {
        return shippingExternal;
    }

    /**
     *
     * @param shippingExternal
     * The shipping_external
     */
    public void setShippingExternal(int shippingExternal) {
        this.shippingExternal = shippingExternal;
    }

    /**
     *
     * @return
     * The needRange
     */
    public int getNeedRange() {
        return needRange;
    }

    /**
     *
     * @param needRange
     * The need_range
     */
    public void setNeedRange(int needRange) {
        this.needRange = needRange;
    }

    /**
     *
     * @return
     * The externalModuleName
     */
    public String getExternalModuleName() {
        return externalModuleName;
    }

    /**
     *
     * @param externalModuleName
     * The external_module_name
     */
    public void setExternalModuleName(String externalModuleName) {
        this.externalModuleName = externalModuleName;
    }

    /**
     *
     * @return
     * The shippingMethod
     */
    public int getShippingMethod() {
        return shippingMethod;
    }

    /**
     *
     * @param shippingMethod
     * The shipping_method
     */
    public void setShippingMethod(int shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    /**
     *
     * @return
     * The position
     */
    public int getPosition() {
        return position;
    }

    /**
     *
     * @param position
     * The position
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     *
     * @return
     * The maxWidth
     */
    public int getMaxWidth() {
        return maxWidth;
    }

    /**
     *
     * @param maxWidth
     * The max_width
     */
    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    /**
     *
     * @return
     * The maxHeight
     */
    public int getMaxHeight() {
        return maxHeight;
    }

    /**
     *
     * @param maxHeight
     * The max_height
     */
    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    /**
     *
     * @return
     * The maxDepth
     */
    public int getMaxDepth() {
        return maxDepth;
    }

    /**
     *
     * @param maxDepth
     * The max_depth
     */
    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    /**
     *
     * @return
     * The maxWeight
     */
    public String getMaxWeight() {
        return maxWeight;
    }

    /**
     *
     * @param maxWeight
     * The max_weight
     */
    public void setMaxWeight(String maxWeight) {
        this.maxWeight = maxWeight;
    }

    /**
     *
     * @return
     * The grade
     */
    public int getGrade() {
        return grade;
    }

    /**
     *
     * @param grade
     * The grade
     */
    public void setGrade(int grade) {
        this.grade = grade;
    }

    /**
     *
     * @return
     * The idDelivery
     */
    public int getIdDelivery() {
        return idDelivery;
    }

    /**
     *
     * @param idDelivery
     * The id_delivery
     */
    public void setIdDelivery(int idDelivery) {
        this.idDelivery = idDelivery;
    }

    /**
     *
     * @return
     * The idShop
     */
    public int getIdShop() {
        return idShop;
    }

    /**
     *
     * @param idShop
     * The id_shop
     */
    public void setIdShop(int idShop) {
        this.idShop = idShop;
    }

    /**
     *
     * @return
     * The idShopGroup
     */
    public int getIdShopGroup() {
        return idShopGroup;
    }

    /**
     *
     * @param idShopGroup
     * The id_shop_group
     */
    public void setIdShopGroup(int idShopGroup) {
        this.idShopGroup = idShopGroup;
    }

    /**
     *
     * @return
     * The idRangePrice
     */
    public int getIdRangePrice() {
        return idRangePrice;
    }

    /**
     *
     * @param idRangePrice
     * The id_range_price
     */
    public void setIdRangePrice(int idRangePrice) {
        this.idRangePrice = idRangePrice;
    }

    /**
     *
     * @return
     * The idRangeWeight
     */
    public int getIdRangeWeight() {
        return idRangeWeight;
    }

    /**
     *
     * @param idRangeWeight
     * The id_range_weight
     */
    public void setIdRangeWeight(int idRangeWeight) {
        this.idRangeWeight = idRangeWeight;
    }

    /**
     *
     * @return
     * The idZone
     */
    public int getIdZone() {
        return idZone;
    }

    /**
     *
     * @param idZone
     * The id_zone
     */
    public void setIdZone(int idZone) {
        this.idZone = idZone;
    }

    /**
     *
     * @return
     * The price
     */
    public int getPrice() {
        return price;
    }

    /**
     *
     * @param price
     * The price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     *
     * @return
     * The idLang
     */
    public int getIdLang() {
        return idLang;
    }

    /**
     *
     * @param idLang
     * The id_lang
     */
    public void setIdLang(int idLang) {
        this.idLang = idLang;
    }

    /**
     *
     * @return
     * The delay
     */
    public String getDelay() {
        return delay;
    }

    /**
     *
     * @param delay
     * The delay
     */
    public void setDelay(String delay) {
        this.delay = delay;
    }


    @Override
    public View getView(LayoutInflater inflator, View convertView, ViewGroup parent, int position, CustomListAdapter.AdapterOnClickListner adapterOnClickListner) {

        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflator.inflate(R.layout.carrier_layout_fragment, parent, false);
            holder.carrier_name = (TextView) convertView.findViewById(R.id.carrier_name);
            holder.prix_ttc = (TextView) convertView.findViewById(R.id.address1);
            holder.delay = (TextView) convertView.findViewById(R.id.last_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.carrier_name.setText(getName());
        holder.prix_ttc.setText(""+getPrice());
        holder.delay.setText(getDelay());

        return convertView;
    }

    @Override
    public int getId(int position) {
        return 0;
    }

    @Override
    public int getViewType() {
        return CustomListAdapter.AddressType.ADDRESS_DELEVERY_LAYOUT.ordinal();
    }

    @SuppressWarnings("serial")
    public static class AddressList extends ArrayList<AddressInvoice> {
    }

    public static class ViewHolder {
        TextView carrier_name;
        TextView delay;
        TextView prix_ttc;


    }
}
