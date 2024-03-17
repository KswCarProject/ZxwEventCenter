package com.szchoiceway.eventcenter;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;

public class JobWakeUpService extends JobService {
    private static final String TAG = "JobWakeUpService";
    private int JobWakeId = 100;

    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        Log.d(TAG, "onStartCommand");
        JobInfo.Builder builder = new JobInfo.Builder(this.JobWakeId, new ComponentName(this, JobWakeUpService.class));
        builder.setPeriodic(3000);
        ((JobScheduler) getSystemService("jobscheduler")).schedule(builder.build());
        return 1;
    }

    public boolean onStartJob(JobParameters jobParameters) {
        boolean isServiceAlive = EventUtils.isServiceAlive(this, EventUtils.BT_MODE_SERVICE_NAME);
        Log.d(TAG, "onStartJob isBtServiceAlice = " + isServiceAlive);
        if (!isServiceAlive) {
            EventService.getInstance().bindBtService();
        }
        if (SysProviderOpt.getInstance(this).getRecordBoolean(SysProviderOpt.KSW_HAVE_ECAR, false)) {
            ComponentName componentName = new ComponentName("com.ecar.assistantnew", "com.ecar.assistantnew.service.BootService");
            Intent intent = new Intent();
            intent.setComponent(componentName);
            boolean isServiceAlive2 = EventUtils.isServiceAlive(this, "com.ecar.assistantnew.service.BootService");
            Log.d(TAG, "onStartJob isECarServiceAlice = " + isServiceAlive);
            if (!isServiceAlive2) {
                startService(intent);
            }
        }
        return false;
    }
}
