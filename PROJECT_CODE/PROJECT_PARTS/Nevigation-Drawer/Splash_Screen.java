package com.mahpara.pet_lover_nevigation_fragments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        Thread th=new Thread()
        {
            public  void run()
            {
                try
                {
                    sleep(3000);
                }

                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                finally
                {
                    Intent in=new Intent(Splash_Screen.this,User_Register.class);
                    startActivity(in);
                }
            }
        };
        th.start();

    }
    @Override
    protected void onPause()
    {
        super.onPause();
        finish();
    }
}

