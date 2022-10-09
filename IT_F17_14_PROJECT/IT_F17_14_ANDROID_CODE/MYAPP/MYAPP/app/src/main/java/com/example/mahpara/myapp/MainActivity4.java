package com.example.mahpara.myapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;


public class MainActivity4 extends AppCompatActivity  {

    private static final String TAG = "";
    Spinner spinn1, spinn2, spinn3, spinn4;
    Button ditpln;
    String abc;
    Toolbar toolbar;
    //Button downloadimgbtn;
    ImageView imageView;
    DatabaseReference databaseReference;
    public Uri uri;
    ProgressDialog mProgressDialog;


    @Nullable
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        spinn1 = (Spinner) findViewById(R.id.spinner1_diet);
        spinn2 = (Spinner) findViewById(R.id.spinner2_diet);
        spinn3 = (Spinner) findViewById(R.id.spinner3_diet);
        spinn4 = (Spinner) findViewById(R.id.spinner4_diet);


        //imageView = (ImageView) findViewById(R.id.imageView_diet);


        ditpln = (Button) findViewById(R.id.buttondietplan_2);

        ArrayAdapter arrayAdap_1 = ArrayAdapter.createFromResource(MainActivity4.this, R.array.specie, android.R.layout.simple_spinner_dropdown_item);
        spinn1.setAdapter(arrayAdap_1);

        ArrayAdapter arrayAdap_2 = ArrayAdapter.createFromResource(MainActivity4.this, R.array.breed, android.R.layout.simple_spinner_dropdown_item);
        spinn2.setAdapter(arrayAdap_2);


        ArrayAdapter arrayAdap_3 = ArrayAdapter.createFromResource(MainActivity4.this, R.array.age, android.R.layout.simple_spinner_dropdown_item);
        spinn3.setAdapter(arrayAdap_3);

        ArrayAdapter arrayAdap_4 = ArrayAdapter.createFromResource(MainActivity4.this, R.array.plan, android.R.layout.simple_spinner_dropdown_item);
        spinn4.setAdapter(arrayAdap_4);


        ditpln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //downloadimgbtn.setVisibility(View.VISIBLE);
                String sp = spinn1.getSelectedItem().toString();
                String brd = spinn2.getSelectedItem().toString();
                String ag = spinn3.getSelectedItem().toString();
                String pln = spinn4.getSelectedItem().toString();


                if(!valid(sp,brd,ag,pln))
                {
                    Toast.makeText(MainActivity4.this, "Invalid data selection ..", Toast.LENGTH_LONG).show();

                }
                else
                {

                    Intent intent = new Intent(MainActivity4.this, MainActivity_Diet_Plan_Load.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("specie", sp);
                    bundle.putString("breed", brd);
                    bundle.putString("age", ag);
                    bundle.putString("pln", pln);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            }
        });
    }
    public boolean valid(String sp,String brd,String ag, String pln)
    {
        boolean value=true;
        if(sp.equals("") || (brd.equals("") || (ag.equals("") && (pln.equals("")))))
        {
            Toast.makeText(MainActivity4.this, "Select The Data", Toast.LENGTH_LONG).show();
            return false;
        }
        if(sp.equals("Cat")&&(brd.equals("Dog:Husky")||(brd.equals("Dog:German Shepered")||(brd.equals("Hen:Boiler/Non Boiler") ||(brd
                .equals("Parrot:(any breed)")||(brd.equals("Fish:(any breed)")))))))
        {
            Toast.makeText(MainActivity4.this, "Invalid data selection", Toast.LENGTH_LONG).show();
            return false;
        }
        if(sp.equals("Dog")&&(brd.equals("Cat:Siamese")||(brd.equals("Cat:American Long hair")||(brd.equals("Cat:Persian")||(brd.equals("Hen:Boiler/Non Boiler") ||(brd
                .equals("Parrot:(any breed)")||(brd.equals("Fish:(any breed)"))))))))
        {
            Toast.makeText(MainActivity4.this, "Invalid data selection", Toast.LENGTH_LONG).show();
            return false;
        }
        if(sp.equals("Hen/Chicken")&&(brd.equals("Dog:Husky")||(brd.equals("Dog:German Shepered")||(brd.equals("Cat:Siamese")||(brd.equals("Cat:American Long hair")||(brd.equals("Cat:Persian")||(brd
                .equals("Parrot:(any breed)")||(brd.equals("Fish:(any breed)")))))))))
        {
            Toast.makeText(MainActivity4.this, "Invalid data selection", Toast.LENGTH_LONG).show();
            return false;
        }
        if(sp.equals("Parrot")&&(brd.equals("Dog:Husky")||(brd.equals("Dog:German Shepered")||(brd.equals("Cat:Siamese")||(brd.equals("Cat:American Long hair")||(brd.equals("Cat:Persian")
                ||(brd.equals("Fish:(any breed)")||(brd.equals("Hen:Boiler/Non Boiler")))))))))
        {
            Toast.makeText(MainActivity4.this, "Invalid data selection", Toast.LENGTH_LONG).show();
            return false;
        }
        if(sp.equals("Fish")&&(brd.equals("Dog:Husky")||(brd.equals("Dog:German Shepered")||(brd.equals("Cat:Siamese")||(brd.equals("Cat:American Long hair")||(brd.equals("Cat:Persian")
                ||(brd.equals("Hen:Boiler/Non Boiler"))))))))
        {
            Toast.makeText(MainActivity4.this, "Invalid data selection", Toast.LENGTH_LONG).show();
            return false;
        }
        if(sp.equals("--select pet specie--")||(brd.equals("--select pet breed--") ||(ag.equals("--select age--"))||(pln.equals("--select plan--"))))
        {
            Toast.makeText(MainActivity4.this, "Invalid data selection", Toast.LENGTH_LONG).show();
            return false;
        }
        return value;
    }
    // BACK BUTTON PREESED
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}