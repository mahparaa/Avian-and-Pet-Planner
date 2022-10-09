package com.example.mahpara.myapp.Doctor_Appoinment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.mahpara.myapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TestActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_addd);
        textView=(TextView)findViewById(R.id.t1);
        init();
    }

    private void init() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Doctor");
        //new DoctorDb(databaseReference).addDoctor("wahab", "test@mail.com", "cardiologist", "0000", "//image");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    doctorData data = postSnapshot.getValue(doctorData.class);
                    //adding artist to the list

                    textView.append(data.getName().toString());
                   // Toast.makeText(TestActivity.this, "Name"+data.name.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
