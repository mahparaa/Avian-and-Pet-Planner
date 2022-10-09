package com.example.mahpara.myapp.Alarm_Notification;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mahpara.myapp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.content.Context.ALARM_SERVICE;



public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewContainer> {

    Context mContext;
    List<DataQue>data=new ArrayList<>();
    int mposition;
    View mView;
    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    AlarmReceiver instance;

    public CardAdapter(Context mContext, List<DataQue>data)
    {
        this.mContext=mContext;
        this.data=data;
        alarmManager = (AlarmManager)mContext.getSystemService(ALARM_SERVICE);
        instance=AlarmReceiver.instance();
    }
    @Override
    public ViewContainer onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mContext);
        View view =inflater.inflate(R.layout.cards_activity,null);
        mView=view;
        return new ViewContainer(view);
    }

    @Override
    public void onBindViewHolder(final ViewContainer holder, final int position) {
        final DataQue dataQue=data.get(position);
        //holder.FeedTime.setText(dataQue.getFeedTime().toString());
        holder.PetName.setText("Pet Name: ");
        if(instance!=null)
        {
            holder.switcher.setChecked(true);
        }
        holder.PetName.append(dataQue.getPetName());
        holder.FeedTime.setText("Feed Time "+dataQue.getFeedTime().get(Calendar.HOUR_OF_DAY)+":"+dataQue.getFeedTime().get(Calendar.MINUTE));
        holder.imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mposition=position;
                ConfimationDialog();
            }
        });
        holder.switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    holder.switcher.setText("Alarm On");
                    Intent myIntent = new Intent(mContext, AlarmReceiver.class);
                    pendingIntent = PendingIntent.getBroadcast(mContext, 0, myIntent, 0);
                   // Calendar now=Calendar.getInstance();
                    Date actualTime=Calendar.getInstance().getTime();
                    Date AlarmTime=dataQue.getFeedTime().getTime();
                    if(actualTime.before(AlarmTime)) {
                        alarmManager.set(AlarmManager.RTC, dataQue.getFeedTime().getTimeInMillis(), pendingIntent);
                    }
                }
                else
                {
                    holder.switcher.setText("Alarm Off");
                    alarmManager.cancel(pendingIntent);
                    stopRing();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewContainer extends RecyclerView.ViewHolder{

        public TextView PetName,FeedTime;
        public ImageButton imgbtn;
        public Switch switcher;
        public ViewContainer(View itemView) {
            super(itemView);
            PetName=(TextView)itemView.findViewById(R.id.PetName);
            FeedTime=(TextView)itemView.findViewById(R.id.FeedTime);
            imgbtn=(ImageButton)itemView.findViewById(R.id.imgbtn);
            switcher=(Switch)itemView.findViewById(R.id.switcher);
        }
    }

    private void stopRing() {
        AlarmReceiver instance=AlarmReceiver.instance();
        if(instance!=null)
        {
        instance.mMediaPlayer.stop();}
    }

    private void ConfimationDialog() {
        AlertDialog alertDialog=new AlertDialog.Builder(mView.getRootView().getContext()).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Do you want to remove Alarm");
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        alertDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        data.remove(mposition);
                        dialog.dismiss();
                        alarmManager.cancel(pendingIntent);
                        stopRing();
                        Intent intent=new Intent(mContext,AlarmCards.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        //((Activity)mContext).finish();
                    }
                });
        alertDialog.setButton(android.app.AlertDialog.BUTTON_NEGATIVE, "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }

                });
        /*alertDialog.setButton(android.app.AlertDialog.BUTTON_NEUTRAL, "View",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        AllergicShow();
                        //dialog.dismiss();
                        //finish();

                    }
                });*/
        alertDialog.show();
    }

}
