package dw.fdb.com.fdbapp.ws;


import dw.fdb.com.fdbapp.model.CategoryModel;
import dw.fdb.com.fdbapp.model.Model;
import retrofit.http.GET;
import retrofit.http.Path;


public interface WSCategory {

    @GET("/category/{id_parent}/get")
    public CategoryModel getChildrenCategory(@Path("id_parent") int id_parent);

    @GET("/category/{sub_category_id}/getcategories.json")
    public Model getSubCategoriesById(@Path("sub_category_id") int sub_category_id);

}
