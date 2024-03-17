package com.szchoiceway.eventcenter.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Point;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import com.szchoiceway.eventcenter.R;
import java.util.Calendar;

public class MyClockView extends View {
    private static final int SETTING_MIMUTE = 1;
    private static final int SETTING_SECOND = 0;
    private static final String TAG = "MyClockView";
    private boolean bInitComplete;
    private int clockCenterX;
    private int clockCenterY;
    private int clockDrawableId;
    private int clockHeith;
    private int clockWidth;
    private int clockX;
    private int clockY;
    private int hourDrawableId;
    private boolean isMoving;
    private boolean isUserTime;
    private Bitmap mClockBitmap;
    private Context mContext;
    private MyTime mCurTime;
    private Bitmap mHourBitmap;
    private int mHourOffsetY;
    private int mHourPosX;
    private int mHourPosY;
    private Bitmap mMinuteBitmap;
    private int mMinuteOffsetY;
    private int mMinutePosX;
    private int mMinutePosY;
    private Bitmap mSecondBitmap;
    private int mSecondOffsetY;
    private int mSecondPosX;
    private int mSecondPosY;
    private int minuteDrawableId;
    private Paint paint;
    private int secondDrawableId;
    WindowManager windowManager;

    public MyClockView(Context context) {
        this(context, (AttributeSet) null);
    }

    public MyClockView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MyClockView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mHourOffsetY = 18;
        this.mMinuteOffsetY = 18;
        this.mSecondOffsetY = 18;
        this.bInitComplete = false;
        this.isUserTime = false;
        this.isMoving = false;
        this.clockDrawableId = R.drawable.dial_bk_ksw;
        this.hourDrawableId = R.drawable.hand_hour_ksw_2;
        this.minuteDrawableId = R.drawable.hand_minute_ksw_2;
        this.secondDrawableId = R.drawable.hand_second_ksw_2;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MyClockView);
        this.clockDrawableId = obtainStyledAttributes.getResourceId(0, this.clockDrawableId);
        this.hourDrawableId = obtainStyledAttributes.getResourceId(1, this.hourDrawableId);
        this.minuteDrawableId = obtainStyledAttributes.getResourceId(2, this.minuteDrawableId);
        this.secondDrawableId = obtainStyledAttributes.getResourceId(3, this.secondDrawableId);
        this.mContext = context;
        this.windowManager = (WindowManager) context.getSystemService("window");
        this.mCurTime = new MyTime();
        init();
    }

    public void init() {
        Paint paint2 = new Paint();
        this.paint = paint2;
        paint2.setAntiAlias(true);
        this.paint.setDither(true);
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), this.clockDrawableId);
        this.mClockBitmap = decodeResource;
        this.clockWidth = decodeResource.getWidth();
        this.clockHeith = this.mClockBitmap.getHeight();
        this.clockX = (this.windowManager.getDefaultDisplay().getWidth() / 2) - (this.clockWidth / 2);
        this.clockY = ((this.windowManager.getDefaultDisplay().getHeight() / 2) - this.clockHeith) + ((int) this.mContext.getResources().getDimension(R.dimen.clock_y_offset));
        this.mHourBitmap = BitmapFactory.decodeResource(getResources(), this.hourDrawableId);
        this.mMinuteBitmap = BitmapFactory.decodeResource(getResources(), this.minuteDrawableId);
        this.mSecondBitmap = BitmapFactory.decodeResource(getResources(), this.secondDrawableId);
        calcPointPosition();
        calcCenter();
        this.bInitComplete = true;
        this.mCurTime.initBySystem();
    }

    public void setClockXY(int i, int i2) {
        this.clockX = i - (this.clockWidth / 2);
        this.clockY = i2 - this.clockHeith;
        calcCenter();
    }

    public void setPointOffset(int i, int i2, int i3) {
        this.mHourOffsetY = i;
        this.mMinuteOffsetY = i2;
        this.mSecondOffsetY = i3;
        calcPointPosition();
    }

    public void calcCenter() {
        Bitmap bitmap = this.mClockBitmap;
        if (bitmap != null) {
            this.clockCenterX = this.clockX + (bitmap.getWidth() / 2);
            this.clockCenterY = this.clockY + (this.mClockBitmap.getHeight() / 2);
        }
    }

    public void calcPointPosition() {
        Bitmap bitmap = this.mHourBitmap;
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = this.mHourBitmap.getHeight();
            this.mHourPosX = (-width) / 2;
            this.mHourPosY = (-height) / 2;
        }
        Bitmap bitmap2 = this.mMinuteBitmap;
        if (bitmap2 != null) {
            int width2 = bitmap2.getWidth();
            int height2 = this.mMinuteBitmap.getHeight();
            this.mMinutePosX = (-width2) / 2;
            this.mMinutePosY = (-height2) / 2;
        }
        Bitmap bitmap3 = this.mSecondBitmap;
        if (bitmap3 != null) {
            int width3 = bitmap3.getWidth();
            int height3 = this.mSecondBitmap.getHeight();
            this.mSecondPosX = (-width3) / 2;
            this.mSecondPosY = (-height3) / 2;
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.bInitComplete) {
            canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
            drawClock(canvas);
            drawHour(canvas);
            drawMinute(canvas);
            drawSecond(canvas);
            if (!this.isUserTime) {
                this.mCurTime.initBySystem();
            } else {
                initUserTime();
            }
            postInvalidate();
        }
    }

    private void initUserTime() {
        Calendar.getInstance().setTimeInMillis(System.currentTimeMillis());
        int i = this.mCurTime.mHour;
        int i2 = this.mCurTime.mMinute;
        int i3 = this.mCurTime.mSecond;
        if (!this.isMoving) {
            SystemClock.sleep(1000);
            i3++;
        }
        if (i3 == 60) {
            i2++;
            if (i2 == 60) {
                i++;
                if (i == 12) {
                    i = 0;
                }
                i2 = 0;
            }
            i3 = 0;
        }
        this.mCurTime.mHour = i;
        this.mCurTime.mMinute = i2;
        this.mCurTime.mSecond = i3;
        this.mCurTime.calcDegreeByTime();
    }

    public void drawClock(Canvas canvas) {
        Bitmap bitmap = this.mClockBitmap;
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, (float) this.clockX, (float) this.clockY, (Paint) null);
        }
    }

    private void drawHour(Canvas canvas) {
        if (this.mHourBitmap != null) {
            canvas.save();
            canvas.translate((float) this.clockCenterX, (float) this.clockCenterY);
            canvas.rotate((float) this.mCurTime.mHourDegree);
            canvas.drawBitmap(this.mHourBitmap, (float) this.mHourPosX, (float) this.mHourPosY, this.paint);
            canvas.restore();
        }
    }

    public void drawMinute(Canvas canvas) {
        if (this.mMinuteBitmap != null) {
            canvas.save();
            canvas.translate((float) this.clockCenterX, (float) this.clockCenterY);
            canvas.rotate((float) this.mCurTime.mMinuteDegree);
            canvas.drawBitmap(this.mMinuteBitmap, (float) this.mMinutePosX, (float) this.mMinutePosY, this.paint);
            canvas.restore();
        }
    }

    public void drawSecond(Canvas canvas) {
        if (this.mSecondBitmap != null) {
            canvas.save();
            canvas.translate((float) this.clockCenterX, (float) this.clockCenterY);
            canvas.rotate((float) this.mCurTime.mSecondDegree);
            canvas.drawBitmap(this.mSecondBitmap, (float) this.mSecondPosX, (float) this.mSecondPosY, this.paint);
            canvas.restore();
        }
    }

    class MyTime {
        private Calendar mCalendar;
        int mHour = 0;
        int mHourDegree = 0;
        int mMinute = 0;
        int mMinuteDegree = 0;
        int mPreDegree = 0;
        int mSecond = 0;
        int mSecondDegree = 0;

        MyTime() {
        }

        public void initBySystem() {
            long currentTimeMillis = System.currentTimeMillis();
            Calendar instance = Calendar.getInstance();
            this.mCalendar = instance;
            instance.setTimeInMillis(currentTimeMillis);
            this.mHour = this.mCalendar.get(11);
            this.mMinute = this.mCalendar.get(12);
            this.mSecond = this.mCalendar.get(13);
            calcDegreeByTime();
        }

        public void calcDegreeByTime() {
            int i = this.mSecond;
            int i2 = i * 6;
            this.mSecondDegree = i2;
            this.mPreDegree = i2;
            int i3 = (this.mMinute * 6) + (i / 10);
            this.mMinuteDegree = i3;
            this.mHourDegree = ((this.mHour % 12) * 30) + (i3 / 12);
        }

        public void calcTime(boolean z) {
            int i = this.mSecondDegree;
            if (i >= 360) {
                this.mSecondDegree = i - 360;
            }
            int i2 = this.mSecondDegree;
            if (i2 < 0) {
                this.mSecondDegree = i2 + 360;
            }
            this.mSecond = (int) ((((double) this.mSecondDegree) / 360.0d) * 60.0d);
            if (deasil()) {
                if (this.mSecondDegree < this.mPreDegree) {
                    int i3 = this.mMinute + 1;
                    this.mMinute = i3;
                    if (i3 == 60) {
                        this.mMinute = 0;
                        this.mHour++;
                    }
                }
            } else if (this.mSecondDegree > this.mPreDegree) {
                int i4 = this.mMinute - 1;
                this.mMinute = i4;
                if (i4 < 0) {
                    this.mMinute = i4 + 60;
                    this.mHour--;
                }
            }
            this.mMinuteDegree = (this.mMinute * 6) + (this.mSecond / 10);
            this.mPreDegree = this.mSecondDegree;
            Log.i(MyClockView.TAG, "mHourDegree = " + this.mHourDegree + ", mMinuteDegree = " + this.mMinuteDegree);
            if (z) {
                calcDegreeByTime();
            }
        }

        public boolean deasil() {
            int i = this.mSecondDegree;
            int i2 = this.mPreDegree;
            return i >= i2 ? i - i2 < 180 : i2 - i > 180;
        }
    }

    public void calcDegree(int i, int i2, boolean z) {
        Point point = new Point(i - this.clockCenterX, -(i2 - this.clockCenterY));
        this.mCurTime.mSecondDegree = MyDegreeAdapter.GetRadianByPos(point);
        this.mCurTime.calcTime(z);
    }
}
