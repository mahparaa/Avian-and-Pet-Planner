package com.example.mahpara.myapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mahpara.myapp.Alarm_Notification.AlarmActivity;
import com.example.mahpara.myapp.Doctor_Appoinment.AddDocActivity;
import com.example.mahpara.myapp.Doctor_Appoinment.doctorView;

public class HomeAcivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences getshared;
    String admincheck;
    Button btn3,btn4,btn5,btn6,btn7;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getshared=getSharedPreferences("UserData",MODE_PRIVATE);
        admincheck=getshared.getString("UserEmail",null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //activity_home_acivity
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        btn3=(Button)findViewById(R.id.button3);
        btn4=(Button)findViewById(R.id.button4);
        btn5=(Button)findViewById(R.id.button5);
        btn6=(Button)findViewById(R.id.button6);
        btn7=(Button)findViewById(R.id.button7);

        // When Button is Clicked
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(HomeAcivity.this,MainActivity4.class);
                startActivity(intent1);

            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent2=new Intent(HomeAcivity.this,MainActivity6.class);
                startActivity(intent2);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent3=new Intent(HomeAcivity.this,AlarmActivity.class);
                startActivity(intent3);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent4=new Intent(HomeAcivity.this, doctorView.class);
                startActivity(intent4);



            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent5=new Intent(HomeAcivity.this,MainActivity_Add.class);
                startActivity(intent5);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_diet) {
            Intent intent1=new Intent(HomeAcivity.this,MainActivity4.class);
            startActivity(intent1);
            // Handle the camera action
        } else if (id == R.id.nav_disease) {
            Intent  intent2=new Intent(HomeAcivity.this,MainActivity6.class);
            startActivity(intent2);

        } else if (id == R.id.nav_notif) {
            Intent  intent3=new Intent(HomeAcivity.this,AlarmActivity.class);
            startActivity(intent3);

        } else if (id == R.id.nav_appoint) {
            Intent  intent4=new Intent(HomeAcivity.this, doctorView.class);
            startActivity(intent4);

        } else if (id == R.id.nav_pet) {
            Intent  intent5=new Intent(HomeAcivity.this,MainActivity_Add.class);
            startActivity(intent5);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tab,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if(id==R.id.logout)
        {
            SharedPreferences.Editor editor = getSharedPreferences("UserData", MODE_PRIVATE).edit();
            editor.putString("Login", "No");
            editor.apply();
            Intent intent=new Intent(HomeAcivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        if(id==R.id.AddDoctor)
        {
            if (admincheck.equalsIgnoreCase("admin@gmail.com"))
            {
                Intent intent=new Intent(HomeAcivity.this,AddDocActivity.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(this, "Admin Permissions required", Toast.LENGTH_SHORT).show();
            }


        }
        return super.onOptionsItemSelected(item);
    }

    public void viewAllData(View view){
        Intent intent = new Intent(HomeAcivity.this, ViewDataActivity.class);
        startActivity(intent);
    }
}
