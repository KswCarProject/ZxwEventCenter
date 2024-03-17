package com.szchoiceway.eventcenter;

import android.content.Context;
import android.util.Log;
import com.example.mylibrary.BuildConfig;
import java.io.File;

public class ConfigUtil {
    private static final String TAG = "ConfigUtil";
    private String mConfigPath = "/dev/block/by-name/privdata2";
    private EventService mContext;

    public ConfigUtil(Context context) {
        this.mContext = (EventService) context;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00c8, code lost:
        if (r11 != 160) goto L_0x0118;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00d7, code lost:
        if (r11 != 240) goto L_0x00e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00e4, code lost:
        if (r11 != 240) goto L_0x00e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00e6, code lost:
        r8 = true;
        r11 = com.szchoiceway.eventcenter.EventUtils.HANDLER_AUTO_RUN_GPS;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00ea, code lost:
        r8 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00f8, code lost:
        if (r11 != 240) goto L_0x00e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0107, code lost:
        if (r11 != 240) goto L_0x00e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0116, code lost:
        if (r11 != 160) goto L_0x0118;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0118, code lost:
        r8 = true;
        r11 = 160;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x011b, code lost:
        r15 = r1.get("zxw_Config", "zxw_no_debug", "0");
        r16 = "zxw_no_debug";
        r7 = r0.mContext.mSysProviderOpt.getRecordValue(com.szchoiceway.eventcenter.SysProviderOpt.KSW_FACTORY_NO_DEBUG, "1");
        r14 = new java.lang.StringBuilder();
        r17 = "ScreenType";
        r14.append("localSwitch = ");
        r14.append(r15);
        r14.append(", userSwitch = ");
        r14.append(r7);
        android.util.Log.d(TAG, r14.toString());
        r9 = r15.equals(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x014f, code lost:
        if (r9 != false) goto L_0x0152;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0151, code lost:
        r15 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0152, code lost:
        r9 = !r9;
        r7 = r1.get("zxw_Config", "lt9211", "0");
        r18 = "lt9211";
        r19 = r15;
        r4 = r0.mContext.mSysProviderOpt.getRecordValue(com.szchoiceway.eventcenter.SysProviderOpt.KSW_IS_9211_DEVICE, "0");
        r14 = r4.equals(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x016c, code lost:
        if (r14 != false) goto L_0x016f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x016e, code lost:
        r7 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x016f, code lost:
        r14 = !r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0171, code lost:
        if (r8 == false) goto L_0x0180;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0173, code lost:
        android.provider.Settings.Secure.putInt(r0.mContext.getContentResolver(), "display_density_forced", r11);
        r15 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0180, code lost:
        r15 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0181, code lost:
        android.util.Log.d(TAG, "changeDeviceType = " + r5 + ", changeScreenType = " + r12 + ", changeUserSwitch = " + r9 + ", change9211 = " + r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01ad, code lost:
        if (r5 != false) goto L_0x01b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01af, code lost:
        if (r12 != false) goto L_0x01b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x01b1, code lost:
        if (r9 != false) goto L_0x01b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x01b3, code lost:
        if (r14 == false) goto L_0x01b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01b8, code lost:
        r1.clearConfig();
        r1.set("zxw_Config", "DeviceType", "1");
        r1.set("zxw_Config", r17, r10);
        r1.set("zxw_Config", r16, r19);
        r1.set("zxw_Config", r18, r7);
        r1.save();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:?, code lost:
        return r15;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean checkConfig() {
        /*
            r20 = this;
            r0 = r20
            com.szchoiceway.eventcenter.IniFile r1 = new com.szchoiceway.eventcenter.IniFile
            java.io.File r2 = new java.io.File
            java.lang.String r3 = r0.mConfigPath
            r2.<init>(r3)
            r1.<init>((java.io.File) r2)
            java.lang.String r2 = "zxw_Config"
            java.lang.String r3 = "DeviceType"
            java.lang.String r4 = "0"
            java.lang.String r5 = r1.get(r2, r3, r4)
            java.lang.String r6 = "1"
            boolean r5 = r6.equals(r5)
            r7 = 1
            r5 = r5 ^ r7
            com.szchoiceway.eventcenter.EventService r8 = r0.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r8 = r8.mSysProviderOpt
            java.lang.String r9 = "RESOLUTION"
            java.lang.String r10 = ""
            java.lang.String r8 = r8.getRecordValue(r9, r10)
            java.lang.String r9 = "ScreenType"
            java.lang.String r10 = r1.get(r2, r9, r6)
            com.szchoiceway.eventcenter.EventService r11 = r0.mContext
            android.content.res.Resources r11 = r11.getResources()
            android.util.DisplayMetrics r11 = r11.getDisplayMetrics()
            int r11 = r11.densityDpi
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "checkConfig screenResolution = "
            r12.append(r13)
            r12.append(r8)
            java.lang.String r13 = ", localScreenType = "
            r12.append(r13)
            r12.append(r10)
            java.lang.String r13 = ", densityDpi = "
            r12.append(r13)
            r12.append(r11)
            java.lang.String r12 = r12.toString()
            java.lang.String r13 = "ConfigUtil"
            android.util.Log.d(r13, r12)
            r8.hashCode()
            r12 = -1
            int r14 = r8.hashCode()
            switch(r14) {
                case -1719907571: goto L_0x00a7;
                case -1719904874: goto L_0x009c;
                case -17630391: goto L_0x0091;
                case 25878691: goto L_0x0086;
                case 237066427: goto L_0x007b;
                case 631973215: goto L_0x0070;
                default: goto L_0x006f;
            }
        L_0x006f:
            goto L_0x00b1
        L_0x0070:
            java.lang.String r14 = "1024x600"
            boolean r8 = r8.equals(r14)
            if (r8 != 0) goto L_0x0079
            goto L_0x00b1
        L_0x0079:
            r12 = 5
            goto L_0x00b1
        L_0x007b:
            java.lang.String r14 = "720x1920_1"
            boolean r8 = r8.equals(r14)
            if (r8 != 0) goto L_0x0084
            goto L_0x00b1
        L_0x0084:
            r12 = 4
            goto L_0x00b1
        L_0x0086:
            java.lang.String r14 = "1920x720"
            boolean r8 = r8.equals(r14)
            if (r8 != 0) goto L_0x008f
            goto L_0x00b1
        L_0x008f:
            r12 = 3
            goto L_0x00b1
        L_0x0091:
            java.lang.String r14 = "720x1920"
            boolean r8 = r8.equals(r14)
            if (r8 != 0) goto L_0x009a
            goto L_0x00b1
        L_0x009a:
            r12 = 2
            goto L_0x00b1
        L_0x009c:
            java.lang.String r14 = "1280x720"
            boolean r8 = r8.equals(r14)
            if (r8 != 0) goto L_0x00a5
            goto L_0x00b1
        L_0x00a5:
            r12 = 1
            goto L_0x00b1
        L_0x00a7:
            java.lang.String r14 = "1280x480"
            boolean r8 = r8.equals(r14)
            if (r8 != 0) goto L_0x00b0
            goto L_0x00b1
        L_0x00b0:
            r12 = 0
        L_0x00b1:
            r8 = 160(0xa0, float:2.24E-43)
            r14 = 240(0xf0, float:3.36E-43)
            switch(r12) {
                case 0: goto L_0x010a;
                case 1: goto L_0x00fb;
                case 2: goto L_0x00ec;
                case 3: goto L_0x00da;
                case 4: goto L_0x00cb;
                case 5: goto L_0x00bc;
                default: goto L_0x00b8;
            }
        L_0x00b8:
            r8 = 0
            r12 = 0
            goto L_0x011b
        L_0x00bc:
            java.lang.String r12 = "4"
            boolean r14 = r12.equals(r10)
            if (r14 != 0) goto L_0x00c7
            r10 = r12
            r12 = 1
            goto L_0x00c8
        L_0x00c7:
            r12 = 0
        L_0x00c8:
            if (r11 == r8) goto L_0x00ea
            goto L_0x0118
        L_0x00cb:
            java.lang.String r8 = "7"
            boolean r12 = r8.equals(r10)
            if (r12 != 0) goto L_0x00d6
            r10 = r8
            r12 = 1
            goto L_0x00d7
        L_0x00d6:
            r12 = 0
        L_0x00d7:
            if (r11 == r14) goto L_0x00ea
            goto L_0x00fa
        L_0x00da:
            boolean r8 = r6.equals(r10)
            if (r8 != 0) goto L_0x00e3
            r10 = r6
            r12 = 1
            goto L_0x00e4
        L_0x00e3:
            r12 = 0
        L_0x00e4:
            if (r11 == r14) goto L_0x00ea
        L_0x00e6:
            r8 = 1
            r11 = 240(0xf0, float:3.36E-43)
            goto L_0x011b
        L_0x00ea:
            r8 = 0
            goto L_0x011b
        L_0x00ec:
            java.lang.String r8 = "3"
            boolean r12 = r8.equals(r10)
            if (r12 != 0) goto L_0x00f7
            r10 = r8
            r12 = 1
            goto L_0x00f8
        L_0x00f7:
            r12 = 0
        L_0x00f8:
            if (r11 == r14) goto L_0x00ea
        L_0x00fa:
            goto L_0x00e6
        L_0x00fb:
            java.lang.String r8 = "5"
            boolean r12 = r8.equals(r10)
            if (r12 != 0) goto L_0x0106
            r10 = r8
            r12 = 1
            goto L_0x0107
        L_0x0106:
            r12 = 0
        L_0x0107:
            if (r11 == r14) goto L_0x00ea
            goto L_0x00e6
        L_0x010a:
            java.lang.String r12 = "2"
            boolean r14 = r12.equals(r10)
            if (r14 != 0) goto L_0x0115
            r10 = r12
            r12 = 1
            goto L_0x0116
        L_0x0115:
            r12 = 0
        L_0x0116:
            if (r11 == r8) goto L_0x00ea
        L_0x0118:
            r8 = 1
            r11 = 160(0xa0, float:2.24E-43)
        L_0x011b:
            java.lang.String r14 = "zxw_no_debug"
            java.lang.String r15 = r1.get(r2, r14, r4)
            com.szchoiceway.eventcenter.EventService r7 = r0.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r7 = r7.mSysProviderOpt
            r16 = r14
            java.lang.String r14 = "KSW_FACTORY_NO_DEBUG"
            java.lang.String r7 = r7.getRecordValue(r14, r6)
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r17 = r9
            java.lang.String r9 = "localSwitch = "
            r14.append(r9)
            r14.append(r15)
            java.lang.String r9 = ", userSwitch = "
            r14.append(r9)
            r14.append(r7)
            java.lang.String r9 = r14.toString()
            android.util.Log.d(r13, r9)
            boolean r9 = r15.equals(r7)
            if (r9 != 0) goto L_0x0152
            r15 = r7
        L_0x0152:
            r7 = 1
            r9 = r9 ^ r7
            java.lang.String r14 = "lt9211"
            java.lang.String r7 = r1.get(r2, r14, r4)
            r18 = r14
            com.szchoiceway.eventcenter.EventService r14 = r0.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r14 = r14.mSysProviderOpt
            r19 = r15
            java.lang.String r15 = "KSW_IS_9211_DEVICE"
            java.lang.String r4 = r14.getRecordValue(r15, r4)
            boolean r14 = r4.equals(r7)
            if (r14 != 0) goto L_0x016f
            r7 = r4
        L_0x016f:
            r4 = 1
            r14 = r14 ^ r4
            if (r8 == 0) goto L_0x0180
            com.szchoiceway.eventcenter.EventService r8 = r0.mContext
            android.content.ContentResolver r8 = r8.getContentResolver()
            java.lang.String r15 = "display_density_forced"
            android.provider.Settings.Secure.putInt(r8, r15, r11)
            r15 = 1
            goto L_0x0181
        L_0x0180:
            r15 = 0
        L_0x0181:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r11 = "changeDeviceType = "
            r8.append(r11)
            r8.append(r5)
            java.lang.String r11 = ", changeScreenType = "
            r8.append(r11)
            r8.append(r12)
            java.lang.String r11 = ", changeUserSwitch = "
            r8.append(r11)
            r8.append(r9)
            java.lang.String r11 = ", change9211 = "
            r8.append(r11)
            r8.append(r14)
            java.lang.String r8 = r8.toString()
            android.util.Log.d(r13, r8)
            if (r5 != 0) goto L_0x01b8
            if (r12 != 0) goto L_0x01b8
            if (r9 != 0) goto L_0x01b8
            if (r14 == 0) goto L_0x01b6
            goto L_0x01b8
        L_0x01b6:
            r7 = r15
            goto L_0x01d3
        L_0x01b8:
            r1.clearConfig()
            r1.set(r2, r3, r6)
            r3 = r17
            r1.set(r2, r3, r10)
            r3 = r16
            r15 = r19
            r1.set(r2, r3, r15)
            r3 = r18
            r1.set(r2, r3, r7)
            r1.save()
            r7 = 1
        L_0x01d3:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.eventcenter.ConfigUtil.checkConfig():boolean");
    }

    public void switchDebug(String str) {
        Log.d(TAG, "userDebug value = " + str);
        EventService eventService = this.mContext;
        eventService.runCmd("chmod 777 " + this.mConfigPath);
        do {
        } while (this.mContext.getCmdResult());
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

    public void switch9211(String str) {
        Log.d(TAG, "userDebug value = " + str);
        EventService eventService = this.mContext;
        eventService.runCmd("chmod 777 " + this.mConfigPath);
        do {
        } while (this.mContext.getCmdResult());
        IniFile iniFile = new IniFile(new File(this.mConfigPath));
        String str2 = iniFile.get("zxw_Config", "lt9211", BuildConfig.FLAVOR);
        Log.d(TAG, "local9211Value = " + str2);
        String recordValue = this.mContext.mSysProviderOpt.getRecordValue(SysProviderOpt.KSW_IS_9211_DEVICE, "0");
        Log.d(TAG, "data9211 = " + recordValue);
        String str3 = iniFile.get("zxw_Config", "DeviceType", "0");
        String str4 = iniFile.get("zxw_Config", "ScreenType", "1");
        String str5 = iniFile.get("zxw_Config", "zxw_no_debug", "0");
        if (!str.equals(str2)) {
            Log.d(TAG, "update logcat value = " + str);
            iniFile.clearConfig();
            iniFile.set("zxw_Config", "DeviceType", str3);
            iniFile.set("zxw_Config", "ScreenType", str4);
            iniFile.set("zxw_Config", "zxw_no_debug", str5);
            iniFile.set("zxw_Config", "lt9211", str);
            iniFile.save();
            this.mContext.mSysProviderOpt.updateRecord(SysProviderOpt.KSW_IS_9211_DEVICE, str);
            this.mContext.rebootService();
        }
    }
}
