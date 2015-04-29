package dw.fdb.com.fdbapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import dw.fdb.com.fdbapp.fragments.product.ProductImageFragment;

public class ViewPagerProductImageAdapter extends FragmentPagerAdapter {

    List<ProductImageFragment> fragmentimage;


    public ViewPagerProductImageAdapter(FragmentManager fm, List<ProductImageFragment> imgFragment) {
        super(fm);
        this.fragmentimage = imgFragment;

    }

    public void setFragmentimage(List<ProductImageFragment> fragmentimage) {
        this.fragmentimage = fragmentimage;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentimage.get(position);
    }

    @Override
    public int getCount() {
        return fragmentimage.size();
    }

}
