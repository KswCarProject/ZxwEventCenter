package com.szchoiceway.eventcenter;

import android.os.Build;

public class NativeCamera {
    public static native int checkEncryptIC();

    public static native int checkLandscapeEncryptIC();

    public static native void closeCamera();

    public static native void closeDvd();

    public static native int getSignalState();

    public static native void openCamera();

    public static native void openDvd();

    public static native void setCameraRect(int i, int i2, int i3, int i4);

    public static native void setColorKey(int i);

    public static native void setRoteta(int i);

    public static native void setSignalChannal(int i);

    static {
        if (Build.VERSION.SDK_INT <= 19) {
            System.loadLibrary("camera");
        }
    }
}
