package com.szchoiceway.eventcenter.View;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemProperties;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.szchoiceway.eventcenter.R;
import com.szchoiceway.eventcenter.SysProviderOpt;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CalibrationView2 extends RelativeLayout implements View.OnClickListener {
    private static final int EVT_MESSAGE_RESTART = 0;
    private static final String TAG = "CalibrationView";
    /* access modifiers changed from: private */
    public boolean isCalibrated = false;
    boolean isXCorrect = true;
    boolean isYCorrect = true;
    /* access modifiers changed from: private */
    public Button mBtnExit;
    private CalibrationCallBack mCallBack;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 0) {
                CalibrationView2.this.mTips.setText(CalibrationView2.this.getResources().getString(R.string.lbl_touch_calibration_start_tips));
                CalibrationView2.this.mTips.setVisibility(0);
                CalibrationView2.this.mBtnExit.setVisibility(8);
                CalibrationView2.this.mImgBox.setVisibility(0);
                boolean unused = CalibrationView2.this.isCalibrated = false;
                int unused2 = CalibrationView2.this.mStep = 0;
                CalibrationView2.this.setTouchInfor("4");
            }
        }
    };
    /* access modifiers changed from: private */
    public ImageView mImgBox;
    private ImageView mImgBox1;
    private ImageView mImgBox2;
    private ImageView mImgBox3;
    private SysProviderOpt mProvider;
    /* access modifiers changed from: private */
    public int mStep = 0;
    /* access modifiers changed from: private */
    public TextView mTips;
    private int mWindowHeight = 0;
    private int mWindowWidth = 0;

    public interface CalibrationCallBack {
        void exitProgram();
    }

    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return super.generateLayoutParams(attributeSet);
    }

    public /* bridge */ /* synthetic */ ViewOverlay getOverlay() {
        return super.getOverlay();
    }

    public CalibrationView2(Context context) {
        super(context);
        View.inflate(context, R.layout.layout_calibration, this);
        this.mBtnExit = (Button) findViewById(R.id.btnExit);
        this.mImgBox = (ImageView) findViewById(R.id.imgBox);
        this.mImgBox1 = (ImageView) findViewById(R.id.imgBox1);
        this.mImgBox2 = (ImageView) findViewById(R.id.imgBox2);
        this.mImgBox3 = (ImageView) findViewById(R.id.imgBox3);
        this.mTips = (TextView) findViewById(R.id.tvTips);
        this.mBtnExit.setOnClickListener(this);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
        this.mWindowHeight = displayMetrics.heightPixels;
        this.mWindowWidth = displayMetrics.widthPixels;
        this.mProvider = SysProviderOpt.getInstance(context);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setTouchInfor("4");
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!this.isCalibrated && motionEvent.getActionMasked() == 0) {
            Log.d(TAG, "event.getX() " + motionEvent.getX() + " event.getY() " + motionEvent.getY());
            StringBuilder sb = new StringBuilder();
            sb.append("mWindowHeight ");
            sb.append(this.mWindowHeight);
            Log.d(TAG, sb.toString());
            Log.d(TAG, "mWindowWidth " + this.mWindowWidth);
            int i = this.mStep;
            if (i == 0) {
                this.isXCorrect = motionEvent.getX() <= ((float) this.mImgBox.getWidth());
                this.isYCorrect = motionEvent.getY() <= ((float) this.mImgBox.getHeight());
                this.mImgBox.setVisibility(8);
                if (!this.isXCorrect || !this.isYCorrect) {
                    this.mImgBox1.setVisibility(0);
                    setTouchInfor("5");
                    this.mStep = 1;
                } else {
                    this.isCalibrated = true;
                    this.mStep = 0;
                    this.mHandler.sendEmptyMessageDelayed(0, 5000);
                    this.mTips.setVisibility(8);
                    this.mBtnExit.setVisibility(0);
                }
            } else if (i == 1) {
                this.isXCorrect = motionEvent.getX() <= ((float) this.mImgBox1.getWidth());
                this.isYCorrect = motionEvent.getY() >= ((float) (this.mWindowHeight - this.mImgBox1.getHeight()));
                this.mImgBox1.setVisibility(8);
                if (!this.isXCorrect || !this.isYCorrect) {
                    this.mImgBox2.setVisibility(0);
                    setTouchInfor("6");
                    this.mStep = 2;
                } else {
                    this.isCalibrated = true;
                    this.mStep = 0;
                    this.mHandler.sendEmptyMessageDelayed(0, 5000);
                    this.mTips.setVisibility(8);
                    this.mBtnExit.setVisibility(0);
                }
            } else if (i == 2) {
                this.isXCorrect = motionEvent.getX() >= ((float) (this.mWindowWidth - this.mImgBox2.getWidth()));
                this.isYCorrect = motionEvent.getY() >= ((float) (this.mWindowHeight - this.mImgBox2.getHeight()));
                this.mImgBox2.setVisibility(8);
                if (!this.isXCorrect || !this.isYCorrect) {
                    this.mImgBox3.setVisibility(0);
                    setTouchInfor("7");
                    this.mStep = 3;
                } else {
                    this.isCalibrated = true;
                    this.mStep = 0;
                    this.mHandler.sendEmptyMessageDelayed(0, 5000);
                    this.mTips.setVisibility(8);
                    this.mBtnExit.setVisibility(0);
                }
            } else if (i == 3) {
                this.isXCorrect = motionEvent.getX() >= ((float) (this.mWindowWidth - this.mImgBox3.getWidth()));
                this.isYCorrect = motionEvent.getY() <= ((float) this.mImgBox3.getHeight());
                this.mImgBox3.setVisibility(8);
                this.isCalibrated = true;
                this.mStep = 0;
                this.mHandler.sendEmptyMessageDelayed(0, 5000);
                this.mTips.setVisibility(8);
                this.mBtnExit.setVisibility(0);
            }
            Log.d(TAG, "isXCorrect " + this.isXCorrect);
            Log.d(TAG, "isYCorrect " + this.isYCorrect);
        }
        if (this.isCalibrated) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void setTouchInfor(String str) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("/sys/touch_type/touch_type"));
            fileOutputStream.write(new String(str).getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.mProvider.updateRecord(SysProviderOpt.SYS_TOUCH_ORGIN_KEY, str);
        SystemProperties.set("persist.touch_type", str);
    }

    public void onClick(View view) {
        if (this.mCallBack != null) {
            this.mHandler.removeMessages(0);
            this.mCallBack.exitProgram();
        }
    }

    public void setCallBack(CalibrationCallBack calibrationCallBack) {
        this.mCallBack = calibrationCallBack;
    }
}
