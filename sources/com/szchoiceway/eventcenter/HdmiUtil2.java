package com.szchoiceway.eventcenter;

import android.content.Context;
import android.hardware.Camera;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.example.mylibrary.BuildConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;

public class HdmiUtil2 {
    public static final int SIGNAL_ORIGINAL = 0;
    public static final int SIGNAL_REVERSE_AHD_1080P_25 = 2;
    public static final int SIGNAL_REVERSE_AHD_1080P_30 = 1;
    public static final int SIGNAL_REVERSE_AHD_720P_25 = 4;
    public static final int SIGNAL_REVERSE_AHD_720P_30 = 3;
    public static final int SIGNAL_REVERSE_AHD_720P_50 = 5;
    public static final int SIGNAL_REVERSE_CVBS_NTSC = 6;
    public static final int SIGNAL_REVERSE_CVBS_PAL = 7;
    private static final String TAG = "HdmiUtil2";
    public static final int TYPE_ORIGINAL = 1;
    public static final int TYPE_RESTING_SCREEN = 0;
    public static final int TYPE_REVERSE = 2;
    private Camera camera;
    private OnSwitchListener listener;
    private Handler mCameraHandler;
    private HandlerThread mCameraThread;
    private Context mContext;
    private boolean mHasOpen = false;
    private String mPath = "/sys/lt9211/lt9211";
    private int mSignalType = 0;
    private SurfaceHolder mSurfaceHolder;

    interface OnSwitchListener {
        void onSwitch();
    }

    public HdmiUtil2(Context context) {
        this.mContext = context;
    }

    public void openCamera(SurfaceView surfaceView, int i) {
        switch (i) {
            case 0:
                setSignalState("c1");
                break;
            case 1:
                setSignalState("c5");
                break;
            case 2:
                setSignalState("c4");
                break;
            case 3:
            case 5:
                setSignalState("c3");
                break;
            case 4:
                setSignalState("c2");
                break;
            case 6:
                setSignalState("c6");
                break;
            case 7:
                setSignalState("c7");
                break;
        }
        if (!this.mHasOpen) {
            openCamera(surfaceView);
        } else if (this.mSignalType != i) {
            closeCamera();
            openCamera(surfaceView);
        }
    }

    private void openCamera(SurfaceView surfaceView) {
        if (!this.mHasOpen) {
            SurfaceHolder holder = surfaceView.getHolder();
            this.mSurfaceHolder = holder;
            holder.addCallback(new SurfaceHolder.Callback() {
                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    Log.i("openCamera", "surface destroyed.");
                }

                public void surfaceCreated(SurfaceHolder surfaceHolder) {
                    Log.i("openCamera", "surface surfaceCreated");
                }

                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                    Log.i("openCamera", "surface changed.");
                }
            });
            this.mContext.getMainThreadHandler().postDelayed(new Runnable() {
                public final void run() {
                    HdmiUtil2.this.lambda$openCamera$0$HdmiUtil2();
                }
            }, 300);
        }
    }

    public /* synthetic */ void lambda$openCamera$0$HdmiUtil2() {
        try {
            Camera openLegacy = openLegacy(0, 256);
            this.camera = openLegacy;
            if (openLegacy != null) {
                for (Camera.Size next : openLegacy.getParameters().getSupportedPreviewSizes()) {
                }
                this.camera.setPreviewDisplay(this.mSurfaceHolder);
                this.camera.startPreview();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mHasOpen = true;
    }

    public void closeCamera() {
        if (this.mHasOpen) {
            Camera camera2 = this.camera;
            if (camera2 != null) {
                try {
                    camera2.stopPreview();
                    this.camera.setPreviewDisplay((SurfaceHolder) null);
                    this.camera.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.mHasOpen = false;
        }
    }

    private Camera openLegacy(int i, int i2) {
        try {
            return (Camera) Class.forName("android.hardware.Camera").getMethod("openLegacy", new Class[]{Integer.TYPE, Integer.TYPE}).invoke((Object) null, new Object[]{Integer.valueOf(i), Integer.valueOf(i2)});
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return null;
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            return null;
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x005a A[SYNTHETIC, Splitter:B:18:0x005a] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0065 A[SYNTHETIC, Splitter:B:23:0x0065] */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setSignalState(java.lang.String r5) {
        /*
            r4 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "setSignalState = "
            r0.append(r1)
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "HdmiUtil2"
            android.util.Log.i(r1, r0)
            java.io.File r0 = new java.io.File
            java.lang.String r2 = "/sys/lt9211/lt9211"
            r0.<init>(r2)
            boolean r2 = r0.exists()
            if (r2 == 0) goto L_0x006e
            r2 = 0
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0054 }
            r3.<init>(r0)     // Catch:{ Exception -> 0x0054 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ Exception -> 0x004f, all -> 0x004c }
            byte[] r0 = r5.getBytes()     // Catch:{ Exception -> 0x004f, all -> 0x004c }
            r3.write(r0)     // Catch:{ Exception -> 0x004f, all -> 0x004c }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x004f, all -> 0x004c }
            r0.<init>()     // Catch:{ Exception -> 0x004f, all -> 0x004c }
            java.lang.String r2 = "write status = "
            r0.append(r2)     // Catch:{ Exception -> 0x004f, all -> 0x004c }
            r0.append(r5)     // Catch:{ Exception -> 0x004f, all -> 0x004c }
            java.lang.String r5 = r0.toString()     // Catch:{ Exception -> 0x004f, all -> 0x004c }
            android.util.Log.d(r1, r5)     // Catch:{ Exception -> 0x004f, all -> 0x004c }
            r3.close()     // Catch:{ IOException -> 0x005e }
            goto L_0x006e
        L_0x004c:
            r5 = move-exception
            r2 = r3
            goto L_0x0063
        L_0x004f:
            r5 = move-exception
            r2 = r3
            goto L_0x0055
        L_0x0052:
            r5 = move-exception
            goto L_0x0063
        L_0x0054:
            r5 = move-exception
        L_0x0055:
            r5.printStackTrace()     // Catch:{ all -> 0x0052 }
            if (r2 == 0) goto L_0x006e
            r2.close()     // Catch:{ IOException -> 0x005e }
            goto L_0x006e
        L_0x005e:
            r5 = move-exception
            r5.printStackTrace()
            goto L_0x006e
        L_0x0063:
            if (r2 == 0) goto L_0x006d
            r2.close()     // Catch:{ IOException -> 0x0069 }
            goto L_0x006d
        L_0x0069:
            r0 = move-exception
            r0.printStackTrace()
        L_0x006d:
            throw r5
        L_0x006e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.eventcenter.HdmiUtil2.setSignalState(java.lang.String):void");
    }

    private String getSignalState() {
        if (new File("/sys/lt9211/lt9211").exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader("/sys/lt9211/lt9211"));
                StringBuffer stringBuffer = new StringBuffer();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        return stringBuffer.toString();
                    }
                    stringBuffer.append(readLine);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return BuildConfig.FLAVOR;
    }

    public void setOnSwitchListener(OnSwitchListener onSwitchListener) {
        this.listener = onSwitchListener;
    }
}
