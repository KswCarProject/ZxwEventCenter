package com.szchoiceway.eventcenter;

import android.util.Log;

public class CarAirStruct {
    public static final String TAG = "CarAirStruct";
    public static final int WIND_DIRECTION_ALL = 7;
    public static final int WIND_DIRECTION_BOTTOM = 3;
    public static final int WIND_DIRECTION_CENTER = 2;
    public static final int WIND_DIRECTION_CENTER_BOTTOM = 6;
    public static final int WIND_DIRECTION_NULL = 0;
    public static final int WIND_DIRECTION_TOP = 1;
    public static final int WIND_DIRECTION_TOP_BOTTOM = 5;
    public static final int WIND_DIRECTION_TOP_CENTER = 4;
    public boolean bAirAcOn = false;
    public boolean bAirCulatOn = false;
    public boolean bAirEco = false;
    public boolean bAirFrontWin = false;
    public boolean bAirFunSpeedAuto = false;
    public boolean bAirFunSpeedHalf = false;
    public boolean bAirLeftFunAtuo = false;
    public boolean bAirLeftFunDown = false;
    public boolean bAirLeftFunParalle = false;
    public boolean bAirLeftFunUp = false;
    public boolean bAirOn = false;
    public boolean bAirRearWin = false;
    public boolean bAirRightFunAtuo = false;
    public boolean bAirRightFunDown = false;
    public boolean bAirRightFunParalle = false;
    public boolean bAirRightFunUp = false;
    public boolean bAirSync = false;
    public int iAirFunSpeed = 0;
    public int iAirLeftTemp = 0;
    public int iAirRightTemp = 0;
    public byte[] mAirData = new byte[10];
    public byte[] mAirDataOld = new byte[10];

    public CarAirStruct() {
        Log.i(TAG, TAG);
    }
}
