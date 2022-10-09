package com.example.mahpara.myapp.Doctor_Appoinment;



public class doctorData {
    String id, name, email, specs, contact, image;

    public doctorData(){}
    public doctorData(String id, String name, String email, String specs, String contact, String image) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.specs = specs;
        this.contact = contact;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSpecs() {
        return specs;
    }

    public String getContact() {
        return contact;
    }

    public String getImage() {
        return image;
    }
}
