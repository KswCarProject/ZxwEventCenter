package com.szchoiceway.eventcenter;

import android.content.Context;
import android.os.Looper;
import android.os.storage.StorageManager;
import android.util.Log;
import android.widget.Toast;
import com.example.mylibrary.BuildConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TouchPaneCfg {
    private static final String TAG = "TouchPaneCfg";
    private String TOUCH_PANE_CFG_FILE_PATH = "/storage/usb_storage0/TouchPaneParam.cfg";
    private String TOUCH_PANE_OLD_CFG_FILE_PATH = "/storage/usb_storage/TouchPaneParam.cfg.bak";
    private Context mContext;

    private void loadPath() {
    }

    public TouchPaneCfg(Context context) {
        this.mContext = context;
    }

    private String searchDir(String str) {
        String[] strArr;
        StorageManager storageManager = (StorageManager) this.mContext.getSystemService("storage");
        try {
            Method method = StorageManager.class.getMethod("getVolumePaths", new Class[0]);
            method.setAccessible(true);
            try {
                strArr = (String[]) method.invoke(storageManager, new Object[0]);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                strArr = null;
            }
            for (int i = 0; i < strArr.length; i++) {
                Log.d(TAG, "path----> " + strArr[i] + "\n");
                String str2 = strArr[i] + "/" + str;
                if (new File(str2).exists()) {
                    return str2;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return null;
    }

    public void loadTouchCfgFile(Context context) {
        Log.i("loadTouchCfgFile", "start");
        this.TOUCH_PANE_CFG_FILE_PATH = searchDir("TouchPaneParam.cfg");
        Log.d(TAG, "TOUCH_PANE_CFG_FILE_PATH = " + this.TOUCH_PANE_CFG_FILE_PATH);
        if (this.TOUCH_PANE_CFG_FILE_PATH != null) {
            File file = new File(this.TOUCH_PANE_CFG_FILE_PATH);
            if (file.exists() && file.isFile() && file.canRead()) {
                readTouchPaneCfg(context);
                FileInputStream fileInputStream = null;
                try {
                    FileInputStream fileInputStream2 = new FileInputStream(file);
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream2));
                        writeTouchPaneCfg(context, bufferedReader.readLine());
                        bufferedReader.close();
                        fileInputStream2.close();
                    } catch (IOException e) {
                        e = e;
                        fileInputStream = fileInputStream2;
                    }
                } catch (IOException e2) {
                    e = e2;
                    e.printStackTrace();
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
            }
        }
    }

    public void writeTouchPaneCfg(Context context, String str) {
        String replace = str.replace("0x", BuildConfig.FLAVOR).replace(",", BuildConfig.FLAVOR);
        Log.i("writeTouchPaneCfg", "strCfgData len = " + (replace.length() / 2));
        if (replace.length() / 2 != 186) {
            toast(context, "无效的TouchPaneParam.cfg文件！！");
            return;
        }
        byte[] hexStringToBytes = hexStringToBytes(replace);
        if (hexStringToBytes != null) {
            hexStringToBytes[0] = 0;
            byte b = 0;
            for (int i = 0; i < hexStringToBytes.length - 2; i++) {
                b = (byte) (b + hexStringToBytes[i]);
            }
            hexStringToBytes[hexStringToBytes.length - 2] = (byte) (((b ^ 255) + 1) & 255);
            hexStringToBytes[hexStringToBytes.length - 1] = 1;
            Log.i("writeScreenTouchCfg", bytesToHexStringEx(hexStringToBytes, ","));
            File file = new File("/proc/gt9xx_config");
            FileOutputStream fileOutputStream = null;
            if (!file.exists() || !file.canWrite()) {
                Log.i("writeTouchPaneCfg", "path [/proc/gt9xx_config] error.");
                toast(context, "没找到路径[/proc/gt9xx_config]，请先更新OS！！");
                return;
            }
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                try {
                    fileOutputStream2.write(hexStringToBytes);
                    Log.i("writeTouchPaneCfg", "write touch param success.");
                    toast(context, "写触摸屏参数成功");
                    fileOutputStream2.close();
                } catch (IOException e) {
                    e = e;
                    fileOutputStream = fileOutputStream2;
                }
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
                Log.i("writeTouchPaneCfg", "write touch param fail.");
                toast(context, "写触摸屏参数失败");
                try {
                    fileOutputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0055 A[SYNTHETIC, Splitter:B:20:0x0055] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00ba  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void readTouchPaneCfg(android.content.Context r7) {
        /*
            r6 = this;
            java.lang.String r0 = ""
            java.io.File r1 = new java.io.File
            java.lang.String r2 = "/proc/gt9xx_config"
            r1.<init>(r2)
            boolean r2 = r1.exists()
            java.lang.String r3 = "readTouchPaneCfg"
            if (r2 == 0) goto L_0x00c0
            boolean r2 = r1.isFile()
            if (r2 == 0) goto L_0x00c0
            boolean r2 = r1.canRead()
            if (r2 == 0) goto L_0x00c0
            r7 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x004d }
            r2.<init>(r1)     // Catch:{ IOException -> 0x004d }
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x004d }
            r1.<init>(r2)     // Catch:{ IOException -> 0x004d }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ IOException -> 0x004d }
            r2.<init>(r1)     // Catch:{ IOException -> 0x004d }
            java.lang.String r1 = r2.readLine()     // Catch:{ IOException -> 0x004b }
            r2.close()     // Catch:{ IOException -> 0x0049 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0049 }
            r4.<init>()     // Catch:{ IOException -> 0x0049 }
            java.lang.String r5 = "oldCfgData = "
            r4.append(r5)     // Catch:{ IOException -> 0x0049 }
            r4.append(r1)     // Catch:{ IOException -> 0x0049 }
            java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x0049 }
            android.util.Log.i(r3, r4)     // Catch:{ IOException -> 0x0049 }
            goto L_0x0062
        L_0x0049:
            r4 = move-exception
            goto L_0x0050
        L_0x004b:
            r4 = move-exception
            goto L_0x004f
        L_0x004d:
            r4 = move-exception
            r2 = r7
        L_0x004f:
            r1 = r0
        L_0x0050:
            r4.printStackTrace()
            if (r2 == 0) goto L_0x005d
            r2.close()     // Catch:{ IOException -> 0x0059 }
            goto L_0x005d
        L_0x0059:
            r2 = move-exception
            r2.printStackTrace()
        L_0x005d:
            java.lang.String r2 = "read touch pane cfg error "
            android.util.Log.i(r3, r2)
        L_0x0062:
            if (r1 == 0) goto L_0x00ba
            int r2 = r1.length()
            if (r2 <= 0) goto L_0x00ba
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r0 = r6.searchDir(r0)
            r2.append(r0)
            java.lang.String r0 = "TouchPaneParam.cfg.bak"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r6.TOUCH_PANE_OLD_CFG_FILE_PATH = r0
            java.io.File r0 = new java.io.File
            java.lang.String r2 = r6.TOUCH_PANE_OLD_CFG_FILE_PATH
            r0.<init>(r2)
            java.io.BufferedWriter r2 = new java.io.BufferedWriter     // Catch:{ IOException -> 0x00a6 }
            java.io.FileWriter r4 = new java.io.FileWriter     // Catch:{ IOException -> 0x00a6 }
            r4.<init>(r0)     // Catch:{ IOException -> 0x00a6 }
            r2.<init>(r4)     // Catch:{ IOException -> 0x00a6 }
            r2.write(r1)     // Catch:{ IOException -> 0x00a3 }
            java.lang.String r0 = "\n"
            r2.append(r0)     // Catch:{ IOException -> 0x00a3 }
            r2.close()     // Catch:{ IOException -> 0x00a3 }
            java.lang.String r0 = "sava old cfg success."
            android.util.Log.i(r3, r0)     // Catch:{ IOException -> 0x00a6 }
            goto L_0x00ca
        L_0x00a3:
            r0 = move-exception
            r7 = r2
            goto L_0x00a7
        L_0x00a6:
            r0 = move-exception
        L_0x00a7:
            r0.printStackTrace()
            if (r7 == 0) goto L_0x00b4
            r7.close()     // Catch:{ IOException -> 0x00b0 }
            goto L_0x00b4
        L_0x00b0:
            r7 = move-exception
            r7.printStackTrace()
        L_0x00b4:
            java.lang.String r7 = "sava old cfg fail."
            android.util.Log.i(r3, r7)
            goto L_0x00ca
        L_0x00ba:
            java.lang.String r7 = "oldCfgData == 0"
            android.util.Log.i(r3, r7)
            goto L_0x00ca
        L_0x00c0:
            java.lang.String r0 = "path [/proc/gt9xx_config] error."
            android.util.Log.i(r3, r0)
            java.lang.String r0 = "没找到路径[/proc/gt9xx_config]，请先更新OS！！"
            r6.toast(r7, r0)
        L_0x00ca:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.eventcenter.TouchPaneCfg.readTouchPaneCfg(android.content.Context):void");
    }

    private byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    private byte[] hexStringToBytes(String str) {
        if (str == null || str.equals(BuildConfig.FLAVOR)) {
            return null;
        }
        String upperCase = str.toUpperCase();
        int length = upperCase.length() / 2;
        char[] charArray = upperCase.toCharArray();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (charToByte(charArray[i2 + 1]) | (charToByte(charArray[i2]) << 4));
        }
        return bArr;
    }

    private String bytesToHexStringEx(byte[] bArr, String str) {
        StringBuilder sb = new StringBuilder(BuildConfig.FLAVOR);
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            sb.append("0x");
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString + str);
        }
        return sb.toString();
    }

    private void toast(Context context, String str) {
        Looper.prepare();
        Toast.makeText(context, str, 1).show();
        Looper.loop();
    }
}
