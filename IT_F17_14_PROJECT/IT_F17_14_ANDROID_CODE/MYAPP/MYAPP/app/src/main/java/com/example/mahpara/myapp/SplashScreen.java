package com.example.mahpara.myapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread th = new Thread() {
            public void run() {
                try
                {
                    sleep(4000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                } finally {

                    SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("UserData", Context.MODE_PRIVATE);
                    String loginCheck=sharedPreferences.getString("Login", "No");
                    if(loginCheck.equalsIgnoreCase("Yes"))
                    {
                        Intent intent = new Intent(SplashScreen.this, HomeAcivity.class);
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        };
        th.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}

