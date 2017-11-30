package com.example.yudhisthira.quandoo;

import com.example.yudhisthira.quandoo.data.DBUtils;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.TaskParams;

import java.util.concurrent.TimeUnit;

/**
 * Created by yudhisthira
 */

public class BackgroundService extends GcmTaskService {
    private final static String TAG = "BackgroundService";

    private static int INTERVEL = 15;

    @Override
    public int onRunTask(TaskParams taskParams) {
        DBUtils.deleteTableList();
        return GcmNetworkManager.RESULT_SUCCESS;
    }

    public static PeriodicTask create() {
        return new PeriodicTask.Builder()
                .setPeriod(TimeUnit.MINUTES.toSeconds(INTERVEL))
                .setRequiredNetwork(PeriodicTask.NETWORK_STATE_ANY)
                .setTag(TAG)
                .setService(BackgroundService.class)
                .setPersisted(true)
                .setUpdateCurrent(true)
                .build();
    }
}
