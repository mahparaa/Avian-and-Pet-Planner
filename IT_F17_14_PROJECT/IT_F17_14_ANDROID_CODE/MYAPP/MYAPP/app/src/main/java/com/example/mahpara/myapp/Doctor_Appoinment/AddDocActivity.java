package com.example.mahpara.myapp.Doctor_Appoinment;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mahpara.myapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AddDocActivity extends AppCompatActivity {
ImageView img;
Button imgload,Submit;
EditText name,email,cnic,contact,specs,hospitalID;
    private Uri imageUri;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    public static final String STORAGE_PATH = "images/";
    String Name,Email,Cnic,Contact,Specs,HospitalID;
    int val=0;


    @Override
    protected void onCreate( Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_addd);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Initilized the View
        img=(ImageView)findViewById(R.id.insertImages);
        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.enteremail);
        cnic=(EditText)findViewById(R.id.cnic);
        hospitalID=(EditText)findViewById(R.id.hospitalId);
        contact=(EditText)findViewById(R.id.contact);
        specs=(EditText) findViewById(R.id.docSpecs);
        imgload=(Button) findViewById(R.id.browse_img);
        Submit=(Button) findViewById(R.id.uploadData);
        storageReference = FirebaseStorage.getInstance().getReference();


        // LOAD THE IMAGE
        imgload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                browseImages(v);
            }
        });

        // SUBMIT THE DATA
        Submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                // Function For Initialization
                initalization();
                // Function For Validation
                if (!validation())
                {
                    Toast.makeText(AddDocActivity.this, "Enter The Data First", Toast.LENGTH_LONG).show();
                }
                else
                {

                    if (Integer.valueOf(HospitalID) > 56789 && Integer.valueOf(HospitalID) < 56796)
                    {
                        uploadData(v);
                    }
                    else
                    {
                        hospitalID.setError("Invalid Hospital ID");
                    }
                }
            }
        });
    } // ONCREATE FUNCTION END


    ///////////////////////////FUNCTIONS/////////////////////

    // FUNCTION INITIALIZED VIEWS
    public void initalization()
    {
        Name = name.getText().toString();
        Email = email.getText().toString();
        Cnic = cnic.getText().toString();
        Contact = contact.getText().toString();
        Specs = specs.getText().toString();
        HospitalID = hospitalID.getText().toString();

    }

    public boolean validation()
    {
        boolean valid=true;

        if(TextUtils.isEmpty(Name) || (Name.length()<3))
        {
            name.setError("Enter User Name");
            return false;
        }
        if((TextUtils.isEmpty(Email)) ||(!isValidEmail(Email)) )
        {
            email.setError("Invalid Email Address");
            return false;
        }
        if(Cnic.isEmpty() || Cnic.length()<10 )
        {
            cnic.setError("Enter Valid CNIC");
            return false;
        }
        if(Cnic.length()>20)
        {
            cnic.setError("Enter Valid CNIC");
            return false;
        }
        if(Contact.isEmpty() || Contact.length()<10)
        {
            contact.setError("Invalid Contact No");
            return false;
        }
        if(Contact.length()>20)
        {
            contact.setError("Invalid Contact No");
            return false;
        }
        if(Specs.isEmpty() || Specs.length()<3)
        {
            specs.setError("Enter Specification");
            return false;
        }
        return valid;
    }


    // BACK BUTTON PREESED
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    // BROWSE THE IMAGE
    public void browseImages(View view){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Image"),0);
    }

    // CAMERA INTENT
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == RESULT_OK){
            imageUri = data.getData();

            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                img.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // GET IMAGE URL
    public String getActualImage(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return  mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    // UPLOAD DATA
    public void uploadData(final View view)
    {

            if(imageUri != null)
            {
                // insert data

                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Uploading");
                progressDialog.show();
                DatabaseReference databaseRef= FirebaseDatabase.getInstance().getReference("Doctor");
                final DoctorDb doctorDb=new DoctorDb(databaseRef);

                StorageReference reference = storageReference.child(STORAGE_PATH + System.currentTimeMillis() + "." + getActualImage(imageUri));

                reference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        // Person person = new Person(taskSnapshot.getDownloadUrl().toString(),NAME,PetNAME,EMAIL,SPECIE,BREED,AGE,TIMINGS,FAVOURITEFOOD);
                        //doctorDb.addDoctor("Ruuub","wahdbasw@","asdas","sdas",taskSnapshot.getDownloadUrl().toString());
                        doctorDb.addDoctor(Name,Email,Specs, Contact,taskSnapshot.getDownloadUrl().toString());
//                    String id = databaseReference.push().getKey();

//                    databaseReference.child("Register"+val).setValue(person);

                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Data uploaded",Toast.LENGTH_LONG).show();
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @SuppressWarnings("VisibleForTests")
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double totalProgress = (100*taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                                progressDialog.setMessage("Uploaded % " + (int)totalProgress);
                                Toast.makeText(AddDocActivity.this, "Doctor Added", Toast.LENGTH_SHORT).show();
                                name.setText("");
                                email.setText("");
                                specs.setText("");
                                contact.setText("");
                                cnic.setText("");
                                //img.setImageBitmap(null);
                            }
                        });
        }
        else
        {
            // show message
            Toast.makeText(getApplicationContext(),"Please Select Image",Toast.LENGTH_LONG).show();
        }

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

} // end of MAIN ACTIVITY

