package dw.fdb.com.fdbapp.request;


import dw.fdb.com.fdbapp.model.Category;
import dw.fdb.com.fdbapp.ws.WSCategory;

public class CategoryGetRequest extends BaseRequest<Category.List, WSCategory> {
	int id;

	public CategoryGetRequest(int id) {
		super(Category.List.class, WSCategory.class);
		this.id = id;
	}

	@Override
	public Category.List loadDataFromNetwork() throws Exception {
		return getService().getChildrenCategory(id);

	}

	public String createCacheKey() {
		return "CategoryGetRequest";

	}

}
