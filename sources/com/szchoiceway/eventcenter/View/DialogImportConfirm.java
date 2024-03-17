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
import com.szchoiceway.eventcenter.EventService;
import com.szchoiceway.eventcenter.R;
import com.szchoiceway.eventcenter.SysProviderOpt;
import com.szchoiceway.eventcenter.XmlUtils;
import java.io.File;

public class DialogImportConfirm {
    private static final String TAG = "ImportConfirm";
    private static final String ksw_backup_path = "/mnt/privdata1/";
    private static String ksw_factory_xml_copy_filename = "zxw_factory_config.xml";
    private static String ksw_lcd_xml_copy_filename = "zxw_lcd.xml";
    private int START_TIMING = 100;
    private AlertDialog alertDialog;
    private Button btnNo;
    private Button btnYes;
    /* access modifiers changed from: private */
    public TextView importResult;
    private String ksw_factory_xml_m_OldPath;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 100:
                    Log.d(DialogImportConfirm.TAG, "time = " + DialogImportConfirm.this.time);
                    DialogImportConfirm.access$010(DialogImportConfirm.this);
                    TextView access$100 = DialogImportConfirm.this.restoreTime;
                    access$100.setText(BuildConfig.FLAVOR + DialogImportConfirm.this.time);
                    if (DialogImportConfirm.this.time == 0) {
                        DialogImportConfirm.this.importFactory();
                        return;
                    } else {
                        sendEmptyMessageDelayed(100, 1000);
                        return;
                    }
                case 101:
                    DialogImportConfirm.this.hideDialog();
                    return;
                case 102:
                    ((EventService) DialogImportConfirm.this.mContext).rebootService();
                    return;
                case 103:
                    DialogImportConfirm.this.importResult.setText(DialogImportConfirm.this.mContext.getResources().getString(R.string.lb_update_configuration));
                    DialogImportConfirm.this.mHandler.sendEmptyMessageDelayed(102, 3000);
                    return;
                case 104:
                    DialogImportConfirm.this.importResult.setText(DialogImportConfirm.this.mContext.getResources().getString(R.string.lb_update_configuration_failed));
                    DialogImportConfirm.this.mHandler.sendEmptyMessageDelayed(101, 3000);
                    return;
                default:
                    return;
            }
        }
    };
    /* access modifiers changed from: private */
    public TextView restoreTime;
    public boolean showDialog = false;
    /* access modifiers changed from: private */
    public int time = 10;
    private View viewImporting;
    private XmlUtils xmlUtils;

    static /* synthetic */ int access$010(DialogImportConfirm dialogImportConfirm) {
        int i = dialogImportConfirm.time;
        dialogImportConfirm.time = i - 1;
        return i;
    }

    public DialogImportConfirm(Context context, XmlUtils xmlUtils2, String str) {
        this.mContext = context;
        this.xmlUtils = xmlUtils2;
        this.ksw_factory_xml_m_OldPath = str;
    }

    public void showDialog() {
        Log.d(TAG, "showDialog");
        if (this.alertDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
            View inflate = View.inflate(this.mContext, R.layout.layout_import_kesaiwei, (ViewGroup) null);
            inflate.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            builder.setView(inflate);
            this.alertDialog = builder.create();
            View findViewById = inflate.findViewById(R.id.viewImporting);
            this.viewImporting = findViewById;
            if (findViewById != null) {
                findViewById.setVisibility(0);
            }
            TextView textView = (TextView) inflate.findViewById(R.id.importResult);
            this.importResult = textView;
            if (textView != null) {
                textView.setVisibility(8);
            }
            TextView textView2 = (TextView) inflate.findViewById(R.id.restoreTime);
            this.restoreTime = textView2;
            if (textView2 != null) {
                textView2.setText(BuildConfig.FLAVOR + this.time);
            }
            Button button = (Button) inflate.findViewById(R.id.btnRestoreYes);
            this.btnYes = button;
            if (button != null) {
                button.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        DialogImportConfirm.this.lambda$showDialog$0$DialogImportConfirm(view);
                    }
                });
            }
            Button button2 = (Button) inflate.findViewById(R.id.btnRestoreNo);
            this.btnNo = button2;
            if (button2 != null) {
                button2.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        DialogImportConfirm.this.lambda$showDialog$1$DialogImportConfirm(view);
                    }
                });
            }
            this.alertDialog.setCancelable(false);
            this.alertDialog.setCanceledOnTouchOutside(false);
            this.alertDialog.getWindow().setType(2003);
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

    public /* synthetic */ void lambda$showDialog$0$DialogImportConfirm(View view) {
        importFactory();
    }

    public /* synthetic */ void lambda$showDialog$1$DialogImportConfirm(View view) {
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
    public void importFactory() {
        Log.d(TAG, "import factory");
        SysProviderOpt.getInstance(this.mContext).updateRecord(SysProviderOpt.KSW_AUTO_IMPORT_FACTORY_SETTINGS, "0");
        this.viewImporting.setVisibility(8);
        this.importResult.setVisibility(0);
        this.importResult.setText(this.mContext.getResources().getString(R.string.lb_import_tip_importing));
        new Thread(new Runnable() {
            public final void run() {
                DialogImportConfirm.this.lambda$importFactory$2$DialogImportConfirm();
            }
        }).start();
    }

    public /* synthetic */ void lambda$importFactory$2$DialogImportConfirm() {
        this.xmlUtils.parseXml(this.ksw_factory_xml_m_OldPath);
        File file = new File(this.ksw_factory_xml_m_OldPath + ksw_factory_xml_copy_filename);
        if (file.exists() && file.length() > 0) {
            String absolutePath = file.getAbsolutePath();
            ((EventService) this.mContext).copyFile(absolutePath, ksw_backup_path + ksw_factory_xml_copy_filename);
        }
        File file2 = new File(this.ksw_factory_xml_m_OldPath + ksw_lcd_xml_copy_filename);
        if (file2.exists() && file2.length() > 0) {
            String absolutePath2 = file2.getAbsolutePath();
            ((EventService) this.mContext).copyFile(absolutePath2, ksw_backup_path + ksw_lcd_xml_copy_filename);
        }
        if (this.xmlUtils.getParseResult()) {
            this.mHandler.sendEmptyMessage(103);
        } else {
            this.mHandler.sendEmptyMessage(104);
        }
    }
}
