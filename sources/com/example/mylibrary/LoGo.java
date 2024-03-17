package com.example.mylibrary;

import android.util.Log;

public class LoGo {
    public native void convertbmp(String str);

    public native void jnitest();

    static {
        System.loadLibrary("native-lib");
    }

    public void debuglog() {
        Log.i("LoGo", "debuglog:cdddddddddddd ");
    }
}
