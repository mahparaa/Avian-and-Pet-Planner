package com.example.mahpara.myapp;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private FirebaseAuth firebaseAuth;
    //progress dialog
    private ProgressDialog progressDialog;

    EditText email;
    EditText password;
    TextView textview1;
    Button button1;
    private static final String[] SMS_PERMISSIONS =
            {
            android.Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.INTERNET,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions();
        //getting firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        //initializing views
        email = (EditText) findViewById(R.id.emaillogin);
        password = (EditText) findViewById(R.id.passwordlogin);
        button1 = (Button) findViewById(R.id.buttonlogin);
        textview1  = (TextView) findViewById(R.id.registerview1);


        progressDialog = new ProgressDialog(this);

        //attaching click listener
        button1.setOnClickListener(this);
        textview1.setOnClickListener(this);
    }

    //Request permissions
    private void requestPermissions()
    {
        List<String> missingPermissions;
        String methodText;

        missingPermissions = getMissingPermissions(SMS_PERMISSIONS);
        if (missingPermissions.isEmpty())
        {

        }
        else
        {
            if (needPermissionsRationale(missingPermissions))
            {
                Toast.makeText(this, "This application needs your permission", Toast.LENGTH_LONG)
                        .show();
            }
            ActivityCompat.requestPermissions(this,
                    missingPermissions.toArray(new String[missingPermissions.size()]),
                    0);
        }
    }

    //method for user login
    private void userLogin()
    {
        final String emaillog = email.getText().toString().trim();
        String passwordlog  = password.getText().toString().trim();


        //checking if email and passwords are empty
        if((TextUtils.isEmpty(emaillog) ||(!isValidEmail(emaillog) )))
        {
            Toast.makeText(this,"Please valid  email",Toast.LENGTH_LONG).show();
            email.setError("Please enter valid email");
            return;
        }

        if((TextUtils.isEmpty(passwordlog)))
        {
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            password.setError("Please enter password");
            return;
        }
        if(passwordlog.length()>25)
        {
            Toast.makeText(this,"Your Password is Too Long",Toast.LENGTH_LONG).show();
            password.setError("Your Password is Too Long");
            return;
        }
        if(passwordlog.length()<6)
        {
            Toast.makeText(this,"Must Contain 6 characters",Toast.LENGTH_LONG).show();
            password.setError("Password must contain 6 characters");
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        //logging in the user
        firebaseAuth.signInWithEmailAndPassword(emaillog, passwordlog)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        //if the task is successfull
                        if(task.isSuccessful()){
                            //start the profile activity
                            SharedPreferences.Editor editor = getSharedPreferences("UserData", MODE_PRIVATE).edit();
                            editor.putString("UserEmail", emaillog);
                            editor.apply();


                            ConfimationDialog();


                        }
                        else
                        {
                            //display some message here
                            Toast.makeText(MainActivity.this,"Invalid Email or Password",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }


    private void ConfimationDialog() {
        AlertDialog alertDialog=new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Login");
        alertDialog.setMessage("Do you want to save Email and Password?");
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        alertDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        SharedPreferences.Editor editor = getSharedPreferences("UserData", MODE_PRIVATE).edit();
                        editor.putString("Login", "Yes");
                        editor.apply();
                        Intent intent=new Intent(MainActivity.this,HomeAcivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
        alertDialog.setButton(android.app.AlertDialog.BUTTON_NEGATIVE, "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent=new Intent(MainActivity.this, HomeAcivity.class);
                        startActivity(intent);
                        finish();
                    }

                });
        alertDialog.show();
    }

    @Override
    public void onClick(View view)
    {
        if(view == button1){
            userLogin();
            //finish();
        }

        if(view == textview1)
        {
            startActivity(new Intent(MainActivity.this, MainActivity2.class));
            finish();
        }
    }

    private boolean needPermissionsRationale(List<String> permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        // Proceed with verification after requesting permissions.
        // If the verification SDK fails to intercept the code automatically due to missing permissions,
        // the VerificationListener.onVerificationFailed(1) method will be executed with an instance of
        // CodeInterceptionException. In this case it is still possible to proceed with verification
        // by asking the user to enter the code manually.
        // createVerification();

    }

    private List<String> getMissingPermissions(String[] requiredPermissions)
    {
        List<String> missingPermissions = new ArrayList<>();
        for (String permission : requiredPermissions) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), permission)
                    != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(permission);
            }
        }
        return missingPermissions;
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

}



