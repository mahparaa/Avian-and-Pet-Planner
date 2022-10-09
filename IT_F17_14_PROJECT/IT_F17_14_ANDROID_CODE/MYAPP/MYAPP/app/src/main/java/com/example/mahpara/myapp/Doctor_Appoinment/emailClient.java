package com.example.mahpara.myapp.Doctor_Appoinment;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class emailClient {
    private static final String BaseUri="http://dibukhanmathematician.com/OnlineFIRSystem/";
    private static Retrofit retrofit;

    public static Retrofit getApiClient()
    {
        if(retrofit==null)
        {
            retrofit= new Retrofit.Builder().baseUrl(BaseUri).
                    addConverterFactory(GsonConverterFactory.create()).build();

        }
        return retrofit;
    }
}
