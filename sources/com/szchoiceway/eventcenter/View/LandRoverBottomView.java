package com.szchoiceway.eventcenter.View;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import androidx.appcompat.view.ContextThemeWrapper;
import com.example.mylibrary.BuildConfig;
import com.szchoiceway.eventcenter.Customer;
import com.szchoiceway.eventcenter.EventService;
import com.szchoiceway.eventcenter.EventUtils;
import com.szchoiceway.eventcenter.R;
import com.szchoiceway.eventcenter.SysProviderOpt;
import java.util.Iterator;
import java.util.Locale;

public class LandRoverBottomView extends RelativeLayout implements View.OnTouchListener {
    private static final String TAG = "LandRoverBottomView";
    private CheckBox chkRadar;
    private int delayMills = 3000;
    private int extra = 0;
    private final Runnable longClickRunnable = new Runnable() {
        public final void run() {
            LandRoverBottomView.this.lambda$new$1$LandRoverBottomView();
        }
    };
    private EventService mContext;
    private boolean mIsPon = false;

    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return super.generateLayoutParams(attributeSet);
    }

    public /* bridge */ /* synthetic */ ViewOverlay getOverlay() {
        return super.getOverlay();
    }

    public LandRoverBottomView(Context context) {
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
    }

    private void initView(Context context) {
        Log.d(TAG, "initView");
        int i = R.style.landrover_bootom_blue;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, (int) R.style.landrover_bootom_blue);
        int recordInteger = SysProviderOpt.getInstance(this.mContext).getRecordInteger(SysProviderOpt.KSW_LANDROVER_THEME_BACKGROUND_INDEX, 0);
        if (Customer.getIModeSet(this.mContext) == 32) {
            if (recordInteger != 0) {
                i = R.style.landrover_bottom_white;
            }
            contextThemeWrapper.setTheme(i);
        } else {
            contextThemeWrapper.setTheme(R.style.landrover_bootom_blue);
        }
        LayoutInflater.from(contextThemeWrapper).inflate(R.layout.layout_landrover_bottom_view, this, true);
        this.chkRadar = (CheckBox) findViewById(R.id.chkBottomRadar);
        int[] iArr = {R.id.btnBottomHome, R.id.btnBottomNavi, R.id.btnBottomSettings, R.id.btnBottomOriginal, R.id.btnBottom360};
        for (int i2 = 0; i2 < 5; i2++) {
            View findViewById = findViewById(iArr[i2]);
            if (findViewById != null) {
                findViewById.setTag(Integer.valueOf(i2));
                findViewById.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        LandRoverBottomView.this.clickBottom(view);
                    }
                });
                findViewById.setOnLongClickListener(new View.OnLongClickListener() {
                    public final boolean onLongClick(View view) {
                        return LandRoverBottomView.this.lambda$initView$0$LandRoverBottomView(view);
                    }
                });
            }
        }
        int[] iArr2 = {R.id.btnBottomUp, R.id.btnBottomDown, R.id.btnBottomRadar};
        for (int i3 = 0; i3 < 3; i3++) {
            View findViewById2 = findViewById(iArr2[i3]);
            if (findViewById2 != null) {
                findViewById2.setOnTouchListener(this);
            }
        }
    }

    public /* synthetic */ boolean lambda$initView$0$LandRoverBottomView(View view) {
        clickBottom(view);
        return true;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            if (view.getId() == R.id.btnBottomUp) {
                this.extra = 12;
            } else if (view.getId() == R.id.btnBottomDown) {
                this.extra = 13;
            } else if (view.getId() == R.id.btnBottomRadar) {
                this.extra = 9;
            }
            sendLandRoverKey(this.extra, 1);
            this.mContext.getMainThreadHandler().postDelayed(this.longClickRunnable, (long) this.delayMills);
        } else if (action == 1) {
            this.mContext.getMainThreadHandler().removeCallbacks(this.longClickRunnable);
            sendLandRoverKey(this.extra, 0);
            this.extra = 0;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void clickBottom(View view) {
        if (view.getId() == R.id.btnBottomHome) {
            this.mContext.sendKeyDownUpSync(3);
        } else if (view.getId() == R.id.btnBottomNavi) {
            enterNavi();
        } else if (view.getId() == R.id.btnBottomSettings) {
            enterSettings();
        } else if (view.getId() == R.id.btnBottomOriginal) {
            EventService eventService = this.mContext;
            if (eventService == null) {
                enterOriginal();
            } else if (eventService.getBTStatus() < 4) {
                enterOriginal();
            }
        } else if (view.getId() == R.id.btnBottom360) {
            enter360();
        }
    }

    private void enterOriginal() {
        EventService eventService = this.mContext;
        if (eventService != null) {
            eventService.sendSwitchOriginaCar();
        }
    }

    private void sendLandRoverKey(int i, int i2) {
        this.mContext.kswSendLandRoverTouchKey(i, i2);
    }

    private void enterNavi() {
        boolean z = Locale.getDefault().getLanguage().equals("zh") && Locale.getDefault().getCountry().equals("CN");
        String str = "com.szchoiceway.navigation";
        String recordValue = SysProviderOpt.getInstance(this.mContext).getRecordValue(SysProviderOpt.NAV_PACKAGENAME, str);
        SysProviderOpt instance = SysProviderOpt.getInstance(this.mContext);
        String str2 = EventUtils.NAV_MODE_CLASS_NAME;
        String recordValue2 = instance.getRecordValue(SysProviderOpt.NAV_ACTIVITYNAME, str2);
        if ((!z || !EventUtils.GOOGLE_MAP_MODE_PACKAGE_NAME.equals(recordValue)) && EventUtils.getInstallStatus(this.mContext, recordValue)) {
            str = recordValue;
            str2 = recordValue2;
        }
        EventUtils.startActivityIfNotRuning(this.mContext, str, str2);
    }

    private void enterSettings() {
        EventUtils.startActivityIfNotRuning(this.mContext, EventUtils.SET_MODE_PACKAGE_NAME, EventUtils.SET_MODE_CLASS_NAME);
    }

    private void enter360() {
        String str;
        if (SysProviderOpt.getInstance(this.mContext).getRecordInteger(SysProviderOpt.KESAIWEI_SYS_CAMERA_SELECTION, 1) == 3) {
            Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
            intent.addCategory("android.intent.category.LAUNCHER");
            Iterator<ResolveInfo> it = this.mContext.getPackageManager().queryIntentActivities(intent, 0).iterator();
            while (true) {
                if (!it.hasNext()) {
                    str = BuildConfig.FLAVOR;
                    break;
                }
                ResolveInfo next = it.next();
                String str2 = next.activityInfo.packageName;
                if (Build.VERSION.SDK_INT == 31) {
                    if (EventUtils.LIDIAN_MODE_PACKAGE_NAME.equals(str2)) {
                        str = next.activityInfo.name;
                        break;
                    }
                } else if (EventUtils.XYQ_MODE_PACKAGE_NAME.equals(str2)) {
                    str = next.activityInfo.name;
                    break;
                }
            }
            if (BuildConfig.FLAVOR.equals(str)) {
                return;
            }
            if (Build.VERSION.SDK_INT == 31) {
                EventUtils.startActivityIfNotRuning(this.mContext, EventUtils.LIDIAN_MODE_PACKAGE_NAME, str);
            } else {
                EventUtils.startActivityIfNotRuning(this.mContext, EventUtils.XYQ_MODE_PACKAGE_NAME, str);
            }
        }
    }

    public void refreshLRChkP(boolean z) {
        if (this.mIsPon != z) {
            this.mIsPon = z;
            CheckBox checkBox = this.chkRadar;
            if (checkBox != null) {
                checkBox.setChecked(z);
            }
        }
    }

    public /* synthetic */ void lambda$new$1$LandRoverBottomView() {
        sendLandRoverKey(this.extra, 2);
    }
}
