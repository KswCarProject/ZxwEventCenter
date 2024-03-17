package com.szchoiceway.eventcenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.storage.StorageManager;
import android.util.Log;
import com.example.mylibrary.BuildConfig;
import com.szchoiceway.eventcenter.ICallbackfn;
import com.szchoiceway.eventcenter.View.DialogRestoreConfirm;
import com.szchoiceway.eventcenter.View.DialogTouchPaneCfg;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EvtModelFile extends BroadcastReceiver {
    public static final String ACTION_PHONE_STATE = "android.intent.action.PHONE_STATE";
    public static final String BROADCAST_APP_QUIT = "net.easyconn.app.quit";
    public static final String BROADCAST_BT_A2DP_ACQUIRE = "net.easyconn.a2dp.acquire";
    public static final String BROADCAST_BT_A2DP_RELEASE = "net.easyconn.a2dp.release";
    public static final String BROADCAST_BT_CHECKSTATUS = "net.easyconn.bt.checkstatus";
    public static final String BROADCAST_BT_CONNECT = "net.easyconn.bt.connect";
    public static final String BROADCAST_BT_CONNECTED = "net.easyconn.bt.connected";
    public static final String BROADCAST_BT_NOT_CONNECTED = "net.easyconn.bt.notconnected";
    public static final String BROADCAST_BT_OPENED = "net.easyconn.bt.opened";
    public static final String BROADCAST_CONNECTION_BREAK = "net.easyconn.connection.break";
    public static final String BROADCAST_EASYCONN_OPEN = "net.easyconn.app.open";
    public static final String BROADCAST_EASYCONN_RESUME = "net.easyconn.app.resume";
    private static final String LOG_TAG = "HomeReceiver EvtModel";
    public static final String SEND_PHONE_NAME_NUM_EVENT = "com.szchoiceway.btsuite.SEND_PHONE_NAME_NUM_EVENT";
    public static final String SEND_PHONE_NAME_NUM_EXTRA = "com.szchoiceway.btsuite.SEND_PHONE_NAME_NUM_EXTRA";
    private static final String SYSTEM_DIALOG_REASON_ASSIST = "assist";
    private static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
    private static final String SYSTEM_DIALOG_REASON_KEY = "reason";
    private static final String SYSTEM_DIALOG_REASON_LOCK = "lock";
    private static final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";
    private static final String TAG = "EvtModelFile";
    private static final int WM_RESET_MCU_EVT = 2;
    public static final String ZXW_CAN_KEY_EVT_NEXT = "com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT_NEXT";
    public static final String ZXW_CAN_KEY_EVT_PLAY_PAUSE = "com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT_PLAY_PAUSE";
    public static final String ZXW_CAN_KEY_EVT_PREV = "com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT_PREV";
    public static final String ZXW_CAN_WHEEL_TRACK_EVT = "com.choiceway.eventcenter.EventUtils.ZXW_CAN_WHEEL_TRACK_EVT";
    public static final String ZXW_CAN_WHEEL_TRACK_EVT_EXTRA = "com.choiceway.eventcenter.EventUtils.ZXW_CAN_WHEEL_TRACK_EVT_EXTRA";
    public static final String ZXW_RELOAD_TOUCH_KEY_CFG = "com.choiceway.FatUtils.ZXW_RELOAD_TOUCH_KEY_CFG";
    public static final String ZXW_SOUND_CONTROL_BACK_EVT = "com.choiceway.eventcenter.EventUtils.ZXW_SOUND_CONTROL_BACK_EVT";
    public static final String ZXW_TOUCH_LEARN_ID = "com.choiceway.FatUtils.ZXW_TOUCH_LEARN_ID";
    public static final String ZXW_TOUCH_LEARN_ID_EXTRA = "com.choiceway.FatUtils.ZXW_TOUCH_LEARN_ID_EXTRA";
    public static String card_dir = "/storage/external_sd/";
    public static String gpscard_dir = "/storage/external_sd0/";
    private static String mcu_upgrade_KSW_auto_filename = "mcu_update_auto.bin";
    private static String mcu_upgrade_MRW_filename = "";
    private static String mcu_upgrade_ZHTY_filename = "Update.nbd";
    public static String mediacard_dir = "/storage/external_sd1/";
    public static String usb_ = "/storage/usb_storage";
    public static String usb_dir = "/storage/usb_storage0/";
    private static String zxw_360_filename = "zxw_switch_360.txt";
    private static String zxw_9211_filename = "zxw_switch_9211.txt";
    private static String zxw_bt_upgrade_filename = "bt_update.bin";
    private static String zxw_debug_filename = "zxw_switch_debug.txt";
    private static String zxw_restore_factory_filename = "OEM/zxw_restore_factory.txt";
    private static String zxw_touch_calibration_filename = "OEM/zxw_tp_cal.txt";
    private static String zxw_touch_filename = "TouchPaneParam.cfg";
    private static String zxw_user_filename = "zxw_switch_user.txt";
    DialogRestoreConfirm dialog;
    DialogTouchPaneCfg dialogTouchPaneCfg;
    Handler handleProgress = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 2 && EvtModelFile.this.mContext != null) {
                EvtModelFile.this.mContext.SendResetMcu((byte) 1);
            }
        }
    };
    private String mConfigPath = "/dev/block/by-name/privdata2";
    protected EventService mContext = null;
    private ICallbackfn.Stub mModeCallback = new ICallbackfn.Stub() {
        public void notifyEvt(int i, int i2, int i3, byte[] bArr, String str) throws RemoteException {
        }
    };
    protected int m_iCurrDim = 0;
    protected boolean register = false;

    static {
        if (Build.VERSION.SDK_INT > 19) {
        }
    }

    public EvtModelFile(EventService eventService) {
        this.mContext = eventService;
    }

    public void registerReceiver() {
        if (this.register) {
            unregisterReceiver();
        }
        if (this.mContext != null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
            intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
            intentFilter.addDataScheme("file");
            this.mContext.registerReceiver(this, intentFilter);
            IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addAction("ZXW_USB_DEBUG_TEST");
            this.mContext.registerReceiver(this, intentFilter2);
            this.register = true;
            Log.i(TAG, "registerReceiver: isMountUSB() = " + isMountUSB());
            this.mContext.refreshUsbStatus(isMountUSB());
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

    public void onReceive(Context context, Intent intent) {
        EventService eventService;
        EventService eventService2;
        String action = intent.getAction();
        Log.i(TAG, "--->>> onReceive " + action);
        if (action.equalsIgnoreCase("android.intent.action.MEDIA_MOUNTED")) {
            String path = intent.getData().getPath();
            Log.i(TAG, "onReceive: path = " + path);
            Log.i(TAG, "--->>> Media --- USB --- MOUNTED");
            boolean autoUpgradeMCU = this.mContext.getAutoUpgradeMCU();
            Log.d(TAG, "ACTION_MEDIA_MOUNTED autoUpgrade = " + autoUpgradeMCU);
            File file = new File(path + "/" + mcu_upgrade_KSW_auto_filename);
            File file2 = new File(path + "/" + zxw_restore_factory_filename);
            File file3 = new File(path + "/" + zxw_user_filename);
            File file4 = new File(path + "/" + zxw_debug_filename);
            File file5 = new File(path + "/" + zxw_touch_filename);
            File file6 = new File(path + "/" + zxw_bt_upgrade_filename);
            File file7 = new File(path + "/" + zxw_touch_calibration_filename);
            File file8 = new File(path + "/" + zxw_9211_filename);
            File file9 = new File(path + "/" + zxw_360_filename);
            if (!file.exists()) {
                if (file2.exists()) {
                    Log.d(TAG, "restoreDialog");
                    DialogRestoreConfirm dialogRestoreConfirm = new DialogRestoreConfirm(this.mContext);
                    this.dialog = dialogRestoreConfirm;
                    dialogRestoreConfirm.showDialog();
                }
                if (file3.exists()) {
                    Log.d(TAG, "switchUserFile is exist");
                    if (Build.VERSION.SDK_INT == 31) {
                        this.mContext.configUtil.switchDebug("1");
                    } else {
                        this.mContext.configUtil2.switchDebug("1");
                    }
                }
                if (file4.exists()) {
                    Log.d(TAG, "switchDebugFile is exist");
                    if (Build.VERSION.SDK_INT == 31) {
                        this.mContext.configUtil.switchDebug("0");
                    } else {
                        this.mContext.configUtil2.switchDebug("0");
                    }
                }
                if (file8.exists() && this.mContext.configUtil != null) {
                    this.mContext.configUtil.switch9211("1");
                }
                if (file9.exists() && this.mContext.configUtil != null) {
                    this.mContext.configUtil.switch9211("0");
                }
                if (file5.exists()) {
                    DialogTouchPaneCfg dialogTouchPaneCfg2 = new DialogTouchPaneCfg(this.mContext);
                    this.dialogTouchPaneCfg = dialogTouchPaneCfg2;
                    dialogTouchPaneCfg2.showDialog();
                }
                if (file6.exists() && (eventService = this.mContext) != null) {
                    eventService.startService(new Intent("com.szchoiceway.updatemcu.UpdateBtService").setPackage("com.szchoiceway.updatemcu"));
                }
                if (file7.exists()) {
                    this.mContext.showCalibrationWnd();
                }
            } else if (autoUpgradeMCU && !this.mContext.isUpgradeMode() && (eventService2 = this.mContext) != null) {
                eventService2.mEventHandler.sendEmptyMessage(EventUtils.KSW_HANDLER_CHECK_MCU_UPGRADE_SERVICE_ALIVE);
            }
            Intent intent2 = new Intent("ZXW.USB_INSTALL_ACTION");
            intent2.putExtra("State_Type", 1);
            this.mContext.sendBroadcastAsUser(intent2, UserHandle.ALL);
            saveAppVer();
        } else if (action.equalsIgnoreCase("android.intent.action.MEDIA_UNMOUNTED")) {
            DialogRestoreConfirm dialogRestoreConfirm2 = this.dialog;
            if (dialogRestoreConfirm2 != null && dialogRestoreConfirm2.showDialog) {
                this.dialog.hideDialog();
            }
            DialogTouchPaneCfg dialogTouchPaneCfg3 = this.dialogTouchPaneCfg;
            if (dialogTouchPaneCfg3 != null && dialogTouchPaneCfg3.showDialog) {
                this.dialogTouchPaneCfg.hideDialog();
            }
            boolean autoUpgradeMCU2 = this.mContext.getAutoUpgradeMCU();
            Log.d(TAG, "ACTION_MEDIA_UNMOUNTED autoUpgrade = " + autoUpgradeMCU2);
            if (!autoUpgradeMCU2) {
                this.mContext.setAutoUpgradeMCU(true);
            }
        } else if ("ZXW_USB_DEBUG_TEST".equals(action)) {
            userDebug(intent.getStringExtra("value"));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0046 A[Catch:{ Exception -> 0x014a }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void saveAppVer() {
        /*
            r7 = this;
            java.lang.String r0 = "appVersion.txt"
            com.szchoiceway.eventcenter.EventService r1 = r7.mContext     // Catch:{ Exception -> 0x014a }
            java.lang.String r2 = ""
            java.lang.String r3 = "EvtModelFile"
            if (r1 == 0) goto L_0x0039
            com.szchoiceway.eventcenter.SysProviderOpt r1 = r1.mSysProviderOpt     // Catch:{ Exception -> 0x014a }
            if (r1 == 0) goto L_0x0039
            com.szchoiceway.eventcenter.EventService r1 = r7.mContext     // Catch:{ Exception -> 0x014a }
            com.szchoiceway.eventcenter.SysProviderOpt r1 = r1.mSysProviderOpt     // Catch:{ Exception -> 0x014a }
            java.lang.String r4 = "Sys_AppVersion"
            java.lang.String r2 = r1.getRecordValue(r4, r2)     // Catch:{ Exception -> 0x014a }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x014a }
            r1.<init>()     // Catch:{ Exception -> 0x014a }
            java.lang.String r4 = "saveAppVer: appVersion = "
            r1.append(r4)     // Catch:{ Exception -> 0x014a }
            r1.append(r2)     // Catch:{ Exception -> 0x014a }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x014a }
            android.util.Log.i(r3, r1)     // Catch:{ Exception -> 0x014a }
            com.szchoiceway.eventcenter.EventService r1 = r7.mContext     // Catch:{ Exception -> 0x014a }
            com.szchoiceway.eventcenter.SysProviderOpt r1 = r1.mSysProviderOpt     // Catch:{ Exception -> 0x014a }
            java.lang.String r4 = "RESOLUTION"
            java.lang.String r5 = "1280x480"
            java.lang.String r1 = r1.getRecordValue(r4, r5)     // Catch:{ Exception -> 0x014a }
            goto L_0x003a
        L_0x0039:
            r1 = r2
        L_0x003a:
            java.lang.String r4 = android.os.Environment.getExternalStorageState()     // Catch:{ Exception -> 0x014a }
            java.lang.String r5 = "mounted"
            boolean r4 = r4.equals(r5)     // Catch:{ Exception -> 0x014a }
            if (r4 == 0) goto L_0x014e
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x014a }
            r5 = 19
            if (r4 <= r5) goto L_0x00f1
            java.lang.String r4 = "/storage/emulated/0/log/"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x014a }
            r5.<init>()     // Catch:{ Exception -> 0x014a }
            java.lang.String r6 = "saveAppVer: isMountUSB() = "
            r5.append(r6)     // Catch:{ Exception -> 0x014a }
            boolean r6 = r7.isMountUSB()     // Catch:{ Exception -> 0x014a }
            r5.append(r6)     // Catch:{ Exception -> 0x014a }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x014a }
            android.util.Log.i(r3, r5)     // Catch:{ Exception -> 0x014a }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x014a }
            r5.<init>()     // Catch:{ Exception -> 0x014a }
            java.lang.String r6 = "saveAppVer: usb_ = "
            r5.append(r6)     // Catch:{ Exception -> 0x014a }
            java.lang.String r6 = usb_     // Catch:{ Exception -> 0x014a }
            r5.append(r6)     // Catch:{ Exception -> 0x014a }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x014a }
            android.util.Log.i(r3, r5)     // Catch:{ Exception -> 0x014a }
            boolean r3 = r7.isMountUSB()     // Catch:{ Exception -> 0x014a }
            if (r3 == 0) goto L_0x0095
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x014a }
            r3.<init>()     // Catch:{ Exception -> 0x014a }
            java.lang.String r4 = usb_     // Catch:{ Exception -> 0x014a }
            r3.append(r4)     // Catch:{ Exception -> 0x014a }
            java.lang.String r4 = "/log/"
            r3.append(r4)     // Catch:{ Exception -> 0x014a }
            java.lang.String r4 = r3.toString()     // Catch:{ Exception -> 0x014a }
        L_0x0095:
            java.io.File r3 = new java.io.File     // Catch:{ Exception -> 0x014a }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x014a }
            r5.<init>()     // Catch:{ Exception -> 0x014a }
            r5.append(r4)     // Catch:{ Exception -> 0x014a }
            r5.append(r0)     // Catch:{ Exception -> 0x014a }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x014a }
            r3.<init>(r5)     // Catch:{ Exception -> 0x014a }
            boolean r5 = r3.exists()     // Catch:{ Exception -> 0x014a }
            if (r5 == 0) goto L_0x00b2
            r3.delete()     // Catch:{ Exception -> 0x014a }
        L_0x00b2:
            java.io.File r3 = new java.io.File     // Catch:{ Exception -> 0x014a }
            r3.<init>(r4)     // Catch:{ Exception -> 0x014a }
            boolean r5 = r3.exists()     // Catch:{ Exception -> 0x014a }
            if (r5 != 0) goto L_0x00c0
            r3.mkdirs()     // Catch:{ Exception -> 0x014a }
        L_0x00c0:
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x014a }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x014a }
            r5.<init>()     // Catch:{ Exception -> 0x014a }
            r5.append(r4)     // Catch:{ Exception -> 0x014a }
            r5.append(r0)     // Catch:{ Exception -> 0x014a }
            java.lang.String r0 = r5.toString()     // Catch:{ Exception -> 0x014a }
            r3.<init>(r0)     // Catch:{ Exception -> 0x014a }
            java.lang.String r0 = r7.getCurTimerInfor()     // Catch:{ Exception -> 0x014a }
            byte[] r0 = r0.getBytes()     // Catch:{ Exception -> 0x014a }
            r3.write(r0)     // Catch:{ Exception -> 0x014a }
            byte[] r0 = r2.getBytes()     // Catch:{ Exception -> 0x014a }
            r3.write(r0)     // Catch:{ Exception -> 0x014a }
            byte[] r0 = r1.getBytes()     // Catch:{ Exception -> 0x014a }
            r3.write(r0)     // Catch:{ Exception -> 0x014a }
            r3.close()     // Catch:{ Exception -> 0x014a }
            goto L_0x014e
        L_0x00f1:
            java.lang.String r1 = "/mnt/usb_storage/log/"
            java.io.File r4 = new java.io.File     // Catch:{ Exception -> 0x014a }
            r4.<init>(r1)     // Catch:{ Exception -> 0x014a }
            boolean r5 = r4.exists()     // Catch:{ Exception -> 0x014a }
            r6 = 1
            if (r5 != 0) goto L_0x0118
            boolean r4 = r4.mkdirs()     // Catch:{ Exception -> 0x014a }
            if (r4 != 0) goto L_0x0117
            java.lang.String r1 = "/mnt/usb_storage1/log/"
            java.io.File r4 = new java.io.File     // Catch:{ Exception -> 0x014a }
            r4.<init>(r1)     // Catch:{ Exception -> 0x014a }
            boolean r5 = r4.exists()     // Catch:{ Exception -> 0x014a }
            if (r5 != 0) goto L_0x0118
            boolean r6 = r4.mkdirs()     // Catch:{ Exception -> 0x014a }
            goto L_0x0118
        L_0x0117:
            r6 = r4
        L_0x0118:
            if (r6 == 0) goto L_0x0144
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x014a }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x014a }
            r4.<init>()     // Catch:{ Exception -> 0x014a }
            r4.append(r1)     // Catch:{ Exception -> 0x014a }
            r4.append(r0)     // Catch:{ Exception -> 0x014a }
            java.lang.String r0 = r4.toString()     // Catch:{ Exception -> 0x014a }
            r3.<init>(r0)     // Catch:{ Exception -> 0x014a }
            java.lang.String r0 = r7.getCurTimerInfor()     // Catch:{ Exception -> 0x014a }
            byte[] r0 = r0.getBytes()     // Catch:{ Exception -> 0x014a }
            r3.write(r0)     // Catch:{ Exception -> 0x014a }
            byte[] r0 = r2.getBytes()     // Catch:{ Exception -> 0x014a }
            r3.write(r0)     // Catch:{ Exception -> 0x014a }
            r3.close()     // Catch:{ Exception -> 0x014a }
            goto L_0x014e
        L_0x0144:
            java.lang.String r0 = "saveCrashInfo2File: mkdirs = false"
            android.util.Log.i(r3, r0)     // Catch:{ Exception -> 0x014a }
            goto L_0x014e
        L_0x014a:
            r0 = move-exception
            r0.printStackTrace()
        L_0x014e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.eventcenter.EvtModelFile.saveAppVer():void");
    }

    private String getCurTimerInfor() {
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
        Log.i(TAG, "getCurTimerInfor: format = " + format);
        return format + " - ";
    }

    private String getUpdateFileName_MRW(String str) {
        File[] listFiles;
        File file = new File(str);
        if (!file.exists() || (listFiles = file.listFiles()) == null) {
            return BuildConfig.FLAVOR;
        }
        for (File file2 : listFiles) {
            if (!file2.isDirectory()) {
                String name = file2.getName();
                if (name.startsWith("MRW_") && name.endsWith(".bin")) {
                    return name;
                }
            }
        }
        return BuildConfig.FLAVOR;
    }

    private boolean isMountUSB() {
        StorageManager storageManager = (StorageManager) this.mContext.getSystemService("storage");
        String volumeState = storageManager.getVolumeState(usb_);
        Log.i(TAG, "isMountUSB: str = " + volumeState);
        Log.i(TAG, "isMountUSB: usb_ = " + usb_);
        if ("mounted".equals(volumeState)) {
            return true;
        }
        usb_ = "/storage/usb_storage1";
        if ("mounted".equals(storageManager.getVolumeState("/storage/usb_storage1"))) {
            return true;
        }
        usb_ = "/storage/usb_storage2";
        if ("mounted".equals(storageManager.getVolumeState("/storage/usb_storage2"))) {
            return true;
        }
        usb_ = "/storage/usb_storage3";
        if ("mounted".equals(storageManager.getVolumeState("/storage/usb_storage3"))) {
            return true;
        }
        usb_ = "/storage/usb_storage4";
        if ("mounted".equals(storageManager.getVolumeState("/storage/usb_storage4"))) {
            return true;
        }
        usb_ = "/storage/usb_storage5";
        if ("mounted".equals(storageManager.getVolumeState("/storage/usb_storage5"))) {
            return true;
        }
        return false;
    }

    private void userDebug(String str) {
        if (Build.VERSION.SDK_INT <= 31) {
            Log.d(TAG, "userDebug value = " + str);
            runCmd("chmod 777 " + this.mConfigPath);
            do {
            } while (getCmdResult());
            IniFile iniFile = new IniFile(new File(this.mConfigPath));
            String str2 = iniFile.get("zxw_Config", "zxw_no_debug", "0");
            Log.d(TAG, "localParam = " + str2);
            String recordValue = this.mContext.mSysProviderOpt.getRecordValue(SysProviderOpt.KSW_FACTORY_NO_DEBUG, "1");
            Log.d(TAG, "haveLogcat = " + recordValue);
            String str3 = iniFile.get("zxw_Config", "DeviceType", "0");
            String str4 = iniFile.get("zxw_Config", "ScreenType", "1");
            if (!str.equals(recordValue) || !str.equals(str2)) {
                Log.d(TAG, "update logcat value = " + str);
                iniFile.clearConfig();
                iniFile.set("zxw_Config", "DeviceType", str3);
                iniFile.set("zxw_Config", "ScreenType", str4);
                iniFile.set("zxw_Config", "zxw_no_debug", str);
                iniFile.save();
                this.mContext.mSysProviderOpt.updateRecord(SysProviderOpt.KSW_FACTORY_NO_DEBUG, str);
                this.mContext.rebootService();
            }
        }
    }

    public void runCmd(String str) {
        SystemProperties.set("sys.apk_path", str);
        SystemProperties.set("ctl.start", "install_apk");
    }

    public boolean getCmdResult() {
        return SystemProperties.get("sys.apk_path").equals("true");
    }
}
