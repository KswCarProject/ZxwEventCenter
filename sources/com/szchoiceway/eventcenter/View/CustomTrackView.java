package com.szchoiceway.eventcenter.View;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;

public class CustomTrackView extends AppCompatImageView {
    private int levelCount = 29;

    public CustomTrackView(Context context) {
        super(context);
        init();
    }

    public CustomTrackView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public CustomTrackView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        setImageLevel(30);
    }

    public void setTrackData(boolean z, int i) {
        if (i > 540) {
            i = 540;
        }
        if (i == 0) {
            setImageLevel(30);
        }
        int i2 = (i * 29) / 540;
        if (z) {
            setImageLevel(i2 + 30);
        } else {
            setImageLevel(30 - i2);
        }
    }
}
