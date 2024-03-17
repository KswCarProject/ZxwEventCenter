package com.szchoiceway.eventcenter;

import android.content.Context;
import android.content.Intent;
import android.net.LocalServerSocket;
import android.net.LocalSocket;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class CarPlaySocketServer {
    private static final String PATH_TOUCH = "com.szchoiceway.zxwauto.touch";
    private static final String TAG = "CarPlaySocketServer";
    private static WindowManager.LayoutParams mLayoutParams = new WindowManager.LayoutParams();
    private static WindowManager mWindowManager = null;
    /* access modifiers changed from: private */
    public boolean exit = false;
    /* access modifiers changed from: private */
    public int mCapture;
    /* access modifiers changed from: private */
    public SocketServerThread.ConnectThread mConnectThread;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public int mEndX = 100;
    /* access modifiers changed from: private */
    public int mEndY = 100;
    /* access modifiers changed from: private */
    public EventService mEventService;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 0) {
                CarPlaySocketServer.this.removeView();
            } else if (i == 1) {
                boolean unused = CarPlaySocketServer.this.mIsStartTouch = true;
            }
        }
    };
    private int mHeight;
    /* access modifiers changed from: private */
    public boolean mInLauncher = false;
    /* access modifiers changed from: private */
    public boolean mInThird = false;
    private int mInt = 22;
    private boolean mIsShow = false;
    /* access modifiers changed from: private */
    public boolean mIsStartTouch = true;
    private ImageView mIvTouchView;
    private boolean mLastInThird = true;
    /* access modifiers changed from: private */
    public boolean mResponse = false;
    /* access modifiers changed from: private */
    public int mStartX = 100;
    /* access modifiers changed from: private */
    public int mStartY = 100;
    private int mState;
    /* access modifiers changed from: private */
    public int mTHeight;
    /* access modifiers changed from: private */
    public int mTState;
    /* access modifiers changed from: private */
    public int mTWidth;
    private SocketServerThread mThread;
    private View mTouchLayout;
    private int mTouchViewHeight;
    private int mTouchViewWidth;
    private int mWidth;
    /* access modifiers changed from: private */
    public int mX = 100;
    /* access modifiers changed from: private */
    public float mXRange = 6.5f;
    /* access modifiers changed from: private */
    public int mY = 100;
    /* access modifiers changed from: private */
    public float mYRange = 6.5f;

    public void startServer(Context context) {
        this.mContext = context;
        this.mEventService = (EventService) context;
        createView(context);
        SocketServerThread socketServerThread = new SocketServerThread();
        this.mThread = socketServerThread;
        socketServerThread.setPriority(10);
        this.mThread.start();
    }

    private void createView(Context context) {
        this.mTouchLayout = View.inflate(context, R.layout.imitate_layout_touch, (ViewGroup) null);
        mWindowManager = (WindowManager) context.getSystemService("window");
        mLayoutParams.type = 2010;
        mLayoutParams.format = 1;
        mLayoutParams.flags = 1336;
        mLayoutParams.gravity = 51;
        mLayoutParams.width = -2;
        mLayoutParams.height = -2;
        initView();
    }

    private void initView() {
        View view = this.mTouchLayout;
        if (view != null) {
            this.mIvTouchView = (ImageView) view.findViewById(R.id.IvTouchView);
        }
        this.mWidth = this.mContext.getDisplay().getWidth();
        this.mHeight = this.mContext.getDisplay().getHeight();
        Log.i(TAG, "initView: mWidth = " + this.mWidth);
        Log.i(TAG, "initView: height = " + this.mHeight);
        this.mInt = (int) (((float) this.mInt) * getDensity());
    }

    private float getDensity() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.mContext.getDisplay().getMetrics(displayMetrics);
        return displayMetrics.density;
    }

    /* access modifiers changed from: private */
    public void addView() {
        WindowManager windowManager;
        Log.i(TAG, "addView: mIsShow = " + this.mIsShow);
        if (!this.mIsShow && (windowManager = mWindowManager) != null) {
            windowManager.addView(this.mTouchLayout, mLayoutParams);
            this.mIsShow = true;
            Log.i(TAG, "addView: success");
        }
    }

    public void removeView() {
        if (this.mIsShow) {
            WindowManager windowManager = mWindowManager;
            if (windowManager != null) {
                windowManager.removeView(this.mTouchLayout);
            }
            this.mIsShow = false;
        }
    }

    /* access modifiers changed from: private */
    public void updateView() {
        mLayoutParams.x = this.mX;
        mLayoutParams.y = this.mY;
        int i = this.mWidth - this.mX;
        if (i <= 0) {
            i = 1;
        }
        int i2 = this.mHeight - this.mY;
        if (i2 <= 0) {
            i2 = 1;
        }
        Log.i(TAG, "updateView: numX = " + i);
        Log.i(TAG, "updateView: numY = " + i2);
        Log.i(TAG, "updateView: mInt = " + this.mInt);
        if (i < this.mInt) {
            mLayoutParams.width = i;
        } else {
            mLayoutParams.width = -2;
        }
        if (i2 < this.mInt) {
            mLayoutParams.height = i2;
        } else {
            mLayoutParams.height = -2;
        }
        Log.i(TAG, "updateView: mLayoutParams.x = " + mLayoutParams.x);
        Log.i(TAG, "updateView: mLayoutParams.y = " + mLayoutParams.y);
        WindowManager windowManager = mWindowManager;
        if (windowManager != null) {
            windowManager.updateViewLayout(this.mTouchLayout, mLayoutParams);
        }
        ImageView imageView = this.mIvTouchView;
        if (imageView == null) {
            return;
        }
        if (this.mCapture == 1) {
            imageView.setBackgroundResource(R.drawable.imitate_auto_shouzhi);
        } else {
            imageView.setBackgroundResource(R.drawable.imitate_auto_shubiao);
        }
    }

    public void stopServer() {
        SocketServerThread socketServerThread = this.mThread;
        if (socketServerThread != null) {
            socketServerThread.exit();
            this.mThread = null;
        }
        removeView();
    }

    public void setCurTopClassName(String str) {
        if (str.startsWith("com.szchoiceway") || str.startsWith("com.choiceway") || str.startsWith("com.android.launcher3")) {
            this.mInThird = false;
            removeView();
        } else {
            this.mInThird = true;
        }
        this.mLastInThird = this.mInThird;
        Intent intent = new Intent("com.choiceway.eventcenter.action.CurTopClass");
        intent.putExtra("InThird", this.mInThird);
        this.mContext.sendBroadcast(intent);
        Log.i(TAG, "setCurTopClassName: sendbroadcast...");
        if (str.startsWith("com.android.launcher3")) {
            this.mInLauncher = true;
            this.mXRange = 6.8f;
            this.mYRange = 5.8f;
            return;
        }
        this.mInLauncher = false;
        this.mXRange = 7.0f;
        this.mYRange = 7.0f;
    }

    class SocketServerThread extends Thread {
        SocketServerThread() {
        }

        /* JADX WARNING: type inference failed for: r2v0, types: [java.io.PrintWriter, java.io.BufferedReader] */
        public void run() {
            LocalServerSocket localServerSocket;
            Log.i(CarPlaySocketServer.TAG, "run: ");
            ? r2 = 0;
            try {
                Log.i(CarPlaySocketServer.TAG, "run: ");
                localServerSocket = new LocalServerSocket(CarPlaySocketServer.PATH_TOUCH);
                while (!CarPlaySocketServer.this.exit) {
                    try {
                        Log.i(CarPlaySocketServer.TAG, "server.accept() start waiting...");
                        LocalSocket accept = localServerSocket.accept();
                        Log.i(CarPlaySocketServer.TAG, "server.accept() end, client connected.");
                        ConnectThread unused = CarPlaySocketServer.this.mConnectThread = new ConnectThread(accept);
                        CarPlaySocketServer.this.mConnectThread.setPriority(10);
                        CarPlaySocketServer.this.mConnectThread.start();
                    } catch (IOException e) {
                        e = e;
                        try {
                            e.printStackTrace();
                            r2.close();
                            r2.close();
                            localServerSocket.close();
                        } catch (Throwable th) {
                            th = th;
                            try {
                                r2.close();
                                r2.close();
                                localServerSocket.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            throw th;
                        }
                    }
                }
                try {
                    r2.close();
                    r2.close();
                    localServerSocket.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            } catch (IOException e4) {
                e = e4;
                localServerSocket = r2;
                e.printStackTrace();
                r2.close();
                r2.close();
                localServerSocket.close();
            } catch (Throwable th2) {
                th = th2;
                localServerSocket = r2;
                r2.close();
                r2.close();
                localServerSocket.close();
                throw th;
            }
        }

        public void exit() {
            boolean unused = CarPlaySocketServer.this.exit = true;
            interrupt();
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        class ConnectThread extends Thread {
            InputStream input = null;
            BufferedReader mBufferedReader = null;
            PrintWriter os = null;
            String readString = null;
            LocalSocket socket = null;

            public ConnectThread(LocalSocket localSocket) {
                this.socket = localSocket;
            }

            public void run() {
                while (!CarPlaySocketServer.this.exit) {
                    try {
                        InputStream inputStream = this.socket.getInputStream();
                        this.input = inputStream;
                        byte[] bArr = new byte[10];
                        int read = inputStream.read(bArr, 0, 10);
                        Log.i(CarPlaySocketServer.TAG, "run: len = " + read);
                        if (read == -1) {
                            if (CarPlaySocketServer.this.mConnectThread != null) {
                                CarPlaySocketServer.this.mConnectThread.interrupt();
                            }
                            CarPlaySocketServer.this.removeView();
                            return;
                        }
                        String bytesToHexString = EventUtils.bytesToHexString(bArr);
                        Log.i(CarPlaySocketServer.TAG, "run: buffer = " + bytesToHexString);
                        int unused = CarPlaySocketServer.this.mCapture = bArr[0];
                        int unused2 = CarPlaySocketServer.this.mTState = bArr[1];
                        int unused3 = CarPlaySocketServer.this.mX = (bArr[2] & 255) + ((bArr[3] & 255) * 256);
                        int unused4 = CarPlaySocketServer.this.mY = (bArr[4] & 255) + ((bArr[5] & 255) * 256);
                        int unused5 = CarPlaySocketServer.this.mTWidth = (bArr[6] & 255) + ((bArr[7] & 255) * 256);
                        int unused6 = CarPlaySocketServer.this.mTHeight = (bArr[8] & 255) + ((bArr[9] & 255) * 256);
                        Log.i(CarPlaySocketServer.TAG, "run: hidtag mCapture = " + CarPlaySocketServer.this.mCapture + ", mTState = " + CarPlaySocketServer.this.mTState + ", mX = " + CarPlaySocketServer.this.mX + ", mY = " + CarPlaySocketServer.this.mY + ",mTWidth = " + CarPlaySocketServer.this.mTWidth + ", mTHeight = " + CarPlaySocketServer.this.mTHeight);
                        CarPlaySocketServer.this.mHandler.post(new Runnable() {
                            public void run() {
                                if (CarPlaySocketServer.this.mInThird) {
                                    if (CarPlaySocketServer.this.mY <= 5) {
                                        Intent intent = new Intent();
                                        intent.setAction("com.szchoiceway.systemui.show");
                                        CarPlaySocketServer.this.mContext.sendBroadcast(intent);
                                    }
                                    CarPlaySocketServer.this.addView();
                                    CarPlaySocketServer.this.updateView();
                                    CarPlaySocketServer.this.mHandler.removeMessages(0);
                                    CarPlaySocketServer.this.mHandler.sendEmptyMessageDelayed(0, 10000);
                                    return;
                                }
                                int access$400 = CarPlaySocketServer.this.mTState;
                                if (access$400 == 0) {
                                    if (!CarPlaySocketServer.this.mResponse) {
                                        if (((double) (CarPlaySocketServer.this.mEndX - CarPlaySocketServer.this.mStartX)) > ((double) CarPlaySocketServer.this.mTWidth) / 7.5d) {
                                            CarPlaySocketServer.this.mEventService.processAutoKey(2);
                                        } else if (((double) (CarPlaySocketServer.this.mEndX - CarPlaySocketServer.this.mStartX)) < ((double) (-CarPlaySocketServer.this.mTWidth)) / 7.5d) {
                                            CarPlaySocketServer.this.mEventService.processAutoKey(1);
                                        } else if (((double) (CarPlaySocketServer.this.mEndY - CarPlaySocketServer.this.mStartY)) > ((double) CarPlaySocketServer.this.mTHeight) / 7.5d) {
                                            if (CarPlaySocketServer.this.mEventService.bInLeftFocus || !CarPlaySocketServer.this.mInLauncher) {
                                                CarPlaySocketServer.this.mEventService.processAutoKey(2);
                                            } else {
                                                CarPlaySocketServer.this.mEventService.processAutoKey(20);
                                            }
                                        } else if (((double) (CarPlaySocketServer.this.mEndY - CarPlaySocketServer.this.mStartY)) < ((double) (-CarPlaySocketServer.this.mTHeight)) / 7.5d) {
                                            if (CarPlaySocketServer.this.mEventService.bInLeftFocus || !CarPlaySocketServer.this.mInLauncher) {
                                                CarPlaySocketServer.this.mEventService.processAutoKey(1);
                                            } else {
                                                CarPlaySocketServer.this.mEventService.processAutoKey(19);
                                            }
                                        }
                                    }
                                    boolean unused = CarPlaySocketServer.this.mIsStartTouch = true;
                                    boolean unused2 = CarPlaySocketServer.this.mResponse = false;
                                } else if (access$400 == 1) {
                                    if (CarPlaySocketServer.this.mIsStartTouch) {
                                        int unused3 = CarPlaySocketServer.this.mStartX = CarPlaySocketServer.this.mX;
                                        int unused4 = CarPlaySocketServer.this.mStartY = CarPlaySocketServer.this.mY;
                                        Log.i(CarPlaySocketServer.TAG, "run: down mX = " + CarPlaySocketServer.this.mX + ", mY = " + CarPlaySocketServer.this.mY);
                                        boolean unused5 = CarPlaySocketServer.this.mIsStartTouch = false;
                                        boolean unused6 = CarPlaySocketServer.this.mResponse = false;
                                        return;
                                    }
                                    int unused7 = CarPlaySocketServer.this.mEndX = CarPlaySocketServer.this.mX;
                                    int unused8 = CarPlaySocketServer.this.mEndY = CarPlaySocketServer.this.mY;
                                    Log.i(CarPlaySocketServer.TAG, "run: up mX = " + CarPlaySocketServer.this.mX + ", mY = " + CarPlaySocketServer.this.mY);
                                    if (((float) (CarPlaySocketServer.this.mEndX - CarPlaySocketServer.this.mStartX)) > ((float) CarPlaySocketServer.this.mTWidth) / CarPlaySocketServer.this.mXRange) {
                                        int unused9 = CarPlaySocketServer.this.mStartX = CarPlaySocketServer.this.mEndX;
                                        CarPlaySocketServer.this.mEventService.processAutoKey(2);
                                        boolean unused10 = CarPlaySocketServer.this.mResponse = true;
                                    } else if (((float) (CarPlaySocketServer.this.mEndX - CarPlaySocketServer.this.mStartX)) < ((float) (-CarPlaySocketServer.this.mTWidth)) / CarPlaySocketServer.this.mXRange) {
                                        int unused11 = CarPlaySocketServer.this.mStartX = CarPlaySocketServer.this.mEndX;
                                        CarPlaySocketServer.this.mEventService.processAutoKey(1);
                                        boolean unused12 = CarPlaySocketServer.this.mResponse = true;
                                    }
                                    if (((float) (CarPlaySocketServer.this.mEndY - CarPlaySocketServer.this.mStartY)) > ((float) CarPlaySocketServer.this.mTHeight) / CarPlaySocketServer.this.mYRange) {
                                        int unused13 = CarPlaySocketServer.this.mStartY = CarPlaySocketServer.this.mEndY;
                                        if (CarPlaySocketServer.this.mEventService.bInLeftFocus || !CarPlaySocketServer.this.mInLauncher) {
                                            CarPlaySocketServer.this.mEventService.processAutoKey(2);
                                        } else {
                                            CarPlaySocketServer.this.mEventService.processAutoKey(20);
                                        }
                                        boolean unused14 = CarPlaySocketServer.this.mResponse = true;
                                    } else if (((float) (CarPlaySocketServer.this.mEndY - CarPlaySocketServer.this.mStartY)) < ((float) (-CarPlaySocketServer.this.mTHeight)) / CarPlaySocketServer.this.mYRange) {
                                        int unused15 = CarPlaySocketServer.this.mStartY = CarPlaySocketServer.this.mEndY;
                                        if (CarPlaySocketServer.this.mEventService.bInLeftFocus || !CarPlaySocketServer.this.mInLauncher) {
                                            CarPlaySocketServer.this.mEventService.processAutoKey(1);
                                        } else {
                                            CarPlaySocketServer.this.mEventService.processAutoKey(19);
                                        }
                                        boolean unused16 = CarPlaySocketServer.this.mResponse = true;
                                    }
                                }
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
