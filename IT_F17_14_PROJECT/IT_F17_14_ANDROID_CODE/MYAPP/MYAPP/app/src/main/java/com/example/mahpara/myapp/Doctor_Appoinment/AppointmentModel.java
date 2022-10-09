package com.example.mahpara.myapp.Doctor_Appoinment;

/**
 * Created by haseeb on 5/2/2018.
 */

public class AppointmentModel {
    String userEmail;
    String status;
    String docEmail;
    String userContact;
    String date;
    String time;
    String description;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getDocEmail() {
        return docEmail;
    }

    public void setDocEmail(String docEmail) {
        this.docEmail = docEmail;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AppointmentModel(String userEmail, String docEmail, String userContact, String date, String time, String description,String status) {

        this.userEmail = userEmail;
        this.docEmail = docEmail;
        this.userContact = userContact;
        this.date = date;
        this.time = time;
        this.description = description;
        this.status=status;
    }

    public AppointmentModel() {
    }
}
