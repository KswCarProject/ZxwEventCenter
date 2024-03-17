package com.szchoiceway.eventcenter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;

public class RadarWndFrame extends RelativeLayout {
    private static final String TAG = "RadarWndFrame";
    private static final int WHAT_REFRESH_UI = 1001;
    private static boolean bEnableDebug = true;
    private static int[] iRadarFLCRes = {R.drawable.f2_1_bc, R.drawable.f2_2_bc, R.drawable.f2_3_bc, R.drawable.f2_4_bc, R.drawable.f2_5_bc, R.drawable.f2_6_bc, R.drawable.f2_7_bc, R.drawable.f2_8_bc};
    private static int[] iRadarFLRes = {R.drawable.f1_1_bc, R.drawable.f1_2_bc, R.drawable.f1_3_bc, R.drawable.f1_4_bc, R.drawable.f1_5_bc, R.drawable.f1_6_bc, R.drawable.f1_7_bc, R.drawable.f1_8_bc};
    private static int[] iRadarFRCRes = {R.drawable.f3_1_bc, R.drawable.f3_2_bc, R.drawable.f3_3_bc, R.drawable.f3_4_bc, R.drawable.f3_5_bc, R.drawable.f3_6_bc, R.drawable.f3_7_bc, R.drawable.f3_8_bc};
    private static int[] iRadarFRRes = {R.drawable.f4_1_bc, R.drawable.f4_2_bc, R.drawable.f4_3_bc, R.drawable.f4_4_bc, R.drawable.f4_5_bc, R.drawable.f4_6_bc, R.drawable.f4_7_bc, R.drawable.f4_8_bc};
    private static int[] iRadarRLCRes = {R.drawable.r2_1_bc, R.drawable.r2_2_bc, R.drawable.r2_3_bc, R.drawable.r2_4_bc, R.drawable.r2_5_bc, R.drawable.r2_6_bc, R.drawable.r2_7_bc, R.drawable.r2_8_bc};
    private static int[] iRadarRLRes = {R.drawable.r1_1_bc, R.drawable.r1_2_bc, R.drawable.r1_3_bc, R.drawable.r1_4_bc, R.drawable.r1_5_bc, R.drawable.r1_6_bc, R.drawable.r1_7_bc, R.drawable.r1_8_bc};
    private static int[] iRadarRRCRes = {R.drawable.r3_1_bc, R.drawable.r3_2_bc, R.drawable.r3_3_bc, R.drawable.r3_4_bc, R.drawable.r3_5_bc, R.drawable.r3_6_bc, R.drawable.r3_7_bc, R.drawable.r3_8_bc};
    private static int[] iRadarRRRes = {R.drawable.r4_1_bc, R.drawable.r4_2_bc, R.drawable.r4_3_bc, R.drawable.r4_4_bc, R.drawable.r4_5_bc, R.drawable.r4_6_bc, R.drawable.r4_7_bc, R.drawable.r4_8_bc};
    private static byte[] m_stBackRadarInfo = new byte[4];
    private static byte[] m_stHeadRadarInfo = new byte[4];
    private Context mContext = null;
    private ImageView mImageRadarFL = null;
    private ImageView mImageRadarFLC = null;
    private ImageView mImageRadarFR = null;
    private ImageView mImageRadarFRC = null;
    private ImageView mImageRadarRL = null;
    private ImageView mImageRadarRLC = null;
    private ImageView mImageRadarRR = null;
    private ImageView mImageRadarRRC = null;
    private int mLength = 4;
    private View mRootView = null;
    private Handler mhandler = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 1001) {
                Log.i(RadarWndFrame.TAG, "***WHAT_REFRESH_UI***");
                RadarWndFrame.this.OnRefreshCarRadarInfo();
            }
        }
    };

    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return super.generateLayoutParams(attributeSet);
    }

    public /* bridge */ /* synthetic */ ViewOverlay getOverlay() {
        return super.getOverlay();
    }

    public RadarWndFrame(Context context) {
        super(context);
    }

    public RadarWndFrame(Context context, int i) {
        super(context);
        this.mContext = context;
        init(i);
    }

    public RadarWndFrame(Context context, int i, int i2) {
        super(context);
        this.mContext = context;
        this.mLength = i2;
        m_stBackRadarInfo = new byte[i2];
        m_stHeadRadarInfo = new byte[i2];
        init(i);
    }

    private void init(int i) {
        View inflate = View.inflate(this.mContext, i, (ViewGroup) null);
        this.mRootView = inflate;
        addView(inflate);
        setBackgroundColor(ViewCompat.MEASURED_SIZE_MASK);
        this.mImageRadarFL = (ImageView) this.mRootView.findViewById(R.id.CarF1_1);
        this.mImageRadarFLC = (ImageView) this.mRootView.findViewById(R.id.CarF2_1);
        this.mImageRadarFRC = (ImageView) this.mRootView.findViewById(R.id.CarF3_1);
        this.mImageRadarFR = (ImageView) this.mRootView.findViewById(R.id.CarF4_1);
        ImageView imageView = this.mImageRadarFL;
        if (imageView != null) {
            imageView.setBackground((Drawable) null);
        }
        ImageView imageView2 = this.mImageRadarFLC;
        if (imageView2 != null) {
            imageView2.setBackground((Drawable) null);
        }
        ImageView imageView3 = this.mImageRadarFRC;
        if (imageView3 != null) {
            imageView3.setBackground((Drawable) null);
        }
        ImageView imageView4 = this.mImageRadarFR;
        if (imageView4 != null) {
            imageView4.setBackground((Drawable) null);
        }
        this.mImageRadarRL = (ImageView) this.mRootView.findViewById(R.id.CarR1_1);
        this.mImageRadarRLC = (ImageView) this.mRootView.findViewById(R.id.CarR2_1);
        this.mImageRadarRRC = (ImageView) this.mRootView.findViewById(R.id.CarR3_1);
        this.mImageRadarRR = (ImageView) this.mRootView.findViewById(R.id.CarR4_1);
        ImageView imageView5 = this.mImageRadarRL;
        if (imageView5 != null) {
            imageView5.setBackground((Drawable) null);
        }
        ImageView imageView6 = this.mImageRadarRLC;
        if (imageView6 != null) {
            imageView6.setBackground((Drawable) null);
        }
        ImageView imageView7 = this.mImageRadarRRC;
        if (imageView7 != null) {
            imageView7.setBackground((Drawable) null);
        }
        ImageView imageView8 = this.mImageRadarRR;
        if (imageView8 != null) {
            imageView8.setBackground((Drawable) null);
        }
    }

    public View getView() {
        return this.mRootView;
    }

    private void setViewVisible(View view, boolean z) {
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
    }

    private void rotationView(View view, float f) {
        if (view != null) {
            view.setRotation(f);
        }
    }

    private void setViewText(TextView textView, String str) {
        if (textView != null) {
            textView.setText(str);
        }
    }

    public boolean updateBackRadarData(byte[] bArr) {
        if (bEnableDebug) {
            Log.i(TAG, "updateBackRadarData =" + EventUtils.bytesToHexString(bArr));
        }
        System.arraycopy(bArr, 0, m_stBackRadarInfo, 0, bArr.length);
        if (bEnableDebug) {
            Log.i(TAG, "BackRadarData =" + EventUtils.bytesToHexString(m_stBackRadarInfo));
        }
        this.mhandler.sendEmptyMessage(1001);
        return true;
    }

    public boolean updateHeadRadarData(byte[] bArr) {
        if (bEnableDebug) {
            Log.i(TAG, "updateHeadRadarData =" + EventUtils.bytesToHexString(bArr));
        }
        System.arraycopy(bArr, 0, m_stHeadRadarInfo, 0, bArr.length);
        if (bEnableDebug) {
            Log.i(TAG, "HeadRadarData =" + EventUtils.bytesToHexString(m_stHeadRadarInfo));
        }
        this.mhandler.sendEmptyMessage(1001);
        return true;
    }

    public void OnRefreshCarRadarInfo() {
        ImageView imageView = this.mImageRadarFL;
        if (imageView != null) {
            byte[] bArr = m_stHeadRadarInfo;
            if (bArr[0] == 0 || bArr[0] > 10) {
                imageView.setBackground((Drawable) null);
            } else if (bArr[0] > 0 && bArr[0] <= 8) {
                imageView.setBackgroundResource(iRadarFLRes[bArr[0] - 1]);
            }
        }
        ImageView imageView2 = this.mImageRadarFLC;
        if (imageView2 != null) {
            byte[] bArr2 = m_stHeadRadarInfo;
            if (bArr2[1] == 0 || bArr2[1] > 10) {
                imageView2.setBackground((Drawable) null);
            } else if (bArr2[1] > 0 && bArr2[1] <= 8) {
                imageView2.setBackgroundResource(iRadarFLCRes[bArr2[1] - 1]);
            }
        }
        ImageView imageView3 = this.mImageRadarFRC;
        if (imageView3 != null) {
            byte[] bArr3 = m_stHeadRadarInfo;
            if (bArr3[2] == 0 || bArr3[2] > 10) {
                imageView3.setBackground((Drawable) null);
            } else if (bArr3[2] > 0 && bArr3[2] <= 10) {
                imageView3.setBackgroundResource(iRadarFRCRes[bArr3[2] - 1]);
            }
        }
        ImageView imageView4 = this.mImageRadarFR;
        if (imageView4 != null) {
            byte[] bArr4 = m_stHeadRadarInfo;
            if (bArr4[3] == 0 || bArr4[3] > 10) {
                imageView4.setBackground((Drawable) null);
            } else if (bArr4[3] > 0 && bArr4[3] <= 10) {
                imageView4.setBackgroundResource(iRadarFRRes[bArr4[3] - 1]);
            }
        }
        ImageView imageView5 = this.mImageRadarRL;
        if (imageView5 != null) {
            byte[] bArr5 = m_stBackRadarInfo;
            if (bArr5[0] == 0 || bArr5[0] > 10) {
                imageView5.setBackground((Drawable) null);
            } else if (bArr5[0] > 0 && bArr5[0] <= 10) {
                imageView5.setBackgroundResource(iRadarRLRes[bArr5[0] - 1]);
            }
        }
        ImageView imageView6 = this.mImageRadarRLC;
        if (imageView6 != null) {
            byte[] bArr6 = m_stBackRadarInfo;
            if (bArr6[1] == 0 || bArr6[1] > 10) {
                imageView6.setBackground((Drawable) null);
            } else if (bArr6[1] > 0 && bArr6[1] <= 10) {
                imageView6.setBackgroundResource(iRadarRLCRes[bArr6[1] - 1]);
            }
        }
        ImageView imageView7 = this.mImageRadarRRC;
        if (imageView7 != null) {
            byte[] bArr7 = m_stBackRadarInfo;
            if (bArr7[2] == 0 || bArr7[2] > 10) {
                imageView7.setBackground((Drawable) null);
            } else if (bArr7[2] > 0 && bArr7[2] <= 10) {
                imageView7.setBackgroundResource(iRadarRRCRes[bArr7[2] - 1]);
            }
        }
        ImageView imageView8 = this.mImageRadarRR;
        if (imageView8 != null) {
            byte[] bArr8 = m_stBackRadarInfo;
            if (bArr8[3] == 0 || bArr8[3] > 10) {
                imageView8.setBackground((Drawable) null);
            } else if (bArr8[3] > 0 && bArr8[3] <= 10) {
                imageView8.setBackgroundResource(iRadarRRRes[bArr8[3] - 1]);
            }
        }
    }
}
