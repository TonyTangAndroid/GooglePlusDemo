package io.anycopy.googleplusdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

public class CustomViewPager extends ViewPager {

    private boolean enabled = false;

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.enabled && super.onTouchEvent(event) && performClick();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.enabled && super.onInterceptTouchEvent(event);

    }

    @Override
    public boolean performClick() {
        return this.enabled && super.performClick();
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}