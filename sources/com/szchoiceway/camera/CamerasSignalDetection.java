package com.szchoiceway.camera;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.example.mylibrary.BuildConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CamerasSignalDetection {
    private static String BCH_PATH = "/sys/devices/platform/vehicle/bch";
    private static String BCH_PATH_8836 = "/sys/devices/platform/vehicle/tw8836_bch";
    public static final int CAMERA_720P_SIGNAL = 3;
    public static String CAMERA_CHANNEL_AUX = "6";
    public static String CAMERA_CHANNEL_BACKCAR = "0";
    public static String CAMERA_CHANNEL_CMMB = "2";
    public static String CAMERA_CHANNEL_DVD = "1";
    public static String CAMERA_CHANNEL_DVR = "5";
    public static String CAMERA_CHANNEL_FRONT_SIGHT = "4";
    public static String CAMERA_CHANNEL_MP5 = "7";
    public static String CAMERA_CHANNEL_RIGHT_SIGHT = "3";
    public static String CAMERA_CHANNEL_TV = "2";
    public static final int CAMERA_NOT_AVAILABLE = 0;
    public static final int CAMERA_NTSC_FORMAT = 2;
    public static final int CAMERA_PAL_FORMAT = 1;
    private static final int MSG_CHECK_FILE = 0;
    private static final int MSG_CHECK_SIGNAL = 1;
    public static final String RN6752_PATH = "/sys/devices/platform/vehicle/rn6752_mode";
    private static final String TAG = "CamerasSignalDetection";
    /* access modifiers changed from: private */
    public static boolean isRN6752 = false;
    private static final String mFileDir = "/sys/video_state/";
    /* access modifiers changed from: private */
    public static String mFileName;
    boolean isCheckEnabled = false;
    private String mCameraChannel = CAMERA_CHANNEL_BACKCAR;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 0) {
                if (i == 1 && CamerasSignalDetection.this.isCheckEnabled) {
                    int access$100 = CamerasSignalDetection.this.getCameraStatus();
                    if (CamerasSignalDetection.this.mStatus != access$100) {
                        int unused = CamerasSignalDetection.this.mStatus = access$100;
                        Log.d(CamerasSignalDetection.TAG, "mStatus: " + CamerasSignalDetection.this.mStatus);
                    }
                    CamerasSignalDetection.this.mStatusListener.OnCameraStatusChanged(CamerasSignalDetection.this.mStatus);
                    CamerasSignalDetection.this.mHandler.sendEmptyMessageDelayed(1, CamerasSignalDetection.isRN6752 ? 100 : 500);
                }
            } else if (!Build.MODEL.equals("rk3399")) {
                String unused2 = CamerasSignalDetection.mFileName = CamerasSignalDetection.this.getFileName();
                if (CamerasSignalDetection.mFileName == null) {
                    sendEmptyMessageDelayed(0, 1000);
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public int mStatus = 0;
    /* access modifiers changed from: private */
    public OnCameraStatusChangedListener mStatusListener;

    public interface OnCameraStatusChangedListener {
        void OnCameraStatusChanged(int i);
    }

    public CamerasSignalDetection(OnCameraStatusChangedListener onCameraStatusChangedListener, boolean z, boolean z2) {
        this.mStatusListener = onCameraStatusChangedListener;
        if (!z) {
            CAMERA_CHANNEL_DVD = "2";
        } else if (!z2) {
            CAMERA_CHANNEL_BACKCAR = "1";
        }
        getCameraStatusRk3399();
    }

    public CamerasSignalDetection(OnCameraStatusChangedListener onCameraStatusChangedListener, boolean z) {
        this.mStatusListener = onCameraStatusChangedListener;
        getCameraStatusRk3399();
    }

    public String getFileName() {
        File[] listFiles = new File(mFileDir).listFiles();
        if (!(listFiles == null || listFiles.length == 0)) {
            for (File file : listFiles) {
                if (!file.isDirectory()) {
                    return file.getName();
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:23:0x0044=Splitter:B:23:0x0044, B:32:0x0059=Splitter:B:32:0x0059} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getCameraStatus() {
        /*
            r7 = this;
            java.lang.String r0 = android.os.Build.MODEL
            java.lang.String r1 = "rk3399"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x000f
            int r0 = r7.getCameraStatusRk3399()
            return r0
        L_0x000f:
            java.lang.String r0 = mFileName
            r1 = 0
            if (r0 != 0) goto L_0x0015
            return r1
        L_0x0015:
            java.io.File r0 = new java.io.File
            java.lang.String r2 = mFileName
            java.lang.String r3 = "/sys/video_state/"
            r0.<init>(r3, r2)
            r2 = 10
            byte[] r2 = new byte[r2]
            r2[r1] = r1
            r3 = 1000(0x3e8, double:4.94E-321)
            r5 = 0
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0057, IOException -> 0x0042, all -> 0x0040 }
            r6.<init>(r0)     // Catch:{ FileNotFoundException -> 0x0057, IOException -> 0x0042, all -> 0x0040 }
            r6.read(r2)     // Catch:{ FileNotFoundException -> 0x003e, IOException -> 0x003c }
            r6.close()     // Catch:{ IOException -> 0x0033 }
            goto L_0x0037
        L_0x0033:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0037:
            byte r0 = r2[r1]
            int r0 = r0 + -48
            return r0
        L_0x003c:
            r0 = move-exception
            goto L_0x0044
        L_0x003e:
            r0 = move-exception
            goto L_0x0059
        L_0x0040:
            r0 = move-exception
            goto L_0x006e
        L_0x0042:
            r0 = move-exception
            r6 = r5
        L_0x0044:
            r0.printStackTrace()     // Catch:{ all -> 0x006c }
            mFileName = r5     // Catch:{ all -> 0x006c }
            android.os.Handler r0 = r7.mHandler     // Catch:{ all -> 0x006c }
            r0.sendEmptyMessageDelayed(r1, r3)     // Catch:{ all -> 0x006c }
            r6.close()     // Catch:{ IOException -> 0x0052 }
            goto L_0x0056
        L_0x0052:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0056:
            return r1
        L_0x0057:
            r0 = move-exception
            r6 = r5
        L_0x0059:
            r0.printStackTrace()     // Catch:{ all -> 0x006c }
            mFileName = r5     // Catch:{ all -> 0x006c }
            android.os.Handler r0 = r7.mHandler     // Catch:{ all -> 0x006c }
            r0.sendEmptyMessageDelayed(r1, r3)     // Catch:{ all -> 0x006c }
            r6.close()     // Catch:{ IOException -> 0x0067 }
            goto L_0x006b
        L_0x0067:
            r0 = move-exception
            r0.printStackTrace()
        L_0x006b:
            return r1
        L_0x006c:
            r0 = move-exception
            r5 = r6
        L_0x006e:
            r5.close()     // Catch:{ IOException -> 0x0072 }
            goto L_0x0076
        L_0x0072:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0076:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.camera.CamerasSignalDetection.getCameraStatus():int");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: java.io.BufferedReader} */
    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v1, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r3v2, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* JADX WARNING: type inference failed for: r3v7 */
    /* JADX WARNING: type inference failed for: r3v11 */
    /* JADX WARNING: type inference failed for: r3v12 */
    /* JADX WARNING: type inference failed for: r3v14, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r3v15 */
    /* JADX WARNING: type inference failed for: r2v13 */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00b3, code lost:
        r3 = r2;
        r4 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00b7, code lost:
        r3 = r2;
        r4 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x00d7, code lost:
        r3 = th;
        r2 = r2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x00f1 A[SYNTHETIC, Splitter:B:100:0x00f1] */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x00fb A[SYNTHETIC, Splitter:B:105:0x00fb] */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0105 A[SYNTHETIC, Splitter:B:110:0x0105] */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x0112 A[SYNTHETIC, Splitter:B:119:0x0112] */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x011c A[SYNTHETIC, Splitter:B:124:0x011c] */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x0126 A[SYNTHETIC, Splitter:B:129:0x0126] */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x012e A[SYNTHETIC, Splitter:B:135:0x012e] */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x0138 A[SYNTHETIC, Splitter:B:140:0x0138] */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x0142 A[SYNTHETIC, Splitter:B:145:0x0142] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0039 A[SYNTHETIC, Splitter:B:21:0x0039] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x003f A[SYNTHETIC, Splitter:B:25:0x003f] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x00d7 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:37:0x005e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int getCameraStatusRk3399() {
        /*
            r13 = this;
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "/sys/devices/platform/vehicle/rn6752_mode"
            r0.<init>(r1)
            r1 = 10
            byte[] r1 = new byte[r1]
            boolean r2 = r0.exists()
            r3 = 0
            r4 = 0
            r5 = 1
            if (r2 == 0) goto L_0x0048
            isRN6752 = r5
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0033 }
            r2.<init>(r0)     // Catch:{ IOException -> 0x0033 }
            r2.read(r1)     // Catch:{ IOException -> 0x002e, all -> 0x002b }
            byte r0 = r1[r4]     // Catch:{ IOException -> 0x002e, all -> 0x002b }
            int r4 = r0 + -48
            r2.close()     // Catch:{ IOException -> 0x0026 }
            goto L_0x003c
        L_0x0026:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x003c
        L_0x002b:
            r0 = move-exception
            r3 = r2
            goto L_0x003d
        L_0x002e:
            r0 = move-exception
            r3 = r2
            goto L_0x0034
        L_0x0031:
            r0 = move-exception
            goto L_0x003d
        L_0x0033:
            r0 = move-exception
        L_0x0034:
            r0.printStackTrace()     // Catch:{ all -> 0x0031 }
            if (r3 == 0) goto L_0x003c
            r3.close()     // Catch:{ IOException -> 0x0026 }
        L_0x003c:
            return r4
        L_0x003d:
            if (r3 == 0) goto L_0x0047
            r3.close()     // Catch:{ IOException -> 0x0043 }
            goto L_0x0047
        L_0x0043:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0047:
            throw r0
        L_0x0048:
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "/dev/vehicle"
            r0.<init>(r1)
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x012a, IOException -> 0x010e, all -> 0x00ea }
            r1.<init>(r0)     // Catch:{ FileNotFoundException -> 0x012a, IOException -> 0x010e, all -> 0x00ea }
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch:{ FileNotFoundException -> 0x00e8, IOException -> 0x00e6, all -> 0x00e3 }
            r0.<init>(r1)     // Catch:{ FileNotFoundException -> 0x00e8, IOException -> 0x00e6, all -> 0x00e3 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ FileNotFoundException -> 0x012c, IOException -> 0x0110, all -> 0x00de }
            r2.<init>(r0)     // Catch:{ FileNotFoundException -> 0x012c, IOException -> 0x0110, all -> 0x00de }
            java.lang.String r3 = r2.readLine()     // Catch:{ FileNotFoundException -> 0x00db, IOException -> 0x00d9, all -> 0x00d7 }
            if (r3 == 0) goto L_0x00bc
            int r6 = r3.length()     // Catch:{ FileNotFoundException -> 0x00db, IOException -> 0x00d9, all -> 0x00d7 }
            if (r6 <= 0) goto L_0x00bc
            java.lang.String r6 = ","
            java.lang.String[] r3 = r3.split(r6)     // Catch:{ FileNotFoundException -> 0x00db, IOException -> 0x00d9, all -> 0x00d7 }
            if (r3 == 0) goto L_0x00bc
            int r6 = r3.length     // Catch:{ FileNotFoundException -> 0x00db, IOException -> 0x00d9, all -> 0x00d7 }
            if (r6 <= 0) goto L_0x00bc
            int r6 = r3.length     // Catch:{ FileNotFoundException -> 0x00db, IOException -> 0x00d9, all -> 0x00d7 }
            r7 = 0
            r8 = 0
        L_0x0078:
            if (r7 >= r6) goto L_0x00bb
            r9 = r3[r7]     // Catch:{ FileNotFoundException -> 0x00b7, IOException -> 0x00b3, all -> 0x00d7 }
            if (r9 == 0) goto L_0x00b0
            int r10 = r9.length()     // Catch:{ FileNotFoundException -> 0x00b7, IOException -> 0x00b3, all -> 0x00d7 }
            if (r10 <= 0) goto L_0x00b0
            java.lang.String r10 = "="
            java.lang.String[] r9 = r9.split(r10)     // Catch:{ FileNotFoundException -> 0x00b7, IOException -> 0x00b3, all -> 0x00d7 }
            if (r9 == 0) goto L_0x00b0
            int r10 = r9.length     // Catch:{ FileNotFoundException -> 0x00b7, IOException -> 0x00b3, all -> 0x00d7 }
            r11 = 2
            if (r10 != r11) goto L_0x00b0
            r10 = r9[r4]     // Catch:{ FileNotFoundException -> 0x00b7, IOException -> 0x00b3, all -> 0x00d7 }
            java.lang.String r11 = "video_signal"
            boolean r10 = r10.equals(r11)     // Catch:{ FileNotFoundException -> 0x00b7, IOException -> 0x00b3, all -> 0x00d7 }
            if (r10 == 0) goto L_0x00b0
            r10 = r9[r5]     // Catch:{ FileNotFoundException -> 0x00b7, IOException -> 0x00b3, all -> 0x00d7 }
            if (r10 == 0) goto L_0x00b0
            r10 = r9[r5]     // Catch:{ FileNotFoundException -> 0x00b7, IOException -> 0x00b3, all -> 0x00d7 }
            int r10 = r10.length()     // Catch:{ FileNotFoundException -> 0x00b7, IOException -> 0x00b3, all -> 0x00d7 }
            if (r10 <= 0) goto L_0x00b0
            r9 = r9[r5]     // Catch:{ FileNotFoundException -> 0x00b7, IOException -> 0x00b3, all -> 0x00d7 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ FileNotFoundException -> 0x00b7, IOException -> 0x00b3, all -> 0x00d7 }
            int r8 = r9.intValue()     // Catch:{ FileNotFoundException -> 0x00b7, IOException -> 0x00b3, all -> 0x00d7 }
        L_0x00b0:
            int r7 = r7 + 1
            goto L_0x0078
        L_0x00b3:
            r3 = r2
            r4 = r8
            goto L_0x0110
        L_0x00b7:
            r3 = r2
            r4 = r8
            goto L_0x012c
        L_0x00bb:
            r4 = r8
        L_0x00bc:
            r2.close()     // Catch:{ IOException -> 0x00c0 }
            goto L_0x00c4
        L_0x00c0:
            r2 = move-exception
            r2.printStackTrace()
        L_0x00c4:
            r0.close()     // Catch:{ IOException -> 0x00c8 }
            goto L_0x00cc
        L_0x00c8:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00cc:
            r1.close()     // Catch:{ IOException -> 0x00d1 }
            goto L_0x0145
        L_0x00d1:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0145
        L_0x00d7:
            r3 = move-exception
            goto L_0x00ef
        L_0x00d9:
            r3 = r2
            goto L_0x0110
        L_0x00db:
            r3 = r2
            goto L_0x012c
        L_0x00de:
            r2 = move-exception
            r12 = r3
            r3 = r2
            r2 = r12
            goto L_0x00ef
        L_0x00e3:
            r0 = move-exception
            r2 = r3
            goto L_0x00ed
        L_0x00e6:
            r0 = r3
            goto L_0x0110
        L_0x00e8:
            r0 = r3
            goto L_0x012c
        L_0x00ea:
            r0 = move-exception
            r1 = r3
            r2 = r1
        L_0x00ed:
            r3 = r0
            r0 = r2
        L_0x00ef:
            if (r2 == 0) goto L_0x00f9
            r2.close()     // Catch:{ IOException -> 0x00f5 }
            goto L_0x00f9
        L_0x00f5:
            r2 = move-exception
            r2.printStackTrace()
        L_0x00f9:
            if (r0 == 0) goto L_0x0103
            r0.close()     // Catch:{ IOException -> 0x00ff }
            goto L_0x0103
        L_0x00ff:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0103:
            if (r1 == 0) goto L_0x010d
            r1.close()     // Catch:{ IOException -> 0x0109 }
            goto L_0x010d
        L_0x0109:
            r0 = move-exception
            r0.printStackTrace()
        L_0x010d:
            throw r3
        L_0x010e:
            r0 = r3
            r1 = r0
        L_0x0110:
            if (r3 == 0) goto L_0x011a
            r3.close()     // Catch:{ IOException -> 0x0116 }
            goto L_0x011a
        L_0x0116:
            r2 = move-exception
            r2.printStackTrace()
        L_0x011a:
            if (r0 == 0) goto L_0x0124
            r0.close()     // Catch:{ IOException -> 0x0120 }
            goto L_0x0124
        L_0x0120:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0124:
            if (r1 == 0) goto L_0x0145
            r1.close()     // Catch:{ IOException -> 0x00d1 }
            goto L_0x0145
        L_0x012a:
            r0 = r3
            r1 = r0
        L_0x012c:
            if (r3 == 0) goto L_0x0136
            r3.close()     // Catch:{ IOException -> 0x0132 }
            goto L_0x0136
        L_0x0132:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0136:
            if (r0 == 0) goto L_0x0140
            r0.close()     // Catch:{ IOException -> 0x013c }
            goto L_0x0140
        L_0x013c:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0140:
            if (r1 == 0) goto L_0x0145
            r1.close()     // Catch:{ IOException -> 0x00d1 }
        L_0x0145:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.camera.CamerasSignalDetection.getCameraStatusRk3399():int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0047 A[SYNTHETIC, Splitter:B:22:0x0047] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0051 A[SYNTHETIC, Splitter:B:28:0x0051] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x005c A[SYNTHETIC, Splitter:B:33:0x005c] */
    /* JADX WARNING: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:19:0x0042=Splitter:B:19:0x0042, B:25:0x004c=Splitter:B:25:0x004c} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void setRN6752Mode(int r3) {
        /*
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "/sys/devices/platform/vehicle/rn6752_mode"
            r0.<init>(r1)
            boolean r1 = r0.exists()
            if (r1 != 0) goto L_0x000e
            return
        L_0x000e:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "setRN6752Mode "
            r1.append(r2)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "CamerasSignalDetection"
            android.util.Log.d(r2, r1)
            r1 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x004b, IOException -> 0x0041 }
            r2.<init>(r0)     // Catch:{ FileNotFoundException -> 0x004b, IOException -> 0x0041 }
            int r3 = r3 + 48
            r2.write(r3)     // Catch:{ FileNotFoundException -> 0x003c, IOException -> 0x0039, all -> 0x0036 }
            r2.close()     // Catch:{ FileNotFoundException -> 0x003c, IOException -> 0x0039, all -> 0x0036 }
            r2.close()     // Catch:{ IOException -> 0x0055 }
            goto L_0x0059
        L_0x0036:
            r3 = move-exception
            r1 = r2
            goto L_0x005a
        L_0x0039:
            r3 = move-exception
            r1 = r2
            goto L_0x0042
        L_0x003c:
            r3 = move-exception
            r1 = r2
            goto L_0x004c
        L_0x003f:
            r3 = move-exception
            goto L_0x005a
        L_0x0041:
            r3 = move-exception
        L_0x0042:
            r3.printStackTrace()     // Catch:{ all -> 0x003f }
            if (r1 == 0) goto L_0x0059
            r1.close()     // Catch:{ IOException -> 0x0055 }
            goto L_0x0059
        L_0x004b:
            r3 = move-exception
        L_0x004c:
            r3.printStackTrace()     // Catch:{ all -> 0x003f }
            if (r1 == 0) goto L_0x0059
            r1.close()     // Catch:{ IOException -> 0x0055 }
            goto L_0x0059
        L_0x0055:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0059:
            return
        L_0x005a:
            if (r1 == 0) goto L_0x0064
            r1.close()     // Catch:{ IOException -> 0x0060 }
            goto L_0x0064
        L_0x0060:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0064:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.camera.CamerasSignalDetection.setRN6752Mode(int):void");
    }

    public static final boolean isRN6752() {
        return isRN6752;
    }

    public void startDetection() {
        this.isCheckEnabled = true;
        this.mStatus = 0;
        this.mHandler.sendEmptyMessage(1);
        Log.d(TAG, "startDetection");
    }

    public void stopDetection() {
        this.isCheckEnabled = false;
        this.mStatus = 0;
        this.mHandler.removeMessages(1);
    }

    public void pauseDetectionForWhile(int i) {
        this.mHandler.removeMessages(1);
        this.mStatus = 0;
        this.mStatusListener.OnCameraStatusChanged(0);
        this.mHandler.sendEmptyMessageDelayed(1, (long) i);
    }

    public boolean setCameraChannel(String str) {
        if (mFileName == null) {
            return false;
        }
        try {
            new FileOutputStream(new File(mFileDir, mFileName)).write(str.getBytes());
            this.mCameraChannel = str;
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static void SetCon_bri_hue(long j) {
        try {
            File file = new File(BCH_PATH_8836);
            if (!file.exists()) {
                file = new File(BCH_PATH);
                if (!file.exists()) {
                    return;
                }
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            String str = BuildConfig.FLAVOR + j + "\u0000";
            Log.i(TAG, "SetCon_bri_hue str " + str);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            Log.e(TAG, "SetCon_bri_hue error " + e.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static long getBrightnessContrassHue() {
        try {
            File file = new File(BCH_PATH_8836);
            if (!file.exists()) {
                file = new File(BCH_PATH);
                if (!file.exists()) {
                    return 0;
                }
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[100];
            int read = fileInputStream.read(bArr);
            fileInputStream.close();
            int i = read - 1;
            byte[] bArr2 = new byte[i];
            System.arraycopy(bArr, 0, bArr2, 0, i);
            return Long.parseLong(new String(bArr2).replace("0x", BuildConfig.FLAVOR), 16);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }
}
