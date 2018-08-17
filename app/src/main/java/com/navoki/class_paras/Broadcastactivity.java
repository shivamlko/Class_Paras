package com.navoki.class_paras;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class Broadcastactivity extends AppCompatActivity {
    private ImageView image;
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcastactivity);

        image = (ImageView) findViewById(R.id.image);


        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                Log.e("MSG","1111");
                if(intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
                    image.setImageResource(R.drawable.ic_battery_charging_80_black_24dp);
                } else if(intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)){
                    image.setImageResource(R.drawable.ic_battery_60_black_24dp);
                }
            }
        };

    }


    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter intentFilter=new IntentFilter(Intent.ACTION_POWER_CONNECTED);
        IntentFilter intentFilter2=new IntentFilter(Intent.ACTION_POWER_DISCONNECTED);

        registerReceiver(broadcastReceiver,intentFilter);
        registerReceiver(broadcastReceiver,intentFilter2);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }
}
