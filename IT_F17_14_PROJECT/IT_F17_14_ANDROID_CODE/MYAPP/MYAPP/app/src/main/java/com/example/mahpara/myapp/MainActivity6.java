package com.example.mahpara.myapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity6 extends AppCompatActivity
{

    Spinner spinner1;
    Button ditpln,download;
    ImageView imageView;
    ProgressDialog mProgressDialog2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        spinner1 = (Spinner) findViewById(R.id.spinner1_diseasesdetail);
        imageView = (ImageView) findViewById(R.id.imageView_diseases);
        ditpln = (Button) findViewById(R.id.button2_diseasesdetail);
        download = (Button) findViewById(R.id.button2_download);
        ArrayAdapter arrayAdapter_1 = ArrayAdapter.createFromResource(this, R.array.specie, android.R.layout.simple_list_item_1);
        spinner1.setAdapter(arrayAdapter_1);


        ditpln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sp1 = spinner1.getSelectedItem().toString();

                if(!valid(sp1))
                {
                    Toast.makeText(MainActivity6.this, "Invalid data selection ..", Toast.LENGTH_LONG).show();

                }
                else {

                    Intent intent = new Intent(MainActivity6.this, MainActivity_Diseases_Load.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("specie", sp1);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }
    public boolean valid(String sp) {
        boolean value = true;
        if (sp.equals("") || (sp.equals("--select pet specie--"))) {
            Toast.makeText(MainActivity6.this, "Select The Data", Toast.LENGTH_LONG).show();
            return false;
        }
        return value;
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

