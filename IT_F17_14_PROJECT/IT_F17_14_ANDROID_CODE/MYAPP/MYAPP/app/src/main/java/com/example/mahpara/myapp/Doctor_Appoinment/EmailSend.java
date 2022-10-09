package com.example.mahpara.myapp.Doctor_Appoinment;

import com.google.gson.annotations.SerializedName;

public class EmailSend{

	@SerializedName("response")
	private String response;

	public void setResponse(String response){
		this.response = response;
	}

	public String getResponse(){
		return response;
	}

	@Override
	public String toString(){
		return
				"EmailSend{" +
						"response = '" + response + '\'' +
						"}";
	}
}