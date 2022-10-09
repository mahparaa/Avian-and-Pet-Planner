package com.example.mahpara.myapp;

        import android.content.SharedPreferences;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.app.ProgressDialog;
        import android.content.ContentResolver;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.net.Uri;
        import android.provider.MediaStore;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.Toolbar;
        import android.text.TextUtils;
        import android.view.View;
        import android.webkit.MimeTypeMap;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.Spinner;
        import android.widget.Toast;

        import com.example.mahpara.myapp.Doctor_Appoinment.AddDocActivity;
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

public class MainActivity_Add extends AppCompatActivity {


    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    public static final String STORAGE_PATH = "images/";
    public static final String DATABASE_PATH = "PetRegistration";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    // Declare variables
    ImageView imageView;
    private Spinner sp, bd, ag, tm;
    String NAME,PetNAME,EMAIL,SPECIE,AGE,BREED,TIMINGS,FAVOURITEFOOD;
    EditText ed1addprofile, ed2addprofile, ed3addprofile, ed8addprofile;
    Button uploaddatabtn;


    private Uri imageUri;
    int val = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__add);
        // Shared preferences
        editor = getSharedPreferences("UserData", MODE_PRIVATE).edit();
        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        val = sharedPreferences.getInt("Value", 0);
        // Firebase
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference(DATABASE_PATH);

        // initializing views
        imageView = (ImageView) findViewById(R.id.insertImages);

        ed1addprofile = (EditText) findViewById(R.id.username_adpet);
        ed2addprofile = (EditText) findViewById(R.id.petname_adpet);
        ed3addprofile = (EditText) findViewById(R.id.enteremail_adpet);
        sp = (Spinner) findViewById(R.id.spn1);
        bd = (Spinner) findViewById(R.id.spn2);
        ag = (Spinner) findViewById(R.id.spn3);
        tm = (Spinner) findViewById(R.id.spn4);
        ed8addprofile = (EditText) findViewById(R.id.favouritefood);
        uploaddatabtn=(Button)findViewById(R.id.uploaddata);

        // setting toolbar and action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Add Pet");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Binding data we use adapter
        ArrayAdapter arrayAdap_1 = ArrayAdapter.createFromResource(MainActivity_Add.this, R.array.specie, android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(arrayAdap_1);
        ArrayAdapter arrayAdap_2 = ArrayAdapter.createFromResource(MainActivity_Add.this, R.array.breed, android.R.layout.simple_spinner_dropdown_item);
        bd.setAdapter(arrayAdap_2);
        ArrayAdapter arrayAdap_3 = ArrayAdapter.createFromResource(MainActivity_Add.this, R.array.age, android.R.layout.simple_spinner_dropdown_item);
        ag.setAdapter(arrayAdap_3);
        ArrayAdapter arrayAdap_4 = ArrayAdapter.createFromResource(MainActivity_Add.this, R.array.foodtimings, android.R.layout.simple_spinner_dropdown_item);
        tm.setAdapter(arrayAdap_4);

        uploaddatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uploadData();
            }
        });


    } // OnCreate end


    /////////////////FUNCTIONS////////////////////////////

    // BACK BUTTON IS PRESSED
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    //BROWSE IMAGE
    public void browseImages(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), 0);
    }

    @Override
    /// CAMMERA INTENT
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // GET THE IMAGE URL
    public String getActualImage(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    // UPLOAD THE DATA
    public void uploadData()
    {
        // Function For Initialization
        initalization();
        // Function For Validation
        if(!validation())
        {
            Toast.makeText(MainActivity_Add.this,"Enter The Data First",Toast.LENGTH_LONG).show();
        }
        else
        {
            onsuccess_fuc();
        }
    }

    // FUNCTION INITIALIZED VIEWS
    public void initalization()
    {
        NAME = ed1addprofile.getText().toString().trim();
        PetNAME = ed2addprofile.getText().toString().trim();
        EMAIL = ed3addprofile.getText().toString().trim();
        SPECIE = sp.getSelectedItem().toString().trim();
        BREED = bd.getSelectedItem().toString().trim();
        AGE = ag.getSelectedItem().toString().trim();
        TIMINGS = tm.getSelectedItem().toString().trim();
        FAVOURITEFOOD = ed8addprofile.getText().toString().trim();
    }

    // FUNCTION FOR VALIDATION
    public boolean validation()
    {
        boolean valid=true;

        if((TextUtils.isEmpty(NAME)|| (NAME.length()<3)))
        {
            ed1addprofile.setError("Enter User Name");
            return false;
        }
        if((TextUtils.isEmpty(PetNAME) || (PetNAME.length()<3)))
        {
            ed2addprofile.setError("Enter Pet Name");
            return false;
        }
        if((TextUtils.isEmpty(EMAIL) ||(!isValidEmail(EMAIL))))
        {
            ed3addprofile.setError("Invalid Email Address");
            return false;
        }
        if(SPECIE.equals("--select pet specie--")||(BREED.equals("--select pet breed--") ||(AGE.equals("--select age--"))||(TIMINGS.equals("--select food timings--"))))
        {
            Toast.makeText(MainActivity_Add.this, "Select The Data First", Toast.LENGTH_LONG).show();
            return false;
        }
        if((TextUtils.isEmpty(FAVOURITEFOOD) || (FAVOURITEFOOD.length()<3)))
        {
            ed8addprofile.setError("Enter Pet Food");
            return false;
        }
      return valid;
    }

    // FUNCTION SUCCESS
    public void onsuccess_fuc()
    {
        if(imageUri!=null)
        {
            editor.putInt("Value", val++);
            editor.apply();
            // insert data
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            StorageReference reference = storageReference.child(STORAGE_PATH + System.currentTimeMillis() + "." + getActualImage(imageUri));

            reference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                    Person person = new Person(taskSnapshot.getDownloadUrl().toString(), NAME, PetNAME, EMAIL, SPECIE, BREED, AGE, TIMINGS, FAVOURITEFOOD);

                    String id = databaseReference.push().getKey();

                    databaseReference.child(id).setValue(person);

                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Data uploaded", Toast.LENGTH_LONG).show();
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity_Add.this, "Select the data first inner", Toast.LENGTH_LONG).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @SuppressWarnings("VisibleForTests")
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double totalProgress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage("Uploaded % " + (int) totalProgress);
                        }
                    });
        }
        else
        {
            Toast.makeText(MainActivity_Add.this,"Select Image",Toast.LENGTH_LONG).show();
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
} // END OF MAIN ACTIVITY