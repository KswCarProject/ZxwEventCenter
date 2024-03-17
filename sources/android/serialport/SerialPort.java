package android.serialport;

import android.os.SystemProperties;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SerialPort {
    private static final String DEFAULT_SU_PATH = "/system/bin/su";
    private static final String TAG = "SerialPort";
    private static String sSuPath = "/system/bin/su";
    private FileDescriptor mFd;
    private FileInputStream mFileInputStream;
    private FileOutputStream mFileOutputStream;

    private static native FileDescriptor open(String str, int i, int i2);

    public native void close();

    public static void setSuPath(String str) {
        if (str != null) {
            sSuPath = str;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x003a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SerialPort(java.io.File r3, int r4, int r5) throws java.lang.SecurityException, java.io.IOException {
        /*
            r2 = this;
            r2.<init>()
            boolean r0 = r3.canRead()
            if (r0 == 0) goto L_0x000f
            boolean r0 = r3.canWrite()
            if (r0 != 0) goto L_0x002e
        L_0x000f:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "chmod 777 "
            r0.append(r1)
            java.lang.String r1 = r3.getAbsolutePath()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r2.runCmd(r0)
        L_0x0027:
            boolean r0 = r2.getCmdResult()
            if (r0 != 0) goto L_0x002e
            goto L_0x0027
        L_0x002e:
            java.lang.String r3 = r3.getAbsolutePath()
            java.io.FileDescriptor r3 = open(r3, r4, r5)
            r2.mFd = r3
            if (r3 == 0) goto L_0x004d
            java.io.FileInputStream r3 = new java.io.FileInputStream
            java.io.FileDescriptor r4 = r2.mFd
            r3.<init>(r4)
            r2.mFileInputStream = r3
            java.io.FileOutputStream r3 = new java.io.FileOutputStream
            java.io.FileDescriptor r4 = r2.mFd
            r3.<init>(r4)
            r2.mFileOutputStream = r3
            return
        L_0x004d:
            java.lang.String r3 = "SerialPort"
            java.lang.String r4 = "native open returns null"
            android.util.Log.e(r3, r4)
            java.io.IOException r3 = new java.io.IOException
            r3.<init>()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.serialport.SerialPort.<init>(java.io.File, int, int):void");
    }

    public SerialPort(String str, int i, int i2) throws SecurityException, IOException {
        this(new File(str), i, i2);
    }

    public SerialPort(File file, int i) throws SecurityException, IOException {
        this(file, i, 0);
    }

    public SerialPort(String str, int i) throws SecurityException, IOException {
        this(new File(str), i, 0);
    }

    public InputStream getInputStream() {
        return this.mFileInputStream;
    }

    public OutputStream getOutputStream() {
        return this.mFileOutputStream;
    }

    static {
        System.loadLibrary("serial_port");
    }

    public void runCmd(String str) {
        SystemProperties.set("sys.apk_path", str);
        SystemProperties.set("ctl.start", "install_apk");
    }

    public boolean getCmdResult() {
        return SystemProperties.get("sys.apk_path").equals("true");
    }
}
