package com.szchoiceway.eventcenter.fattouchkey;

import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

public class TouchKeyConfig {
    public static final String TAG = "TouchKeyConfig";
    private FatTouchKeyPara m_FatTouchKeyPara = new FatTouchKeyPara();

    public TouchKeyConfig() {
        Log.i(TAG, "***SysVarConfig***");
        init();
    }

    public void init() {
        File file = new File("/data/local/");
        if (!file.exists() && !file.isDirectory()) {
            System.out.println("配置文件夹Touch_Key不存�?");
            if (file.mkdirs()) {
                System.out.println("创建文件夹成�?");
            } else {
                System.out.println("创建文件夹失�?");
            }
        }
        File file2 = new File(file.getPath(), "touchkeycfg.xml");
        Log.i(TAG, "****file_cfg_dir.getPath()1*****=== " + file.getPath());
        if (!file2.exists()) {
            System.out.println("配置文件touchkeycfg.xml不存�?");
            try {
                file2.createNewFile();
                System.out.println("创建文件touchkeycfg.xml成功!");
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    this.m_FatTouchKeyPara.InitPara();
                    fileOutputStream.write(produce_xml_string(this.m_FatTouchKeyPara).getBytes());
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            try {
                Runtime.getRuntime().exec("chmod 777 /data/local/touchkeycfg.xml");
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        try {
            NodeList childNodes = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new FileInputStream(file2)).getDocumentElement().getChildNodes();
            FatTouchKeyPara.iKeyPosType = Integer.parseInt(childNodes.item(0).getFirstChild().getNodeValue());
            FatTouchKeyPara.StrTouchKeyPowerRt = childNodes.item(1).getFirstChild().getNodeValue();
            FatTouchKeyPara.StrTouchKeyHomeRt = childNodes.item(2).getFirstChild().getNodeValue();
            FatTouchKeyPara.StrTouchKeyrReturnRt = childNodes.item(3).getFirstChild().getNodeValue();
            FatTouchKeyPara.StrTouchKeyVolUpRt = childNodes.item(4).getFirstChild().getNodeValue();
            FatTouchKeyPara.StrTouchKeyVolDownRt = childNodes.item(5).getFirstChild().getNodeValue();
            FatTouchKeyPara.StrTouchKeyNextRt = childNodes.item(6).getFirstChild().getNodeValue();
            FatTouchKeyPara.StrTouchKeyPrevRt = childNodes.item(7).getFirstChild().getNodeValue();
            FatTouchKeyPara.StrTouchKeyBndRt = childNodes.item(8).getFirstChild().getNodeValue();
            FatTouchKeyPara.StrTouchKeyMuteRt = childNodes.item(9).getFirstChild().getNodeValue();
            FatTouchKeyPara.StrTouchKeyTalkRt = childNodes.item(10).getFirstChild().getNodeValue();
            FatTouchKeyPara.StrTouchKeyHungUpRt = childNodes.item(11).getFirstChild().getNodeValue();
            FatTouchKeyPara.StrTouchKeyNaviRt = childNodes.item(12).getFirstChild().getNodeValue();
            FatTouchKeyPara.StrTouchKeyModeRt = childNodes.item(13).getFirstChild().getNodeValue();
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }

    private String produce_xml_string(FatTouchKeyPara fatTouchKeyPara) {
        StringWriter stringWriter;
        StringWriter stringWriter2 = new StringWriter();
        try {
            String str = "TouchKeyModeRt";
            XmlSerializer newSerializer = XmlPullParserFactory.newInstance().newSerializer();
            newSerializer.setOutput(stringWriter2);
            stringWriter = stringWriter2;
            String str2 = "TouchKeyNaviRt";
            try {
                newSerializer.startDocument("utf-8", true);
                newSerializer.startTag((String) null, "config");
                newSerializer.startTag((String) null, "KeyPosType");
                newSerializer.text(Integer.toString(FatTouchKeyPara.iKeyPosType));
                newSerializer.endTag((String) null, "KeyPosType");
                newSerializer.startTag((String) null, "TouchKeyPowerRt");
                newSerializer.text(FatTouchKeyPara.StrTouchKeyPowerRt);
                newSerializer.endTag((String) null, "TouchKeyPowerRt");
                newSerializer.startTag((String) null, "TouchKeyHomeRt");
                newSerializer.text(FatTouchKeyPara.StrTouchKeyHomeRt);
                newSerializer.endTag((String) null, "TouchKeyHomeRt");
                newSerializer.startTag((String) null, "TouchKeyrReturnRt");
                newSerializer.text(FatTouchKeyPara.StrTouchKeyrReturnRt);
                newSerializer.endTag((String) null, "TouchKeyrReturnRt");
                newSerializer.startTag((String) null, "TouchKeyVolUpRt");
                newSerializer.text(FatTouchKeyPara.StrTouchKeyVolUpRt);
                newSerializer.endTag((String) null, "TouchKeyVolUpRt");
                newSerializer.startTag((String) null, "TouchKeyVolDownRt");
                newSerializer.text(FatTouchKeyPara.StrTouchKeyVolDownRt);
                newSerializer.endTag((String) null, "TouchKeyVolDownRt");
                newSerializer.startTag((String) null, "TouchKeyNextRt");
                newSerializer.text(FatTouchKeyPara.StrTouchKeyNextRt);
                newSerializer.endTag((String) null, "TouchKeyNextRt");
                newSerializer.startTag((String) null, "TouchKeyPrevRt");
                newSerializer.text(FatTouchKeyPara.StrTouchKeyPrevRt);
                newSerializer.endTag((String) null, "TouchKeyPrevRt");
                newSerializer.startTag((String) null, "TouchKeyBndRt");
                newSerializer.text(FatTouchKeyPara.StrTouchKeyBndRt);
                newSerializer.endTag((String) null, "TouchKeyBndRt");
                newSerializer.startTag((String) null, "TouchKeyMuteRt");
                newSerializer.text(FatTouchKeyPara.StrTouchKeyMuteRt);
                newSerializer.endTag((String) null, "TouchKeyMuteRt");
                newSerializer.startTag((String) null, "TouchKeyTalkRt");
                newSerializer.text(FatTouchKeyPara.StrTouchKeyTalkRt);
                newSerializer.endTag((String) null, "TouchKeyTalkRt");
                newSerializer.startTag((String) null, "TouchKeyHungUpRt");
                newSerializer.text(FatTouchKeyPara.StrTouchKeyHungUpRt);
                newSerializer.endTag((String) null, "TouchKeyHungUpRt");
                String str3 = str2;
                newSerializer.startTag((String) null, str3);
                newSerializer.text(FatTouchKeyPara.StrTouchKeyNaviRt);
                newSerializer.endTag((String) null, str3);
                String str4 = str;
                newSerializer.startTag((String) null, str4);
                newSerializer.text(FatTouchKeyPara.StrTouchKeyModeRt);
                newSerializer.endTag((String) null, str4);
                newSerializer.endTag((String) null, "config");
                newSerializer.endDocument();
            } catch (Exception e) {
                e = e;
            }
        } catch (Exception e2) {
            e = e2;
            stringWriter = stringWriter2;
            e.printStackTrace();
            return stringWriter.toString();
        }
        return stringWriter.toString();
    }

    public FatTouchKeyPara GetFatTouchConfig() {
        return this.m_FatTouchKeyPara;
    }

    public void SaveTouchKeyPara(FatTouchKeyPara fatTouchKeyPara) {
        Log.i(TAG, "****SaveFatPara****");
        this.m_FatTouchKeyPara = fatTouchKeyPara;
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        Log.i(TAG, "****sd_path***** 2=== " + externalStorageDirectory);
        Log.i(TAG, "****sd_path.getPath()2*****=== " + externalStorageDirectory.getPath());
        File file = new File("/data/local/");
        if (!file.exists() && !file.isDirectory()) {
            System.out.println("配置文件夹Touch_Key不存�?");
            if (file.mkdirs()) {
                System.out.println("创建文件夹成�?");
            } else {
                System.out.println("创建文件夹失�?");
            }
        }
        File file2 = new File(file.getPath(), "touchkeycfg.xml");
        Log.i(TAG, "****file_cfg_dir.getPath()2*****=== " + file.getPath());
        if (!file2.exists()) {
            System.out.println("配置文件touchkeycfg.xml不存�?");
            try {
                file2.createNewFile();
                System.out.println("创建文件touchkeycfg.xml成功!");
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    String produce_xml_string = produce_xml_string(this.m_FatTouchKeyPara);
                    Log.i(TAG, "****SaveFatPara****");
                    fileOutputStream.write(produce_xml_string.getBytes());
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } else {
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                fileOutputStream2.write(produce_xml_string(this.m_FatTouchKeyPara).getBytes());
                fileOutputStream2.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        try {
            Runtime.getRuntime().exec("chmod 777 /data/local/touchkeycfg.xml");
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }
}
