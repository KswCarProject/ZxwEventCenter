package com.szchoiceway.eventcenter.View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import com.szchoiceway.eventcenter.R;

public class DialogRepairMcu {
    private static final String TAG = "DialogRepairMcu";
    private AlertDialog alertDialog;
    private Context mContext;
    public boolean showDialog = false;

    public DialogRepairMcu(Context context) {
        this.mContext = context;
    }

    public void showDialog() {
        Log.d(TAG, "showDialog");
        if (this.alertDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
            View inflate = View.inflate(this.mContext, R.layout.layout_repair_mcu, (ViewGroup) null);
            inflate.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            builder.setView(inflate);
            this.alertDialog = builder.create();
            Button button = (Button) inflate.findViewById(R.id.btnRestoreYes);
            if (button != null) {
                button.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        DialogRepairMcu.this.lambda$showDialog$0$DialogRepairMcu(view);
                    }
                });
            }
            Button button2 = (Button) inflate.findViewById(R.id.btnRestoreNo);
            if (button2 != null) {
                button2.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        DialogRepairMcu.this.lambda$showDialog$1$DialogRepairMcu(view);
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
    }

    public /* synthetic */ void lambda$showDialog$0$DialogRepairMcu(View view) {
        Log.d(TAG, "onClick btnYes");
        this.mContext.startService(new Intent("com.szchoiceway.updatemcu.UpdateMcuService").setPackage("com.szchoiceway.updatemcu"));
        hideDialog();
    }

    public /* synthetic */ void lambda$showDialog$1$DialogRepairMcu(View view) {
        Log.d(TAG, "onClick btnNo");
        hideDialog();
    }

    public void hideDialog() {
        Log.d(TAG, "hideDialog");
        this.showDialog = false;
        this.alertDialog.dismiss();
    }
}
