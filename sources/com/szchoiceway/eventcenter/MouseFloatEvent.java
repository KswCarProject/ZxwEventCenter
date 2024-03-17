package com.szchoiceway.eventcenter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class MouseFloatEvent {
    private static final String TAG = "MouseFloatEvent";
    protected boolean bShow = false;
    protected EventApp mApp = null;
    private Context mContext = null;
    private View mMousePipView = null;
    private final Object mObject = new Object();
    private WindowManager wm = null;
    /* access modifiers changed from: private */
    public WindowManager.LayoutParams wmMousePipParams = new WindowManager.LayoutParams();

    public MouseFloatEvent(Context context) {
        this.mContext = context;
        this.mApp = (EventApp) context.getApplicationContext();
        Log.i(TAG, "mContext " + this.mContext);
        Log.i(TAG, "mApp " + this.mApp);
        initMousePipWnd();
    }

    private void initMousePipWnd() {
        createMousePipView(0);
    }

    public void startMouseMini() {
        Log.i(TAG, "startMousemini");
        showMouseMiniWnd();
    }

    public void exitMouseMini() {
        hideMouseMiniWnd();
        Log.i(TAG, "exitMousemini 0");
    }

    private void showMouseMiniWnd() {
        addMousePipView();
    }

    private void hideMouseMiniWnd() {
        removeMousePipView();
    }

    public void createMousePipView(int i) {
        if (this.mMousePipView == null) {
            this.mMousePipView = View.inflate(this.mContext, R.layout.maisiluo_1280x480_mouse, (ViewGroup) null);
        }
        this.mMousePipView.setOnTouchListener(new View.OnTouchListener() {
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
                    MouseFloatEvent.this.wmMousePipParams.x += i;
                    MouseFloatEvent.this.wmMousePipParams.y += i2;
                    return true;
                }
            }
        });
        if (this.mMousePipView != null) {
            if (this.wm == null) {
                this.wm = (WindowManager) this.mContext.getSystemService("window");
            }
            this.wmMousePipParams.type = 2003;
            this.wmMousePipParams.type = 2010;
            this.wmMousePipParams.flags = 296;
            this.wmMousePipParams.gravity = 51;
            this.wmMousePipParams.format = 1;
            this.wm.getDefaultDisplay().getMetrics(new DisplayMetrics());
            this.wmMousePipParams.x = 0;
            this.wmMousePipParams.y = EventUtils.WHAT_AUTO_RUNNING_USB_DVR_CRASH_SERVICE;
            this.wmMousePipParams.width = 21;
            this.wmMousePipParams.height = 29;
        }
    }

    public void updateViewLayout() {
        synchronized (this.mObject) {
            if (!(this.wm == null || this.mMousePipView == null)) {
                this.wm.getDefaultDisplay().getMetrics(new DisplayMetrics());
                this.wmMousePipParams.x = 0;
                this.wmMousePipParams.y = 404;
                this.wmMousePipParams.width = 21;
                this.wmMousePipParams.height = 29;
                this.wm.updateViewLayout(this.mMousePipView, this.wmMousePipParams);
            }
        }
    }

    public void updateMouseParam(int i, int i2) {
        synchronized (this.mObject) {
            if (!(this.wm == null || this.mMousePipView == null)) {
                this.wm.getDefaultDisplay().getMetrics(new DisplayMetrics());
                this.wmMousePipParams.x += i;
                if (this.wmMousePipParams.x < 0) {
                    this.wmMousePipParams.x = 0;
                } else if (this.wmMousePipParams.x > 1280) {
                    this.wmMousePipParams.x = 1280;
                }
                this.wmMousePipParams.y += i2;
                if (this.wmMousePipParams.y < 0) {
                    this.wmMousePipParams.y = 0;
                } else if (this.wmMousePipParams.y > 480) {
                    this.wmMousePipParams.y = 480;
                }
                this.wmMousePipParams.width = 21;
                this.wmMousePipParams.height = 29;
                this.wm.updateViewLayout(this.mMousePipView, this.wmMousePipParams);
            }
        }
    }

    public void addMousePipView() {
        View view;
        synchronized (this.mObject) {
            if (!this.bShow) {
                this.bShow = true;
                WindowManager windowManager = this.wm;
                if (!(windowManager == null || (view = this.mMousePipView) == null)) {
                    windowManager.addView(view, this.wmMousePipParams);
                }
            }
        }
    }

    public void removeMousePipView() {
        synchronized (this.mObject) {
            if (this.bShow) {
                this.bShow = false;
                if (this.mMousePipView != null) {
                    if (this.wm == null) {
                        this.wm = (WindowManager) this.mContext.getSystemService("window");
                    }
                    WindowManager windowManager = this.wm;
                    if (windowManager != null) {
                        windowManager.removeView(this.mMousePipView);
                    }
                }
            }
        }
    }

    public int getParamX() {
        return this.wmMousePipParams.x;
    }

    public int getParamY() {
        return this.wmMousePipParams.y;
    }

    public boolean isShow() {
        return this.bShow;
    }
}
