package com.szchoiceway.camera;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.IOException;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = "CameraPreview";
    private long initVideoParam = 0;
    private Camera mCamera;
    private SurfaceHolder mHolder;

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    public CameraPreview(Context context, Camera camera, long j) {
        super(context);
        this.mCamera = camera;
        this.initVideoParam = j;
        SurfaceHolder holder = getHolder();
        this.mHolder = holder;
        holder.addCallback(this);
        this.mHolder.setType(3);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Camera camera = this.mCamera;
        if (camera != null) {
            try {
                camera.setDisplayOrientation(0);
                this.mCamera.setPreviewDisplay(surfaceHolder);
                this.mCamera.startPreview();
                CamerasSignalDetection.SetCon_bri_hue(this.initVideoParam);
            } catch (IOException e) {
                Log.d(TAG, "Error setting camera preview: " + e.getMessage());
            }
        } else {
            CameraService.setSurface(surfaceHolder.getSurface());
        }
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0019 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void surfaceChanged(android.view.SurfaceHolder r1, int r2, int r3, int r4) {
        /*
            r0 = this;
            java.lang.String r2 = "CameraPreview"
            java.lang.String r3 = "surfaceChanged"
            android.util.Log.d(r2, r3)
            android.hardware.Camera r3 = r0.mCamera
            if (r3 == 0) goto L_0x0040
            android.view.SurfaceHolder r1 = r0.mHolder
            android.view.Surface r1 = r1.getSurface()
            if (r1 != 0) goto L_0x0014
            return
        L_0x0014:
            android.hardware.Camera r1 = r0.mCamera     // Catch:{ Exception -> 0x0019 }
            r1.stopPreview()     // Catch:{ Exception -> 0x0019 }
        L_0x0019:
            android.hardware.Camera r1 = r0.mCamera     // Catch:{ Exception -> 0x0026 }
            android.view.SurfaceHolder r3 = r0.mHolder     // Catch:{ Exception -> 0x0026 }
            r1.setPreviewDisplay(r3)     // Catch:{ Exception -> 0x0026 }
            android.hardware.Camera r1 = r0.mCamera     // Catch:{ Exception -> 0x0026 }
            r1.startPreview()     // Catch:{ Exception -> 0x0026 }
            goto L_0x004b
        L_0x0026:
            r1 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Error starting camera preview: "
            r3.append(r4)
            java.lang.String r1 = r1.getMessage()
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            android.util.Log.d(r2, r1)
            goto L_0x004b
        L_0x0040:
            r2 = 0
            com.szchoiceway.camera.CameraService.setSurface(r2)
            android.view.Surface r1 = r1.getSurface()
            com.szchoiceway.camera.CameraService.setSurface(r1)
        L_0x004b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.camera.CameraPreview.surfaceChanged(android.view.SurfaceHolder, int, int, int):void");
    }

    public void stopPreview() {
        try {
            Camera camera = this.mCamera;
            if (camera != null) {
                camera.stopPreview();
            }
        } catch (Exception unused) {
        }
    }
}
