package com.example.mahpara.myapp.Doctor_Appoinment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mahpara.myapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class doc_adapter extends RecyclerView.Adapter<doc_adapter.ViewContainer> {

    Context context;
    List<doctorData> mData;
    View mView;
     String  docMail,Status,Usermail;
     String contactNo;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Boolean appointment;


    Boolean checksum=true;
    public doc_adapter(Context context,List<doctorData>data, String contactNo)
    {
        this.context=context;
        mData=data;
        this.contactNo=contactNo;



    }
    @Override
    public ViewContainer onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.doctor_view,null);
        mView=view;
        sharedPreferences=context.getSharedPreferences("UserData",Context.MODE_PRIVATE);
        Usermail=sharedPreferences.getString("UserEmail",null);
        editor = sharedPreferences.edit();
        return new ViewContainer(view);
    }

    @Override
    public void onBindViewHolder(final ViewContainer holder, final int position) {
        final doctorData mdata=mData.get(position);
        holder.name.setText(mdata.getName());
        holder.email.setText(mdata.getEmail());
        holder.specs.setText(mdata.getSpecs());
        docMail=holder.email.getText().toString();
        /*contactNo=mdata.getContact();*/
        holder.contact.setText(mdata.getContact());
        Picasso.with(context).load(mdata.getImage()).into(holder.image);
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("App_Appointment");
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapData:dataSnapshot.getChildren())
                {
                    AppointmentModel model=postSnapData.getValue(AppointmentModel.class);

                    if (mdata.getEmail().toString().equalsIgnoreCase(model.getDocEmail().toString())
                            &&model.getUserEmail().equalsIgnoreCase(Usermail))
                    {
                        if(model.getStatus().equalsIgnoreCase("Appoint"))
                        {
                            holder.appoint.setText("Cancel");
                        }
                        else if(model.getStatus().equalsIgnoreCase("Cancel"))
                        {
                            holder.appoint.setText("Appoint");
                        }
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        holder.appoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            if (holder.appoint.getText().toString().equalsIgnoreCase("Appoint")) {
               //holder.appoint.setText("Cancel");
                Status = "Appoint";
                //mailCall(Usermail, Status, docMail);
                Intent intent=new Intent(context,AppointmentDetail.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("userEmail",Usermail);
                intent.putExtra("docEmail",holder.email.getText());
                intent.putExtra("status",Status);
                intent.putExtra("contact",contactNo);
                context.startActivity(intent);

            }
            else if (holder.appoint.getText().toString().equalsIgnoreCase("Cancel")) {
                String docmail=holder.email.getText().toString();
                ConfimationDialog(mdata,docmail);

                }
            }
        });

    }

    private void mailCall(String email,  String Status,  String docmail,String date,String time,String des,final doctorData mdata) {
        mailprototype mail=emailClient.getApiClient().create(mailprototype.class);
        //contactNo insert
        Call<EmailSend>call=mail.emailSend(email,  "Cancel",  docmail,contactNo,time,date,des);
        call.enqueue(new Callback<EmailSend>() {
            @Override
            public void onResponse(Call<EmailSend> call, Response<EmailSend> response) {
                EmailSend emailSend=response.body();
                if(emailSend.getResponse().equalsIgnoreCase("Success")) {
                    FirebaseStatusChanged(mdata);
                    Toast.makeText(context, "Cancel request Sent to the doctor", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<EmailSend> call, Throwable t) {
                Toast.makeText(context,"Exception: "+t.toString(),Toast.LENGTH_LONG).show();
            }
        });

    }

    private void ConfimationDialog(final doctorData mdata, final String docMail) {
        AlertDialog alertDialog=new AlertDialog.Builder(mView.getRootView().getContext()).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Are you sure to cancel request?");
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        alertDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                        mailCall(Usermail,"Cancel",docMail,"nodata","nodata","nodata",mdata);
                    }
                });
        alertDialog.setButton(android.app.AlertDialog.BUTTON_NEGATIVE, "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }

                });

        alertDialog.show();
    }

    private void FirebaseStatusChanged(final doctorData mdata) {
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("App_Appointment");
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapData:dataSnapshot.getChildren())
                {
                    AppointmentModel model=postSnapData.getValue(AppointmentModel.class);

                    if (mdata.getEmail().toString().equalsIgnoreCase(model.getDocEmail().toString())
                            &&model.getUserEmail().equalsIgnoreCase(Usermail))
                    {
                        if(model.getStatus().equalsIgnoreCase("Appoint"))
                        {
                            postSnapData.getRef().child("status").getRef().setValue("Cancel");
                        }

                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();

    }

    public class ViewContainer extends RecyclerView.ViewHolder
    {
        TextView name,email,specs,contact;
        ImageView image;
        Button appoint;
        public ViewContainer(View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.name);
            email=(TextView)itemView.findViewById(R.id.email);
            specs=(TextView)itemView.findViewById(R.id.specs);
            contact=(TextView)itemView.findViewById(R.id.contact);
            image=(ImageView) itemView.findViewById(R.id.img);
            appoint=(Button)itemView.findViewById(R.id.appoint);
        }
    }

}
