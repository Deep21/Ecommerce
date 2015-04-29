package dw.fdb.com.fdbapp.fragments.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import dw.fdb.com.fdbapp.R;
import dw.fdb.com.fdbapp.model.Image;

public class ProductImageFragment extends Fragment {
    ImageView product_image;

    public static ProductImageFragment newInstance(String imageUrl) {
        Bundle b = new Bundle();
        b.putString(Image.ARGS_IMAGE_URL, imageUrl);
        ProductImageFragment productImageFragment = new ProductImageFragment();
        productImageFragment.setArguments(b);
        return productImageFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.image_fragment, container, false);
        product_image = (ImageView) v.findViewById(R.id.product_viewpager_image);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle b = getArguments();
        if (b != null) {
            String image_url = b.getString(Image.ARGS_IMAGE_URL);
            Picasso.with(getActivity()).load(image_url).into(product_image);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("ProductImageFragment", "onDestroy");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("ProductImageFragment", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("ProductImageFragment", "onDestroyView");
    }

    @Override
    public void onResume() {
        super.onResume();
    }


}
