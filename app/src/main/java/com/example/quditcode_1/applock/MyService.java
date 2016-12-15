package com.example.quditcode_1.applock;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import static android.support.v4.app.ServiceCompat.START_STICKY;

/**
 * Created by QuditCode on 12/12/2016.
 */

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        new Thread(new Runnable() {
            @Override
            public void run() {
                ActivityManager am = (ActivityManager) getApplicationContext().getSystemService(ACTIVITY_SERVICE);
// The first in the list of RunningTasks is always the foreground task.
                ActivityManager.RunningTaskInfo foregroundTaskInfo = am.getRunningTasks(1).get(0);
                String foregroundTaskPackageName = foregroundTaskInfo.topActivity.getPackageName();
                PackageManager pm = getApplicationContext().getPackageManager();
                PackageInfo foregroundAppPackageInfo = null;
                try {
                    foregroundAppPackageInfo = pm.getPackageInfo(foregroundTaskPackageName, 0);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                String foregroundTaskAppName = foregroundAppPackageInfo.applicationInfo.loadLabel(pm).toString();
                Log.d("Current", "Hi m Current Activity" + foregroundTaskAppName);
//                Toast.makeText(getApplicationContext(), "CURRENT Activity ::" + foregroundTaskAppName, Toast.LENGTH_LONG).show();

//                Toast.makeText(getApplicationContext(), "Hi M Service Which Started by u... ", Toast.LENGTH_LONG).show();

            }
        }).start();
        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Hi M Your Started Service ", Toast.LENGTH_LONG).show();
    }
}