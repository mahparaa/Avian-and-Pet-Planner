package com.mahpara.splash_screen;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by cell  spot on 8/13/2017.
 */

public class Splash_Screen extends MainActivity_01
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

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
                    Intent in=new Intent(Splash_Screen.this,MainActivity_01.class);
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
