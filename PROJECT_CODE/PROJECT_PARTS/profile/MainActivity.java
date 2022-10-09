package com.mahpara.pet_profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.*;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText ed1=(EditText)findViewById( R.id.ed_1 );
        final EditText ed2=(EditText)findViewById( R.id.ed_2 );
        final EditText ed3=(EditText)findViewById( R.id.ed_3 );
        final EditText ed4=(EditText)findViewById( R.id.ed_4 );
        final EditText ed5=(EditText)findViewById( R.id.ed_5 );
        final Button btn=(Button)findViewById( R.id.btn1 );

        Firebase.setAndroidContext(this);


        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String name1=ed1.getText().toString().trim();
                String age1=ed2.getText().toString().trim();
                String specie1=ed3.getText().toString().trim();
                String favfood1=ed4.getText().toString().trim();
                String timings1=ed5.getText().toString().trim();

       /*METHOD*/
       /*
         Firebase firebase=new Firebase(Config.FIREBASE_URL);
         Person person =new Person(name1,email1,password1,phone1,address1);
         person.setName(name1);
         person.setEmail(email1);
         person.setPassword(password1);
         person.setPhone(phone1);
         person.setAddress(address1);
                firebase.child("Person").push().setValue(person);
       */


       /* METHOD 1 */
        Firebase firebase=new Firebase("https://pet-registration.firebaseio.com/");
        HashMap<String,String> hashMap=new HashMap<String,String>();
        hashMap.put("Name",name1);
        hashMap.put("Age",age1);
        hashMap.put("Specie",specie1);
        hashMap.put("Favourit-food",favfood1);
        hashMap.put("Timings",timings1);
        firebase.child("Pet-Profile").push().setValue(hashMap);


                /*Bundle */
                Toast.makeText(MainActivity.this,"Successfully",Toast.LENGTH_LONG).show();
                Bundle b=new Bundle();
                b.putString("Name",name1);
                b.putString("Age",age1);
                b.putString("Specie",specie1);
                b.putString("Favourit-Food",favfood1);
                b.putString("Timings-Gap",timings1);
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtras(b);
                startActivity(intent);




            }
        });
    }
}
