package com.mahpara.pet_planner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Activity_05 extends AppCompatActivity {

    Spinner spinner_1,spinner_2,spinner_3,spinner_4;
    Button btn_diet_plan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_05);

        // Binding layout widges with code
        spinner_1=(Spinner)findViewById(R.id.spinner);
        spinner_2=(Spinner)findViewById(R.id.spinner2);
        spinner_3=(Spinner)findViewById(R.id.spinner3);
        spinner_4=(Spinner)findViewById(R.id.spinner4);
        // Binding data with spinner through Adapter
        ArrayAdapter arrayAdapter_1=ArrayAdapter.createFromResource(this,R.array.specie,android.R.layout.simple_list_item_1);
        spinner_1.setAdapter(arrayAdapter_1);

        ArrayAdapter arrayAdapter_2=ArrayAdapter.createFromResource(this,R.array.breed,android.R.layout.simple_list_item_1);
        spinner_2.setAdapter(arrayAdapter_2);

        ArrayAdapter arrayAdapter_3=ArrayAdapter.createFromResource(this,R.array.age,android.R.layout.simple_list_item_1);
        spinner_3.setAdapter(arrayAdapter_3);

        ArrayAdapter arrayAdapter_4=ArrayAdapter.createFromResource(this,R.array.plan,android.R.layout.simple_list_item_1);
        spinner_4.setAdapter(arrayAdapter_4);


        btn_diet_plan=(Button)findViewById(R.id.button);
        btn_diet_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Activity_05.this,Activity_06.class);
                startActivity(intent1);
            }
        });

    }
}
