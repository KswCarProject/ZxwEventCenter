package com.szchoiceway.eventcenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;

public class DoorWndFrame extends RelativeLayout {
    private static final String TAG = "DoorWndFrame dazong";
    private static boolean bEnableDebug = true;
    private ImageView ImgBCMCovereOnIcon = null;
    private ImageView ImgBCMCovereOnIcon1 = null;
    private ImageView ImgDoorFLeftIcon = null;
    private ImageView ImgDoorFLeftIcon1 = null;
    private ImageView ImgDoorFRightIcon = null;
    private ImageView ImgDoorFRightIcon1 = null;
    private ImageView ImgDoorRLeftIcon = null;
    private ImageView ImgDoorRLeftIcon1 = null;
    private ImageView ImgDoorRRightIcon = null;
    private ImageView ImgDoorRRightIcon1 = null;
    private ImageView ImgFrontCovereOnIcon = null;
    private ImageView ImgFrontCovereOnIcon1 = null;
    private final int WHAT_REFRESH_UI = 1000;
    private Context mContext = null;
    private View mRootView = null;
    private byte mbyDoorData = 0;
    private Handler mhandler = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 1000) {
                Log.i(DoorWndFrame.TAG, "***WHAT_REFRESH_UI***");
                DoorWndFrame.this.OnRefreshCarDoorInfo();
            }
        }
    };
    private RelativeLayout rlCarDoor1;

    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return super.generateLayoutParams(attributeSet);
    }

    public /* bridge */ /* synthetic */ ViewOverlay getOverlay() {
        return super.getOverlay();
    }

    public DoorWndFrame(Context context) {
        super(context);
    }

    public DoorWndFrame(Context context, int i) {
        super(context);
        this.mContext = context;
        init(i);
    }

    private void init(int i) {
        View inflate = View.inflate(this.mContext, i, (ViewGroup) null);
        this.mRootView = inflate;
        addView(inflate);
        setBackgroundColor(ViewCompat.MEASURED_SIZE_MASK);
        this.ImgFrontCovereOnIcon = (ImageView) this.mRootView.findViewById(R.id.ImgFrontCovereOnIcon);
        this.ImgBCMCovereOnIcon = (ImageView) this.mRootView.findViewById(R.id.ImgBCMCovereOnIcon);
        this.ImgDoorFLeftIcon = (ImageView) this.mRootView.findViewById(R.id.ImgDoorFLeftIcon);
        this.ImgDoorRLeftIcon = (ImageView) this.mRootView.findViewById(R.id.ImgDoorRLeftIcon);
        this.ImgDoorFRightIcon = (ImageView) this.mRootView.findViewById(R.id.ImgDoorFRightIcon);
        this.ImgDoorRRightIcon = (ImageView) this.mRootView.findViewById(R.id.ImgDoorRRightIcon);
        this.rlCarDoor1 = (RelativeLayout) this.mRootView.findViewById(R.id.rlCarDoor1);
        this.ImgFrontCovereOnIcon1 = (ImageView) this.mRootView.findViewById(R.id.ImgFrontCovereOnIcon1);
        this.ImgBCMCovereOnIcon1 = (ImageView) this.mRootView.findViewById(R.id.ImgBCMCovereOnIcon1);
        this.ImgDoorFLeftIcon1 = (ImageView) this.mRootView.findViewById(R.id.ImgDoorFLeftIcon1);
        this.ImgDoorRLeftIcon1 = (ImageView) this.mRootView.findViewById(R.id.ImgDoorRLeftIcon1);
        this.ImgDoorFRightIcon1 = (ImageView) this.mRootView.findViewById(R.id.ImgDoorFRightIcon1);
        this.ImgDoorRRightIcon1 = (ImageView) this.mRootView.findViewById(R.id.ImgDoorRRightIcon1);
    }

    public View getView() {
        return this.mRootView;
    }

    private void setViewVisible(View view, boolean z) {
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
    }

    private void rotationView(View view, float f) {
        if (view != null) {
            view.setRotation(f);
        }
    }

    private void setViewText(TextView textView, String str) {
        if (textView != null) {
            textView.setText(str);
        }
    }

    public boolean updateCarDoorData(byte b) {
        this.mbyDoorData = b;
        if (bEnableDebug) {
            Log.i(TAG, "updateCarDoorData =" + this.mbyDoorData);
        }
        this.mhandler.sendEmptyMessage(1000);
        return true;
    }

    public void OnRefreshCarDoorInfo() {
        if ((this.mbyDoorData & 8) > 0) {
            this.ImgBCMCovereOnIcon.setVisibility(0);
        } else {
            this.ImgBCMCovereOnIcon.setVisibility(8);
        }
        if ((this.mbyDoorData & 4) > 0) {
            this.ImgFrontCovereOnIcon.setVisibility(0);
        } else {
            this.ImgFrontCovereOnIcon.setVisibility(8);
        }
        if ((this.mbyDoorData & 32) > 0) {
            this.ImgDoorRRightIcon.setVisibility(0);
        } else {
            this.ImgDoorRRightIcon.setVisibility(8);
        }
        if ((this.mbyDoorData & 16) > 0) {
            this.ImgDoorRLeftIcon.setVisibility(0);
        } else {
            this.ImgDoorRLeftIcon.setVisibility(8);
        }
        if ((this.mbyDoorData & EventUtils.CMD_FREQ_SEL) > 0) {
            this.ImgDoorFRightIcon.setVisibility(0);
        } else {
            this.ImgDoorFRightIcon.setVisibility(8);
        }
        if ((this.mbyDoorData & 64) > 0) {
            this.ImgDoorFLeftIcon.setVisibility(0);
        } else {
            this.ImgDoorFLeftIcon.setVisibility(8);
        }
        if (this.rlCarDoor1 != null) {
            if ((this.mbyDoorData & 8) > 0) {
                this.ImgBCMCovereOnIcon1.setVisibility(0);
            } else {
                this.ImgBCMCovereOnIcon1.setVisibility(8);
            }
            if ((this.mbyDoorData & 4) > 0) {
                this.ImgFrontCovereOnIcon1.setVisibility(0);
            } else {
                this.ImgFrontCovereOnIcon1.setVisibility(8);
            }
            if ((this.mbyDoorData & 32) > 0) {
                this.ImgDoorRRightIcon1.setVisibility(0);
            } else {
                this.ImgDoorRRightIcon1.setVisibility(8);
            }
            if ((this.mbyDoorData & 16) > 0) {
                this.ImgDoorRLeftIcon1.setVisibility(0);
            } else {
                this.ImgDoorRLeftIcon1.setVisibility(8);
            }
            if ((this.mbyDoorData & EventUtils.CMD_FREQ_SEL) > 0) {
                this.ImgDoorFRightIcon1.setVisibility(0);
            } else {
                this.ImgDoorFRightIcon1.setVisibility(8);
            }
            if ((this.mbyDoorData & 64) > 0) {
                this.ImgDoorFLeftIcon1.setVisibility(0);
            } else {
                this.ImgDoorFLeftIcon1.setVisibility(8);
            }
        }
    }
}
