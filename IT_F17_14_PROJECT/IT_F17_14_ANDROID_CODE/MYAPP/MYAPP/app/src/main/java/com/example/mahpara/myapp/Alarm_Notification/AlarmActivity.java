package com.example.mahpara.myapp.Alarm_Notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.mahpara.myapp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AlarmActivity extends AppCompatActivity {

    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker alarmTimePicker;
    private static AlarmActivity inst;
    private TextView alarmTextView;
    private Button btn,setAlarm;
    private EditText petName;
    List<DataQue> dataQueList=new ArrayList<>();

    public static AlarmActivity instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        alarmTextView = (TextView) findViewById(R.id.alarmText);
        //btn=(Button)findViewById(R.id.StartService);
        setAlarm=(Button)findViewById(R.id.set);
        petName=(EditText)findViewById(R.id.PtName);
        //ToggleButton alarmToggle = (ToggleButton) findViewById(R.id.alarmToggle);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
                /*calendar.set(Calendar.DAY_OF_WEEK_IN_MONTH,alarmTimePicker.);*/
                String petname=petName.getText().toString();
                if(petname.isEmpty())
                {
                    petName.setError("Add Pet Name first");
                }
                else
                {
                    StaticDataValues.dataQueList.add(new DataQue(petname,calendar));
                    petName.setText("");
                    Intent intent=new Intent(AlarmActivity.this,AlarmCards.class);
                    startActivity(intent);
                }

            }
        });

    }

    public void onClicked(View view) {
     Intent intent=new Intent(AlarmActivity.this,AlarmCards.class);
     startActivity(intent);
    }

    private void stopRing() {
        AlarmReceiver instance=AlarmReceiver.instance();
        instance.mMediaPlayer.stop();
    }

    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText);
    }

}
