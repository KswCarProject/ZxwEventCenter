package com.szchoiceway.eventcenter;

import android.content.Context;
import android.os.SystemProperties;
import android.util.Log;
import com.example.mylibrary.BuildConfig;
import java.io.File;

public class ConfigUtil2 {
    private static final String TAG = "ConfigUtil2";
    private String mConfigPath = "/dev/block/by-name/privdata2";
    private EventService mContext;

    public ConfigUtil2(Context context) {
        this.mContext = (EventService) context;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x04a0, code lost:
        r2 = r0;
        r12 = r9;
        r14 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x04a4, code lost:
        r6 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:260:0x06ee, code lost:
        if (r11 == false) goto L_0x0725;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:261:0x06f0, code lost:
        r1 = r30;
        android.util.Log.d(r1, "checkConfig: changeDensityDpi = " + r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:263:?, code lost:
        java.lang.Runtime.getRuntime().exec("wm density " + r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:264:0x071f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:265:0x0720, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:266:0x0725, code lost:
        r1 = r30;
     */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x0440  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x0446  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x044b  */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x044f  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x0458  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean checkConfig() {
        /*
            r69 = this;
            r1 = r69
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r0 = r0.mSysProviderOpt
            java.lang.String r2 = "RESOLUTION"
            java.lang.String r3 = "1920x720"
            java.lang.String r0 = r0.getRecordValue(r2, r3)
            r1.checkUIResolution(r0)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r2 = r2.mSysProviderOpt
            java.lang.String r3 = "KSW_DATA_DECODER_V3"
            r4 = 0
            boolean r2 = r2.getRecordBoolean(r3, r4)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "resolution = "
            r3.append(r5)
            r3.append(r0)
            java.lang.String r5 = ", isDecoderV3 = "
            r3.append(r5)
            r3.append(r2)
            java.lang.String r3 = r3.toString()
            java.lang.String r5 = "ConfigUtil2"
            android.util.Log.d(r5, r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r6 = "chmod 777 "
            r3.append(r6)
            java.lang.String r6 = r1.mConfigPath
            r3.append(r6)
            java.lang.String r3 = r3.toString()
            r1.runCmd(r3)
        L_0x0050:
            boolean r3 = r69.getCmdResult()
            if (r3 != 0) goto L_0x0057
            goto L_0x0050
        L_0x0057:
            com.szchoiceway.eventcenter.IniFile r3 = new com.szchoiceway.eventcenter.IniFile
            java.io.File r6 = new java.io.File
            java.lang.String r7 = r1.mConfigPath
            r6.<init>(r7)
            r3.<init>((java.io.File) r6)
            java.lang.String r6 = "zxw_Config"
            java.lang.String r7 = "ScreenChip"
            java.lang.String r8 = "0"
            java.lang.String r9 = r3.get(r6, r7, r8)
            int r9 = java.lang.Integer.parseInt(r9)
            java.lang.String r10 = "ScreenWidth"
            java.lang.String r11 = r3.get(r6, r10, r8)
            int r11 = java.lang.Integer.parseInt(r11)
            java.lang.String r12 = "ScreenHeight"
            java.lang.String r13 = r3.get(r6, r12, r8)
            int r13 = java.lang.Integer.parseInt(r13)
            java.lang.String r14 = "TouchSwap"
            java.lang.String r14 = r3.get(r6, r14, r8)
            int r14 = java.lang.Integer.parseInt(r14)
            java.lang.String r15 = "TouchMirrorWidth"
            java.lang.String r15 = r3.get(r6, r15, r8)
            int r15 = java.lang.Integer.parseInt(r15)
            java.lang.String r4 = "TouchType"
            java.lang.String r16 = r3.get(r6, r4, r8)
            r17 = r15
            int r15 = java.lang.Integer.parseInt(r16)
            r16 = r15
            com.szchoiceway.eventcenter.EventService r15 = r1.mContext
            android.content.res.Resources r15 = r15.getResources()
            android.util.DisplayMetrics r15 = r15.getDisplayMetrics()
            int r15 = r15.densityDpi
            r18 = r14
            java.lang.String r14 = "persist.sys.status_bar_height_landscape"
            r19 = r4
            r4 = 90
            int r14 = android.os.SystemProperties.getInt(r14, r4)
            java.lang.String r4 = "persist.sys.show_navigationbar"
            r21 = r12
            java.lang.String r12 = ""
            r22 = r10
            java.lang.String r10 = android.os.SystemProperties.get(r4, r12)
            r23 = r7
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r24 = r12
            java.lang.String r12 = "checkConfig: width = "
            r7.append(r12)
            r7.append(r11)
            java.lang.String r12 = ", height = "
            r7.append(r12)
            r7.append(r13)
            java.lang.String r12 = ", densityDpi = "
            r7.append(r12)
            r7.append(r15)
            java.lang.String r12 = ", statusHeight = "
            r7.append(r12)
            r7.append(r14)
            java.lang.String r12 = ", showNaviBar = "
            r7.append(r12)
            r7.append(r10)
            java.lang.String r7 = r7.toString()
            android.util.Log.d(r5, r7)
            java.lang.String r7 = "zxw_no_debug"
            java.lang.String r12 = r3.get(r6, r7, r8)
            r25 = r7
            com.szchoiceway.eventcenter.EventService r7 = r1.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r7 = r7.mSysProviderOpt
            java.lang.String r1 = "KSW_FACTORY_NO_DEBUG"
            r26 = r6
            java.lang.String r6 = "1"
            java.lang.String r1 = r7.getRecordValue(r1, r6)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r27 = r6
            java.lang.String r6 = "localSwitch = "
            r7.append(r6)
            r7.append(r12)
            java.lang.String r6 = ", userSwitch = "
            r7.append(r6)
            r7.append(r1)
            java.lang.String r6 = r7.toString()
            android.util.Log.d(r5, r6)
            boolean r6 = r12.equals(r1)
            if (r6 != 0) goto L_0x013e
            r12 = r1
        L_0x013e:
            r1 = 1
            r6 = r6 ^ r1
            r0.hashCode()
            int r28 = r0.hashCode()
            switch(r28) {
                case -1719907571: goto L_0x01b6;
                case -1719904874: goto L_0x01aa;
                case -900378731: goto L_0x019e;
                case -59415976: goto L_0x0191;
                case -17630391: goto L_0x0184;
                case 25878691: goto L_0x0177;
                case 237066427: goto L_0x016a;
                case 631973215: goto L_0x015d;
                case 885347805: goto L_0x014e;
                default: goto L_0x014a;
            }
        L_0x014a:
            r29 = -1
            goto L_0x01c1
        L_0x014e:
            java.lang.String r1 = "1560x700"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0157
            goto L_0x014a
        L_0x0157:
            r0 = 8
            r29 = 8
            goto L_0x01c1
        L_0x015d:
            java.lang.String r1 = "1024x600"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0166
            goto L_0x014a
        L_0x0166:
            r0 = 7
            r29 = 7
            goto L_0x01c1
        L_0x016a:
            java.lang.String r1 = "720x1920_1"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0173
            goto L_0x014a
        L_0x0173:
            r0 = 6
            r29 = 6
            goto L_0x01c1
        L_0x0177:
            java.lang.String r1 = "1920x720"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0180
            goto L_0x014a
        L_0x0180:
            r0 = 5
            r29 = 5
            goto L_0x01c1
        L_0x0184:
            java.lang.String r1 = "720x1920"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x018d
            goto L_0x014a
        L_0x018d:
            r0 = 4
            r29 = 4
            goto L_0x01c1
        L_0x0191:
            java.lang.String r1 = "1440x540"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x019a
            goto L_0x014a
        L_0x019a:
            r0 = 3
            r29 = 3
            goto L_0x01c1
        L_0x019e:
            java.lang.String r1 = "1920x720_1"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x01a7
            goto L_0x014a
        L_0x01a7:
            r29 = 2
            goto L_0x01c1
        L_0x01aa:
            java.lang.String r1 = "1280x720"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x01b3
            goto L_0x014a
        L_0x01b3:
            r29 = 1
            goto L_0x01c1
        L_0x01b6:
            java.lang.String r1 = "1280x480"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x01bf
            goto L_0x014a
        L_0x01bf:
            r29 = 0
        L_0x01c1:
            java.lang.String r0 = "4"
            java.lang.String r1 = "1920"
            java.lang.String r7 = "CameraConfig"
            r30 = r5
            java.lang.String r5 = "TouchHeight"
            r31 = r1
            java.lang.String r1 = "TouchWidth"
            r32 = r12
            java.lang.String r12 = "720"
            r33 = r12
            switch(r29) {
                case 0: goto L_0x0648;
                case 1: goto L_0x05c4;
                case 2: goto L_0x052b;
                case 3: goto L_0x04a8;
                case 4: goto L_0x040f;
                case 5: goto L_0x036d;
                case 6: goto L_0x02dd;
                case 7: goto L_0x025d;
                case 8: goto L_0x01df;
                default: goto L_0x01d8;
            }
        L_0x01d8:
            r6 = r24
            r2 = 0
            r11 = 0
            r12 = 0
            goto L_0x06ee
        L_0x01df:
            if (r2 == 0) goto L_0x01e4
            r2 = 10
            goto L_0x01e5
        L_0x01e4:
            r2 = 2
        L_0x01e5:
            r12 = 1560(0x618, float:2.186E-42)
            if (r11 != r12) goto L_0x01f2
            r11 = 700(0x2bc, float:9.81E-43)
            if (r13 != r11) goto L_0x01f2
            if (r9 == r2) goto L_0x01f0
            goto L_0x01f2
        L_0x01f0:
            r2 = 0
            goto L_0x01f4
        L_0x01f2:
            r9 = r2
            r2 = 1
        L_0x01f4:
            r11 = 240(0xf0, float:3.36E-43)
            if (r15 == r11) goto L_0x01fc
            r11 = 1
            r15 = 240(0xf0, float:3.36E-43)
            goto L_0x01fd
        L_0x01fc:
            r11 = 0
        L_0x01fd:
            r12 = 70
            if (r14 == r12) goto L_0x0205
            r14 = 70
            r12 = 1
            goto L_0x0206
        L_0x0205:
            r12 = 0
        L_0x0206:
            boolean r10 = r8.equals(r10)
            if (r10 != 0) goto L_0x0210
            android.os.SystemProperties.set(r4, r8)
            r12 = 1
        L_0x0210:
            if (r2 != 0) goto L_0x0214
            if (r6 == 0) goto L_0x04a4
        L_0x0214:
            r3.clearConfig()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r9)
            r6 = r24
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            r8 = r23
            r9 = r26
            r3.set(r9, r8, r4)
            java.lang.String r4 = "1560"
            r8 = r22
            r3.set(r9, r8, r4)
            java.lang.String r4 = "700"
            r8 = r21
            r3.set(r9, r8, r4)
            r4 = r19
            r3.set(r9, r4, r0)
            java.lang.String r0 = "1560"
            r3.set(r9, r1, r0)
            java.lang.String r0 = "700"
            r3.set(r9, r5, r0)
            r0 = r27
            r3.set(r9, r7, r0)
            r1 = r25
            r0 = r32
            r3.set(r9, r1, r0)
            r3.save()
            goto L_0x06ee
        L_0x025d:
            r2 = r19
            r12 = r21
            r34 = r27
            r21 = r5
            r19 = r7
            r7 = r22
            r5 = r23
            r23 = r0
            r22 = r1
            r1 = r26
            r0 = 1024(0x400, float:1.435E-42)
            if (r11 != r0) goto L_0x027f
            r0 = 600(0x258, float:8.41E-43)
            if (r13 != r0) goto L_0x027f
            r0 = 2
            if (r9 == r0) goto L_0x027d
            goto L_0x027f
        L_0x027d:
            r0 = 0
            goto L_0x0280
        L_0x027f:
            r0 = 1
        L_0x0280:
            r9 = 200(0xc8, float:2.8E-43)
            if (r15 == r9) goto L_0x0288
            r15 = 200(0xc8, float:2.8E-43)
            r9 = 1
            goto L_0x0289
        L_0x0288:
            r9 = 0
        L_0x0289:
            r11 = 60
            if (r14 == r11) goto L_0x0291
            r14 = 60
            r11 = 1
            goto L_0x0292
        L_0x0291:
            r11 = 0
        L_0x0292:
            boolean r10 = r8.equals(r10)
            if (r10 != 0) goto L_0x029c
            android.os.SystemProperties.set(r4, r8)
            r11 = 1
        L_0x029c:
            if (r0 != 0) goto L_0x02a0
            if (r6 == 0) goto L_0x02d6
        L_0x02a0:
            r3.clearConfig()
            java.lang.String r4 = "2"
            r3.set(r1, r5, r4)
            java.lang.String r4 = "1024"
            r3.set(r1, r7, r4)
            java.lang.String r4 = "600"
            r3.set(r1, r12, r4)
            r4 = r23
            r3.set(r1, r2, r4)
            java.lang.String r2 = "1024"
            r4 = r22
            r3.set(r1, r4, r2)
            java.lang.String r2 = "600"
            r4 = r21
            r3.set(r1, r4, r2)
            r2 = r19
            r4 = r34
            r3.set(r1, r2, r4)
            r4 = r25
            r2 = r32
            r3.set(r1, r4, r2)
            r3.save()
        L_0x02d6:
            r2 = r0
            r12 = r11
            r6 = r24
            r11 = r9
            goto L_0x06ee
        L_0x02dd:
            r37 = r1
            r36 = r5
            r35 = r7
            r2 = r19
            r12 = r21
            r7 = r22
            r5 = r23
            r38 = r25
            r1 = r26
            r39 = r27
            r23 = r0
            r0 = 720(0x2d0, float:1.009E-42)
            if (r11 != r0) goto L_0x030a
            r0 = 1920(0x780, float:2.69E-42)
            if (r13 != r0) goto L_0x030a
            r0 = 7
            if (r9 != r0) goto L_0x030a
            r0 = r18
            r9 = 1
            if (r0 != r9) goto L_0x030a
            r0 = r17
            if (r0 == r9) goto L_0x0308
            goto L_0x030a
        L_0x0308:
            r0 = 0
            goto L_0x030b
        L_0x030a:
            r0 = 1
        L_0x030b:
            r9 = 240(0xf0, float:3.36E-43)
            if (r15 == r9) goto L_0x0315
            r9 = 90
            r11 = 1
            r15 = 240(0xf0, float:3.36E-43)
            goto L_0x0318
        L_0x0315:
            r9 = 90
            r11 = 0
        L_0x0318:
            if (r14 == r9) goto L_0x031e
            r9 = 1
            r20 = 90
            goto L_0x0321
        L_0x031e:
            r20 = r14
            r9 = 0
        L_0x0321:
            boolean r10 = r8.equals(r10)
            if (r10 != 0) goto L_0x032b
            android.os.SystemProperties.set(r4, r8)
            r9 = 1
        L_0x032b:
            if (r0 != 0) goto L_0x032f
            if (r6 == 0) goto L_0x04a0
        L_0x032f:
            r3.clearConfig()
            java.lang.String r4 = "7"
            r3.set(r1, r5, r4)
            r4 = r33
            r3.set(r1, r7, r4)
            r5 = r31
            r3.set(r1, r12, r5)
            r6 = r23
            r3.set(r1, r2, r6)
            r2 = r37
            r3.set(r1, r2, r4)
            r2 = r36
            r3.set(r1, r2, r5)
            java.lang.String r2 = "TouchSwap"
            r4 = r39
            r3.set(r1, r2, r4)
            java.lang.String r2 = "TouchMirrorWidth"
            r3.set(r1, r2, r4)
            r2 = r35
            r3.set(r1, r2, r4)
            r2 = r32
            r4 = r38
            r3.set(r1, r4, r2)
            r3.save()
            goto L_0x04a0
        L_0x036d:
            r41 = r0
            r44 = r1
            r0 = r2
            r43 = r5
            r42 = r7
            r7 = r22
            r5 = r23
            r2 = r24
            r46 = r25
            r1 = r26
            r47 = r27
            r12 = r31
            r40 = r32
            r45 = r33
            if (r0 == 0) goto L_0x038f
            r0 = 8
            r22 = r7
            goto L_0x0392
        L_0x038f:
            r22 = r7
            r0 = 2
        L_0x0392:
            r7 = 1920(0x780, float:2.69E-42)
            if (r11 != r7) goto L_0x039f
            r7 = 720(0x2d0, float:1.009E-42)
            if (r13 != r7) goto L_0x039f
            if (r9 == r0) goto L_0x039d
            goto L_0x039f
        L_0x039d:
            r0 = 0
            goto L_0x03a1
        L_0x039f:
            r9 = r0
            r0 = 1
        L_0x03a1:
            r7 = 240(0xf0, float:3.36E-43)
            if (r15 == r7) goto L_0x03ab
            r7 = 90
            r11 = 1
            r15 = 240(0xf0, float:3.36E-43)
            goto L_0x03ae
        L_0x03ab:
            r7 = 90
            r11 = 0
        L_0x03ae:
            if (r14 == r7) goto L_0x03b4
            r7 = 1
            r20 = 90
            goto L_0x03b7
        L_0x03b4:
            r20 = r14
            r7 = 0
        L_0x03b7:
            boolean r10 = r8.equals(r10)
            if (r10 != 0) goto L_0x03c1
            android.os.SystemProperties.set(r4, r8)
            r7 = 1
        L_0x03c1:
            if (r0 != 0) goto L_0x03c5
            if (r6 == 0) goto L_0x0408
        L_0x03c5:
            r3.clearConfig()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r9)
            r4.append(r2)
            java.lang.String r4 = r4.toString()
            r3.set(r1, r5, r4)
            r4 = r22
            r3.set(r1, r4, r12)
            r5 = r21
            r4 = r45
            r3.set(r1, r5, r4)
            r6 = r19
            r5 = r41
            r3.set(r1, r6, r5)
            r5 = r44
            r3.set(r1, r5, r12)
            r5 = r43
            r3.set(r1, r5, r4)
            r4 = r42
            r5 = r47
            r3.set(r1, r4, r5)
            r4 = r40
            r5 = r46
            r3.set(r1, r5, r4)
            r3.save()
        L_0x0408:
            r6 = r2
            r12 = r7
            r14 = r20
            r2 = r0
            goto L_0x06ee
        L_0x040f:
            r50 = r1
            r49 = r5
            r48 = r7
            r7 = r17
            r52 = r19
            r12 = r22
            r5 = r23
            r51 = r25
            r1 = r26
            r53 = r27
            r23 = r0
            r0 = r18
            r2 = 720(0x2d0, float:1.009E-42)
            if (r11 != r2) goto L_0x043a
            r2 = 1920(0x780, float:2.69E-42)
            if (r13 != r2) goto L_0x043a
            r2 = 3
            if (r9 != r2) goto L_0x043a
            r2 = 1
            if (r0 != r2) goto L_0x043b
            if (r7 == r2) goto L_0x0438
            goto L_0x043b
        L_0x0438:
            r0 = 0
            goto L_0x043c
        L_0x043a:
            r2 = 1
        L_0x043b:
            r0 = 1
        L_0x043c:
            r7 = 240(0xf0, float:3.36E-43)
            if (r15 == r7) goto L_0x0446
            r7 = 90
            r11 = 1
            r15 = 240(0xf0, float:3.36E-43)
            goto L_0x0449
        L_0x0446:
            r7 = 90
            r11 = 0
        L_0x0449:
            if (r14 == r7) goto L_0x044f
            r9 = 1
            r20 = 90
            goto L_0x0452
        L_0x044f:
            r20 = r14
            r9 = 0
        L_0x0452:
            boolean r7 = r8.equals(r10)
            if (r7 != 0) goto L_0x045c
            android.os.SystemProperties.set(r4, r8)
            r9 = 1
        L_0x045c:
            if (r0 != 0) goto L_0x0460
            if (r6 == 0) goto L_0x04a0
        L_0x0460:
            r3.clearConfig()
            java.lang.String r4 = "3"
            r3.set(r1, r5, r4)
            r7 = r33
            r3.set(r1, r12, r7)
            r5 = r21
            r4 = r31
            r3.set(r1, r5, r4)
            r5 = r23
            r6 = r52
            r3.set(r1, r6, r5)
            r5 = r50
            r3.set(r1, r5, r7)
            r7 = r49
            r3.set(r1, r7, r4)
            java.lang.String r4 = "TouchSwap"
            r5 = r53
            r3.set(r1, r4, r5)
            java.lang.String r4 = "TouchMirrorWidth"
            r3.set(r1, r4, r5)
            r4 = r48
            r3.set(r1, r4, r5)
            r4 = r32
            r5 = r51
            r3.set(r1, r5, r4)
            r3.save()
        L_0x04a0:
            r2 = r0
            r12 = r9
            r14 = r20
        L_0x04a4:
            r6 = r24
            goto L_0x06ee
        L_0x04a8:
            r12 = r22
            r54 = r25
            r55 = r27
            r22 = r1
            r1 = r26
            r67 = r23
            r23 = r0
            r0 = r21
            r21 = r5
            r5 = r67
            r68 = r19
            r19 = r7
            r7 = r68
            r2 = 1440(0x5a0, float:2.018E-42)
            if (r11 != r2) goto L_0x04d1
            r2 = 540(0x21c, float:7.57E-43)
            if (r13 != r2) goto L_0x04d1
            r2 = 9
            if (r9 == r2) goto L_0x04cf
            goto L_0x04d1
        L_0x04cf:
            r2 = 0
            goto L_0x04d2
        L_0x04d1:
            r2 = 1
        L_0x04d2:
            r9 = 180(0xb4, float:2.52E-43)
            if (r15 == r9) goto L_0x04da
            r15 = 180(0xb4, float:2.52E-43)
            r11 = 1
            goto L_0x04db
        L_0x04da:
            r11 = 0
        L_0x04db:
            r9 = 75
            if (r14 == r9) goto L_0x04e3
            r14 = 75
            r9 = 1
            goto L_0x04e4
        L_0x04e3:
            r9 = 0
        L_0x04e4:
            boolean r10 = r8.equals(r10)
            if (r10 != 0) goto L_0x04ee
            android.os.SystemProperties.set(r4, r8)
            r9 = 1
        L_0x04ee:
            if (r2 != 0) goto L_0x04f2
            if (r6 == 0) goto L_0x0528
        L_0x04f2:
            r3.clearConfig()
            java.lang.String r4 = "9"
            r3.set(r1, r5, r4)
            java.lang.String r4 = "1440"
            r3.set(r1, r12, r4)
            java.lang.String r4 = "540"
            r3.set(r1, r0, r4)
            r0 = r23
            r3.set(r1, r7, r0)
            java.lang.String r0 = "1440"
            r4 = r22
            r3.set(r1, r4, r0)
            java.lang.String r0 = "540"
            r4 = r21
            r3.set(r1, r4, r0)
            r0 = r19
            r4 = r55
            r3.set(r1, r0, r4)
            r0 = r32
            r4 = r54
            r3.set(r1, r4, r0)
            r3.save()
        L_0x0528:
            r12 = r9
            goto L_0x04a4
        L_0x052b:
            r58 = r1
            r57 = r5
            r56 = r7
            r12 = r22
            r5 = r23
            r59 = r25
            r1 = r26
            r60 = r27
            r2 = r31
            r7 = r33
            r0 = 1920(0x780, float:2.69E-42)
            if (r11 != r0) goto L_0x0553
            r0 = 720(0x2d0, float:1.009E-42)
            if (r13 != r0) goto L_0x0553
            r0 = 2
            if (r9 != r0) goto L_0x0553
            r0 = 100
            r11 = r16
            if (r11 == r0) goto L_0x0551
            goto L_0x0553
        L_0x0551:
            r0 = 0
            goto L_0x0555
        L_0x0553:
            r0 = 1
            r9 = 2
        L_0x0555:
            r11 = 240(0xf0, float:3.36E-43)
            if (r15 == r11) goto L_0x055f
            r11 = 1
            r13 = 90
            r15 = 240(0xf0, float:3.36E-43)
            goto L_0x0562
        L_0x055f:
            r11 = 0
            r13 = 90
        L_0x0562:
            if (r14 == r13) goto L_0x0568
            r13 = 1
            r20 = 90
            goto L_0x056b
        L_0x0568:
            r20 = r14
            r13 = 0
        L_0x056b:
            boolean r10 = r8.equals(r10)
            if (r10 != 0) goto L_0x0575
            android.os.SystemProperties.set(r4, r8)
            r13 = 1
        L_0x0575:
            if (r0 != 0) goto L_0x057d
            if (r6 == 0) goto L_0x057a
            goto L_0x057d
        L_0x057a:
            r6 = r24
            goto L_0x05be
        L_0x057d:
            r3.clearConfig()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r9)
            r6 = r24
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            r3.set(r1, r5, r4)
            r3.set(r1, r12, r2)
            r4 = r21
            r3.set(r1, r4, r7)
            java.lang.String r4 = "100"
            r5 = r19
            r3.set(r1, r5, r4)
            r4 = r58
            r3.set(r1, r4, r2)
            r2 = r57
            r3.set(r1, r2, r7)
            r2 = r56
            r4 = r60
            r3.set(r1, r2, r4)
            r2 = r32
            r4 = r59
            r3.set(r1, r4, r2)
            r3.save()
        L_0x05be:
            r2 = r0
            r12 = r13
            r14 = r20
            goto L_0x06ee
        L_0x05c4:
            r62 = r7
            r12 = r22
            r61 = r24
            r63 = r25
            r64 = r27
            r7 = r33
            r22 = r1
            r1 = r26
            r67 = r23
            r23 = r0
            r0 = r21
            r21 = r5
            r5 = r67
            r2 = 1280(0x500, float:1.794E-42)
            if (r11 != r2) goto L_0x05ec
            r2 = 720(0x2d0, float:1.009E-42)
            if (r13 != r2) goto L_0x05ec
            r2 = 2
            if (r9 == r2) goto L_0x05ea
            goto L_0x05ec
        L_0x05ea:
            r2 = 0
            goto L_0x05ed
        L_0x05ec:
            r2 = 1
        L_0x05ed:
            r9 = 240(0xf0, float:3.36E-43)
            if (r15 == r9) goto L_0x05f7
            r9 = 90
            r11 = 1
            r15 = 240(0xf0, float:3.36E-43)
            goto L_0x05fa
        L_0x05f7:
            r9 = 90
            r11 = 0
        L_0x05fa:
            if (r14 == r9) goto L_0x05fe
            r13 = 1
            goto L_0x0600
        L_0x05fe:
            r9 = r14
            r13 = 0
        L_0x0600:
            boolean r10 = r8.equals(r10)
            if (r10 != 0) goto L_0x060a
            android.os.SystemProperties.set(r4, r8)
            r13 = 1
        L_0x060a:
            if (r2 != 0) goto L_0x060e
            if (r6 == 0) goto L_0x0642
        L_0x060e:
            r3.clearConfig()
            java.lang.String r4 = "2"
            r3.set(r1, r5, r4)
            java.lang.String r4 = "1280"
            r3.set(r1, r12, r4)
            r3.set(r1, r0, r7)
            r4 = r19
            r0 = r23
            r3.set(r1, r4, r0)
            java.lang.String r0 = "1280"
            r4 = r22
            r3.set(r1, r4, r0)
            r0 = r21
            r3.set(r1, r0, r7)
            r7 = r62
            r0 = r64
            r3.set(r1, r7, r0)
            r12 = r32
            r0 = r63
            r3.set(r1, r0, r12)
            r3.save()
        L_0x0642:
            r14 = r9
            r12 = r13
            r6 = r61
            goto L_0x06ee
        L_0x0648:
            r16 = r2
            r12 = r22
            r61 = r24
            r65 = r25
            r66 = r27
            r2 = r0
            r22 = r1
            r0 = r21
            r1 = r26
            r21 = r5
            r5 = r23
            r67 = r19
            r19 = r7
            r7 = r67
            if (r16 == 0) goto L_0x066e
            r16 = 11
            r23 = r2
            r52 = r7
            r2 = 11
            goto L_0x0673
        L_0x066e:
            r23 = r2
            r52 = r7
            r2 = 2
        L_0x0673:
            r7 = 1280(0x500, float:1.794E-42)
            if (r11 != r7) goto L_0x0680
            r7 = 480(0x1e0, float:6.73E-43)
            if (r13 != r7) goto L_0x0680
            if (r9 == r2) goto L_0x067e
            goto L_0x0680
        L_0x067e:
            r2 = 0
            goto L_0x0682
        L_0x0680:
            r9 = r2
            r2 = 1
        L_0x0682:
            r7 = 160(0xa0, float:2.24E-43)
            if (r15 == r7) goto L_0x068a
            r15 = 160(0xa0, float:2.24E-43)
            r11 = 1
            goto L_0x068b
        L_0x068a:
            r11 = 0
        L_0x068b:
            r7 = 60
            if (r14 == r7) goto L_0x0693
            r14 = 60
            r7 = 1
            goto L_0x0694
        L_0x0693:
            r7 = 0
        L_0x0694:
            boolean r10 = r8.equals(r10)
            if (r10 != 0) goto L_0x069e
            android.os.SystemProperties.set(r4, r8)
            r7 = 1
        L_0x069e:
            if (r2 != 0) goto L_0x06a6
            if (r6 == 0) goto L_0x06a3
            goto L_0x06a6
        L_0x06a3:
            r6 = r61
            goto L_0x06ed
        L_0x06a6:
            r3.clearConfig()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r9)
            r6 = r61
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            r3.set(r1, r5, r4)
            java.lang.String r4 = "1280"
            r3.set(r1, r12, r4)
            java.lang.String r4 = "480"
            r3.set(r1, r0, r4)
            r0 = r23
            r4 = r52
            r3.set(r1, r4, r0)
            java.lang.String r0 = "1280"
            r4 = r22
            r3.set(r1, r4, r0)
            java.lang.String r0 = "480"
            r4 = r21
            r3.set(r1, r4, r0)
            r0 = r19
            r4 = r66
            r3.set(r1, r0, r4)
            r12 = r32
            r0 = r65
            r3.set(r1, r0, r12)
            r3.save()
        L_0x06ed:
            r12 = r7
        L_0x06ee:
            if (r11 == 0) goto L_0x0725
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "checkConfig: changeDensityDpi = "
            r0.append(r1)
            r0.append(r15)
            java.lang.String r0 = r0.toString()
            r1 = r30
            android.util.Log.d(r1, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r3 = "wm density "
            r0.append(r3)
            r0.append(r15)
            java.lang.String r0 = r0.toString()
            java.lang.Runtime r3 = java.lang.Runtime.getRuntime()
            r3.exec(r0)     // Catch:{ IOException -> 0x071f }
            goto L_0x0727
        L_0x071f:
            r0 = move-exception
            r3 = r0
            r3.printStackTrace()
            goto L_0x0727
        L_0x0725:
            r1 = r30
        L_0x0727:
            if (r12 == 0) goto L_0x073d
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r6)
            r0.append(r14)
            java.lang.String r0 = r0.toString()
            java.lang.String r3 = "persist.sys.status_bar_height_landscape"
            android.os.SystemProperties.set(r3, r0)
        L_0x073d:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r3 = "changeConfig = "
            r0.append(r3)
            r0.append(r2)
            java.lang.String r3 = ", changeHeight = "
            r0.append(r3)
            r0.append(r12)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r1, r0)
            if (r2 != 0) goto L_0x0760
            if (r12 == 0) goto L_0x075e
            goto L_0x0760
        L_0x075e:
            r4 = 0
            goto L_0x0761
        L_0x0760:
            r4 = 1
        L_0x0761:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.eventcenter.ConfigUtil2.checkConfig():boolean");
    }

    private void checkUIResolution(String str) {
        String recordValue = this.mContext.mSysProviderOpt.getRecordValue(SysProviderOpt.KSW_UI_RESOLUTION, BuildConfig.FLAVOR);
        str.hashCode();
        String str2 = "1560x700";
        char c = 65535;
        switch (str.hashCode()) {
            case -1816511563:
                if (str.equals("1920X720_1")) {
                    c = 0;
                    break;
                }
                break;
            case -1720860883:
                if (str.equals("1280X480")) {
                    c = 1;
                    break;
                }
                break;
            case -1720858186:
                if (str.equals("1280X720")) {
                    c = 2;
                    break;
                }
                break;
            case -1719907571:
                if (str.equals("1280x480")) {
                    c = 3;
                    break;
                }
                break;
            case -1719904874:
                if (str.equals("1280x720")) {
                    c = 4;
                    break;
                }
                break;
            case -900378731:
                if (str.equals("1920x720_1")) {
                    c = 5;
                    break;
                }
                break;
            case -418199796:
                if (str.equals("800X480")) {
                    c = 6;
                    break;
                }
                break;
            case -417246484:
                if (str.equals("800x480")) {
                    c = 7;
                    break;
                }
                break;
            case -60369288:
                if (str.equals("1440X540")) {
                    c = 8;
                    break;
                }
                break;
            case -59415976:
                if (str.equals("1440x540")) {
                    c = 9;
                    break;
                }
                break;
            case -47183063:
                if (str.equals("720X1920")) {
                    c = 10;
                    break;
                }
                break;
            case -17630391:
                if (str.equals("720x1920")) {
                    c = 11;
                    break;
                }
                break;
            case 24925379:
                if (str.equals("1920X720")) {
                    c = 12;
                    break;
                }
                break;
            case 25878691:
                if (str.equals("1920x720")) {
                    c = 13;
                    break;
                }
                break;
            case 237066427:
                if (str.equals("720x1920_1")) {
                    c = 14;
                    break;
                }
                break;
            case 631019903:
                if (str.equals("1024X600")) {
                    c = 15;
                    break;
                }
                break;
            case 631973215:
                if (str.equals("1024x600")) {
                    c = 16;
                    break;
                }
                break;
            case 884394493:
                if (str.equals("1560X700")) {
                    c = 17;
                    break;
                }
                break;
            case 885347805:
                if (str.equals(str2)) {
                    c = 18;
                    break;
                }
                break;
            case 1901719707:
                if (str.equals("720X1920_1")) {
                    c = 19;
                    break;
                }
                break;
        }
        switch (c) {
            case 1:
            case 3:
                str2 = "1280x480";
                break;
            case 2:
            case 4:
                str2 = "1280x720";
                break;
            case 6:
            case 7:
                str2 = "800x480";
                break;
            case 8:
            case 9:
                str2 = "1440x540";
                break;
            case 15:
            case 16:
                str2 = "1024x600";
                break;
            case 17:
            case 18:
                break;
            default:
                str2 = "1920x720";
                break;
        }
        if (!recordValue.equals(str2)) {
            this.mContext.mSysProviderOpt.updateRecord(SysProviderOpt.KSW_UI_RESOLUTION, str2);
        }
        Log.d(TAG, "uiResolution = " + this.mContext.mSysProviderOpt.getRecordValue(SysProviderOpt.KSW_UI_RESOLUTION, BuildConfig.FLAVOR));
    }

    public void runCmd(String str) {
        SystemProperties.set("sys.apk_path", str);
        SystemProperties.set("ctl.start", "install_apk");
    }

    public boolean getCmdResult() {
        return SystemProperties.get("sys.apk_path").equals("true");
    }

    private void example() {
        SystemProperties.set("persist.sys.show_statusbar", "-1");
        SystemProperties.set("persist.sys.show_navigationbar", "0");
        SystemProperties.set("persist.sys.status_bar_height_landscape", "90");
        SystemProperties.set("persist.sys.navigation_bar_height_landscape", "0");
        SystemProperties.set("persist.sys.status_bar_height", "-1");
        SystemProperties.set("persist.sys.navigation_bar_height", "-1");
    }

    public void switchDebug(String str) {
        String str2 = str;
        Log.d(TAG, "userDebug value = " + str2);
        EventService eventService = this.mContext;
        eventService.runCmd("chmod 777 " + this.mConfigPath);
        do {
        } while (this.mContext.getCmdResult());
        IniFile iniFile = new IniFile(new File(this.mConfigPath));
        String str3 = iniFile.get("zxw_Config", "zxw_no_debug", "0");
        Log.d(TAG, "localParam = " + str3);
        String recordValue = this.mContext.mSysProviderOpt.getRecordValue(SysProviderOpt.KSW_FACTORY_NO_DEBUG, "1");
        Log.d(TAG, "haveLogcat = " + recordValue);
        String str4 = iniFile.get("zxw_Config", "ScreenChip", "2");
        String str5 = iniFile.get("zxw_Config", "ScreenWidth", "1920");
        String str6 = SysProviderOpt.KSW_FACTORY_NO_DEBUG;
        String str7 = "zxw_no_debug";
        String str8 = iniFile.get("zxw_Config", "ScreenHeight", "720");
        String str9 = iniFile.get("zxw_Config", "TouchType", "4");
        String str10 = iniFile.get("zxw_Config", "TouchWidth", "1920");
        String str11 = "TouchWidth";
        String str12 = iniFile.get("zxw_Config", "TouchHeight", "720");
        String str13 = "TouchHeight";
        String str14 = iniFile.get("zxw_Config", "CameraConfig", "1");
        if (!str2.equals(recordValue) || !str2.equals(str3)) {
            Log.d(TAG, "update logcat value = " + str2);
            iniFile.clearConfig();
            iniFile.set("zxw_Config", "ScreenChip", str4);
            iniFile.set("zxw_Config", "ScreenWidth", str5);
            iniFile.set("zxw_Config", "ScreenHeight", str8);
            iniFile.set("zxw_Config", "TouchType", str9);
            iniFile.set("zxw_Config", str11, str10);
            iniFile.set("zxw_Config", str13, str12);
            iniFile.set("zxw_Config", "CameraConfig", str14);
            iniFile.set("zxw_Config", str7, str2);
            iniFile.save();
            this.mContext.mSysProviderOpt.updateRecord(str6, str2);
            this.mContext.rebootService();
            return;
        }
    }
}
