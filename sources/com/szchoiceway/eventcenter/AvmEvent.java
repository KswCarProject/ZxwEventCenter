package com.szchoiceway.eventcenter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import java.util.Timer;
import java.util.TimerTask;

public class AvmEvent {
    private static final String TAG = "AvmEvent";
    private static final int WM_OPEN_CAMARA_EVT = 3;
    private static final int WM_SIGNAL_EVT = 2;
    /* access modifiers changed from: private */
    public static int iCamaraSignalValidCnt;
    /* access modifiers changed from: private */
    public boolean bCamaraOpen = false;
    /* access modifiers changed from: private */
    public NativeCamera camera = new NativeCamera();
    Handler handleProgress = new Handler() {
        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 2) {
                if (i == 3 && AvmEvent.this.mCameraPipView != null) {
                    AvmEvent.this.mCameraPipView.setBackgroundColor(Color.parseColor("#ff060606"));
                }
            } else if (AvmEvent.iCamaraSignalValidCnt > 20) {
                AvmEvent avmEvent = AvmEvent.this;
                avmEvent.ShowWgt(R.id.NoSignalTip, avmEvent.signalState <= 0);
            }
        }
    };
    private boolean isEnable = false;
    /* access modifiers changed from: private */
    public View mCameraPipView = null;
    private Context mContext = null;
    private boolean mInAvmMode = false;
    private final Object mObject = new Object();
    private View mRootView = null;
    private ImageView mvwBackTrack = null;
    /* access modifiers changed from: private */
    public int signalState = 0;
    private Timer timer = null;
    private WindowManager wm = null;
    /* access modifiers changed from: private */
    public WindowManager.LayoutParams wmCameraPipParams = new WindowManager.LayoutParams();

    static /* synthetic */ int access$408() {
        int i = iCamaraSignalValidCnt;
        iCamaraSignalValidCnt = i + 1;
        return i;
    }

    public AvmEvent(Context context) {
        this.mContext = context;
        initBackcarWnd();
    }

    public void startAvmMode() {
        if (!this.mInAvmMode) {
            this.mInAvmMode = true;
            showBackcarWnd();
            Log.i(TAG, "startAvmMode");
        }
    }

    public void endAvmMode() {
        if (this.mInAvmMode) {
            this.mInAvmMode = false;
            hideBackcarWnd();
            Log.i(TAG, "endAvmMode 0");
            iCamaraSignalValidCnt = 0;
            ShowWgt(R.id.NoSignalTip, false);
        }
    }

    private void initBackcarWnd() {
        createCameraPipView(0);
    }

    private void showBackcarWnd() {
        View view = this.mCameraPipView;
        if (view != null) {
            view.setBackgroundColor(Color.parseColor("#ff000000"));
        }
        setSignalChannal(9);
        addCameraPipView();
        setVehicle(true);
        Handler handler = this.handleProgress;
        if (handler != null) {
            handler.removeMessages(3);
            this.handleProgress.sendEmptyMessageDelayed(3, 1000);
        }
    }

    private void hideBackcarWnd() {
        View view = this.mCameraPipView;
        if (view != null) {
            view.setBackgroundColor(Color.parseColor("#ff000000"));
        }
        try {
            Thread.sleep(10);
        } catch (Exception e) {
            Log.e(TAG, "DetectSignThread error " + e.toString());
        }
        Handler handler = this.handleProgress;
        if (handler != null) {
            handler.removeMessages(3);
        }
        setVehicle(false);
        iCamaraSignalValidCnt = 0;
        removeCameraPipView();
    }

    public void openSignalTimer() {
        Timer timer2 = new Timer();
        this.timer = timer2;
        timer2.schedule(new TimerTask() {
            public void run() {
                AvmEvent avmEvent = AvmEvent.this;
                NativeCamera unused = avmEvent.camera;
                int unused2 = avmEvent.signalState = NativeCamera.getSignalState();
                AvmEvent.this.handleProgress.removeMessages(2);
                AvmEvent.this.handleProgress.sendEmptyMessageDelayed(2, 300);
            }
        }, 200, 200);
    }

    public void createCameraPipView(int i) {
        if (this.mCameraPipView == null) {
            View inflate = View.inflate(this.mContext, R.layout.layout_backcar, (ViewGroup) null);
            this.mCameraPipView = inflate;
            this.mvwBackTrack = (ImageView) inflate.findViewById(R.id.vwBackTrack);
        }
        this.mCameraPipView.setOnTouchListener(new View.OnTouchListener() {
            int startX = 0;
            int startY = 0;

            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    this.startX = (int) motionEvent.getRawX();
                    this.startY = (int) motionEvent.getRawY();
                    return true;
                } else if (action != 2) {
                    return true;
                } else {
                    int rawX = (int) motionEvent.getRawX();
                    int rawY = (int) motionEvent.getRawY();
                    int i = rawX - this.startX;
                    int i2 = rawY - this.startY;
                    this.startX = rawX;
                    this.startY = rawY;
                    AvmEvent.this.wmCameraPipParams.x += i;
                    AvmEvent.this.wmCameraPipParams.y += i2;
                    AvmEvent.this.updateViewLayout();
                    return true;
                }
            }
        });
        if (this.mCameraPipView != null) {
            if (this.wm == null) {
                this.wm = (WindowManager) this.mContext.getSystemService("window");
            }
            this.wmCameraPipParams.type = 2010;
            this.wmCameraPipParams.flags = 296;
            this.wmCameraPipParams.gravity = 51;
            this.wmCameraPipParams.x = 0;
            this.wmCameraPipParams.y = 0;
            if (EventService.mScreen800x480) {
                this.wmCameraPipParams.width = 800;
                this.wmCameraPipParams.height = 480;
            } else if (EventService.mScreen1280x480) {
                this.wmCameraPipParams.width = 1280;
                this.wmCameraPipParams.height = 480;
            } else {
                this.wmCameraPipParams.width = 1024;
                this.wmCameraPipParams.height = 600;
            }
        }
    }

    public void addCameraPipView() {
        View view;
        synchronized (this.mObject) {
            WindowManager windowManager = this.wm;
            if (!(windowManager == null || (view = this.mCameraPipView) == null)) {
                windowManager.addView(view, this.wmCameraPipParams);
                setVehicleSize(this.wmCameraPipParams.x, this.wmCameraPipParams.y, this.wmCameraPipParams.width, this.wmCameraPipParams.height);
            }
        }
    }

    public void updateViewLayout() {
        synchronized (this.mObject) {
            if (!(this.wm == null || this.mCameraPipView == null)) {
                if (EventService.mScreen800x480) {
                    if (this.wmCameraPipParams.x < 0) {
                        this.wmCameraPipParams.x = 0;
                    }
                    if (this.wmCameraPipParams.x > 800 - this.wmCameraPipParams.width) {
                        WindowManager.LayoutParams layoutParams = this.wmCameraPipParams;
                        layoutParams.x = 800 - layoutParams.width;
                    }
                    if (this.wmCameraPipParams.y < 0) {
                        this.wmCameraPipParams.y = 0;
                    }
                    if (this.wmCameraPipParams.y > 480 - this.wmCameraPipParams.height) {
                        WindowManager.LayoutParams layoutParams2 = this.wmCameraPipParams;
                        layoutParams2.y = 480 - layoutParams2.height;
                    }
                } else if (EventService.mScreen1280x480) {
                    if (this.wmCameraPipParams.x < 0) {
                        this.wmCameraPipParams.x = 0;
                    }
                    if (this.wmCameraPipParams.x > 1280 - this.wmCameraPipParams.width) {
                        WindowManager.LayoutParams layoutParams3 = this.wmCameraPipParams;
                        layoutParams3.x = 1280 - layoutParams3.width;
                    }
                    if (this.wmCameraPipParams.y < 0) {
                        this.wmCameraPipParams.y = 0;
                    }
                    if (this.wmCameraPipParams.y > 480 - this.wmCameraPipParams.height) {
                        WindowManager.LayoutParams layoutParams4 = this.wmCameraPipParams;
                        layoutParams4.y = 480 - layoutParams4.height;
                    }
                } else {
                    if (this.wmCameraPipParams.x < 0) {
                        this.wmCameraPipParams.x = 0;
                    }
                    if (this.wmCameraPipParams.x > 1024 - this.wmCameraPipParams.width) {
                        WindowManager.LayoutParams layoutParams5 = this.wmCameraPipParams;
                        layoutParams5.x = 1024 - layoutParams5.width;
                    }
                    if (this.wmCameraPipParams.y < 0) {
                        this.wmCameraPipParams.y = 0;
                    }
                    if (this.wmCameraPipParams.y > 600 - this.wmCameraPipParams.height) {
                        WindowManager.LayoutParams layoutParams6 = this.wmCameraPipParams;
                        layoutParams6.y = 600 - layoutParams6.height;
                    }
                }
                this.wm.updateViewLayout(this.mCameraPipView, this.wmCameraPipParams);
                setVehicleSize(this.wmCameraPipParams.x, this.wmCameraPipParams.y, this.wmCameraPipParams.width, this.wmCameraPipParams.height);
            }
        }
    }

    public void removeCameraPipView() {
        synchronized (this.mObject) {
            if (this.mCameraPipView != null) {
                if (this.wm == null) {
                    this.wm = (WindowManager) this.mContext.getSystemService("window");
                }
                WindowManager windowManager = this.wm;
                if (windowManager != null) {
                    windowManager.removeView(this.mCameraPipView);
                }
            }
        }
    }

    public void setVehicle(boolean z) {
        if (z) {
            NativeCamera.openCamera();
            Log.i(TAG, "openCamera");
            this.bCamaraOpen = true;
            DetectSignThread();
            return;
        }
        NativeCamera.closeCamera();
        Log.i(TAG, "closeCamera");
        this.bCamaraOpen = false;
    }

    /* access modifiers changed from: protected */
    public void DetectSignThread() {
        new Thread(new Runnable() {
            public void run() {
                while (AvmEvent.this.bCamaraOpen) {
                    AvmEvent avmEvent = AvmEvent.this;
                    NativeCamera unused = avmEvent.camera;
                    int unused2 = avmEvent.signalState = NativeCamera.getSignalState();
                    AvmEvent.access$408();
                    try {
                        Thread.sleep(200);
                    } catch (Exception e) {
                        Log.e(AvmEvent.TAG, "DetectSignThread error " + e.toString());
                    }
                    AvmEvent.this.handleProgress.removeMessages(2);
                    AvmEvent.this.handleProgress.sendEmptyMessageDelayed(2, 10);
                }
            }
        }).start();
    }

    public void setVehicleSize(int i, int i2, int i3, int i4) {
        Log.i(TAG, "x = " + i);
        Log.i(TAG, "y = " + i2);
        Log.i(TAG, "width = " + i3);
        Log.i(TAG, "height = " + i4);
        NativeCamera.setCameraRect(i, i2, i3, i4);
    }

    public void setSignalChannal(int i) {
        Log.i(TAG, "Video ch ==" + i);
        NativeCamera.setSignalChannal(i);
    }

    public int GetSignalStatus() {
        return this.signalState;
    }

    /* access modifiers changed from: package-private */
    public void showSystermTitleBar(boolean z) {
        if (!z) {
            WindowManager.LayoutParams attributes = ((Activity) this.mContext).getWindow().getAttributes();
            attributes.flags |= 1024;
            ((Activity) this.mContext).getWindow().setAttributes(attributes);
            ((Activity) this.mContext).getWindow().addFlags(512);
            return;
        }
        WindowManager.LayoutParams attributes2 = ((Activity) this.mContext).getWindow().getAttributes();
        attributes2.flags &= -1025;
        ((Activity) this.mContext).getWindow().setAttributes(attributes2);
        ((Activity) this.mContext).getWindow().clearFlags(512);
    }

    /* access modifiers changed from: package-private */
    public void ShowWgt(int i, boolean z) {
        View findViewById;
        View view = this.mCameraPipView;
        if (view != null && (findViewById = view.findViewById(i)) != null) {
            if (z) {
                findViewById.setVisibility(0);
            } else {
                findViewById.setVisibility(8);
            }
        }
    }

    public void setTrackData(byte b) {
        int i;
        ImageView imageView;
        int[] iArr = {R.drawable.gj01, R.drawable.gj02, R.drawable.gj03, R.drawable.gj04, R.drawable.gj05, R.drawable.gj06, R.drawable.gj07, R.drawable.gj08, R.drawable.gj09, R.drawable.gj10, R.drawable.gj11, R.drawable.gj12, R.drawable.gj13, R.drawable.gj14, R.drawable.gj15, R.drawable.gj16, R.drawable.gj17, R.drawable.gj18, R.drawable.gj19, R.drawable.gj20, R.drawable.gj21, R.drawable.gj22, R.drawable.gj23, R.drawable.gj24, R.drawable.gj25, R.drawable.gj26, R.drawable.gj27, R.drawable.gj28, R.drawable.gj29, R.drawable.gj30, R.drawable.gj31, R.drawable.gj32, R.drawable.gj33, R.drawable.gj34, R.drawable.gj35, R.drawable.gj36, R.drawable.gj37, R.drawable.gj38, R.drawable.gj39};
        boolean z = (b & EventUtils.CMD_FREQ_SEL) > 0;
        byte b2 = b & EventUtils.CMD_UPGRADE_ACK;
        if (b2 < 0) {
            b2 = 0;
        } else if (b2 > 38) {
            b2 = 38;
        }
        if (z) {
            i = 19 - (b2 / 2);
        } else {
            i = (b2 / 2) + 19;
        }
        if (i >= 0 && i < 39 && (imageView = this.mvwBackTrack) != null) {
            imageView.setBackgroundResource(iArr[i]);
            this.mvwBackTrack.setVisibility(0);
        }
    }
}
