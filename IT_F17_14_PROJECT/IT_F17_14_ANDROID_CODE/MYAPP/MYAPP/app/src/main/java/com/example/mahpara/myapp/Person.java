package com.example.mahpara.myapp;

/**
 * Created by Mahpara on 3/11/2018.
 */

public class Person {

    private String Petimage_class;
    private String Username_class;
    private String Petname_class;
    private String Useremail_class;
    private String Petspecie_class;
    private String Petbreed_class;
    private String Petage_class;
    private String Pettimings_class;
    private String Petfood_class;

    public Person() {
    }

    public Person(String petimage_class, String username_class,String petname_class, String useremail_class, String petspecie_class, String petbreed_class, String petage_class, String pettimings_class, String petfood_class) {
        Petimage_class = petimage_class;
        Username_class = username_class;
        Petname_class = petname_class;
        Useremail_class = useremail_class;
        Petspecie_class = petspecie_class;
        Petbreed_class = petbreed_class;
        Petage_class = petage_class;
        Pettimings_class = pettimings_class;
        Petfood_class = petfood_class;
    }

    public String getPetimage_class() {
        return Petimage_class;
    }

    public void setPetimage_class(String petimage_class) {
        Petimage_class = petimage_class;
    }

    public String getUsername_class() {
        return Username_class;
    }

    public void setUsername_class(String username_class) {
        Username_class = username_class;
    }

    public String getUseremail_class() {
        return Useremail_class;
    }

    public void setUseremail_class(String useremail_class) {
        Useremail_class = useremail_class;
    }

    public String getPetname_class() {
        return Petname_class;
    }

    public void setPetname_class(String petname_class) {
        Petname_class = petname_class;
    }

    public String getPetspecie_class() {
        return Petspecie_class;
    }

    public void setPetspecie_class(String petspecie_class) {
        Petspecie_class = petspecie_class;
    }

    public String getPetbreed_class() {
        return Petbreed_class;
    }

    public void setPetbreed_class(String petbreed_class) {
        Petbreed_class = petbreed_class;
    }

    public String getPetage_class() {
        return Petage_class;
    }

    public void setPetage_class(String petage_class) {
        Petage_class = petage_class;
    }

    public String getPettimings_class() {
        return Pettimings_class;
    }

    public void setPettimings_class(String pettimings_class) {
        Pettimings_class = pettimings_class;
    }

    public String getPetfood_class() {
        return Petfood_class;
    }

    public void setPetfood_class(String petfood_class) {
        Petfood_class = petfood_class;
    }
}
