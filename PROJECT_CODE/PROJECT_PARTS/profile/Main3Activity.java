package com.mahpara.pet_profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Main3Activity extends AppCompatActivity
{

    private StorageReference mstoage;
    private static final int CAMERA_REQUEST_CODE=1;
    ProgressDialog progressDialog;
    private ImageView imageview;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mstoage= FirebaseStorage.getInstance().getReference();
        Button btn=(Button)findViewById(R.id.btn3);
        imageview=(ImageView)findViewById(R.id.imageView);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent in=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(in,CAMERA_REQUEST_CODE);
            }
        });
    }

    protected void onActivityResult(int requestcode,int resultcode,Intent data)
    {
        if(requestcode==CAMERA_REQUEST_CODE && resultcode==RESULT_OK )
        {
            progressDialog=new ProgressDialog(Main3Activity.this);
            progressDialog.setMessage("Uploading");
            progressDialog.show();
            Uri uri=data.getData();
            StorageReference filepath=mstoage.child("Photos").child(uri.getLastPathSegment());

            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                     Uri downloaduri=taskSnapshot.getDownloadUrl();
                    Picasso.with(Main3Activity.this).load(downloaduri).fit().centerCrop().into(imageview);
                    Toast.makeText(Main3Activity.this,"Successfully Uploaded",Toast.LENGTH_LONG).show();
                }
            });

        }
    }
}
