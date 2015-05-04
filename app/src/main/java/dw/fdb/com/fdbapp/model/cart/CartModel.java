package dw.fdb.com.fdbapp.model.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Starlight on 01/05/2015.
 */
public class CartModel {
    @SerializedName("nb_product")
    @Expose
    private int nb_product;
    @Expose
    @SerializedName("cart_product_list")
    private List<CartProductList> cart_product_list = new ArrayList<CartProductList>();


    public List<CartProductList> getProductList() {
        return cart_product_list;
    }

    public void setProductList(List<CartProductList> productList) {
        this.cart_product_list = productList;
    }

    public int getNb_product() {
        return nb_product;
    }

    public void setNb_product(int nb_product) {
        this.nb_product = nb_product;
    }
}
