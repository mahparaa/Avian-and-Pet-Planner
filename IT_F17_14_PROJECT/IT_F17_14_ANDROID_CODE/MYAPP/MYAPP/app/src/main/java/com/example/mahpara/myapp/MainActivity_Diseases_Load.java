package com.example.mahpara.myapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity_Diseases_Load extends AppCompatActivity {

    ImageView imageView;
    ProgressDialog mProgressDialog;
    DatabaseReference databaseReference;
    Button  download;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__diseases__load);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imageView = (ImageView) findViewById(R.id.imageView_diseases);
        download = (Button) findViewById(R.id.button2_download);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String sp1 = bundle.getString("specie");

        /*
        if (sp1.equals(""))
        {
            Toast.makeText(MainActivity_Diseases_Load.this, "Select the Data", Toast.LENGTH_LONG).show();
        }*/
        if (sp1.equals("Dog"))
        {
            function_call("Dog");
        }
        if (sp1.equals("Cat"))
        {
            function_call("Cat");
        }
        if (sp1.equals("Fish"))
        {
            function_call("Fish");
        }
        if (sp1.equals("Hen/Chicken"))
        {
            function_call("Hen");
        }
        if (sp1.equals("Parrot"))
        {
            function_call("Parrot");
        }
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadImage();
                Toast.makeText(MainActivity_Diseases_Load.this, "Your Image is downloaded", Toast.LENGTH_SHORT).show();
            }
        });
    } // OnCreate Finish

    /////////////////////////FUNCTIONS/////////////////////////

    void ShowImage(String url)
    {
        mProgressDialog = new ProgressDialog(MainActivity_Diseases_Load.this);
        mProgressDialog.setMessage("Fetching Plan..");
        mProgressDialog.setIndeterminate(false);
        // Show progressdialog
        mProgressDialog.show();
    }
    String imageuri=new String();
    public void function_call(final String child1)
    {
        mProgressDialog = new ProgressDialog(MainActivity_Diseases_Load.this);
        mProgressDialog.setMessage("Fetching Details..");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.show();
        databaseReference= FirebaseDatabase.getInstance().getReference("Common_Diseases");
        databaseReference.addValueEventListener(new ValueEventListener()
        {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {

                imageuri=dataSnapshot.child(child1).getValue(String.class);

                if (!imageuri.equalsIgnoreCase(""))
                {

                    Picasso
                            .with(MainActivity_Diseases_Load.this)  // functions of picasso
                            .load(imageuri).fit()
                            .error(R.mipmap.ic_launcher)
                            .into(imageView, new com.squareup.picasso.Callback() {

                                @Override
                                public void onSuccess() {

                                    Toast.makeText(MainActivity_Diseases_Load.this, "Successfully displayed", Toast.LENGTH_LONG).show();
                                    mProgressDialog.dismiss();
                                }

                                @Override
                                public void onError() {
                                    Toast.makeText(MainActivity_Diseases_Load.this, "Failed to generate", Toast.LENGTH_LONG).show();
                                }
                            });
                }
                else
                {
                    mProgressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "No data Found", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                mProgressDialog.dismiss();
            }
        });

    }

    void DownloadImage()
    {
        if(imageView.getDrawable()!=null)
        {
            mProgressDialog.setMessage("Downloading..");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();

            FileOutputStream outStream = null;
            BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
            Bitmap bitmap = drawable.getBitmap();


            // Write to SD Card
            try {
                File sdCard = Environment.getExternalStorageDirectory();
                File dir = new File(sdCard.getAbsolutePath() + "/PetPlanner/Diseases");
                dir.mkdirs();

                String fileName = String.format("%d.jpg", System.currentTimeMillis());
                fileName = "IMG" + fileName;
                File outFile = new File(dir, fileName);

                outStream = new FileOutputStream(outFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
                outStream.flush();
                outStream.close();

                Log.d("ImageWRite", "onPictureTaken - wrote to " + outFile.getAbsolutePath());
                mProgressDialog.dismiss();

           /* refreshGallery(outFile);*/
            }
            catch (FileNotFoundException e)
            {
                Log.d("CatchTag", "DownloadImage: FNF");
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally {}
        }
        else
        {
            Toast.makeText(MainActivity_Diseases_Load.this, "Generate Diseases Details First ", Toast.LENGTH_LONG).show();
        }

    }
    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }
}

