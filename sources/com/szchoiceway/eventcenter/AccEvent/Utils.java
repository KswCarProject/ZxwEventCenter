package com.szchoiceway.eventcenter.AccEvent;

import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.net.wifi.WifiManager;
import android.os.PowerManager;
import android.os.SystemClock;
import android.os.UserHandle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import androidx.appcompat.widget.ActivityChooserView;
import com.szchoiceway.eventcenter.EventUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Utils {
    public static final int MODE_AUX = 1;
    public static final int MODE_BACKCAR = 99;
    public static final int MODE_CAR_AUX = 3;
    private static final String MODE_CHANGING_ACTION = "com.android.settings.location.MODE_CHANGING";
    public static final int MODE_DVR = 0;
    public static final int MODE_EXT_DVR = 2;
    public static final int MODE_EXT_USBDVR = 4;
    public static final int MODE_SHOW_CVBS = 5;
    public static final int MODE_TV = 6;
    private static final String NEW_MODE_KEY = "NEW_MODE";
    private static final String TAG = "CAM_Utils";
    private static boolean mFirstTimeInit = true;
    private static String[] mSysApkLst = {EventUtils.RADIO_MODE_PACKAGE_NAME, EventUtils.BT_MODE_PACKAGE_NAME, "com.szchoiceway.navigation", EventUtils.SET_MODE_PACKAGE_NAME, EventUtils.PHONEAPP_MODE_PACKAGE_NAME, "com.android.gallery3d", EventUtils.ESTRONGS_MODE_PACKAGE_NAME, EventUtils.ESUPER_MODE_PACKAGE_NAME, "com.szchoiceway.tpms", EventUtils.EXPLORER_MODE_PACKAGE_NAME, "com.android.browser", "req.dvr.status.action", "com.example.administrator.eqsetapplication", "com.google.android.gsf", "com.android.vending", "com.google.android.syncadapters.calendar", "com.google.android.syncadapters.contacts", "com.google.android.finsky.instantapps.statussync.StatusSyncService", "com.android.providers.calendar", "com.google.android.gms", EventUtils.ZLINK_MODE_PACKAGE_NAME};
    public static String[] mSysApkLstExt = {EventUtils.RADIO_MODE_PACKAGE_NAME, EventUtils.BT_MODE_PACKAGE_NAME, "com.szchoiceway.navigation", EventUtils.SET_MODE_PACKAGE_NAME, EventUtils.PHONEAPP_MODE_PACKAGE_NAME, "com.android.gallery3d", EventUtils.ESTRONGS_MODE_PACKAGE_NAME, EventUtils.ESUPER_MODE_PACKAGE_NAME, "com.szchoiceway.tpms", EventUtils.EXPLORER_MODE_PACKAGE_NAME, "com.android.browser", "req.dvr.status.action", "com.example.administrator.eqsetapplication", "com.google.android.gsf", "com.android.vending", "com.google.android.syncadapters.calendar", "com.google.android.syncadapters.contacts", "com.google.android.finsky.instantapps.statussync.StatusSyncService", "com.android.providers.calendar", "com.google.android.gms", EventUtils.MUSIC_MODE_PACKAGE_NAME, EventUtils.MOVIE_MODE_PACKAGE_NAME, EventUtils.ZLINK_MODE_PACKAGE_NAME};
    private static int mWifiState = 11;

    public static void killProcess(Context context, String str) {
        Log.d(TAG, "Utils::killProcess. package:" + str);
        try {
            ((ActivityManager) context.getSystemService("activity")).forceStopPackage(str);
        } catch (Exception e) {
            Log.e(TAG, "close " + str + " error");
            e.printStackTrace();
        }
    }

    public static void killapps(Context context, String[] strArr) {
        String packageName = context.getPackageName();
        if (!(strArr == null || strArr.length == 0)) {
            for (String str : strArr) {
                if (!str.equals(packageName)) {
                    killProcess(context, str);
                }
            }
        }
        List<PackageInfo> allCustomApps = getAllCustomApps(context);
        if (allCustomApps != null && allCustomApps.size() != 0) {
            for (PackageInfo next : allCustomApps) {
                if (!next.packageName.equals(packageName)) {
                    killProcess(context, next.packageName);
                }
            }
        }
    }

    public static void standby(Context context) {
        killapps(context, mSysApkLst);
        accOff(context);
        gotoSleep(context);
    }

    public static boolean isServiceWorked(Context context, String str) {
        Iterator it = ((ArrayList) ((ActivityManager) context.getSystemService("activity")).getRunningServices(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED)).iterator();
        while (it.hasNext()) {
            if (((ActivityManager.RunningServiceInfo) it.next()).service.getClassName().toString().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBtOn() {
        return BluetoothAdapter.getDefaultAdapter().isEnabled();
    }

    public static boolean enableBT(Context context, int i) {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (i == 1) {
            Settings.Global.putInt(context.getContentResolver(), "bluetooth_on", 1);
            if (!defaultAdapter.isEnabled()) {
                return defaultAdapter.enable();
            }
        } else {
            Settings.Global.putInt(context.getContentResolver(), "bluetooth_on", 0);
            if (defaultAdapter.isEnabled()) {
                return defaultAdapter.disable();
            }
        }
        return true;
    }

    public static boolean openBT(Context context) {
        return enableBT(context, 1);
    }

    public static boolean closeBT(Context context) {
        return enableBT(context, -1);
    }

    public static void forceWifiClose(Context context) {
        ((WifiManager) context.getSystemService("wifi")).setWifiEnabled(false);
    }

    public static void setWifiMode(Context context, boolean z) {
        Log.d(TAG, "setWifiMode = " + z);
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        if (!z) {
            wifiManager.setWifiEnabled(false);
            wifiManager.stopSoftAp();
            return;
        }
        wifiManager.setWifiEnabled(true);
    }

    public static boolean isGpsOn(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), "location_mode", 0) == 3;
    }

    public static void accOn(Context context) {
        openGps(context);
        Log.i(TAG, "openGps");
        enableAirplaneMode(context, false);
        enableMobileMode(context, true);
        Log.i(TAG, "accOn");
        setDataConnective(context, true);
        Log.i(TAG, "setDataConnective true");
        openBT(context);
        Log.i(TAG, "openBT");
        Log.i(TAG, "openWifi");
    }

    private static void accOff(Context context) {
        Log.i(TAG, "accOff");
        setDataConnective(context, false);
        Log.i(TAG, "setDataConnective false");
        closeBT(context);
        Log.i(TAG, "closeBT");
        closeGps(context);
        Log.i(TAG, "closeGps");
        setWifiMode(context, false);
        Log.i(TAG, "closeWifi");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0044 A[SYNTHETIC, Splitter:B:28:0x0044] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x004f A[SYNTHETIC, Splitter:B:33:0x004f] */
    /* JADX WARNING: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void openAdb(boolean r3) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            java.lang.String r1 = "1"
            r2 = 27
            if (r0 != r2) goto L_0x000e
            java.lang.String r3 = "sys.usb_debug_mode"
            android.os.SystemProperties.set(r3, r1)
            return
        L_0x000e:
            if (r3 == 0) goto L_0x0012
            java.lang.String r1 = "2"
        L_0x0012:
            java.io.File r3 = new java.io.File
            java.lang.String r0 = "/sys/devices/platform/usb0/dwc3_mode"
            r3.<init>(r0)
            boolean r0 = r3.exists()
            if (r0 != 0) goto L_0x0020
            return
        L_0x0020:
            r0 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x003e }
            r2.<init>(r3)     // Catch:{ FileNotFoundException -> 0x003e }
            byte[] r3 = r1.getBytes()     // Catch:{ IOException -> 0x0034 }
            r2.write(r3)     // Catch:{ IOException -> 0x0034 }
            goto L_0x0038
        L_0x002e:
            r3 = move-exception
            r0 = r2
            goto L_0x004d
        L_0x0031:
            r3 = move-exception
            r0 = r2
            goto L_0x003f
        L_0x0034:
            r3 = move-exception
            r3.printStackTrace()     // Catch:{ FileNotFoundException -> 0x0031, all -> 0x002e }
        L_0x0038:
            r2.close()     // Catch:{ IOException -> 0x0048 }
            goto L_0x004c
        L_0x003c:
            r3 = move-exception
            goto L_0x004d
        L_0x003e:
            r3 = move-exception
        L_0x003f:
            r3.printStackTrace()     // Catch:{ all -> 0x003c }
            if (r0 == 0) goto L_0x004c
            r0.close()     // Catch:{ IOException -> 0x0048 }
            goto L_0x004c
        L_0x0048:
            r3 = move-exception
            r3.printStackTrace()
        L_0x004c:
            return
        L_0x004d:
            if (r0 == 0) goto L_0x0057
            r0.close()     // Catch:{ IOException -> 0x0053 }
            goto L_0x0057
        L_0x0053:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0057:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.eventcenter.AccEvent.Utils.openAdb(boolean):void");
    }

    public static void closeGps(Context context) {
        Intent intent = new Intent(MODE_CHANGING_ACTION);
        intent.putExtra(NEW_MODE_KEY, 0);
        context.sendBroadcastAsUser(intent, UserHandle.ALL, "android.permission.WRITE_SECURE_SETTINGS");
        Settings.Secure.putInt(context.getContentResolver(), "location_mode", 0);
    }

    public static void openGps(Context context) {
        Intent intent = new Intent(MODE_CHANGING_ACTION);
        intent.putExtra(NEW_MODE_KEY, 3);
        context.sendBroadcastAsUser(intent, UserHandle.ALL, "android.permission.WRITE_SECURE_SETTINGS");
        Settings.Secure.putInt(context.getContentResolver(), "location_mode", 3);
    }

    private static void gotoSleep(Context context) {
        ((PowerManager) context.getSystemService("power")).goToSleep(SystemClock.uptimeMillis());
    }

    private static List<PackageInfo> getAllCustomApps(Context context) {
        ArrayList arrayList = new ArrayList();
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < installedPackages.size(); i++) {
            PackageInfo packageInfo = installedPackages.get(i);
            int i2 = packageInfo.applicationInfo.flags;
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            if ((i2 & 1) <= 0 && !packageInfo.packageName.startsWith("com.szchoiceway")) {
                arrayList.add(packageInfo);
            }
        }
        return arrayList;
    }

    public static void enableAirplaneMode(Context context, boolean z) {
        Settings.Global.putInt(context.getContentResolver(), "airplane_mode_on", z ? 1 : 0);
    }

    public static void enableMobileMode(Context context, boolean z) {
        Settings.Global.putInt(context.getContentResolver(), "mobile_data", z ? 1 : 0);
    }

    public static void setDataConnective(Context context, boolean z) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (z) {
            telephonyManager.enableDataConnectivity();
        } else {
            telephonyManager.disableDataConnectivity();
        }
    }
}
