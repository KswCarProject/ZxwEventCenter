package com.szchoiceway.eventcenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.szchoiceway.eventcenter.View.MyClockView;

public class PowerOffEvt {
    private static final int HANDLER_POWER_VIEW_HIDE = 0;
    private static final int HANDLER_POWER_VIEW_SHOW = 1;
    private static final String TAG = "PowerOffEvt";
    private MyClockView mClockView;
    /* access modifiers changed from: private */
    public Context mContext = null;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 0) {
                PowerOffEvt.this.removePwrOffView();
            } else if (i == 1) {
                PowerOffEvt.this.addPwrOffView();
            }
        }
    };
    private boolean mInPwrOffMode = false;
    public boolean mIsAddPwrOffView = false;
    private final Object mObject = new Object();
    public View mPwrOffView = null;
    private View mRootView = null;
    private TextView twCurDataTimeYMD;
    private WindowManager wm = null;
    private WindowManager.LayoutParams wmPwrOffParams = new WindowManager.LayoutParams();

    public PowerOffEvt(Context context) {
        this.mContext = context;
        initPoweroffWnd();
        Log.i(TAG, "***PowerOffEvt***");
    }

    private void initPoweroffWnd() {
        createCameraPipView(0);
    }

    public void showPoweroffWnd() {
        Log.d(TAG, "showPoweroffWnd");
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.sendEmptyMessageDelayed(1, 10);
        }
    }

    public void hidePoweroffWnd() {
        Log.d(TAG, "hidePoweroffWnd");
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.sendEmptyMessageDelayed(0, 10);
        }
    }

    public void PowerOff(boolean z) {
        boolean z2 = this.mInPwrOffMode;
        if (z2 == z) {
            return;
        }
        if (!z2) {
            showPoweroffWnd();
            this.mInPwrOffMode = z;
            Log.i(TAG, "***start power off***" + this.mInPwrOffMode);
            return;
        }
        hidePoweroffWnd();
        this.mInPwrOffMode = z;
        Log.i(TAG, "***end power off***" + this.mInPwrOffMode);
    }

    public void createCameraPipView(int i) {
        if (this.mPwrOffView == null) {
            this.mPwrOffView = View.inflate(this.mContext, R.layout.layout_poweroff, (ViewGroup) null);
        }
        this.mPwrOffView.setOnTouchListener(new View.OnTouchListener() {
            int startX = 0;
            int startY = 0;

            public boolean onTouch(View view, MotionEvent motionEvent) {
                motionEvent.getAction();
                Log.i(PowerOffEvt.TAG, "onTouch: ");
                ((EventService) PowerOffEvt.this.mContext).virtualCenterClick();
                return true;
            }
        });
        if (this.mPwrOffView != null) {
            if (this.wm == null) {
                this.wm = (WindowManager) this.mContext.getSystemService("window");
            }
            this.wmPwrOffParams.type = 2010;
            this.wmPwrOffParams.flags = 296;
            this.wmPwrOffParams.gravity = 51;
            this.wmPwrOffParams.x = 0;
            this.wmPwrOffParams.y = 0;
            this.wmPwrOffParams.width = (int) this.mContext.getResources().getDimension(R.dimen.backcar_width);
            this.wmPwrOffParams.height = (int) this.mContext.getResources().getDimension(R.dimen.backcar_height);
            this.twCurDataTimeYMD = (TextView) this.mPwrOffView.findViewById(R.id.TwCurDataTimeYMD);
            MyClockView myClockView = (MyClockView) this.mPwrOffView.findViewById(R.id.mClockView);
            this.mClockView = myClockView;
            if (myClockView != null) {
                myClockView.setClockXY((int) this.mContext.getResources().getDimension(R.dimen.clock_x), (int) this.mContext.getResources().getDimension(R.dimen.clock_y));
            }
        }
    }

    public void addPwrOffView() {
        View view;
        if (!this.mIsAddPwrOffView) {
            synchronized (this.mObject) {
                WindowManager windowManager = this.wm;
                if (!(windowManager == null || (view = this.mPwrOffView) == null)) {
                    windowManager.addView(view, this.wmPwrOffParams);
                    Log.i(TAG, "mPwrOffView-addPwrOffView: ");
                }
            }
            this.mIsAddPwrOffView = true;
        }
    }

    public void updateViewLayout() {
        synchronized (this.mObject) {
            if (!(this.wm == null || this.mPwrOffView == null)) {
                this.wmPwrOffParams.x = 0;
                this.wmPwrOffParams.y = 0;
                this.wm.updateViewLayout(this.mPwrOffView, this.wmPwrOffParams);
            }
        }
    }

    public void removePwrOffView() {
        if (this.mIsAddPwrOffView) {
            synchronized (this.mObject) {
                if (this.mPwrOffView != null) {
                    if (this.wm == null) {
                        this.wm = (WindowManager) this.mContext.getSystemService("window");
                    }
                    if (this.wm != null) {
                        Log.i(TAG, "mPwrOffView-removePwrOffView: ");
                    }
                    this.wm.removeView(this.mPwrOffView);
                }
            }
            this.mIsAddPwrOffView = false;
        }
    }

    public void updateTimerInfor(int i, int i2, int i3) {
        String format = String.format("%d-%02d-%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
        TextView textView = this.twCurDataTimeYMD;
        if (textView != null) {
            textView.setText(format);
        }
    }
}
