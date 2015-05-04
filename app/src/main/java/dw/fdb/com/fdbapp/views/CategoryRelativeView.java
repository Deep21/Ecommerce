package dw.fdb.com.fdbapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import dw.fdb.com.fdbapp.R;
import dw.fdb.com.fdbapp.adapter.CustomListAdapter;
import dw.fdb.com.fdbapp.model.Category;
import dw.fdb.com.fdbapp.model.Item;


public class CategoryRelativeView extends RelativeLayout implements Item {
	LayoutInflater inflater;
	@InjectView(R.id.libelle_category) TextView name;
	Category category;
	 
	public Category getItems() {
		return category;
	}


	public void setItems(Category category) {
		this.category = category;
		setUpFields();
	}

    public CategoryRelativeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
            inflater = LayoutInflater.from(context);
            inflater.inflate(R.layout.category_list_children, this , true);
            ButterKnife.inject(this);


    }

    public CategoryRelativeView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.category_list_children, this , true);
		ButterKnife.inject(this);
	}


	public CategoryRelativeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.category_list_children, this , true);
		ButterKnife.inject(this);
		
	}


	public CategoryRelativeView(Context context) {
		super(context);
		inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.category_list_children, this , true);
		ButterKnife.inject(this);
	}

	
	public static CategoryRelativeView inflate(ViewGroup context) {
		CategoryRelativeView categoryView = (CategoryRelativeView) LayoutInflater.from(context.getContext()).inflate(R.layout.category_list_layout, context, false);
		return categoryView;
	}

	
	public static CategoryRelativeView inflate(Context context) {
		CategoryRelativeView categoryView = (CategoryRelativeView) LayoutInflater.from(context).inflate(R.layout.category_list_layout, null, false);
		return categoryView;
	}


	@Override
	public View getView(LayoutInflater inflator, View convertView, ViewGroup parent, int position, CustomListAdapter.AdapterOnClickListner adapterOnClickListner) {

		return this;
	}


	@Override
	public int getId(int position) {
		return 0;
	}


	@Override
	public int getViewType() {
		return 0;
	}


	public void setUpFields() {
		name.setText(getItems().getName());
		
	}


}
