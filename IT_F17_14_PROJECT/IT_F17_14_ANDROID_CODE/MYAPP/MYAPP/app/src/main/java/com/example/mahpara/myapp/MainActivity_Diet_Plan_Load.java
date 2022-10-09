package com.example.mahpara.myapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
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

public class MainActivity_Diet_Plan_Load extends AppCompatActivity
{

    Button downloadimgbtn;
    ImageView imageView;

    public Uri uri;
    ProgressDialog mProgressDialog;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__diet__plan__load);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String sp=bundle.getString("specie");
        String brd=bundle.getString("breed");
        String ag=bundle.getString("age");
        String pln=bundle.getString("pln");

        imageView=(ImageView)findViewById(R.id.imageView_diet);
        downloadimgbtn=(Button)findViewById(R.id.downloadimage);


/////////////////////////////////////////////////////////////////
                            //INSECPTION
/////////////////////////////////////////////////////////////////
        /*
        if(sp.equals("") || (brd.equals("") || (ag.equals("") && (pln.equals("")))))
        {
            Toast.makeText(MainActivity_Diet_Plan_Load.this, "Select The Data", Toast.LENGTH_LONG).show();
        }
        if(sp.equals("Cat")&&(brd.equals("Dog:Husky")||(brd.equals("Dog:German Shepered")||(brd.equals("Hen:Boiler/Non Boiler") ||(brd
                .equals("Parrot:(any breed)")||(brd.equals("Fish:(any breed)")))))))
        {
            Toast.makeText(MainActivity_Diet_Plan_Load.this, "Invalid data selection", Toast.LENGTH_LONG).show();
        }
        if(sp.equals("Dog")&&(brd.equals("Cat:Siamese")||(brd.equals("Cat:American Long hair")||(brd.equals("Cat:Persian")||(brd.equals("Hen:Boiler/Non Boiler") ||(brd
                .equals("Parrot:(any breed)")||(brd.equals("Fish:(any breed)"))))))))
        {
            Toast.makeText(MainActivity_Diet_Plan_Load.this, "Invalid data selection", Toast.LENGTH_LONG).show();
        }
        if(sp.equals("Hen/Chicken")&&(brd.equals("Dog:Husky")||(brd.equals("Dog:German Shepered")||(brd.equals("Cat:Siamese")||(brd.equals("Cat:American Long hair")||(brd.equals("Cat:Persian")||(brd
                .equals("Parrot:(any breed)")||(brd.equals("Fish:(any breed)")))))))))
        {
            Toast.makeText(MainActivity_Diet_Plan_Load.this, "Invalid data selection", Toast.LENGTH_LONG).show();
        }
        if(sp.equals("Parrot")&&(brd.equals("Dog:Husky")||(brd.equals("Dog:German Shepered")||(brd.equals("Cat:Siamese")||(brd.equals("Cat:American Long hair")||(brd.equals("Cat:Persian")
                ||(brd.equals("Fish:(any breed)")||(brd.equals("Hen:Boiler/Non Boiler")))))))))
        {
            Toast.makeText(MainActivity_Diet_Plan_Load.this, "Invalid data selection", Toast.LENGTH_LONG).show();
        }
        if(sp.equals("Fish")&&(brd.equals("Dog:Husky")||(brd.equals("Dog:German Shepered")||(brd.equals("Cat:Siamese")||(brd.equals("Cat:American Long hair")||(brd.equals("Cat:Persian")
                ||(brd.equals("Hen:Boiler/Non Boiler"))))))))
        {
            Toast.makeText(MainActivity_Diet_Plan_Load.this, "Invalid data selection", Toast.LENGTH_LONG).show();
        }
        */
////////////////////////////////////////////////////////////////////
                                   //FISH//
////////////////////////////////////////////////////////////////////
        //get data from DataBase

        if(sp.equals("Fish") && (brd.equals("Fish:(any breed)") && (ag.equals("Small") && (pln.equals("Daily Plan")))))
        {
            function_call("Fish","Any_Breed","Small_Daily");
        }
        if (sp.equals("Fish") && (brd.equals("Fish:(any breed)") && (ag.equals("Small") && (pln.equals("Weekly Plan")))))
        {
           function_call("Fish","Any_Breed","Small_Weekly");
        }
        if(sp.equals("Fish") && (brd.equals("Fish:(any breed)") && (ag.equals("Adult") && (pln.equals("Daily Plan")))))
        {
            function_call("Fish","Any_Breed","Adult_Daily");
        }
        if(sp.equals("Fish") && (brd.equals("Fish:(any breed)") && (ag.equals("Adult") && (pln.equals("Weekly Plan")))))
        {
            function_call("Fish","Any_Breed","Adult_Weekly");
        }
        if(sp.equals("Fish") && (brd.equals("Fish:(any breed)") && (ag.equals("Senior Adult") && (pln.equals("Daily Plan")))))
        {
            function_call("Fish","Any_Breed","Senior_Adult_Daily");
        }
        if(sp.equals("Fish") && (brd.equals("Fish:(any breed)") && (ag.equals("Senior Adult") && (pln.equals("Weekly Plan")))))
        {
            function_call("Fish","Any_Breed","Senior_Adult_Weekly");
        }


//////////////////////////////////////////////////////////////////////////////////
                               // DOG-HUSKY //
//////////////////////////////////////////////////////////////////////////////////
        //get data from DataBase

        if (sp.equals("Dog") && (brd.equals("Dog:Husky") && (ag.equals("Small") && (pln.equals("Daily Plan")))))
        {
            function_call("Dog","Husky","Small_Daily");
        }
        if (sp.equals("Dog") && (brd.equals("Dog:Husky") && (ag.equals("Small") && (pln.equals("Weekly Plan")))))
        {
            function_call("Dog","Husky","Small_Weekly");
        }
        if (sp.equals("Dog") && (brd.equals("Dog:Husky") && (ag.equals("Adult") && (pln.equals("Daily Plan")))))
        {
            function_call("Dog","Husky","Adult_Daily");
        }
        if (sp.equals("Dog") && (brd.equals("Dog:Husky") && (ag.equals("Adult") && (pln.equals("Weekly Plan")))))
        {
            function_call("Dog","Husky","Adult_Weekly");
        }
        if (sp.equals("Dog") && (brd.equals("Dog:Husky") && (ag.equals("Senior Adult") && (pln.equals("Daily Plan")))))
        {
            function_call("Dog","Husky","Senior_Adult_Daily");
        }
        if (sp.equals("Dog") && (brd.equals("Dog:Husky") && (ag.equals("Senior Adult") && (pln.equals("Weekly Plan")))))
        {
            function_call("Dog","Husky","Senior_Adult_Weekly");
        }

////////////////////////////////////////////////////////////////////////
                            //DOG-German Shepered//
///////////////////////////////////////////////////////////////////////
        //get data from DataBase
        if (sp.equals("Dog") && (brd.equals("Dog:German Shepered") && (ag.equals("Small") && (pln.equals("Daily Plan")))))
        {
            function_call("Dog","German Shepered","Small_Daily");
        }
        if (sp.equals("Dog") && (brd.equals("Dog:German Shepered") && (ag.equals("Small") && (pln.equals("Weekly Plan")))))
        {
            function_call("Dog","German Shepered","Small_Weekly");
        }
        if (sp.equals("Dog") && (brd.equals("Dog:German Shepered") && (ag.equals("Adult") && (pln.equals("Daily Plan")))))
        {
            function_call("Dog","German Shepered","Adult_Daily");
        }
        if (sp.equals("Dog") && (brd.equals("Dog:German Shepered") && (ag.equals("Adult") && (pln.equals("Weekly Plan")))))
        {
            function_call("Dog","German Shepered","Adult_Weekly");
        }
        if (sp.equals("Dog") && (brd.equals("Dog:German Shepered") && (ag.equals("Senior Adult") && (pln.equals("Daily Plan")))))
        {
            function_call("Dog","German Shepered","Senior_Adult_Daily");
        }
        if (sp.equals("Dog") && (brd.equals("Dog:German Shepered") && (ag.equals("Senior Adult") && (pln.equals("Weekly Plan")))))
        {
            function_call("Dog","German Shepered","Senior_Adult_Weekly");
        }

///////////////////////////////////////////////////////////////
                                    //CAT//
///////////////////////////////////////////////////////////////
        //get data from DataBase
        if (sp.equals("Cat") && (brd.equals("Cat:Persian") && (ag.equals("Small") && (pln.equals("Daily Plan")))))
        {
            function_call("Cat","Persian","Small_Daily");
        }
        if (sp.equals("Cat") && (brd.equals("Cat:Persian") && (ag.equals("Small") && (pln.equals("Weekly Plan")))))
        {
            function_call("Cat","Persian","Small_Weekly");
        }
        if (sp.equals("Cat") && (brd.equals("Cat:Persian") && (ag.equals("Adult") && (pln.equals("Daily Plan")))))
        {
            function_call("Cat","Persian","Adult_Daily");
        }
        if (sp.equals("Cat") && (brd.equals("Cat:Persian") && (ag.equals("Adult") && (pln.equals("Weekly Plan")))))
        {
            function_call("Cat","Persian","Adult_Weekly");
        }
        if (sp.equals("Cat") && (brd.equals("Cat:Persian") && (ag.equals("Senior Adult") && (pln.equals("Daily Plan")))))
        {
            function_call("Cat","Persian","Senior_Adult_Daily");
        }
        if (sp.equals("Cat") && (brd.equals("Cat:Persian") && (ag.equals("Senior Adult") && (pln.equals("Weekly Plan")))))
        {
            function_call("Cat","Persian","Senior_Adult_Weekly");
        }
//////////////////////////// CAT : AMERICAN LONG HAIR ////////////////////

        if (sp.equals("Cat") && (brd.equals("Cat:American Long hair") && (ag.equals("Small") && (pln.equals("Daily Plan")))))
        {
            function_call("Cat","American Long hair","Small_Daily");
        }
        if (sp.equals("Cat") && (brd.equals("Cat:American Long hair") && (ag.equals("Small") && (pln.equals("Weekly Plan")))))
        {
            function_call("Cat","American Long hair","Small_Weekly");
        }
        if (sp.equals("Cat") && (brd.equals("Cat:American Long hair") && (ag.equals("Adult") && (pln.equals("Daily Plan")))))
        {
            function_call("Cat","American Long hair","Adult_Daily");
        }
        if (sp.equals("Cat") && (brd.equals("Cat:American Long hair") && (ag.equals("Adult") && (pln.equals("Weekly Plan")))))
        {
            function_call("Cat","American Long hair","Adult_Weekly");
        }
        if (sp.equals("Cat") && (brd.equals("Cat:American Long hair") && (ag.equals("Senior Adult") && (pln.equals("Daily Plan")))))
        {
            function_call("Cat","American Long hair","Senior_Adult_Daily");
        }
        if (sp.equals("Cat") && (brd.equals("Cat:American Long hair") && (ag.equals("Senior Adult") && (pln.equals("Weekly Plan")))))
        {
            function_call("Cat","American Long hair","Senior_Adult_Weekly");
        }

/////////////////////////////////CAT :SIEMENSNS////////////////
        if (sp.equals("Cat") && (brd.equals("Cat:Siamese") && (ag.equals("Small") && (pln.equals("Daily Plan")))))
        {
            function_call("Cat","Siamese","Small_Daily");
        }
        if (sp.equals("Cat") && (brd.equals("Cat:Siamese") && (ag.equals("Small") && (pln.equals("Weekly Plan")))))
        {
            function_call("Cat","Siamese","Small_Weekly");
        }
        if (sp.equals("Cat") && (brd.equals("Cat:Siamese") && (ag.equals("Adult") && (pln.equals("Daily Plan")))))
        {
            function_call("Cat","Siamese","Adult_Daily");
        }
        if (sp.equals("Cat") && (brd.equals("Cat:Siamese") && (ag.equals("Adult") && (pln.equals("Weekly Plan")))))
        {
            function_call("Cat","Siamese","Adult_Weekly");
        }
        if (sp.equals("Cat") && (brd.equals("Cat:Siamese") && (ag.equals("Senior Adult") && (pln.equals("Daily Plan")))))
        {
            function_call("Cat","Siamese","Senior_Adult_Daily");
        }
        if (sp.equals("Cat") && (brd.equals("Cat:Siamese") && (ag.equals("Senior Adult") && (pln.equals("Weekly Plan")))))
        {
            function_call("Cat","Siamese","Senior_Adult_Weekly");
        }
///////////////////////////////////////////////////////////////////////////////////////////////////////
                                      //PARROT//
////////////////////////////////////////////////////////////////////////////////////////////////////////
        //get data from DataBase
        if (sp.equals("Parrot") && (brd.equals("Parrot:(any breed)") && (ag.equals("Small") && (pln.equals("Daily Plan")))))
        {
            function_call("Parrot","Any_Breed","Small_Daily");
        }
        if (sp.equals("Parrot") && (brd.equals("Parrot:(any breed)") && (ag.equals("Small") && (pln.equals("Weekly Plan")))))
        {
            function_call("Parrot","Any_Breed","Small_Weekly");
        }
        if (sp.equals("Parrot") && (brd.equals("Parrot:(any breed)") && (ag.equals("Adult") && (pln.equals("Daily Plan")))))
        {
            function_call("Parrot","Any_Breed","Adult_Daily");
        }
        if (sp.equals("Parrot") && (brd.equals("Parrot:(any breed)") && (ag.equals("Adult") && (pln.equals("Weekly Plan")))))
        {
            function_call("Parrot","Any_Breed","Adult_Weekly");
        }
        if (sp.equals("Parrot") && (brd.equals("Parrot:(any breed)") && (ag.equals("Senior Adult") && (pln.equals("Daily Plan")))))
        {
            function_call("Parrot","Any_Breed","Senior_Adult_Daily");
        }
        if (sp.equals("Parrot") && (brd.equals("Parrot:(any breed)") && (ag.equals("Senior Adult") && (pln.equals("Weekly Plan")))))
        {
            function_call("Parrot","Any_Breed","Senior_Adult_Weekly");
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////
                                       //HEN/CHICKEN//
////////////////////////////////////////////////////////////////////////////////////////////////////////
         //get data from DataBase
        if (sp.equals("Hen/Chicken") && (brd.equals("Hen:Boiler") && (ag.equals("Small") && (pln.equals("Daily Plan")))))
        {
            function_call("Hen","Boiler","Small_Daily");
        }
        if (sp.equals("Hen/Chicken") && (brd.equals("Hen:Boiler") && (ag.equals("Small") && (pln.equals("Weekly Plan")))))
        {
            function_call("Hen","Boiler","Small_Weekly");
        }
        if (sp.equals("Hen/Chicken") && (brd.equals("Hen:Boiler") && (ag.equals("Adult") && (pln.equals("Daily Plan")))))
        {
            function_call("Hen","Boiler","Adult_Daily");
        }
        if (sp.equals("Hen/Chicken") && (brd.equals("Hen:Boiler") && (ag.equals("Adult") && (pln.equals("Weekly Plan")))))
        {
            function_call("Hen","Boiler","Adult_Weekly");
        }
        if (sp.equals("Hen/Chicken") && (brd.equals("Hen:Boiler") && (ag.equals("Senior Adult") && (pln.equals("Daily Plan")))))
        {
            function_call("Hen","Boiler","Senior_Adult_Daily");
        }
        if (sp.equals("Hen/Chicken") && (brd.equals("Hen:Boiler") && (ag.equals("Senior Adult") && (pln.equals("Weekly Plan")))))
        {
            function_call("Hen","Boiler","Senior_Adult_Weekly");
        }
///////////////////////////////////////// NON -BOILER ////////////////////
        //get data from DataBase
        if (sp.equals("Hen/Chicken") && (brd.equals("Hen:Non Boiler") && (ag.equals("Small") && (pln.equals("Daily Plan")))))
        {
            function_call("Hen","Boiler","Small_Daily");
        }
        if (sp.equals("Hen/Chicken") && (brd.equals("Hen:Non Boiler") && (ag.equals("Small") && (pln.equals("Weekly Plan")))))
        {
            function_call("Hen","Boiler","Small_Weekly");
        }
        if (sp.equals("Hen/Chicken") && (brd.equals("Hen:Non Boiler") && (ag.equals("Adult") && (pln.equals("Daily Plan")))))
        {
            function_call("Hen","Boiler","Adult_Daily");
        }
        if (sp.equals("Hen/Chicken") && (brd.equals("Hen:Non Boiler") && (ag.equals("Adult") && (pln.equals("Weekly Plan")))))
        {
            function_call("Hen","Boiler","Adult_Weekly");
        }
        if (sp.equals("Hen/Chicken") && (brd.equals("Hen:Non Boiler") && (ag.equals("Senior Adult") && (pln.equals("Daily Plan")))))
        {
            function_call("Hen","Boiler","Senior_Adult_Daily");
        }
        if (sp.equals("Hen/Chicken") && (brd.equals("Hen:Non Boiler") && (ag.equals("Senior Adult") && (pln.equals("Weekly Plan")))))
        {
            function_call("Hen","Boiler","Senior_Adult_Weekly");
        }


        downloadimgbtn.setOnClickListener(new View.OnClickListener()
        {

        @Override
        public void onClick(View v) {
        DownloadImage();
        Toast.makeText(MainActivity_Diet_Plan_Load.this, "Your Image is Downloaded", Toast.LENGTH_SHORT).show();
        }
        });
    } // On Create End


    //////////////////////////FUNCTIONS//////////////////////////

    void ShowImage(String url)
    {
        mProgressDialog = new ProgressDialog(MainActivity_Diet_Plan_Load.this);
        mProgressDialog.setMessage("Fetching Plan..");
        mProgressDialog.setIndeterminate(false);
        // Show progressdialog
        mProgressDialog.show();
    }
    String imageuri=new String();
    public void function_call(final String child1, final String child2,final String child3)
    {
        mProgressDialog = new ProgressDialog(MainActivity_Diet_Plan_Load.this);
        mProgressDialog.setMessage("Fetching Plan..");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.show();
        databaseReference= FirebaseDatabase.getInstance().getReference("Diet_Plan");
        databaseReference.addValueEventListener(new ValueEventListener()
        {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {

                imageuri=dataSnapshot.child(child1).child(child2).child(child3).getValue(String.class);

                if (!imageuri.equalsIgnoreCase(""))
                {

                 Picasso
                .with(MainActivity_Diet_Plan_Load.this)  // functions of picasso
                .load(imageuri).fit()
                .error(R.mipmap.ic_launcher)
                .into(imageView, new com.squareup.picasso.Callback() {

                    @Override
                    public void onSuccess() {

                        Toast.makeText(MainActivity_Diet_Plan_Load.this, "Successfully Generated", Toast.LENGTH_LONG).show();
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError() {
                        Toast.makeText(MainActivity_Diet_Plan_Load.this, "Failed to generate", Toast.LENGTH_LONG).show();
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
        File dir = new File(sdCard.getAbsolutePath() + "/PetPlanner/DietPlan");
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
        Toast.makeText(MainActivity_Diet_Plan_Load.this, "Generate Diet Plan First ", Toast.LENGTH_LONG).show();
        }

        }
    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }
}

