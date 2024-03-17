package com.szchoiceway.eventcenter;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;
import android.view.WindowManager;
import com.szchoiceway.eventcenter.AccEvent.AccRev;

public class EventApp extends Application {
    public static final String TAG = "EventApp";
    private AccRev mAccRevManager;
    private WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();

    public void onCreate() {
        CrashHandler.getInstance().init(getApplicationContext());
        startService(new Intent(this, EventService.class));
        Log.i("Eventapp", "onCreate");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.example.logcapture", "com.example.logcapture.LogService"));
        startService(intent);
    }

    public WindowManager.LayoutParams getMywmParams() {
        return this.wmParams;
    }

    public AccRev getAccRevManager() {
        if (this.mAccRevManager == null) {
            synchronized (EventApp.class) {
                if (this.mAccRevManager == null) {
                    this.mAccRevManager = new AccRev(this);
                }
            }
        }
        return this.mAccRevManager;
    }
}
