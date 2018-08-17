package com.navoki.class_paras;

import android.app.Service;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyServices extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        int i = 0;
        for (i = 0; i <= 10000; i++) {

        }

        BatteryManager manager=(BatteryManager) getSystemService(BATTERY_SERVICE);

        int c=manager.getIntProperty(BatteryManager.BATTERY_PLUGGED_USB);

        Log.e("Battery",c+"");
        Intent intent1=new Intent("com.navoki.class_paras.BATTERY");
        intent1.putExtra("battery",c);
        sendBroadcast(intent1);

        return START_STICKY;

    }
}
