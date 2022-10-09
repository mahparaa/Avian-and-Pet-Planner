package com.mahpara.pet_lover_nevigation_fragments;

/**
 * Created by cell  spot on 8/12/2017.
 */


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

/**
 * Created by Belal on 18/09/16.
 */


public class PROFILE extends Fragment {


    @Nullable

    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        View view=null;

        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("PET PROFILE");
        //change R.layout.yourlayoutfilename for each of your fragments
        View vi= inflater.inflate(R.layout.profile, container, false);

        final TextView textView1=(TextView)vi.findViewById(R.id.tv2_1);
        final TextView textView2=(TextView)vi.findViewById(R.id.tv2_2);
        final TextView textView3=(TextView)vi.findViewById(R.id.tv2_3);
        final TextView textView4=(TextView)vi.findViewById(R.id.tv2_4);
        final TextView textView5=(TextView)vi.findViewById(R.id.tv2_5);

        DatabaseReference mDatabase;
// ...
        mDatabase = FirebaseDatabase.getInstance().getReference();

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Person post = dataSnapshot.getValue(Person.class);
                Log.i("", "onDataChange: ");
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(PROFILE.class.getCanonicalName(), "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        mDatabase.addListenerForSingleValueEvent(postListener);

        

        /*Method 1

       //Firebase firebase=new Firebase(Config.FIREBASE_URL);
       // HashMap<String,String> hashMap=new HashMap<String,String>();
       //String nam=hashMap.get("Name");

        Bundle bundle=getArguments();

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


         return vi;
        /*METHOD-2
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
        return vi;
    }

}
