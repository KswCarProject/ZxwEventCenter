package com.szchoiceway.eventcenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemProperties;
import android.util.Log;

public class CameraUtilXYQ {
    public static final int HANDLER_LIGHT_OFF = 1;
    public static final int HANDLE_TIME_OUT = 100;
    private static final String TAG = "CameraUtilXYQ";
    public static final int TIME_OUT = 6000;
    Handler handler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                CameraUtilXYQ.this.sendLightMessageData(false, false);
            }
        }
    };
    private boolean isReverse = false;
    private Context mContext;
    private boolean mDoubleOn = false;
    private boolean mLeftOn = false;
    private boolean mLightOn = false;
    private boolean mRightOn = false;

    public CameraUtilXYQ(Context context) {
        this.mContext = context;
    }

    public boolean getReverse() {
        return this.isReverse;
    }

    public void enterBackCar() {
        if (SysProviderOpt.getInstance(this.mContext).getRecordInteger(SysProviderOpt.KESAIWEI_SYS_CAMERA_SELECTION, 0) == 3) {
            this.isReverse = true;
            Intent intent = new Intent(EventUtils.ZXW_ACTION_ENTER_REVERSE_IMAGE);
            Log.d(TAG, "send360Broadcast ZXW_ACTION_ENTER_REVERSE_IMAGE");
            this.mContext.sendBroadcast(intent);
            SystemProperties.set("sys.backcar.state", "1");
        }
    }

    public void exitBackCar() {
        if (SysProviderOpt.getInstance(this.mContext).getRecordInteger(SysProviderOpt.KESAIWEI_SYS_CAMERA_SELECTION, 0) == 3) {
            this.isReverse = false;
            Intent intent = new Intent(EventUtils.ZXW_ACTION_EXIT_REVERSE_IMAGE);
            Log.d(TAG, "send360Broadcast ZXW_ACTION_EXIT_REVERSE_IMAGE");
            this.mContext.sendBroadcast(intent);
            SystemProperties.set("sys.backcar.state", "0");
        }
    }

    public void sendSpeedMessage(int i, int i2) {
        String str;
        if (SysProviderOpt.getInstance(this.mContext).getRecordInteger(SysProviderOpt.KESAIWEI_SYS_CAMERA_SELECTION, 0) == 3) {
            if (i == 0) {
                str = "{kph: " + i2 + "}";
            } else {
                str = "{mps: " + i2 + "}";
            }
            Intent intent = new Intent("com.atelectronic.atavm3d.toapp.carinfo");
            intent.putExtra("speed", str);
            Log.d(TAG, "sendSpeedMessage value = " + str);
            this.mContext.sendBroadcast(intent);
        }
    }

    public void sendWheelMessage(int i, boolean z) {
        int i2;
        if (SysProviderOpt.getInstance(this.mContext).getRecordInteger(SysProviderOpt.KESAIWEI_SYS_CAMERA_SELECTION, 0) == 3) {
            Log.d(TAG, "angle = " + i + ", right = " + z);
            if (z) {
                i2 = EventUtils.BIT_OFF(i, 7);
            } else {
                i2 = EventUtils.BIT_ON(i, 7);
            }
            Intent intent = new Intent("com.choiceway.eventcenter.EventUtils.ZXW_CAN_WHEEL_TRACK_EVT");
            intent.putExtra("com.choiceway.eventcenter.EventUtils.ZXW_CAN_WHEEL_TRACK_EVT_EXTRA", i2);
            Log.d(TAG, "sendWheelMessage data = " + i2);
            this.mContext.sendBroadcast(intent);
        }
    }

    public void sendDoorMessage(boolean z, boolean z2, boolean z3, boolean z4) {
        int i;
        int i2;
        int i3;
        int i4;
        if (SysProviderOpt.getInstance(this.mContext).getRecordInteger(SysProviderOpt.KESAIWEI_SYS_CAMERA_SELECTION, 0) == 3) {
            if (SysProviderOpt.getInstance(this.mContext).getRecordBoolean(SysProviderOpt.SYS_DOOR_SET_VALUE_INDEX_KEY, false)) {
                boolean z5 = z2;
                z2 = z;
                z = z5;
                boolean z6 = z4;
                z4 = z3;
                z3 = z6;
            }
            int recordInteger = SysProviderOpt.getInstance(this.mContext).getRecordInteger(SysProviderOpt.SYS_DOOR_DISPLAYSET_VALUE_INDEX_KEY, 0);
            if (recordInteger > 0) {
                z3 = false;
                z4 = false;
            }
            if (recordInteger > 1) {
                z = false;
                z2 = false;
            }
            if (z) {
                i = EventUtils.BIT_ON(0, 7);
            } else {
                i = EventUtils.BIT_OFF(0, 7);
            }
            byte b = (byte) (i & 255);
            if (z2) {
                i2 = EventUtils.BIT_ON(b, 6);
            } else {
                i2 = EventUtils.BIT_OFF(b, 6);
            }
            byte b2 = (byte) (i2 & 255);
            if (z3) {
                i3 = EventUtils.BIT_ON(b2, 5);
            } else {
                i3 = EventUtils.BIT_OFF(b2, 5);
            }
            byte b3 = (byte) (i3 & 255);
            if (z4) {
                i4 = EventUtils.BIT_ON(b3, 4);
            } else {
                i4 = EventUtils.BIT_OFF(b3, 4);
            }
            byte b4 = (byte) (i4 & 255);
            Intent intent = new Intent(EventUtils.ZXW_CAN_ACCORD_DOOR_INFO);
            intent.putExtra(EventUtils.ZXW_CAN_ACCORD_DOOR_INFO_EXTRA, b4);
            Log.d(TAG, "sendDoorMessage data = " + b4);
            this.mContext.sendBroadcast(intent);
        }
    }

    public void sendLightMessage(boolean z, boolean z2) {
        Log.d(TAG, "sendLightMessage leftOn = " + z + ", rightOn = " + z2);
        if (SysProviderOpt.getInstance(this.mContext).getRecordInteger(SysProviderOpt.KESAIWEI_SYS_CAMERA_SELECTION, 0) == 3) {
            if (z || z2) {
                this.handler.removeMessages(1);
                this.mLightOn = true;
                sendLightMessageData(z, z2);
            } else if (this.mLightOn) {
                this.handler.removeMessages(1);
                this.mLightOn = false;
                this.handler.sendEmptyMessageDelayed(1, 1500);
            }
        }
    }

    public void sendLightMessageData(boolean z, boolean z2) {
        if (SysProviderOpt.getInstance(this.mContext).getRecordInteger(SysProviderOpt.KESAIWEI_SYS_CAMERA_SELECTION, 0) == 3) {
            Log.d(TAG, "sendLightMessageData leftOn = " + z + ", rightOn = " + z2);
            if (!z || !z2) {
                if (this.mDoubleOn) {
                    this.mDoubleOn = false;
                    Intent intent = new Intent(EventUtils.ZXW_ACTION_DOUBLE_FLASH);
                    intent.putExtra(EventUtils.ZXW_ACTION_LIGHT_EXTRA, 0);
                    Log.d(TAG, "send360Broadcast ZXW_ACTION_DOUBLE_FLASH data = 0");
                    this.mContext.sendBroadcast(intent);
                }
                if (z) {
                    Intent intent2 = new Intent(EventUtils.ZXW_ACTION_TURN_LEFT);
                    intent2.putExtra(EventUtils.ZXW_ACTION_LIGHT_EXTRA, 1);
                    Log.d(TAG, "send360Broadcast ZXW_ACTION_TURN_LEFT data = 1");
                    this.mContext.sendBroadcast(intent2);
                }
                this.mLeftOn = z;
                if (z2) {
                    Intent intent3 = new Intent(EventUtils.ZXW_ACTION_TURN_RIGHT);
                    intent3.putExtra(EventUtils.ZXW_ACTION_LIGHT_EXTRA, 1);
                    Log.d(TAG, "send360Broadcast ZXW_ACTION_TURN_RIGHT data = 1");
                    this.mContext.sendBroadcast(intent3);
                }
                this.mRightOn = z2;
                if (!this.mLeftOn && !z2) {
                    Intent intent4 = new Intent(EventUtils.ZXW_ACTION_TURN_LEFT);
                    intent4.putExtra(EventUtils.ZXW_ACTION_LIGHT_EXTRA, 0);
                    Log.d(TAG, "send360Broadcast ZXW_ACTION_TURN_LEFT data = 0");
                    this.mContext.sendBroadcast(intent4);
                    Intent intent5 = new Intent(EventUtils.ZXW_ACTION_TURN_RIGHT);
                    intent5.putExtra(EventUtils.ZXW_ACTION_LIGHT_EXTRA, 0);
                    Log.d(TAG, "send360Broadcast ZXW_ACTION_TURN_RIGHT data = 0");
                    this.mContext.sendBroadcast(intent5);
                    return;
                }
                return;
            }
            this.mDoubleOn = true;
            Intent intent6 = new Intent(EventUtils.ZXW_ACTION_DOUBLE_FLASH);
            intent6.putExtra(EventUtils.ZXW_ACTION_LIGHT_EXTRA, 1);
            Log.d(TAG, "send360Broadcast ZXW_ACTION_DOUBLE_FLASH data = 1");
            this.mContext.sendBroadcast(intent6);
        }
    }

    public void sendRadarMessage(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        if (SysProviderOpt.getInstance(this.mContext).getRecordInteger(SysProviderOpt.KESAIWEI_SYS_CAMERA_SELECTION, 0) == 3) {
            if (i9 > 30) {
                i = 0;
                i2 = 0;
                i3 = 0;
                i4 = 0;
                i5 = 0;
                i6 = 0;
                i7 = 0;
                i8 = 0;
            }
            Log.d(TAG, "sendRadarMessage radarFrontLeft = " + i + ", radarFrontLeftMiddle = " + i2 + ", radarFrontRightMiddle = " + i3 + ", radarFrontRight = " + i4 + ", radarBackLeft = " + i5 + ",  radarBackLeftMiddle = " + i6 + ", radarBackRightMiddle = " + i7 + ", radarBackRight = " + i8);
            byte[] bArr = {0, (byte) radarData(i), (byte) radarData(i2), (byte) radarData(i3), (byte) radarData(i4), (byte) radarData(i5), (byte) radarData(i6), (byte) radarData(i7), (byte) radarData(i8)};
            Intent intent = new Intent(EventUtils.ZXW_ACTION_MCU_CAR_CAN_RADAR_INFO);
            intent.putExtra(EventUtils.ZXW_ACTION_MCU_CAR_CAN_RADAR_INFO_EXTRA, bArr);
            StringBuilder sb = new StringBuilder();
            sb.append("sendRadarMessage data = ");
            sb.append(EventUtils.bytesToHexString(bArr));
            Log.d(TAG, sb.toString());
            this.mContext.sendBroadcast(intent);
        }
    }

    private int radarData(int i) {
        if (i <= 1) {
            return 1;
        }
        return (i * 160) / EventUtils.HANDLER_LAMP_EVT;
    }

    public void send360SpeechBroadcast(String str) {
        if (SysProviderOpt.getInstance(this.mContext).getRecordInteger(SysProviderOpt.KESAIWEI_SYS_CAMERA_SELECTION, 1) == 3) {
            Intent intent = new Intent("com.atelectronic.atavm3d.toapp.arctrl");
            intent.putExtra("speechCtrl", str);
            Log.d(TAG, "send360SpeechBroadcast value = " + str);
            this.mContext.sendBroadcast(intent);
        }
    }

    public void exit360() {
        this.mContext.sendBroadcast(new Intent("com.ivicar.avm.action.HIDE_IVICAR"));
    }
}
