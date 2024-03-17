package com.szchoiceway.eventcenter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

public class ForceLandPortService extends Service {
    protected static final String TAG = "ForceLandPortService";
    private View view;
    private WindowManager wm = null;
    private WindowManager.LayoutParams wmCalibrateParams = null;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        this.wm = (WindowManager) getApplicationContext().getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(0, 0, 2005, 8, -3);
        this.wmCalibrateParams = layoutParams;
        layoutParams.gravity = 48;
        this.wmCalibrateParams.screenOrientation = 0;
        View view2 = new View(getApplicationContext());
        this.view = view2;
        this.wm.addView(view2, this.wmCalibrateParams);
        Log.i(TAG, "****onCreate*****");
        super.onCreate();
    }
}
