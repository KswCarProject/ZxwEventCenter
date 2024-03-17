package com.szchoiceway.eventcenter;

import android.os.SystemProperties;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class PackageInstallFilter {
    static final String PackageFilterFile = "/data/local/PackageFilterFile.txt";
    static final String TAG = "PackageInstallFilter";

    public static boolean isFilterPackageName(String str) throws IOException {
        Log.d(TAG, "packageName is " + str);
        Log.d(TAG, "fileName is /data/local/PackageFilterFile.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(PackageFilterFile)), "UTF-8"));
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return false;
            }
            Log.d(TAG, readLine);
            if (!readLine.startsWith("#") && readLine.contains(str)) {
                Log.d(TAG, "OK!Already find the " + str);
                return true;
            }
        }
    }

    public static void setPackageInstallFilterState(boolean z) {
        Log.d(TAG, "setPackageInstallFilterState:" + z);
        SystemProperties.set("sys.choiceway.packagefilter", String.valueOf(z));
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0061  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void setPackageInstallFilterFile(java.lang.String r9) throws java.io.IOException {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "setPackageInstallFilterFile:"
            r0.append(r1)
            r0.append(r9)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "PackageInstallFilter"
            android.util.Log.d(r1, r0)
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x0058 }
            r1.<init>(r9)     // Catch:{ all -> 0x0058 }
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x0058 }
            r1.<init>(r9)     // Catch:{ all -> 0x0058 }
            java.nio.channels.FileChannel r9 = r1.getChannel()     // Catch:{ all -> 0x0058 }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x0053 }
            java.lang.String r2 = "/data/local/PackageFilterFile.txt"
            r1.<init>(r2)     // Catch:{ all -> 0x0053 }
            java.nio.channels.FileChannel r0 = r1.getChannel()     // Catch:{ all -> 0x0053 }
            if (r0 == 0) goto L_0x0048
            if (r9 == 0) goto L_0x0048
            r4 = 0
            long r6 = r9.size()     // Catch:{ all -> 0x0053 }
            r2 = r0
            r3 = r9
            r2.transferFrom(r3, r4, r6)     // Catch:{ all -> 0x0053 }
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch:{ all -> 0x0053 }
            java.lang.String r2 = "chmod 777 /data/local/PackageFilterFile.txt"
            r1.exec(r2)     // Catch:{ all -> 0x0053 }
        L_0x0048:
            if (r9 == 0) goto L_0x004d
            r9.close()
        L_0x004d:
            if (r0 == 0) goto L_0x0052
            r0.close()
        L_0x0052:
            return
        L_0x0053:
            r1 = move-exception
            r8 = r0
            r0 = r9
            r9 = r8
            goto L_0x005a
        L_0x0058:
            r1 = move-exception
            r9 = r0
        L_0x005a:
            if (r0 == 0) goto L_0x005f
            r0.close()
        L_0x005f:
            if (r9 == 0) goto L_0x0064
            r9.close()
        L_0x0064:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.eventcenter.PackageInstallFilter.setPackageInstallFilterFile(java.lang.String):void");
    }
}
