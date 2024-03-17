package com.szchoiceway.eventcenter.usbinstallapk;

import java.io.IOException;

public class SystemManager {
    private static final String TAG = "SystemManager";

    public static void installApk(String str) {
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0068 A[SYNTHETIC, Splitter:B:24:0x0068] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0072 A[SYNTHETIC, Splitter:B:31:0x0072] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int RootCommand(java.lang.String r5) {
        /*
            java.lang.String r0 = "SystemManager"
            r1 = 0
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch:{ Exception -> 0x005c, all -> 0x0059 }
            java.lang.String r3 = "su"
            java.lang.Process r2 = r2.exec(r3)     // Catch:{ Exception -> 0x005c, all -> 0x0059 }
            java.io.DataOutputStream r3 = new java.io.DataOutputStream     // Catch:{ Exception -> 0x0057 }
            java.io.OutputStream r4 = r2.getOutputStream()     // Catch:{ Exception -> 0x0057 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x0057 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0054, all -> 0x0051 }
            r1.<init>()     // Catch:{ Exception -> 0x0054, all -> 0x0051 }
            r1.append(r5)     // Catch:{ Exception -> 0x0054, all -> 0x0051 }
            java.lang.String r5 = "\n"
            r1.append(r5)     // Catch:{ Exception -> 0x0054, all -> 0x0051 }
            java.lang.String r5 = r1.toString()     // Catch:{ Exception -> 0x0054, all -> 0x0051 }
            r3.writeBytes(r5)     // Catch:{ Exception -> 0x0054, all -> 0x0051 }
            java.lang.String r5 = "exit\n"
            r3.writeBytes(r5)     // Catch:{ Exception -> 0x0054, all -> 0x0051 }
            r3.flush()     // Catch:{ Exception -> 0x0054, all -> 0x0051 }
            int r5 = r2.waitFor()     // Catch:{ Exception -> 0x0054, all -> 0x0051 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0054, all -> 0x0051 }
            r1.<init>()     // Catch:{ Exception -> 0x0054, all -> 0x0051 }
            java.lang.String r4 = "i:"
            r1.append(r4)     // Catch:{ Exception -> 0x0054, all -> 0x0051 }
            r1.append(r5)     // Catch:{ Exception -> 0x0054, all -> 0x0051 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0054, all -> 0x0051 }
            android.util.Log.d(r0, r1)     // Catch:{ Exception -> 0x0054, all -> 0x0051 }
            r3.close()     // Catch:{ Exception -> 0x0050 }
            r2.destroy()     // Catch:{ Exception -> 0x0050 }
        L_0x0050:
            return r5
        L_0x0051:
            r5 = move-exception
            r1 = r3
            goto L_0x0070
        L_0x0054:
            r5 = move-exception
            r1 = r3
            goto L_0x005e
        L_0x0057:
            r5 = move-exception
            goto L_0x005e
        L_0x0059:
            r5 = move-exception
            r2 = r1
            goto L_0x0070
        L_0x005c:
            r5 = move-exception
            r2 = r1
        L_0x005e:
            java.lang.String r5 = r5.getMessage()     // Catch:{ all -> 0x006f }
            android.util.Log.d(r0, r5)     // Catch:{ all -> 0x006f }
            r5 = -1
            if (r1 == 0) goto L_0x006b
            r1.close()     // Catch:{ Exception -> 0x006e }
        L_0x006b:
            r2.destroy()     // Catch:{ Exception -> 0x006e }
        L_0x006e:
            return r5
        L_0x006f:
            r5 = move-exception
        L_0x0070:
            if (r1 == 0) goto L_0x0075
            r1.close()     // Catch:{ Exception -> 0x0078 }
        L_0x0075:
            r2.destroy()     // Catch:{ Exception -> 0x0078 }
        L_0x0078:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.eventcenter.usbinstallapk.SystemManager.RootCommand(java.lang.String):int");
    }

    public static void setPermission(String str) {
        try {
            Runtime.getRuntime().exec("chmod 777 " + str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00d3 A[SYNTHETIC, Splitter:B:45:0x00d3] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00db A[Catch:{ Exception -> 0x00e1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00e5 A[SYNTHETIC, Splitter:B:54:0x00e5] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00ed A[Catch:{ Exception -> 0x00f4 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean runRootCommand(java.lang.String r8) {
        /*
            java.lang.String r0 = "/n"
            java.lang.String r1 = "SystemManager"
            r2 = 0
            r3 = 0
            java.lang.Runtime r4 = java.lang.Runtime.getRuntime()     // Catch:{ Exception -> 0x00b6, all -> 0x00b2 }
            java.lang.String r5 = "su"
            java.lang.Process r4 = r4.exec(r5)     // Catch:{ Exception -> 0x00b6, all -> 0x00b2 }
            java.io.DataOutputStream r5 = new java.io.DataOutputStream     // Catch:{ Exception -> 0x00af, all -> 0x00ac }
            java.io.OutputStream r6 = r4.getOutputStream()     // Catch:{ Exception -> 0x00af, all -> 0x00ac }
            r5.<init>(r6)     // Catch:{ Exception -> 0x00af, all -> 0x00ac }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a8, all -> 0x00a4 }
            r6.<init>()     // Catch:{ Exception -> 0x00a8, all -> 0x00a4 }
            java.lang.String r7 = "chmod 777 "
            r6.append(r7)     // Catch:{ Exception -> 0x00a8, all -> 0x00a4 }
            r6.append(r8)     // Catch:{ Exception -> 0x00a8, all -> 0x00a4 }
            r6.append(r0)     // Catch:{ Exception -> 0x00a8, all -> 0x00a4 }
            java.lang.String r8 = r6.toString()     // Catch:{ Exception -> 0x00a8, all -> 0x00a4 }
            r5.writeBytes(r8)     // Catch:{ Exception -> 0x00a8, all -> 0x00a4 }
            java.lang.String r8 = "exit/n"
            r5.writeBytes(r8)     // Catch:{ Exception -> 0x00a8, all -> 0x00a4 }
            java.io.BufferedReader r8 = new java.io.BufferedReader     // Catch:{ Exception -> 0x00a8, all -> 0x00a4 }
            java.io.InputStreamReader r6 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x00a8, all -> 0x00a4 }
            java.io.InputStream r7 = r4.getInputStream()     // Catch:{ Exception -> 0x00a8, all -> 0x00a4 }
            r6.<init>(r7)     // Catch:{ Exception -> 0x00a8, all -> 0x00a4 }
            r8.<init>(r6)     // Catch:{ Exception -> 0x00a8, all -> 0x00a4 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a2, all -> 0x00a0 }
            r2.<init>()     // Catch:{ Exception -> 0x00a2, all -> 0x00a0 }
        L_0x0048:
            java.lang.String r6 = r8.readLine()     // Catch:{ Exception -> 0x00a2, all -> 0x00a0 }
            if (r6 == 0) goto L_0x008f
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a2, all -> 0x00a0 }
            r7.<init>()     // Catch:{ Exception -> 0x00a2, all -> 0x00a0 }
            r7.append(r6)     // Catch:{ Exception -> 0x00a2, all -> 0x00a0 }
            r7.append(r0)     // Catch:{ Exception -> 0x00a2, all -> 0x00a0 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x00a2, all -> 0x00a0 }
            r2.append(r7)     // Catch:{ Exception -> 0x00a2, all -> 0x00a0 }
            java.lang.String r7 = "Success"
            boolean r6 = r7.equalsIgnoreCase(r6)     // Catch:{ Exception -> 0x00a2, all -> 0x00a0 }
            if (r6 == 0) goto L_0x0048
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a2, all -> 0x00a0 }
            r0.<init>()     // Catch:{ Exception -> 0x00a2, all -> 0x00a0 }
            java.lang.String r6 = "----------"
            r0.append(r6)     // Catch:{ Exception -> 0x00a2, all -> 0x00a0 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x00a2, all -> 0x00a0 }
            r0.append(r2)     // Catch:{ Exception -> 0x00a2, all -> 0x00a0 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x00a2, all -> 0x00a0 }
            android.util.Log.i(r1, r0)     // Catch:{ Exception -> 0x00a2, all -> 0x00a0 }
            r0 = 1
            r5.flush()     // Catch:{ Exception -> 0x008e }
            r5.close()     // Catch:{ Exception -> 0x008e }
            r8.close()     // Catch:{ Exception -> 0x008e }
            r4.destroy()     // Catch:{ Exception -> 0x008e }
            return r0
        L_0x008e:
            return r3
        L_0x008f:
            r4.waitFor()     // Catch:{ Exception -> 0x00a2, all -> 0x00a0 }
            r5.flush()     // Catch:{ Exception -> 0x009f }
            r5.close()     // Catch:{ Exception -> 0x009f }
            r8.close()     // Catch:{ Exception -> 0x009f }
            r4.destroy()     // Catch:{ Exception -> 0x009f }
            goto L_0x00e1
        L_0x009f:
            return r3
        L_0x00a0:
            r0 = move-exception
            goto L_0x00a6
        L_0x00a2:
            r0 = move-exception
            goto L_0x00aa
        L_0x00a4:
            r0 = move-exception
            r8 = r2
        L_0x00a6:
            r2 = r5
            goto L_0x00e3
        L_0x00a8:
            r0 = move-exception
            r8 = r2
        L_0x00aa:
            r2 = r5
            goto L_0x00b9
        L_0x00ac:
            r0 = move-exception
            r8 = r2
            goto L_0x00e3
        L_0x00af:
            r0 = move-exception
            r8 = r2
            goto L_0x00b9
        L_0x00b2:
            r0 = move-exception
            r8 = r2
            r4 = r8
            goto L_0x00e3
        L_0x00b6:
            r0 = move-exception
            r8 = r2
            r4 = r8
        L_0x00b9:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e2 }
            r5.<init>()     // Catch:{ all -> 0x00e2 }
            java.lang.String r6 = "异常:"
            r5.append(r6)     // Catch:{ all -> 0x00e2 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x00e2 }
            r5.append(r0)     // Catch:{ all -> 0x00e2 }
            java.lang.String r0 = r5.toString()     // Catch:{ all -> 0x00e2 }
            android.util.Log.i(r1, r0)     // Catch:{ all -> 0x00e2 }
            if (r2 == 0) goto L_0x00d9
            r2.flush()     // Catch:{ Exception -> 0x00e1 }
            r2.close()     // Catch:{ Exception -> 0x00e1 }
        L_0x00d9:
            if (r8 == 0) goto L_0x00de
            r8.close()     // Catch:{ Exception -> 0x00e1 }
        L_0x00de:
            r4.destroy()     // Catch:{ Exception -> 0x00e1 }
        L_0x00e1:
            return r3
        L_0x00e2:
            r0 = move-exception
        L_0x00e3:
            if (r2 == 0) goto L_0x00eb
            r2.flush()     // Catch:{ Exception -> 0x00f4 }
            r2.close()     // Catch:{ Exception -> 0x00f4 }
        L_0x00eb:
            if (r8 == 0) goto L_0x00f0
            r8.close()     // Catch:{ Exception -> 0x00f4 }
        L_0x00f0:
            r4.destroy()     // Catch:{ Exception -> 0x00f4 }
            throw r0
        L_0x00f4:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.eventcenter.usbinstallapk.SystemManager.runRootCommand(java.lang.String):boolean");
    }
}
