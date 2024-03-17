package com.szchoiceway.eventcenter;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.UserHandle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.mylibrary.BuildConfig;
import com.szchoiceway.camera.CameraPreview;
import com.szchoiceway.camera.CameraService;
import com.szchoiceway.camera.CamerasSignalDetection;
import com.szchoiceway.eventcenter.EventUtils;
import java.io.File;
import java.util.Timer;

public class BackcarEvent implements View.OnTouchListener, View.OnClickListener {
    private static final int AUTO_FULLSCREEN = 4;
    private static final int EVENT_OPEN_CAMERA = 512;
    private static final int EVENT_RELEASE_LOCK_CAMERA = 513;
    private static final String TAG = "BackcarEvent";
    private static final int WM_OPEN_CAMARA_EVT = 3;
    private static final int WM_SIGNAL_EVT = 2;
    /* access modifiers changed from: private */
    public static int iCamaraSignalValidCnt = 0;
    private static int iHaveTrack = -1;
    /* access modifiers changed from: private */
    public static int mNoSignalCount;
    private static int mSignalCount;
    /* access modifiers changed from: private */
    public boolean bCamaraOpen = false;
    /* access modifiers changed from: private */
    public boolean bDisRightOrLeftCamara = true;
    private Button btnTouch_Cheku = null;
    private Button btnTouch_KeSaiWei = null;
    /* access modifiers changed from: private */
    public NativeCamera camera = new NativeCamera();
    /* access modifiers changed from: private */
    public float downX = 0.0f;
    /* access modifiers changed from: private */
    public float downY = 0.0f;
    Handler handleProgress = new Handler() {
        public void handleMessage(Message message) {
            int i = message.what;
            boolean z = true;
            if (i == 2) {
                if (BackcarEvent.this.mEventContext.m_bWheelTrack) {
                    if (BackcarEvent.this.mEventContext.m_iCanbustype == 20 && BackcarEvent.this.mEventContext.m_iCarstype_ID == 4 && BackcarEvent.this.mEventContext.m_iCarCanbusName_ID == 5) {
                        BackcarEvent.this.ShowWgt(R.id.vwBackTrack, false);
                    } else if (BackcarEvent.this.bDisRightOrLeftCamara) {
                        BackcarEvent.this.ShowWgt(R.id.vwBackTrack, true);
                    } else if (BackcarEvent.this.mvwBackTrack != null) {
                        BackcarEvent.this.mvwBackTrack.setVisibility(8);
                    }
                }
                if (BackcarEvent.iCamaraSignalValidCnt > 20) {
                    BackcarEvent backcarEvent = BackcarEvent.this;
                    if (backcarEvent.signalState > 0) {
                        z = false;
                    }
                    backcarEvent.ShowWgt(R.id.NoSignalTip, z);
                }
                if (BackcarEvent.this.mBoruizenghengBlueBk == null) {
                    return;
                }
                if (BackcarEvent.this.signalState > 0) {
                    BackcarEvent.this.mBoruizenghengBlueBk.setVisibility(8);
                } else {
                    BackcarEvent.this.mBoruizenghengBlueBk.setVisibility(0);
                }
            } else if (i != 3) {
                if (i == 4) {
                    if (!BackcarEvent.this.mbFullsrceen) {
                        BackcarEvent.this.fullsrceenPlay(true);
                    }
                    sendEmptyMessageDelayed(4, 5000);
                } else if (i == 513) {
                    boolean unused = BackcarEvent.this.mIsCameraLocked = false;
                } else if (i == 2570) {
                    BackcarEvent.this.removeCameraPipView();
                }
            } else if (BackcarEvent.this.mCameraPipView == null) {
            } else {
                if (BackcarEvent.this.mEventContext.m_iUITypeVer == 1 || BackcarEvent.this.mEventContext.m_iUITypeVer == 3 || BackcarEvent.this.mEventContext.m_iUITypeVer == 32 || BackcarEvent.this.mEventContext.m_iUITypeVer == 39 || BackcarEvent.this.mEventContext.m_iUITypeVer == 37 || BackcarEvent.this.mEventContext.m_iUITypeVer == 41 || BackcarEvent.this.mEventContext.m_iUITypeVer == 42) {
                    BackcarEvent.this.mCameraPipView.setBackgroundColor(Color.parseColor("#ff060606"));
                } else if (BackcarEvent.this.mEventContext.m_iUITypeVer == 36 || BackcarEvent.this.mEventContext.m_iUITypeVer == 35 || BackcarEvent.this.mEventContext.m_iUITypeVer == 40 || BackcarEvent.this.mEventContext.m_iUITypeVer == 38 || BackcarEvent.this.mEventContext.m_iUITypeVer == 43 || BackcarEvent.this.mEventContext.m_iUITypeVer == 44 || BackcarEvent.this.mEventContext.m_iUITypeVer == 45) {
                    BackcarEvent.this.mCameraPipView.setBackgroundColor(Color.parseColor("#ff130513"));
                } else {
                    BackcarEvent.this.mCameraPipView.setBackgroundColor(Color.parseColor("#ff130513"));
                }
            }
        }
    };
    private boolean isEnable = false;
    /* access modifiers changed from: private */
    public final Runnable longClickRunnable = new Runnable() {
        public final void run() {
            BackcarEvent.this.lambda$new$0$BackcarEvent();
        }
    };
    private TextView mAVShow_boruizongheng = null;
    private CheckBox mAboutMirror = null;
    /* access modifiers changed from: private */
    public TextView mAboutMirrorText = null;
    /* access modifiers changed from: private */
    public int mBackcarCameraMode = -1;
    /* access modifiers changed from: private */
    public int mBackcarVideoSettings = 0;
    /* access modifiers changed from: private */
    public ImageView mBoruizenghengBlueBk = null;
    private Camera mCamera;
    private Button mCamera1 = null;
    private Button mCamera2 = null;
    private Button mCamera3 = null;
    private String mCameraChannel = "0";
    /* access modifiers changed from: private */
    public CamerasSignalDetection mCameraDetection = null;
    private long mCameraOpenTimeElapse = 0;
    private int mCameraOwner = -1;
    /* access modifiers changed from: private */
    public View mCameraPipView = null;
    private CameraPreview mCameraPreview = null;
    /* access modifiers changed from: private */
    public int mCameraStatus = 0;
    private Button mCloseCamera = null;
    /* access modifiers changed from: private */
    public EventService mContext = null;
    private Button mDepressionAngle = null;
    /* access modifiers changed from: private */
    public EventService mEventContext = null;
    private Button mFullWide = null;
    private ImageView mImageView = null;
    /* access modifiers changed from: private */
    public boolean mInBackcarMode = false;
    public boolean mInCameraPipView = false;
    /* access modifiers changed from: private */
    public boolean mIsCameraLocked = false;
    /* access modifiers changed from: private */
    public boolean mIsOpenCamera;
    private RelativeLayout mLytBottom_boruizongheng = null;
    private final Object mObject = new Object();
    private RadioGroup.OnCheckedChangeListener mOnVideoChangedListener = new RadioGroup.OnCheckedChangeListener() {
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i) {
                case R.id.rdbAhd /*2131231058*/:
                    int unused = BackcarEvent.this.mBackcarVideoSettings = 3;
                    break;
                case R.id.rdbAuto /*2131231059*/:
                    int unused2 = BackcarEvent.this.mBackcarVideoSettings = 0;
                    break;
                case R.id.rdbNtsc /*2131231060*/:
                    int unused3 = BackcarEvent.this.mBackcarVideoSettings = 2;
                    break;
                case R.id.rdbPal /*2131231061*/:
                    int unused4 = BackcarEvent.this.mBackcarVideoSettings = 1;
                    break;
            }
            BackcarEvent.this.closeCamera();
            BackcarEvent.this.mCameraDetection.pauseDetectionForWhile(500);
            if (BackcarEvent.this.mBackcarVideoSettings != 0) {
                CamerasSignalDetection.setRN6752Mode(BackcarEvent.this.mBackcarVideoSettings);
                SysProviderOpt sysProviderOpt = BackcarEvent.this.mContext.mSysProviderOpt;
                sysProviderOpt.updateRecord(SysProviderOpt.SYS_BACKCAR_6752_VIDEO_TYPE, BuildConfig.FLAVOR + BackcarEvent.this.mBackcarVideoSettings);
                SysProviderOpt sysProviderOpt2 = BackcarEvent.this.mContext.mSysProviderOpt;
                sysProviderOpt2.updateRecord(SysProviderOpt.SYS_BACKCAR_VIDEO_TYPE, BuildConfig.FLAVOR + BackcarEvent.this.mBackcarVideoSettings);
                BackcarEvent backcarEvent = BackcarEvent.this;
                int unused5 = backcarEvent.mBackcarCameraMode = backcarEvent.mBackcarVideoSettings;
                return;
            }
            int unused6 = BackcarEvent.this.mBackcarCameraMode = -1;
            CamerasSignalDetection.setRN6752Mode(0);
            BackcarEvent.this.mContext.mSysProviderOpt.updateRecord(SysProviderOpt.SYS_BACKCAR_6752_VIDEO_TYPE, "-1");
            BackcarEvent.this.mContext.mSysProviderOpt.updateRecord(SysProviderOpt.SYS_BACKCAR_VIDEO_TYPE, "0");
        }
    };
    private FrameLayout mPreViewLayout = null;
    private CameraPreview mPreview;
    private View mRootView = null;
    int mScreenHeight;
    int mScreenWidth;
    private Button mStandard = null;
    private ImageView mSurfaceView = null;
    private ImageView mVwTouchBtn = null;
    private Button mWideAngle = null;
    int mX;
    int mY;
    /* access modifiers changed from: private */
    public boolean mbFullsrceen = false;
    int miBrightVal = 230;
    int miContrastVal = 17;
    int miHueVal = 137;
    private View mlytNosignalPane = null;
    private ImageView mvwBackCarAid = null;
    /* access modifiers changed from: private */
    public ImageView mvwBackTrack = null;
    /* access modifiers changed from: private */
    public int signalState = 0;
    private Timer timer = null;
    private WindowManager wm = null;
    /* access modifiers changed from: private */
    public WindowManager.LayoutParams wmCameraPipParams = new WindowManager.LayoutParams();

    static /* synthetic */ int access$1908() {
        int i = iCamaraSignalValidCnt;
        iCamaraSignalValidCnt = i + 1;
        return i;
    }

    static /* synthetic */ int access$308() {
        int i = mNoSignalCount;
        mNoSignalCount = i + 1;
        return i;
    }

    public void startCameraDetection() {
        CamerasSignalDetection camerasSignalDetection = this.mCameraDetection;
        if (camerasSignalDetection != null) {
            camerasSignalDetection.startDetection();
        }
    }

    public void stopCameraDetection() {
        CamerasSignalDetection camerasSignalDetection = this.mCameraDetection;
        if (camerasSignalDetection != null) {
            camerasSignalDetection.stopDetection();
        }
    }

    private void initCameraDetection() {
        Log.i(TAG, "initCameraDetection: ");
        this.mCameraDetection = new CamerasSignalDetection(new CamerasSignalDetection.OnCameraStatusChangedListener() {
            public void OnCameraStatusChanged(int i) {
                Log.i(BackcarEvent.TAG, "OnCameraStatusChanged: status = " + i + ", mInBackcarMode = " + BackcarEvent.this.mInBackcarMode);
                if (!BackcarEvent.this.mInBackcarMode) {
                    BackcarEvent.this.mContext.notifyValidModeEvt(EventUtils.EVENT_CAMERA_STATUS, i, 0, (byte[]) null, (String) null);
                    return;
                }
                Log.i(BackcarEvent.TAG, "OnCameraStatusChanged: mCameraStatus = " + BackcarEvent.this.mCameraStatus);
                int access$200 = BackcarEvent.this.mCameraStatus;
                if (access$200 != 0) {
                    if (access$200 == 1 || access$200 == 2 || access$200 == 3) {
                        int unused = BackcarEvent.mNoSignalCount = 0;
                        if (i != BackcarEvent.this.mCameraStatus) {
                            if (BackcarEvent.this.mBackcarVideoSettings == 0) {
                                CamerasSignalDetection.setRN6752Mode(0);
                                SysProviderOpt sysProviderOpt = BackcarEvent.this.mContext.mSysProviderOpt;
                                sysProviderOpt.updateRecord(SysProviderOpt.SYS_BACKCAR_6752_VIDEO_TYPE, BuildConfig.FLAVOR + i);
                                int unused2 = BackcarEvent.this.mBackcarCameraMode = i;
                            }
                            BackcarEvent.this.closeCamera();
                        }
                    }
                } else if (i == 0) {
                    Log.i(BackcarEvent.TAG, "OnCameraStatusChanged: mNoSignalCount = " + BackcarEvent.mNoSignalCount);
                    if (BackcarEvent.mNoSignalCount < 31) {
                        BackcarEvent.access$308();
                    }
                    if (BackcarEvent.mNoSignalCount == 25) {
                        BackcarEvent.this.handleProgress.post(new Runnable() {
                            public void run() {
                                BackcarEvent.this.ShowWgt(R.id.NoSignalTip, true);
                            }
                        });
                    } else if (BackcarEvent.mNoSignalCount == 30) {
                        BackcarEvent.this.handleProgress.post(new Runnable() {
                            public void run() {
                                BackcarEvent.this.ShowWgt(R.id.NoSignalTip, true);
                            }
                        });
                    }
                } else {
                    if (BackcarEvent.this.mBackcarCameraMode != i && BackcarEvent.this.mBackcarVideoSettings == 0 && CamerasSignalDetection.isRN6752()) {
                        CamerasSignalDetection.setRN6752Mode(0);
                        SysProviderOpt sysProviderOpt2 = BackcarEvent.this.mContext.mSysProviderOpt;
                        sysProviderOpt2.updateRecord(SysProviderOpt.SYS_BACKCAR_6752_VIDEO_TYPE, BuildConfig.FLAVOR + i);
                        int unused3 = BackcarEvent.this.mBackcarCameraMode = i;
                    }
                    BackcarEvent.this.openCamera();
                    if (BackcarEvent.this.mIsOpenCamera) {
                        int unused4 = BackcarEvent.this.mCameraStatus = i;
                    }
                }
            }
        }, false);
    }

    public int getCameraOwner() {
        int i;
        synchronized (this) {
            i = this.mCameraOwner;
        }
        return i;
    }

    public void setCameraOwner(int i) {
        synchronized (this) {
            this.mCameraOwner = i;
        }
    }

    /* access modifiers changed from: private */
    public void openCamera() {
        Log.i(TAG, "openCamera: mIsOpenCamera = " + this.mIsOpenCamera + ",mIsCameraLocked = " + this.mIsCameraLocked);
        if (!this.mIsOpenCamera && !this.mIsCameraLocked) {
            if (!this.mCameraChannel.equals(CamerasSignalDetection.CAMERA_CHANNEL_BACKCAR)) {
                setCameraChannel(CamerasSignalDetection.CAMERA_CHANNEL_BACKCAR);
            }
            if (getCameraOwner() == -1 || getCameraOwner() == 0) {
                setCameraOwner(0);
                if (Build.MODEL.equals("rk3399")) {
                    boolean recordBoolean = this.mContext.mSysProviderOpt.getRecordBoolean(SysProviderOpt.SYS_REVERSE_IMAGE, false);
                    Log.i(TAG, "openCamera: reverseImage = " + recordBoolean);
                    if (recordBoolean) {
                        CameraService.openCamera("state=1,tw8836_cvbs_mode=1,rotate=360");
                    } else {
                        CameraService.openCamera("state=1,tw8836_cvbs_mode=1,rotate=0");
                    }
                } else if (this.mCamera == null) {
                    try {
                        Log.d(TAG, "Camera.getNumberOfCameras() " + Camera.getNumberOfCameras());
                        this.mCamera = Camera.open(0);
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                if (this.mCameraPreview == null) {
                    CameraPreview cameraPreview = new CameraPreview(this.mContext, this.mCamera, (long) ((this.miBrightVal << 16) + (this.miContrastVal << 8) + 0));
                    this.mCameraPreview = cameraPreview;
                    cameraPreview.setLayoutParams(new WindowManager.LayoutParams(-1, -1));
                }
                if (this.mPreViewLayout == null) {
                    FrameLayout frameLayout = (FrameLayout) this.mCameraPipView.findViewById(R.id.camera_preview);
                    this.mPreViewLayout = frameLayout;
                    if (frameLayout != null) {
                        frameLayout.setOnTouchListener(this);
                        this.mPreViewLayout.addView(this.mCameraPreview);
                    }
                }
                this.mIsOpenCamera = true;
                this.handleProgress.post(new Runnable() {
                    public void run() {
                        BackcarEvent.this.ShowWgt(R.id.NoSignalTip, false);
                    }
                });
                Log.d(TAG, "openCamera");
                return;
            }
            this.mContext.sendBroadcastAsUser(new Intent(EventUtils.ACTION_FORCE_CAMERA_CLOSE), UserHandle.ALL);
        }
    }

    /* access modifiers changed from: private */
    public void closeCamera() {
        mNoSignalCount = 0;
        Log.i(TAG, "closeCamera: mIsOpenCamera = " + this.mIsOpenCamera);
        if (this.mIsOpenCamera) {
            if (Build.MODEL.equals("rk3399")) {
                CameraService.setSurface((Surface) null);
            }
            this.mCameraPreview.stopPreview();
            this.mPreViewLayout.removeView(this.mCameraPreview);
            if (Build.MODEL.equals("rk3399")) {
                CameraService.closeCamera();
            } else {
                Camera camera2 = this.mCamera;
                if (camera2 != null) {
                    camera2.release();
                    this.mCamera = null;
                }
            }
            this.mCameraStatus = 0;
            this.mCameraPreview = null;
            this.mPreViewLayout = null;
            this.mIsOpenCamera = false;
            mNoSignalCount = 0;
            mSignalCount = 0;
            Log.d(TAG, "closeCamera mNoSignalCount = " + mNoSignalCount);
        }
    }

    public boolean setCameraChannel(String str) {
        CamerasSignalDetection camerasSignalDetection = this.mCameraDetection;
        if (camerasSignalDetection == null) {
            return false;
        }
        this.mCameraChannel = str;
        return camerasSignalDetection.setCameraChannel(str);
    }

    private void releaseCamera() {
        closeCamera();
        Log.d(TAG, "releaseCamera");
        mSignalCount = 0;
        setCameraOwner(-1);
        this.mIsCameraLocked = true;
        View view = this.mlytNosignalPane;
        if (view != null) {
            view.setVisibility(8);
        }
        this.handleProgress.sendEmptyMessageDelayed(513, 100);
    }

    public BackcarEvent(EventService eventService) {
        this.mContext = eventService;
        this.mEventContext = eventService;
        int recordInteger = eventService.mSysProviderOpt.getRecordInteger(SysProviderOpt.SYS_BACKCAR_VIDEO_TYPE, 0);
        this.mBackcarVideoSettings = recordInteger;
        if (recordInteger == 0) {
            this.mBackcarCameraMode = this.mEventContext.mSysProviderOpt.getRecordInteger(SysProviderOpt.SYS_BACKCAR_6752_VIDEO_TYPE, -1);
            CamerasSignalDetection.setRN6752Mode(0);
        } else {
            this.mBackcarCameraMode = recordInteger;
            SysProviderOpt sysProviderOpt = this.mEventContext.mSysProviderOpt;
            sysProviderOpt.updateRecord(SysProviderOpt.SYS_BACKCAR_6752_VIDEO_TYPE, BuildConfig.FLAVOR + this.mBackcarVideoSettings);
            CamerasSignalDetection.setRN6752Mode(this.mBackcarCameraMode);
        }
        initBackcarWnd();
        int recordInteger2 = this.mEventContext.mSysProviderOpt.getRecordInteger(SysProviderOpt.KSW_REVEERSING_TYPE_SELECT_INDEX, 0);
        Log.i(TAG, "BackcarEvent: reversingType = " + recordInteger2);
        if (Build.VERSION.SDK_INT <= 19) {
            return;
        }
        if (this.mContext.m_iUITypeVer != 41 || "DaoHang".equalsIgnoreCase(this.mContext.xml_client) || recordInteger2 != 0) {
            initCameraDetection();
        }
    }

    public void startBackcar(int i) {
        if (!this.mInBackcarMode) {
            this.mInBackcarMode = true;
            startCameraDetection();
            showBackcarWnd(i);
            Log.i(TAG, "startBackcar bBackcarCamera = " + i);
        }
    }

    public void endBackcar() {
        if (this.mInBackcarMode) {
            this.mInBackcarMode = false;
            stopCameraDetection();
            hideBackcarWnd();
            Log.i(TAG, "endBackcar 0");
            iCamaraSignalValidCnt = 0;
            ShowWgt(R.id.NoSignalTip, false);
        }
    }

    private void initBackcarWnd() {
        boolean z = false;
        createCameraPipView(0);
        this.mlytNosignalPane = this.mCameraPipView.findViewById(R.id.NoSignalTip);
        RadioGroup radioGroup = (RadioGroup) this.mCameraPipView.findViewById(R.id.radioGroupVideoSignalType);
        File file = new File(CamerasSignalDetection.RN6752_PATH);
        if (radioGroup != null && file.exists()) {
            radioGroup.setVisibility(0);
            ((RadioButton) radioGroup.findViewById(R.id.rdbAuto)).setChecked(this.mBackcarVideoSettings == 0);
            ((RadioButton) radioGroup.findViewById(R.id.rdbPal)).setChecked(this.mBackcarVideoSettings == 1);
            ((RadioButton) radioGroup.findViewById(R.id.rdbNtsc)).setChecked(this.mBackcarVideoSettings == 2);
            RadioButton radioButton = (RadioButton) radioGroup.findViewById(R.id.rdbAhd);
            if (this.mBackcarVideoSettings == 3) {
                z = true;
            }
            radioButton.setChecked(z);
            radioGroup.setOnCheckedChangeListener(this.mOnVideoChangedListener);
        }
    }

    private void showBackcarWnd(int i) {
        View view = this.mCameraPipView;
        if (view != null) {
            view.setBackgroundColor(Color.parseColor("#ff000000"));
        }
        if (i == 1 || i == 3) {
            this.bDisRightOrLeftCamara = false;
        } else {
            this.bDisRightOrLeftCamara = true;
        }
        EventService eventService = this.mEventContext;
        if (eventService != null) {
            eventService.getSwitchMirror();
        }
        if (this.mEventContext.m_iUITypeVer == 101 || this.mEventContext.m_iUITypeVer == 48) {
            Log.i(TAG, "showBackcarWnd: mEventContext.mCurrSendMode.getIntValue() = " + this.mEventContext.mCurrSendMode.getIntValue());
            if (this.mEventContext.mCurrSendMode.getIntValue() != EventUtils.eSrcMode.SRC_BACKCAR.getIntValue()) {
                setSignalChannal(1);
            } else if (this.mEventContext.mBackCar6752Type == 0) {
                setSignalChannal(1);
            } else if (this.mEventContext.mBackCar6752Type == 1) {
                setDVDSignalChannal();
            } else {
                setSignalChannal(1);
            }
        } else {
            setSignalChannal(1);
        }
        addCameraPipView();
        setVehicle(true);
        Handler handler = this.handleProgress;
        if (handler != null) {
            handler.removeMessages(3);
            this.handleProgress.sendEmptyMessageDelayed(3, 1000);
        }
    }

    private void hideBackcarWnd() {
        setVehicle(false);
        View view = this.mCameraPipView;
        if (view != null) {
            view.setBackgroundColor(Color.parseColor("#ff000000"));
        }
        releaseCamera();
        Handler handler = this.handleProgress;
        if (handler != null) {
            handler.removeMessages(3);
        }
        iCamaraSignalValidCnt = 0;
        removeCameraPipView();
    }

    public void createCameraPipView(int i) {
        if (this.mCameraPipView == null) {
            if (this.mEventContext.m_iUITypeVer == 41) {
                this.mCameraPipView = View.inflate(this.mContext, R.layout.layout_backcar_kesaiwei, (ViewGroup) null);
            } else if (this.mEventContext.m_iUITypeVer == 101) {
                this.mCameraPipView = View.inflate(this.mContext, R.layout.changtong_1920x720_layout_backcar, (ViewGroup) null);
            } else if (this.mEventContext.m_iUITypeVer == 48) {
                this.mCameraPipView = View.inflate(this.mContext, R.layout.chwy_1280x480_layout_backcar, (ViewGroup) null);
            } else {
                this.mCameraPipView = View.inflate(this.mContext, R.layout.layout_backcar, (ViewGroup) null);
            }
            this.mvwBackTrack = (ImageView) this.mCameraPipView.findViewById(R.id.vwBackTrack);
            this.mStandard = (Button) this.mCameraPipView.findViewById(R.id.BtnStandard);
            this.mWideAngle = (Button) this.mCameraPipView.findViewById(R.id.BtnWideAngle);
            this.mDepressionAngle = (Button) this.mCameraPipView.findViewById(R.id.BtnDepressionAngle);
            this.mFullWide = (Button) this.mCameraPipView.findViewById(R.id.BtnFullWide);
            this.mvwBackCarAid = (ImageView) this.mCameraPipView.findViewById(R.id.ImgBackCarAid);
            this.mCamera1 = (Button) this.mCameraPipView.findViewById(R.id.BtnCamera1);
            this.mCamera2 = (Button) this.mCameraPipView.findViewById(R.id.BtnCamera2);
            this.mCamera3 = (Button) this.mCameraPipView.findViewById(R.id.BtnCamera3);
            this.mCloseCamera = (Button) this.mCameraPipView.findViewById(R.id.BtnCameraClose);
            this.mAboutMirror = (CheckBox) this.mCameraPipView.findViewById(R.id.ChkAboutMirror);
            this.mAboutMirrorText = (TextView) this.mCameraPipView.findViewById(R.id.tvAboutMirror);
            this.mAVShow_boruizongheng = (TextView) this.mCameraPipView.findViewById(R.id.tvAVshow_boruizongheng);
            this.mLytBottom_boruizongheng = (RelativeLayout) this.mCameraPipView.findViewById(R.id.lytBottom_boruizongheng);
            this.mVwTouchBtn = (ImageView) this.mCameraPipView.findViewById(R.id.VwTouchBtn);
            this.mSurfaceView = (ImageView) this.mCameraPipView.findViewById(R.id.ViewSurface);
            this.mBoruizenghengBlueBk = (ImageView) this.mCameraPipView.findViewById(R.id.ivBoruizenghengBlueBk);
            this.btnTouch_Cheku = (Button) this.mCameraPipView.findViewById(R.id.btnTouch_Cheku);
            ImageView imageView = this.mVwTouchBtn;
            if (imageView != null) {
                imageView.setOnTouchListener(this);
            }
            ImageView imageView2 = this.mSurfaceView;
            if (imageView2 != null) {
                imageView2.setOnClickListener(this);
            }
            Button button = this.mCamera1;
            if (button != null) {
                button.setOnClickListener(this);
            }
            Button button2 = this.mCamera2;
            if (button2 != null) {
                button2.setOnClickListener(this);
            }
            Button button3 = this.mCamera3;
            if (button3 != null) {
                button3.setOnClickListener(this);
            }
            Button button4 = this.mCloseCamera;
            if (button4 != null) {
                button4.setOnClickListener(this);
            }
            Button button5 = this.btnTouch_KeSaiWei;
            if (button5 != null) {
                button5.setOnClickListener(this);
            }
            Button button6 = this.btnTouch_Cheku;
            if (button6 != null) {
                button6.setOnClickListener(this);
            }
            if (this.mAboutMirror != null) {
                EventService eventService = this.mEventContext;
                if (eventService != null) {
                    boolean switchMirror = eventService.getSwitchMirror();
                    CheckBox checkBox = this.mAboutMirror;
                    if (checkBox != null) {
                        checkBox.setChecked(switchMirror);
                        if (switchMirror) {
                            setRoteta(2);
                        } else {
                            setRoteta(0);
                        }
                        if (Build.VERSION.SDK_INT <= 19) {
                            NativeCamera.setCameraRect(0, 0, 1024, 600);
                        }
                    }
                    TextView textView = this.mAboutMirrorText;
                    if (textView != null) {
                        if (switchMirror) {
                            textView.setText(R.string.lb_BR_Mirror);
                        } else {
                            textView.setText(R.string.lb_BR_Normal);
                        }
                    }
                }
                this.mAboutMirror.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        if (BackcarEvent.this.mAboutMirrorText != null) {
                            if (z) {
                                BackcarEvent.this.mAboutMirrorText.setText(R.string.lb_BR_Mirror);
                            } else {
                                BackcarEvent.this.mAboutMirrorText.setText(R.string.lb_BR_Normal);
                            }
                        }
                        if (BackcarEvent.this.mEventContext != null) {
                            if (BackcarEvent.this.camera != null) {
                                if (z) {
                                    BackcarEvent.this.setRoteta(2);
                                    Log.i(BackcarEvent.TAG, "--->>> Mirror = true");
                                } else {
                                    BackcarEvent.this.setRoteta(0);
                                    Log.i(BackcarEvent.TAG, "--->>> Mirror = false");
                                }
                                if (Build.VERSION.SDK_INT <= 19) {
                                    NativeCamera unused = BackcarEvent.this.camera;
                                    NativeCamera.setCameraRect(0, 0, 1024, 600);
                                }
                            }
                            BackcarEvent.this.mEventContext.setSwitchMirror(z);
                        }
                    }
                });
            }
            if (!this.mEventContext.m_bWheelTrack) {
                ImageView imageView3 = this.mvwBackTrack;
                if (imageView3 != null) {
                    imageView3.setVisibility(8);
                }
            } else if (this.mEventContext.m_iCanbustype == 20 && this.mEventContext.m_iCarstype_ID == 4 && this.mEventContext.m_iCarCanbusName_ID == 5) {
                ImageView imageView4 = this.mvwBackTrack;
                if (imageView4 != null) {
                    imageView4.setVisibility(8);
                }
                Button button7 = this.mStandard;
                if (button7 != null) {
                    button7.setVisibility(0);
                    this.mStandard.setOnClickListener(this);
                }
                Button button8 = this.mWideAngle;
                if (button8 != null) {
                    button8.setVisibility(0);
                    this.mWideAngle.setOnClickListener(this);
                }
                Button button9 = this.mDepressionAngle;
                if (button9 != null) {
                    button9.setVisibility(0);
                    this.mDepressionAngle.setOnClickListener(this);
                }
            } else if (this.mEventContext.m_iCanbustype == 21 && this.mEventContext.m_iCarstype_ID == 4 && this.mEventContext.m_iCarCanbusName_ID == 2) {
                Button button10 = this.mStandard;
                if (button10 != null) {
                    button10.setVisibility(0);
                    this.mStandard.setOnClickListener(this);
                }
                Button button11 = this.mWideAngle;
                if (button11 != null) {
                    button11.setVisibility(0);
                    this.mWideAngle.setOnClickListener(this);
                }
                Button button12 = this.mDepressionAngle;
                if (button12 != null) {
                    button12.setVisibility(0);
                    this.mDepressionAngle.setOnClickListener(this);
                }
                Button button13 = this.mFullWide;
                if (button13 != null) {
                    button13.setVisibility(0);
                    this.mFullWide.setOnClickListener(this);
                }
            } else {
                Button button14 = this.mStandard;
                if (button14 != null) {
                    button14.setVisibility(8);
                }
                Button button15 = this.mWideAngle;
                if (button15 != null) {
                    button15.setVisibility(8);
                }
                Button button16 = this.mDepressionAngle;
                if (button16 != null) {
                    button16.setVisibility(8);
                }
            }
            if (this.mEventContext.m_bBackCarAid) {
                ImageView imageView5 = this.mvwBackCarAid;
                if (imageView5 != null) {
                    imageView5.setVisibility(0);
                }
            } else {
                ImageView imageView6 = this.mvwBackCarAid;
                if (imageView6 != null) {
                    imageView6.setVisibility(8);
                }
            }
        }
        this.mCameraPipView.setOnTouchListener(new View.OnTouchListener(2000, 50) {
            int startX = 0;
            int startY = 0;

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (BackcarEvent.this.mEventContext.m_isOff_BackLight_KSW) {
                    BackcarEvent.this.mEventContext.backLightOn_KSW();
                }
                int action = motionEvent.getAction();
                if (action == 0) {
                    Log.i(BackcarEvent.TAG, "mCameraPipView ACTION_DOWN");
                    BackcarEvent.this.handleProgress.removeCallbacks(BackcarEvent.this.longClickRunnable);
                    this.startX = (int) motionEvent.getRawX();
                    this.startY = (int) motionEvent.getRawY();
                    float unused = BackcarEvent.this.downX = motionEvent.getX();
                    float unused2 = BackcarEvent.this.downY = motionEvent.getY();
                    BackcarEvent.this.handleProgress.postDelayed(BackcarEvent.this.longClickRunnable, (long) 2000);
                    BackcarEvent.this.mEventContext.sendTouchPos((int) BackcarEvent.this.downX, (int) BackcarEvent.this.downY, true, false);
                } else if (action == 1) {
                    Log.i(BackcarEvent.TAG, "mCameraPipView ACTION_UP");
                    BackcarEvent.this.handleProgress.removeCallbacks(BackcarEvent.this.longClickRunnable);
                    BackcarEvent.this.mEventContext.sendTouchPos((int) motionEvent.getX(), (int) motionEvent.getY(), false, false);
                } else if (action == 2) {
                    Log.i(BackcarEvent.TAG, "mCameraPipView ACTION_MOVE");
                    int rawX = (int) motionEvent.getRawX();
                    int rawY = (int) motionEvent.getRawY();
                    int i = rawX - this.startX;
                    int i2 = rawY - this.startY;
                    this.startX = rawX;
                    this.startY = rawY;
                    BackcarEvent.this.wmCameraPipParams.x += i;
                    BackcarEvent.this.wmCameraPipParams.y += i2;
                    BackcarEvent.this.updateViewLayout();
                    if (Math.abs(BackcarEvent.this.downX - motionEvent.getX()) > ((float) 50) || Math.abs(BackcarEvent.this.downY - motionEvent.getY()) > ((float) 50)) {
                        BackcarEvent.this.handleProgress.removeCallbacks(BackcarEvent.this.longClickRunnable);
                        BackcarEvent.this.mEventContext.sendTouchPos((int) motionEvent.getX(), (int) motionEvent.getY(), true, false);
                    }
                }
                return false;
            }
        });
        if (this.mCameraPipView != null) {
            if (this.wm == null) {
                this.wm = (WindowManager) this.mContext.getSystemService("window");
            }
            this.wmCameraPipParams.type = 2010;
            this.wmCameraPipParams.format = 1;
            this.wmCameraPipParams.flags = 296;
            this.wmCameraPipParams.gravity = 51;
            this.wmCameraPipParams.x = 0;
            this.wmCameraPipParams.y = 0;
            this.wmCameraPipParams.width = this.mContext.getResources().getDisplayMetrics().widthPixels;
            this.wmCameraPipParams.height = this.mContext.getResources().getDisplayMetrics().heightPixels;
            Log.d(TAG, "wmCameraPipParams width = " + this.wmCameraPipParams.width + ", height = " + this.wmCameraPipParams.height);
        }
    }

    public void addCameraPipView() {
        View view;
        Log.i(TAG, "addCameraPipView: mInCameraPipView = " + this.mInCameraPipView);
        if (!this.mInCameraPipView) {
            synchronized (this.mObject) {
                WindowManager windowManager = this.wm;
                if (!(windowManager == null || (view = this.mCameraPipView) == null)) {
                    this.mInCameraPipView = true;
                    windowManager.addView(view, this.wmCameraPipParams);
                    setVehicleSize(this.wmCameraPipParams.x, this.wmCameraPipParams.y, this.wmCameraPipParams.width, this.wmCameraPipParams.height);
                }
            }
        }
    }

    public void updateViewLayout() {
        synchronized (this.mObject) {
            if (!(this.wm == null || this.mCameraPipView == null)) {
                this.wmCameraPipParams.x = 0;
                this.wmCameraPipParams.y = 0;
                this.wm.updateViewLayout(this.mCameraPipView, this.wmCameraPipParams);
                setVehicleSize(this.wmCameraPipParams.x, this.wmCameraPipParams.y, this.wmCameraPipParams.width, this.wmCameraPipParams.height);
            }
        }
    }

    public void removeCameraPipView() {
        Log.i(TAG, "removeCameraPipView: mInCameraPipView = " + this.mInCameraPipView);
        if (this.mInCameraPipView) {
            synchronized (this.mObject) {
                if (this.mCameraPipView != null) {
                    if (this.wm == null) {
                        this.wm = (WindowManager) this.mContext.getSystemService("window");
                    }
                    WindowManager windowManager = this.wm;
                    if (windowManager != null) {
                        this.mInCameraPipView = false;
                    }
                    windowManager.removeView(this.mCameraPipView);
                }
            }
        }
    }

    public void setVehicle(boolean z) {
        if (z) {
            if (this.mEventContext.m_iUITypeVer == 101 || this.mEventContext.m_iUITypeVer == 48) {
                Log.i(TAG, "setVehicle: mEventContext.mCurrSendMode.getIntValue() open = " + this.mEventContext.mCurrSendMode.getIntValue());
                if (this.mEventContext.mCurrSendMode.getIntValue() != EventUtils.eSrcMode.SRC_BACKCAR.getIntValue()) {
                    if (Build.VERSION.SDK_INT <= 19) {
                        NativeCamera.openCamera();
                    }
                    Log.i(TAG, "openCamera");
                } else if (this.mEventContext.mBackCar6752Type != 1) {
                    if (Build.VERSION.SDK_INT <= 19) {
                        NativeCamera.openCamera();
                    }
                    Log.i(TAG, "openCamera");
                }
            } else if (Build.VERSION.SDK_INT <= 19) {
                NativeCamera.openCamera();
            }
            this.bCamaraOpen = true;
            return;
        }
        if (this.mEventContext.m_iUITypeVer == 101 || this.mEventContext.m_iUITypeVer == 48) {
            Log.i(TAG, "setVehicle: mEventContext.mCurrSendMode.getIntValue() close = " + this.mEventContext.mCurrSendMode.getIntValue());
            if (this.mEventContext.mCurrSendMode.getIntValue() != EventUtils.eSrcMode.SRC_BACKCAR.getIntValue()) {
                if (Build.VERSION.SDK_INT <= 19) {
                    NativeCamera.closeCamera();
                }
                Log.i(TAG, "closeCamera");
            } else if (this.mEventContext.mBackCar6752Type == 0) {
                if (Build.VERSION.SDK_INT <= 19) {
                    NativeCamera.closeCamera();
                }
                Log.i(TAG, "closeCamera");
            } else if (this.mEventContext.mBackCar6752Type == 1) {
                closeDVDSignalChannal();
            } else {
                if (Build.VERSION.SDK_INT <= 19) {
                    NativeCamera.closeCamera();
                }
                Log.i(TAG, "closeCamera");
            }
        } else {
            if (Build.VERSION.SDK_INT <= 19) {
                NativeCamera.closeCamera();
            }
            Log.i(TAG, "closeCamera");
        }
        this.bCamaraOpen = false;
    }

    /* access modifiers changed from: protected */
    public void DetectSignThread() {
        new Thread(new Runnable() {
            public void run() {
                while (BackcarEvent.this.bCamaraOpen) {
                    if (BackcarEvent.this.mEventContext.m_iUITypeVer == 101 || BackcarEvent.this.mEventContext.m_iUITypeVer == 48) {
                        if (BackcarEvent.this.mEventContext.mCurrSendMode.getIntValue() == EventUtils.eSrcMode.SRC_BACKCAR.getIntValue()) {
                            if (BackcarEvent.this.mEventContext.mBackCar6752Type == 0) {
                                if (Build.VERSION.SDK_INT <= 19) {
                                    BackcarEvent backcarEvent = BackcarEvent.this;
                                    NativeCamera unused = backcarEvent.camera;
                                    int unused2 = backcarEvent.signalState = NativeCamera.getSignalState();
                                }
                            } else if (BackcarEvent.this.mEventContext.mBackCar6752Type == 1) {
                                if (Build.VERSION.SDK_INT <= 19) {
                                    int unused3 = BackcarEvent.this.signalState = 1;
                                }
                            } else if (Build.VERSION.SDK_INT <= 19) {
                                BackcarEvent backcarEvent2 = BackcarEvent.this;
                                NativeCamera unused4 = backcarEvent2.camera;
                                int unused5 = backcarEvent2.signalState = NativeCamera.getSignalState();
                            }
                        } else if (Build.VERSION.SDK_INT <= 19) {
                            BackcarEvent backcarEvent3 = BackcarEvent.this;
                            NativeCamera unused6 = backcarEvent3.camera;
                            int unused7 = backcarEvent3.signalState = NativeCamera.getSignalState();
                        }
                    } else if (Build.VERSION.SDK_INT <= 19) {
                        BackcarEvent backcarEvent4 = BackcarEvent.this;
                        NativeCamera unused8 = backcarEvent4.camera;
                        int unused9 = backcarEvent4.signalState = NativeCamera.getSignalState();
                    }
                    BackcarEvent.access$1908();
                    Log.i(BackcarEvent.TAG, "signalState = " + BackcarEvent.this.signalState + " ,iCamaraSignalValidCnt = " + BackcarEvent.iCamaraSignalValidCnt);
                    try {
                        Thread.sleep(200);
                    } catch (Exception e) {
                        Log.e(BackcarEvent.TAG, "DetectSignThread error " + e.toString());
                    }
                    BackcarEvent.this.handleProgress.removeMessages(2);
                    BackcarEvent.this.handleProgress.sendEmptyMessageDelayed(2, 10);
                }
            }
        }).start();
    }

    public void setVehicleSize(int i, int i2, int i3, int i4) {
        if (Build.VERSION.SDK_INT <= 19) {
            NativeCamera.setCameraRect(i, i2, i3, i4);
        }
    }

    public void setSignalChannal(int i) {
        Log.i(TAG, "Video ch ==" + i);
        if (Build.VERSION.SDK_INT <= 19) {
            NativeCamera.setSignalChannal(i);
        }
    }

    public void setDVDSignalChannal() {
        Log.i(TAG, "setDVDSignalChannal ");
        if (Build.VERSION.SDK_INT <= 19) {
            NativeCamera.openDvd();
        }
    }

    public void closeDVDSignalChannal() {
        Log.i(TAG, "closeDVDSignalChannal ");
        if (Build.VERSION.SDK_INT <= 19) {
            NativeCamera.closeDvd();
        }
    }

    public int GetSignalStatus() {
        return this.signalState;
    }

    /* access modifiers changed from: package-private */
    public void ShowWgt(int i, boolean z) {
        View findViewById;
        Log.i(TAG, "ShowWgt: bShow = " + z);
        View view = this.mCameraPipView;
        if (view != null && (findViewById = view.findViewById(i)) != null) {
            if (z) {
                findViewById.setVisibility(0);
            } else {
                findViewById.setVisibility(8);
            }
        }
    }

    public void setTrackData(byte b) {
        int i;
        int[] iArr = {R.drawable.gj01, R.drawable.gj02, R.drawable.gj03, R.drawable.gj04, R.drawable.gj05, R.drawable.gj06, R.drawable.gj07, R.drawable.gj08, R.drawable.gj09, R.drawable.gj10, R.drawable.gj11, R.drawable.gj12, R.drawable.gj13, R.drawable.gj14, R.drawable.gj15, R.drawable.gj16, R.drawable.gj17, R.drawable.gj18, R.drawable.gj19, R.drawable.gj20, R.drawable.gj21, R.drawable.gj22, R.drawable.gj23, R.drawable.gj24, R.drawable.gj25, R.drawable.gj26, R.drawable.gj27, R.drawable.gj28, R.drawable.gj29, R.drawable.gj30, R.drawable.gj31, R.drawable.gj32, R.drawable.gj33, R.drawable.gj34, R.drawable.gj35, R.drawable.gj36, R.drawable.gj37, R.drawable.gj38, R.drawable.gj39};
        boolean z = (b & EventUtils.CMD_FREQ_SEL) > 0;
        byte b2 = b & EventUtils.CMD_UPGRADE_ACK;
        if (b2 < 0) {
            b2 = 0;
        } else if (b2 > 38) {
            b2 = 38;
        }
        if (z) {
            i = 19 - (b2 / 2);
        } else {
            i = (b2 / 2) + 19;
        }
        if (i >= 0 && i < 39 && this.mvwBackTrack != null) {
            if (!this.mEventContext.m_bWheelTrack) {
                this.mvwBackTrack.setVisibility(8);
            } else if (this.mEventContext.m_iCanbustype != 20 || this.mEventContext.m_iCarstype_ID != 4 || this.mEventContext.m_iCarCanbusName_ID != 5) {
                if (!this.bDisRightOrLeftCamara) {
                    this.mvwBackTrack.setVisibility(8);
                    return;
                }
                this.mvwBackTrack.setVisibility(0);
                Log.i(TAG, "setTrackData: resIndex = " + i);
                this.mvwBackTrack.setBackgroundResource(iArr[i]);
                if (iHaveTrack == -1) {
                    this.mvwBackTrack.setVisibility(0);
                }
                iHaveTrack = 1;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x0038 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0039 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouch(android.view.View r4, android.view.MotionEvent r5) {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "onTouch: "
            r0.append(r1)
            int r5 = r5.getAction()
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            java.lang.String r0 = "BackcarEvent"
            android.util.Log.i(r0, r5)
            int r4 = r4.getId()
            r5 = 2131230831(0x7f08006f, float:1.8077726E38)
            r1 = 1
            r2 = 0
            if (r4 == r5) goto L_0x002d
            r5 = 2131231032(0x7f080138, float:1.8078134E38)
            if (r4 == r5) goto L_0x002b
            goto L_0x0035
        L_0x002b:
            r4 = 1
            goto L_0x0036
        L_0x002d:
            java.lang.String r4 = "VwTouchBtn onTouch"
            android.util.Log.i(r0, r4)
            r3.createFullScreenTimer()
        L_0x0035:
            r4 = 0
        L_0x0036:
            if (r4 == 0) goto L_0x0039
            return r1
        L_0x0039:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.eventcenter.BackcarEvent.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public void SendCmdLstToCanbus(byte[] bArr) {
        if (bArr != null && bArr.length != 0) {
            byte[] bArr2 = new byte[(bArr.length + 2)];
            byte b = 0;
            bArr2[0] = 46;
            int i = 0;
            while (i < bArr.length) {
                int i2 = i + 1;
                bArr2[i2] = bArr[i];
                i = i2;
            }
            for (int i3 = 1; i3 < bArr.length + 1; i3++) {
                b = (byte) (b + bArr2[i3]);
            }
            bArr2[bArr.length + 1] = (byte) (b ^ 255);
            EventService eventService = this.mEventContext;
            if (eventService != null) {
                eventService.sendCanbusData(bArr2);
            }
        }
    }

    public void onClick(View view) {
        RelativeLayout relativeLayout;
        Log.d(TAG, "onClick");
        int id = view.getId();
        if (id == R.id.BtnWideAngle) {
            SendCmdLstToCanbus(new byte[]{-58, 2, 64, 1});
            ShowWgt(R.id.ImgSelStandard, false);
            ShowWgt(R.id.ImgSelWideAngle, true);
            ShowWgt(R.id.ImgSelDepressionAngle, false);
            ShowWgt(R.id.ImgSelFullWide, false);
            EventService eventService = this.mEventContext;
            if (eventService != null) {
                eventService.m_iBackCarVideoStatus = 1;
            }
        } else if (id == R.id.ViewSurface) {
            Log.i(TAG, "ViewSurface onClick");
            fullsrceenPlay(!this.mbFullsrceen);
        } else if (id != R.id.btnTouch_Cheku) {
            switch (id) {
                case R.id.BtnBrightAdd /*2131230723*/:
                    int i = this.miBrightVal;
                    if (i <= 254) {
                        this.miBrightVal = i + 1;
                    }
                    updateColorSet();
                    break;
                case R.id.BtnBrightSub /*2131230724*/:
                    int i2 = this.miBrightVal;
                    if (i2 >= 1) {
                        this.miBrightVal = i2 - 1;
                    }
                    updateColorSet();
                    break;
                default:
                    switch (id) {
                        case R.id.BtnCamera1 /*2131230726*/:
                            EventService eventService2 = this.mEventContext;
                            if (eventService2 != null) {
                                eventService2.SetAVMType(1, false);
                                break;
                            }
                            break;
                        case R.id.BtnCamera2 /*2131230727*/:
                            EventService eventService3 = this.mEventContext;
                            if (eventService3 != null) {
                                eventService3.SetAVMType(2, false);
                                break;
                            }
                            break;
                        case R.id.BtnCamera3 /*2131230728*/:
                            EventService eventService4 = this.mEventContext;
                            if (eventService4 != null) {
                                eventService4.SetAVMType(3, false);
                                break;
                            }
                            break;
                        case R.id.BtnCameraClose /*2131230729*/:
                            if (this.mEventContext.m_iUITypeVer != 44) {
                                EventService eventService5 = this.mEventContext;
                                if (eventService5 != null) {
                                    eventService5.CloseAVMType();
                                    break;
                                }
                            } else {
                                Log.i(TAG, "--->>> btnTouch_Cheku btnTouch_KeSaiWei 111");
                                this.mEventContext.closeSwitchOriginaCar();
                                break;
                            }
                            break;
                        case R.id.BtnContrastAdd /*2131230730*/:
                            int i3 = this.miContrastVal;
                            if (i3 <= 254) {
                                this.miContrastVal = i3 + 1;
                            }
                            updateColorSet();
                            break;
                        case R.id.BtnContrastSub /*2131230731*/:
                            int i4 = this.miContrastVal;
                            if (i4 >= 1) {
                                this.miContrastVal = i4 - 1;
                            }
                            updateColorSet();
                            break;
                        case R.id.BtnDepressionAngle /*2131230732*/:
                            SendCmdLstToCanbus(new byte[]{-58, 2, 64, 2});
                            ShowWgt(R.id.ImgSelStandard, false);
                            ShowWgt(R.id.ImgSelWideAngle, false);
                            ShowWgt(R.id.ImgSelDepressionAngle, true);
                            ShowWgt(R.id.ImgSelFullWide, false);
                            EventService eventService6 = this.mEventContext;
                            if (eventService6 != null) {
                                eventService6.m_iBackCarVideoStatus = 2;
                                break;
                            }
                            break;
                        case R.id.BtnFullWide /*2131230733*/:
                            SendCmdLstToCanbus(new byte[]{-58, 2, 64, 3});
                            ShowWgt(R.id.ImgSelStandard, false);
                            ShowWgt(R.id.ImgSelWideAngle, false);
                            ShowWgt(R.id.ImgSelDepressionAngle, false);
                            ShowWgt(R.id.ImgSelFullWide, true);
                            EventService eventService7 = this.mEventContext;
                            if (eventService7 != null) {
                                eventService7.m_iBackCarVideoStatus = 3;
                                break;
                            }
                            break;
                        default:
                            switch (id) {
                                case R.id.BtnHueAdd /*2131230735*/:
                                    int i5 = this.miHueVal;
                                    if (i5 <= 254) {
                                        this.miHueVal = i5 + 1;
                                    }
                                    updateColorSet();
                                    break;
                                case R.id.BtnHueSub /*2131230736*/:
                                    int i6 = this.miHueVal;
                                    if (i6 >= 1) {
                                        this.miHueVal = i6 - 1;
                                    }
                                    updateColorSet();
                                    break;
                                default:
                                    switch (id) {
                                        case R.id.BtnShowColorSet /*2131230748*/:
                                            View view2 = this.mCameraPipView;
                                            if (!(view2 == null || (relativeLayout = (RelativeLayout) view2.findViewById(R.id.lytColorSet)) == null)) {
                                                if (relativeLayout.getVisibility() != 8) {
                                                    relativeLayout.setVisibility(8);
                                                    break;
                                                } else {
                                                    relativeLayout.setVisibility(0);
                                                    break;
                                                }
                                            }
                                        case R.id.BtnStandard /*2131230749*/:
                                            SendCmdLstToCanbus(new byte[]{-58, 2, 64, 0});
                                            ShowWgt(R.id.ImgSelStandard, true);
                                            ShowWgt(R.id.ImgSelWideAngle, false);
                                            ShowWgt(R.id.ImgSelDepressionAngle, false);
                                            ShowWgt(R.id.ImgSelFullWide, false);
                                            EventService eventService8 = this.mEventContext;
                                            if (eventService8 != null) {
                                                eventService8.m_iBackCarVideoStatus = 0;
                                                break;
                                            }
                                            break;
                                    }
                                    break;
                            }
                    }
            }
        } else if (this.mEventContext != null) {
            Log.i(TAG, "--->>> btnTouch_Cheku btnTouch_KeSaiWei 000");
            this.mEventContext.closeSwitchOriginaCar();
        }
        EventService eventService9 = this.mEventContext;
        if (eventService9 != null && eventService9.mSysProviderOpt != null) {
            SysProviderOpt sysProviderOpt = this.mEventContext.mSysProviderOpt;
            sysProviderOpt.updateRecord(SysProviderOpt.SET_BACKCAR_VIDEO, BuildConfig.FLAVOR + this.mEventContext.m_iBackCarVideoStatus);
        }
    }

    /* access modifiers changed from: package-private */
    public void createFullScreenTimer() {
        this.handleProgress.removeMessages(4);
        this.handleProgress.sendEmptyMessageDelayed(4, 5000);
    }

    /* access modifiers changed from: package-private */
    public void killFullScreenTimer() {
        this.handleProgress.removeMessages(4);
    }

    /* access modifiers changed from: package-private */
    public void fullsrceenPlay(boolean z) {
        if (!z) {
            this.mbFullsrceen = false;
            RelativeLayout relativeLayout = this.mLytBottom_boruizongheng;
            if (relativeLayout != null) {
                relativeLayout.setVisibility(0);
            }
            createFullScreenTimer();
            return;
        }
        this.mbFullsrceen = true;
        RelativeLayout relativeLayout2 = this.mLytBottom_boruizongheng;
        if (relativeLayout2 != null) {
            relativeLayout2.setVisibility(8);
        }
        killFullScreenTimer();
    }

    public void updateColorSet() {
        Log.i(TAG, "--->>> UI_BORUIZENGHENG 444");
        if (this.mCameraPipView != null) {
            Log.i(TAG, "--->>> UI_BORUIZENGHENG 555");
            TextView textView = (TextView) this.mCameraPipView.findViewById(R.id.tvContrastVal);
            TextView textView2 = (TextView) this.mCameraPipView.findViewById(R.id.tvBrightVal);
            TextView textView3 = (TextView) this.mCameraPipView.findViewById(R.id.tvHueVal);
            if (textView != null) {
                textView.setText(BuildConfig.FLAVOR + this.miContrastVal);
            }
            if (textView2 != null) {
                textView2.setText(BuildConfig.FLAVOR + this.miBrightVal);
            }
            if (textView3 != null) {
                textView3.setText(BuildConfig.FLAVOR + this.miHueVal);
            }
        }
        Log.i(TAG, "miContrastVal 2== " + this.miContrastVal);
        Log.i(TAG, "miBrightVal 2== " + this.miBrightVal);
        Log.i(TAG, "miHueVal 2== " + this.miHueVal);
        int i = (this.miHueVal * 256 * 256) + (this.miBrightVal * 256) + this.miContrastVal;
        EventService eventService = this.mEventContext;
        if (eventService != null) {
            eventService.SetCon_bri_hue(i);
        }
        if (this.mEventContext.mSysProviderOpt != null) {
            SysProviderOpt sysProviderOpt = this.mEventContext.mSysProviderOpt;
            sysProviderOpt.updateRecord(SysProviderOpt.SYS_CONTRAST_SET, BuildConfig.FLAVOR + this.miContrastVal);
            SysProviderOpt sysProviderOpt2 = this.mEventContext.mSysProviderOpt;
            sysProviderOpt2.updateRecord(SysProviderOpt.SYS_BRIGHT_SET, BuildConfig.FLAVOR + this.miBrightVal);
            SysProviderOpt sysProviderOpt3 = this.mEventContext.mSysProviderOpt;
            sysProviderOpt3.updateRecord(SysProviderOpt.SYS_HUE_SET, BuildConfig.FLAVOR + this.miHueVal);
        }
    }

    public void setRoteta(int i) {
        if (this.camera != null && Build.VERSION.SDK_INT <= 19) {
            NativeCamera.setRoteta(i);
        }
    }

    private boolean on360TouchEvent(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mX = (int) motionEvent.getX();
            this.mY = (int) motionEvent.getY();
            this.mScreenWidth = view.getWidth();
            int height = view.getHeight();
            this.mScreenHeight = height;
            send360TouchPos((int) ((((float) this.mX) * 4096.0f) / ((float) this.mScreenWidth)), (int) ((((float) this.mY) * 4096.0f) / ((float) height)), 1);
        } else if (action == 1) {
            this.mX = (int) motionEvent.getX();
            this.mY = (int) motionEvent.getY();
            this.mScreenWidth = view.getWidth();
            int height2 = view.getHeight();
            this.mScreenHeight = height2;
            send360TouchPos((int) ((((float) this.mX) * 4096.0f) / ((float) this.mScreenWidth)), (int) ((((float) this.mY) * 4096.0f) / ((float) height2)), 0);
        } else if (action != 2) {
            return false;
        } else {
            this.mX = (int) motionEvent.getX();
            this.mY = (int) motionEvent.getY();
            this.mScreenWidth = view.getWidth();
            int height3 = view.getHeight();
            this.mScreenHeight = height3;
            send360TouchPos((int) ((((float) this.mX) * 4096.0f) / ((float) this.mScreenWidth)), (int) ((((float) this.mY) * 4096.0f) / ((float) height3)), 1);
        }
        return true;
    }

    public void send360TouchPos(int i, int i2, int i3) {
        this.mContext.send360Data(new byte[]{5, 5, (byte) i3, (byte) (i >> 8), (byte) (i & 255), (byte) (i2 >> 8), (byte) (i2 & 255)});
    }

    public /* synthetic */ void lambda$new$0$BackcarEvent() {
        this.mEventContext.sendTouchPos((int) this.downX, (int) this.downY, true, true);
    }
}
