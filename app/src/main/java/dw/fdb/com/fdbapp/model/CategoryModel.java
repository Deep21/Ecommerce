package dw.fdb.com.fdbapp.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class CategoryModel{
    @Expose
    private List<Category> category = new ArrayList<Category>();

    /**
     *
     * @return
     * The category
     */
    public List<Category> getCategory() {
        return category;
    }

    /**
     *
     * @param category
     * The category
     */
    public void setCategory(List<Category> category) {
        this.category = category;
    }
}
