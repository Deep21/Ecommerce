package dw.fdb.com.fdbapp.request;


import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import dw.fdb.com.fdbapp.model.Model;
import dw.fdb.com.fdbapp.ws.WSCategory;

public class SubCategoryRequest extends RetrofitSpiceRequest<Model, WSCategory> {
	int sub_category_id;
	
	public SubCategoryRequest() {
		super(Model.class, WSCategory.class);

	}
	
	public SubCategoryRequest(int sub_category_id) {
		super(Model.class, WSCategory.class);
		this.sub_category_id = sub_category_id;
	}

	@Override
	public Model loadDataFromNetwork() throws Exception {
		System.out.println(sub_category_id);
		try {
			return getService().getSubCategoriesById(sub_category_id);
		} catch (Exception e) {
			System.out.println(e.getCause().getMessage());
		}
		return null;
	}

	public String createCacheKey() {
		return "SubCategoryRequest";
	}
	
	

}
