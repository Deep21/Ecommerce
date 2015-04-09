package dw.fdb.com.fdbapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import dw.fdb.com.fdbapp.adapter.CustommListAdapter;
import dw.fdb.com.fdbapp.model.Item;


public class EndlessListView extends ListView implements OnScrollListener {
	private EndlessListner endlessListner;
	private CustommListAdapter adapter;
	
	private boolean isLoading;

	public interface EndlessListner {
		public void onLoadMore();
	}

	public EndlessListView(Context context, AttributeSet attrs,int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);

	}

	public EndlessListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.setOnScrollListener(this);
	}

	public EndlessListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setOnScrollListener(this);
	}

	public EndlessListView(Context context) {
		super(context);
		this.setOnScrollListener(this);
	}
	
	
	
	public void setListner(EndlessListner endlessListner) {
		this.endlessListner = endlessListner;

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

		if (getAdapter() == null)
			return;

		if (getAdapter().getCount() == 0)
			return;

		int l = visibleItemCount + firstVisibleItem;

		if (l >= totalItemCount && !isLoading) {
			isLoading = true;
			System.out.println("on load");
			if (endlessListner != null)
				endlessListner.onLoadMore();

		}
	}
	


	public void onLoadMoreComplete() {
		isLoading = false;

	}
	
	@Override
	public void setAdapter(ListAdapter adapter) {
		super.setAdapter(adapter);
		this.adapter = (CustommListAdapter)adapter;
	}

	

	
	public void addNewData(List<Item> data) {
		adapter.setCustomItems(data);
		adapter.notifyDataSetChanged();
		isLoading = false;
	}

	@Override
	public ListAdapter getAdapter() {
		return super.getAdapter();
	}

}
