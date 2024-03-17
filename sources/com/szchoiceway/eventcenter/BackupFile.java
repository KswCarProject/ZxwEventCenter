package com.szchoiceway.eventcenter;

import android.content.Context;

public class BackupFile {
    public static final String FILE_PATH = "/dev/block/by-name/backup";
    private static final String TAG = "BackupFile";
    private SysProviderOpt mProvider;

    public BackupFile(Context context) {
        this.mProvider = SysProviderOpt.getInstance(context);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x007c, code lost:
        r3 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x008b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x008c, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x007b, code lost:
        r0 = e;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x008b A[ExcHandler: FileNotFoundException (r0v2 'e' java.io.FileNotFoundException A[CUSTOM_DECLARE]), Splitter:B:1:0x0013] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void saveBackup() {
        /*
            r6 = this;
            java.lang.String r0 = "\n"
            r6.clearFile()
            java.io.File r1 = new java.io.File
            java.lang.String r2 = "/dev/block/by-name/backup"
            r1.<init>(r2)
            com.szchoiceway.eventcenter.SysProviderOpt r2 = r6.mProvider
            java.util.ArrayList r2 = r2.queryRecordItemName()
            r3 = 0
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x007e }
            r4.<init>(r1)     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x007e }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x007b }
            r1.<init>()     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x007b }
            java.lang.String r3 = "Head:"
            r1.append(r3)     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x007b }
            int r3 = r2.size()     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x007b }
            r1.append(r3)     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x007b }
            r1.append(r0)     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x007b }
            java.lang.String r1 = r1.toString()     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x007b }
            byte[] r1 = r1.getBytes()     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x007b }
            r4.write(r1)     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x007b }
            java.util.Iterator r1 = r2.iterator()     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x007b }
        L_0x003b:
            boolean r2 = r1.hasNext()     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x007b }
            if (r2 == 0) goto L_0x006e
            java.lang.Object r2 = r1.next()     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x007b }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x007b }
            com.szchoiceway.eventcenter.SysProviderOpt r3 = r6.mProvider     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x007b }
            java.lang.String r5 = ""
            java.lang.String r3 = r3.getRecordValue(r2, r5)     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x007b }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x007b }
            r5.<init>()     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x007b }
            r5.append(r2)     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x007b }
            java.lang.String r2 = "|"
            r5.append(r2)     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x007b }
            r5.append(r3)     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x007b }
            r5.append(r0)     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x007b }
            java.lang.String r2 = r5.toString()     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x007b }
            byte[] r2 = r2.getBytes()     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x007b }
            r4.write(r2)     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x007b }
            goto L_0x003b
        L_0x006e:
            java.lang.String r0 = "End\n"
            byte[] r0 = r0.getBytes()     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x007b }
            r4.write(r0)     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x007b }
            r4.close()     // Catch:{ FileNotFoundException -> 0x008b, IOException -> 0x007b }
            goto L_0x008f
        L_0x007b:
            r0 = move-exception
            r3 = r4
            goto L_0x007f
        L_0x007e:
            r0 = move-exception
        L_0x007f:
            r0.printStackTrace()
            r3.close()     // Catch:{ IOException -> 0x0086 }
            goto L_0x008f
        L_0x0086:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x008f
        L_0x008b:
            r0 = move-exception
            r0.printStackTrace()
        L_0x008f:
            java.lang.String r0 = "BackupFile"
            java.lang.String r1 = "saveBackup success!"
            android.util.Log.d(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.eventcenter.BackupFile.saveBackup():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x001b A[Catch:{ IOException -> 0x0021 }] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001c A[Catch:{ IOException -> 0x0021 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String readLine(java.io.BufferedReader r5, int r6) {
        /*
            r4 = this;
            r0 = 0
            java.lang.String r1 = ""
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0021 }
            r2.<init>(r1)     // Catch:{ IOException -> 0x0021 }
        L_0x0008:
            int r1 = r5.read()     // Catch:{ IOException -> 0x0021 }
            r3 = 10
            if (r1 == r3) goto L_0x0019
            if (r6 <= 0) goto L_0x0019
            char r1 = (char) r1     // Catch:{ IOException -> 0x0021 }
            r2.append(r1)     // Catch:{ IOException -> 0x0021 }
            int r6 = r6 + -1
            goto L_0x0008
        L_0x0019:
            if (r6 > 0) goto L_0x001c
            goto L_0x0020
        L_0x001c:
            java.lang.String r0 = r2.toString()     // Catch:{ IOException -> 0x0021 }
        L_0x0020:
            return r0
        L_0x0021:
            r5 = move-exception
            r5.printStackTrace()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.eventcenter.BackupFile.readLine(java.io.BufferedReader, int):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a8, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00b5, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00c2, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00c3, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00c6, code lost:
        return false;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00c2 A[ExcHandler: FileNotFoundException (r0v1 'e' java.io.FileNotFoundException A[CUSTOM_DECLARE]), Splitter:B:1:0x0009] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean readBackup() {
        /*
            r10 = this;
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "/dev/block/by-name/backup"
            r0.<init>(r1)
            r1 = 0
            r2 = 0
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00b5, Exception -> 0x00a8 }
            java.io.FileReader r4 = new java.io.FileReader     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00b5, Exception -> 0x00a8 }
            r4.<init>(r0)     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00b5, Exception -> 0x00a8 }
            r3.<init>(r4)     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00b5, Exception -> 0x00a8 }
            r0 = 200(0xc8, float:2.8E-43)
            r3.mark(r0)     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
            char[] r2 = new char[r0]     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
            r3.read(r2, r1, r0)     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
            java.lang.String r0 = new java.lang.String     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
            r0.<init>(r2)     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
            java.lang.String r2 = "\n"
            boolean r0 = r0.contains(r2)     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
            if (r0 != 0) goto L_0x002b
            return r1
        L_0x002b:
            r3.reset()     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
            r0 = 1024(0x400, float:1.435E-42)
            java.lang.String r2 = r10.readLine(r3, r0)     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
            if (r2 == 0) goto L_0x00a1
            java.lang.String r4 = "Head"
            boolean r4 = r2.contains(r4)     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
            if (r4 != 0) goto L_0x003f
            goto L_0x00a1
        L_0x003f:
            java.lang.String r4 = ":"
            java.lang.String[] r2 = r2.split(r4)     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
            r4 = 1
            r2 = r2[r4]     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
            r5 = 0
        L_0x004d:
            java.lang.String r6 = "BackupFile"
            if (r5 >= r2) goto L_0x0095
            java.lang.String r7 = r10.readLine(r3, r0)     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
            r8.<init>()     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
            java.lang.String r9 = "str =======> "
            r8.append(r9)     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
            r8.append(r7)     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
            java.lang.String r8 = r8.toString()     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
            android.util.Log.d(r6, r8)     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
            if (r7 == 0) goto L_0x0095
            java.lang.String r8 = "End"
            boolean r8 = r7.equals(r8)     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
            if (r8 == 0) goto L_0x0074
            goto L_0x0095
        L_0x0074:
            java.lang.String r6 = "[|]"
            java.lang.String[] r6 = r7.split(r6)     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
            int r7 = r6.length     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
            r8 = 2
            if (r7 >= r8) goto L_0x007f
            goto L_0x004d
        L_0x007f:
            r7 = r6[r1]     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
            java.lang.String r8 = "Sys_custom_factory_password"
            boolean r7 = r7.equals(r8)     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
            if (r7 != 0) goto L_0x0092
            com.szchoiceway.eventcenter.SysProviderOpt r7 = r10.mProvider     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
            r8 = r6[r1]     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
            r6 = r6[r4]     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
            r7.updateRecord(r8, r6)     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
        L_0x0092:
            int r5 = r5 + 1
            goto L_0x004d
        L_0x0095:
            r3.close()     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
            r10.clearFile()     // Catch:{ FileNotFoundException -> 0x00c2, IOException -> 0x00a5, Exception -> 0x00a2 }
            java.lang.String r0 = "readBackup success!"
            android.util.Log.d(r6, r0)
            return r4
        L_0x00a1:
            return r1
        L_0x00a2:
            r0 = move-exception
            r2 = r3
            goto L_0x00a9
        L_0x00a5:
            r0 = move-exception
            r2 = r3
            goto L_0x00b6
        L_0x00a8:
            r0 = move-exception
        L_0x00a9:
            r0.printStackTrace()
            r2.close()     // Catch:{ IOException -> 0x00b0 }
            goto L_0x00b4
        L_0x00b0:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00b4:
            return r1
        L_0x00b5:
            r0 = move-exception
        L_0x00b6:
            r0.printStackTrace()
            r2.close()     // Catch:{ IOException -> 0x00bd }
            goto L_0x00c1
        L_0x00bd:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00c1:
            return r1
        L_0x00c2:
            r0 = move-exception
            r0.printStackTrace()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.eventcenter.BackupFile.readBackup():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0025, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0026, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002b, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001b, code lost:
        r0 = e;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0021 A[SYNTHETIC, Splitter:B:10:0x0021] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x002a A[ExcHandler: FileNotFoundException (r0v1 'e' java.io.FileNotFoundException A[CUSTOM_DECLARE]), Splitter:B:1:0x000c] */
    /* JADX WARNING: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void clearFile() {
        /*
            r4 = this;
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "/dev/block/by-name/backup"
            r0.<init>(r1)
            r1 = 92274688(0x5800000, float:1.2037062E-35)
            byte[] r1 = new byte[r1]
            r2 = 0
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x002a, IOException -> 0x001b }
            r3.<init>(r0)     // Catch:{ FileNotFoundException -> 0x002a, IOException -> 0x001b }
            r3.write(r1)     // Catch:{ FileNotFoundException -> 0x002a, IOException -> 0x0018 }
            r3.close()     // Catch:{ FileNotFoundException -> 0x002a, IOException -> 0x0018 }
            goto L_0x002e
        L_0x0018:
            r0 = move-exception
            r2 = r3
            goto L_0x001c
        L_0x001b:
            r0 = move-exception
        L_0x001c:
            r0.printStackTrace()
            if (r2 == 0) goto L_0x002e
            r2.close()     // Catch:{ IOException -> 0x0025 }
            goto L_0x002e
        L_0x0025:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x002e
        L_0x002a:
            r0 = move-exception
            r0.printStackTrace()
        L_0x002e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.eventcenter.BackupFile.clearFile():void");
    }
}
