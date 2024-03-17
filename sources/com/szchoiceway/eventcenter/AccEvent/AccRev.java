package com.szchoiceway.eventcenter.AccEvent;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

public class AccRev {
    public static final int IDLE = 0;
    private static final int MSG_DO_ACC = 1;
    public static final int ONING = 2;
    public static final int ON_START = 1;
    public static final int OVER_START = 3;
    private static final String TAG = "AccRev";
    long accOnOffStartCount = 0;
    int accState = 1;
    /* access modifiers changed from: private */
    public Object mAccLock = new Object();
    /* access modifiers changed from: private */
    public int mAccState;
    /* access modifiers changed from: private */
    public Context mContext;
    private Handler mHandler;
    private HandlerThread mHandlerThread;

    public AccRev(Context context) {
        this.mContext = context;
        if (this.mHandlerThread == null) {
            HandlerThread handlerThread = new HandlerThread("AccRec");
            this.mHandlerThread = handlerThread;
            handlerThread.start();
        }
        this.mHandler = new Handler(this.mHandlerThread.getLooper()) {
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    AccRev.this.doAcc(message.arg1);
                }
            }
        };
    }

    public void doAcc(int i) {
        Log.d(TAG, "AccRev::doAcc. state:" + i + "; mAccState:" + this.mAccState);
        if (i == 1) {
            int i2 = this.mAccState;
            if (i2 == 0) {
                doAccOn();
                this.accState = 1;
                this.accOnOffStartCount = SystemClock.elapsedRealtime();
            } else if (i2 == 3) {
                Message message = new Message();
                message.what = 1;
                message.arg1 = i;
                this.mHandler.sendMessageDelayed(message, 500);
            }
        } else if (i == 0) {
            int i3 = this.mAccState;
            if (i3 == 0) {
                doAccOver();
                this.accState = 0;
                this.accOnOffStartCount = SystemClock.elapsedRealtime();
            } else if (i3 == 1 || i3 == 2) {
                Message message2 = new Message();
                message2.what = 1;
                message2.arg1 = i;
                this.mHandler.sendMessageDelayed(message2, 500);
            }
        }
    }

    private void doAccOn() {
        new Thread(new Runnable() {
            public void run() {
                synchronized (AccRev.this.mAccLock) {
                    int unused = AccRev.this.mAccState = 1;
                    Utils.accOn(AccRev.this.mContext);
                    int unused2 = AccRev.this.mAccState = 0;
                }
            }
        }).start();
    }

    private void doAccOver() {
        Log.d(TAG, "AccRev::doAccOver. ");
        new Thread(new Runnable() {
            public void run() {
                synchronized (AccRev.this.mAccLock) {
                    int unused = AccRev.this.mAccState = 3;
                    Utils.standby(AccRev.this.mContext);
                    int unused2 = AccRev.this.mAccState = 0;
                }
            }
        }).start();
    }
}
