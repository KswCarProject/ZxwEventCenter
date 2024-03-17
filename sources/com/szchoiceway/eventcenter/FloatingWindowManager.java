package com.szchoiceway.eventcenter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.example.mylibrary.BuildConfig;
import com.szchoiceway.eventcenter.AccEvent.Utils;

public class FloatingWindowManager implements View.OnTouchListener, View.OnClickListener {
    private static final String TAG = "FloatingWindowManager";
    private String mAppVersion;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public EventService mEventService;
    private boolean mIsAdd = false;
    private boolean mIsMoving = false;
    private View mLayoutContent;
    private WindowManager.LayoutParams mMenuLayoutParams;
    private boolean mTipIsAdd = false;
    private WindowManager.LayoutParams mTipLayoutParams;
    private View mTipView;
    private int mTouchStartX;
    private int mTouchStartY;
    private View mTvHome;
    private View mTvMenu;
    private View mTvVoice;
    private View mView;
    private WindowManager wm;
    private float x;
    private float y;

    public FloatingWindowManager(Context context) {
        EventService eventService = (EventService) context;
        this.mEventService = eventService;
        this.mAppVersion = eventService.mSysProviderOpt.getRecordValue(SysProviderOpt.SYS_APP_VERSION, BuildConfig.FLAVOR);
        this.mAppVersion += "--" + this.mEventService.mSysProviderOpt.getRecordInteger("ly_encrypt_ic", 1);
        this.mContext = context;
        this.wm = (WindowManager) context.getSystemService("window");
        initTipView();
        initMenuView();
    }

    private void initTipView() {
        this.mTipView = View.inflate(this.mContext, R.layout.layout_floating_tip, (ViewGroup) null);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.mTipLayoutParams = layoutParams;
        layoutParams.type = 2010;
        this.mTipLayoutParams.format = 1;
        this.mTipLayoutParams.flags = 296;
        this.mTipLayoutParams.gravity = 51;
        this.mTipLayoutParams.width = -1;
        this.mTipLayoutParams.height = -1;
        TextView textView = (TextView) this.mTipView.findViewById(R.id.TvAppVer);
        if (textView != null) {
            textView.setText(this.mAppVersion);
        }
        View findViewById = this.mTipView.findViewById(R.id.IvTouch);
        if (findViewById != null) {
            findViewById.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    FloatingWindowManager.this.hideTipView();
                    FloatingWindowManager.this.mEventService.mEventHandler.postDelayed(new Runnable() {
                        public void run() {
                            FloatingWindowManager.this.showTipView();
                        }
                    }, 30000);
                    return true;
                }
            });
        }
    }

    private void initMenuView() {
        this.mView = View.inflate(this.mContext, R.layout.layout_floating_menu, (ViewGroup) null);
        this.mMenuLayoutParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= 26) {
            this.mMenuLayoutParams.type = 2038;
        } else {
            this.mMenuLayoutParams.type = 2002;
        }
        this.mMenuLayoutParams.format = 1;
        this.mMenuLayoutParams.flags = 296;
        this.mMenuLayoutParams.gravity = 8388659;
        this.mMenuLayoutParams.width = -2;
        this.mMenuLayoutParams.height = -2;
        this.mMenuLayoutParams.x = this.mEventService.screenWidth / 8;
        this.mMenuLayoutParams.y = this.mEventService.screenHeight / 4;
        View findViewById = this.mView.findViewById(R.id.LayoutContent);
        this.mLayoutContent = findViewById;
        if (findViewById != null) {
            findViewById.setOnTouchListener(this);
        }
        View findViewById2 = this.mView.findViewById(R.id.TvMenu);
        this.mTvMenu = findViewById2;
        if (findViewById2 != null) {
            findViewById2.setOnTouchListener(this);
        }
        View findViewById3 = this.mView.findViewById(R.id.TvVoice);
        this.mTvVoice = findViewById3;
        if (findViewById3 != null) {
            findViewById3.setOnClickListener(this);
        }
        View findViewById4 = this.mView.findViewById(R.id.TvHome);
        this.mTvHome = findViewById4;
        if (findViewById4 != null) {
            findViewById4.setOnClickListener(this);
        }
        View findViewById5 = this.mView.findViewById(R.id.TvReturn);
        if (findViewById5 != null) {
            findViewById5.setOnClickListener(this);
        }
    }

    public void setVoiceView(boolean z) {
        View view = this.mTvVoice;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
    }

    private void addTipView() {
        if (!this.mTipIsAdd) {
            WindowManager windowManager = this.wm;
            if (windowManager != null) {
                windowManager.addView(this.mTipView, this.mTipLayoutParams);
            }
            this.mTipIsAdd = true;
        }
    }

    public void showTipView() {
        addTipView();
    }

    public void hideTipView() {
        removeTipView();
    }

    private void removeTipView() {
        if (this.mTipIsAdd) {
            WindowManager windowManager = this.wm;
            if (windowManager != null) {
                windowManager.removeView(this.mTipView);
            }
            this.mTipIsAdd = false;
        }
    }

    private void addMenuView() {
        if (!this.mIsAdd) {
            autoHideMenuContent();
            WindowManager windowManager = this.wm;
            if (windowManager != null) {
                windowManager.addView(this.mView, this.mMenuLayoutParams);
            }
            this.mIsAdd = true;
        }
    }

    private void removeMenuView() {
        if (this.mIsAdd) {
            hideMenuContent();
            WindowManager windowManager = this.wm;
            if (windowManager != null) {
                windowManager.removeView(this.mView);
            }
            this.mIsAdd = false;
        }
    }

    private void updateMenuView() {
        WindowManager windowManager;
        if (this.mIsAdd && (windowManager = this.wm) != null) {
            windowManager.updateViewLayout(this.mView, this.mMenuLayoutParams);
        }
    }

    public void showMenuView() {
        addMenuView();
    }

    public void hideMenuView() {
        removeMenuView();
    }

    public void autoHideMenuContent() {
        EventService eventService = this.mEventService;
        if (eventService != null) {
            eventService.mEventHandler.removeMessages(EventUtils.WHAT_HIDE_MEMU_CONTENT);
            this.mEventService.mEventHandler.sendEmptyMessageDelayed(EventUtils.WHAT_HIDE_MEMU_CONTENT, 3500);
            this.mEventService.mEventHandler.removeMessages(EventUtils.WHAT_HIDE_MEMU_ROOT);
            this.mEventService.mEventHandler.sendEmptyMessageDelayed(EventUtils.WHAT_HIDE_MEMU_ROOT, 6000);
        }
    }

    public void hideMenuContent() {
        Log.i(TAG, "hideMenuContent: ");
        View view = this.mLayoutContent;
        if (view != null) {
            view.setVisibility(8);
        }
        View view2 = this.mTvMenu;
        if (view2 != null) {
            view2.setVisibility(0);
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (R.id.TvMenu != view.getId()) {
            return false;
        }
        this.x = motionEvent.getRawX();
        this.y = motionEvent.getRawY();
        autoHideMenuContent();
        int action = motionEvent.getAction();
        if (action == 0) {
            Log.i(TAG, "onTouch: ACTION_DOWN");
            this.mIsMoving = false;
            this.mTouchStartX = (int) motionEvent.getX();
            this.mTouchStartY = (int) motionEvent.getY();
        } else if (action == 1) {
            Log.i(TAG, "onTouch: ACTION_UP");
            if (!this.mIsMoving) {
                controlContent();
                View view2 = this.mLayoutContent;
                if (view2 != null) {
                    view2.setVisibility(8 - view2.getVisibility());
                }
                View view3 = this.mTvMenu;
                if (view3 != null) {
                    view3.setVisibility(8 - this.mLayoutContent.getVisibility());
                }
            }
            this.mIsMoving = false;
            this.mTouchStartX = 0;
            this.mTouchStartY = 0;
        } else if (action == 2) {
            Log.i(TAG, "onTouch: ACTION_MOVE");
            float abs = Math.abs(motionEvent.getX() - ((float) this.mTouchStartX));
            float abs2 = Math.abs(motionEvent.getY() - ((float) this.mTouchStartY));
            Log.i(TAG, "onTouch: vX = " + abs + ",vY = " + abs2);
            if (abs > 15.0f || abs2 > 15.0f) {
                this.mIsMoving = true;
                this.mMenuLayoutParams.x = (int) (this.x - ((float) this.mTouchStartX));
                this.mMenuLayoutParams.y = (int) (this.y - ((float) this.mTouchStartY));
                updateMenuView();
            }
        }
        return true;
    }

    private void controlContent() {
        if (this.mTvHome == null) {
            return;
        }
        if (!this.mEventService.bZlinkHicarResume || !this.mEventService.bZlinkHicarConnected) {
            this.mTvHome.setBackgroundResource(R.drawable.imitate_auto_floating_home);
        } else {
            this.mTvHome.setBackgroundResource(R.drawable.imitate_auto_floating_exit_hicar);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.TvHome) {
            EventService eventService = this.mEventService;
            if (eventService != null) {
                if (!eventService.bZlinkHicarResume || !this.mEventService.bZlinkHicarConnected) {
                    this.mEventService.sendKeyDownUpSync(3);
                } else {
                    this.mContext.sendBroadcast(new Intent("com.choiceway.action.Exit_LINK"));
                    this.mEventService.sendKeyDownUpSync(4);
                    this.mEventService.mEventHandler.postDelayed(new Runnable() {
                        public void run() {
                            Utils.setWifiMode(FloatingWindowManager.this.mContext, true);
                        }
                    }, 500);
                }
            }
        } else if (id == R.id.TvReturn) {
            EventService eventService2 = this.mEventService;
            if (eventService2 != null) {
                eventService2.sendKeyDownUpSync(4);
            }
        } else if (id == R.id.TvVoice) {
            EventUtils.startVoice(this.mContext);
        }
        autoHideMenuContent();
    }
}
