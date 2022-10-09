package com.example.mahpara.myapp.Doctor_Appoinment;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;



public interface mailprototype {

    @FormUrlEncoded
    @POST("EmailNotification.php")
    Call<EmailSend>emailSend(@Field("email") String email,
                             @Field("Status") String Status,
                             @Field("docMail") String docmail,
                             @Field("Contact") String contact,
                             @Field("Time") String time,
                             @Field("Date") String date,
                             @Field("Description") String description);
}
