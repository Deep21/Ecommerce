package dw.fdb.com.fdbapp.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

@SuppressLint("ClickableViewAccessibility")
public class SmartViewPager extends ViewPager {
    private boolean enabled;
    private GestureDetector mGestureDetector;
    private boolean mIsLockOnHorizontalAxis = false;

    public SmartViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGestureDetector = new GestureDetector(context, new XScrollDetector());
        this.enabled = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // decide if horizontal axis is locked already or we need to check the
        // scrolling direction

        if (enabled)
            return super.onTouchEvent(event);

        if (!mIsLockOnHorizontalAxis)
            mIsLockOnHorizontalAxis = mGestureDetector.onTouchEvent(event);

        // release the lock when finger is up
        if (event.getAction() == MotionEvent.ACTION_UP)
            mIsLockOnHorizontalAxis = false;

        getParent().requestDisallowInterceptTouchEvent(mIsLockOnHorizontalAxis);
        return false;
    }

    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.enabled) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }

    // -----------------------------------------------------------------------
    //
    // Inner Classes
    //
    // -----------------------------------------------------------------------
    private class XScrollDetector extends SimpleOnGestureListener {

        // -----------------------------------------------------------------------
        //
        // Methods
        //
        // -----------------------------------------------------------------------

        /**
         * @return true - if we're scrolling in X direction, false - in Y
         * direction.
         */
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            return Math.abs(distanceX) > Math.abs(distanceY);
        }

    }
}
