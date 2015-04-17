package dw.fdb.com.fdbapp.request;


import dw.fdb.com.fdbapp.model.CategoryModel;
import dw.fdb.com.fdbapp.ws.WSCategory;

public class CategoryGetRequest extends BaseRequest<CategoryModel, WSCategory> {
	int id;

	public CategoryGetRequest(int id) {
		super(CategoryModel.class, WSCategory.class);
		this.id = id;
	}

	@Override
	public CategoryModel loadDataFromNetwork() throws Exception {
		return getService().getChildrenCategory(id);

	}

	public String createCacheKey() {
		return "CategoryGetRequest";

	}

}
