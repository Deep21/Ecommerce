package dw.fdb.com.fdbapp.model.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Starlight on 01/05/2015.
 */
public class CartModel {

    @Expose
    @SerializedName("cart_product_list")
    private List<CartProductList> cart_product_list = new ArrayList<CartProductList>();


    public List<CartProductList> getProductList() {
        return cart_product_list;
    }

    public void setProductList(List<CartProductList> productList) {
        this.cart_product_list = productList;
    }
}
