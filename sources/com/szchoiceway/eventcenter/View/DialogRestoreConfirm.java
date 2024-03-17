package com.szchoiceway.eventcenter.View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
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

public class DialogRestoreConfirm {
    private static final String TAG = "RestoreConfirm";
    private int START_TIMING = 100;
    private AlertDialog alertDialog;
    private Button btnNo;
    private Button btnYes;
    private Context mContext;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 100) {
                DialogRestoreConfirm.access$010(DialogRestoreConfirm.this);
                TextView access$100 = DialogRestoreConfirm.this.restoreTime;
                access$100.setText(BuildConfig.FLAVOR + DialogRestoreConfirm.this.time);
                if (DialogRestoreConfirm.this.time == 0) {
                    DialogRestoreConfirm.this.restore();
                    DialogRestoreConfirm.this.hideDialog();
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

    static /* synthetic */ int access$010(DialogRestoreConfirm dialogRestoreConfirm) {
        int i = dialogRestoreConfirm.time;
        dialogRestoreConfirm.time = i - 1;
        return i;
    }

    public DialogRestoreConfirm(Context context) {
        this.mContext = context;
    }

    public void showDialog() {
        Log.d(TAG, "showDialog");
        if (this.alertDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
            View inflate = View.inflate(this.mContext, R.layout.layout_restore_kesaiwei, (ViewGroup) null);
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
                        DialogRestoreConfirm.this.lambda$showDialog$0$DialogRestoreConfirm(view);
                    }
                });
            }
            Button button2 = (Button) inflate.findViewById(R.id.btnRestoreNo);
            this.btnNo = button2;
            if (button2 != null) {
                button2.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        DialogRestoreConfirm.this.lambda$showDialog$1$DialogRestoreConfirm(view);
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

    public /* synthetic */ void lambda$showDialog$0$DialogRestoreConfirm(View view) {
        Log.d(TAG, "onClick btnYes");
        restore();
        hideDialog();
    }

    public /* synthetic */ void lambda$showDialog$1$DialogRestoreConfirm(View view) {
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
    public void restore() {
        Intent intent = new Intent("android.intent.action.FACTORY_RESET");
        intent.setPackage("android");
        intent.addFlags(268435456);
        intent.putExtra("android.intent.extra.REASON", "doFactoryReset");
        intent.putExtra("android.intent.extra.WIPE_EXTERNAL_STORAGE", false);
        intent.putExtra("com.android.internal.intent.extra.WIPE_ESIMS", true);
        this.mContext.sendBroadcast(intent);
        Log.d(TAG, "restore factory");
    }
}
