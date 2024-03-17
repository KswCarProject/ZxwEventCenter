package com.szchoiceway.eventcenter.View;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import com.szchoiceway.eventcenter.EventService;
import com.szchoiceway.eventcenter.R;

public class DialogRebootWithoutTime {
    private static final String TAG = "DialogRebootWithoutTime";
    private AlertDialog alertDialog;
    private Context mContext;

    public DialogRebootWithoutTime(Context context) {
        this.mContext = context;
    }

    public void showDialog() {
        AlertDialog alertDialog2 = this.alertDialog;
        if (alertDialog2 != null) {
            alertDialog2.dismiss();
            this.alertDialog = null;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
        View inflate = View.inflate(this.mContext, R.layout.layout_reboot_without_time_kesaiwei, (ViewGroup) null);
        inflate.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        builder.setView(inflate);
        AlertDialog create = builder.create();
        this.alertDialog = create;
        create.setCancelable(true);
        Button button = (Button) inflate.findViewById(R.id.btnRebootNo);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    DialogRebootWithoutTime.this.lambda$showDialog$0$DialogRebootWithoutTime(view);
                }
            });
        }
        Button button2 = (Button) inflate.findViewById(R.id.btnRebootYes);
        if (button2 != null) {
            button2.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    DialogRebootWithoutTime.this.lambda$showDialog$1$DialogRebootWithoutTime(view);
                }
            });
        }
        Window window = this.alertDialog.getWindow();
        window.setType(2003);
        this.alertDialog.show();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = (int) this.mContext.getResources().getDimension(R.dimen.rebootDialog_height);
        attributes.width = (int) this.mContext.getResources().getDimension(R.dimen.rebootDialog_width);
        window.setAttributes(attributes);
    }

    public /* synthetic */ void lambda$showDialog$0$DialogRebootWithoutTime(View view) {
        this.alertDialog.dismiss();
    }

    public /* synthetic */ void lambda$showDialog$1$DialogRebootWithoutTime(View view) {
        restore();
    }

    private void restore() {
        ((EventService) this.mContext).rebootService();
    }
}
