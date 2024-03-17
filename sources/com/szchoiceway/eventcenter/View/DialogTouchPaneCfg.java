package com.szchoiceway.eventcenter.View;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.example.mylibrary.BuildConfig;
import com.szchoiceway.eventcenter.R;
import com.szchoiceway.eventcenter.TouchPaneCfg;

public class DialogTouchPaneCfg {
    private static final String TAG = "DialogTouchPaneCfg";
    private int START_TIMING = 100;
    private AlertDialog alertDialog;
    private Button btnNo;
    private Button btnYes;
    private Context mContext;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 100) {
                DialogTouchPaneCfg.access$010(DialogTouchPaneCfg.this);
                TextView access$100 = DialogTouchPaneCfg.this.restoreTime;
                access$100.setText(BuildConfig.FLAVOR + DialogTouchPaneCfg.this.time);
                if (DialogTouchPaneCfg.this.time == 0) {
                    DialogTouchPaneCfg.this.touchPane();
                    DialogTouchPaneCfg.this.hideDialog();
                    return;
                }
                sendEmptyMessageDelayed(100, 1000);
            }
        }
    };
    /* access modifiers changed from: private */
    public TextView restoreTime;
    public boolean showDialog = false;
    /* access modifiers changed from: private */
    public int time = 10;

    static /* synthetic */ int access$010(DialogTouchPaneCfg dialogTouchPaneCfg) {
        int i = dialogTouchPaneCfg.time;
        dialogTouchPaneCfg.time = i - 1;
        return i;
    }

    public DialogTouchPaneCfg(Context context) {
        this.mContext = context;
    }

    public void showDialog() {
        Log.d(TAG, "showDialog");
        if (this.alertDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
            View inflate = View.inflate(this.mContext, R.layout.layout_touch_kesaiwei, (ViewGroup) null);
            inflate.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            builder.setView(inflate);
            this.alertDialog = builder.create();
            TextView textView = (TextView) inflate.findViewById(R.id.restoreTime);
            this.restoreTime = textView;
            if (textView != null) {
                textView.setText(BuildConfig.FLAVOR + this.time);
            }
            Button button = (Button) inflate.findViewById(R.id.btnRestoreYes);
            this.btnYes = button;
            if (button != null) {
                button.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        DialogTouchPaneCfg.this.lambda$showDialog$0$DialogTouchPaneCfg(view);
                    }
                });
            }
            Button button2 = (Button) inflate.findViewById(R.id.btnRestoreNo);
            this.btnNo = button2;
            if (button2 != null) {
                button2.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        DialogTouchPaneCfg.this.lambda$showDialog$1$DialogTouchPaneCfg(view);
                    }
                });
            }
            this.alertDialog.setCancelable(false);
            this.alertDialog.setCanceledOnTouchOutside(false);
            this.alertDialog.getWindow().setType(2038);
        }
        this.alertDialog.show();
        this.showDialog = true;
        Window window = this.alertDialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = (int) this.mContext.getResources().getDimension(R.dimen.rebootDialog_height);
        attributes.width = (int) this.mContext.getResources().getDimension(R.dimen.rebootDialog_width);
        window.setAttributes(attributes);
        this.mHandler.sendEmptyMessageDelayed(100, 1000);
    }

    public /* synthetic */ void lambda$showDialog$0$DialogTouchPaneCfg(View view) {
        Log.d(TAG, "onClick btnYes");
        touchPane();
        hideDialog();
    }

    public /* synthetic */ void lambda$showDialog$1$DialogTouchPaneCfg(View view) {
        Log.d(TAG, "onClick btnNo");
        hideDialog();
    }

    public void hideDialog() {
        Log.d(TAG, "hideDialog");
        this.time = 10;
        this.showDialog = false;
        if (this.mHandler.hasMessages(100)) {
            this.mHandler.removeMessages(100);
        }
        this.alertDialog.dismiss();
    }

    /* access modifiers changed from: private */
    public void touchPane() {
        new Thread(new Runnable() {
            public final void run() {
                DialogTouchPaneCfg.this.lambda$touchPane$2$DialogTouchPaneCfg();
            }
        }).start();
    }

    public /* synthetic */ void lambda$touchPane$2$DialogTouchPaneCfg() {
        new TouchPaneCfg(this.mContext).loadTouchCfgFile(this.mContext);
        Log.d(TAG, "touchPane");
    }
}
