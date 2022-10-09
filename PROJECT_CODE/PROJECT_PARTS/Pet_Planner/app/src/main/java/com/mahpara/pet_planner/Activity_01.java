package com.mahpara.pet_planner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Activity_01 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_01);
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
                    Intent in=new Intent(Activity_01.this,Activity_02.class);
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


