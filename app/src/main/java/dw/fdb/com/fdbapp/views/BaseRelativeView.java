package dw.fdb.com.fdbapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import dw.fdb.com.fdbapp.model.Item;

public class BaseRelativeView extends RelativeLayout implements Item {

    public BaseRelativeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }

    public BaseRelativeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public BaseRelativeView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public BaseRelativeView(Context context) {
        super(context);
    }

    @Override
    public View getView(LayoutInflater inflator, View convertView, ViewGroup parent, int position) {
        return null;
    }

    @Override
    public int getId(int position) {

        return 0;
    }

    @Override
    public int getViewType() {
        // TODO Auto-generated method stub
        return 0;
    }

}
