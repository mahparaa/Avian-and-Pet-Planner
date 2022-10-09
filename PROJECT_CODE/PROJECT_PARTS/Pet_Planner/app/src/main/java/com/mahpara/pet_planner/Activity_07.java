package com.mahpara.pet_planner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Activity_07 extends AppCompatActivity {

    Spinner spinn_1,spinn_2,spinn_3;
    Button btn_diseases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_07);

        spinn_1=(Spinner)findViewById(R.id.spinner5);
        spinn_2=(Spinner)findViewById(R.id.spinner6);
        spinn_3=(Spinner)findViewById(R.id.spinner7);

        ArrayAdapter arrayAdap_1=ArrayAdapter.createFromResource(this,R.array.specie,android.R.layout.simple_list_item_1);
        spinn_1.setAdapter(arrayAdap_1);

        ArrayAdapter arrayAdap_2=ArrayAdapter.createFromResource(this,R.array.breed,android.R.layout.simple_list_item_1);
        spinn_2.setAdapter(arrayAdap_2);

        ArrayAdapter arrayAdap_3=ArrayAdapter.createFromResource(this,R.array.age,android.R.layout.simple_list_item_1);
        spinn_3.setAdapter(arrayAdap_3);



        btn_diseases=(Button)findViewById(R.id.button7);
        btn_diseases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Activity_07.this,Activity_08.class);
                startActivity(intent1);
            }
        });
    }
}
