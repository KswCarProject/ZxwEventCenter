package android.serialport;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import com.szchoiceway.eventcenter.EventUtils;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class SerialPortManager {
    private static final String TAG = "SerialPortManager";
    private List<byte[]> bytesTmp;
    private int checkTag;
    private OutputStream mOutputStream;
    private SerialReadThread mReadThread;
    private SerialPort mSerialPort;
    private HandlerThread mWriteThread;
    private int m_iUITypeVer;

    private static class InstanceHolder {
        public static SerialPortManager sManager = new SerialPortManager();

        private InstanceHolder() {
        }
    }

    public static SerialPortManager instance() {
        return InstanceHolder.sManager;
    }

    private SerialPortManager() {
        this.checkTag = 0;
        this.bytesTmp = new ArrayList();
        this.m_iUITypeVer = 0;
    }

    public SerialPort open(Context context, int i, Handler handler, Device device) {
        return open(context, i, handler, device.getPath(), device.getBaudrate());
    }

    public SerialPort open(Context context, int i, Handler handler, String str, String str2) {
        if (this.mSerialPort != null) {
            close();
        }
        try {
            File file = new File(str);
            int parseInt = Integer.parseInt(str2);
            if ("GT7-CAR".equals(Build.MODEL)) {
                SerialPort.setSuPath("/system/xbin");
            }
            this.mSerialPort = new SerialPort(file, parseInt, 0);
            SerialReadThread serialReadThread = new SerialReadThread(i, handler, this.mSerialPort.getInputStream());
            this.mReadThread = serialReadThread;
            serialReadThread.start();
            this.mOutputStream = this.mSerialPort.getOutputStream();
            HandlerThread handlerThread = new HandlerThread("write-thread");
            this.mWriteThread = handlerThread;
            handlerThread.start();
            return this.mSerialPort;
        } catch (Throwable th) {
            Log.e(TAG, "打开串口失败", th);
            close();
            return null;
        }
    }

    public void close() {
        SerialReadThread serialReadThread = this.mReadThread;
        if (serialReadThread != null) {
            serialReadThread.close();
        }
        OutputStream outputStream = this.mOutputStream;
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        HandlerThread handlerThread = this.mWriteThread;
        if (handlerThread != null) {
            handlerThread.quit();
        }
        SerialPort serialPort = this.mSerialPort;
        if (serialPort != null) {
            serialPort.close();
            this.mSerialPort = null;
        }
    }

    public void sendData(byte[] bArr) {
        try {
            OutputStream outputStream = this.mOutputStream;
            if (outputStream != null) {
                outputStream.write(bArr);
                if (bArr.length > 4) {
                    if (bArr[2] != 100 && bArr[2] != 118 && bArr[2] != 111) {
                        if (bArr[2] == 104 && bArr[4] == 8) {
                            return;
                        }
                    } else {
                        return;
                    }
                }
                Log.d("SerialPortData", "sendData: " + EventUtils.bytesToHexString(bArr));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
