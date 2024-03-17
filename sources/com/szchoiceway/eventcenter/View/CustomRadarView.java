package com.szchoiceway.eventcenter.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.szchoiceway.eventcenter.EventUtils;
import com.szchoiceway.eventcenter.R;
import com.szchoiceway.eventcenter.SysProviderOpt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomRadarView extends View {
    private static final int TYPE_FRONT_RADAR = 0;
    private static final int TYPE_REAR_RADAR = 0;
    private Bitmap mFrontBg;
    private List<Bitmap> mFrontRadarBitmaps1;
    private List<Bitmap> mFrontRadarBitmaps2;
    private List<Bitmap> mFrontRadarBitmaps3;
    private List<Bitmap> mFrontRadarBitmaps4;
    private int[] mFrontRadarData = {0, 0, 0, 0};
    private int[] mFrontRadarLevels = {0, 0, 0, 0};
    private List<List<Bitmap>> mFrontRadarList;
    private float mLeftOffset1 = 4.0f;
    private float mLeftOffset2 = 103.0f;
    private float mLeftOffset3 = 187.0f;
    private float mLeftOffset4 = 220.0f;
    private float[] mLeftOffsets;
    private Paint mPaint;
    private Bitmap mRearBg;
    private List<Bitmap> mRearRadarBitmaps1;
    private List<Bitmap> mRearRadarBitmaps2;
    private List<Bitmap> mRearRadarBitmaps3;
    private List<Bitmap> mRearRadarBitmaps4;
    private int[] mRearRadarData = {0, 0, 0, 0};
    private int[] mRearRadarLevels = {0, 0, 0, 0};
    private List<List<Bitmap>> mRearRadarList;
    private int type = 0;

    public CustomRadarView(Context context) {
        super(context);
    }

    public CustomRadarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public CustomRadarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        int[] iArr;
        int i;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int i2;
        int[] iArr5;
        int[] iArr6;
        String recordValue = SysProviderOpt.getInstance(this.mContext).getRecordValue(SysProviderOpt.KSW_UI_RESOLUTION, "1920x720");
        if ("1280x480".equals(recordValue)) {
            this.mLeftOffset1 *= 0.666667f;
            this.mLeftOffset2 *= 0.666667f;
            this.mLeftOffset3 *= 0.666667f;
            this.mLeftOffset4 *= 0.666667f;
        }
        int i3 = 0;
        this.mLeftOffsets = new float[]{this.mLeftOffset1, this.mLeftOffset2, this.mLeftOffset3, this.mLeftOffset4};
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CustomRadarView);
        this.type = obtainStyledAttributes.getInt(0, 0);
        Matrix matrix = new Matrix();
        matrix.setScale(0.666667f, 0.666667f);
        if (this.type == 0) {
            this.mFrontBg = EventUtils.drawableToBitmap(this.mContext.getDrawable(R.drawable.mipi_reverse_radar_shangxian));
            if ("1280x480".equals(recordValue)) {
                Bitmap bitmap = this.mFrontBg;
                this.mFrontBg = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), this.mFrontBg.getHeight(), matrix, true);
            }
            this.mFrontRadarList = new ArrayList();
            this.mFrontRadarBitmaps1 = new ArrayList();
            this.mFrontRadarBitmaps2 = new ArrayList();
            this.mFrontRadarBitmaps3 = new ArrayList();
            this.mFrontRadarBitmaps4 = new ArrayList();
            int[] iArr7 = {R.drawable.mipi_reverse_radar_shang1_1, R.drawable.mipi_reverse_radar_shang1_2, R.drawable.mipi_reverse_radar_shang1_3, R.drawable.mipi_reverse_radar_shang1_4, R.drawable.mipi_reverse_radar_shang1_5, R.drawable.mipi_reverse_radar_shang1_6};
            int[] iArr8 = {R.drawable.mipi_reverse_radar_shang2_1, R.drawable.mipi_reverse_radar_shang2_2, R.drawable.mipi_reverse_radar_shang2_3, R.drawable.mipi_reverse_radar_shang2_4, R.drawable.mipi_reverse_radar_shang2_5, R.drawable.mipi_reverse_radar_shang2_6};
            int[] iArr9 = {R.drawable.mipi_reverse_radar_shang3_1, R.drawable.mipi_reverse_radar_shang3_2, R.drawable.mipi_reverse_radar_shang3_3, R.drawable.mipi_reverse_radar_shang3_4, R.drawable.mipi_reverse_radar_shang3_5, R.drawable.mipi_reverse_radar_shang3_6};
            int[] iArr10 = {R.drawable.mipi_reverse_radar_shang4_1, R.drawable.mipi_reverse_radar_shang4_2, R.drawable.mipi_reverse_radar_shang4_3, R.drawable.mipi_reverse_radar_shang4_4, R.drawable.mipi_reverse_radar_shang4_5, R.drawable.mipi_reverse_radar_shang4_6};
            int i4 = 0;
            while (i4 < 6) {
                Bitmap drawableToBitmap = EventUtils.drawableToBitmap(this.mContext.getDrawable(iArr7[i4]));
                if ("1280x480".equals(recordValue)) {
                    i2 = i4;
                    iArr6 = iArr10;
                    iArr5 = iArr9;
                    iArr4 = iArr8;
                    drawableToBitmap = Bitmap.createBitmap(drawableToBitmap, 0, 0, drawableToBitmap.getWidth(), drawableToBitmap.getHeight(), matrix, true);
                } else {
                    i2 = i4;
                    iArr6 = iArr10;
                    iArr5 = iArr9;
                    iArr4 = iArr8;
                }
                this.mFrontRadarBitmaps1.add(drawableToBitmap);
                i4 = i2 + 1;
                iArr10 = iArr6;
                iArr9 = iArr5;
                iArr8 = iArr4;
            }
            int[] iArr11 = iArr10;
            int[] iArr12 = iArr9;
            int[] iArr13 = iArr8;
            this.mFrontRadarList.add(this.mFrontRadarBitmaps1);
            for (int i5 = 0; i5 < 6; i5++) {
                Bitmap drawableToBitmap2 = EventUtils.drawableToBitmap(this.mContext.getDrawable(iArr13[i5]));
                if ("1280x480".equals(recordValue)) {
                    drawableToBitmap2 = Bitmap.createBitmap(drawableToBitmap2, 0, 0, drawableToBitmap2.getWidth(), drawableToBitmap2.getHeight(), matrix, true);
                }
                this.mFrontRadarBitmaps2.add(drawableToBitmap2);
            }
            this.mFrontRadarList.add(this.mFrontRadarBitmaps2);
            for (int i6 = 0; i6 < 6; i6++) {
                Bitmap drawableToBitmap3 = EventUtils.drawableToBitmap(this.mContext.getDrawable(iArr12[i6]));
                if ("1280x480".equals(recordValue)) {
                    drawableToBitmap3 = Bitmap.createBitmap(drawableToBitmap3, 0, 0, drawableToBitmap3.getWidth(), drawableToBitmap3.getHeight(), matrix, true);
                }
                this.mFrontRadarBitmaps3.add(drawableToBitmap3);
            }
            this.mFrontRadarList.add(this.mFrontRadarBitmaps3);
            while (i3 < 6) {
                Bitmap drawableToBitmap4 = EventUtils.drawableToBitmap(this.mContext.getDrawable(iArr11[i3]));
                if ("1280x480".equals(recordValue)) {
                    drawableToBitmap4 = Bitmap.createBitmap(drawableToBitmap4, 0, 0, drawableToBitmap4.getWidth(), drawableToBitmap4.getHeight(), matrix, true);
                }
                this.mFrontRadarBitmaps4.add(drawableToBitmap4);
                i3++;
            }
            this.mFrontRadarList.add(this.mFrontRadarBitmaps4);
        } else {
            this.mRearBg = EventUtils.drawableToBitmap(this.mContext.getDrawable(R.drawable.mipi_reverse_radar_xiaxian));
            if ("1280x480".equals(recordValue)) {
                Bitmap bitmap2 = this.mRearBg;
                this.mRearBg = Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.getWidth(), this.mRearBg.getHeight(), matrix, true);
            }
            this.mRearRadarList = new ArrayList();
            this.mRearRadarBitmaps1 = new ArrayList();
            this.mRearRadarBitmaps2 = new ArrayList();
            this.mRearRadarBitmaps3 = new ArrayList();
            this.mRearRadarBitmaps4 = new ArrayList();
            int[] iArr14 = {R.drawable.mipi_reverse_radar_xia1_1, R.drawable.mipi_reverse_radar_xia1_2, R.drawable.mipi_reverse_radar_xia1_3, R.drawable.mipi_reverse_radar_xia1_4, R.drawable.mipi_reverse_radar_xia1_5, R.drawable.mipi_reverse_radar_xia1_6};
            int[] iArr15 = {R.drawable.mipi_reverse_radar_xia2_1, R.drawable.mipi_reverse_radar_xia2_2, R.drawable.mipi_reverse_radar_xia2_3, R.drawable.mipi_reverse_radar_xia2_4, R.drawable.mipi_reverse_radar_xia2_5, R.drawable.mipi_reverse_radar_xia2_6};
            int[] iArr16 = {R.drawable.mipi_reverse_radar_xia3_1, R.drawable.mipi_reverse_radar_xia3_2, R.drawable.mipi_reverse_radar_xia3_3, R.drawable.mipi_reverse_radar_xia3_4, R.drawable.mipi_reverse_radar_xia3_5, R.drawable.mipi_reverse_radar_xia3_6};
            int[] iArr17 = {R.drawable.mipi_reverse_radar_xia4_1, R.drawable.mipi_reverse_radar_xia4_2, R.drawable.mipi_reverse_radar_xia4_3, R.drawable.mipi_reverse_radar_xia4_4, R.drawable.mipi_reverse_radar_xia4_5, R.drawable.mipi_reverse_radar_xia4_6};
            int i7 = 0;
            while (i7 < 6) {
                Bitmap drawableToBitmap5 = EventUtils.drawableToBitmap(this.mContext.getDrawable(iArr14[i7]));
                if ("1280x480".equals(recordValue)) {
                    i = i7;
                    iArr3 = iArr17;
                    iArr2 = iArr16;
                    iArr = iArr15;
                    drawableToBitmap5 = Bitmap.createBitmap(drawableToBitmap5, 0, 0, drawableToBitmap5.getWidth(), drawableToBitmap5.getHeight(), matrix, true);
                } else {
                    i = i7;
                    iArr3 = iArr17;
                    iArr2 = iArr16;
                    iArr = iArr15;
                }
                this.mRearRadarBitmaps1.add(drawableToBitmap5);
                i7 = i + 1;
                iArr17 = iArr3;
                iArr16 = iArr2;
                iArr15 = iArr;
            }
            int[] iArr18 = iArr17;
            int[] iArr19 = iArr16;
            int[] iArr20 = iArr15;
            this.mRearRadarList.add(this.mRearRadarBitmaps1);
            for (int i8 = 0; i8 < 6; i8++) {
                Bitmap drawableToBitmap6 = EventUtils.drawableToBitmap(this.mContext.getDrawable(iArr20[i8]));
                if ("1280x480".equals(recordValue)) {
                    drawableToBitmap6 = Bitmap.createBitmap(drawableToBitmap6, 0, 0, drawableToBitmap6.getWidth(), drawableToBitmap6.getHeight(), matrix, true);
                }
                this.mRearRadarBitmaps2.add(drawableToBitmap6);
            }
            this.mRearRadarList.add(this.mRearRadarBitmaps2);
            for (int i9 = 0; i9 < 6; i9++) {
                Bitmap drawableToBitmap7 = EventUtils.drawableToBitmap(this.mContext.getDrawable(iArr19[i9]));
                if ("1280x480".equals(recordValue)) {
                    drawableToBitmap7 = Bitmap.createBitmap(drawableToBitmap7, 0, 0, drawableToBitmap7.getWidth(), drawableToBitmap7.getHeight(), matrix, true);
                }
                this.mRearRadarBitmaps3.add(drawableToBitmap7);
            }
            this.mRearRadarList.add(this.mRearRadarBitmaps3);
            while (i3 < 6) {
                Bitmap drawableToBitmap8 = EventUtils.drawableToBitmap(this.mContext.getDrawable(iArr18[i3]));
                if ("1280x480".equals(recordValue)) {
                    drawableToBitmap8 = Bitmap.createBitmap(drawableToBitmap8, 0, 0, drawableToBitmap8.getWidth(), drawableToBitmap8.getHeight(), matrix, true);
                }
                this.mRearRadarBitmaps4.add(drawableToBitmap8);
                i3++;
            }
            this.mRearRadarList.add(this.mRearRadarBitmaps4);
        }
        obtainStyledAttributes.recycle();
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setFilterBitmap(true);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = 0;
        if (this.type == 0) {
            while (true) {
                int[] iArr = this.mFrontRadarLevels;
                if (i < iArr.length) {
                    int i2 = iArr[i];
                    if (i2 > 0) {
                        canvas.drawBitmap((Bitmap) this.mFrontRadarList.get(i).get(i2 - 1), this.mLeftOffsets[i], 0.0f, this.mPaint);
                    }
                    i++;
                } else {
                    canvas.drawBitmap(this.mFrontBg, 0.0f, 0.0f, this.mPaint);
                    return;
                }
            }
        } else {
            while (true) {
                int[] iArr2 = this.mRearRadarLevels;
                if (i < iArr2.length) {
                    if (iArr2[i] > 0) {
                        canvas.drawBitmap((Bitmap) this.mRearRadarList.get(i).get(this.mRearRadarLevels[i] - 1), this.mLeftOffsets[i], 0.0f, this.mPaint);
                    }
                    i++;
                } else {
                    canvas.drawBitmap(this.mRearBg, 0.0f, 0.0f, this.mPaint);
                    return;
                }
            }
        }
    }

    private int countLevel(int i) {
        int i2 = 0;
        if (i > 252) {
            return 0;
        }
        int i3 = i / 42;
        if (i % 42 > 0) {
            i2 = 1;
        }
        return i3 + i2;
    }

    public void setFrontRadarData(int[] iArr) {
        int[] iArr2 = this.mFrontRadarData;
        System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
        int[] iArr3 = {countLevel(this.mFrontRadarData[0]), countLevel(this.mFrontRadarData[1]), countLevel(this.mFrontRadarData[2]), countLevel(this.mFrontRadarData[3])};
        if (!Arrays.equals(this.mFrontRadarLevels, iArr3)) {
            System.arraycopy(iArr3, 0, this.mFrontRadarLevels, 0, 4);
            postInvalidate();
        }
    }

    public void setRearRadarData(int[] iArr) {
        int[] iArr2 = this.mRearRadarData;
        System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
        int[] iArr3 = {countLevel(this.mRearRadarData[0]), countLevel(this.mRearRadarData[1]), countLevel(this.mRearRadarData[2]), countLevel(this.mRearRadarData[3])};
        if (!Arrays.equals(this.mRearRadarLevels, iArr3)) {
            System.arraycopy(iArr3, 0, this.mRearRadarLevels, 0, 4);
            postInvalidate();
        }
    }
}
