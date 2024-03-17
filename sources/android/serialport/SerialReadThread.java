package android.serialport;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import com.example.mylibrary.BuildConfig;
import com.szchoiceway.eventcenter.EventUtils;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class SerialReadThread extends Thread {
    private static final String TAG = "SerialReadThread";
    ByteBuffer bufArray = ByteBuffer.wrap(new byte[2048]);
    private Handler mHandler;
    private BufferedInputStream mInputStream;
    private int m_iUITypeVer = 0;

    public SerialReadThread(int i, Handler handler, InputStream inputStream) {
        this.mInputStream = new BufferedInputStream(inputStream);
        this.mHandler = handler;
        this.m_iUITypeVer = i;
    }

    public void run() {
        Log.d(TAG, "开始读线程");
        byte[] bArr = new byte[4096];
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Arrays.fill(bArr, (byte) 0);
                if (this.mInputStream.available() > 0) {
                    int read = this.mInputStream.read(bArr);
                    byte[] copyOf = Arrays.copyOf(bArr, read);
                    int i = this.m_iUITypeVer;
                    if (i == 41) {
                        parseKSWRxData(copyOf, read);
                    } else if (i == 37) {
                        parseMSNData(copyOf, read);
                    } else if (i == 39) {
                        parseZHTYRxData2(copyOf, read);
                    } else if (i == 45) {
                        parseMRWRxData(copyOf, read);
                    } else {
                        parseRxData(copyOf, read);
                    }
                } else {
                    SystemClock.sleep(1);
                }
            } catch (IOException e) {
                Log.e(TAG, "读取数据失败", e);
            }
        }
        Log.e(TAG, "结束读进程");
    }

    public void parseRxData(byte[] bArr, int i) {
        this.bufArray.put(bArr, 0, i);
        this.bufArray.flip();
        int i2 = 0;
        boolean z = false;
        boolean z2 = false;
        byte b = -1;
        int i3 = 0;
        while (this.bufArray.hasRemaining()) {
            byte b2 = this.bufArray.get();
            if (b2 == 13) {
                if (!z && !z2) {
                    z = true;
                    z2 = false;
                }
            } else if (b2 == 10 && z && !z2) {
                z2 = true;
            }
            if (!z || z2) {
                if (z2 && b == -1) {
                    b = b2 & 255;
                    if (b >= 0 && b < 255) {
                        i3 = this.bufArray.position();
                    }
                } else if (b >= 0 && this.bufArray.position() == i3 + b) {
                    i2 = this.bufArray.position();
                    int limit = this.bufArray.limit();
                    this.bufArray.position(i3);
                    this.bufArray.limit(i2);
                    parseCmdEvt(this.bufArray);
                    this.bufArray.position(i2);
                    this.bufArray.limit(limit);
                    i3 = i2;
                }
                z = false;
                z2 = false;
                b = -1;
            } else {
                z = false;
            }
        }
        this.bufArray.position(i2);
        this.bufArray.compact();
        if (this.bufArray.remaining() < 512) {
            this.bufArray.clear();
        }
    }

    private void parseKSWRxData(byte[] bArr, int i) {
        int i2;
        int i3;
        this.bufArray.put(bArr, 0, i);
        this.bufArray.flip();
        int i4 = 0;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        byte b = -1;
        int i5 = 0;
        while (this.bufArray.hasRemaining()) {
            byte b2 = this.bufArray.get();
            if (!z && !z2 && !z3 && b2 == -14) {
                if (!z && !z2 && !z3) {
                    z = true;
                    z2 = false;
                }
                if (z) {
                }
                z = false;
            } else if (!z || z2 || z3) {
                if (z && z2 && !z3) {
                    z3 = true;
                }
                if (z || !z2 || !z3) {
                    z = false;
                } else {
                    if (!z || !z2 || !z3 || b != -1) {
                        if (b >= 0) {
                            int i6 = i5 - 3;
                            if (this.bufArray.get(i6) == -96 && this.bufArray.position() == i5 + b + 1) {
                                Log.i(TAG, "--->>> KSW 0xA0 ");
                                int position = this.bufArray.position();
                                int limit = this.bufArray.limit();
                                int i7 = i5 - 4;
                                this.bufArray.position(i7);
                                this.bufArray.limit(position);
                                Log.i("parseRxData", "--->>> KSW 111 parseRxData: " + EventUtils.bytesToHexString(this.bufArray.array()));
                                int i8 = i7 + 1;
                                int i9 = 0;
                                while (true) {
                                    i3 = position - 1;
                                    if (i8 >= i3) {
                                        break;
                                    }
                                    i9 += this.bufArray.get(i8) & 255;
                                    i8++;
                                }
                                StringBuilder sb = new StringBuilder();
                                sb.append("iChecked = ");
                                byte b3 = (byte) (i9 ^ 255);
                                sb.append(b3);
                                sb.append(", bufArray.get(iEnd - 2) = ");
                                sb.append(this.bufArray.get(i3));
                                Log.d(TAG, sb.toString());
                                if (b3 == this.bufArray.get(i3)) {
                                    parseZHTYCmdEvt(this.bufArray);
                                }
                                this.bufArray.position(position);
                                this.bufArray.limit(limit);
                                i4 = position;
                            } else if (this.bufArray.get(i6) != -96 && this.bufArray.position() == i5 + b + 1) {
                                i4 = this.bufArray.position();
                                int limit2 = this.bufArray.limit();
                                int i10 = i5 - 4;
                                this.bufArray.position(i10);
                                this.bufArray.limit(i4);
                                int i11 = i10 + 1;
                                byte b4 = 0;
                                while (true) {
                                    i2 = i4 - 1;
                                    if (i11 >= i2) {
                                        break;
                                    }
                                    b4 = (byte) (b4 + this.bufArray.get(i11));
                                    i11++;
                                }
                                if (this.bufArray.get(i2) == ((byte) (b4 ^ 255))) {
                                    parseZHTYCmdEvt(this.bufArray);
                                }
                                this.bufArray.position(i4);
                                this.bufArray.limit(limit2);
                            }
                            i5 = i4;
                        }
                    } else if (b2 >= 0 && b2 < 255) {
                        i5 = this.bufArray.position();
                        b = b2;
                    }
                    z = false;
                    z2 = false;
                    z3 = false;
                    b = -1;
                }
            } else {
                z2 = true;
            }
            z3 = false;
        }
        this.bufArray.position(i4);
        this.bufArray.compact();
        if (this.bufArray.remaining() < 512) {
            this.bufArray.clear();
        }
    }

    private void parseMSNData(byte[] bArr, int i) {
        int i2;
        int i3 = 0;
        this.bufArray.put(bArr, 0, i);
        this.bufArray.flip();
        char c = 0;
        int i4 = 0;
        char c2 = 0;
        byte b = 0;
        loop0:
        while (true) {
            int i5 = -1;
            while (this.bufArray.hasRemaining()) {
                byte b2 = this.bufArray.get();
                if (c == 0 && b2 == 85) {
                    i2 = this.bufArray.position();
                    Log.i("parseMSNData", "position....0....: " + i2);
                    Log.i("parseMSNData", "iStep......0..: " + 1);
                } else if (c == 1 && b2 == 85) {
                    i2 = this.bufArray.position();
                    Log.i("parseMSNData", "position....1....: " + i2);
                    Log.i("parseMSNData", "iStep......1..: " + 1);
                } else if (c == 1 && b2 == -86) {
                    Log.i("parseMSNData", "position....2....: " + i2);
                    Log.i("parseMSNData", "iStep......2..: " + 2);
                    c = 2;
                } else if (c == 2) {
                    Log.i("parseMSNData", "position....3....: " + i2);
                    Log.i("parseMSNData", "iStep......3..: " + 3);
                    b = b2;
                    c = 3;
                    c2 = 1;
                } else if (c == 3 && c2 == 1) {
                    i5 = (b * 256) + b2;
                    Log.i("parseMSNData", "position....4....: " + i2);
                    Log.i("parseMSNData", "iStep......4..: " + 4);
                    Log.i("parseMSNData", "cmlen......4..: " + i5);
                    Log.i("parseMSNData", "icmLenH......4..: " + b);
                    Log.i("parseMSNData", "icmLenL......4..: " + b2);
                    c = 4;
                    c2 = 2;
                } else if (i5 >= 0 && c == 4 && this.bufArray.position() == i2 + i5 + 7) {
                    Log.i("parseMSNData", "ok ok ok  ");
                    i3 = this.bufArray.position();
                    int limit = this.bufArray.limit();
                    this.bufArray.position(i2);
                    this.bufArray.limit(i3);
                    parseMSNCmdEvt(this.bufArray);
                    this.bufArray.position(i3);
                    this.bufArray.limit(limit);
                    i4 = i3;
                }
                c = 1;
            }
            break loop0;
        }
        this.bufArray.position(i3);
        this.bufArray.compact();
        if (this.bufArray.remaining() < 512) {
            this.bufArray.clear();
        }
    }

    private void parseMSNCmdEvt(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() > 0) {
            int remaining = byteBuffer.remaining();
            byte[] bArr = new byte[remaining];
            byteBuffer.get(bArr, 0, remaining);
            try {
                Handler handler = this.mHandler;
                if (handler != null) {
                    this.mHandler.sendMessage(handler.obtainMessage(0, bArr));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void parseZHTYRxData2(byte[] bArr, int i) {
        this.bufArray.put(bArr, 0, i);
        this.bufArray.flip();
        int i2 = 0;
        boolean z = false;
        boolean z2 = false;
        byte b = -1;
        byte b2 = -1;
        int i3 = 0;
        loop0:
        while (true) {
            int i4 = -1;
            while (this.bufArray.hasRemaining()) {
                byte b3 = this.bufArray.get();
                if (b3 == 13) {
                    if (!z && !z2) {
                        z = true;
                        z2 = false;
                    }
                } else if (b3 == 36 && z && !z2) {
                    z2 = true;
                }
                if (z && !z2) {
                    z = false;
                } else if (z2 && b == -1) {
                    b = b3 & 255;
                    if (b < 0 || b >= 255) {
                        z = false;
                        z2 = false;
                        b = -1;
                    } else {
                        i3 = this.bufArray.position();
                    }
                } else if (z2 && b >= 0 && b2 == -1) {
                    b2 = b3 & 255;
                    if (b2 < 0 || b2 >= 255) {
                        z = false;
                        z2 = false;
                        b = -1;
                        b2 = -1;
                    } else {
                        i3 = this.bufArray.position();
                        i4 = (b * 256) + b2;
                    }
                } else if (i4 >= 0 && this.bufArray.position() == i3 + i4 + 1) {
                    i2 = this.bufArray.position();
                    int limit = this.bufArray.limit();
                    this.bufArray.position(i3);
                    this.bufArray.limit(i2);
                    parseZHTYCmdEvt(this.bufArray);
                    this.bufArray.position(i2);
                    this.bufArray.limit(limit);
                    i3 = i2;
                    z = false;
                    z2 = false;
                    b = -1;
                    b2 = -1;
                }
            }
            break loop0;
        }
        this.bufArray.position(i2);
        this.bufArray.compact();
        if (this.bufArray.remaining() < 512) {
            this.bufArray.clear();
        }
    }

    private void parseZHTYCmdEvt(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() > 0) {
            int remaining = byteBuffer.remaining();
            byte[] bArr = new byte[remaining];
            byteBuffer.get(bArr, 0, remaining);
            try {
                Handler handler = this.mHandler;
                if (handler != null) {
                    this.mHandler.sendMessage(handler.obtainMessage(0, bArr));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void parseMRWRxData(byte[] bArr, int i) {
        int i2;
        this.bufArray.put(bArr, 0, i);
        this.bufArray.flip();
        int i3 = 0;
        boolean z = false;
        boolean z2 = false;
        byte b = -1;
        int i4 = 0;
        while (this.bufArray.hasRemaining()) {
            byte b2 = this.bufArray.get();
            if (b2 == -1) {
                if (!z && !z2) {
                    z = true;
                    z2 = false;
                }
            } else if (b2 == 85 && z && !z2) {
                z2 = true;
            }
            if (!z || z2) {
                if (z2 && b == -1) {
                    b = b2 & 255;
                    if (b >= 0 && b < 255) {
                        i4 = this.bufArray.position();
                    }
                } else if (b >= 0 && this.bufArray.position() == i4 + b) {
                    i3 = this.bufArray.position();
                    int limit = this.bufArray.limit();
                    this.bufArray.position(i4);
                    this.bufArray.limit(i3);
                    byte b3 = 0;
                    while (true) {
                        i2 = i3 - 1;
                        if (i4 >= i2) {
                            break;
                        }
                        b3 = (byte) (b3 + this.bufArray.get(i4));
                        i4++;
                    }
                    if (this.bufArray.get(i2) == b3) {
                        parseZHTYCmdEvt(this.bufArray);
                    }
                    this.bufArray.position(i3);
                    this.bufArray.limit(limit);
                    i4 = i3;
                }
                z = false;
                z2 = false;
                b = -1;
            } else {
                z = false;
            }
        }
        this.bufArray.position(i3);
        this.bufArray.compact();
        if (this.bufArray.remaining() < 512) {
            this.bufArray.clear();
        }
    }

    private String doDataTransfer(String str, int i) {
        while (str.length() >= i) {
            String substring = str.substring(0, i);
            Message message = new Message();
            message.obj = substring;
            message.what = 4;
            this.mHandler.sendMessage(message);
            str = str.length() == i ? BuildConfig.FLAVOR : str.substring(i);
        }
        return str;
    }

    private void parseCmdEvt(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() > 0) {
            int remaining = byteBuffer.remaining();
            byte[] bArr = new byte[remaining];
            byteBuffer.get(bArr, 0, remaining);
            Message message = new Message();
            message.obj = bArr;
            message.what = 0;
            this.mHandler.sendMessage(message);
        }
    }

    public void close() {
        try {
            this.mInputStream.close();
        } catch (IOException e) {
            Log.e(TAG, "异常", e);
        } catch (Throwable th) {
            super.interrupt();
            throw th;
        }
        super.interrupt();
    }
}
