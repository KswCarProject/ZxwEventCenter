package com.szchoiceway.eventcenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AutoStart extends BroadcastReceiver {
    private static final String TAG = "AutoStart";

    public void onReceive(Context context, Intent intent) {
        Log.i("TAG", "onCreate AutoStart");
        String action = intent.getAction();
        Log.i(TAG, "action " + action);
        if ("android.intent.action.BOOT_COMPLETED".equals(action)) {
            Log.i(TAG, "ACTION_BOOT_COMPLETED");
            Intent intent2 = new Intent(context, EventService.class);
            intent2.putExtra("BOOT_COMPLETED", 1);
            try {
                context.startService(intent2);
            } catch (Exception unused) {
            }
        }
    }
}
