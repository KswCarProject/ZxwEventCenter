package com.szchoiceway.eventcenter.fattouchkey;

import android.graphics.Rect;
import android.util.Log;

public class TouchKeyStruct {
    public static final String TAG = "TouchKeyStruct";
    public Rect KeyRt = new Rect(0, 0, 0, 0);
    public int iKeyPosType = 0;
    public int iLearnStatus = 0;

    public TouchKeyStruct() {
        Log.i(TAG, "***TouchKeyStruct***");
    }
}
