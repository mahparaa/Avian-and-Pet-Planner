package com.example.mahpara.myapp.Alarm_Notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;



public class mService extends Service {

    private PendingIntent pendingIntent;
    AlarmManager alarmManager;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Log.d("IntentDataCheck", "onStartCommand: "+intent.getLongExtra("data",1));
        Intent myIntent = new Intent(mService.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(mService.this, 0, myIntent, 0);
        alarmManager.set(AlarmManager.RTC, intent.getLongExtra("data",1)*1000, pendingIntent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
