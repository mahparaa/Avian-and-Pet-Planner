package com.example.mahpara.myapp.Alarm_Notification;



import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;

import java.io.IOException;

public class AlarmReceiver extends WakefulBroadcastReceiver {
    Uri alarmUri;
    private static AlarmReceiver instance;
    Ringtone ringtone;
    MediaPlayer mMediaPlayer=new MediaPlayer();

    public static AlarmReceiver instance() {
        return instance;
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        //this will update the UI with message
        AlarmActivity inst = AlarmActivity.instance();
        //inst.setAlarmText("Alarm! Wake up! Wake up!");
        instance=this;

        Uri alert =  RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        //mMediaPlayer = new MediaPlayer();
        try {
            mMediaPlayer.setDataSource(context, alert);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (audioManager.getStreamVolume(AudioManager.STREAM_RING) != 0) {*/
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);
            mMediaPlayer.setLooping(true);
        try {
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }



      /*  alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        ringtone= RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();*/

        //this will sound the alarm tone
        //this will sound the alarm once, if you wish to
        //raise alarm in loop continuously then use MediaPlayer and setLooping(true)

        mMediaPlayer.start();
        //this will send a notification message
        ComponentName comp = new ComponentName(context.getPackageName(),
                AlarmService.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }
}