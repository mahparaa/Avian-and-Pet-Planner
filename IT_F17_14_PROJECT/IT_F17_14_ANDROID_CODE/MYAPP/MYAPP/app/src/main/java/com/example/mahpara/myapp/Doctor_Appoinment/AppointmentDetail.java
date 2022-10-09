package com.example.mahpara.myapp.Doctor_Appoinment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mahpara.myapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by haseeb on 4/28/2018.
 */

public class AppointmentDetail extends AppCompatActivity implements View.OnClickListener {
    Button timebtn,datebtn,submit;
    EditText des;
    TextView date,time;
    int mYear,mMonth,mDay,mHour,mMinute;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.appointment_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        databaseReference= FirebaseDatabase.getInstance().getReference("App_Appointment");
        timebtn=(Button)findViewById(R.id.time_btn);
        datebtn=(Button)findViewById(R.id.date_btn);
        submit=(Button)findViewById(R.id.submit);
        des=(EditText) findViewById(R.id.des);
        date=(TextView) findViewById(R.id.date_txt);
        time=(TextView) findViewById(R.id.time_txt);
        date.setOnClickListener(this);
        time.setOnClickListener(this);
        timebtn.setOnClickListener(this);
        datebtn.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()== R.id.time_btn||v.getId()== R.id.time_txt)
        {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            time.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, true);
            timePickerDialog.show();

        }
        else if (v.getId()== R.id.date_btn||v.getId()== R.id.date_txt)
        {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();

        }
        else if(v.getId()== R.id.submit) {

            String sdate,stime,sdes;
            sdate=date.getText().toString();
            stime=time.getText().toString();
            sdes=des.getText().toString();
            if (TextUtils.isEmpty(sdate)) {
                date.setError("Select Date first");
            } if (TextUtils.isEmpty(stime)){
                time.setError("Select Time first");
            } if (TextUtils.isEmpty(sdes)) {
                des.setError("Add description first");
            }
            if(!TextUtils.isEmpty(sdes)&&!TextUtils.isEmpty(sdate)&&!TextUtils.isEmpty(stime))
            {
                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
                Intent intent=getIntent();
                String userEmail=intent.getStringExtra("userEmail");
                String docEmail=intent.getStringExtra("docEmail");
                String status=intent.getStringExtra("status");
                String contact=intent.getStringExtra("contact");
                String id = databaseReference.push().getKey();
                databaseReference.child(id).setValue(new AppointmentModel(userEmail,docEmail,contact,sdate,stime,sdes,status));
                mailCall(userEmail,status,docEmail,contact,time.getText().toString(),date.getText().toString(),des.getText().toString());
                finish();

            }
        }
    }

    private void mailCall(   String email,  String Status,  String docmail,String contactNo,String time,String date,String des) {
        mailprototype mail=emailClient.getApiClient().create(mailprototype.class);
        //contactNo insert
        Call<EmailSend> call=mail.emailSend(email,  "Appoint",  docmail,contactNo,time,date,des);
        call.enqueue(new Callback<EmailSend>() {
            @Override
            public void onResponse(Call<EmailSend> call, Response<EmailSend> response) {
                EmailSend emailSend=response.body();
                if(emailSend.getResponse().equalsIgnoreCase("Success")) {
                    Toast.makeText(AppointmentDetail.this, "Mail Sent to the doctor", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EmailSend> call, Throwable t) {
                Toast.makeText(AppointmentDetail.this,"Exception: "+t.toString(),Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
