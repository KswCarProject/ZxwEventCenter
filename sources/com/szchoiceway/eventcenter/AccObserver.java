package com.szchoiceway.eventcenter;

import android.os.UEventObserver;
import android.util.Log;
import com.example.mylibrary.BuildConfig;
import java.io.File;
import java.io.FileInputStream;

public abstract class AccObserver extends UEventObserver {
    private static final String ACC_STATE_PATH = "/sys/class/gpio-detection/car-reverse/status";
    private static final String ACC_UEVENT_MATCH = "DEVPATH=/devices/virtual/gpio-detection/car-acc";
    private static final int MAX_DELAY = 50;
    private static final String TAG = "AccObserver";
    /* access modifiers changed from: private */
    public int mAccState = 48;
    /* access modifiers changed from: private */
    public int mBackcarState = 49;
    /* access modifiers changed from: private */
    public int mCheckDelay = 0;
    private Thread mGPIOMonitor = new Thread(new Runnable() {
        public void run() {
            File file = new File("/sys/devices/virtual/gpio-detection/car-acc/status");
            File file2 = new File("/sys/devices/virtual/gpio-detection/car-reverse/status");
            byte[] bArr = new byte[10];
            while (true) {
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    fileInputStream.read(bArr);
                    if (bArr[0] != AccObserver.this.mAccState) {
                        if (bArr[0] != 49) {
                            AccObserver.this.onAccEvent(1);
                            int unused = AccObserver.this.mCheckDelay = 50;
                            int unused2 = AccObserver.this.mAccState = bArr[0];
                        } else if (AccObserver.this.mCheckDelay == 0) {
                            AccObserver.this.onAccEvent(0);
                            int unused3 = AccObserver.this.mAccState = bArr[0];
                        }
                    }
                    fileInputStream.close();
                    FileInputStream fileInputStream2 = new FileInputStream(file2);
                    fileInputStream2.read(bArr);
                    if (bArr[0] != AccObserver.this.mBackcarState) {
                        int unused4 = AccObserver.this.mBackcarState = bArr[0];
                        if (bArr[0] == 48) {
                            AccObserver.this.onBackcarEvent(1);
                        } else {
                            AccObserver.this.onBackcarEvent(0);
                        }
                    }
                    fileInputStream2.close();
                    if (AccObserver.this.mCheckDelay > 0) {
                        AccObserver.access$110(AccObserver.this);
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            }
        }
    });
    private final Object mLock = new Object();

    /* access modifiers changed from: package-private */
    public abstract void onAccEvent(int i);

    /* access modifiers changed from: package-private */
    public abstract void onBackcarEvent(int i);

    public void onUEvent(UEventObserver.UEvent uEvent) {
    }

    static /* synthetic */ int access$110(AccObserver accObserver) {
        int i = accObserver.mCheckDelay;
        accObserver.mCheckDelay = i - 1;
        return i;
    }

    private String getState(String str, String str2) {
        String str3;
        char[] charArray = str.toCharArray();
        if (str.contains(str2)) {
            str3 = BuildConfig.FLAVOR;
            for (int indexOf = str.indexOf(str2) + str2.length(); charArray[indexOf] != ','; indexOf++) {
                if (charArray[indexOf] != '=') {
                    str3 = str3 + charArray[indexOf];
                }
            }
        } else {
            str3 = BuildConfig.FLAVOR;
        }
        return str3.replace(" ", BuildConfig.FLAVOR);
    }

    public void startObserving() {
        startObserving(ACC_UEVENT_MATCH);
        Log.d(TAG, "startObserving");
        this.mGPIOMonitor.start();
    }
}
