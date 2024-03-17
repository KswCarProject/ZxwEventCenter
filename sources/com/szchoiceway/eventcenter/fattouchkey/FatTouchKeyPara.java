package com.szchoiceway.eventcenter.fattouchkey;

import android.util.Log;

public class FatTouchKeyPara {
    public static String StrTouchKeyBndRt = "0.447.40.527.0.0";
    public static String StrTouchKeyHomeRt = "0.147.40.227.0.1";
    public static String StrTouchKeyHungUpRt = "0.447.40.527.0.0";
    public static String StrTouchKeyModeRt = "0.447.40.527.0.0";
    public static String StrTouchKeyMuteRt = "0.447.40.527.0.0";
    public static String StrTouchKeyNaviRt = "0.447.40.527.0.0";
    public static String StrTouchKeyNextRt = "0.447.40.527.0.0";
    public static String StrTouchKeyPowerRt = "0.46.40.127.0.1";
    public static String StrTouchKeyPrevRt = "0.447.40.527.0.0";
    public static String StrTouchKeyTalkRt = "0.447.40.527.0.0";
    public static String StrTouchKeyVolDownRt = "0.447.40.527.0.1";
    public static String StrTouchKeyVolUpRt = "0.347.40.427.0.1";
    public static String StrTouchKeyrReturnRt = "0.247.40.327.0.1";
    public static final String TAG = "FatTouchKeyPara";
    public static int iKeyPosType = 1;

    public FatTouchKeyPara() {
        Log.i(TAG, "***FatTouchKeyPara***");
    }

    /* access modifiers changed from: package-private */
    public void InitPara() {
        iKeyPosType = 1;
        StrTouchKeyPowerRt = "0.46.40.127.0.1";
        StrTouchKeyHomeRt = "0.147.40.227.0.1";
        StrTouchKeyrReturnRt = "0.247.40.327.0.1";
        StrTouchKeyVolUpRt = "0.347.40.427.0.1";
        StrTouchKeyVolDownRt = "0.447.40.527.0.1";
        StrTouchKeyNextRt = "0.447.40.527.0.0";
        StrTouchKeyPrevRt = "0.447.40.527.0.0";
        StrTouchKeyBndRt = "0.447.40.527.0.0";
        StrTouchKeyMuteRt = "0.447.40.527.0.0";
        StrTouchKeyTalkRt = "0.447.40.527.0.0";
        StrTouchKeyHungUpRt = "0.447.40.527.0.0";
        StrTouchKeyNaviRt = "0.447.40.527.0.0";
        StrTouchKeyModeRt = "0.447.40.527.0.0";
    }
}
