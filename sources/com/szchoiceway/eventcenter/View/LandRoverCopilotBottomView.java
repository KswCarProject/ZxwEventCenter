package com.szchoiceway.eventcenter.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.widget.RelativeLayout;
import com.szchoiceway.eventcenter.EventService;
import com.szchoiceway.eventcenter.EventUtils;
import com.szchoiceway.eventcenter.R;
import com.szchoiceway.eventcenter.SysProviderOpt;

public class LandRoverCopilotBottomView extends RelativeLayout implements View.OnTouchListener, View.OnClickListener {
    private static final int LONG_CLICK_ADD = 1;
    private static final int LONG_CLICK_REDUCE = 2;
    private static final String TAG = "LandRoverCBottomView";
    private int extra = 0;
    private final Runnable longClickRunnable = new Runnable() {
        public final void run() {
            LandRoverCopilotBottomView.this.lambda$new$1$LandRoverCopilotBottomView();
        }
    };
    private int longClickTime = 3000;
    private Activity mActivity;
    /* access modifiers changed from: private */
    public EventService mContext;
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                LandRoverCopilotBottomView.this.mContext.sendBroadcast(new Intent(EventUtils.ZXW_ACTION_VOLUME_ADD));
                sendEmptyMessageDelayed(1, (long) LandRoverCopilotBottomView.this.volumeSendInterval);
            } else if (i == 2) {
                LandRoverCopilotBottomView.this.mContext.sendBroadcast(new Intent(EventUtils.ZXW_ACTION_VOLUME_REDUCE));
                sendEmptyMessageDelayed(2, (long) LandRoverCopilotBottomView.this.volumeSendInterval);
            }
        }
    };
    /* access modifiers changed from: private */
    public int volumeSendInterval = 100;

    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return super.generateLayoutParams(attributeSet);
    }

    public /* bridge */ /* synthetic */ ViewOverlay getOverlay() {
        return super.getOverlay();
    }

    public void setActivity(Activity activity) {
        this.mActivity = activity;
    }

    public LandRoverCopilotBottomView(Context context) {
        super(context);
        this.mContext = (EventService) context;
        initView(context);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow");
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow");
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(2);
    }

    private void initView(Context context) {
        int i = R.style.landrover_bootom_blue;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, R.style.landrover_bootom_blue);
        int recordInteger = SysProviderOpt.getInstance(context).getRecordInteger(SysProviderOpt.KSW_LANDROVER_THEME_BACKGROUND_INDEX, 0);
        Log.d(TAG, "initView index = " + recordInteger);
        if (recordInteger != 0) {
            i = R.style.landrover_bottom_white;
        }
        contextThemeWrapper.setTheme(i);
        LayoutInflater.from(contextThemeWrapper).inflate(R.layout.layout_landrover_copilot_bottom_view, this, true);
        int[] iArr = {R.id.btnBottomApps, R.id.btnBottomVideo, R.id.btnBottomSettings, R.id.btnBottomHome};
        for (int i2 = 0; i2 < 4; i2++) {
            View findViewById = findViewById(iArr[i2]);
            if (findViewById != null) {
                findViewById.setTag(Integer.valueOf(i2));
                findViewById.setOnClickListener(this);
            }
        }
        int[] iArr2 = {R.id.btnBottomUp, R.id.btnBottomDown, R.id.btnBottomVolumeAdd, R.id.btnBottomVolumeReduce};
        for (int i3 = 0; i3 < 4; i3++) {
            View findViewById2 = findViewById(iArr2[i3]);
            if (findViewById2 != null) {
                findViewById2.setOnTouchListener(this);
            }
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 1) {
                if (view.getId() == R.id.btnBottomUp || view.getId() == R.id.btnBottomDown) {
                    this.mContext.getMainThreadHandler().removeCallbacks(this.longClickRunnable);
                    sendLandRoverKey(this.extra, 0);
                    this.extra = 0;
                } else if (view.getId() == R.id.btnBottomVolumeAdd) {
                    this.mHandler.removeMessages(1);
                } else if (view.getId() == R.id.btnBottomVolumeReduce) {
                    this.mHandler.removeMessages(2);
                }
            }
        } else if (view.getId() == R.id.btnBottomUp) {
            this.extra = 12;
            sendLandRoverKey(12, 1);
            this.mContext.getMainThreadHandler().postDelayed(this.longClickRunnable, (long) this.longClickTime);
        } else if (view.getId() == R.id.btnBottomDown) {
            this.extra = 13;
            sendLandRoverKey(13, 1);
            this.mContext.getMainThreadHandler().postDelayed(this.longClickRunnable, (long) this.longClickTime);
        } else if (view.getId() == R.id.btnBottomVolumeAdd) {
            this.mHandler.removeMessages(1);
            this.mHandler.sendEmptyMessage(1);
        } else if (view.getId() == R.id.btnBottomVolumeReduce) {
            this.mHandler.removeMessages(2);
            this.mHandler.sendEmptyMessage(2);
        }
        return false;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btnBottomApps) {
            sendKeyDownUpSync(3);
            this.mContext.getMainThreadHandler().postDelayed(new Runnable() {
                public final void run() {
                    LandRoverCopilotBottomView.this.lambda$onClick$0$LandRoverCopilotBottomView();
                }
            }, 80);
        } else if (view.getId() == R.id.btnBottomVideo) {
            enterVideo();
        } else if (view.getId() == R.id.btnBottomSettings) {
            EventUtils.startActivityIfNotRuning(this.mContext, EventUtils.SET_MODE_PACKAGE_NAME, EventUtils.SET_MODE_CLASS_NAME);
        } else if (view.getId() == R.id.btnBottomHome) {
            sendKeyDownUpSync(3);
        }
    }

    public /* synthetic */ void lambda$onClick$0$LandRoverCopilotBottomView() {
        this.mContext.sendBroadcast(new Intent("ZXW_ACTION_SHOW_APPLIST"));
    }

    private void enterVideo() {
        String recordValue = SysProviderOpt.getInstance(this.mContext).getRecordValue(SysProviderOpt.VIDEO_PACKAGENAME, EventUtils.MOVIE_MODE_PACKAGE_NAME);
        String recordValue2 = SysProviderOpt.getInstance(this.mContext).getRecordValue(SysProviderOpt.VIDEO_ACTIVITYNAME, EventUtils.MOVIE_MODE_CLASS_NAME);
        if (!EventUtils.MOVIE_MODE_PACKAGE_NAME.equals(recordValue)) {
            this.mContext.sendBroadcast(new Intent(EventUtils.ZXW_ACTION_SEND_THIRD_MEDIA_SOURCE));
        }
        EventUtils.startActivityIfNotRuning(this.mContext, recordValue, recordValue2);
    }

    private void sendLandRoverKey(int i, int i2) {
        this.mContext.kswSendLandRoverTouchKey(i, i2);
    }

    public void sendKeyDownUpSync(int i) {
        sendKeySync(new KeyEvent(0, i));
        SystemClock.sleep(10);
        sendKeySync(new KeyEvent(1, i));
    }

    public void sendKeySync(KeyEvent keyEvent) {
        long downTime = keyEvent.getDownTime();
        long eventTime = keyEvent.getEventTime();
        int action = keyEvent.getAction();
        int keyCode = keyEvent.getKeyCode();
        int repeatCount = keyEvent.getRepeatCount();
        int metaState = keyEvent.getMetaState();
        int deviceId = keyEvent.getDeviceId();
        int scanCode = keyEvent.getScanCode();
        int source = keyEvent.getSource();
        int flags = keyEvent.getFlags();
        int i = source == 0 ? 257 : source;
        if (eventTime == 0) {
            eventTime = SystemClock.uptimeMillis();
        }
        if (downTime == 0) {
            downTime = eventTime;
        }
        KeyEvent keyEvent2 = r4;
        KeyEvent keyEvent3 = new KeyEvent(downTime, eventTime, action, keyCode, repeatCount, metaState, deviceId, scanCode, flags | 8, i);
        InputManager.getInstance().injectInputEvent(keyEvent2, 2);
    }

    public /* synthetic */ void lambda$new$1$LandRoverCopilotBottomView() {
        sendLandRoverKey(this.extra, 2);
    }
}
