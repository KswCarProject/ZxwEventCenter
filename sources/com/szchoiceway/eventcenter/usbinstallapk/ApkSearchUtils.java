package com.szchoiceway.eventcenter.usbinstallapk;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mylibrary.BuildConfig;
import com.szchoiceway.eventcenter.EventUtils;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class ApkSearchUtils {
    private static int INSTALLED = 0;
    private static final int INSTALLED_PROGRESS = 0;
    private static final int INSTALLED_STARTING = 1;
    private static int INSTALLED_UPDATE = 2;
    private static int UNINSTALLED = 1;
    private static final int UPDATE_PROGRESS = 2;
    private int Apk_Count = 0;
    /* access modifiers changed from: private */
    public Context context;
    private int fileCounts = 0;
    private int file_in = 0;
    private int file_progress = 0;
    /* access modifiers changed from: private */
    public AlertDialog mDialog;
    /* access modifiers changed from: private */
    public ProgressDialog mInstallBar;
    /* access modifiers changed from: private */
    public Handler mOffHandler;
    /* access modifiers changed from: private */
    public TextView mOffTextView;
    /* access modifiers changed from: private */
    public Timer mOffTime;
    /* access modifiers changed from: private */
    public Timer mOffTime2;
    private int m_Count = 0;
    /* access modifiers changed from: private */
    public Handler mhandle = new Handler() {
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                int i2 = message.arg1;
                if (ApkSearchUtils.this.mInstallBar != null) {
                    ApkSearchUtils.this.mInstallBar.setProgress(message.arg1);
                }
                if (i2 == 100) {
                    ApkSearchUtils.this.mInstallBar.dismiss();
                    Toast.makeText(ApkSearchUtils.this.context, "  100%  安装完成  ", 1).show();
                }
                Log.i("upgrade_progess", message.arg1 + "%");
            } else if (i == 1) {
                ApkSearchUtils apkSearchUtils = ApkSearchUtils.this;
                apkSearchUtils.initInstallDialog(apkSearchUtils.newfile);
            } else if (i == 2) {
                if (message.arg1 < 1) {
                    ApkSearchUtils.this.mOffTime2.cancel();
                    ApkSearchUtils apkSearchUtils2 = ApkSearchUtils.this;
                    apkSearchUtils2.FindandInstallAPKFile(apkSearchUtils2.newfile);
                } else if (message.arg1 >= 1) {
                    ApkSearchUtils.this.mhandle.sendMessage(ApkSearchUtils.this.mhandle.obtainMessage(0, ((5 - message.arg1) * 13) + message.arg1, 0));
                }
            }
            super.handleMessage(message);
        }
    };
    /* access modifiers changed from: private */
    public File newfile = new File("/mnt/usb_storage/");
    String sfile = BuildConfig.FLAVOR;

    public ApkSearchUtils() {
    }

    public ApkSearchUtils(Context context2) {
        this.context = context2;
        if (this.newfile.list() == null) {
            this.newfile = new File("/mnt/usb_storage1/");
        }
    }

    public void FindAllAPKFile(File file) {
        if (file.exists() && file.isFile()) {
            this.fileCounts++;
            if (file.getName().toLowerCase().endsWith(".apk")) {
                String absolutePath = file.getAbsolutePath();
                PackageManager packageManager = this.context.getPackageManager();
                PackageInfo packageArchiveInfo = packageManager.getPackageArchiveInfo(absolutePath, 1);
                ApplicationInfo applicationInfo = packageArchiveInfo.applicationInfo;
                this.sfile = file.getAbsolutePath();
                applicationInfo.sourceDir = absolutePath;
                applicationInfo.publicSourceDir = absolutePath;
                applicationInfo.loadIcon(packageManager);
                String str = packageArchiveInfo.packageName;
                String str2 = packageArchiveInfo.versionName;
                int doType = doType(packageManager, str, packageArchiveInfo.versionCode);
                if (doType == UNINSTALLED) {
                    this.Apk_Count++;
                    Log.i("test", "未安装该应用，可以安装  " + this.Apk_Count + this.sfile);
                    if (this.Apk_Count == 1) {
                        initDialog();
                    }
                }
                Log.i("ok", "处理类型:" + String.valueOf(doType) + "\n------------------我是纯洁的分割线-------------------");
            }
        }
    }

    public void FindandInstallAPKFile(File file) {
        if (file.isFile()) {
            int i = this.file_in + 1;
            this.file_in = i;
            int i2 = (int) (((((float) i) * 1.0f) / (((float) this.fileCounts) * 1.0f)) * 100.0f);
            this.file_progress = i2;
            if (i2 > 70) {
                this.mhandle.sendMessageDelayed(this.mhandle.obtainMessage(0, i2, 0), 1000);
            }
            if (file.getName().toLowerCase().endsWith(".apk")) {
                String absolutePath = file.getAbsolutePath();
                PackageManager packageManager = this.context.getPackageManager();
                PackageInfo packageArchiveInfo = packageManager.getPackageArchiveInfo(absolutePath, 1);
                ApplicationInfo applicationInfo = packageArchiveInfo.applicationInfo;
                this.sfile = file.getAbsolutePath();
                applicationInfo.sourceDir = absolutePath;
                applicationInfo.publicSourceDir = absolutePath;
                applicationInfo.loadIcon(packageManager);
                int doType = doType(packageManager, packageArchiveInfo.packageName, packageArchiveInfo.versionCode);
                if (doType == UNINSTALLED) {
                    this.m_Count++;
                    Log.i("test", "未安装该应用，可以安装  " + this.Apk_Count);
                    installSlient2(this.sfile);
                }
                Log.i("ok", "处理类型:" + String.valueOf(doType) + "\n------------------我是纯洁的分割线-------------------");
                return;
            }
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length > 0) {
            for (File FindandInstallAPKFile : listFiles) {
                FindandInstallAPKFile(FindandInstallAPKFile);
            }
        }
    }

    private int doType(PackageManager packageManager, String str, int i) {
        for (PackageInfo next : packageManager.getInstalledPackages(8192)) {
            String str2 = next.packageName;
            int i2 = next.versionCode;
            if (str.endsWith(str2)) {
                if (i == i2) {
                    Log.i("test", "已经安装，不用更新，可以卸载该应用" + this.sfile);
                    return INSTALLED;
                } else if (i > i2) {
                    Log.i("test", "已经安装，有更新");
                    return INSTALLED_UPDATE;
                }
            }
        }
        return UNINSTALLED;
    }

    private void initDialog() {
        TextView textView = new TextView(this.context);
        this.mOffTextView = textView;
        textView.setTextSize(18.0f);
        this.mOffTextView.setTextColor(-1);
        this.mOffTextView.setText("\t\t15s 后自动安装!");
        AlertDialog create = new AlertDialog.Builder(this.context).setTitle("检测到可安装程序！").setCancelable(false).setView(this.mOffTextView).setPositiveButton("安装", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ApkSearchUtils.this.mOffTime.cancel();
                ApkSearchUtils.this.mDialog.dismiss();
                ApkSearchUtils.this.mhandle.sendEmptyMessage(1);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                ApkSearchUtils.this.mOffTime.cancel();
                ApkSearchUtils.this.mDialog.dismiss();
            }
        }).create();
        this.mDialog = create;
        create.getWindow().setType(2003);
        this.mDialog.show();
        this.mDialog.setCanceledOnTouchOutside(false);
        this.mOffHandler = new Handler() {
            public void handleMessage(Message message) {
                if (message.what > 0) {
                    TextView access$300 = ApkSearchUtils.this.mOffTextView;
                    access$300.setText("\t\t" + message.what + "s 后自动安装!");
                } else {
                    if (ApkSearchUtils.this.mDialog != null) {
                        ApkSearchUtils.this.mDialog.dismiss();
                    }
                    ApkSearchUtils.this.mOffTime.cancel();
                    ApkSearchUtils.this.mhandle.sendEmptyMessage(1);
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
                ApkSearchUtils.this.mOffHandler.sendMessage(message);
            }
        }, 1000, 1000);
    }

    /* access modifiers changed from: private */
    public void initInstallDialog(File file) {
        ProgressDialog progressDialog = new ProgressDialog(this.context);
        this.mInstallBar = progressDialog;
        progressDialog.setProgressStyle(1);
        this.mInstallBar.setMax(100);
        this.mInstallBar.setProgress(0);
        this.mInstallBar.setMessage("Install...");
        this.mInstallBar.getWindow().setType(2003);
        this.mInstallBar.setCanceledOnTouchOutside(false);
        this.mInstallBar.show();
        Log.i("initInstallDialog", "show");
        this.mOffTime2 = new Timer(true);
        this.mOffTime2.schedule(new TimerTask() {
            int countTime = 5;

            public void run() {
                int i = this.countTime;
                if (i > 0) {
                    this.countTime = i - 1;
                }
                Message message = new Message();
                message.what = 2;
                message.arg1 = this.countTime;
                ApkSearchUtils.this.mhandle.sendMessage(message);
            }
        }, 500, 500);
    }

    private void installApk(String str) {
        File file = new File(str);
        if (file.exists()) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setFlags(268435456);
            intent.setDataAndType(Uri.parse("file://" + file.toString()), "application/vnd.android.package-archive");
            this.context.startActivity(intent);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v4, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v9, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.io.DataOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v12, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.io.DataOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: java.io.DataOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v7, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v10, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v11, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v10, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v11, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v12, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v27, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v28, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v29, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v30, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v31, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v32, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v33, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v12, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v13, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v14, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v15, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v16, resolved type: java.lang.StringBuilder} */
    /* JADX WARNING: type inference failed for: r10v17 */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0097, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0098, code lost:
        r5 = null;
        r0 = r4;
        r4 = null;
        r10 = r10;
        r3 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x009c, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x009d, code lost:
        r4 = null;
        r5 = null;
        r0 = r3;
        r3 = null;
        r10 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00a2, code lost:
        r10 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a3, code lost:
        r4 = null;
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00c6, code lost:
        r1.destroy();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00cb, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00d0, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0102, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0106, code lost:
        r1.destroy();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x010b, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0110, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0114, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a2 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:5:0x0025] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00c1 A[SYNTHETIC, Splitter:B:54:0x00c1] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00c6 A[Catch:{ Exception -> 0x0086 }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00cb A[Catch:{ Exception -> 0x0086 }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00d0 A[Catch:{ Exception -> 0x0086 }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00fe A[SYNTHETIC, Splitter:B:67:0x00fe] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0106 A[Catch:{ Exception -> 0x0102 }] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x010b A[Catch:{ Exception -> 0x0102 }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0110 A[Catch:{ Exception -> 0x0102 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void installSlient(java.lang.String r10) {
        /*
            r9 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = " pm install -r "
            r0.append(r1)
            r0.append(r10)
            java.lang.String r10 = r0.toString()
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch:{ Exception -> 0x00b4, all -> 0x00af }
            java.lang.String r2 = "su"
            java.lang.Process r1 = r1.exec(r2)     // Catch:{ Exception -> 0x00b4, all -> 0x00af }
            java.io.DataOutputStream r2 = new java.io.DataOutputStream     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            java.io.OutputStream r3 = r1.getOutputStream()     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            byte[] r10 = r10.getBytes()     // Catch:{ Exception -> 0x00a6, all -> 0x00a2 }
            r2.write(r10)     // Catch:{ Exception -> 0x00a6, all -> 0x00a2 }
            java.lang.String r10 = "\n"
            r2.writeBytes(r10)     // Catch:{ Exception -> 0x00a6, all -> 0x00a2 }
            java.lang.String r10 = "exit\n"
            r2.writeBytes(r10)     // Catch:{ Exception -> 0x00a6, all -> 0x00a2 }
            r2.flush()     // Catch:{ Exception -> 0x00a6, all -> 0x00a2 }
            r1.waitFor()     // Catch:{ Exception -> 0x00a6, all -> 0x00a2 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a6, all -> 0x00a2 }
            r10.<init>()     // Catch:{ Exception -> 0x00a6, all -> 0x00a2 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009c, all -> 0x00a2 }
            r3.<init>()     // Catch:{ Exception -> 0x009c, all -> 0x00a2 }
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0097, all -> 0x00a2 }
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0097, all -> 0x00a2 }
            java.io.InputStream r6 = r1.getInputStream()     // Catch:{ Exception -> 0x0097, all -> 0x00a2 }
            r5.<init>(r6)     // Catch:{ Exception -> 0x0097, all -> 0x00a2 }
            r4.<init>(r5)     // Catch:{ Exception -> 0x0097, all -> 0x00a2 }
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0092, all -> 0x008e }
            java.io.InputStreamReader r6 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0092, all -> 0x008e }
            java.io.InputStream r7 = r1.getErrorStream()     // Catch:{ Exception -> 0x0092, all -> 0x008e }
            r6.<init>(r7)     // Catch:{ Exception -> 0x0092, all -> 0x008e }
            r5.<init>(r6)     // Catch:{ Exception -> 0x0092, all -> 0x008e }
        L_0x0062:
            java.lang.String r0 = r4.readLine()     // Catch:{ Exception -> 0x008c }
            if (r0 == 0) goto L_0x006c
            r10.append(r0)     // Catch:{ Exception -> 0x008c }
            goto L_0x0062
        L_0x006c:
            java.lang.String r0 = r5.readLine()     // Catch:{ Exception -> 0x008c }
            if (r0 == 0) goto L_0x0076
            r3.append(r0)     // Catch:{ Exception -> 0x008c }
            goto L_0x006c
        L_0x0076:
            r2.close()     // Catch:{ Exception -> 0x0086 }
            if (r1 == 0) goto L_0x007e
            r1.destroy()     // Catch:{ Exception -> 0x0086 }
        L_0x007e:
            r4.close()     // Catch:{ Exception -> 0x0086 }
            r5.close()     // Catch:{ Exception -> 0x0086 }
            goto L_0x00d3
        L_0x0086:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00d3
        L_0x008c:
            r0 = move-exception
            goto L_0x00bc
        L_0x008e:
            r10 = move-exception
            r5 = r0
            goto L_0x00fb
        L_0x0092:
            r5 = move-exception
            r8 = r5
            r5 = r0
            r0 = r8
            goto L_0x00bc
        L_0x0097:
            r4 = move-exception
            r5 = r0
            r0 = r4
            r4 = r5
            goto L_0x00bc
        L_0x009c:
            r3 = move-exception
            r4 = r0
            r5 = r4
            r0 = r3
            r3 = r5
            goto L_0x00bc
        L_0x00a2:
            r10 = move-exception
            r4 = r0
            r5 = r4
            goto L_0x00fb
        L_0x00a6:
            r10 = move-exception
            r3 = r0
            goto L_0x00b8
        L_0x00a9:
            r10 = move-exception
            r4 = r0
            goto L_0x00b2
        L_0x00ac:
            r10 = move-exception
            r2 = r0
            goto L_0x00b7
        L_0x00af:
            r10 = move-exception
            r1 = r0
            r4 = r1
        L_0x00b2:
            r5 = r4
            goto L_0x00fc
        L_0x00b4:
            r10 = move-exception
            r1 = r0
            r2 = r1
        L_0x00b7:
            r3 = r2
        L_0x00b8:
            r4 = r3
            r5 = r4
            r0 = r10
            r10 = r5
        L_0x00bc:
            r0.printStackTrace()     // Catch:{ all -> 0x00fa }
            if (r2 == 0) goto L_0x00c4
            r2.close()     // Catch:{ Exception -> 0x0086 }
        L_0x00c4:
            if (r1 == 0) goto L_0x00c9
            r1.destroy()     // Catch:{ Exception -> 0x0086 }
        L_0x00c9:
            if (r4 == 0) goto L_0x00ce
            r4.close()     // Catch:{ Exception -> 0x0086 }
        L_0x00ce:
            if (r5 == 0) goto L_0x00d3
            r5.close()     // Catch:{ Exception -> 0x0086 }
        L_0x00d3:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "安装完成"
            r0.append(r1)
            java.lang.String r10 = r10.toString()
            r0.append(r10)
            java.lang.String r10 = "错误消息: "
            r0.append(r10)
            java.lang.String r10 = r3.toString()
            r0.append(r10)
            java.lang.String r10 = r0.toString()
            java.lang.String r0 = "test"
            android.util.Log.i(r0, r10)
            return
        L_0x00fa:
            r10 = move-exception
        L_0x00fb:
            r0 = r2
        L_0x00fc:
            if (r0 == 0) goto L_0x0104
            r0.close()     // Catch:{ Exception -> 0x0102 }
            goto L_0x0104
        L_0x0102:
            r0 = move-exception
            goto L_0x0114
        L_0x0104:
            if (r1 == 0) goto L_0x0109
            r1.destroy()     // Catch:{ Exception -> 0x0102 }
        L_0x0109:
            if (r4 == 0) goto L_0x010e
            r4.close()     // Catch:{ Exception -> 0x0102 }
        L_0x010e:
            if (r5 == 0) goto L_0x0117
            r5.close()     // Catch:{ Exception -> 0x0102 }
            goto L_0x0117
        L_0x0114:
            r0.printStackTrace()
        L_0x0117:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.eventcenter.usbinstallapk.ApkSearchUtils.installSlient(java.lang.String):void");
    }

    private void installSlient2(String str) {
        EventUtils.runCmdAtRootMode("pm install -r " + str, 500);
        Log.i("test", "安装完成" + this.Apk_Count);
    }
}
