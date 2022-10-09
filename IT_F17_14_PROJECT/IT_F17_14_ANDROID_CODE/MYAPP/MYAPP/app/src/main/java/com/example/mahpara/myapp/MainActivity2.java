package com.example.mahpara.myapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class MainActivity2 extends AppCompatActivity implements View.OnClickListener
{

    //defining view objects
    private EditText editTextEmail;
    private EditText editTextPassword,phno;
    private Button buttonSignup;
    private DatabaseReference databaseReference;
    private TextView textViewSignin;

    private ProgressDialog progressDialog;


    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main2);
        databaseReference = FirebaseDatabase.getInstance().getReference("userdata");
        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        //if getCurrentUser does not returns null
       /* if(firebaseAuth.getCurrentUser() != null){
            //that means user is already logged in
            //so close this activity
            finish();

            //and open login activity
            startActivity(new Intent(this, MainActivity.class));
        }*/

        //initializing views
        editTextEmail = (EditText) findViewById(R.id.emailregister);
        editTextPassword = (EditText) findViewById(R.id.passwordregister);
        textViewSignin = (TextView) findViewById(R.id.loginview2);
        phno=(EditText)findViewById(R.id.phnno);
        buttonSignup = (Button) findViewById(R.id.buttonregister);

        progressDialog = new ProgressDialog(this);

        //attaching listener to button
        buttonSignup.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
    }

    private void registerUser(){

        //getting email and password from edit texts
        final String email = editTextEmail.getText().toString().trim();
        final String password  = editTextPassword.getText().toString().trim();
        final String ph=phno.getText().toString();


        //checking if email and passwords are empty
        if((TextUtils.isEmpty(email) ||(!isValidEmail(email) )))
        {
            Toast.makeText(this,"Please Enter Valid Email",Toast.LENGTH_LONG).show();
            editTextEmail.setError("Please Enter Valid Email");
            return;
        }

        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Please Enter Password",Toast.LENGTH_LONG).show();
            editTextPassword.setError("Please Enter Password");
            return;
        }
        if(password.length()<6)
        {
            Toast.makeText(this,"Your Password Must Contain 6 Characters",Toast.LENGTH_LONG).show();
            editTextPassword.setError("Your Password Must Contain 6 Characters");
            return;
        }
        if(password.length()>25)
        {
            Toast.makeText(this,"Your Password is Too Long",Toast.LENGTH_LONG).show();
            editTextPassword.setError("Your Password is Too Long");
            return;
        }
        if ((TextUtils.isEmpty(ph)))
        {
            Toast.makeText(this,"Please Enter Mobile Number",Toast.LENGTH_LONG).show();
            phno.setError("Please Enter Mobile Number");
            return;
        }

        if(ph.length()>12)
        {
            Toast.makeText(this,"Incorrect Mobile Number",Toast.LENGTH_LONG).show();
            phno.setError("Incorrect Mobile Number");

            return;
        }
        if(ph.length()<10)
        {
            Toast.makeText(this,"Incorrect Mobile Number",Toast.LENGTH_LONG).show();
            phno.setError("Incorrect Mobile Number");
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful())
                        {
                            RegisterModel registerModel=new RegisterModel(email,password,ph);
                            String id = databaseReference.push().getKey();

                            //CHILD FOR FIREBASE DATA REFERENCE

                            databaseReference.child(id).setValue(registerModel);

                                SharedPreferences.Editor editor = getSharedPreferences("UserData", MODE_PRIVATE).edit();
                                editor.putString("UserEmail", email);
                                editor.apply();
                                finish();
                                Intent inte = new Intent(MainActivity2.this, MainActivity.class);
                                startActivity(inte);

                        }
                        else
                        {
                            //display some message here
                            Toast.makeText(MainActivity2.this,"Invalid Email or Password",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }
    //validating email id
    private boolean isValidEmail(String email)
    {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }



    private void ConfimationDialog() {
        AlertDialog alertDialog=new AlertDialog.Builder(MainActivity2.this).create();
        alertDialog.setTitle("Login");
        alertDialog.setMessage("Do you want to Login");
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        alertDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        SharedPreferences.Editor editor = getSharedPreferences("User_Registration", MODE_PRIVATE).edit();
                        editor.putString("Login", "Yes");
                        editor.apply();
                        Intent intent=new Intent(MainActivity2.this,MainActivity.class);
                        startActivity(intent);


                    }
                });
        alertDialog.setButton(android.app.AlertDialog.BUTTON_NEGATIVE, "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent=new Intent(MainActivity2.this, MainActivity2.class);
                        startActivity(intent);
                    }

                });
        alertDialog.show();
    }

    @Override
    public void onClick(View view) {

        if(view == buttonSignup){
            registerUser();
        }

        if(view == textViewSignin){
            //open login activity when user taps on the already registered textview
            startActivity(new Intent(this, MainActivity.class));
        }

    }
}
