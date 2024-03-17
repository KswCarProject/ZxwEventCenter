package com.szchoiceway.eventcenter.util;

import android.app.ActivityOptions;
import android.app.ActivityTaskManager;
import android.content.Context;
import android.os.Build;
import android.os.Message;
import android.os.RemoteException;
import com.szchoiceway.eventcenter.EventService;
import com.szchoiceway.eventcenter.EventUtils;
import java.util.List;

public class CheckUtil {
    private EventService mService;
    private Thread thread = null;

    public CheckUtil(Context context) {
        this.mService = (EventService) context;
    }

    public void startThread() {
        if (this.thread == null) {
            Thread thread2 = new Thread(new Runnable() {
                public final void run() {
                    CheckUtil.this.lambda$startThread$0$CheckUtil();
                }
            });
            this.thread = thread2;
            thread2.start();
        }
    }

    public /* synthetic */ void lambda$startThread$0$CheckUtil() {
        while (true) {
            try {
                if (this.mService.m_iUiIndex == 5 && Build.VERSION.SDK_INT == 31) {
                    boolean isSplitScreenA12 = isSplitScreenA12();
                    Message message = new Message();
                    message.what = EventUtils.KSW_HANDLER_CHECK_SPLIT_SCREEN_A12;
                    message.obj = Boolean.valueOf(isSplitScreenA12);
                    this.mService.mEventHandler.sendMessage(message);
                }
                Thread.sleep(400);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isSplitScreenA12() {
        try {
            List allRootTaskInfos = ActivityTaskManager.getService().getAllRootTaskInfos();
            ActivityTaskManager.RootTaskInfo rootTaskInfo = null;
            ActivityTaskManager.RootTaskInfo rootTaskInfo2 = null;
            for (int i = 0; i < allRootTaskInfos.size(); i++) {
                ActivityTaskManager.RootTaskInfo rootTaskInfo3 = (ActivityTaskManager.RootTaskInfo) allRootTaskInfos.get(i);
                if (rootTaskInfo3.visible) {
                    ActivityOptions.makeBasic().getLaunchWindowingMode();
                    if (rootTaskInfo3.getConfiguration().windowConfiguration.getWindowingMode() == 3) {
                        rootTaskInfo = rootTaskInfo3;
                    } else if (rootTaskInfo3.getConfiguration().windowConfiguration.getWindowingMode() == 4) {
                        rootTaskInfo2 = rootTaskInfo3;
                    }
                }
            }
            if (rootTaskInfo == null || rootTaskInfo2 == null) {
                return false;
            }
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
