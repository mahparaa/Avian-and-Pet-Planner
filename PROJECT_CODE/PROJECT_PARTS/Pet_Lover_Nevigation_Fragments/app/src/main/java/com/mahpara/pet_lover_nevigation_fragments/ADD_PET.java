package com.mahpara.pet_lover_nevigation_fragments;

/**
 * Created by cell  spot on 8/12/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * Created by Belal on 18/09/16.
 */


public class ADD_PET extends Fragment {

    private String TAG = ADD_PET.class.getName();

    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("ADD-PET");
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View view1 = inflater.inflate(R.layout.add_pet, container, false);

        final EditText ed1 = (EditText) view1.findViewById(R.id.ed_1);
        final EditText ed2 = (EditText) view1.findViewById(R.id.ed_2);
        final EditText ed3 = (EditText) view1.findViewById(R.id.ed_3);
        final EditText ed4 = (EditText) view1.findViewById(R.id.ed_4);
        final EditText ed5 = (EditText) view1.findViewById(R.id.ed_5);
        final Button btn = (Button) view1.findViewById(R.id.btn1);

        // Firebase.setAndroidContext();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference mDatabase;
// ...
                mDatabase = FirebaseDatabase.getInstance().getReference();
                String name1 = ed1.getText().toString().trim();
                String age1 = ed2.getText().toString().trim();
                String specie1 = ed3.getText().toString().trim();
                String favfood1 = ed4.getText().toString().trim();
                String timings1 = ed5.getText().toString().trim();

                Person person = new Person(name1, age1, specie1, favfood1, timings1);
                person.setName(name1);
                person.setAge(age1);
                person.setSpecie(specie1);
                person.setFavfood(favfood1);
                person.setTimgs(timings1);

                mDatabase.child("Person").setValue(person, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        Log.i(TAG, "onComplete: " + "");
                    }
                });





                /*User user = new User("Tes", "test");

                mDatabase.setValue(user, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        Log.i(TAG, "onComplete: ");
                    }
                });*/

                /*myRef.setValue("Hello, World!", new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        Log.i(TAG, "onComplete: test");
                    }
                });*/

               /* HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("Name", name1);
                hashMap.put("Age", age1);
                hashMap.put("Specie", specie1);
                hashMap.put("Favourit-food", favfood1);
                hashMap.put("Timings", timings1);
                mDatabase.child("Pet-Profile").push().setValue(hashMap);*/



                 /*Bundle */
                //  Toast.makeText(ADD_PET.this,"Successfully",Toast.LENGTH_LONG).show();
                Bundle b = new Bundle();
                b.putString("Name", name1);
                b.putString("Age", age1);
                b.putString("Specie", specie1);
                b.putString("Favourit-Food", favfood1);
                b.putString("Timings-Gap", timings1);

                PROFILE fragment = new PROFILE();
                fragment.setArguments(b);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.ct, fragment);
                ft.commit();


            }
        });


        return view1;

    }

}
