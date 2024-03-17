package com.szchoiceway.eventcenter.usbinstallapk;

import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.szchoiceway.eventcenter.EventUtils;
import com.szchoiceway.eventcenter.R;
import java.io.File;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class UsbApkInstallService extends Service {
    private static int INSTALLED = 0;
    private static int INSTALLED_UPDATE = 2;
    public static final int INSTALL_END = 5378;
    public static final int SHOW_DIALOG = 5380;
    public static final int START_COUNT_DOWN = 5379;
    public static final int START_INSTALL_APK = 5377;
    public static final int START_SCAN_FILE = 5376;
    protected static final String TAG = "FactoryAPKInstall";
    private static int UNINSTALLED = 1;
    /* access modifiers changed from: private */
    public static String usb_dir = "/storage/usb_storage0/zxwapk";
    /* access modifiers changed from: private */
    public static String usb_dir1 = "/storage/usb_storage1/zxwapk";
    /* access modifiers changed from: private */
    public static String usb_dir2 = "/storage/usb_storage2/zxwapk";
    /* access modifiers changed from: private */
    public static String usb_dir3 = "/storage/usb_storage3/zxwapk";
    /* access modifiers changed from: private */
    public static String usb_dir4 = "/storage/external_sd1/zxwapk";
    /* access modifiers changed from: private */
    public LinkedList ls = new LinkedList();
    /* access modifiers changed from: private */
    public AlertDialog mDialog;
    Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case UsbApkInstallService.START_SCAN_FILE /*5376*/:
                    File file = new File(UsbApkInstallService.usb_dir);
                    if (!file.exists()) {
                        file = new File(UsbApkInstallService.usb_dir1);
                        if (!file.exists()) {
                            file = new File(UsbApkInstallService.usb_dir2);
                            if (!file.exists()) {
                                file = new File(UsbApkInstallService.usb_dir3);
                                if (!file.exists()) {
                                    file = new File(UsbApkInstallService.usb_dir4);
                                }
                            }
                        }
                    }
                    if (file.exists()) {
                        UsbApkInstallService.this.FindAllAPKFile(file);
                        UsbApkInstallService.this.mHandler.sendEmptyMessageDelayed(UsbApkInstallService.SHOW_DIALOG, 500);
                        return;
                    }
                    return;
                case UsbApkInstallService.START_INSTALL_APK /*5377*/:
                    int size = UsbApkInstallService.this.ls.size();
                    if (!UsbApkInstallService.this.mDialog.isShowing()) {
                        UsbApkInstallService usbApkInstallService = UsbApkInstallService.this;
                        AlertDialog.Builder builder = new AlertDialog.Builder(UsbApkInstallService.this);
                        AlertDialog unused = usbApkInstallService.mDialog = builder.setTitle(UsbApkInstallService.this.getString(R.string.lbl_installing) + "\t" + size + "\t" + UsbApkInstallService.this.getString(R.string.lbl_case)).setCancelable(false).create();
                        UsbApkInstallService.this.mDialog.getWindow().setType(2003);
                        UsbApkInstallService.this.mDialog.show();
                    }
                    AlertDialog access$600 = UsbApkInstallService.this.mDialog;
                    access$600.setTitle(UsbApkInstallService.this.getString(R.string.lbl_installing) + "\t" + size + "\t" + UsbApkInstallService.this.getString(R.string.lbl_case));
                    UsbApkInstallService usbApkInstallService2 = UsbApkInstallService.this;
                    usbApkInstallService2.installSlient2(usbApkInstallService2.ls);
                    return;
                case UsbApkInstallService.INSTALL_END /*5378*/:
                    UsbApkInstallService.this.mOffTextView.setText(UsbApkInstallService.this.getString(R.string.lbl_install_success));
                    UsbApkInstallService.this.mDialog.setTitle(UsbApkInstallService.this.getString(R.string.lbl_install_success));
                    UsbApkInstallService.this.mDialog.setView(UsbApkInstallService.this.mOffTextView);
                    UsbApkInstallService.this.mDialog.getWindow().setType(2003);
                    UsbApkInstallService.this.mDialog.show();
                    UsbApkInstallService.this.mHandler.sendEmptyMessageDelayed(UsbApkInstallService.START_COUNT_DOWN, 2000);
                    return;
                case UsbApkInstallService.START_COUNT_DOWN /*5379*/:
                    if (UsbApkInstallService.this.mDialog != null) {
                        UsbApkInstallService.this.mDialog.dismiss();
                        return;
                    }
                    return;
                case UsbApkInstallService.SHOW_DIALOG /*5380*/:
                    UsbApkInstallService.this.initDialog(UsbApkInstallService.this.ls.size());
                    return;
                default:
                    return;
            }
        }
    };
    /* access modifiers changed from: private */
    public Handler mOffHandler;
    /* access modifiers changed from: private */
    public TextView mOffTextView;
    /* access modifiers changed from: private */
    public Timer mOffTime;
    private Timer mOffTime2;
    private ProgressBar mUpgradeProcessBar = null;

    public IBinder onBind(Intent intent) {
        return null;
    }

    static {
        if (Build.VERSION.SDK_INT > 19) {
        }
    }

    public void onCreate() {
        Log.i("***UsbApkService***", "start UsbApkInstall");
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        searchUtils();
        return super.onStartCommand(intent, i, i2);
    }

    private void searchUtils() {
        this.mHandler.sendEmptyMessageDelayed(START_SCAN_FILE, 200);
    }

    public void FindAllAPKFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                String path = file.getPath();
                if (path.endsWith(".apk")) {
                    Log.i(TAG, "FindAllAPKFile: " + path);
                    PackageManager packageManager = getPackageManager();
                    PackageInfo packageArchiveInfo = packageManager.getPackageArchiveInfo(path, 1);
                    ApplicationInfo applicationInfo = packageArchiveInfo.applicationInfo;
                    applicationInfo.sourceDir = path;
                    applicationInfo.publicSourceDir = path;
                    applicationInfo.loadIcon(packageManager);
                    String str = packageArchiveInfo.packageName;
                    String str2 = packageArchiveInfo.versionName;
                    int i = packageArchiveInfo.versionCode;
                    this.ls.add(path);
                    return;
                }
                return;
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File FindAllAPKFile : listFiles) {
                    FindAllAPKFile(FindAllAPKFile);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void installSlient2(LinkedList linkedList) {
        try {
            if (linkedList.size() > 0) {
                final String str = (String) linkedList.getFirst();
                Log.i(TAG, "installSlient2: path = " + str);
                new Thread() {
                    public void run() {
                        super.run();
                        SystemManager.setPermission(str);
                        SystemManager.RootCommand(str);
                        String str = "pm install -r " + str + "\n";
                        File file = new File(str);
                        if (file.exists()) {
                            EventUtils.runCmdAtRootMode(str, 100);
                        }
                        if (!file.exists()) {
                            UsbApkInstallService.this.mHandler.sendEmptyMessageDelayed(UsbApkInstallService.START_COUNT_DOWN, 200);
                            return;
                        }
                        UsbApkInstallService.this.ls.removeFirst();
                        if (UsbApkInstallService.this.ls.size() > 0) {
                            UsbApkInstallService.this.mHandler.sendEmptyMessageDelayed(UsbApkInstallService.START_INSTALL_APK, 100);
                        } else {
                            UsbApkInstallService.this.mHandler.sendEmptyMessageDelayed(UsbApkInstallService.INSTALL_END, 100);
                        }
                    }
                }.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int doType(PackageManager packageManager, String str, int i) {
        for (PackageInfo next : packageManager.getInstalledPackages(8192)) {
            String str2 = next.packageName;
            int i2 = next.versionCode;
            if (str.endsWith(str2)) {
                if (i == i2) {
                    return INSTALLED;
                }
                if (i > i2) {
                    return INSTALLED_UPDATE;
                }
            }
        }
        return UNINSTALLED;
    }

    /* access modifiers changed from: private */
    public void initDialog(final int i) {
        String str;
        TextView textView = new TextView(this);
        this.mOffTextView = textView;
        textView.setTextSize(18.0f);
        this.mOffTextView.setTextColor(-1);
        TextView textView2 = this.mOffTextView;
        int i2 = R.string.lbl_not_detected;
        if (i == 0) {
            str = getString(R.string.lbl_not_detected);
        } else {
            str = getString(R.string.lbl_find) + "\t" + i + "\t" + getString(R.string.lbl_case) + "\t" + 15 + getString(R.string.lbl_auto_install);
        }
        textView2.setText(str);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (i != 0) {
            i2 = R.string.lbl_detected;
        }
        AlertDialog create = builder.setTitle(getString(i2)).setCancelable(false).setView(this.mOffTextView).setPositiveButton(getString(R.string.lbl_enter), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (UsbApkInstallService.this.mOffTime != null) {
                    UsbApkInstallService.this.mOffTime.cancel();
                }
                if (UsbApkInstallService.this.mDialog != null) {
                    UsbApkInstallService.this.mDialog.dismiss();
                }
                if (i != 0) {
                    UsbApkInstallService.this.mHandler.sendEmptyMessage(UsbApkInstallService.START_INSTALL_APK);
                }
            }
        }).setNegativeButton(getString(R.string.lbl_cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                if (UsbApkInstallService.this.mOffTime != null) {
                    UsbApkInstallService.this.mOffTime.cancel();
                }
                if (UsbApkInstallService.this.mDialog != null) {
                    UsbApkInstallService.this.mDialog.dismiss();
                }
            }
        }).create();
        this.mDialog = create;
        create.getWindow().setType(2003);
        this.mDialog.show();
        this.mDialog.setCanceledOnTouchOutside(false);
        if (i != 0) {
            this.mOffHandler = new Handler() {
                public void handleMessage(Message message) {
                    if (message.what > 0) {
                        TextView access$800 = UsbApkInstallService.this.mOffTextView;
                        access$800.setText(UsbApkInstallService.this.getString(R.string.lbl_find) + "\t" + i + "\t" + UsbApkInstallService.this.getString(R.string.lbl_case) + "\t" + message.what + UsbApkInstallService.this.getString(R.string.lbl_auto_install));
                    } else {
                        if (UsbApkInstallService.this.mDialog != null) {
                            UsbApkInstallService.this.mDialog.dismiss();
                        }
                        UsbApkInstallService.this.mOffTime.cancel();
                        UsbApkInstallService.this.mHandler.sendEmptyMessage(UsbApkInstallService.START_INSTALL_APK);
                    }
                    super.handleMessage(message);
                }
            };
            this.mOffTime = new Timer(true);
            this.mOffTime.schedule(new TimerTask() {
                int countTime = 15;

                public void run() {
                    int i = this.countTime;
                    if (i > 0) {
                        this.countTime = i - 1;
                    }
                    Message message = new Message();
                    message.what = this.countTime;
                    UsbApkInstallService.this.mOffHandler.sendMessage(message);
                }
            }, 1000, 1000);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        Log.i("***UsbApkService***", "stop UsbApkInstall");
    }
}
