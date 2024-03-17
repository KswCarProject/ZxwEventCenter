package com.szchoiceway.eventcenter.SendDataToCan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.szchoiceway.eventcenter.EventService;
import com.szchoiceway.eventcenter.EventUtils;

public class SendDataToRichanCan extends BroadcastReceiver {
    private static final String TAG = "SendDataToRichanCan";
    protected EventService mContext = null;
    protected boolean register = false;

    public SendDataToRichanCan(EventService eventService) {
        this.mContext = eventService;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(EventUtils.ZXW_RADIO_INFO_EVT)) {
            SendModeInfo(1);
        } else if (action.equals(EventUtils.MCU_MSG_MAIL_VOL)) {
            SendVolInfo(intent.getIntExtra(EventUtils.MCU_MSG_MAIL_VOL_VAL, 0));
        } else if (action.equals(EventUtils.HBCP_EVT_HSHF_STATUS) || action.equals(EventUtils.HBCP_EVT_HSHF_GET_STATUS)) {
            intent.getStringExtra(EventUtils.INTENT_EXTRA_STR_KEYNAME);
            int intExtra = intent.getIntExtra(EventUtils.INTENT_EXTRA_INT_KEYNAME, 0);
            this.mContext.NotifyBTStatus(intExtra);
            if (this.mContext == null) {
                return;
            }
            if (intExtra == 3) {
                SendPhoneInfo(3);
            } else if (intExtra == 4) {
                SendPhoneInfo(2);
            } else if (intExtra == 5) {
                SendPhoneInfo(2);
            } else if (intExtra != 6) {
                SendPhoneInfo(0);
            } else {
                SendPhoneInfo(2);
            }
        }
    }

    public void registerReceiver() {
        if (this.register) {
            unregisterReceiver();
        }
        if (this.mContext != null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(EventUtils.HBCP_EVT_HSHF_STATUS);
            intentFilter.addAction(EventUtils.ZXW_ARRAY_BYTE_DATA_EVT);
            intentFilter.addAction(EventUtils.ZXW_CAN_KEY_EVT);
            intentFilter.addAction(EventUtils.ZXW_RADIO_INFO_EVT);
            intentFilter.addAction(EventUtils.MCU_MSG_MAIL_VOL);
            this.mContext.registerReceiver(this, intentFilter);
            this.register = true;
        }
        Log.i(TAG, "registerReceiver");
    }

    public void unregisterReceiver() {
        EventService eventService;
        if (this.register && (eventService = this.mContext) != null) {
            eventService.unregisterReceiver(this);
            this.register = false;
        }
        Log.i(TAG, "unregisterReceiver");
    }

    public void SendModeInfo(int i) {
        byte[] bArr = {-64, 8, 1, 1, 16, 0, 0, 0, 0, 0};
        if (i == 1) {
            EventService eventService = this.mContext;
            if (eventService != null) {
                byte radioBand = (byte) eventService.getRadioBand();
                if (radioBand <= 2) {
                    bArr[4] = (byte) (radioBand + 1);
                    bArr[5] = (byte) (this.mContext.getRadioFreq() & 255);
                    bArr[6] = (byte) ((this.mContext.getRadioFreq() >> 8) & 255);
                } else {
                    if (radioBand == 3) {
                        bArr[4] = 17;
                    } else {
                        bArr[4] = 18;
                    }
                    bArr[5] = (byte) (this.mContext.getRadioFreq() & 255);
                    bArr[6] = (byte) ((this.mContext.getRadioFreq() >> 8) & 255);
                }
            }
        } else if (i == 40) {
            bArr[2] = 7;
            bArr[3] = 48;
        } else if (i == 6) {
            bArr[2] = 5;
            bArr[3] = 64;
        } else if (i == 7) {
            bArr[2] = 11;
            bArr[3] = 64;
        } else if (i == 10) {
            bArr[2] = 12;
            bArr[3] = 48;
        } else if (i == 11) {
            bArr[2] = 12;
            bArr[3] = 48;
        }
        if (this.mContext != null) {
            SendCmdLstToCanbus(bArr);
        }
    }

    public void SendRadioInfo(int i) {
        byte[] bArr = {-62, 4, 0, 0, 0, 0};
        EventService eventService = this.mContext;
        if (eventService != null) {
            byte radioBand = (byte) eventService.getRadioBand();
            if (radioBand <= 2) {
                bArr[2] = (byte) (radioBand + 1);
                bArr[3] = (byte) (this.mContext.getRadioFreq() & 255);
                bArr[4] = (byte) ((this.mContext.getRadioFreq() >> 8) & 255);
                bArr[5] = (byte) (this.mContext.getRadioTuneNum() + 1);
            } else {
                if (radioBand == 3) {
                    bArr[2] = 17;
                } else {
                    bArr[2] = 18;
                }
                bArr[3] = (byte) (this.mContext.getRadioFreq() & 255);
                bArr[4] = (byte) ((this.mContext.getRadioFreq() >> 8) & 255);
                bArr[5] = (byte) (this.mContext.getRadioTuneNum() + 1);
            }
        }
        SendCmdLstToCanbus(bArr);
    }

    public void SendMediaInfo(int i) {
        if (i == 40 || i == 10 || i == 11) {
            SendCmdLstToCanbus(new byte[]{-61, 6, 0, 0, 0, 0, 0, 0});
        } else if (i == 6) {
            SendCmdLstToCanbus(new byte[]{-61, 6, 0, 0, 0, 0, 0, 0});
        } else if (i == 7) {
            SendCmdLstToCanbus(new byte[]{-61, 6, 0, 0, 0, 0, 0, 0});
        }
    }

    public void SendVolInfo(int i) {
        byte[] bArr = {-60, 1, 0};
        bArr[2] = (byte) i;
        SendCmdLstToCanbus(bArr);
    }

    public void SendPhoneInfo(int i) {
        byte[] bArr = {-59, 1, 0};
        bArr[2] = (byte) i;
        SendCmdLstToCanbus(bArr);
    }

    public void SetRadarSnd(boolean z) {
        byte[] bArr = {-58, 2, 0, 0};
        if (z) {
            bArr[3] = 1;
        } else {
            bArr[3] = 0;
        }
        SendCmdLstToCanbus(bArr);
    }

    public void SendBackCarMirrorSwitch(boolean z) {
        byte[] bArr = {-58, 2, 1, 0};
        if (z) {
            bArr[3] = 1;
        } else {
            bArr[3] = 0;
        }
        SendCmdLstToCanbus(bArr);
    }

    public void SendParkSwitch(boolean z) {
        byte[] bArr = {-58, 3, 1, 0};
        if (z) {
            bArr[3] = 0;
        } else {
            bArr[3] = 1;
        }
        SendCmdLstToCanbus(bArr);
    }

    public void SendCmdLstToCanbus(byte[] bArr) {
        if (bArr != null && bArr.length != 0) {
            byte[] bArr2 = new byte[(bArr.length + 2)];
            byte b = 0;
            bArr2[0] = 46;
            int i = 0;
            while (i < bArr.length) {
                int i2 = i + 1;
                bArr2[i2] = bArr[i];
                i = i2;
            }
            for (int i3 = 1; i3 < bArr.length + 1; i3++) {
                b = (byte) (b + bArr2[i3]);
            }
            bArr2[bArr.length + 1] = (byte) (b ^ 255);
            EventService eventService = this.mContext;
            if (eventService != null) {
                eventService.sendCanbusData(bArr2);
            }
        }
    }
}
