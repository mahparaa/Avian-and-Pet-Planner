package com.example.mahpara.myapp.Doctor_Appoinment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.example.mahpara.myapp.R;
import com.example.mahpara.myapp.RegisterModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;




public class doctorView extends AppCompatActivity {
    TextView textView;
    RecyclerView recyclerView;
    String Usermail;
    private DatabaseReference databaseReference;
    List<doctorData> Docdata;
     String contactNo="";
    private ProgressDialog progressDialog;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_cards);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Doctors List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Docdata=new ArrayList<>();
        getUserContact();
        /*textView=(TextView)findViewById(R.id.t1);
        textView.setText("Doctors List");*/





    }
    public void getUserContact() {
        progressDialog = new ProgressDialog(doctorView.this);
        progressDialog.setMessage("Fetching Details..");
        progressDialog.setIndeterminate(false);
        // Show progressdialog
        progressDialog.show();
        SharedPreferences sharedPreferences=this.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        Usermail=sharedPreferences.getString("UserEmail",null);
        Log.d("logChecke",Usermail);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("userdata");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    RegisterModel registerModel = snap.getValue(RegisterModel.class);

                    if (registerModel.getEmail().equals(Usermail)) {
                        contactNo=registerModel.getContact().toString();
                        getdata();
                        progressDialog.dismiss();
                        Log.e("logCheck",contactNo);
                    }

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }

        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void getdata() {

        databaseReference = FirebaseDatabase.getInstance().getReference("Doctor");
        databaseReference.addValueEventListener(new ValueEventListener() {
            doctorData data;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren())
                {
                    data = postSnapshot.getValue(doctorData.class);
                    Docdata.add(data);
                }
                recyclerView=(RecyclerView)findViewById(R.id.recyler);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                doc_adapter adapter=new doc_adapter(getApplicationContext(),Docdata,contactNo);
                recyclerView.setAdapter(adapter);

                /*doc_adapter adapter=new doc_adapter(getApplicationContext(),Docdata);
                recyclerView.setAdapter(adapter);*/

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });
    }



}
