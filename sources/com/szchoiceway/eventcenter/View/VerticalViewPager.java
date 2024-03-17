package com.szchoiceway.eventcenter.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOverlay;
import com.android.internal.widget.ViewPager;

public class VerticalViewPager extends ViewPager {
    private static final String TAG = "VerticalViewPager";

    public /* bridge */ /* synthetic */ ViewOverlay getOverlay() {
        return VerticalViewPager.super.getOverlay();
    }

    public VerticalViewPager(Context context) {
        super(context);
        init();
    }

    public VerticalViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        setPageTransformer(true, new VerticalPageTransformer());
        setOverScrollMode(2);
    }

    private class VerticalPageTransformer implements ViewPager.PageTransformer {
        private VerticalPageTransformer() {
        }

        public void transformPage(View view, float f) {
            if (f < -1.0f) {
                view.setAlpha(0.0f);
            } else if (f <= 1.0f) {
                view.setAlpha(1.0f);
                view.setTranslationX(((float) view.getWidth()) * (-f));
                view.setTranslationY(f * ((float) view.getHeight()));
            } else {
                view.setAlpha(0.0f);
            }
        }
    }

    private MotionEvent swapXY(MotionEvent motionEvent) {
        float width = ((float) getWidth()) * 2.5f;
        float height = (float) getHeight();
        float y = (motionEvent.getY() / height) * width;
        float x = (motionEvent.getX() / width) * height;
        Log.i(TAG, "swapXY: newX = " + y);
        Log.i(TAG, "swapXY: newY = " + x);
        motionEvent.setLocation(y, x);
        return motionEvent;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean onInterceptTouchEvent = VerticalViewPager.super.onInterceptTouchEvent(swapXY(motionEvent));
        swapXY(motionEvent);
        return onInterceptTouchEvent;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return VerticalViewPager.super.onTouchEvent(swapXY(motionEvent));
    }
}
