package dw.fdb.com.fdbapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PicassoImageView extends ImageView {
	
	public PicassoImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}


	public PicassoImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}


	public PicassoImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}


	public PicassoImageView(Context context) {
		super(context);
	}


	public void loadFromUrl(String uri){
		Picasso.with(getContext()).load(uri).into(this);
	}
	
	public static PicassoImageView getInstance(Context context) {
		PicassoImageView imageView = new PicassoImageView(context, null, 0);
		return imageView;

	}

}
