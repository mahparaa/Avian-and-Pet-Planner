package com.example.mahpara.myapp.Alarm_Notification;

import java.util.Calendar;



public  class DataQue {
    String PetName;
    Calendar FeedTime;
   public DataQue(String PetName, Calendar cl)
   {
       this.PetName=PetName;
       FeedTime=cl;
   }
   public String getPetName()
   {
       return PetName;
   }
   public Calendar getFeedTime()
   {
       return FeedTime;
   }
}
