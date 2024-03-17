package com.szchoiceway;

import android.hardware.SerialPort;
import java.io.IOException;

public class ZXWSerialPort extends SerialPort {
    public void open(String str, int i) {
        try {
            ZXWSerialPort.super.open(str, i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
