package com.mahpara.pet_profile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Main2Activity extends AppCompatActivity
{
    /*Mehod 2*/
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
    DatabaseReference databaseReference1=databaseReference.child("Person");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final TextView textView1=(TextView)findViewById(R.id.tv2_1);
        final TextView textView2=(TextView)findViewById(R.id.tv2_2);
        final TextView textView3=(TextView)findViewById(R.id.tv2_3);
        final TextView textView4=(TextView)findViewById(R.id.tv2_4);
        final TextView textView5=(TextView)findViewById(R.id.tv2_5);



        /*Method 1 */

        Firebase firebase=new Firebase("https://pet-registration.firebaseio.com/");
        HashMap<String,String> hashMap=new HashMap<String,String>();
        //String nam=hashMap.get("Name");

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        String na=bundle.getString("Name");
        textView1.setText("Pet-Name: "+na);

        String em=bundle.getString("Age");
        textView2.setText("Age: "+em);

        String ps=bundle.getString("Password");
        textView3.setText("Specie: "+ps);

        String ph=bundle.getString("Phone");
        textView4.setText("Favourit-Food: "+ph);

        String ad=bundle.getString("Address");
        textView5.setText("Timings-Gap: "+ad);

        Intent in=new Intent(Main2Activity.this,Main3Activity.class);
        startActivity(in);

        /*
        databaseReference1.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                String nam=dataSnapshot.getValue(String.class);
                textView1.setText(nam);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        */
    }

}
