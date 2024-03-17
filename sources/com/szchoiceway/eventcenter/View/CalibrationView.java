package com.szchoiceway.eventcenter.View;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class CalibrationView extends RelativeLayout implements View.OnClickListener {
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
                CalibrationView.this.mTips.setText(CalibrationView.this.getResources().getString(R.string.lbl_touch_calibration_start_tips));
                CalibrationView.this.mTips.setVisibility(0);
                CalibrationView.this.mBtnExit.setVisibility(8);
                CalibrationView.this.mImgBox.setVisibility(0);
                boolean unused = CalibrationView.this.isCalibrated = false;
                int unused2 = CalibrationView.this.mStep = 0;
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

    public CalibrationView(Context context) {
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
            int i = this.mStep;
            boolean z = true;
            if (i == 0) {
                this.isXCorrect = motionEvent.getX() <= ((float) this.mImgBox.getWidth());
                this.isYCorrect = motionEvent.getY() <= ((float) this.mImgBox.getHeight());
                this.mImgBox.setVisibility(8);
                this.mImgBox1.setVisibility(0);
                this.mImgBox2.setVisibility(8);
                this.mImgBox3.setVisibility(8);
                this.mStep = 1;
                Log.d(TAG, " case 0 isXCorrect = " + this.isXCorrect + ", isYCorrect = " + this.isYCorrect);
                if (!this.isXCorrect || !this.isYCorrect) {
                    setTouchInfor("5");
                }
            } else if (i == 1) {
                this.isXCorrect = motionEvent.getX() <= ((float) this.mImgBox1.getWidth());
                if (motionEvent.getY() < ((float) (this.mWindowHeight - this.mImgBox1.getHeight()))) {
                    z = false;
                }
                this.isYCorrect = z;
                this.mImgBox.setVisibility(8);
                this.mImgBox1.setVisibility(8);
                this.mImgBox2.setVisibility(0);
                this.mImgBox3.setVisibility(8);
                Log.d(TAG, " case 1 isXCorrect = " + this.isXCorrect + ", isYCorrect = " + this.isYCorrect);
                if (!this.isXCorrect || !this.isYCorrect) {
                    setTouchInfor("6");
                }
                this.mStep = 2;
            } else if (i == 2) {
                this.isXCorrect = motionEvent.getX() >= ((float) (this.mWindowWidth - this.mImgBox2.getWidth()));
                if (motionEvent.getY() < ((float) (this.mWindowHeight - this.mImgBox2.getHeight()))) {
                    z = false;
                }
                this.isYCorrect = z;
                this.mImgBox.setVisibility(8);
                this.mImgBox1.setVisibility(8);
                this.mImgBox2.setVisibility(8);
                this.mImgBox3.setVisibility(0);
                Log.d(TAG, " case 2 isXCorrect = " + this.isXCorrect + ", isYCorrect = " + this.isYCorrect);
                if (!this.isXCorrect || !this.isYCorrect) {
                    setTouchInfor("7");
                }
                this.mStep = 3;
            } else if (i == 3) {
                this.isXCorrect = motionEvent.getX() >= ((float) (this.mWindowWidth - this.mImgBox3.getWidth()));
                this.isYCorrect = motionEvent.getY() <= ((float) this.mImgBox3.getHeight());
                this.mImgBox.setVisibility(8);
                this.mImgBox1.setVisibility(8);
                this.mImgBox2.setVisibility(8);
                Log.d(TAG, " case 3 isXCorrect = " + this.isXCorrect + ", isYCorrect = " + this.isYCorrect);
                this.isCalibrated = true;
                this.mStep = 0;
                this.mHandler.sendEmptyMessageDelayed(0, 5000);
                this.mTips.setVisibility(8);
                this.mBtnExit.setVisibility(0);
            }
        }
        if (this.isCalibrated) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return false;
    }

    private void setTouchInfor(String str) {
        Log.d(TAG, "setTouchInfor param = " + str);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("/sys/touch_type/touch_type"));
            fileOutputStream.write(new String(str).getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.mProvider.updateRecord(SysProviderOpt.SYS_TOUCH_ORGIN_KEY, str);
        File file = new File("/mnt/privdata1/touchParam.txt");
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
            file.setWritable(true);
            file.setReadable(true);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/mnt/privdata1/touchParam.txt"));
            bufferedWriter.write(str);
            bufferedWriter.close();
            Runtime.getRuntime().exec("sync");
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void onClick(View view) {
        CalibrationCallBack calibrationCallBack = this.mCallBack;
        if (calibrationCallBack != null) {
            calibrationCallBack.exitProgram();
        }
    }

    public void setCallBack(CalibrationCallBack calibrationCallBack) {
        this.mCallBack = calibrationCallBack;
    }
}
