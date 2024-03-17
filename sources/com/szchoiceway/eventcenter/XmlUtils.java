package com.szchoiceway.eventcenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;
import com.android.internal.app.LocalePicker;
import com.example.mylibrary.BuildConfig;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Locale;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class XmlUtils {
    private static final String TAG = "XmlUtils";
    private String backupPath = "/mnt/privdata1/";
    private String currentLocation = BuildConfig.FLAVOR;
    private SharedPreferences.Editor editorCan;
    private SharedPreferences.Editor editorCarType;
    private SharedPreferences.Editor editorDvr;
    private SharedPreferences.Editor editorNavigation;
    private SharedPreferences.Editor editorUI;
    private String factoryFileName = "zxw_factory_config.xml";
    private String lcdFileName = "zxw_lcd.xml";
    private EventService mContext;
    private String mLogoPath = "/dev/block/by-name/privdata2";
    private SysProviderOpt mProvider;
    private boolean originalBackcarTime = false;
    private boolean parseFactory = false;
    private boolean parseLcd = false;
    private SharedPreferences spCan;
    private SharedPreferences spCarType;
    private SharedPreferences spDvr;
    private SharedPreferences spNavigation;
    private SharedPreferences spUI;
    private File xmlFile;

    public XmlUtils(Context context) {
        this.mContext = (EventService) context;
        this.mProvider = SysProviderOpt.getInstance(context);
        initLcdXml();
    }

    private void initLcdXml() {
        Log.d(TAG, "initLcdXml");
        File file = new File("/mnt/privdata1/zxw_factory_config.xml");
        File file2 = new File("/mnt/privdata1/zxw_lcd.xml");
        if (file.exists() && !file2.exists()) {
            try {
                XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                newPullParser.setInput(new FileInputStream(file), "utf-8");
                for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                    if (newPullParser.getEventType() == 2) {
                        String name = newPullParser.getName();
                        char c = 65535;
                        if (name.hashCode() == -1600030548) {
                            if (name.equals("resolution")) {
                                c = 0;
                            }
                        }
                        if (c == 0) {
                            createLcdXml(newPullParser.nextText().trim().toLowerCase());
                            Log.d(TAG, "initLcdXml resolution");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void createLcdXml(String str) {
        try {
            File file = new File("/mnt/privdata1/zxw_lcd.xml");
            file.createNewFile();
            file.setReadable(true);
            file.setWritable(true);
            Document createDocument = DocumentHelper.createDocument();
            Element addElement = createDocument.addElement("ConfigInfo").addElement("basic");
            addElement.addComment("分辨率:  1280x480:1280x480  1920x720:1920x720 720x1920mipi屏(凯利光电):720x1920 1024x600:1024x600 1280x720:1280x720");
            addElement.addElement("resolution").addText(str);
            OutputFormat createPrettyPrint = OutputFormat.createPrettyPrint();
            createPrettyPrint.setEncoding("UTF-8");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            XMLWriter xMLWriter = new XMLWriter((OutputStream) byteArrayOutputStream, createPrettyPrint);
            xMLWriter.write(createDocument);
            xMLWriter.close();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            fileOutputStream.close();
            Log.d(TAG, "createLcdXml success");
        } catch (Exception e) {
            Log.d(TAG, "createLcdXml fail");
            e.printStackTrace();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x01ce A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006b A[Catch:{ Exception -> 0x01e3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0074 A[Catch:{ Exception -> 0x01e3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00bd A[Catch:{ Exception -> 0x01e3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x019a A[Catch:{ Exception -> 0x01e3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01c7 A[Catch:{ Exception -> 0x01e3 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void parseLcdXml(java.lang.String r18) {
        /*
            r17 = this;
            r1 = r17
            java.lang.String r0 = "resolution = "
            java.lang.String r2 = "1920x720"
            com.szchoiceway.eventcenter.SysProviderOpt r3 = r1.mProvider
            java.lang.String r4 = "1"
            if (r3 == 0) goto L_0x0011
            java.lang.String r5 = "KSW_ACTION_IMPORT_CONFIG"
            r3.updateRecord(r5, r4)
        L_0x0011:
            org.xmlpull.v1.XmlPullParserFactory r5 = org.xmlpull.v1.XmlPullParserFactory.newInstance()     // Catch:{ Exception -> 0x01e3 }
            org.xmlpull.v1.XmlPullParser r5 = r5.newPullParser()     // Catch:{ Exception -> 0x01e3 }
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ Exception -> 0x01e3 }
            java.io.File r7 = new java.io.File     // Catch:{ Exception -> 0x01e3 }
            r8 = r18
            r7.<init>(r8)     // Catch:{ Exception -> 0x01e3 }
            r6.<init>(r7)     // Catch:{ Exception -> 0x01e3 }
            java.lang.String r7 = "utf-8"
            r5.setInput(r6, r7)     // Catch:{ Exception -> 0x01e3 }
            int r6 = r5.getEventType()     // Catch:{ Exception -> 0x01e3 }
        L_0x002e:
            r7 = 1
            if (r6 == r7) goto L_0x01d6
            int r6 = r5.getEventType()     // Catch:{ Exception -> 0x01e3 }
            java.lang.String r8 = "basic"
            java.lang.String r9 = "ConfigInfo"
            r10 = 93508654(0x592d42e, float:1.3807717E-35)
            r11 = -1470314704(0xffffffffa85cc730, float:-1.2255645E-14)
            r13 = 2
            java.lang.String r14 = "XmlUtils"
            if (r6 == r13) goto L_0x007a
            r13 = 3
            if (r6 == r13) goto L_0x004b
        L_0x0047:
            r16 = r2
            goto L_0x01ce
        L_0x004b:
            java.lang.String r6 = r5.getName()     // Catch:{ Exception -> 0x01e3 }
            int r13 = r6.hashCode()     // Catch:{ Exception -> 0x01e3 }
            if (r13 == r11) goto L_0x0060
            if (r13 == r10) goto L_0x0058
            goto L_0x0068
        L_0x0058:
            boolean r6 = r6.equals(r8)     // Catch:{ Exception -> 0x01e3 }
            if (r6 == 0) goto L_0x0068
            r12 = 1
            goto L_0x0069
        L_0x0060:
            boolean r6 = r6.equals(r9)     // Catch:{ Exception -> 0x01e3 }
            if (r6 == 0) goto L_0x0068
            r12 = 0
            goto L_0x0069
        L_0x0068:
            r12 = -1
        L_0x0069:
            if (r12 == 0) goto L_0x0074
            if (r12 == r7) goto L_0x006e
            goto L_0x0047
        L_0x006e:
            java.lang.String r6 = "结束解析基本信息设置"
            android.util.Log.d(r14, r6)     // Catch:{ Exception -> 0x01e3 }
            goto L_0x0047
        L_0x0074:
            java.lang.String r6 = "结束解析"
            android.util.Log.d(r14, r6)     // Catch:{ Exception -> 0x01e3 }
            goto L_0x0047
        L_0x007a:
            java.lang.String r6 = r5.getName()     // Catch:{ Exception -> 0x01e3 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01e3 }
            r15.<init>()     // Catch:{ Exception -> 0x01e3 }
            java.lang.String r12 = "startTagName = "
            r15.append(r12)     // Catch:{ Exception -> 0x01e3 }
            r15.append(r6)     // Catch:{ Exception -> 0x01e3 }
            java.lang.String r12 = r15.toString()     // Catch:{ Exception -> 0x01e3 }
            android.util.Log.d(r14, r12)     // Catch:{ Exception -> 0x01e3 }
            int r12 = r6.hashCode()     // Catch:{ Exception -> 0x01e3 }
            r15 = -1600030548(0xffffffffa0a178ac, float:-2.7354315E-19)
            if (r12 == r15) goto L_0x00b0
            if (r12 == r11) goto L_0x00a8
            if (r12 == r10) goto L_0x00a0
            goto L_0x00ba
        L_0x00a0:
            boolean r6 = r6.equals(r8)     // Catch:{ Exception -> 0x01e3 }
            if (r6 == 0) goto L_0x00ba
            r12 = 1
            goto L_0x00bb
        L_0x00a8:
            boolean r6 = r6.equals(r9)     // Catch:{ Exception -> 0x01e3 }
            if (r6 == 0) goto L_0x00ba
            r12 = 0
            goto L_0x00bb
        L_0x00b0:
            java.lang.String r8 = "resolution"
            boolean r6 = r6.equals(r8)     // Catch:{ Exception -> 0x01e3 }
            if (r6 == 0) goto L_0x00ba
            r12 = 2
            goto L_0x00bb
        L_0x00ba:
            r12 = -1
        L_0x00bb:
            if (r12 == 0) goto L_0x01c7
            if (r12 == r7) goto L_0x01bf
            if (r12 == r13) goto L_0x00c2
            goto L_0x0047
        L_0x00c2:
            java.lang.String r6 = r5.nextText()     // Catch:{ Exception -> 0x01e3 }
            java.lang.String r6 = r6.trim()     // Catch:{ Exception -> 0x01e3 }
            java.lang.String r6 = r6.toLowerCase()     // Catch:{ Exception -> 0x01e3 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01e3 }
            r7.<init>()     // Catch:{ Exception -> 0x01e3 }
            r7.append(r0)     // Catch:{ Exception -> 0x01e3 }
            r7.append(r6)     // Catch:{ Exception -> 0x01e3 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x01e3 }
            android.util.Log.d(r14, r7)     // Catch:{ Exception -> 0x01e3 }
            java.lang.String r7 = "decoder_v3"
            boolean r7 = r7.equalsIgnoreCase(r6)     // Catch:{ Exception -> 0x01e3 }
            java.lang.String r8 = "KSW_DATA_DECODER_V3"
            java.lang.String r9 = ""
            java.lang.String r10 = "RESOLUTION"
            if (r7 == 0) goto L_0x0108
            com.szchoiceway.eventcenter.SysProviderOpt r6 = r1.mProvider     // Catch:{ Exception -> 0x01e3 }
            r6.updateRecord(r8, r4)     // Catch:{ Exception -> 0x01e3 }
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext     // Catch:{ Exception -> 0x01e3 }
            java.lang.String r6 = r6.decoderResolution     // Catch:{ Exception -> 0x01e3 }
            boolean r6 = r9.equals(r6)     // Catch:{ Exception -> 0x01e3 }
            if (r6 != 0) goto L_0x0047
            com.szchoiceway.eventcenter.SysProviderOpt r6 = r1.mProvider     // Catch:{ Exception -> 0x01e3 }
            com.szchoiceway.eventcenter.EventService r7 = r1.mContext     // Catch:{ Exception -> 0x01e3 }
            java.lang.String r7 = r7.decoderResolution     // Catch:{ Exception -> 0x01e3 }
            r6.updateRecord(r10, r7)     // Catch:{ Exception -> 0x01e3 }
            goto L_0x0047
        L_0x0108:
            com.szchoiceway.eventcenter.SysProviderOpt r7 = r1.mProvider     // Catch:{ Exception -> 0x01e3 }
            java.lang.String r11 = "0"
            r7.updateRecord(r8, r11)     // Catch:{ Exception -> 0x01e3 }
            boolean r7 = r2.equalsIgnoreCase(r6)     // Catch:{ Exception -> 0x01e3 }
            java.lang.String r8 = "1440x540"
            java.lang.String r11 = "720x1920_1"
            java.lang.String r12 = "1280x720"
            java.lang.String r13 = "1024x600"
            java.lang.String r15 = "720x1920"
            java.lang.String r3 = "1280x480"
            r16 = r2
            java.lang.String r2 = "1920x720_1"
            if (r7 == 0) goto L_0x0128
        L_0x0125:
            r8 = r16
            goto L_0x0174
        L_0x0128:
            boolean r7 = r2.equalsIgnoreCase(r6)     // Catch:{ Exception -> 0x01e3 }
            if (r7 == 0) goto L_0x0130
            r8 = r2
            goto L_0x0174
        L_0x0130:
            boolean r2 = r3.equalsIgnoreCase(r6)     // Catch:{ Exception -> 0x01e3 }
            if (r2 == 0) goto L_0x0138
            r8 = r3
            goto L_0x0174
        L_0x0138:
            boolean r2 = r15.equalsIgnoreCase(r6)     // Catch:{ Exception -> 0x01e3 }
            if (r2 == 0) goto L_0x0140
            r8 = r15
            goto L_0x0174
        L_0x0140:
            boolean r2 = r13.equalsIgnoreCase(r6)     // Catch:{ Exception -> 0x01e3 }
            if (r2 == 0) goto L_0x0148
            r8 = r13
            goto L_0x0174
        L_0x0148:
            boolean r2 = r12.equalsIgnoreCase(r6)     // Catch:{ Exception -> 0x01e3 }
            if (r2 == 0) goto L_0x0150
            r8 = r12
            goto L_0x0174
        L_0x0150:
            boolean r2 = r11.equalsIgnoreCase(r6)     // Catch:{ Exception -> 0x01e3 }
            if (r2 == 0) goto L_0x0158
            r8 = r11
            goto L_0x0174
        L_0x0158:
            boolean r2 = r8.equalsIgnoreCase(r6)     // Catch:{ Exception -> 0x01e3 }
            if (r2 == 0) goto L_0x015f
            goto L_0x0174
        L_0x015f:
            java.lang.String r2 = "1560x700"
            boolean r2 = r2.equalsIgnoreCase(r6)     // Catch:{ Exception -> 0x01e3 }
            if (r2 == 0) goto L_0x016a
            java.lang.String r8 = "1560x700"
            goto L_0x0174
        L_0x016a:
            java.lang.String r2 = "800x480"
            boolean r2 = r2.equalsIgnoreCase(r6)     // Catch:{ Exception -> 0x01e3 }
            if (r2 == 0) goto L_0x0125
            java.lang.String r8 = "800x480"
        L_0x0174:
            com.szchoiceway.eventcenter.SysProviderOpt r2 = r1.mProvider     // Catch:{ Exception -> 0x01e3 }
            java.lang.String r2 = r2.getRecordValue(r10, r9)     // Catch:{ Exception -> 0x01e3 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01e3 }
            r3.<init>()     // Catch:{ Exception -> 0x01e3 }
            r3.append(r0)     // Catch:{ Exception -> 0x01e3 }
            r3.append(r8)     // Catch:{ Exception -> 0x01e3 }
            java.lang.String r6 = ", sysResolution = "
            r3.append(r6)     // Catch:{ Exception -> 0x01e3 }
            r3.append(r2)     // Catch:{ Exception -> 0x01e3 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x01e3 }
            android.util.Log.d(r14, r3)     // Catch:{ Exception -> 0x01e3 }
            boolean r2 = r2.equals(r8)     // Catch:{ Exception -> 0x01e3 }
            if (r2 != 0) goto L_0x01ce
            com.szchoiceway.eventcenter.SysProviderOpt r2 = r1.mProvider     // Catch:{ Exception -> 0x01e3 }
            r2.updateRecord(r10, r8)     // Catch:{ Exception -> 0x01e3 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01e3 }
            r2.<init>()     // Catch:{ Exception -> 0x01e3 }
            java.lang.String r3 = "update resolution = "
            r2.append(r3)     // Catch:{ Exception -> 0x01e3 }
            com.szchoiceway.eventcenter.SysProviderOpt r3 = r1.mProvider     // Catch:{ Exception -> 0x01e3 }
            java.lang.String r3 = r3.getRecordValue(r10, r9)     // Catch:{ Exception -> 0x01e3 }
            r2.append(r3)     // Catch:{ Exception -> 0x01e3 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x01e3 }
            android.util.Log.d(r14, r2)     // Catch:{ Exception -> 0x01e3 }
            r2 = 100
            java.lang.Thread.sleep(r2)     // Catch:{ Exception -> 0x01e3 }
            goto L_0x01ce
        L_0x01bf:
            r16 = r2
            java.lang.String r2 = "开始解析基础信息"
            android.util.Log.d(r14, r2)     // Catch:{ Exception -> 0x01e3 }
            goto L_0x01ce
        L_0x01c7:
            r16 = r2
            java.lang.String r2 = "开始解析"
            android.util.Log.d(r14, r2)     // Catch:{ Exception -> 0x01e3 }
        L_0x01ce:
            int r6 = r5.next()     // Catch:{ Exception -> 0x01e3 }
            r2 = r16
            goto L_0x002e
        L_0x01d6:
            r2 = 1000(0x3e8, double:4.94E-321)
            java.lang.Thread.sleep(r2)     // Catch:{ Exception -> 0x01e3 }
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext     // Catch:{ Exception -> 0x01e3 }
            r0.checkConfig()     // Catch:{ Exception -> 0x01e3 }
            r1.parseLcd = r7     // Catch:{ Exception -> 0x01e3 }
            goto L_0x01ea
        L_0x01e3:
            r0 = move-exception
            r2 = 0
            r1.parseLcd = r2
            r0.printStackTrace()
        L_0x01ea:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.eventcenter.XmlUtils.parseLcdXml(java.lang.String):void");
    }

    public void parseXml(String str) {
        initLcdXml();
        File file = new File(str + this.factoryFileName);
        if (file.exists() && file.length() > 0) {
            parseFactoryXml(file.getAbsolutePath());
        }
        File file2 = new File(str + this.lcdFileName);
        if (file2.exists() && file2.length() > 0) {
            parseLcdXml(file2.getAbsolutePath());
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:445:0x07e3 A[Catch:{ Exception -> 0x12d6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:645:0x12c7 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void parseFactoryXml(java.lang.String r20) {
        /*
            r19 = this;
            r1 = r19
            java.lang.String r2 = "persist.vendor.bluetooth_ext"
            java.lang.String r3 = "XmlUtils"
            java.lang.String r4 = "parseXml"
            android.util.Log.d(r3, r4)
            org.xmlpull.v1.XmlPullParserFactory r5 = org.xmlpull.v1.XmlPullParserFactory.newInstance()     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "parseFactoryXml aaa"
            android.util.Log.d(r3, r6)     // Catch:{ Exception -> 0x12d6 }
            org.xmlpull.v1.XmlPullParser r5 = r5.newPullParser()     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "parseFactoryXml bbb"
            android.util.Log.d(r3, r6)     // Catch:{ Exception -> 0x12d6 }
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ Exception -> 0x12d6 }
            java.io.File r7 = new java.io.File     // Catch:{ Exception -> 0x12d6 }
            r8 = r20
            r7.<init>(r8)     // Catch:{ Exception -> 0x12d6 }
            r6.<init>(r7)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "utf-8"
            r5.setInput(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            int r6 = r5.getEventType()     // Catch:{ Exception -> 0x12d6 }
        L_0x0032:
            r7 = 1
            if (r6 == r7) goto L_0x12cd
            int r6 = r5.getEventType()     // Catch:{ Exception -> 0x12d6 }
            r8 = 3
            java.lang.String r9 = "SupportDvrAppList"
            java.lang.String r10 = "SupportNaviAppList"
            java.lang.String r11 = "SupportUIList"
            r12 = 2
            java.lang.String r14 = ""
            if (r6 == r12) goto L_0x010e
            if (r6 == r8) goto L_0x0049
            goto L_0x12c7
        L_0x0049:
            java.lang.String r6 = r5.getName()     // Catch:{ Exception -> 0x12d6 }
            r15 = -1
            int r16 = r6.hashCode()     // Catch:{ Exception -> 0x12d6 }
            switch(r16) {
                case -1470314704: goto L_0x00a0;
                case -1190811969: goto L_0x0096;
                case -1091882742: goto L_0x008b;
                case -162193983: goto L_0x0083;
                case 93508654: goto L_0x007a;
                case 1326168234: goto L_0x0072;
                case 1619392814: goto L_0x006a;
                case 1889167208: goto L_0x0060;
                case 1985617971: goto L_0x0056;
                default: goto L_0x0055;
            }     // Catch:{ Exception -> 0x12d6 }
        L_0x0055:
            goto L_0x00aa
        L_0x0056:
            java.lang.String r7 = "setings"
            boolean r6 = r6.equals(r7)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x00aa
            r7 = 7
            goto L_0x00ab
        L_0x0060:
            java.lang.String r7 = "CANBusProtocol"
            boolean r6 = r6.equals(r7)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x00aa
            r7 = 6
            goto L_0x00ab
        L_0x006a:
            boolean r6 = r6.equals(r9)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x00aa
            r7 = 4
            goto L_0x00ab
        L_0x0072:
            boolean r6 = r6.equals(r10)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x00aa
            r7 = 3
            goto L_0x00ab
        L_0x007a:
            java.lang.String r8 = "basic"
            boolean r6 = r6.equals(r8)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x00aa
            goto L_0x00ab
        L_0x0083:
            boolean r6 = r6.equals(r11)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x00aa
            r7 = 2
            goto L_0x00ab
        L_0x008b:
            java.lang.String r7 = "factory"
            boolean r6 = r6.equals(r7)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x00aa
            r7 = 8
            goto L_0x00ab
        L_0x0096:
            java.lang.String r7 = "CarDisplayParam"
            boolean r6 = r6.equals(r7)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x00aa
            r7 = 5
            goto L_0x00ab
        L_0x00a0:
            java.lang.String r7 = "ConfigInfo"
            boolean r6 = r6.equals(r7)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x00aa
            r7 = 0
            goto L_0x00ab
        L_0x00aa:
            r7 = -1
        L_0x00ab:
            switch(r7) {
                case 0: goto L_0x0107;
                case 1: goto L_0x0100;
                case 2: goto L_0x00f2;
                case 3: goto L_0x00e4;
                case 4: goto L_0x00d6;
                case 5: goto L_0x00ca;
                case 6: goto L_0x00be;
                case 7: goto L_0x00b7;
                case 8: goto L_0x00b0;
                default: goto L_0x00ae;
            }     // Catch:{ Exception -> 0x12d6 }
        L_0x00ae:
            goto L_0x12c7
        L_0x00b0:
            java.lang.String r6 = "结束解析工厂设置"
            android.util.Log.d(r3, r6)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x00b7:
            java.lang.String r6 = "结束解析系统设置"
            android.util.Log.d(r3, r6)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x00be:
            java.lang.String r6 = "结束解析协议"
            android.util.Log.d(r3, r6)     // Catch:{ Exception -> 0x12d6 }
            android.content.SharedPreferences$Editor r6 = r1.editorCan     // Catch:{ Exception -> 0x12d6 }
            r6.apply()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x00ca:
            java.lang.String r6 = "结束解析原车显示"
            android.util.Log.d(r3, r6)     // Catch:{ Exception -> 0x12d6 }
            android.content.SharedPreferences$Editor r6 = r1.editorCarType     // Catch:{ Exception -> 0x12d6 }
            r6.apply()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x00d6:
            java.lang.String r6 = "结束解析DVR列表"
            android.util.Log.d(r3, r6)     // Catch:{ Exception -> 0x12d6 }
            android.content.SharedPreferences$Editor r6 = r1.editorDvr     // Catch:{ Exception -> 0x12d6 }
            r6.apply()     // Catch:{ Exception -> 0x12d6 }
            r1.currentLocation = r14     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x00e4:
            java.lang.String r6 = "结束解析地图列表"
            android.util.Log.d(r3, r6)     // Catch:{ Exception -> 0x12d6 }
            android.content.SharedPreferences$Editor r6 = r1.editorNavigation     // Catch:{ Exception -> 0x12d6 }
            r6.apply()     // Catch:{ Exception -> 0x12d6 }
            r1.currentLocation = r14     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x00f2:
            java.lang.String r6 = "结束解析UI列表"
            android.util.Log.d(r3, r6)     // Catch:{ Exception -> 0x12d6 }
            android.content.SharedPreferences$Editor r6 = r1.editorUI     // Catch:{ Exception -> 0x12d6 }
            r6.apply()     // Catch:{ Exception -> 0x12d6 }
            r1.currentLocation = r14     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0100:
            java.lang.String r6 = "结束解析基本信息设置"
            android.util.Log.d(r3, r6)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0107:
            java.lang.String r6 = "结束解析"
            android.util.Log.d(r3, r6)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x010e:
            java.lang.String r6 = r5.getName()     // Catch:{ Exception -> 0x12d6 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r15.<init>()     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = "startTagName = "
            r15.append(r8)     // Catch:{ Exception -> 0x12d6 }
            r15.append(r6)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = r15.toString()     // Catch:{ Exception -> 0x12d6 }
            android.util.Log.d(r3, r8)     // Catch:{ Exception -> 0x12d6 }
            int r15 = r6.hashCode()     // Catch:{ Exception -> 0x12d6 }
            r17 = 16
            switch(r15) {
                case -2139808985: goto L_0x05ef;
                case -2138014344: goto L_0x05e4;
                case -2085476082: goto L_0x05d9;
                case -2012107777: goto L_0x05ce;
                case -1936114931: goto L_0x05c3;
                case -1922874146: goto L_0x05b8;
                case -1812703406: goto L_0x05ad;
                case -1772013800: goto L_0x05a2;
                case -1717710401: goto L_0x0597;
                case -1642493919: goto L_0x058b;
                case -1567149168: goto L_0x057f;
                case -1470314704: goto L_0x0574;
                case -1416954168: goto L_0x0568;
                case -1408535349: goto L_0x055c;
                case -1357712437: goto L_0x0551;
                case -1291616239: goto L_0x0545;
                case -1284283353: goto L_0x0539;
                case -1250105222: goto L_0x052d;
                case -1190811969: goto L_0x0521;
                case -1135598697: goto L_0x0515;
                case -1091882742: goto L_0x0509;
                case -1040301734: goto L_0x04fd;
                case -995859034: goto L_0x04f1;
                case -960737960: goto L_0x04e5;
                case -924519752: goto L_0x04d9;
                case -745034626: goto L_0x04cd;
                case -669077989: goto L_0x04c1;
                case -663805402: goto L_0x04b5;
                case -646344043: goto L_0x04a9;
                case -633246340: goto L_0x049d;
                case -446219952: goto L_0x0491;
                case -432269092: goto L_0x0485;
                case -426171434: goto L_0x0479;
                case -407696962: goto L_0x046d;
                case -376609188: goto L_0x0462;
                case -309474065: goto L_0x0457;
                case -296319600: goto L_0x044b;
                case -286540370: goto L_0x043f;
                case -266683378: goto L_0x0433;
                case -190642791: goto L_0x0427;
                case -162193983: goto L_0x041e;
                case -148368470: goto L_0x0412;
                case -136235554: goto L_0x0406;
                case -111161616: goto L_0x03fa;
                case -58215508: goto L_0x03ee;
                case -16216101: goto L_0x03e2;
                case 116643: goto L_0x03d7;
                case 2289459: goto L_0x03cb;
                case 3107151: goto L_0x03bf;
                case 39012324: goto L_0x03b3;
                case 93508654: goto L_0x03a8;
                case 104069929: goto L_0x039c;
                case 166478474: goto L_0x0390;
                case 179335123: goto L_0x0384;
                case 363301769: goto L_0x0378;
                case 530780508: goto L_0x036c;
                case 549064888: goto L_0x0360;
                case 557180872: goto L_0x0354;
                case 587015889: goto L_0x0348;
                case 594249293: goto L_0x033c;
                case 676185733: goto L_0x0330;
                case 758409209: goto L_0x0324;
                case 833959196: goto L_0x0318;
                case 853617721: goto L_0x030c;
                case 858595598: goto L_0x0300;
                case 900633589: goto L_0x02f4;
                case 949162812: goto L_0x02e8;
                case 1035356055: goto L_0x02dc;
                case 1053765153: goto L_0x02d0;
                case 1120682260: goto L_0x02c4;
                case 1152812390: goto L_0x02b8;
                case 1216985755: goto L_0x02ac;
                case 1218276535: goto L_0x02a0;
                case 1326168234: goto L_0x0297;
                case 1339009368: goto L_0x028b;
                case 1373074255: goto L_0x027f;
                case 1402698609: goto L_0x0273;
                case 1433360160: goto L_0x0267;
                case 1449327977: goto L_0x025b;
                case 1450511812: goto L_0x024f;
                case 1450511813: goto L_0x0243;
                case 1484229630: goto L_0x0237;
                case 1567043119: goto L_0x022b;
                case 1619392814: goto L_0x0221;
                case 1639786160: goto L_0x0215;
                case 1718050264: goto L_0x0209;
                case 1723957031: goto L_0x01fd;
                case 1726307692: goto L_0x01f1;
                case 1730354331: goto L_0x01e5;
                case 1755908672: goto L_0x01d9;
                case 1863609622: goto L_0x01cd;
                case 1889167208: goto L_0x01c1;
                case 1911698938: goto L_0x01b5;
                case 1936322979: goto L_0x01a9;
                case 1939633330: goto L_0x019d;
                case 1958848525: goto L_0x0191;
                case 1985617971: goto L_0x0185;
                case 2007535377: goto L_0x0179;
                case 2020772526: goto L_0x016d;
                case 2024968126: goto L_0x0161;
                case 2033697544: goto L_0x0155;
                case 2091773772: goto L_0x0149;
                case 2107831567: goto L_0x013d;
                case 2135588649: goto L_0x0131;
                default: goto L_0x012f;
            }     // Catch:{ Exception -> 0x12d6 }
        L_0x012f:
            goto L_0x05fa
        L_0x0131:
            java.lang.String r15 = "modeSelection"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 94
            goto L_0x05fb
        L_0x013d:
            java.lang.String r15 = "usbHostMode"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 52
            goto L_0x05fb
        L_0x0149:
            java.lang.String r15 = "dvrSelection"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 42
            goto L_0x05fb
        L_0x0155:
            java.lang.String r15 = "rearCameraMirror"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 27
            goto L_0x05fb
        L_0x0161:
            java.lang.String r15 = "phoneSelection"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 96
            goto L_0x05fb
        L_0x016d:
            java.lang.String r15 = "frontCamera"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 62
            goto L_0x05fb
        L_0x0179:
            java.lang.String r15 = "originalNaviVolume"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 23
            goto L_0x05fb
        L_0x0185:
            java.lang.String r15 = "setings"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 12
            goto L_0x05fb
        L_0x0191:
            java.lang.String r15 = "frontCameraMirror"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 26
            goto L_0x05fb
        L_0x019d:
            java.lang.String r15 = "backcarTimeSelection"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 74
            goto L_0x05fb
        L_0x01a9:
            java.lang.String r15 = "videoDrivingBan"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 34
            goto L_0x05fb
        L_0x01b5:
            java.lang.String r15 = "backcarTime"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 75
            goto L_0x05fb
        L_0x01c1:
            java.lang.String r15 = "CANBusProtocol"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 10
            goto L_0x05fb
        L_0x01cd:
            java.lang.String r15 = "bootModeMemory"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 50
            goto L_0x05fb
        L_0x01d9:
            java.lang.String r15 = "backlightBrightness"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 36
            goto L_0x05fb
        L_0x01e5:
            java.lang.String r15 = "naviApp"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 16
            goto L_0x05fb
        L_0x01f1:
            java.lang.String r15 = "reversingTrack"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 28
            goto L_0x05fb
        L_0x01fd:
            java.lang.String r15 = "reversingRadar"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 29
            goto L_0x05fb
        L_0x0209:
            java.lang.String r15 = "reversingMute"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 30
            goto L_0x05fb
        L_0x0215:
            java.lang.String r15 = "wheelTrack"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 73
            goto L_0x05fb
        L_0x0221:
            boolean r6 = r6.equals(r9)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 8
            goto L_0x05fb
        L_0x022b:
            java.lang.String r15 = "androidMediaVolume"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 20
            goto L_0x05fb
        L_0x0237:
            java.lang.String r15 = "carTypeSelection"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 99
            goto L_0x05fb
        L_0x0243:
            java.lang.String r15 = "auxPosition2"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 47
            goto L_0x05fb
        L_0x024f:
            java.lang.String r15 = "auxPosition1"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 46
            goto L_0x05fb
        L_0x025b:
            java.lang.String r15 = "gearType"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 72
            goto L_0x05fb
        L_0x0267:
            java.lang.String r15 = "auxProgram"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 45
            goto L_0x05fb
        L_0x0273:
            java.lang.String r15 = "userManual"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 60
            goto L_0x05fb
        L_0x027f:
            java.lang.String r15 = "carAutoVisible"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 53
            goto L_0x05fb
        L_0x028b:
            java.lang.String r15 = "uiSelection"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 98
            goto L_0x05fb
        L_0x0297:
            boolean r6 = r6.equals(r10)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 7
            goto L_0x05fb
        L_0x02a0:
            java.lang.String r15 = "bootCamera"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 81
            goto L_0x05fb
        L_0x02ac:
            java.lang.String r15 = "password"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 51
            goto L_0x05fb
        L_0x02b8:
            java.lang.String r15 = "musicPlayer"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 48
            goto L_0x05fb
        L_0x02c4:
            java.lang.String r15 = "languageSelection"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 17
            goto L_0x05fb
        L_0x02d0:
            java.lang.String r15 = "micType"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 83
            goto L_0x05fb
        L_0x02dc:
            java.lang.String r15 = "originalPhoneVolume"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 22
            goto L_0x05fb
        L_0x02e8:
            java.lang.String r15 = "videoPlayer"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 49
            goto L_0x05fb
        L_0x02f4:
            java.lang.String r15 = "lrLeftPanelSelection"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 91
            goto L_0x05fb
        L_0x0300:
            java.lang.String r15 = "benzControl"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 78
            goto L_0x05fb
        L_0x030c:
            java.lang.String r15 = "androidPhoneVolume"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 21
            goto L_0x05fb
        L_0x0318:
            java.lang.String r15 = "txzVisible"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 63
            goto L_0x05fb
        L_0x0324:
            java.lang.String r15 = "googleVisible"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 55
            goto L_0x05fb
        L_0x0330:
            java.lang.String r15 = "usbDvrApp"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 43
            goto L_0x05fb
        L_0x033c:
            java.lang.String r15 = "driverSeat"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 76
            goto L_0x05fb
        L_0x0348:
            java.lang.String r15 = "user24Hour"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 19
            goto L_0x05fb
        L_0x0354:
            java.lang.String r15 = "airVisible"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 79
            goto L_0x05fb
        L_0x0360:
            java.lang.String r15 = "corneringLampControl"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 82
            goto L_0x05fb
        L_0x036c:
            java.lang.String r15 = "timeModeSelection"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 18
            goto L_0x05fb
        L_0x0378:
            java.lang.String r15 = "touchData"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 65
            goto L_0x05fb
        L_0x0384:
            java.lang.String r15 = "btPower"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 37
            goto L_0x05fb
        L_0x0390:
            java.lang.String r15 = "lexusOriginFM"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 88
            goto L_0x05fb
        L_0x039c:
            java.lang.String r15 = "model"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 14
            goto L_0x05fb
        L_0x03a8:
            java.lang.String r15 = "basic"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 1
            goto L_0x05fb
        L_0x03b3:
            java.lang.String r15 = "canProtocolSelection"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 100
            goto L_0x05fb
        L_0x03bf:
            java.lang.String r15 = "ecar"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 58
            goto L_0x05fb
        L_0x03cb:
            java.lang.String r15 = "Item"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 11
            goto L_0x05fb
        L_0x03d7:
            java.lang.String r15 = "ver"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 2
            goto L_0x05fb
        L_0x03e2:
            java.lang.String r15 = "ksw_logo"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 102(0x66, float:1.43E-43)
            goto L_0x05fb
        L_0x03ee:
            java.lang.String r15 = "dtvVisible"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 57
            goto L_0x05fb
        L_0x03fa:
            java.lang.String r15 = "audiGt6RightWidget"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 86
            goto L_0x05fb
        L_0x0406:
            java.lang.String r15 = "weatherVisible"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 68
            goto L_0x05fb
        L_0x0412:
            java.lang.String r15 = "useHorn"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 67
            goto L_0x05fb
        L_0x041e:
            boolean r6 = r6.equals(r11)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 6
            goto L_0x05fb
        L_0x0427:
            java.lang.String r15 = "unitDistance"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 39
            goto L_0x05fb
        L_0x0433:
            java.lang.String r15 = "userMode"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 25
            goto L_0x05fb
        L_0x043f:
            java.lang.String r15 = "unitOil"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 40
            goto L_0x05fb
        L_0x044b:
            java.lang.String r15 = "lexusAirControl"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 87
            goto L_0x05fb
        L_0x0457:
            java.lang.String r15 = "product"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 5
            goto L_0x05fb
        L_0x0462:
            java.lang.String r15 = "car_manufacturer"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 4
            goto L_0x05fb
        L_0x046d:
            java.lang.String r15 = "lrRightPanelSelection"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 92
            goto L_0x05fb
        L_0x0479:
            java.lang.String r15 = "audiGt6LeftCarIcon"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 85
            goto L_0x05fb
        L_0x0485:
            java.lang.String r15 = "lvdsMode"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 84
            goto L_0x05fb
        L_0x0491:
            java.lang.String r15 = "unitTemperature"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 41
            goto L_0x05fb
        L_0x049d:
            java.lang.String r15 = "bootAnimationRestore"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 103(0x67, float:1.44E-43)
            goto L_0x05fb
        L_0x04a9:
            java.lang.String r15 = "autoAux"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 44
            goto L_0x05fb
        L_0x04b5:
            java.lang.String r15 = "eqVisible"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 61
            goto L_0x05fb
        L_0x04c1:
            java.lang.String r15 = "amplifierSelection"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 66
            goto L_0x05fb
        L_0x04cd:
            java.lang.String r15 = "reversingCameraType"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 33
            goto L_0x05fb
        L_0x04d9:
            java.lang.String r15 = "Protocol"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 15
            goto L_0x05fb
        L_0x04e5:
            java.lang.String r15 = "dashboardSelection"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 97
            goto L_0x05fb
        L_0x04f1:
            java.lang.String r15 = "doorAmount"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 77
            goto L_0x05fb
        L_0x04fd:
            java.lang.String r15 = "btSelection"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 38
            goto L_0x05fb
        L_0x0509:
            java.lang.String r15 = "factory"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 13
            goto L_0x05fb
        L_0x0515:
            java.lang.String r15 = "lrWheelSelection"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 90
            goto L_0x05fb
        L_0x0521:
            java.lang.String r15 = "CarDisplayParam"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 9
            goto L_0x05fb
        L_0x052d:
            java.lang.String r15 = "voiceSelection"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 95
            goto L_0x05fb
        L_0x0539:
            java.lang.String r15 = "screenCastVisible"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 64
            goto L_0x05fb
        L_0x0545:
            java.lang.String r15 = "bootLogoRestore"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 101(0x65, float:1.42E-43)
            goto L_0x05fb
        L_0x0551:
            java.lang.String r15 = "client"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 3
            goto L_0x05fb
        L_0x055c:
            java.lang.String r15 = "iDriverType"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 70
            goto L_0x05fb
        L_0x0568:
            java.lang.String r15 = "reversingCameraSelection"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 31
            goto L_0x05fb
        L_0x0574:
            java.lang.String r15 = "ConfigInfo"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 0
            goto L_0x05fb
        L_0x057f:
            java.lang.String r15 = "mapSelection"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 93
            goto L_0x05fb
        L_0x058b:
            java.lang.String r15 = "speedType"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 80
            goto L_0x05fb
        L_0x0597:
            java.lang.String r15 = "hicarVisible"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 54
            goto L_0x05fb
        L_0x05a2:
            java.lang.String r15 = "lightControlBrightness"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 35
            goto L_0x05fb
        L_0x05ad:
            java.lang.String r15 = "soundMode"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 24
            goto L_0x05fb
        L_0x05b8:
            java.lang.String r15 = "lrHostSelection"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 89
            goto L_0x05fb
        L_0x05c3:
            java.lang.String r15 = "esExplorer"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 59
            goto L_0x05fb
        L_0x05ce:
            java.lang.String r15 = "cameraType"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 71
            goto L_0x05fb
        L_0x05d9:
            java.lang.String r15 = "auxVisible"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 56
            goto L_0x05fb
        L_0x05e4:
            java.lang.String r15 = "androidCameraSelection"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 32
            goto L_0x05fb
        L_0x05ef:
            java.lang.String r15 = "notHaveScreen"
            boolean r6 = r6.equals(r15)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x05fa
            r6 = 69
            goto L_0x05fb
        L_0x05fa:
            r6 = -1
        L_0x05fb:
            java.lang.String r15 = "KSW_FACTORY_SET_CLIENT"
            java.lang.String r12 = "ALS_6208"
            java.lang.String r13 = "KESAIWEI_RECORD_BT_OFF"
            java.lang.String r8 = "id"
            java.lang.String r4 = "1"
            java.lang.String r7 = "0"
            r18 = r9
            r9 = 0
            switch(r6) {
                case 0: goto L_0x12c2;
                case 1: goto L_0x12bc;
                case 2: goto L_0x129c;
                case 3: goto L_0x127a;
                case 4: goto L_0x126e;
                case 5: goto L_0x1262;
                case 6: goto L_0x1241;
                case 7: goto L_0x121f;
                case 8: goto L_0x11fb;
                case 9: goto L_0x11db;
                case 10: goto L_0x11bb;
                case 11: goto L_0x1112;
                case 12: goto L_0x110b;
                case 13: goto L_0x1104;
                case 14: goto L_0x10d9;
                case 15: goto L_0x10ae;
                case 16: goto L_0x1031;
                case 17: goto L_0x1010;
                case 18: goto L_0x0ff4;
                case 19: goto L_0x0f93;
                case 20: goto L_0x0f6a;
                case 21: goto L_0x0f41;
                case 22: goto L_0x0f18;
                case 23: goto L_0x0eea;
                case 24: goto L_0x0ed9;
                case 25: goto L_0x0e7b;
                case 26: goto L_0x0e68;
                case 27: goto L_0x0e55;
                case 28: goto L_0x0e43;
                case 29: goto L_0x0e31;
                case 30: goto L_0x0e1f;
                case 31: goto L_0x0df9;
                case 32: goto L_0x0dc9;
                case 33: goto L_0x0da8;
                case 34: goto L_0x0d86;
                case 35: goto L_0x0d65;
                case 36: goto L_0x0d41;
                case 37: goto L_0x0d31;
                case 38: goto L_0x0ce1;
                case 39: goto L_0x0cbf;
                case 40: goto L_0x0c9d;
                case 41: goto L_0x0c7b;
                case 42: goto L_0x0c69;
                case 43: goto L_0x0c48;
                case 44: goto L_0x0c36;
                case 45: goto L_0x0c10;
                case 46: goto L_0x0be9;
                case 47: goto L_0x0bc2;
                case 48: goto L_0x0b8e;
                case 49: goto L_0x0b5a;
                case 50: goto L_0x0b48;
                case 51: goto L_0x0b2c;
                case 52: goto L_0x0b1f;
                case 53: goto L_0x0b12;
                case 54: goto L_0x0b05;
                case 55: goto L_0x0af8;
                case 56: goto L_0x0aeb;
                case 57: goto L_0x0ade;
                case 58: goto L_0x0ad1;
                case 59: goto L_0x0ac4;
                case 60: goto L_0x0ab7;
                case 61: goto L_0x0aaa;
                case 62: goto L_0x0a9d;
                case 63: goto L_0x0a90;
                case 64: goto L_0x0a83;
                case 65: goto L_0x0a71;
                case 66: goto L_0x0a38;
                case 67: goto L_0x0a13;
                case 68: goto L_0x0a06;
                case 69: goto L_0x09ec;
                case 70: goto L_0x09da;
                case 71: goto L_0x09c8;
                case 72: goto L_0x09b6;
                case 73: goto L_0x098f;
                case 74: goto L_0x0962;
                case 75: goto L_0x0934;
                case 76: goto L_0x0927;
                case 77: goto L_0x091a;
                case 78: goto L_0x090d;
                case 79: goto L_0x0900;
                case 80: goto L_0x08f3;
                case 81: goto L_0x08e6;
                case 82: goto L_0x08d9;
                case 83: goto L_0x08cc;
                case 84: goto L_0x08bf;
                case 85: goto L_0x08b2;
                case 86: goto L_0x08a5;
                case 87: goto L_0x0898;
                case 88: goto L_0x088b;
                case 89: goto L_0x0879;
                case 90: goto L_0x0867;
                case 91: goto L_0x0855;
                case 92: goto L_0x0843;
                case 93: goto L_0x0831;
                case 94: goto L_0x081f;
                case 95: goto L_0x080d;
                case 96: goto L_0x07fb;
                case 97: goto L_0x07ee;
                case 98: goto L_0x073e;
                case 99: goto L_0x072c;
                case 100: goto L_0x071a;
                case 101: goto L_0x0673;
                case 102: goto L_0x0666;
                case 103: goto L_0x060f;
                default: goto L_0x060d;
            }
        L_0x060d:
            goto L_0x12c7
        L_0x060f:
            java.lang.String r4 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x12d6 }
            r6 = 1
            if (r4 != r6) goto L_0x12c7
            java.lang.String r4 = "restore android boot animation"
            android.util.Log.d(r3, r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r4 = "/mnt/privdata1/"
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r6.runCmd(r4)     // Catch:{ Exception -> 0x12d6 }
        L_0x0626:
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            boolean r6 = r6.getCmdResult()     // Catch:{ Exception -> 0x12d6 }
            if (r6 != 0) goto L_0x062f
            goto L_0x0626
        L_0x062f:
            java.io.File r6 = new java.io.File     // Catch:{ Exception -> 0x12d6 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r7.<init>()     // Catch:{ Exception -> 0x12d6 }
            r7.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = "bootanimation.zip"
            r7.append(r8)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x12d6 }
            r6.<init>(r7)     // Catch:{ Exception -> 0x12d6 }
            java.io.File r7 = new java.io.File     // Catch:{ Exception -> 0x12d6 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r8.<init>()     // Catch:{ Exception -> 0x12d6 }
            r8.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r4 = "bootanimation_tmp.zip"
            r8.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r4 = r8.toString()     // Catch:{ Exception -> 0x12d6 }
            r7.<init>(r4)     // Catch:{ Exception -> 0x12d6 }
            boolean r4 = r6.exists()     // Catch:{ Exception -> 0x12d6 }
            if (r4 == 0) goto L_0x12c7
            r6.renameTo(r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0666:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "SYS_FACTORY_SET_SHOW_KSW_LOGO"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0673:
            java.lang.String r4 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.SysProviderOpt r6 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "SYS_SETLOGO_INDEX"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r8.<init>()     // Catch:{ Exception -> 0x12d6 }
            r8.append(r4)     // Catch:{ Exception -> 0x12d6 }
            r8.append(r14)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x12d6 }
            r6.updateRecord(r7, r8)     // Catch:{ Exception -> 0x12d6 }
            r6 = 1
            if (r4 != r6) goto L_0x12c7
            java.lang.String r4 = "restore android boot logo"
            android.util.Log.d(r3, r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            android.content.res.AssetManager r4 = r4.getAssets()     // Catch:{ Exception -> 0x12d6 }
            if (r4 == 0) goto L_0x12c7
            java.lang.String r6 = "logo_1920x720_default.bmp"
            com.szchoiceway.eventcenter.SysProviderOpt r7 = r1.mProvider     // Catch:{ Exception -> 0x0713 }
            java.lang.String r8 = "RESOLUTION"
            java.lang.String r9 = "1920x720"
            java.lang.String r7 = r7.getRecordValue(r8, r9)     // Catch:{ Exception -> 0x0713 }
            java.lang.String r8 = "1280x480"
            boolean r8 = r8.equalsIgnoreCase(r7)     // Catch:{ Exception -> 0x0713 }
            if (r8 == 0) goto L_0x06b8
            java.lang.String r6 = "logo_1280x480_default.bmp"
            goto L_0x06cd
        L_0x06b8:
            java.lang.String r8 = "1024x600"
            boolean r8 = r8.equals(r7)     // Catch:{ Exception -> 0x0713 }
            if (r8 == 0) goto L_0x06c3
            java.lang.String r6 = "logo_1024x600_default.bmp"
            goto L_0x06cd
        L_0x06c3:
            java.lang.String r8 = "1280x720"
            boolean r7 = r8.equals(r7)     // Catch:{ Exception -> 0x0713 }
            if (r7 == 0) goto L_0x06cd
            java.lang.String r6 = "logo_1280x720_default.bmp"
        L_0x06cd:
            java.io.InputStream r4 = r4.open(r6)     // Catch:{ Exception -> 0x0713 }
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext     // Catch:{ Exception -> 0x0713 }
            android.graphics.Bitmap r7 = android.graphics.BitmapFactory.decodeStream(r4)     // Catch:{ Exception -> 0x0713 }
            r6.ksw_set_logo(r7)     // Catch:{ Exception -> 0x0713 }
            r4.close()     // Catch:{ Exception -> 0x0713 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x0713 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0713 }
            r6.<init>()     // Catch:{ Exception -> 0x0713 }
            java.lang.String r7 = r1.mLogoPath     // Catch:{ Exception -> 0x0713 }
            r6.append(r7)     // Catch:{ Exception -> 0x0713 }
            java.lang.String r7 = "/logo.bmp"
            r6.append(r7)     // Catch:{ Exception -> 0x0713 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x0713 }
            java.lang.String r7 = "/data/local/logo.bmp"
            r4.copyFile(r6, r7)     // Catch:{ Exception -> 0x0713 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x0713 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0713 }
            r6.<init>()     // Catch:{ Exception -> 0x0713 }
            java.lang.String r7 = r1.mLogoPath     // Catch:{ Exception -> 0x0713 }
            r6.append(r7)     // Catch:{ Exception -> 0x0713 }
            java.lang.String r7 = "/logo.bmp"
            r6.append(r7)     // Catch:{ Exception -> 0x0713 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x0713 }
            java.lang.String r7 = "/mnt/privdata1/logo.bmp"
            r4.copyFile(r6, r7)     // Catch:{ Exception -> 0x0713 }
            goto L_0x12c7
        L_0x0713:
            r0 = move-exception
            r4 = r0
            r4.printStackTrace()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x071a:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_AGREEMENT_SELECT_INDEX"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.ksw_Send8902BackcarMod_0x1d()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x072c:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KESAIWEI_RECORD_CAR_TYPE"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.ksw_Send8902BackcarMod_0x0a()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x073e:
            java.lang.String r4 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.SysProviderOpt r6 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "KSW_DATA_PRODUCT_INDEX"
            r8 = 0
            int r6 = r6.getRecordInteger(r7, r8)     // Catch:{ Exception -> 0x12d6 }
            r7 = 1
            if (r6 != r7) goto L_0x0761
            r7 = 18
            if (r4 == r7) goto L_0x07b4
            r6 = 21
            if (r4 == r6) goto L_0x07b4
            r6 = 22
            if (r4 == r6) goto L_0x07b4
            r8 = 18
            goto L_0x07b5
        L_0x0761:
            r7 = 2
            if (r6 != r7) goto L_0x0767
            r8 = 17
            goto L_0x07b5
        L_0x0767:
            com.szchoiceway.eventcenter.SysProviderOpt r6 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "KSW"
            java.lang.String r6 = r6.getRecordValue(r15, r7)     // Catch:{ Exception -> 0x12d6 }
            boolean r7 = r12.equals(r6)     // Catch:{ Exception -> 0x12d6 }
            if (r7 != 0) goto L_0x0780
            r7 = 19
            if (r4 == r7) goto L_0x077d
            r7 = 20
            if (r4 != r7) goto L_0x0780
        L_0x077d:
            r8 = 16
            goto L_0x07b5
        L_0x0780:
            java.lang.String r7 = "GS"
            boolean r7 = r7.equals(r6)     // Catch:{ Exception -> 0x12d6 }
            if (r7 != 0) goto L_0x078d
            r7 = 26
            if (r4 != r7) goto L_0x078d
            goto L_0x077d
        L_0x078d:
            java.lang.String r7 = "LHX2306"
            boolean r7 = r7.equals(r6)     // Catch:{ Exception -> 0x12d6 }
            if (r7 != 0) goto L_0x079a
            r7 = 29
            if (r4 != r7) goto L_0x079a
            goto L_0x077d
        L_0x079a:
            java.lang.String r7 = "LS2682"
            boolean r7 = r7.equals(r6)     // Catch:{ Exception -> 0x12d6 }
            if (r7 != 0) goto L_0x07a7
            r7 = 39
            if (r4 != r7) goto L_0x07a7
            goto L_0x077d
        L_0x07a7:
            java.lang.String r7 = "CK"
            boolean r6 = r7.equals(r6)     // Catch:{ Exception -> 0x12d6 }
            if (r6 != 0) goto L_0x07b4
            r6 = 40
            if (r4 != r6) goto L_0x07b4
            goto L_0x077d
        L_0x07b4:
            r8 = r4
        L_0x07b5:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r4.<init>()     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "uiSelection m_iModeSet = "
            r4.append(r6)     // Catch:{ Exception -> 0x12d6 }
            r4.append(r8)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x12d6 }
            android.util.Log.d(r3, r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KESAIWEI_SYS_MODE_SELECTION"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r7.<init>()     // Catch:{ Exception -> 0x12d6 }
            r7.append(r8)     // Catch:{ Exception -> 0x12d6 }
            r7.append(r14)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            r4 = 22
            if (r8 != r4) goto L_0x12c7
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_BENZ_THEME_BACKGROUND_INDEX"
            java.lang.String r7 = "7"
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x07ee:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_DASHBOARD_SELECT"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x07fb:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_PHONE_KEY_FUNCTION_INDEX"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.ksw_Send8902BackcarMod_0x32()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x080d:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_VOICE_KEY_FUNCTION_INDEX"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.ksw_Send8902BackcarMod_0x24()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x081f:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_MODE_KEY_FUNCTION_INDEX"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.ksw_Send8902BackcarMod_0x31()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0831:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_MAP_KEY_FUNCTION_INDEX"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.ksw_Send8902BackcarMod_0x29()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0843:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_LANDROVER_KEY_PANEL_RIGHT_INDEX"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.kswSendSpecialSetLR()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0855:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_LANDROVER_KEY_PANEL_LEFT_INDEX"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.kswSendSpecialSetLR()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0867:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_LANDRVOER_WHEEL_CONTROL_TYPE"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.kswSendSpecialSetLR()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0879:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_LANDROVER_HOST_INDEX"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.kswSendSpecialSetLR()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x088b:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_LEXUS_ORIGIN_FM"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0898:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_LEXUS_AIR_CONTROL"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x08a5:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "AUDI_GT6_RIGHT_WIDGET"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x08b2:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "AUDI_GT6_LEFT_CAR_ICON"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x08bf:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_SPLITTING_MACHINE_LVDS_MODE"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x08cc:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_EXTERNAL_INTERNAL_MIC_SELECTION"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x08d9:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_TURN_SIGNAL_CONTROL"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x08e6:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_BOOT_360_CAMERA_INDEX"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x08f3:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_SPEED_TYPE_SELECTION"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0900:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_SHOW_AIR"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x090d:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "SYS_BENZ_CONTROL_INDEX"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x091a:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "SYS_DOOR_DISPLAYSET_VALUE_INDEX_KEY"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0927:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "SYS_DOOR_SET_VALUE_INDEX_KEY"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0934:
            java.lang.String r4 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x12d6 }
            boolean r6 = r1.originalBackcarTime     // Catch:{ Exception -> 0x12d6 }
            if (r6 != 0) goto L_0x12c7
            if (r4 < 0) goto L_0x12c7
            com.szchoiceway.eventcenter.SysProviderOpt r6 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "KSW_REVERSE_EXIT_TIME_CUSTOMIZE"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r8.<init>()     // Catch:{ Exception -> 0x12d6 }
            r8.append(r14)     // Catch:{ Exception -> 0x12d6 }
            r8.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r4 = r8.toString()     // Catch:{ Exception -> 0x12d6 }
            r6.updateRecord(r7, r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r6 = 30
            r7 = 1
            r4.sendKSW_0x00_0x70(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0962:
            java.lang.String r4 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x12d6 }
            if (r4 != 0) goto L_0x12c7
            r6 = 1
            r1.originalBackcarTime = r6     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.SysProviderOpt r6 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "KSW_REVERSE_EXIT_TIME_INDEX"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r8.<init>()     // Catch:{ Exception -> 0x12d6 }
            r8.append(r14)     // Catch:{ Exception -> 0x12d6 }
            r8.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r4 = r8.toString()     // Catch:{ Exception -> 0x12d6 }
            r6.updateRecord(r7, r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r6 = 30
            r7 = 0
            r4.sendKSW_0x00_0x70(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x098f:
            java.lang.String r4 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.SysProviderOpt r6 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "KSW_WHELLTRACK_INDEX"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r8.<init>()     // Catch:{ Exception -> 0x12d6 }
            r8.append(r14)     // Catch:{ Exception -> 0x12d6 }
            r8.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x12d6 }
            r6.updateRecord(r7, r8)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r7 = 27
            r6.sendKSW_0x00_0x70(r7, r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x09b6:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_HANDSET_AUTOMATIC_SET_INDEX"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.ksw_Send8902BackcarMod_0x1f()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x09c8:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_360_CAMERA_TYPE_INDEX"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.ksw_Send8902BackcarMod_0x25()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x09da:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_CCC_IDRIVE_TYPE"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.ksw_Send8902BackcarMod_0x17()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x09ec:
            java.lang.String r6 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.SysProviderOpt r8 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r9 = "KSW_ORIGINAL_CAR_VIDEO_DISPLAY"
            r10 = 1
            if (r6 != r10) goto L_0x09fc
            r4 = r7
        L_0x09fc:
            r8.updateRecord(r9, r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.ksw_Send8902BackcarMod_0x0c()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0a06:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_HAVE_WEATHER"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0a13:
            java.lang.String r4 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.SysProviderOpt r6 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "KESAIWEI_HORN_SELECTION"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r8.<init>()     // Catch:{ Exception -> 0x12d6 }
            r8.append(r14)     // Catch:{ Exception -> 0x12d6 }
            r8.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r4 = r8.toString()     // Catch:{ Exception -> 0x12d6 }
            r6.updateRecord(r7, r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.ksw_Send8902BackcarMod_0x36()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0a38:
            java.lang.String r4 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r6.<init>()     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "amplifierSelection = "
            r6.append(r7)     // Catch:{ Exception -> 0x12d6 }
            r6.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x12d6 }
            android.util.Log.d(r3, r6)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.SysProviderOpt r6 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "KESAIWEI_RECORD_AMPLIFIER"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r8.<init>()     // Catch:{ Exception -> 0x12d6 }
            r8.append(r14)     // Catch:{ Exception -> 0x12d6 }
            r8.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r4 = r8.toString()     // Catch:{ Exception -> 0x12d6 }
            r6.updateRecord(r7, r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.ksw_Send8902BackcarMod_0x07()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0a71:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_SEND_TOUCH_DATA_CONTINUED"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.ksw_Send8902BackcarMod_0x33()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0a83:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_SCREEN_CAST_MS9120"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0a90:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_HAVE_TXZ"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0a9d:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_HAVE_FRONT_CAMERA"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0aaa:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_HAVE_EQ"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0ab7:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_HAVE_MANUAL"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0ac4:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_HAVE_ESEXPLORER"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0ad1:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_HAVE_ECAR"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0ade:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_HAVE_TV"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0aeb:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_HAVE_AUX"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0af8:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "MAISILUO_SYS_GOOGLEPLAY"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0b05:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_HAVE_HICAR"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0b12:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_HAVE_CARAUTO"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0b1f:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_USB_HOST_MODE"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0b2c:
            java.lang.String r4 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.SysProviderOpt r6 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "KSW_FACTORY_SET_PASSWORD"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r8.<init>()     // Catch:{ Exception -> 0x12d6 }
            r8.append(r14)     // Catch:{ Exception -> 0x12d6 }
            r8.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r4 = r8.toString()     // Catch:{ Exception -> 0x12d6 }
            r6.updateRecord(r7, r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0b48:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_BOOT_MODE_MEMORY_INDEX"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.ksw_Send8902BackcarMod_0x2D()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0b5a:
            java.lang.String r4 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = r1.getActivityNameByPackageName(r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.SysProviderOpt r7 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = "Video_PackageName"
            r7.updateRecord(r8, r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.SysProviderOpt r7 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = "Video_ActivityName"
            r7.updateRecord(r8, r6)     // Catch:{ Exception -> 0x12d6 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r7.<init>()     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = "videoPackageName = "
            r7.append(r8)     // Catch:{ Exception -> 0x12d6 }
            r7.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r4 = ", videoActivityName = "
            r7.append(r4)     // Catch:{ Exception -> 0x12d6 }
            r7.append(r6)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r4 = r7.toString()     // Catch:{ Exception -> 0x12d6 }
            android.util.Log.d(r3, r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0b8e:
            java.lang.String r4 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = r1.getActivityNameByPackageName(r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.SysProviderOpt r7 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = "Music_PackageName"
            r7.updateRecord(r8, r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.SysProviderOpt r7 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = "Music_ActivityName"
            r7.updateRecord(r8, r6)     // Catch:{ Exception -> 0x12d6 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r7.<init>()     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = "musicPackageName = "
            r7.append(r8)     // Catch:{ Exception -> 0x12d6 }
            r7.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r4 = ", musicActivityName = "
            r7.append(r4)     // Catch:{ Exception -> 0x12d6 }
            r7.append(r6)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r4 = r7.toString()     // Catch:{ Exception -> 0x12d6 }
            android.util.Log.d(r3, r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0bc2:
            java.lang.String r4 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.SysProviderOpt r6 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "KSW_AUX_ITEM_POSITION2"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r8.<init>()     // Catch:{ Exception -> 0x12d6 }
            r8.append(r14)     // Catch:{ Exception -> 0x12d6 }
            r8.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x12d6 }
            r6.updateRecord(r7, r8)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r7 = 24
            r6.sendKSW_0x00_0x70(r7, r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0be9:
            java.lang.String r4 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.SysProviderOpt r6 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "KSW_AUX_ITEM_POSITION"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r8.<init>()     // Catch:{ Exception -> 0x12d6 }
            r8.append(r14)     // Catch:{ Exception -> 0x12d6 }
            r8.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x12d6 }
            r6.updateRecord(r7, r8)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r7 = 23
            r6.sendKSW_0x00_0x70(r7, r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0c10:
            java.lang.String r4 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.SysProviderOpt r6 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "KESAIWEI_SYS_SD_HOST"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r8.<init>()     // Catch:{ Exception -> 0x12d6 }
            r8.append(r14)     // Catch:{ Exception -> 0x12d6 }
            r8.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r4 = r8.toString()     // Catch:{ Exception -> 0x12d6 }
            r6.updateRecord(r7, r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r6 = 1
            r4.ksw_Send8902BackcarMod(r6)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0c36:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KESAIWEI_RECORD_AUX_SWITCHING"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.ksw_Send8902BackcarMod_0x08()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0c48:
            java.lang.String r4 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.SysProviderOpt r6 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "KSW_DVR_APK_PACKAGENAME"
            r6.updateRecord(r7, r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r6.<init>()     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "usbDvrApp = "
            r6.append(r7)     // Catch:{ Exception -> 0x12d6 }
            r6.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r4 = r6.toString()     // Catch:{ Exception -> 0x12d6 }
            android.util.Log.d(r3, r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0c69:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KESAIWEI_RECORD_DVR"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.ksw_Send8902BackcarMod_0x09()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0c7b:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_TEMP_UNIT"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r7.<init>()     // Catch:{ Exception -> 0x12d6 }
            r7.append(r14)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r7.append(r8)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r6 = 0
            r4.ksw_Send8902BackcarMod(r6)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0c9d:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_OIL_UNIT"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r7.<init>()     // Catch:{ Exception -> 0x12d6 }
            r7.append(r14)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r7.append(r8)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r6 = 0
            r4.ksw_Send8902BackcarMod(r6)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0cbf:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_DISTACNE_UNIT"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r7.<init>()     // Catch:{ Exception -> 0x12d6 }
            r7.append(r14)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r7.append(r8)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r6 = 0
            r4.ksw_Send8902BackcarMod(r6)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0ce1:
            java.lang.String r6 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ Exception -> 0x12d6 }
            if (r6 != 0) goto L_0x0cf1
            com.szchoiceway.eventcenter.SysProviderOpt r8 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            r8.updateRecord(r13, r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x0cf6
        L_0x0cf1:
            com.szchoiceway.eventcenter.SysProviderOpt r8 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            r8.updateRecord(r13, r7)     // Catch:{ Exception -> 0x12d6 }
        L_0x0cf6:
            java.lang.String r8 = android.os.SystemProperties.get(r2, r7)     // Catch:{ Exception -> 0x12d6 }
            boolean r9 = r7.equals(r8)     // Catch:{ Exception -> 0x12d6 }
            if (r9 != 0) goto L_0x0d08
            if (r6 == 0) goto L_0x0d05
            r9 = 1
            if (r6 != r9) goto L_0x0d08
        L_0x0d05:
            android.os.SystemProperties.set(r2, r7)     // Catch:{ Exception -> 0x12d6 }
        L_0x0d08:
            boolean r7 = r4.equals(r8)     // Catch:{ Exception -> 0x12d6 }
            if (r7 != 0) goto L_0x0d14
            r7 = 2
            if (r6 != r7) goto L_0x0d14
            android.os.SystemProperties.set(r2, r4)     // Catch:{ Exception -> 0x12d6 }
        L_0x0d14:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "KESAIWEI_RECORD_BT_INDEX"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r8.<init>()     // Catch:{ Exception -> 0x12d6 }
            r8.append(r14)     // Catch:{ Exception -> 0x12d6 }
            r8.append(r6)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = r8.toString()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r7, r6)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.ksw_Send8902BackcarMod_0x0b_btIndex()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0d31:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r13, r6)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.ksw_Send8902BackcarMod_0x0b()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0d41:
            java.lang.String r4 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x12c7
            java.lang.String r7 = "COM.SZCHOICEWAY_BRIGHTNESS_SETTINGS"
            r6.putSettingInt(r7, r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r6.appySetting()     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r6.commitSetting()     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            byte r4 = (byte) r4     // Catch:{ Exception -> 0x12d6 }
            r7 = 0
            r6.SendBLVal(r4, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0d65:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_BACKLIGHT_CONTROL_INDEX"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r7.<init>()     // Catch:{ Exception -> 0x12d6 }
            r7.append(r14)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r7.append(r8)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.ksw_Send8902BackcarMod_0x26()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0d86:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KESAIWEI_SYS_VIDEO_DRIVING_BAN"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r7.<init>()     // Catch:{ Exception -> 0x12d6 }
            r7.append(r14)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r7.append(r8)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r6 = 1
            r4.ksw_Send8902BackcarMod(r6)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0da8:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_AHD_CAMERA_TYPE"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r7.<init>()     // Catch:{ Exception -> 0x12d6 }
            r7.append(r14)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r7.append(r8)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.ksw_Send8902BackcarMod_0x28()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0dc9:
            java.lang.String r4 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.SysProviderOpt r6 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "KSW_ANDROID_CAMERA_TYPE"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r8.<init>()     // Catch:{ Exception -> 0x12d6 }
            r8.append(r14)     // Catch:{ Exception -> 0x12d6 }
            r8.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x12d6 }
            r6.updateRecord(r7, r8)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "persist.camera.sensor360.resolution"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r7.<init>()     // Catch:{ Exception -> 0x12d6 }
            r7.append(r14)     // Catch:{ Exception -> 0x12d6 }
            r7.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r4 = r7.toString()     // Catch:{ Exception -> 0x12d6 }
            android.os.SystemProperties.set(r6, r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0df9:
            java.lang.String r4 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.SysProviderOpt r6 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "KESAIWEI_SYS_CAMERA_SELECTION"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r8.<init>()     // Catch:{ Exception -> 0x12d6 }
            r8.append(r14)     // Catch:{ Exception -> 0x12d6 }
            r8.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r4 = r8.toString()     // Catch:{ Exception -> 0x12d6 }
            r6.updateRecord(r7, r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r6 = 1
            r4.ksw_Send8902BackcarMod(r6)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0e1f:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW_REVEERSING_MUTE_SELECT_INDEX"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.ksw_Send8902BackcarMod_0x1e()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0e31:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KESAIWEI_SYS_RADAR"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.ksw_Send8902BackcarMod_0x1c()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0e43:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KESAIWEI_SYS_REVERSING_TRACK"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.ksw_Send8902BackcarMod_0x1b()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0e55:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KESAIWEI_SYS_BACKCAR_MIRROR"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r6 = 1
            r4.ksw_Send8902BackcarMod(r6)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0e68:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KESAIWEI_SYS_FRONT_MIRROR"
            java.lang.String r7 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r6 = 1
            r4.ksw_Send8902BackcarMod(r6)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0e7b:
            java.lang.String r4 = "low"
            java.lang.String r4 = r5.getAttributeValue(r9, r4)     // Catch:{ Exception -> 0x12d6 }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "middle"
            java.lang.String r6 = r5.getAttributeValue(r9, r6)     // Catch:{ Exception -> 0x12d6 }
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "high"
            java.lang.String r7 = r5.getAttributeValue(r9, r7)     // Catch:{ Exception -> 0x12d6 }
            int r7 = java.lang.Integer.parseInt(r7)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r8 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r9 = "COM.KESAIWEI_EQ_USER_LOW"
            r8.putSettingInt(r9, r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r8 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r9 = "COM.KESAIWEI_EQ_USER_MID"
            r8.putSettingInt(r9, r6)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r8 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r9 = "COM.KESAIWEI_EQ_USER_HIGHT"
            r8.putSettingInt(r9, r6)     // Catch:{ Exception -> 0x12d6 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r8.<init>()     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r9 = "userMode: low = "
            r8.append(r9)     // Catch:{ Exception -> 0x12d6 }
            r8.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r4 = ", middle = "
            r8.append(r4)     // Catch:{ Exception -> 0x12d6 }
            r8.append(r6)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r4 = ", high = "
            r8.append(r4)     // Catch:{ Exception -> 0x12d6 }
            r8.append(r7)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r4 = r8.toString()     // Catch:{ Exception -> 0x12d6 }
            android.util.Log.d(r3, r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.ksw_initSendVol_page2_info()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0ed9:
            java.lang.String r4 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "COM.KESAIWEI_EQ_MODE_SELECT"
            r6.putSettingInt(r7, r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0eea:
            java.lang.String r4 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            if (r4 == 0) goto L_0x0ef5
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x0ef6
        L_0x0ef5:
            r4 = 0
        L_0x0ef6:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r6.<init>()     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "iVol_04 = "
            r6.append(r7)     // Catch:{ Exception -> 0x12d6 }
            r6.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x12d6 }
            android.util.Log.d(r3, r6)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "COM.SZCHOICEWAY_KEY_KSW_VOL_VAL_04"
            r6.putSettingInt(r7, r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.ksw_initSendVol()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0f18:
            java.lang.String r4 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            if (r4 == 0) goto L_0x0f23
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x0f24
        L_0x0f23:
            r4 = 0
        L_0x0f24:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r6.<init>()     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "iVol_03 = "
            r6.append(r7)     // Catch:{ Exception -> 0x12d6 }
            r6.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x12d6 }
            android.util.Log.d(r3, r6)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "COM.SZCHOICEWAY_KEY_KSW_VOL_VAL_03"
            r6.putSettingInt(r7, r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0f41:
            java.lang.String r4 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            if (r4 == 0) goto L_0x0f4c
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x0f4d
        L_0x0f4c:
            r4 = 0
        L_0x0f4d:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r6.<init>()     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "iVol_01 = "
            r6.append(r7)     // Catch:{ Exception -> 0x12d6 }
            r6.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x12d6 }
            android.util.Log.d(r3, r6)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "COM.SZCHOICEWAY_KEY_KSW_VOL_VAL_01"
            r6.putSettingInt(r7, r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0f6a:
            java.lang.String r4 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            if (r4 == 0) goto L_0x0f75
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x0f76
        L_0x0f75:
            r4 = 0
        L_0x0f76:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r6.<init>()     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "iVol_00 = "
            r6.append(r7)     // Catch:{ Exception -> 0x12d6 }
            r6.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x12d6 }
            android.util.Log.d(r3, r6)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "COM.SZCHOICEWAY_KEY_KSW_VOL_VAL_00"
            r6.putSettingInt(r7, r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0f93:
            java.lang.String r4 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r6.<init>()     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "user24Hour = "
            r6.append(r7)     // Catch:{ Exception -> 0x12d6 }
            r6.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x12d6 }
            android.util.Log.d(r3, r6)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.SysProviderOpt r6 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "KESAIWEI_SYS_USER_TIME_TYPE"
            r8 = 0
            int r6 = r6.getRecordInteger(r7, r8)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x0fed
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            android.content.ContentResolver r6 = r6.getContentResolver()     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "time_12_24"
            java.lang.String r7 = android.provider.Settings.System.getString(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            if (r4 != 0) goto L_0x0fd9
            if (r7 == 0) goto L_0x0fd9
            java.lang.String r8 = "24"
            boolean r8 = r7.equals(r8)     // Catch:{ Exception -> 0x12d6 }
            if (r8 == 0) goto L_0x0fd9
            java.lang.String r8 = "time_12_24"
            java.lang.String r9 = "12"
            android.provider.Settings.System.putString(r6, r8, r9)     // Catch:{ Exception -> 0x12d6 }
        L_0x0fd9:
            r8 = 1
            if (r4 != r8) goto L_0x0fed
            if (r7 == 0) goto L_0x0fed
            java.lang.String r4 = "12"
            boolean r4 = r7.equals(r4)     // Catch:{ Exception -> 0x12d6 }
            if (r4 == 0) goto L_0x0fed
            java.lang.String r4 = "time_12_24"
            java.lang.String r7 = "24"
            android.provider.Settings.System.putString(r6, r4, r7)     // Catch:{ Exception -> 0x12d6 }
        L_0x0fed:
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            r4.timeSetChange_KSW()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x0ff4:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KESAIWEI_SYS_USER_TIME_TYPE"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r7.<init>()     // Catch:{ Exception -> 0x12d6 }
            r7.append(r14)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            r7.append(r8)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x1010:
            java.lang.String r4 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r6.<init>()     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "languageSelection = "
            r6.append(r7)     // Catch:{ Exception -> 0x12d6 }
            r6.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x12d6 }
            android.util.Log.d(r3, r6)     // Catch:{ Exception -> 0x12d6 }
            r1.setLanguage(r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x1031:
            java.lang.String r4 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r6.<init>()     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "naviApp packageName = "
            r6.append(r7)     // Catch:{ Exception -> 0x12d6 }
            r6.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x12d6 }
            android.util.Log.d(r3, r6)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            boolean r6 = com.szchoiceway.eventcenter.EventUtils.getInstallStatus(r6, r4)     // Catch:{ Exception -> 0x12d6 }
            if (r6 == 0) goto L_0x1081
            java.lang.String r6 = r1.getActivityNameByPackageName(r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.SysProviderOpt r7 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = "NAV_PACKAGENAME"
            r7.updateRecord(r8, r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.SysProviderOpt r7 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = "NAV_ACTIVITYNAME"
            r7.updateRecord(r8, r6)     // Catch:{ Exception -> 0x12d6 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r7.<init>()     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = "update naviApp succeed "
            r7.append(r8)     // Catch:{ Exception -> 0x12d6 }
            r7.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r4 = ", activityName = "
            r7.append(r4)     // Catch:{ Exception -> 0x12d6 }
            r7.append(r6)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r4 = r7.toString()     // Catch:{ Exception -> 0x12d6 }
            android.util.Log.d(r3, r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x1081:
            com.szchoiceway.eventcenter.SysProviderOpt r6 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "NAV_PACKAGENAME"
            java.lang.String r8 = "com.szchoiceway.navigation"
            r6.updateRecord(r7, r8)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.SysProviderOpt r6 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "NAV_ACTIVITYNAME"
            java.lang.String r8 = "com.szchoiceway.navigation.MainActivity"
            r6.updateRecord(r7, r8)     // Catch:{ Exception -> 0x12d6 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r6.<init>()     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "update naviApp failed "
            r6.append(r7)     // Catch:{ Exception -> 0x12d6 }
            r6.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r4 = "is not exist"
            r6.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r4 = r6.toString()     // Catch:{ Exception -> 0x12d6 }
            android.util.Log.d(r3, r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x10ae:
            java.lang.String r4 = r5.getAttributeValue(r9, r8)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r7.<init>()     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = "Protocol: id = "
            r7.append(r8)     // Catch:{ Exception -> 0x12d6 }
            r7.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = ", value = "
            r7.append(r8)     // Catch:{ Exception -> 0x12d6 }
            r7.append(r6)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x12d6 }
            android.util.Log.d(r3, r7)     // Catch:{ Exception -> 0x12d6 }
            android.content.SharedPreferences$Editor r7 = r1.editorCan     // Catch:{ Exception -> 0x12d6 }
            r7.putString(r4, r6)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x10d9:
            java.lang.String r4 = r5.getAttributeValue(r9, r8)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r7.<init>()     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = "CarDisplayParam: id = "
            r7.append(r8)     // Catch:{ Exception -> 0x12d6 }
            r7.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = ", param = "
            r7.append(r8)     // Catch:{ Exception -> 0x12d6 }
            r7.append(r6)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x12d6 }
            android.util.Log.d(r3, r7)     // Catch:{ Exception -> 0x12d6 }
            android.content.SharedPreferences$Editor r7 = r1.editorCarType     // Catch:{ Exception -> 0x12d6 }
            r7.putString(r4, r6)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x1104:
            java.lang.String r4 = "开始解析工厂设置"
            android.util.Log.d(r3, r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x110b:
            java.lang.String r4 = "开始解析系统设置"
            android.util.Log.d(r3, r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x1112:
            java.lang.String r4 = r1.currentLocation     // Catch:{ Exception -> 0x12d6 }
            boolean r4 = r4.equals(r11)     // Catch:{ Exception -> 0x12d6 }
            if (r4 == 0) goto L_0x1165
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "KSW"
            java.lang.String r4 = r4.getRecordValue(r15, r6)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = r5.getAttributeValue(r9, r8)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "ui"
            java.lang.String r7 = r5.getAttributeValue(r9, r7)     // Catch:{ Exception -> 0x12d6 }
            boolean r4 = r12.equals(r4)     // Catch:{ Exception -> 0x12d6 }
            if (r4 != 0) goto L_0x1142
            java.lang.String r4 = "19"
            boolean r4 = r4.equals(r6)     // Catch:{ Exception -> 0x12d6 }
            if (r4 != 0) goto L_0x12c7
            java.lang.String r4 = "20"
            boolean r4 = r4.equals(r6)     // Catch:{ Exception -> 0x12d6 }
            if (r4 != 0) goto L_0x12c7
        L_0x1142:
            android.content.SharedPreferences$Editor r4 = r1.editorUI     // Catch:{ Exception -> 0x12d6 }
            r4.putString(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r4.<init>()     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = "id = "
            r4.append(r8)     // Catch:{ Exception -> 0x12d6 }
            r4.append(r6)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = ", ui = "
            r4.append(r6)     // Catch:{ Exception -> 0x12d6 }
            r4.append(r7)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x12d6 }
            android.util.Log.d(r3, r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x1165:
            java.lang.String r4 = r1.currentLocation     // Catch:{ Exception -> 0x12d6 }
            boolean r4 = r4.equals(r10)     // Catch:{ Exception -> 0x12d6 }
            if (r4 == 0) goto L_0x118f
            r4 = 0
            java.lang.String r6 = r5.getAttributeValue(r4)     // Catch:{ Exception -> 0x12d6 }
            android.content.SharedPreferences$Editor r4 = r1.editorNavigation     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "navigation"
            r4.putString(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r4.<init>()     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "navigation = "
            r4.append(r7)     // Catch:{ Exception -> 0x12d6 }
            r4.append(r6)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x12d6 }
            android.util.Log.d(r3, r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x118f:
            java.lang.String r4 = r1.currentLocation     // Catch:{ Exception -> 0x12d6 }
            r6 = r18
            boolean r4 = r4.equals(r6)     // Catch:{ Exception -> 0x12d6 }
            if (r4 == 0) goto L_0x12c7
            r4 = 0
            java.lang.String r6 = r5.getAttributeValue(r4)     // Catch:{ Exception -> 0x12d6 }
            android.content.SharedPreferences$Editor r4 = r1.editorDvr     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "dvr"
            r4.putString(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r4.<init>()     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "dvr = "
            r4.append(r7)     // Catch:{ Exception -> 0x12d6 }
            r4.append(r6)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x12d6 }
            android.util.Log.d(r3, r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x11bb:
            java.lang.String r4 = "开始解析协议"
            android.util.Log.d(r3, r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "com.szchoiceway.eventcenter.can"
            r7 = 4
            android.content.SharedPreferences r4 = r4.getSharedPreferences(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            r1.spCan = r4     // Catch:{ Exception -> 0x12d6 }
            android.content.SharedPreferences$Editor r4 = r4.edit()     // Catch:{ Exception -> 0x12d6 }
            r1.editorCan = r4     // Catch:{ Exception -> 0x12d6 }
            r4.clear()     // Catch:{ Exception -> 0x12d6 }
            android.content.SharedPreferences$Editor r4 = r1.editorCan     // Catch:{ Exception -> 0x12d6 }
            r4.apply()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x11db:
            java.lang.String r4 = "开始解析原车显示"
            android.util.Log.d(r3, r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "com.szchoiceway.eventcenter.carType"
            r7 = 4
            android.content.SharedPreferences r4 = r4.getSharedPreferences(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            r1.spCarType = r4     // Catch:{ Exception -> 0x12d6 }
            android.content.SharedPreferences$Editor r4 = r4.edit()     // Catch:{ Exception -> 0x12d6 }
            r1.editorCarType = r4     // Catch:{ Exception -> 0x12d6 }
            r4.clear()     // Catch:{ Exception -> 0x12d6 }
            android.content.SharedPreferences$Editor r4 = r1.editorCarType     // Catch:{ Exception -> 0x12d6 }
            r4.apply()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x11fb:
            r6 = r18
            java.lang.String r4 = "开始解析DVR列表"
            android.util.Log.d(r3, r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "com.szchoiceway.eventcenter.dvr"
            r8 = 4
            android.content.SharedPreferences r4 = r4.getSharedPreferences(r7, r8)     // Catch:{ Exception -> 0x12d6 }
            r1.spDvr = r4     // Catch:{ Exception -> 0x12d6 }
            android.content.SharedPreferences$Editor r4 = r4.edit()     // Catch:{ Exception -> 0x12d6 }
            r1.editorDvr = r4     // Catch:{ Exception -> 0x12d6 }
            r4.clear()     // Catch:{ Exception -> 0x12d6 }
            android.content.SharedPreferences$Editor r4 = r1.editorDvr     // Catch:{ Exception -> 0x12d6 }
            r4.apply()     // Catch:{ Exception -> 0x12d6 }
            r1.currentLocation = r6     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x121f:
            java.lang.String r4 = "开始解析地图列表"
            android.util.Log.d(r3, r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "com.szchoiceway.eventcenter.navigation"
            r7 = 4
            android.content.SharedPreferences r4 = r4.getSharedPreferences(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            r1.spNavigation = r4     // Catch:{ Exception -> 0x12d6 }
            android.content.SharedPreferences$Editor r4 = r4.edit()     // Catch:{ Exception -> 0x12d6 }
            r1.editorNavigation = r4     // Catch:{ Exception -> 0x12d6 }
            r4.clear()     // Catch:{ Exception -> 0x12d6 }
            android.content.SharedPreferences$Editor r4 = r1.editorNavigation     // Catch:{ Exception -> 0x12d6 }
            r4.apply()     // Catch:{ Exception -> 0x12d6 }
            r1.currentLocation = r10     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x1241:
            java.lang.String r4 = "开始解析UI列表"
            android.util.Log.d(r3, r4)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = "com.szchoiceway.eventcenter.ui"
            r7 = 4
            android.content.SharedPreferences r4 = r4.getSharedPreferences(r6, r7)     // Catch:{ Exception -> 0x12d6 }
            r1.spUI = r4     // Catch:{ Exception -> 0x12d6 }
            android.content.SharedPreferences$Editor r4 = r4.edit()     // Catch:{ Exception -> 0x12d6 }
            r1.editorUI = r4     // Catch:{ Exception -> 0x12d6 }
            r4.clear()     // Catch:{ Exception -> 0x12d6 }
            android.content.SharedPreferences$Editor r4 = r1.editorUI     // Catch:{ Exception -> 0x12d6 }
            r4.apply()     // Catch:{ Exception -> 0x12d6 }
            r1.currentLocation = r11     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x1262:
            java.lang.String r4 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.SysProviderOpt r6 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "KSW_DATA_PRODUCT_INDEX"
            r6.updateRecord(r7, r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x126e:
            java.lang.String r4 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.SysProviderOpt r6 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "KSW_DATA_CAR_MANUFACTURER_INDEX"
            r6.updateRecord(r7, r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x127a:
            java.lang.String r6 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = "ALS_6208_TEST"
            boolean r8 = r8.equals(r6)     // Catch:{ Exception -> 0x12d6 }
            if (r8 == 0) goto L_0x128e
            com.szchoiceway.eventcenter.SysProviderOpt r6 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "KSW_FACTORY_SET_CLIENT_ALS_TEST"
            r6.updateRecord(r7, r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x1296
        L_0x128e:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r8 = "KSW_FACTORY_SET_CLIENT_ALS_TEST"
            r4.updateRecord(r8, r7)     // Catch:{ Exception -> 0x12d6 }
            r12 = r6
        L_0x1296:
            com.szchoiceway.eventcenter.SysProviderOpt r4 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            r4.updateRecord(r15, r12)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x129c:
            java.lang.String r4 = r5.nextText()     // Catch:{ Exception -> 0x12d6 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x12d6 }
            r6.<init>()     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "配置文件版本号 ver = "
            r6.append(r7)     // Catch:{ Exception -> 0x12d6 }
            r6.append(r4)     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x12d6 }
            android.util.Log.d(r3, r6)     // Catch:{ Exception -> 0x12d6 }
            com.szchoiceway.eventcenter.SysProviderOpt r6 = r1.mProvider     // Catch:{ Exception -> 0x12d6 }
            java.lang.String r7 = "KSW_FACTORY_VER"
            r6.updateRecord(r7, r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x12bc:
            java.lang.String r4 = "开始解析基本信息设置"
            android.util.Log.d(r3, r4)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12c7
        L_0x12c2:
            java.lang.String r4 = "开始解析"
            android.util.Log.d(r3, r4)     // Catch:{ Exception -> 0x12d6 }
        L_0x12c7:
            int r6 = r5.next()     // Catch:{ Exception -> 0x12d6 }
            goto L_0x0032
        L_0x12cd:
            r2 = 1
            r1.parseFactory = r2     // Catch:{ Exception -> 0x12d6 }
            r2 = 1000(0x3e8, double:4.94E-321)
            java.lang.Thread.sleep(r2)     // Catch:{ Exception -> 0x12d6 }
            goto L_0x12de
        L_0x12d6:
            r0 = move-exception
            r2 = r0
            r3 = 0
            r1.parseFactory = r3
            r2.printStackTrace()
        L_0x12de:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.eventcenter.XmlUtils.parseFactoryXml(java.lang.String):void");
    }

    public boolean getParseResult() {
        return this.parseFactory || this.parseLcd;
    }

    private String getActivityNameByPackageName(String str) {
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        String str2 = BuildConfig.FLAVOR;
        for (ResolveInfo next : this.mContext.getPackageManager().queryIntentActivities(intent, 0)) {
            if (str.equals(next.activityInfo.packageName)) {
                str2 = next.activityInfo.name;
            }
        }
        return str2;
    }

    private void setLanguage(int i) {
        switch (i) {
            case 0:
                LocalePicker.updateLocale(Locale.SIMPLIFIED_CHINESE);
                return;
            case 1:
                LocalePicker.updateLocale(Locale.TRADITIONAL_CHINESE);
                return;
            case 2:
                LocalePicker.updateLocale(Locale.US);
                return;
            case 3:
                LocalePicker.updateLocale(new Locale("ar"));
                return;
            case 4:
                LocalePicker.updateLocale(Locale.GERMAN);
                return;
            case 5:
                LocalePicker.updateLocale(new Locale("el"));
                return;
            case 6:
                LocalePicker.updateLocale(new Locale("es", "ES"));
                return;
            case 7:
                LocalePicker.updateLocale(Locale.FRENCH);
                return;
            case 8:
                LocalePicker.updateLocale(Locale.ITALY);
                return;
            case 9:
                LocalePicker.updateLocale(new Locale("iw"));
                return;
            case 10:
                LocalePicker.updateLocale(Locale.JAPANESE);
                return;
            case 11:
                LocalePicker.updateLocale(Locale.KOREA);
                return;
            case 12:
                LocalePicker.updateLocale(new Locale("nl"));
                return;
            case 13:
                LocalePicker.updateLocale(new Locale("pl"));
                return;
            case 14:
                LocalePicker.updateLocale(new Locale("pt", "BR"));
                return;
            case 15:
                LocalePicker.updateLocale(new Locale("ru"));
                return;
            case 16:
                LocalePicker.updateLocale(new Locale("th"));
                return;
            case 17:
                LocalePicker.updateLocale(new Locale("tr"));
                return;
            case 18:
                LocalePicker.updateLocale(new Locale("vi"));
                return;
            default:
                return;
        }
    }
}
