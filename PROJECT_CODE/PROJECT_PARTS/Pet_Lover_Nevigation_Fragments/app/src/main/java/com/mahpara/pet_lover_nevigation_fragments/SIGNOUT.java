package com.mahpara.pet_lover_nevigation_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SIGNOUT extends Fragment implements View.OnClickListener {

    //firebase auth object
    private FirebaseAuth firebaseAuth;
    //view objects
    private TextView textViewUserEmail;
    private Button buttonLogout;

    @Nullable

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = null;
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("SIGN_OUT");

        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View vi = inflater.inflate(R.layout.sign_out, container, false);


        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if (firebaseAuth.getCurrentUser() == null) {
            //closing this activity
            //finish();
            //starting login activity
            Intent intent=new Intent(getActivity(),User_Login_in.class);
            startActivity(intent);
        }

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //initializing views
        textViewUserEmail = (TextView) vi.findViewById(R.id.textViewUserEmail);
        buttonLogout = (Button) vi.findViewById(R.id.buttonLogout);

        //displaying logged in user name
        textViewUserEmail.setText( user.getEmail());

        //adding listener to button
        buttonLogout.setOnClickListener(this);
        return vi;
    }

    public void onClick(View view)
    {
        //if logout is pressed
        if (view == buttonLogout) {
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            //finish();
            //starting login activity
            startActivity(new Intent(getActivity(), User_Login_in.class));
        }
    }
}








