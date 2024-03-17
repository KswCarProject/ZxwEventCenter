package com.szchoiceway.camera;

import android.view.Surface;

public class CameraService {
    public static String CAMERA_6806_CHANNEL_AUX = "0";
    public static String CAMERA_6806_CHANNEL_FRONT_CAM = "3";
    public static String CAMERA_6806_CHANNEL_HDMI_1080P = "9";
    public static String CAMERA_6806_CHANNEL_HDMI_720P = "8";
    public static String CAMERA_6806_CHANNEL_REAR_CAM = "1";
    public static String CAMERA_6806_CHANNEL_RIGHT_CAM = "2";
    private static final String CAMERA_6806_PATH = "/sys/devices/platform/vehicle/tp6806_ch";
    private static final String TAG = "CameraService";

    public static native void setDiscardFrameCount(int i);

    public static native void setSurface(Surface surface);

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x005a A[SYNTHETIC, Splitter:B:32:0x005a] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0064 A[SYNTHETIC, Splitter:B:37:0x0064] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0076 A[SYNTHETIC, Splitter:B:47:0x0076] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0080 A[SYNTHETIC, Splitter:B:52:0x0080] */
    /* JADX WARNING: Removed duplicated region for block: B:57:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void openCamera(java.lang.String r6) {
        /*
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "/dev/vehicle"
            r0.<init>(r1)
            r1 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x006d, all -> 0x0050 }
            r2.<init>(r0)     // Catch:{ FileNotFoundException -> 0x006d, all -> 0x0050 }
            java.io.OutputStreamWriter r0 = new java.io.OutputStreamWriter     // Catch:{ FileNotFoundException -> 0x004e, all -> 0x004b }
            r0.<init>(r2)     // Catch:{ FileNotFoundException -> 0x004e, all -> 0x004b }
            java.io.PrintWriter r3 = new java.io.PrintWriter     // Catch:{ FileNotFoundException -> 0x0049, all -> 0x0047 }
            r3.<init>(r0)     // Catch:{ FileNotFoundException -> 0x0049, all -> 0x0047 }
            java.lang.String r1 = "CameraService"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0045, all -> 0x0042 }
            r4.<init>()     // Catch:{ FileNotFoundException -> 0x0045, all -> 0x0042 }
            java.lang.String r5 = "openCamera = "
            r4.append(r5)     // Catch:{ FileNotFoundException -> 0x0045, all -> 0x0042 }
            r4.append(r6)     // Catch:{ FileNotFoundException -> 0x0045, all -> 0x0042 }
            java.lang.String r4 = r4.toString()     // Catch:{ FileNotFoundException -> 0x0045, all -> 0x0042 }
            android.util.Log.i(r1, r4)     // Catch:{ FileNotFoundException -> 0x0045, all -> 0x0042 }
            r3.write(r6)     // Catch:{ FileNotFoundException -> 0x0045, all -> 0x0042 }
            r3.flush()     // Catch:{ FileNotFoundException -> 0x0045, all -> 0x0042 }
            r3.close()
            r0.close()     // Catch:{ IOException -> 0x003a }
            goto L_0x003e
        L_0x003a:
            r6 = move-exception
            r6.printStackTrace()
        L_0x003e:
            r2.close()     // Catch:{ IOException -> 0x0084 }
            goto L_0x0088
        L_0x0042:
            r6 = move-exception
            r1 = r3
            goto L_0x0053
        L_0x0045:
            r1 = r3
            goto L_0x006f
        L_0x0047:
            r6 = move-exception
            goto L_0x0053
        L_0x0049:
            goto L_0x006f
        L_0x004b:
            r6 = move-exception
            r0 = r1
            goto L_0x0053
        L_0x004e:
            r0 = r1
            goto L_0x006f
        L_0x0050:
            r6 = move-exception
            r0 = r1
            r2 = r0
        L_0x0053:
            if (r1 == 0) goto L_0x0058
            r1.close()
        L_0x0058:
            if (r0 == 0) goto L_0x0062
            r0.close()     // Catch:{ IOException -> 0x005e }
            goto L_0x0062
        L_0x005e:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0062:
            if (r2 == 0) goto L_0x006c
            r2.close()     // Catch:{ IOException -> 0x0068 }
            goto L_0x006c
        L_0x0068:
            r0 = move-exception
            r0.printStackTrace()
        L_0x006c:
            throw r6
        L_0x006d:
            r0 = r1
            r2 = r0
        L_0x006f:
            if (r1 == 0) goto L_0x0074
            r1.close()
        L_0x0074:
            if (r0 == 0) goto L_0x007e
            r0.close()     // Catch:{ IOException -> 0x007a }
            goto L_0x007e
        L_0x007a:
            r6 = move-exception
            r6.printStackTrace()
        L_0x007e:
            if (r2 == 0) goto L_0x0088
            r2.close()     // Catch:{ IOException -> 0x0084 }
            goto L_0x0088
        L_0x0084:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0088:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.camera.CameraService.openCamera(java.lang.String):void");
    }

    public static void openCamera() {
        openCamera("state=1,rotate=0");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v16, resolved type: java.io.OutputStreamWriter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v18, resolved type: java.io.OutputStreamWriter} */
    /* JADX WARNING: type inference failed for: r0v5, types: [java.io.OutputStreamWriter] */
    /* JADX WARNING: type inference failed for: r0v9 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x004a A[SYNTHETIC, Splitter:B:33:0x004a] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0054 A[SYNTHETIC, Splitter:B:38:0x0054] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0066 A[SYNTHETIC, Splitter:B:48:0x0066] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0070 A[SYNTHETIC, Splitter:B:53:0x0070] */
    /* JADX WARNING: Removed duplicated region for block: B:58:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void closeCamera() {
        /*
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "/dev/vehicle"
            r0.<init>(r1)
            r1 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x005d, all -> 0x003e }
            r2.<init>(r0)     // Catch:{ FileNotFoundException -> 0x005d, all -> 0x003e }
            java.io.OutputStreamWriter r0 = new java.io.OutputStreamWriter     // Catch:{ FileNotFoundException -> 0x003c, all -> 0x0039 }
            r0.<init>(r2)     // Catch:{ FileNotFoundException -> 0x003c, all -> 0x0039 }
            java.io.PrintWriter r3 = new java.io.PrintWriter     // Catch:{ FileNotFoundException -> 0x0037, all -> 0x0032 }
            r3.<init>(r0)     // Catch:{ FileNotFoundException -> 0x0037, all -> 0x0032 }
            java.lang.String r1 = "state=0"
            r3.write(r1)     // Catch:{ FileNotFoundException -> 0x0030, all -> 0x002e }
            r3.flush()     // Catch:{ FileNotFoundException -> 0x0030, all -> 0x002e }
            r3.close()
            r0.close()     // Catch:{ IOException -> 0x0026 }
            goto L_0x002a
        L_0x0026:
            r0 = move-exception
            r0.printStackTrace()
        L_0x002a:
            r2.close()     // Catch:{ IOException -> 0x0074 }
            goto L_0x0078
        L_0x002e:
            r1 = move-exception
            goto L_0x0043
        L_0x0030:
            r1 = r3
            goto L_0x005f
        L_0x0032:
            r3 = move-exception
            r4 = r3
            r3 = r1
            r1 = r4
            goto L_0x0043
        L_0x0037:
            goto L_0x005f
        L_0x0039:
            r0 = move-exception
            r3 = r1
            goto L_0x0041
        L_0x003c:
            r0 = r1
            goto L_0x005f
        L_0x003e:
            r0 = move-exception
            r2 = r1
            r3 = r2
        L_0x0041:
            r1 = r0
            r0 = r3
        L_0x0043:
            if (r3 == 0) goto L_0x0048
            r3.close()
        L_0x0048:
            if (r0 == 0) goto L_0x0052
            r0.close()     // Catch:{ IOException -> 0x004e }
            goto L_0x0052
        L_0x004e:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0052:
            if (r2 == 0) goto L_0x005c
            r2.close()     // Catch:{ IOException -> 0x0058 }
            goto L_0x005c
        L_0x0058:
            r0 = move-exception
            r0.printStackTrace()
        L_0x005c:
            throw r1
        L_0x005d:
            r0 = r1
            r2 = r0
        L_0x005f:
            if (r1 == 0) goto L_0x0064
            r1.close()
        L_0x0064:
            if (r0 == 0) goto L_0x006e
            r0.close()     // Catch:{ IOException -> 0x006a }
            goto L_0x006e
        L_0x006a:
            r0 = move-exception
            r0.printStackTrace()
        L_0x006e:
            if (r2 == 0) goto L_0x0078
            r2.close()     // Catch:{ IOException -> 0x0074 }
            goto L_0x0078
        L_0x0074:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0078:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.camera.CameraService.closeCamera():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0052 A[SYNTHETIC, Splitter:B:37:0x0052] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x005c A[SYNTHETIC, Splitter:B:42:0x005c] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x006d A[SYNTHETIC, Splitter:B:50:0x006d] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0077 A[SYNTHETIC, Splitter:B:55:0x0077] */
    /* JADX WARNING: Removed duplicated region for block: B:61:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void set6806Channel(java.lang.String r4) {
        /*
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "/sys/devices/platform/vehicle/tp6806_ch"
            r0.<init>(r1)
            boolean r1 = r0.exists()
            if (r1 != 0) goto L_0x000e
            return
        L_0x000e:
            r1 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0045, all -> 0x0041 }
            r2.<init>(r0)     // Catch:{ FileNotFoundException -> 0x0045, all -> 0x0041 }
            java.io.OutputStreamWriter r0 = new java.io.OutputStreamWriter     // Catch:{ FileNotFoundException -> 0x003e, all -> 0x003b }
            r0.<init>(r2)     // Catch:{ FileNotFoundException -> 0x003e, all -> 0x003b }
            java.io.PrintWriter r3 = new java.io.PrintWriter     // Catch:{ FileNotFoundException -> 0x0039 }
            r3.<init>(r0)     // Catch:{ FileNotFoundException -> 0x0039 }
            r3.write(r4)     // Catch:{ FileNotFoundException -> 0x0036, all -> 0x0033 }
            r3.flush()     // Catch:{ FileNotFoundException -> 0x0036, all -> 0x0033 }
            r3.close()
            r0.close()     // Catch:{ IOException -> 0x002b }
            goto L_0x002f
        L_0x002b:
            r4 = move-exception
            r4.printStackTrace()
        L_0x002f:
            r2.close()     // Catch:{ IOException -> 0x0060 }
            goto L_0x0064
        L_0x0033:
            r4 = move-exception
            r1 = r3
            goto L_0x0066
        L_0x0036:
            r4 = move-exception
            r1 = r3
            goto L_0x0048
        L_0x0039:
            r4 = move-exception
            goto L_0x0048
        L_0x003b:
            r4 = move-exception
            r0 = r1
            goto L_0x0066
        L_0x003e:
            r4 = move-exception
            r0 = r1
            goto L_0x0048
        L_0x0041:
            r4 = move-exception
            r0 = r1
            r2 = r0
            goto L_0x0066
        L_0x0045:
            r4 = move-exception
            r0 = r1
            r2 = r0
        L_0x0048:
            r4.printStackTrace()     // Catch:{ all -> 0x0065 }
            if (r1 == 0) goto L_0x0050
            r1.close()
        L_0x0050:
            if (r0 == 0) goto L_0x005a
            r0.close()     // Catch:{ IOException -> 0x0056 }
            goto L_0x005a
        L_0x0056:
            r4 = move-exception
            r4.printStackTrace()
        L_0x005a:
            if (r2 == 0) goto L_0x0064
            r2.close()     // Catch:{ IOException -> 0x0060 }
            goto L_0x0064
        L_0x0060:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0064:
            return
        L_0x0065:
            r4 = move-exception
        L_0x0066:
            if (r1 == 0) goto L_0x006b
            r1.close()
        L_0x006b:
            if (r0 == 0) goto L_0x0075
            r0.close()     // Catch:{ IOException -> 0x0071 }
            goto L_0x0075
        L_0x0071:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0075:
            if (r2 == 0) goto L_0x007f
            r2.close()     // Catch:{ IOException -> 0x007b }
            goto L_0x007f
        L_0x007b:
            r0 = move-exception
            r0.printStackTrace()
        L_0x007f:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.camera.CameraService.set6806Channel(java.lang.String):void");
    }

    static {
        System.loadLibrary("camerayuv");
    }
}
