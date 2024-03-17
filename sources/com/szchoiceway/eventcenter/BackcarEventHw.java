package com.szchoiceway.eventcenter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.mylibrary.BuildConfig;
import com.szchoiceway.eventcenter.View.CustomRadarView;
import com.szchoiceway.eventcenter.View.CustomTrackView;
import java.util.Arrays;

public class BackcarEventHw implements View.OnTouchListener {
    private static final int HANDLER_ORIGINAL = 5;
    private static final int HANDLER_REFRESH_BELT = 3;
    private static final int HANDLER_REFRESH_HANDBRAKE = 4;
    private static final int HANDLER_REFRESH_LIGHT = 2;
    private static final int HANDLER_REFRESH_SPEED = 0;
    private static final int HANDLER_REFRESH_TEMP = 1;
    private static final int HANDLER_REMOVE_BACKGROUND = 7;
    private static final int HANDLER_REVERSE = 6;
    public static final String TAG = "BackcarEventHw";
    private View mBackgroundView;
    /* access modifiers changed from: private */
    public final EventService mContext;
    private CustomRadarView mCustomRadarViewFront;
    private CustomRadarView mCustomRadarViewRear;
    private CustomTrackView mCustomTrackView;
    /* access modifiers changed from: private */
    public float mDownX = 0.0f;
    /* access modifiers changed from: private */
    public float mDownY = 0.0f;
    private int[] mFrontRadarData = new int[4];
    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 0:
                    BackcarEventHw.this.refreshSpeedView();
                    return;
                case 1:
                    BackcarEventHw.this.refreshTempView();
                    return;
                case 2:
                    BackcarEventHw.this.refreshLightView();
                    return;
                case 3:
                    BackcarEventHw.this.refreshBeltView();
                    return;
                case 4:
                    BackcarEventHw.this.refreshHandBrakeView();
                    return;
                case 5:
                    removeMessages(5);
                    boolean booleanValue = ((Boolean) message.obj).booleanValue();
                    Log.d(BackcarEventHw.TAG, "HANDLER_ORIGINAL showOriginal = " + booleanValue);
                    if (booleanValue) {
                        BackcarEventHw.this.showOriginalView();
                    } else {
                        BackcarEventHw.this.hideOriginalView();
                    }
                    BackcarEventHw.this.hideBgView();
                    return;
                case 6:
                    removeMessages(6);
                    boolean booleanValue2 = ((Boolean) message.obj).booleanValue();
                    Log.d(BackcarEventHw.TAG, "HANDLER_REVERSE showReverse = " + booleanValue2);
                    if (booleanValue2) {
                        BackcarEventHw.this.showReverseView();
                    } else {
                        BackcarEventHw.this.hideReverseView();
                    }
                    BackcarEventHw.this.hideBgView();
                    return;
                default:
                    return;
            }
        }
    };
    private ImageView mImgBelt;
    private ImageView mImgDashboard;
    private ImageView mImgHandBrake;
    private ImageView mImgLight;
    private boolean mIsReverseTrackRight = true;
    private int mLastType = -1;
    private final Runnable mLongClickRunnable = new Runnable() {
        public void run() {
            if (BackcarEventHw.this.mContext != null) {
                BackcarEventHw.this.mContext.sendTouchPos((int) BackcarEventHw.this.mDownX, (int) BackcarEventHw.this.mDownY, true, true);
            }
            if (SysProviderOpt.getInstance(BackcarEventHw.this.mContext).getRecordBoolean(SysProviderOpt.KSW_SEND_TOUCH_DATA_CONTINUED, false) && BackcarEventHw.this.mType == 1) {
                BackcarEventHw.this.stopCamera();
            }
        }
    };
    private int mLongPressDetermine = 2000;
    private View mOriginalView;
    private WindowManager.LayoutParams mParams = new WindowManager.LayoutParams();
    private final SysProviderOpt mProvider;
    private int[] mRearRadarData = new int[4];
    private View mRestingScreenView;
    private int mReverseTrackData = 0;
    private View mReverseView;
    private boolean mShowBelt = false;
    private boolean mShowBg = false;
    private boolean mShowHandBrakeIcon = false;
    public boolean mShowOriginalView = false;
    private boolean mShowRestingScreen = false;
    public boolean mShowReverseView = false;
    private boolean mSmallLightOn = false;
    private int mSpeedData = 0;
    private int mSpeedUnit = 0;
    private String mTempData = "26℃";
    private int mTempUnit = 0;
    private SurfaceView mTextureOriginal;
    private SurfaceView mTextureReverse;
    private int mTouchRange = 50;
    private TextView mTvSpeed;
    private TextView mTvSpeedUnit;
    private TextView mTvTemperature;
    /* access modifiers changed from: private */
    public int mType = -1;
    private final HdmiUtil2 mUtil;
    private WindowManager mWindowManage;
    public boolean reverseWhenOriginal = false;

    public BackcarEventHw(Context context) {
        EventService eventService = (EventService) context;
        this.mContext = eventService;
        this.mProvider = SysProviderOpt.getInstance(eventService);
        this.mUtil = new HdmiUtil2(context);
        init();
    }

    private void init() {
        if (this.mWindowManage == null) {
            this.mWindowManage = (WindowManager) this.mContext.getSystemService("window");
        }
        this.mParams.type = 2010;
        this.mParams.format = 3;
        this.mParams.flags = 296;
        this.mParams.gravity = 8388659;
        this.mParams.x = 0;
        this.mParams.y = 0;
        this.mParams.width = this.mContext.getResources().getDisplayMetrics().widthPixels;
        this.mParams.height = this.mContext.getResources().getDisplayMetrics().heightPixels;
        intBgView();
        initRestingScreenView();
        initOriginalView();
        initReverseView();
    }

    private void intBgView() {
        this.mBackgroundView = View.inflate(this.mContext, R.layout.layout_hw_screen_resting, (ViewGroup) null);
    }

    private void initRestingScreenView() {
        View inflate = View.inflate(this.mContext, R.layout.layout_hw_screen_resting, (ViewGroup) null);
        this.mRestingScreenView = inflate;
        if (inflate != null) {
            inflate.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    BackcarEventHw.this.lambda$initRestingScreenView$0$BackcarEventHw(view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$initRestingScreenView$0$BackcarEventHw(View view) {
        stopCamera();
    }

    private void initOriginalView() {
        View inflate = View.inflate(this.mContext, R.layout.layout_hw_screen_original, (ViewGroup) null);
        this.mOriginalView = inflate;
        if (inflate != null) {
            SurfaceView surfaceView = (SurfaceView) inflate.findViewById(R.id.textureOriginal);
            this.mTextureOriginal = surfaceView;
            if (surfaceView != null) {
                surfaceView.setOnTouchListener(this);
            }
            this.mImgDashboard = (ImageView) this.mOriginalView.findViewById(R.id.imgDashboard);
            this.mTvSpeedUnit = (TextView) this.mOriginalView.findViewById(R.id.tvSpeedUnit);
            this.mTvSpeed = (TextView) this.mOriginalView.findViewById(R.id.tvSpeed);
            this.mTvTemperature = (TextView) this.mOriginalView.findViewById(R.id.tvTemperature);
            this.mImgLight = (ImageView) this.mOriginalView.findViewById(R.id.imgLight);
            this.mImgBelt = (ImageView) this.mOriginalView.findViewById(R.id.imgBelt);
            this.mImgHandBrake = (ImageView) this.mOriginalView.findViewById(R.id.imgHandBrake);
        }
    }

    private void initReverseView() {
        View inflate = View.inflate(this.mContext, R.layout.layout_hw_screen_reverse, (ViewGroup) null);
        this.mReverseView = inflate;
        if (inflate != null) {
            SurfaceView surfaceView = (SurfaceView) inflate.findViewById(R.id.textureReverse);
            this.mTextureReverse = surfaceView;
            if (surfaceView != null) {
                surfaceView.setOnTouchListener(this);
            }
            this.mCustomTrackView = (CustomTrackView) this.mReverseView.findViewById(R.id.customTrack);
            this.mCustomRadarViewFront = (CustomRadarView) this.mReverseView.findViewById(R.id.customRadarFront);
            this.mCustomRadarViewRear = (CustomRadarView) this.mReverseView.findViewById(R.id.customRadarRear);
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean recordBoolean = SysProviderOpt.getInstance(this.mContext).getRecordBoolean(SysProviderOpt.KSW_SEND_TOUCH_DATA_CONTINUED, false);
        int action = motionEvent.getAction();
        if (action == 0) {
            Log.i(TAG, "mCameraPipView ACTION_DOWN");
            this.mContext.mEventHandler.removeCallbacks(this.mLongClickRunnable);
            this.mDownX = motionEvent.getX();
            this.mDownY = motionEvent.getY();
            this.mContext.mEventHandler.postDelayed(this.mLongClickRunnable, (long) this.mLongPressDetermine);
            this.mContext.sendTouchPos((int) this.mDownX, (int) this.mDownY, true, false);
            if (!recordBoolean && this.mType == 1) {
                stopCamera();
            }
        } else if (action == 1) {
            Log.i(TAG, "mCameraPipView ACTION_UP");
            this.mContext.mEventHandler.removeCallbacks(this.mLongClickRunnable);
            this.mContext.sendTouchPos((int) motionEvent.getX(), (int) motionEvent.getY(), false, false);
        } else if (action == 2) {
            Log.i(TAG, "mCameraPipView ACTION_MOVE");
            if (Math.abs(this.mDownX - motionEvent.getX()) > ((float) this.mTouchRange) || Math.abs(this.mDownY - motionEvent.getY()) > ((float) this.mTouchRange)) {
                this.mContext.mEventHandler.removeCallbacks(this.mLongClickRunnable);
                this.mContext.sendTouchPos((int) motionEvent.getX(), (int) motionEvent.getY(), true, false);
            }
        }
        return false;
    }

    public void showBgView() {
        if (!this.mShowBg) {
            Log.d(TAG, "showBgView");
            this.mShowBg = true;
            this.mWindowManage.addView(this.mBackgroundView, this.mParams);
        }
    }

    public void hideBgView() {
        if (this.mShowBg) {
            Log.d(TAG, "hideBgView");
            this.mShowBg = false;
            this.mWindowManage.removeView(this.mBackgroundView);
        }
    }

    private void showRestingScreenView() {
        if (!this.mShowRestingScreen) {
            this.mWindowManage.addView(this.mRestingScreenView, this.mParams);
            this.mShowRestingScreen = true;
        }
    }

    private void hideRestingScreenView() {
        if (this.mShowRestingScreen) {
            this.mWindowManage.removeView(this.mRestingScreenView);
            this.mShowRestingScreen = false;
        }
    }

    /* access modifiers changed from: private */
    public void showOriginalView() {
        if (!this.mShowOriginalView) {
            refreshOriginalDashboard();
            this.mContext.sendKSW_0x00_0x68(5, 0);
            this.mWindowManage.addView(this.mOriginalView, this.mParams);
            this.mShowOriginalView = true;
        }
    }

    public void hideOriginalView() {
        Log.d(TAG, "hideOriginalView");
        if (this.mShowOriginalView) {
            this.mWindowManage.removeView(this.mOriginalView);
            this.mShowOriginalView = false;
        }
    }

    /* access modifiers changed from: private */
    public void showReverseView() {
        Log.d(TAG, "showReverseView");
        if (!this.mShowReverseView) {
            this.mWindowManage.addView(this.mReverseView, this.mParams);
            this.mShowReverseView = true;
        }
        setTrackData(this.mIsReverseTrackRight, this.mReverseTrackData);
        setFrontRadarData(this.mFrontRadarData);
        setRearRadarData(this.mRearRadarData);
    }

    public void hideReverseView() {
        if (this.mShowReverseView) {
            this.mWindowManage.removeView(this.mReverseView);
            this.mShowReverseView = false;
        }
    }

    public void startCamera(int i, int i2) {
        Log.d(TAG, "startCamera type = " + i + ", mType = " + this.mType);
        int i3 = this.mLastType;
        if (i != i3) {
            this.mType = i;
            if (i3 > -1) {
                stopCamera();
            }
            int i4 = this.mType;
            if (i4 == 0) {
                showRestingScreenView();
            } else if (i4 == 1) {
                Message message = new Message();
                message.what = 5;
                message.obj = true;
                this.mHandler.sendMessage(message);
                Log.d(TAG, "startBackCar mTextureOriginal = " + this.mTextureOriginal);
                this.mUtil.openCamera(this.mTextureOriginal, 0);
            } else if (i4 == 2) {
                Message message2 = new Message();
                message2.what = 6;
                message2.obj = true;
                this.mHandler.sendMessage(message2);
                int recordInteger = SysProviderOpt.getInstance(this.mContext).getRecordInteger(SysProviderOpt.KESAIWEI_SYS_CAMERA_SELECTION, 0);
                int recordInteger2 = SysProviderOpt.getInstance(this.mContext).getRecordInteger(SysProviderOpt.KSW_AHD_CAMERA_TYPE, 1);
                if (recordInteger != 1) {
                    switch (recordInteger2) {
                        case 0:
                            Log.d(TAG, "signalType = " + i2);
                            break;
                        case 1:
                            i2 = 1;
                            break;
                        case 2:
                            i2 = 2;
                            break;
                        case 3:
                            i2 = 3;
                            break;
                        case 4:
                            i2 = 4;
                            break;
                        case 5:
                            i2 = 5;
                            break;
                        case 6:
                            i2 = 6;
                            break;
                        case 7:
                            i2 = 7;
                            break;
                    }
                } else {
                    i2 = 0;
                }
                this.mUtil.openCamera(this.mTextureReverse, i2);
            }
            this.mLastType = this.mType;
        }
    }

    public void stopCamera() {
        Log.d(TAG, "stopCamera mLastType = " + this.mLastType);
        int i = this.mLastType;
        if (i == 0) {
            hideRestingScreenView();
        } else if (i == 1) {
            this.mUtil.closeCamera();
            Message message = new Message();
            message.what = 5;
            message.obj = false;
            this.mHandler.sendMessage(message);
            if (this.mType != 2) {
                this.mContext.b_Original_View = false;
                this.mHandler.sendEmptyMessage(7);
            }
        } else if (i == 2) {
            this.mUtil.closeCamera();
            Message message2 = new Message();
            message2.what = 6;
            message2.obj = false;
            if (this.reverseWhenOriginal) {
                showBgView();
            }
            this.mHandler.sendMessage(message2);
        }
        if (this.mType == 2 && this.mLastType == 1) {
            this.reverseWhenOriginal = true;
        } else {
            this.reverseWhenOriginal = false;
        }
        this.mLastType = -1;
    }

    public void refreshOriginalDashboard() {
        Drawable drawable;
        int recordInteger = this.mProvider.getRecordInteger(SysProviderOpt.KSW_DISTACNE_UNIT, 0);
        if (this.mSpeedUnit != recordInteger) {
            this.mSpeedUnit = recordInteger;
            ImageView imageView = this.mImgDashboard;
            if (imageView != null) {
                if (recordInteger == 0) {
                    drawable = this.mContext.getDrawable(R.drawable.mipi_original_youdi_260);
                } else {
                    drawable = this.mContext.getDrawable(R.drawable.mipi_original_youdi_160);
                }
                imageView.setBackground(drawable);
            }
            setSpeedData(this.mSpeedData);
        }
    }

    public void setSpeedData(int i) {
        this.mSpeedData = i;
        this.mHandler.removeMessages(0);
        this.mHandler.sendEmptyMessage(0);
    }

    /* access modifiers changed from: private */
    public void refreshSpeedView() {
        TextView textView = this.mTvSpeed;
        if (textView != null) {
            if (this.mSpeedUnit == 1) {
                textView.setText(BuildConfig.FLAVOR + ((int) (((double) this.mSpeedData) * 0.62d)));
            } else {
                textView.setText(BuildConfig.FLAVOR + this.mSpeedData);
            }
        }
        TextView textView2 = this.mTvSpeedUnit;
        if (textView2 != null) {
            textView2.setText(this.mSpeedUnit == 0 ? "km/h" : "mph");
        }
    }

    public void setTempData(String str) {
        this.mTempData = str;
        this.mHandler.removeMessages(1);
        this.mHandler.sendEmptyMessage(1);
    }

    /* access modifiers changed from: private */
    public void refreshTempView() {
        TextView textView = this.mTvTemperature;
        if (textView != null) {
            textView.setText(this.mTempData);
        }
    }

    public void setLightData(boolean z) {
        if (this.mSmallLightOn != z) {
            this.mSmallLightOn = z;
            this.mHandler.removeMessages(2);
            this.mHandler.sendEmptyMessage(2);
        }
    }

    /* access modifiers changed from: private */
    public void refreshLightView() {
        ImageView imageView = this.mImgLight;
        if (imageView != null) {
            imageView.setVisibility(this.mSmallLightOn ? 0 : 8);
        }
    }

    public void setBeltData(boolean z) {
        if (this.mShowBelt != z) {
            this.mShowBelt = z;
            this.mHandler.removeMessages(3);
            this.mHandler.sendEmptyMessage(3);
        }
    }

    /* access modifiers changed from: private */
    public void refreshBeltView() {
        ImageView imageView = this.mImgBelt;
        if (imageView != null) {
            imageView.setVisibility(this.mShowBelt ? 0 : 8);
        }
    }

    public void setHandBrakeData(boolean z) {
        if (this.mShowHandBrakeIcon != z) {
            this.mShowHandBrakeIcon = z;
            this.mHandler.removeMessages(4);
            this.mHandler.sendEmptyMessage(4);
        }
    }

    /* access modifiers changed from: private */
    public void refreshHandBrakeView() {
        ImageView imageView = this.mImgHandBrake;
        if (imageView != null) {
            imageView.setVisibility(this.mShowHandBrakeIcon ? 0 : 8);
        }
    }

    public void setTrackData(boolean z, int i) {
        this.mIsReverseTrackRight = z;
        this.mReverseTrackData = i;
        CustomTrackView customTrackView = this.mCustomTrackView;
        if (customTrackView != null) {
            customTrackView.setTrackData(z, i);
        }
    }

    public void setFrontRadarData(int[] iArr) {
        if (!Arrays.equals(this.mFrontRadarData, iArr)) {
            int[] iArr2 = this.mFrontRadarData;
            System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
            CustomRadarView customRadarView = this.mCustomRadarViewFront;
            if (customRadarView != null) {
                customRadarView.setFrontRadarData(iArr);
            }
        }
    }

    public void setRearRadarData(int[] iArr) {
        if (!Arrays.equals(this.mRearRadarData, iArr)) {
            int[] iArr2 = this.mRearRadarData;
            System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
            CustomRadarView customRadarView = this.mCustomRadarViewRear;
            if (customRadarView != null) {
                customRadarView.setRearRadarData(iArr);
            }
        }
    }
}
