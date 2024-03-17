package com.szchoiceway.eventcenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.example.mylibrary.BuildConfig;

public class CameraUtil {
    public static final int HANDLE_DOUBLE_LIGHT_OFF = 3;
    public static final int HANDLE_LEFT_LIGHT_OFF = 1;
    public static final int HANDLE_RIGHT_LIGHT_OFF = 2;
    private static final int INTETVAL = 1500;
    private static final int REFRESH_LIGHT = 0;
    private static final String TAG = "CameraUtil";
    private Handler handler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 0) {
                CameraUtil.this.sendLightMessage();
            } else if (i == 1) {
                boolean unused = CameraUtil.this.mLeftOn2 = false;
                if (!CameraUtil.this.mRightOn2 && !CameraUtil.this.mDoubleOn2) {
                    CameraUtil.this.sendLightMessage2();
                }
            } else if (i == 2) {
                boolean unused2 = CameraUtil.this.mRightOn2 = false;
                if (!CameraUtil.this.mLeftOn2 && !CameraUtil.this.mDoubleOn2) {
                    CameraUtil.this.sendLightMessage2();
                }
            } else if (i == 3) {
                boolean unused3 = CameraUtil.this.mDoubleOn2 = false;
                if (!CameraUtil.this.mLeftOn2 && !CameraUtil.this.mRightOn2) {
                    CameraUtil.this.sendLightMessage2();
                }
            }
        }
    };
    private Context mContext;
    /* access modifiers changed from: private */
    public boolean mDoubleOn2 = false;
    private int mGearType = -1;
    private boolean mLeftOn = false;
    /* access modifiers changed from: private */
    public boolean mLeftOn2 = false;
    private boolean mPOn = false;
    private boolean mRightOn = false;
    /* access modifiers changed from: private */
    public boolean mRightOn2 = false;
    private boolean mSmallOn = false;

    public void sendDoorMessage(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
    }

    public void sendPMessage(boolean z) {
    }

    public CameraUtil(Context context) {
        this.mContext = context;
    }

    public void sendPowerMessage(boolean z) {
        send360Broadcast("Power", z ? "off" : "on");
    }

    public void sendGearMessage(int i) {
        String str = "{\"type\": \"" + "AT" + "\",\"mode\": \"" + (i != 0 ? i != 1 ? i != 2 ? i != 3 ? BuildConfig.FLAVOR : "R" : "D" : "N" : "P") + "\"}";
        Log.d(TAG, "mGearType = " + this.mGearType + ", gearType = " + i);
        if (this.mGearType != i && i >= 0) {
            Log.d("sendGearMessage aaaaaa", str);
            send360Broadcast("gear_box", str);
            this.mGearType = i;
        }
    }

    public void sendLightMessage(boolean z, boolean z2, boolean z3) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"turn_left\": \"");
        String str = "on";
        sb.append(z2 ? str : "off");
        sb.append("\",\"turn_right\": \"");
        sb.append(z3 ? str : "off");
        sb.append("\",\"side\": \"");
        if (!z) {
            str = "off";
        }
        sb.append(str);
        sb.append("\"}");
        String sb2 = sb.toString();
        boolean z4 = this.mSmallOn;
        if ((!z4) != z && (!this.mLeftOn) != z2 && (!this.mRightOn) != z3) {
            return;
        }
        if (z4 != z) {
            this.mSmallOn = z;
            this.mLeftOn = z2;
            this.mRightOn = z3;
            Log.d("sendLightMessage aaaaaa", sb2);
            if (this.handler.hasMessages(0)) {
                send360Broadcast("light", sb2);
                return;
            }
            return;
        }
        this.mLeftOn = z2;
        this.mRightOn = z3;
        if (z2 || z3) {
            Log.d("sendLightMessage aaaaaa", sb2);
            if (this.handler.hasMessages(0)) {
                this.handler.removeMessages(0);
            }
            send360Broadcast("light", sb2);
            return;
        }
        this.handler.sendEmptyMessageDelayed(0, 1500);
    }

    public void sendLightMessage() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("{\"turn_left\": \"");
        String str2 = "on";
        sb.append(this.mLeftOn ? str2 : "off");
        sb.append("\",\"turn_right\": \"");
        if (this.mRightOn) {
            str = str2;
        } else {
            str = "off";
        }
        sb.append(str);
        sb.append("\",\"side\": \"");
        if (!this.mSmallOn) {
            str2 = "off";
        }
        sb.append(str2);
        sb.append("\"}");
        String sb2 = sb.toString();
        Log.d("sendLightMessage aaaaaa", sb2);
        send360Broadcast("light", sb2);
    }

    public void sendLightMessage2(boolean z, boolean z2, boolean z3) {
        if (SysProviderOpt.getInstance(this.mContext).getRecordInteger(SysProviderOpt.KESAIWEI_SYS_CAMERA_SELECTION, 0) == 3) {
            boolean z4 = z2 && z3;
            if (this.mDoubleOn2 == z4) {
                this.handler.removeMessages(3);
            } else if (z4) {
                this.mDoubleOn2 = true;
                this.mLeftOn2 = false;
                this.mRightOn2 = false;
                this.handler.removeMessages(1);
                this.handler.removeMessages(2);
                this.handler.removeMessages(3);
                sendLightMessage2();
            } else {
                this.handler.sendEmptyMessageDelayed(3, 1500);
            }
            if (!z4) {
                if (this.mLeftOn2 == z2) {
                    this.handler.removeMessages(1);
                } else if (z2) {
                    this.mLeftOn2 = true;
                    this.mRightOn2 = false;
                    this.mDoubleOn2 = false;
                    this.handler.removeMessages(1);
                    this.handler.removeMessages(2);
                    this.handler.removeMessages(3);
                    sendLightMessage2();
                } else {
                    this.handler.sendEmptyMessageDelayed(1, 1500);
                }
                if (this.mRightOn2 == z3) {
                    this.handler.removeMessages(2);
                } else if (z3) {
                    this.mRightOn2 = true;
                    this.mLeftOn2 = false;
                    this.mDoubleOn2 = false;
                    this.handler.removeMessages(1);
                    this.handler.removeMessages(2);
                    this.handler.removeMessages(3);
                    sendLightMessage2();
                } else {
                    this.handler.sendEmptyMessageDelayed(2, 1500);
                }
            }
        }
    }

    public void sendLightMessage2() {
        String str;
        if (this.mDoubleOn2) {
            str = "{\"turn_left\": \"on\",\"turn_right\": \"on\",\"side\": \"off\"}";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("{\"turn_left\": \"");
            String str2 = "on";
            sb.append(this.mLeftOn2 ? str2 : "off");
            sb.append("\",\"turn_right\": \"");
            if (!this.mRightOn2) {
                str2 = "off";
            }
            sb.append(str2);
            sb.append("\",\"side\": \"off\"}");
            str = sb.toString();
        }
        Log.d("sendLightMessage aaaaaa", str);
        send360Broadcast("light", str);
    }

    public void sendSpeedMessage(int i, int i2) {
        String str;
        if (i == 0) {
            str = "{\"kph\": " + i2 + "}";
        } else {
            str = "{\"mps\": " + i2 + "}";
        }
        Log.d("sendSpeedMessage aaaaaa", str);
        send360Broadcast("speed", str);
    }

    public void sendRadarMessage(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        String str = "{\"front_count\": 4,\"back_count\": 4,\"left_count\": 0,\"right_count\": 0,\"front\": [" + i + "," + i2 + "," + i3 + "," + i4 + "],\"back\": [" + i5 + "," + i6 + "," + i7 + "," + i8 + "]}";
        Log.d("sendRadarMessage aaaaaa", str);
        send360Broadcast("radar", str);
    }

    public void sendWheelMessage(float f, boolean z) {
        String str;
        if (f > 90.0f) {
            f = 90.0f;
        }
        if (f < -90.0f) {
            f = -90.0f;
        }
        if (z) {
            str = "{\"angle\": " + f + "}";
        } else {
            str = "{\"angle\": " + (-f) + "}";
        }
        Log.d("sendWheelMessage aaaaaa", str);
        send360Broadcast("steering_wheel", str);
    }

    private void send360Broadcast(String str, String str2) {
        if (SysProviderOpt.getInstance(this.mContext).getRecordInteger(SysProviderOpt.KESAIWEI_SYS_CAMERA_SELECTION, 1) == 3) {
            Intent intent = new Intent("com.atelectronic.atavm3d.toapp.carinfo");
            intent.putExtra(str, str2);
            Log.d(TAG, "send360Broadcast key = " + str + ", value = " + str2);
            this.mContext.sendBroadcast(intent);
        }
    }

    public void send360SpeechBroadcast(String str) {
        if (SysProviderOpt.getInstance(this.mContext).getRecordInteger(SysProviderOpt.KESAIWEI_SYS_CAMERA_SELECTION, 1) == 3) {
            Intent intent = new Intent("com.atelectronic.atavm3d.toapp.arctrl");
            intent.putExtra("speechCtrl", str);
            this.mContext.sendBroadcast(intent);
        }
    }
}
