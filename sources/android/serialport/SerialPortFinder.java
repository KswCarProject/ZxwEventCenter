package android.serialport;

import android.util.Log;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Iterator;
import java.util.Vector;

public class SerialPortFinder {
    private static final String TAG = "SerialPort";
    private Vector<Driver> mDrivers = null;

    public class Driver {
        private String mDeviceRoot;
        Vector<File> mDevices = null;
        private String mDriverName;

        public Driver(String str, String str2) {
            this.mDriverName = str;
            this.mDeviceRoot = str2;
        }

        public Vector<File> getDevices() {
            if (this.mDevices == null) {
                this.mDevices = new Vector<>();
                File[] listFiles = new File("/dev").listFiles();
                if (listFiles != null) {
                    for (int i = 0; i < listFiles.length; i++) {
                        if (listFiles[i].getAbsolutePath().startsWith(this.mDeviceRoot)) {
                            Log.d(SerialPortFinder.TAG, "Found new device: " + listFiles[i]);
                            this.mDevices.add(listFiles[i]);
                        }
                    }
                }
            }
            return this.mDevices;
        }

        public String getName() {
            return this.mDriverName;
        }
    }

    /* access modifiers changed from: package-private */
    public Vector<Driver> getDrivers() throws IOException {
        if (this.mDrivers == null) {
            this.mDrivers = new Vector<>();
            LineNumberReader lineNumberReader = new LineNumberReader(new FileReader("/proc/tty/drivers"));
            while (true) {
                String readLine = lineNumberReader.readLine();
                if (readLine == null) {
                    break;
                }
                String trim = readLine.substring(0, 21).trim();
                String[] split = readLine.split(" +");
                if (split.length >= 5 && split[split.length - 1].equals("serial")) {
                    Log.d(TAG, "Found new driver " + trim + " on " + split[split.length - 4]);
                    this.mDrivers.add(new Driver(trim, split[split.length + -4]));
                }
            }
            lineNumberReader.close();
        }
        return this.mDrivers;
    }

    public String[] getAllDevices() {
        Vector vector = new Vector();
        try {
            Iterator<Driver> it = getDrivers().iterator();
            while (it.hasNext()) {
                Driver next = it.next();
                Iterator<File> it2 = next.getDevices().iterator();
                while (it2.hasNext()) {
                    vector.add(String.format("%s (%s)", new Object[]{it2.next().getName(), next.getName()}));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (String[]) vector.toArray(new String[vector.size()]);
    }

    public String[] getAllDevicesPath() {
        Vector vector = new Vector();
        try {
            Iterator<Driver> it = getDrivers().iterator();
            while (it.hasNext()) {
                Iterator<File> it2 = it.next().getDevices().iterator();
                while (it2.hasNext()) {
                    vector.add(it2.next().getAbsolutePath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (String[]) vector.toArray(new String[vector.size()]);
    }
}
