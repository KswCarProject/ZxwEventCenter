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
import android.widget.TextView;
import com.example.mylibrary.BuildConfig;
import com.szchoiceway.eventcenter.EventService;
import com.szchoiceway.eventcenter.R;

public class DialogReboot {
    private static final String TAG = "DialogReboot";
    private int START_TIMING = 100;
    private AlertDialog alertDialog;
    private Context mContext;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 100) {
                if (DialogReboot.this.time <= 0) {
                    DialogReboot.this.restore();
                    DialogReboot.this.hideDialog();
                    return;
                }
                DialogReboot.access$010(DialogReboot.this);
                TextView access$200 = DialogReboot.this.restoreTime;
                access$200.setText(BuildConfig.FLAVOR + DialogReboot.this.time);
                sendEmptyMessageDelayed(100, 1000);
            }
        }
    };
    /* access modifiers changed from: private */
    public TextView restoreTime;
    public boolean showDialog = false;
    /* access modifiers changed from: private */
    public int time = 5;
    private TextView tvTips;

    static /* synthetic */ int access$010(DialogReboot dialogReboot) {
        int i = dialogReboot.time;
        dialogReboot.time = i - 1;
        return i;
    }

    public DialogReboot(Context context) {
        this.mContext = context;
    }

    public void showDialog(int i) {
        Log.d(TAG, "showDialog");
        if (this.alertDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
            View inflate = View.inflate(this.mContext, R.layout.layout_reboot_kesaiwei, (ViewGroup) null);
            inflate.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            builder.setView(inflate);
            this.alertDialog = builder.create();
            this.restoreTime = (TextView) inflate.findViewById(R.id.restoreTime);
            this.tvTips = (TextView) inflate.findViewById(R.id.tvTips);
            TextView textView = this.restoreTime;
            if (textView != null) {
                textView.setText(BuildConfig.FLAVOR + this.time);
            }
            TextView textView2 = this.tvTips;
            if (textView2 != null) {
                if (i == 1) {
                    textView2.setText(this.mContext.getResources().getString(R.string.lb_update_screenType));
                } else if (i == 2) {
                    textView2.setText(this.mContext.getResources().getString(R.string.lb_update_configuration));
                } else if (i == 3) {
                    this.time = 10;
                    textView2.setText(this.mContext.getResources().getString(R.string.lb_update_factory_configuration_file_error));
                } else if (i == 4) {
                    this.time = 10;
                    textView2.setText(this.mContext.getResources().getString(R.string.lb_update_lcd_configuration_file_error));
                } else if (i == 5) {
                    this.time = 0;
                    textView2.setText(this.mContext.getResources().getString(R.string.lb_update_screenType));
                }
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

    public void hideDialog() {
        Log.d(TAG, "hideDialog");
        this.time = 5;
        this.showDialog = false;
        if (this.mHandler.hasMessages(100)) {
            this.mHandler.removeMessages(100);
        }
        this.alertDialog.dismiss();
    }

    /* access modifiers changed from: private */
    public void restore() {
        new Thread(new Runnable() {
            public final void run() {
                DialogReboot.this.lambda$restore$0$DialogReboot();
            }
        }).start();
        Log.d(TAG, "restore factory");
    }

    public /* synthetic */ void lambda$restore$0$DialogReboot() {
        try {
            Thread.sleep(2000);
            ((EventService) this.mContext).rebootService();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
