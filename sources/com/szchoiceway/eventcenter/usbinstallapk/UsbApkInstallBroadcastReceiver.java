package com.szchoiceway.eventcenter.usbinstallapk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class UsbApkInstallBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        int intExtra;
        Log.i("TAG", "UsbApkInstallBroadcastReceiver-onReceive");
        if (intent.getAction().equals("ZXW.USB_INSTALL_ACTION") && (intExtra = intent.getIntExtra("State_Type", -1)) != -1) {
            if (intExtra == 0) {
                new Intent(context, UsbApkInstallService.class);
            } else if (intExtra == 1) {
                new Intent(context, UsbApkInstallService.class);
                Log.i("***UsbApkService***", "BroadcastReceiver UsbApkInstall");
            }
        }
    }
}
