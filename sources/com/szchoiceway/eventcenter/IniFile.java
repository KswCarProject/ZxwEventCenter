package com.szchoiceway.eventcenter;

import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import com.example.mylibrary.BuildConfig;
import java.io.File;
import java.util.HashMap;
import java.util.List;

public class IniFile {
    public static final String TAG = "IniFile";
    String cmd = BuildConfig.FLAVOR;
    private String mPath;
    private List<Section> mSectionList;
    private boolean mSuccess = false;

    private static class Section {
        HashMap<String, String> mKeyMap;
        String mSectionName;

        private Section() {
            this.mKeyMap = new HashMap<>(10);
        }
    }

    public IniFile(File file) {
        if (file != null && file.exists()) {
            init(file.getPath());
        }
    }

    public IniFile(String str) {
        if (!TextUtils.isEmpty(str)) {
            init(str);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b9 A[SYNTHETIC, Splitter:B:37:0x00b9] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00c4 A[SYNTHETIC, Splitter:B:42:0x00c4] */
    /* JADX WARNING: Removed duplicated region for block: B:54:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void init(java.lang.String r11) {
        /*
            r10 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r10.mSectionList = r0
            r10.mPath = r11
            java.io.File r11 = new java.io.File
            java.lang.String r0 = r10.mPath
            r11.<init>(r0)
            boolean r0 = r11.exists()
            if (r0 == 0) goto L_0x00cd
            r0 = 0
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Exception -> 0x00b1 }
            r2.<init>(r11)     // Catch:{ Exception -> 0x00b1 }
            r11 = 1048576(0x100000, float:1.469368E-39)
            byte[] r3 = new byte[r11]     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            int r11 = r2.read(r3, r1, r11)     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            r2.close()     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            r4 = -1
            r5 = 1
            if (r11 == r4) goto L_0x00a3
            java.lang.String r11 = new java.lang.String     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            java.nio.charset.Charset r6 = java.nio.charset.StandardCharsets.UTF_8     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            r11.<init>(r3, r6)     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            java.lang.String r11 = r11.trim()     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            java.lang.String r3 = "IniFile"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            r6.<init>()     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            java.lang.String r7 = "IniFile: content = "
            r6.append(r7)     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            r6.append(r11)     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            android.util.Log.i(r3, r6)     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            java.lang.String r3 = "\n"
            java.lang.String[] r11 = r11.split(r3)     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            int r3 = r11.length     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            if (r3 <= 0) goto L_0x00a3
            int r3 = r11.length     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            r6 = 0
        L_0x0058:
            if (r6 >= r3) goto L_0x00a3
            r7 = r11[r6]     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            java.lang.String r8 = "["
            boolean r8 = r7.startsWith(r8)     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            if (r8 == 0) goto L_0x007b
            java.lang.String r8 = "]"
            boolean r8 = r7.endsWith(r8)     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            if (r8 == 0) goto L_0x007b
            com.szchoiceway.eventcenter.IniFile$Section r8 = new com.szchoiceway.eventcenter.IniFile$Section     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            r8.<init>()     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            r8.mSectionName = r7     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            java.util.List<com.szchoiceway.eventcenter.IniFile$Section> r7 = r10.mSectionList     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            r7.add(r8)     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            int r4 = r4 + 1
            goto L_0x00a0
        L_0x007b:
            if (r4 < 0) goto L_0x00a0
            java.util.List<com.szchoiceway.eventcenter.IniFile$Section> r8 = r10.mSectionList     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            int r8 = r8.size()     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            if (r8 <= 0) goto L_0x00a0
            java.lang.String r8 = "="
            java.lang.String[] r7 = r7.split(r8)     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            int r8 = r7.length     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            r9 = 2
            if (r8 != r9) goto L_0x00a0
            java.util.List<com.szchoiceway.eventcenter.IniFile$Section> r8 = r10.mSectionList     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            java.lang.Object r8 = r8.get(r4)     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            com.szchoiceway.eventcenter.IniFile$Section r8 = (com.szchoiceway.eventcenter.IniFile.Section) r8     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            java.util.HashMap<java.lang.String, java.lang.String> r8 = r8.mKeyMap     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            r9 = r7[r1]     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            r7 = r7[r5]     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            r8.put(r9, r7)     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
        L_0x00a0:
            int r6 = r6 + 1
            goto L_0x0058
        L_0x00a3:
            r10.mSuccess = r5     // Catch:{ Exception -> 0x00ac, all -> 0x00a9 }
            r2.close()     // Catch:{ IOException -> 0x00bd }
            goto L_0x00cd
        L_0x00a9:
            r11 = move-exception
            r0 = r2
            goto L_0x00c2
        L_0x00ac:
            r11 = move-exception
            r0 = r2
            goto L_0x00b2
        L_0x00af:
            r11 = move-exception
            goto L_0x00c2
        L_0x00b1:
            r11 = move-exception
        L_0x00b2:
            r11.printStackTrace()     // Catch:{ all -> 0x00af }
            r10.mSuccess = r1     // Catch:{ all -> 0x00af }
            if (r0 == 0) goto L_0x00cd
            r0.close()     // Catch:{ IOException -> 0x00bd }
            goto L_0x00cd
        L_0x00bd:
            r11 = move-exception
            r11.printStackTrace()
            goto L_0x00cd
        L_0x00c2:
            if (r0 == 0) goto L_0x00cc
            r0.close()     // Catch:{ IOException -> 0x00c8 }
            goto L_0x00cc
        L_0x00c8:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00cc:
            throw r11
        L_0x00cd:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.eventcenter.IniFile.init(java.lang.String):void");
    }

    private void runCmd(String str) {
        SystemProperties.set("sys.apk_path", str);
        SystemProperties.set("ctl.start", "install_apk");
    }

    public boolean isInitSuccess() {
        Log.i(TAG, "isInitSuccess: mSuccess = " + this.mSuccess);
        return this.mSuccess;
    }

    public void clearSectionList() {
        List<Section> list = this.mSectionList;
        if (list != null) {
            list.clear();
        }
    }

    private boolean checkSectionExist(String str) {
        if (this.mSectionList == null) {
            return false;
        }
        String str2 = "[" + str + "]";
        for (Section section : this.mSectionList) {
            if (section.mSectionName.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    private HashMap<String, String> getSectionMap(String str) {
        if (this.mSectionList == null) {
            return null;
        }
        String str2 = "[" + str + "]";
        for (Section next : this.mSectionList) {
            if (next.mSectionName.equals(str2)) {
                return next.mKeyMap;
            }
        }
        return null;
    }

    public void set(String str, String str2, String str3) {
        Log.d(TAG, "set " + str + ", " + str2 + " " + str3);
        if (this.mSectionList != null) {
            if (checkSectionExist(str)) {
                HashMap<String, String> sectionMap = getSectionMap(str);
                if (sectionMap != null) {
                    sectionMap.put(str2, str3);
                    return;
                }
                return;
            }
            Section section = new Section();
            section.mSectionName = "[" + str + "]";
            section.mKeyMap.put(str2, str3);
            this.mSectionList.add(section);
        }
    }

    public String get(String str, String str2, String str3) {
        return (!checkSectionExist(str) || getSectionMap(str).get(str2) == null) ? str3 : getSectionMap(str).get(str2);
    }

    public String get(String str, String str2) {
        return get(str, str2, BuildConfig.FLAVOR);
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00bf A[SYNTHETIC, Splitter:B:31:0x00bf] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ca A[SYNTHETIC, Splitter:B:36:0x00ca] */
    /* JADX WARNING: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void save() {
        /*
            r8 = this;
            java.lang.String r0 = "\n"
            java.util.List<com.szchoiceway.eventcenter.IniFile$Section> r1 = r8.mSectionList
            if (r1 != 0) goto L_0x0007
            return
        L_0x0007:
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch:{ IOException -> 0x00b9 }
            java.lang.String r3 = r8.mPath     // Catch:{ IOException -> 0x00b9 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x00b9 }
            boolean r2 = r2.exists()     // Catch:{ IOException -> 0x00b9 }
            if (r2 == 0) goto L_0x00b1
            java.io.RandomAccessFile r2 = new java.io.RandomAccessFile     // Catch:{ IOException -> 0x00b9 }
            java.lang.String r3 = r8.mPath     // Catch:{ IOException -> 0x00b9 }
            java.lang.String r4 = "rw"
            r2.<init>(r3, r4)     // Catch:{ IOException -> 0x00b9 }
            r3 = 0
            r2.seek(r3)     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            r1.<init>()     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            java.util.List<com.szchoiceway.eventcenter.IniFile$Section> r3 = r8.mSectionList     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
        L_0x002e:
            boolean r4 = r3.hasNext()     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            if (r4 == 0) goto L_0x007f
            java.lang.Object r4 = r3.next()     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            com.szchoiceway.eventcenter.IniFile$Section r4 = (com.szchoiceway.eventcenter.IniFile.Section) r4     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            java.lang.String r5 = r4.mSectionName     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            r1.append(r5)     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            r1.append(r0)     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            java.util.HashMap<java.lang.String, java.lang.String> r4 = r4.mKeyMap     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            java.util.Set r4 = r4.entrySet()     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
        L_0x004c:
            boolean r5 = r4.hasNext()     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            if (r5 == 0) goto L_0x002e
            java.lang.Object r5 = r4.next()     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            r6.<init>()     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            java.lang.Object r7 = r5.getKey()     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            r6.append(r7)     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            java.lang.String r7 = "="
            r6.append(r7)     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            java.lang.Object r5 = r5.getValue()     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            r6.append(r5)     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            java.lang.String r5 = r6.toString()     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            r1.append(r5)     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            r1.append(r0)     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            goto L_0x004c
        L_0x007f:
            java.lang.String r0 = r1.toString()     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            java.lang.String r1 = "IniFile"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            r3.<init>()     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            java.lang.String r4 = "save: content = "
            r3.append(r4)     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            r3.append(r0)     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            android.util.Log.i(r1, r3)     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            java.nio.charset.Charset r1 = java.nio.charset.StandardCharsets.UTF_8     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            byte[] r0 = r0.getBytes(r1)     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            r2.write(r0)     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            r0 = 0
            r2.write(r0)     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            r2.close()     // Catch:{ IOException -> 0x00ae, all -> 0x00ab }
            r1 = r2
            goto L_0x00b1
        L_0x00ab:
            r0 = move-exception
            r1 = r2
            goto L_0x00c8
        L_0x00ae:
            r0 = move-exception
            r1 = r2
            goto L_0x00ba
        L_0x00b1:
            if (r1 == 0) goto L_0x00c7
            r1.close()     // Catch:{ IOException -> 0x00c3 }
            goto L_0x00c7
        L_0x00b7:
            r0 = move-exception
            goto L_0x00c8
        L_0x00b9:
            r0 = move-exception
        L_0x00ba:
            r0.printStackTrace()     // Catch:{ all -> 0x00b7 }
            if (r1 == 0) goto L_0x00c7
            r1.close()     // Catch:{ IOException -> 0x00c3 }
            goto L_0x00c7
        L_0x00c3:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00c7:
            return
        L_0x00c8:
            if (r1 == 0) goto L_0x00d2
            r1.close()     // Catch:{ IOException -> 0x00ce }
            goto L_0x00d2
        L_0x00ce:
            r1 = move-exception
            r1.printStackTrace()
        L_0x00d2:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.eventcenter.IniFile.save():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x003f A[SYNTHETIC, Splitter:B:20:0x003f] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x004e A[SYNTHETIC, Splitter:B:28:0x004e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void clearConfig() {
        /*
            r5 = this;
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x0036, all -> 0x0031 }
            java.lang.String r2 = r5.mPath     // Catch:{ Exception -> 0x0036, all -> 0x0031 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x0036, all -> 0x0031 }
            boolean r1 = r1.exists()     // Catch:{ Exception -> 0x0036, all -> 0x0031 }
            if (r1 == 0) goto L_0x002b
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch:{ Exception -> 0x0036, all -> 0x0031 }
            java.lang.String r2 = r5.mPath     // Catch:{ Exception -> 0x0036, all -> 0x0031 }
            java.lang.String r3 = "rw"
            r1.<init>(r2, r3)     // Catch:{ Exception -> 0x0036, all -> 0x0031 }
            r2 = 0
            r1.seek(r2)     // Catch:{ Exception -> 0x0029 }
            r0 = 1048576(0x100000, float:1.469368E-39)
            byte[] r2 = new byte[r0]     // Catch:{ Exception -> 0x0029 }
            r3 = 0
            r1.write(r2, r3, r0)     // Catch:{ Exception -> 0x0029 }
            r1.close()     // Catch:{ Exception -> 0x0029 }
            r0 = r1
            goto L_0x002b
        L_0x0029:
            r0 = move-exception
            goto L_0x003a
        L_0x002b:
            if (r0 == 0) goto L_0x0047
            r0.close()     // Catch:{ IOException -> 0x0043 }
            goto L_0x0047
        L_0x0031:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L_0x004c
        L_0x0036:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L_0x003a:
            r0.printStackTrace()     // Catch:{ all -> 0x004b }
            if (r1 == 0) goto L_0x0047
            r1.close()     // Catch:{ IOException -> 0x0043 }
            goto L_0x0047
        L_0x0043:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0047:
            r5.clearSectionList()
            return
        L_0x004b:
            r0 = move-exception
        L_0x004c:
            if (r1 == 0) goto L_0x0056
            r1.close()     // Catch:{ IOException -> 0x0052 }
            goto L_0x0056
        L_0x0052:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0056:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.eventcenter.IniFile.clearConfig():void");
    }
}
