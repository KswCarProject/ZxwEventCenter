package com.szchoiceway.eventcenter;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.ImageReader;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;
import com.example.mylibrary.BuildConfig;
import java.util.ArrayList;
import java.util.Arrays;

public class HdmiUtil {
    public static final int SIGNAL_ORIGINAL = 0;
    public static final int SIGNAL_REVERSE_AHD_1080P_25 = 2;
    public static final int SIGNAL_REVERSE_AHD_1080P_30 = 1;
    public static final int SIGNAL_REVERSE_AHD_720P_25 = 4;
    public static final int SIGNAL_REVERSE_AHD_720P_30 = 3;
    public static final int SIGNAL_REVERSE_AHD_720P_50 = 5;
    public static final int SIGNAL_REVERSE_CVBS_NTSC = 6;
    public static final int SIGNAL_REVERSE_CVBS_PAL = 7;
    private static final String TAG = "HdmiUtil";
    public static final int TYPE_ORIGINAL = 1;
    public static final int TYPE_RESTING_SCREEN = 0;
    public static final int TYPE_REVERSE = 2;
    /* access modifiers changed from: private */
    public CameraCaptureSession mCameraCaptureSession;
    /* access modifiers changed from: private */
    public CameraDevice mCameraDevice;
    private Handler mCameraHandler;
    private String mCameraId = BuildConfig.FLAVOR;
    private CameraManager mCameraManager;
    private HandlerThread mCameraThread;
    private Context mContext;
    private boolean mHasOpen = false;
    private String mPath = "/sys/lt9211/lt9211";
    /* access modifiers changed from: private */
    public Surface mPreviewSurface;
    private int mSignalType = 0;
    private CameraDevice.StateCallback mStateCallback;
    private TextureView mTextureView;

    public HdmiUtil(Context context) {
        this.mContext = context;
        initCamera();
    }

    private void initCamera() {
        CameraManager cameraManager = (CameraManager) this.mContext.getSystemService("camera");
        this.mCameraManager = cameraManager;
        try {
            String[] cameraIdList = cameraManager.getCameraIdList();
            int length = cameraIdList.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                String str = cameraIdList[i];
                CameraCharacteristics cameraCharacteristics = this.mCameraManager.getCameraCharacteristics(str);
                if (((Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING)).intValue() == 1) {
                    this.mCameraId = str;
                    Size[] outputSizes = ((StreamConfigurationMap) cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)).getOutputSizes(ImageReader.class);
                    Log.d(TAG, "supportedSizes = " + Arrays.toString(outputSizes) + ", cameraId = " + str);
                    break;
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "mCameraId = " + this.mCameraId);
    }

    public void openCamera(TextureView textureView, int i) {
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
            openCamera(textureView);
        } else if (this.mSignalType != i) {
            closeCamera();
            openCamera(textureView);
        }
    }

    private void startCameraThread() {
        HandlerThread handlerThread = new HandlerThread("CameraThread");
        this.mCameraThread = handlerThread;
        handlerThread.start();
        this.mCameraHandler = new Handler(this.mCameraThread.getLooper());
    }

    private void stopCameraThread() {
        HandlerThread handlerThread = this.mCameraThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
            try {
                this.mCameraThread.join();
                this.mCameraThread = null;
                this.mCameraHandler = null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void openCamera(TextureView textureView) {
        Log.d(TAG, "openCamera textureView = " + textureView);
        this.mHasOpen = true;
        if (textureView != null) {
            textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
                public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                    Surface unused = HdmiUtil.this.mPreviewSurface = new Surface(surfaceTexture);
                    Log.d(HdmiUtil.TAG, "SurfaceTextureListener onSurfaceTextureAvailable mPreviewSurface = " + HdmiUtil.this.mPreviewSurface);
                }

                public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
                    Log.d(HdmiUtil.TAG, "SurfaceTextureListener onSurfaceTextureSizeChanged");
                }

                public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                    Log.d(HdmiUtil.TAG, "SurfaceTextureListener onSurfaceTextureDestroyed");
                    return false;
                }

                public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
                    Log.d(HdmiUtil.TAG, "SurfaceTextureListener onSurfaceTextureUpdated");
                }
            });
        }
        startCameraThread();
        AnonymousClass2 r4 = new CameraDevice.StateCallback() {
            public void onOpened(CameraDevice cameraDevice) {
                CameraDevice unused = HdmiUtil.this.mCameraDevice = cameraDevice;
            }

            public void onDisconnected(CameraDevice cameraDevice) {
                CameraDevice unused = HdmiUtil.this.mCameraDevice = null;
            }

            public void onError(CameraDevice cameraDevice, int i) {
                cameraDevice.close();
                CameraDevice unused = HdmiUtil.this.mCameraDevice = null;
            }
        };
        this.mStateCallback = r4;
        try {
            this.mCameraManager.openCamera(this.mCameraId, r4, this.mCameraHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        this.mContext.getMainThreadHandler().postDelayed(new Runnable() {
            public void run() {
                HdmiUtil.this.createCaptureSession();
                HdmiUtil.this.startPreview();
            }
        }, 300);
    }

    public void closeCamera() {
        this.mHasOpen = false;
        stopPreview();
        closeCaptureSession();
        CameraDevice cameraDevice = this.mCameraDevice;
        if (cameraDevice != null) {
            cameraDevice.close();
            this.mCameraDevice = null;
        }
        stopCameraThread();
    }

    /* access modifiers changed from: private */
    public void createCaptureSession() {
        Log.d(TAG, "createCaptureSession mPreviewSurface = " + this.mPreviewSurface);
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.mPreviewSurface);
        AnonymousClass4 r1 = new CameraCaptureSession.StateCallback() {
            public void onConfigured(CameraCaptureSession cameraCaptureSession) {
                CameraCaptureSession unused = HdmiUtil.this.mCameraCaptureSession = cameraCaptureSession;
            }

            public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
                CameraCaptureSession unused = HdmiUtil.this.mCameraCaptureSession = null;
            }
        };
        try {
            CameraDevice cameraDevice = this.mCameraDevice;
            if (cameraDevice != null) {
                cameraDevice.createCaptureSession(arrayList, r1, this.mCameraHandler);
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void closeCaptureSession() {
        CameraCaptureSession cameraCaptureSession = this.mCameraCaptureSession;
        if (cameraCaptureSession != null) {
            cameraCaptureSession.close();
            this.mCameraCaptureSession = null;
        }
    }

    /* access modifiers changed from: private */
    public void startPreview() {
        try {
            CameraDevice cameraDevice = this.mCameraDevice;
            if (cameraDevice != null && this.mCameraCaptureSession != null) {
                CaptureRequest.Builder createCaptureRequest = cameraDevice.createCaptureRequest(1);
                createCaptureRequest.addTarget(this.mPreviewSurface);
                this.mCameraCaptureSession.setRepeatingRequest(createCaptureRequest.build(), new CameraCaptureSession.CaptureCallback() {
                }, this.mCameraHandler);
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void stopPreview() {
        try {
            CameraCaptureSession cameraCaptureSession = this.mCameraCaptureSession;
            if (cameraCaptureSession != null) {
                cameraCaptureSession.stopRepeating();
                this.mCameraCaptureSession = null;
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x005e A[SYNTHETIC, Splitter:B:18:0x005e] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0069 A[SYNTHETIC, Splitter:B:23:0x0069] */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setSignalState(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "setSignalState = "
            r0.append(r1)
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "HdmiUtil"
            android.util.Log.d(r1, r0)
            java.io.File r0 = new java.io.File
            java.lang.String r2 = r5.mPath
            r0.<init>(r2)
            boolean r2 = r0.exists()
            if (r2 == 0) goto L_0x0072
            r2 = 0
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0058 }
            r3.<init>(r0)     // Catch:{ Exception -> 0x0058 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0053, all -> 0x0050 }
            r2.<init>()     // Catch:{ Exception -> 0x0053, all -> 0x0050 }
            java.lang.String r4 = "exist "
            r2.append(r4)     // Catch:{ Exception -> 0x0053, all -> 0x0050 }
            boolean r0 = r0.exists()     // Catch:{ Exception -> 0x0053, all -> 0x0050 }
            r2.append(r0)     // Catch:{ Exception -> 0x0053, all -> 0x0050 }
            java.lang.String r0 = r2.toString()     // Catch:{ Exception -> 0x0053, all -> 0x0050 }
            android.util.Log.d(r1, r0)     // Catch:{ Exception -> 0x0053, all -> 0x0050 }
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ Exception -> 0x0053, all -> 0x0050 }
            byte[] r6 = r6.getBytes()     // Catch:{ Exception -> 0x0053, all -> 0x0050 }
            r3.write(r6)     // Catch:{ Exception -> 0x0053, all -> 0x0050 }
            r3.close()     // Catch:{ IOException -> 0x0062 }
            goto L_0x0072
        L_0x0050:
            r6 = move-exception
            r2 = r3
            goto L_0x0067
        L_0x0053:
            r6 = move-exception
            r2 = r3
            goto L_0x0059
        L_0x0056:
            r6 = move-exception
            goto L_0x0067
        L_0x0058:
            r6 = move-exception
        L_0x0059:
            r6.printStackTrace()     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x0072
            r2.close()     // Catch:{ IOException -> 0x0062 }
            goto L_0x0072
        L_0x0062:
            r6 = move-exception
            r6.printStackTrace()
            goto L_0x0072
        L_0x0067:
            if (r2 == 0) goto L_0x0071
            r2.close()     // Catch:{ IOException -> 0x006d }
            goto L_0x0071
        L_0x006d:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0071:
            throw r6
        L_0x0072:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.eventcenter.HdmiUtil.setSignalState(java.lang.String):void");
    }
}
