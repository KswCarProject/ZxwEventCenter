package com.szchoiceway.eventcenter.AssistiveTouch;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.szchoiceway.eventcenter.EventApp;
import com.szchoiceway.eventcenter.EventService;
import com.szchoiceway.eventcenter.EventUtils;
import com.szchoiceway.eventcenter.R;
import com.szchoiceway.eventcenter.SysProviderOpt;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TopFloatService extends Service implements View.OnClickListener, View.OnKeyListener, View.OnLongClickListener, View.OnTouchListener {
    protected static final String TAG = "TopFloatService";
    private static final int TEST_CAN_SEND_DATA = 5;
    private static final int WM_VOL_DOWN_EVT = 3;
    private static final int WM_VOL_MUTE_EVT = 4;
    private static final int WM_VOL_UP_EVT = 2;
    protected static EventService mContext;
    private static PopupWindow pop;
    private boolean bBtnDown = false;
    boolean bTest = true;
    boolean b_Customize_Resum = false;
    private View ballView;
    WindowManager.LayoutParams ballWmParams = null;
    private TextView btn_apps;
    private TextView btn_favor;
    private TextView btn_home_screen;
    private TextView btn_lock_screen;
    private TextView btn_setting;
    private Button floatImage;
    private int iBtnDownCnt = 0;
    /* access modifiers changed from: private */
    public boolean ismoving = false;
    private LongClickThread mBtnDelLongClick = null;
    Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i != R.id.btn_favor) {
                if (i == R.id.btn_setting && TopFloatService.this.bTest) {
                    TopFloatService.mContext.processAutoKey(2);
                }
            } else if (TopFloatService.this.bTest) {
                TopFloatService.mContext.processAutoKey(1);
            }
        }
    };
    /* access modifiers changed from: private */
    public float mTouchStartX;
    /* access modifiers changed from: private */
    public float mTouchStartY;
    private int m_iTest = 0;
    private int m_iTest_Radar = 0;
    private RelativeLayout menuLayout;
    private RelativeLayout menuTop;
    /* access modifiers changed from: private */
    public View menuView;
    private ScheduledExecutorService scheduledExecutor;
    int statusBarHeight = 0;
    WindowManager wm = null;
    /* access modifiers changed from: private */
    public float x;
    /* access modifiers changed from: private */
    public float y;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        Log.i(TAG, "ikeyCode === " + keyCode);
        if (keyCode == 3) {
            pop.dismiss();
            return true;
        } else if (keyCode != 4) {
            return true;
        } else {
            pop.dismiss();
            return true;
        }
    }

    public class LongClickThread extends Thread {
        private static final int TOUCH_SLOP = 1;
        private boolean isMoved;
        private boolean isReleased;
        private View.OnClickListener mClickLis = null;
        private int mCounter = 0;
        private int mLastMotionX;
        private int mLastMotionY;
        /* access modifiers changed from: private */
        public View.OnLongClickListener mLongClickLis = null;
        private Runnable mLongPressRunnable = new Runnable() {
            public void run() {
                if (LongClickThread.this.mLongClickLis != null) {
                    LongClickThread.this.mLongClickLis.onLongClick(LongClickThread.this.mView);
                }
            }
        };
        private boolean mRunOnce = false;
        /* access modifiers changed from: private */
        public View mView = null;

        public LongClickThread() {
        }

        public void setView(View view, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener) {
            this.mView = view;
            this.mClickLis = onClickListener;
            this.mLongClickLis = onLongClickListener;
        }

        public void onlyRunOnce() {
            this.mRunOnce = true;
        }

        public void exitThread() {
            this.isReleased = true;
            this.isMoved = true;
        }

        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            View.OnClickListener onClickListener;
            View view;
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            int action = motionEvent.getAction();
            if (action == 0) {
                this.mLastMotionX = x;
                this.mLastMotionY = y;
                Log.i(TopFloatService.TAG, "case MotionEvent.ACTION_DOWN");
                this.isReleased = false;
                this.isMoved = false;
            } else if (action == 1) {
                this.isReleased = true;
                if (this.mCounter == 0 && !this.isMoved && (onClickListener = this.mClickLis) != null && (view = this.mView) != null) {
                    onClickListener.onClick(view);
                }
            } else if (action == 2 && !this.isMoved && (Math.abs(this.mLastMotionX - x) > 1 || Math.abs(this.mLastMotionY - y) > 1)) {
                this.isMoved = true;
            }
            return false;
        }

        public void run() {
            View view;
            View view2;
            if (!this.isMoved && !this.isReleased) {
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!this.isMoved && !this.isReleased) {
                    this.mCounter++;
                    if (!(this.mLongClickLis == null || (view2 = this.mView) == null)) {
                        view2.post(this.mLongPressRunnable);
                    }
                    while (!this.mRunOnce && !this.isMoved && !this.isReleased) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                        if (!this.isMoved && !this.isReleased) {
                            this.mCounter++;
                            if (!(this.mLongClickLis == null || (view = this.mView) == null)) {
                                view.post(this.mLongPressRunnable);
                            }
                        } else {
                            return;
                        }
                    }
                }
            }
        }
    }

    public void onCreate() {
        super.onCreate();
        View inflate = LayoutInflater.from(this).inflate(R.layout.floatball, (ViewGroup) null);
        this.ballView = inflate;
        this.floatImage = (Button) inflate.findViewById(R.id.float_image);
        setUpFloatMenuView();
        createView();
        Log.i(TAG, "2****setUpFloatMenuView****2");
    }

    public static void SetEvtServiceContext(EventService eventService) {
        mContext = eventService;
        Log.i(TAG, "SetEvtServiceContext");
    }

    private void setUpFloatMenuView() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.floatmenu, (ViewGroup) null);
        this.menuView = inflate;
        this.menuLayout = (RelativeLayout) inflate.findViewById(R.id.menu);
        this.menuTop = (RelativeLayout) this.menuView.findViewById(R.id.lay_main);
        this.menuLayout.setOnClickListener(this);
        this.menuLayout.setOnKeyListener(this);
        this.menuTop.setOnClickListener(this);
        TextView textView = (TextView) this.menuView.findViewById(R.id.btn_apps);
        this.btn_apps = textView;
        textView.setOnClickListener(this);
        TextView textView2 = (TextView) this.menuView.findViewById(R.id.btn_home_screen);
        this.btn_home_screen = textView2;
        textView2.setOnClickListener(this);
        TextView textView3 = (TextView) this.menuView.findViewById(R.id.btn_setting);
        this.btn_setting = textView3;
        textView3.setOnClickListener(this);
        this.btn_setting.setOnLongClickListener(this);
        this.btn_setting.setOnTouchListener(this);
        this.btn_setting.setOnLongClickListener(this);
        TextView textView4 = (TextView) this.menuView.findViewById(R.id.btn_lock_screen);
        this.btn_lock_screen = textView4;
        textView4.setOnClickListener(this);
        TextView textView5 = (TextView) this.menuView.findViewById(R.id.btn_favor);
        this.btn_favor = textView5;
        textView5.setOnClickListener(this);
        this.btn_favor.setOnLongClickListener(this);
        this.btn_favor.setOnTouchListener(this);
    }

    private void createView() {
        this.wm = (WindowManager) getApplicationContext().getSystemService("window");
        WindowManager.LayoutParams mywmParams = ((EventApp) getApplication()).getMywmParams();
        this.ballWmParams = mywmParams;
        mywmParams.type = 2003;
        this.ballWmParams.flags |= 8;
        this.ballWmParams.gravity = 51;
        this.ballWmParams.x = 10;
        this.ballWmParams.y = 140;
        this.ballWmParams.width = -2;
        this.ballWmParams.height = -2;
        this.ballWmParams.format = 1;
        this.wm.addView(this.ballView, this.ballWmParams);
        this.floatImage.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float unused = TopFloatService.this.x = motionEvent.getRawX();
                float unused2 = TopFloatService.this.y = motionEvent.getRawY();
                int action = motionEvent.getAction();
                if (action == 0) {
                    boolean unused3 = TopFloatService.this.ismoving = false;
                    float unused4 = TopFloatService.this.mTouchStartX = (float) ((int) motionEvent.getX());
                    float unused5 = TopFloatService.this.mTouchStartY = (float) ((int) motionEvent.getY());
                } else if (action == 1) {
                    TopFloatService topFloatService = TopFloatService.this;
                    float unused6 = topFloatService.mTouchStartX = topFloatService.mTouchStartY = 0.0f;
                } else if (action == 2) {
                    boolean unused7 = TopFloatService.this.ismoving = true;
                    TopFloatService.this.updateViewPosition();
                }
                return TopFloatService.this.ismoving;
            }
        });
        this.floatImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.type = 2003;
                layoutParams.flags |= 8;
                layoutParams.gravity = 17;
                layoutParams.x = 0;
                layoutParams.y = 0;
                layoutParams.width = -1;
                layoutParams.height = -1;
                layoutParams.format = 1;
                TopFloatService.this.wm.addView(TopFloatService.this.menuView, layoutParams);
            }
        });
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            this.statusBarHeight = getResources().getDimensionPixelSize(identifier);
        }
    }

    /* access modifiers changed from: private */
    public void updateViewPosition() {
        this.ballWmParams.x = (int) (this.x - this.mTouchStartX);
        this.ballWmParams.y = ((int) (this.y - this.mTouchStartY)) - this.statusBarHeight;
        this.wm.updateViewLayout(this.ballView, this.ballWmParams);
    }

    public void onClick(View view) {
        int streamVolume = mContext.mAudioMgr.getStreamVolume(3);
        Log.i(TAG, "onClick: streamVolume = " + streamVolume);
        int lastAudibleStreamVolume = mContext.mAudioMgr.getLastAudibleStreamVolume(3);
        Log.i(TAG, "onClick: lastAudibleStreamVolume = " + lastAudibleStreamVolume);
        Log.i(TAG, "onClick: SysProviderOpt.m_bSupportFocusMove = " + SysProviderOpt.m_bSupportFocusMove);
        int id = view.getId();
        if (id == R.id.lay_main) {
            return;
        }
        if (id != R.id.menu) {
            switch (id) {
                case R.id.btn_apps /*2131230914*/:
                    Log.d(TAG, "onClick: btn_apps === " + SysProviderOpt.m_bSupportFocusMove);
                    if (SysProviderOpt.m_bSupportFocusMove) {
                        if (Build.VERSION.SDK_INT < 26) {
                            this.b_Customize_Resum = mContext.mSysProviderOpt.getRecordBoolean(SysProviderOpt.MAISILUO_LAUNCHER_APPS_CUSTOMIZE_RESUM, false);
                        }
                        if (this.bTest) {
                            mContext.processAutoKey(23);
                            return;
                        } else if (this.b_Customize_Resum) {
                            mContext.sendKeyDownUpSync(23);
                            return;
                        } else {
                            Intent intent = new Intent(EventUtils.ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_EVT);
                            intent.putExtra(EventUtils.ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_DATA, 5);
                            sendBroadcast(intent);
                            return;
                        }
                    } else {
                        EventService eventService = mContext;
                        if (eventService != null) {
                            eventService.sendKeyDownUpSync(4);
                            return;
                        }
                        return;
                    }
                case R.id.btn_favor /*2131230915*/:
                    if (SysProviderOpt.m_bSupportFocusMove) {
                        if (Build.VERSION.SDK_INT < 26) {
                            this.b_Customize_Resum = mContext.mSysProviderOpt.getRecordBoolean(SysProviderOpt.MAISILUO_LAUNCHER_APPS_CUSTOMIZE_RESUM, false);
                        }
                        if (this.bTest) {
                            mContext.processAutoKey(1);
                            return;
                        } else if (this.b_Customize_Resum) {
                            mContext.sendKeyDownUpSync(21);
                            return;
                        } else {
                            Intent intent2 = new Intent(EventUtils.ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_EVT);
                            intent2.putExtra(EventUtils.ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_DATA, 7);
                            sendBroadcast(intent2);
                            return;
                        }
                    } else {
                        EventUtils.sendSysBroadcast(getApplicationContext(), EventUtils.ZXW_SYS_KEY_EVT, 19);
                        return;
                    }
                case R.id.btn_home_screen /*2131230916*/:
                    if (SysProviderOpt.m_bSupportFocusMove) {
                        if (Build.VERSION.SDK_INT < 26) {
                            this.b_Customize_Resum = mContext.mSysProviderOpt.getRecordBoolean(SysProviderOpt.MAISILUO_LAUNCHER_APPS_CUSTOMIZE_RESUM, false);
                        }
                        if (this.bTest) {
                            mContext.processAutoKey(22);
                            return;
                        } else if (this.b_Customize_Resum) {
                            mContext.sendKeyDownUpSync(20);
                            return;
                        } else {
                            Intent intent3 = new Intent(EventUtils.ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_EVT);
                            intent3.putExtra(EventUtils.ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_DATA, 2);
                            sendBroadcast(intent3);
                            return;
                        }
                    } else {
                        EventService eventService2 = mContext;
                        if (eventService2 != null) {
                            eventService2.sendKeyDownUpSync(3);
                            return;
                        }
                        return;
                    }
                case R.id.btn_lock_screen /*2131230917*/:
                    if (SysProviderOpt.m_bSupportFocusMove) {
                        if (Build.VERSION.SDK_INT < 26) {
                            this.b_Customize_Resum = mContext.mSysProviderOpt.getRecordBoolean(SysProviderOpt.MAISILUO_LAUNCHER_APPS_CUSTOMIZE_RESUM, false);
                        }
                        if (this.bTest) {
                            mContext.processAutoKey(21);
                            return;
                        } else if (this.b_Customize_Resum) {
                            mContext.sendKeyDownUpSync(19);
                            return;
                        } else {
                            Intent intent4 = new Intent(EventUtils.ZXW_ORIGINAL_MCU_KEY_MOUSE_MOVE_EVT);
                            intent4.putExtra(EventUtils.ZXW_ORIGINAL_MCU_KEY_MOUSE_MOVE_DATA, 3);
                            sendBroadcast(intent4);
                            return;
                        }
                    } else if (mContext.GetPowerStatus()) {
                        mContext.PowerOff(false);
                        return;
                    } else {
                        EventUtils.sendSysBroadcast(getApplicationContext(), EventUtils.ZXW_SYS_KEY_EVT, 17);
                        return;
                    }
                case R.id.btn_setting /*2131230918*/:
                    if (SysProviderOpt.m_bSupportFocusMove) {
                        if (Build.VERSION.SDK_INT < 26) {
                            this.b_Customize_Resum = mContext.mSysProviderOpt.getRecordBoolean(SysProviderOpt.MAISILUO_LAUNCHER_APPS_CUSTOMIZE_RESUM, false);
                        }
                        if (this.bTest) {
                            mContext.processAutoKey(2);
                            return;
                        } else if (this.b_Customize_Resum) {
                            mContext.sendKeyDownUpSync(22);
                            return;
                        } else {
                            Intent intent5 = new Intent(EventUtils.ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_EVT);
                            intent5.putExtra(EventUtils.ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_DATA, 8);
                            sendBroadcast(intent5);
                            return;
                        }
                    } else {
                        EventUtils.sendSysBroadcast(getApplicationContext(), EventUtils.ZXW_SYS_KEY_EVT, 18);
                        return;
                    }
                default:
                    PopupWindow popupWindow = pop;
                    if (popupWindow != null && popupWindow.isShowing()) {
                        pop.dismiss();
                        return;
                    }
                    return;
            }
        } else {
            Log.d(TAG, "onClick: menu ");
            this.wm.removeView(this.menuView);
        }
    }

    public boolean onLongClick(View view) {
        try {
            int id = view.getId();
            if (id != R.id.btn_favor) {
                if (id == R.id.btn_setting) {
                    if (!this.bTest) {
                        mContext.sendKeyDownUpSync(2);
                    }
                }
            } else if (!this.bTest) {
                mContext.sendKeyDownUpSync(1);
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return true;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            stopAddOrSubtract();
            return false;
        }
        updateAddOrSubtract(view.getId());
        return false;
    }

    public static void HideTopFloat(boolean z) {
        PopupWindow popupWindow = pop;
        if (popupWindow != null && popupWindow.isShowing()) {
            pop.dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public void updateAddOrSubtract(final int i) {
        if (this.scheduledExecutor == null) {
            ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
            this.scheduledExecutor = newSingleThreadScheduledExecutor;
            newSingleThreadScheduledExecutor.scheduleWithFixedDelay(new Runnable() {
                public void run() {
                    Message message = new Message();
                    message.what = i;
                    TopFloatService.this.mHandler.sendMessage(message);
                }
            }, 500, 100, TimeUnit.MILLISECONDS);
        }
    }

    /* access modifiers changed from: protected */
    public void stopAddOrSubtract() {
        ScheduledExecutorService scheduledExecutorService = this.scheduledExecutor;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
            this.scheduledExecutor = null;
        }
    }

    public void sendBroadcastCanDataExtra(Context context, String str, String str2, int i) {
        Intent intent = new Intent(str);
        intent.putExtra(str2, i);
        sendBroadcast(intent);
    }
}
