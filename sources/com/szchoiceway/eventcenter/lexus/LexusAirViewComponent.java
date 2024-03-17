package com.szchoiceway.eventcenter.lexus;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.mylibrary.BuildConfig;
import com.szchoiceway.eventcenter.JavaLazy;
import com.szchoiceway.eventcenter.JavaStandard;
import com.szchoiceway.eventcenter.R;
import com.szchoiceway.eventcenter.function.Function0;
import com.szchoiceway.eventcenter.function.Function1Void;
import com.szchoiceway.eventcenter.lexus.LexusAirViewComponent;
import com.szchoiceway.eventcenter.listener.OnLexusAirEventListener;

public class LexusAirViewComponent {
    private ImageView air_switch_ac_btn;
    private ImageView air_switch_auto_btn;
    private ImageView air_switch_cycle_btn;
    private ImageView air_switch_dual_btn;
    private ImageView air_switch_front_btn;
    private ImageView air_switch_rear_btn;
    private ImageView air_win_mode_iv;
    private ImageView air_win_speed_iv;
    private Context context;
    private boolean isAcChecked = false;
    private boolean isAutoChecked = false;
    private boolean isCycleChecked = false;
    private boolean isDualChecked = false;
    private boolean isFrontChecked = false;
    private boolean isRearChecked = false;
    private JavaLazy<WindowManager.LayoutParams> layoutParams = new JavaLazy<>(new Function0() {
        public final Object invoke() {
            return LexusAirViewComponent.this.lambda$new$1$LexusAirViewComponent();
        }
    });
    /* access modifiers changed from: private */
    public OnLexusAirEventListener onClickListener = new OnLexusAirEventListener(this.context);
    private View rootView;
    private TextView tvAirLeftTemp;
    private TextView tvAirRightTemp;
    private TextView tvAirWindSpeed;

    public /* synthetic */ WindowManager.LayoutParams lambda$new$1$LexusAirViewComponent() {
        return (WindowManager.LayoutParams) JavaStandard.also(new WindowManager.LayoutParams(), new Function1Void() {
            public final void invoke(Object obj) {
                LexusAirViewComponent.this.lambda$new$0$LexusAirViewComponent((WindowManager.LayoutParams) obj);
            }
        });
    }

    public /* synthetic */ void lambda$new$0$LexusAirViewComponent(WindowManager.LayoutParams layoutParams2) {
        layoutParams2.type = 2002;
        layoutParams2.format = 1;
        layoutParams2.flags = 296;
        layoutParams2.gravity = 51;
        layoutParams2.x = 0;
        layoutParams2.width = (int) this.context.getResources().getDimension(R.dimen.backcar_width);
        layoutParams2.y = 0;
        layoutParams2.height = -1;
        layoutParams2.windowAnimations = R.style.PopupAnimation2;
    }

    public LexusAirViewComponent(Context context2) {
        this.context = context2;
    }

    public void initRootView() {
        this.rootView = View.inflate(this.context, R.layout.layout_lexus_car_air, (ViewGroup) null);
    }

    public void init() {
        View view = this.rootView;
        if (view != null) {
            JavaStandard.also(view.findViewById(R.id.air_left_win_up_btn), new Function1Void() {
                public final void invoke(Object obj) {
                    LexusAirViewComponent.this.lambda$init$3$LexusAirViewComponent((View) obj);
                }
            });
            JavaStandard.also(this.rootView.findViewById(R.id.air_left_win_down_btn), new Function1Void() {
                public final void invoke(Object obj) {
                    LexusAirViewComponent.this.lambda$init$5$LexusAirViewComponent((View) obj);
                }
            });
            JavaStandard.also(this.rootView.findViewById(R.id.air_win_speed_up_btn), new Function1Void() {
                public final void invoke(Object obj) {
                    LexusAirViewComponent.this.lambda$init$7$LexusAirViewComponent((View) obj);
                }
            });
            JavaStandard.also(this.rootView.findViewById(R.id.air_win_speed_down_btn), new Function1Void() {
                public final void invoke(Object obj) {
                    LexusAirViewComponent.this.lambda$init$9$LexusAirViewComponent((View) obj);
                }
            });
            JavaStandard.also(this.rootView.findViewById(R.id.air_right_win_up_btn), new Function1Void() {
                public final void invoke(Object obj) {
                    LexusAirViewComponent.this.lambda$init$11$LexusAirViewComponent((View) obj);
                }
            });
            JavaStandard.also(this.rootView.findViewById(R.id.air_right_win_down_btn), new Function1Void() {
                public final void invoke(Object obj) {
                    LexusAirViewComponent.this.lambda$init$13$LexusAirViewComponent((View) obj);
                }
            });
            this.air_switch_dual_btn = (ImageView) JavaStandard.also((ImageView) this.rootView.findViewById(R.id.air_switch_dual_btn), new Function1Void() {
                public final void invoke(Object obj) {
                    LexusAirViewComponent.this.lambda$init$15$LexusAirViewComponent((ImageView) obj);
                }
            });
            this.air_switch_ac_btn = (ImageView) JavaStandard.also((ImageView) this.rootView.findViewById(R.id.air_switch_ac_btn), new Function1Void() {
                public final void invoke(Object obj) {
                    LexusAirViewComponent.this.lambda$init$17$LexusAirViewComponent((ImageView) obj);
                }
            });
            this.air_switch_auto_btn = (ImageView) JavaStandard.also((ImageView) this.rootView.findViewById(R.id.air_switch_auto_btn), new Function1Void() {
                public final void invoke(Object obj) {
                    LexusAirViewComponent.this.lambda$init$19$LexusAirViewComponent((ImageView) obj);
                }
            });
            this.air_switch_cycle_btn = (ImageView) JavaStandard.also((ImageView) this.rootView.findViewById(R.id.air_switch_cycle_btn), new Function1Void() {
                public final void invoke(Object obj) {
                    LexusAirViewComponent.this.lambda$init$21$LexusAirViewComponent((ImageView) obj);
                }
            });
            this.air_switch_front_btn = (ImageView) JavaStandard.also((ImageView) this.rootView.findViewById(R.id.air_switch_front_btn), new Function1Void() {
                public final void invoke(Object obj) {
                    LexusAirViewComponent.this.lambda$init$23$LexusAirViewComponent((ImageView) obj);
                }
            });
            this.air_switch_rear_btn = (ImageView) JavaStandard.also((ImageView) this.rootView.findViewById(R.id.air_switch_rear_btn), new Function1Void() {
                public final void invoke(Object obj) {
                    LexusAirViewComponent.this.lambda$init$25$LexusAirViewComponent((ImageView) obj);
                }
            });
            this.tvAirLeftTemp = (TextView) this.rootView.findViewById(R.id.tvAirLeftTemp);
            this.tvAirWindSpeed = (TextView) this.rootView.findViewById(R.id.tvAirWindSpeed);
            this.air_win_speed_iv = (ImageView) this.rootView.findViewById(R.id.air_win_speed_iv);
            this.air_win_mode_iv = (ImageView) this.rootView.findViewById(R.id.air_win_mode_iv);
            this.tvAirRightTemp = (TextView) this.rootView.findViewById(R.id.tvAirRightTemp);
        }
    }

    public /* synthetic */ void lambda$init$2$LexusAirViewComponent(View view, View view2) {
        view.setOnTouchListener(this.onClickListener);
    }

    public /* synthetic */ void lambda$init$3$LexusAirViewComponent(View view) {
        JavaStandard.runIfNonNull(view, new Function1Void(view) {
            public final /* synthetic */ View f$1;

            {
                this.f$1 = r2;
            }

            public final void invoke(Object obj) {
                LexusAirViewComponent.this.lambda$init$2$LexusAirViewComponent(this.f$1, (View) obj);
            }
        });
    }

    public /* synthetic */ void lambda$init$4$LexusAirViewComponent(View view, View view2) {
        view.setOnTouchListener(this.onClickListener);
    }

    public /* synthetic */ void lambda$init$5$LexusAirViewComponent(View view) {
        JavaStandard.runIfNonNull(view, new Function1Void(view) {
            public final /* synthetic */ View f$1;

            {
                this.f$1 = r2;
            }

            public final void invoke(Object obj) {
                LexusAirViewComponent.this.lambda$init$4$LexusAirViewComponent(this.f$1, (View) obj);
            }
        });
    }

    public /* synthetic */ void lambda$init$6$LexusAirViewComponent(View view, View view2) {
        view.setOnTouchListener(this.onClickListener);
    }

    public /* synthetic */ void lambda$init$7$LexusAirViewComponent(View view) {
        JavaStandard.runIfNonNull(view, new Function1Void(view) {
            public final /* synthetic */ View f$1;

            {
                this.f$1 = r2;
            }

            public final void invoke(Object obj) {
                LexusAirViewComponent.this.lambda$init$6$LexusAirViewComponent(this.f$1, (View) obj);
            }
        });
    }

    public /* synthetic */ void lambda$init$8$LexusAirViewComponent(View view, View view2) {
        view.setOnTouchListener(this.onClickListener);
    }

    public /* synthetic */ void lambda$init$9$LexusAirViewComponent(View view) {
        JavaStandard.runIfNonNull(view, new Function1Void(view) {
            public final /* synthetic */ View f$1;

            {
                this.f$1 = r2;
            }

            public final void invoke(Object obj) {
                LexusAirViewComponent.this.lambda$init$8$LexusAirViewComponent(this.f$1, (View) obj);
            }
        });
    }

    public /* synthetic */ void lambda$init$10$LexusAirViewComponent(View view, View view2) {
        view.setOnTouchListener(this.onClickListener);
    }

    public /* synthetic */ void lambda$init$11$LexusAirViewComponent(View view) {
        JavaStandard.runIfNonNull(view, new Function1Void(view) {
            public final /* synthetic */ View f$1;

            {
                this.f$1 = r2;
            }

            public final void invoke(Object obj) {
                LexusAirViewComponent.this.lambda$init$10$LexusAirViewComponent(this.f$1, (View) obj);
            }
        });
    }

    public /* synthetic */ void lambda$init$12$LexusAirViewComponent(View view, View view2) {
        view.setOnTouchListener(this.onClickListener);
    }

    public /* synthetic */ void lambda$init$13$LexusAirViewComponent(View view) {
        JavaStandard.runIfNonNull(view, new Function1Void(view) {
            public final /* synthetic */ View f$1;

            {
                this.f$1 = r2;
            }

            public final void invoke(Object obj) {
                LexusAirViewComponent.this.lambda$init$12$LexusAirViewComponent(this.f$1, (View) obj);
            }
        });
    }

    public /* synthetic */ void lambda$init$15$LexusAirViewComponent(ImageView imageView) {
        JavaStandard.runIfNonNull(imageView, new Function1Void(imageView) {
            public final /* synthetic */ ImageView f$1;

            {
                this.f$1 = r2;
            }

            public final void invoke(Object obj) {
                LexusAirViewComponent.this.lambda$init$14$LexusAirViewComponent(this.f$1, (ImageView) obj);
            }
        });
    }

    public /* synthetic */ void lambda$init$14$LexusAirViewComponent(ImageView imageView, ImageView imageView2) {
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ButtonState access$000 = LexusAirViewComponent.this.buildButtonState();
                access$000.isDualChecked = !access$000.isDualChecked;
                view.setTag(access$000);
                LexusAirViewComponent.this.onClickListener.onClick(view);
            }
        });
    }

    public /* synthetic */ void lambda$init$17$LexusAirViewComponent(ImageView imageView) {
        JavaStandard.runIfNonNull(imageView, new Function1Void(imageView) {
            public final /* synthetic */ ImageView f$1;

            {
                this.f$1 = r2;
            }

            public final void invoke(Object obj) {
                LexusAirViewComponent.this.lambda$init$16$LexusAirViewComponent(this.f$1, (ImageView) obj);
            }
        });
    }

    public /* synthetic */ void lambda$init$16$LexusAirViewComponent(ImageView imageView, ImageView imageView2) {
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ButtonState access$000 = LexusAirViewComponent.this.buildButtonState();
                access$000.isAcChecked = !access$000.isAcChecked;
                view.setTag(access$000);
                LexusAirViewComponent.this.onClickListener.onClick(view);
            }
        });
    }

    public /* synthetic */ void lambda$init$19$LexusAirViewComponent(ImageView imageView) {
        JavaStandard.runIfNonNull(imageView, new Function1Void(imageView) {
            public final /* synthetic */ ImageView f$1;

            {
                this.f$1 = r2;
            }

            public final void invoke(Object obj) {
                LexusAirViewComponent.this.lambda$init$18$LexusAirViewComponent(this.f$1, (ImageView) obj);
            }
        });
    }

    public /* synthetic */ void lambda$init$18$LexusAirViewComponent(ImageView imageView, ImageView imageView2) {
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ButtonState access$000 = LexusAirViewComponent.this.buildButtonState();
                access$000.isAutoChecked = true;
                view.setTag(access$000);
                LexusAirViewComponent.this.onClickListener.onClick(view);
            }
        });
    }

    public /* synthetic */ void lambda$init$21$LexusAirViewComponent(ImageView imageView) {
        JavaStandard.runIfNonNull(imageView, new Function1Void(imageView) {
            public final /* synthetic */ ImageView f$1;

            {
                this.f$1 = r2;
            }

            public final void invoke(Object obj) {
                LexusAirViewComponent.this.lambda$init$20$LexusAirViewComponent(this.f$1, (ImageView) obj);
            }
        });
    }

    public /* synthetic */ void lambda$init$20$LexusAirViewComponent(ImageView imageView, ImageView imageView2) {
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ButtonState access$000 = LexusAirViewComponent.this.buildButtonState();
                access$000.isCycleChecked = !access$000.isCycleChecked;
                view.setTag(access$000);
                LexusAirViewComponent.this.onClickListener.onClick(view);
            }
        });
    }

    public /* synthetic */ void lambda$init$23$LexusAirViewComponent(ImageView imageView) {
        JavaStandard.runIfNonNull(imageView, new Function1Void(imageView) {
            public final /* synthetic */ ImageView f$1;

            {
                this.f$1 = r2;
            }

            public final void invoke(Object obj) {
                LexusAirViewComponent.this.lambda$init$22$LexusAirViewComponent(this.f$1, (ImageView) obj);
            }
        });
    }

    public /* synthetic */ void lambda$init$22$LexusAirViewComponent(ImageView imageView, ImageView imageView2) {
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ButtonState access$000 = LexusAirViewComponent.this.buildButtonState();
                access$000.isFrontChecked = !access$000.isFrontChecked;
                view.setTag(access$000);
                LexusAirViewComponent.this.onClickListener.onClick(view);
            }
        });
    }

    public /* synthetic */ void lambda$init$25$LexusAirViewComponent(ImageView imageView) {
        JavaStandard.runIfNonNull(imageView, new Function1Void(imageView) {
            public final /* synthetic */ ImageView f$1;

            {
                this.f$1 = r2;
            }

            public final void invoke(Object obj) {
                LexusAirViewComponent.this.lambda$init$24$LexusAirViewComponent(this.f$1, (ImageView) obj);
            }
        });
    }

    public /* synthetic */ void lambda$init$24$LexusAirViewComponent(ImageView imageView, ImageView imageView2) {
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ButtonState access$000 = LexusAirViewComponent.this.buildButtonState();
                access$000.isRearChecked = !access$000.isRearChecked;
                view.setTag(access$000);
                LexusAirViewComponent.this.onClickListener.onClick(view);
            }
        });
    }

    public View getRootView() {
        return this.rootView;
    }

    public WindowManager.LayoutParams getLayoutParams() {
        return this.layoutParams.get();
    }

    public void setLefAirTemp(String str) {
        runIfVariableNonNull(this.tvAirLeftTemp, new Function1Void(str) {
            public final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            public final void invoke(Object obj) {
                ((TextView) obj).setText(this.f$0);
            }
        });
    }

    public /* synthetic */ void lambda$setWinLevel$27$LexusAirViewComponent(int i, TextView textView) {
        TextView textView2 = this.tvAirWindSpeed;
        textView2.setText(i + BuildConfig.FLAVOR);
    }

    public void setWinLevel(int i) {
        runIfVariableNonNull(this.tvAirWindSpeed, new Function1Void(i) {
            public final /* synthetic */ int f$1;

            {
                this.f$1 = r2;
            }

            public final void invoke(Object obj) {
                LexusAirViewComponent.this.lambda$setWinLevel$27$LexusAirViewComponent(this.f$1, (TextView) obj);
            }
        });
        runIfVariableNonNull(this.air_win_speed_iv, new Function1Void(i) {
            public final /* synthetic */ int f$0;

            {
                this.f$0 = r1;
            }

            public final void invoke(Object obj) {
                ((ImageView) obj).setImageLevel(this.f$0);
            }
        });
    }

    public void setWinMode(int i) {
        runIfVariableNonNull(this.air_win_mode_iv, new Function1Void(i) {
            public final /* synthetic */ int f$0;

            {
                this.f$0 = r1;
            }

            public final void invoke(Object obj) {
                ((ImageView) obj).setImageLevel(this.f$0);
            }
        });
    }

    public void setRightAirTemp(String str) {
        runIfVariableNonNull(this.tvAirRightTemp, new Function1Void(str) {
            public final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            public final void invoke(Object obj) {
                ((TextView) obj).setText(this.f$0);
            }
        });
    }

    public void setDualState(boolean z) {
        this.isDualChecked = z;
        runIfVariableNonNull(this.air_switch_dual_btn, new Function1Void(z) {
            public final /* synthetic */ boolean f$0;

            {
                this.f$0 = r1;
            }

            public final void invoke(Object obj) {
                LexusAirViewComponent.lambda$setDualState$31(this.f$0, (ImageView) obj);
            }
        });
    }

    static /* synthetic */ void lambda$setDualState$31(boolean z, ImageView imageView) {
        if (z) {
            imageView.setImageResource(R.drawable.lexus_air_btn_dual_on);
        } else {
            imageView.setImageResource(R.drawable.lexus_air_btn_dual_off);
        }
    }

    public void setAcState(boolean z) {
        this.isAcChecked = z;
        runIfVariableNonNull(this.air_switch_ac_btn, new Function1Void(z) {
            public final /* synthetic */ boolean f$0;

            {
                this.f$0 = r1;
            }

            public final void invoke(Object obj) {
                LexusAirViewComponent.lambda$setAcState$32(this.f$0, (ImageView) obj);
            }
        });
    }

    static /* synthetic */ void lambda$setAcState$32(boolean z, ImageView imageView) {
        if (z) {
            imageView.setImageResource(R.drawable.lexus_air_btn_ac_on);
        } else {
            imageView.setImageResource(R.drawable.lexus_air_btn_ac_off);
        }
    }

    public void setAutoState(boolean z) {
        this.isAutoChecked = z;
        runIfVariableNonNull(this.air_switch_auto_btn, new Function1Void(z) {
            public final /* synthetic */ boolean f$0;

            {
                this.f$0 = r1;
            }

            public final void invoke(Object obj) {
                LexusAirViewComponent.lambda$setAutoState$33(this.f$0, (ImageView) obj);
            }
        });
    }

    static /* synthetic */ void lambda$setAutoState$33(boolean z, ImageView imageView) {
        if (z) {
            imageView.setImageResource(R.drawable.lexus_air_btn_auto_on);
        } else {
            imageView.setImageResource(R.drawable.lexus_air_btn_auto_off);
        }
    }

    public void setCycleModeState(boolean z) {
        this.isCycleChecked = z;
        runIfVariableNonNull(this.air_switch_cycle_btn, new Function1Void(z) {
            public final /* synthetic */ boolean f$0;

            {
                this.f$0 = r1;
            }

            public final void invoke(Object obj) {
                LexusAirViewComponent.lambda$setCycleModeState$34(this.f$0, (ImageView) obj);
            }
        });
    }

    static /* synthetic */ void lambda$setCycleModeState$34(boolean z, ImageView imageView) {
        if (z) {
            imageView.setImageResource(R.drawable.lexus_air_btn_cycle_m1);
        } else {
            imageView.setImageResource(R.drawable.lexus_air_btn_cycle_m2);
        }
    }

    public void setFrontWinState(boolean z) {
        this.isFrontChecked = z;
        runIfVariableNonNull(this.air_switch_front_btn, new Function1Void(z) {
            public final /* synthetic */ boolean f$0;

            {
                this.f$0 = r1;
            }

            public final void invoke(Object obj) {
                LexusAirViewComponent.lambda$setFrontWinState$35(this.f$0, (ImageView) obj);
            }
        });
    }

    static /* synthetic */ void lambda$setFrontWinState$35(boolean z, ImageView imageView) {
        if (z) {
            imageView.setImageResource(R.drawable.lexus_air_btn_front_on);
        } else {
            imageView.setImageResource(R.drawable.lexus_air_btn_front_off);
        }
    }

    public void setRearWinState(boolean z) {
        this.isRearChecked = z;
        runIfVariableNonNull(this.air_switch_rear_btn, new Function1Void(z) {
            public final /* synthetic */ boolean f$0;

            {
                this.f$0 = r1;
            }

            public final void invoke(Object obj) {
                LexusAirViewComponent.lambda$setRearWinState$36(this.f$0, (ImageView) obj);
            }
        });
    }

    static /* synthetic */ void lambda$setRearWinState$36(boolean z, ImageView imageView) {
        if (z) {
            imageView.setImageResource(R.drawable.lexus_air_btn_front_on);
        } else {
            imageView.setImageResource(R.drawable.lexus_air_btn_front_off);
        }
    }

    public boolean isTouching() {
        return this.onClickListener.isTouching;
    }

    /* access modifiers changed from: private */
    public ButtonState buildButtonState() {
        return (ButtonState) JavaStandard.also(new ButtonState(), new Function1Void() {
            public final void invoke(Object obj) {
                LexusAirViewComponent.this.lambda$buildButtonState$37$LexusAirViewComponent((LexusAirViewComponent.ButtonState) obj);
            }
        });
    }

    public /* synthetic */ void lambda$buildButtonState$37$LexusAirViewComponent(ButtonState buttonState) {
        buttonState.isDualChecked = this.isDualChecked;
        buttonState.isAcChecked = this.isAcChecked;
        buttonState.isAutoChecked = true;
        buttonState.isCycleChecked = this.isCycleChecked;
        buttonState.isFrontChecked = this.isFrontChecked;
        buttonState.isRearChecked = this.isRearChecked;
    }

    private <T> void runIfVariableNonNull(T t, Function1Void<T> function1Void) {
        JavaStandard.runIfNonNull(t, function1Void);
    }

    public static class ButtonState {
        public boolean isAcChecked;
        public boolean isAutoChecked;
        public boolean isCycleChecked;
        public boolean isDualChecked;
        public boolean isFrontChecked;
        public boolean isRearChecked;

        public String toString() {
            return "ButtonState{isDualChecked=" + this.isDualChecked + ", isAcChecked=" + this.isAcChecked + ", isAutoChecked=" + this.isAutoChecked + ", isCycleChecked=" + this.isCycleChecked + ", isFrontChecked=" + this.isFrontChecked + ", isRearChecked=" + this.isRearChecked + '}';
        }
    }
}
