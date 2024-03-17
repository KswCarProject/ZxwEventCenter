package com.szchoiceway.eventcenter.listener;

public class AirCmdUtil {
    public static final byte AC = 16;
    public static final byte AIR_SWITCH = 32;
    public static final int AUTO = -128;
    private static final byte BYTE_MASK = -1;
    public static final byte CMD = 122;
    public static final byte CYCLE = 8;
    private static final int DATA1_MASK = 256;
    public static final byte DATA_TYPE = 0;
    public static final byte DUAL = 1;
    public static final byte FRONT_WIND = 4;
    public static final byte HEAD = -14;
    public static final int LEFT_TEMP_DOWN = 272;
    public static final int LEFT_TEMP_UP = 264;
    public static final byte LEN = 3;
    public static final byte REAR_WIND = 2;
    public static final int RIGHT_TEMP_DOWN = 320;
    public static final int RIGHT_TEMP_UP = 288;
    public static final int WIN_DIRECTION = 260;
    public static final int WIN_DOWN = 258;
    public static final int WIN_UP = 257;

    public static byte[] getLeftTempUpBytes() {
        return getBytes(264);
    }

    public static byte[] getLeftTempDownBytes() {
        return getBytes(272);
    }

    public static byte[] getRightTempUpBytes() {
        return getBytes(288);
    }

    public static byte[] getRightTempDownBytes() {
        return getBytes(RIGHT_TEMP_DOWN);
    }

    public static byte[] getWinUpBytes() {
        return getBytes(257);
    }

    public static byte[] getWinDownBytes() {
        return getBytes(258);
    }

    public static byte[] getDualBytes() {
        return getBytes(1);
    }

    public static byte[] getAcBytes() {
        return getBytes(16);
    }

    public static byte[] getAutoBytes() {
        return getBytes(AUTO);
    }

    public static byte[] getCircleBytes() {
        return getBytes(8);
    }

    public static byte[] getFrontWindBytes() {
        return getBytes(4);
    }

    public static byte[] getRearWindBytes() {
        return getBytes(2);
    }

    public static byte[] getReleaseBytes() {
        return getBytesBase();
    }

    public static byte[] getBytes(byte[] bArr, int i) {
        char c = (i & 256) != 0 ? (char) 256 : 0;
        byte b = (byte) (i & -1);
        if (c == 256) {
            bArr[5] = b;
        } else {
            bArr[4] = b;
        }
        return bArr;
    }

    public static byte[] getBytes(int i) {
        return getBytes(getBytesBase(), i);
    }

    private static byte[] getBytesBase() {
        return new byte[]{-14, 0, 122, 3, 0, 0, 0};
    }
}
