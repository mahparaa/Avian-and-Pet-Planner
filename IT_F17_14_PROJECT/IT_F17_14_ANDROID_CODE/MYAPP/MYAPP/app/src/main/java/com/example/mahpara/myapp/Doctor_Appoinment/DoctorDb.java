package com.example.mahpara.myapp.Doctor_Appoinment;

import com.google.firebase.database.DatabaseReference;



public class DoctorDb {

    private DatabaseReference databaseReference;

    public DoctorDb() {
    }

    public DoctorDb(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    public boolean addDoctor (String name,String email,String specs,String contact,String image) {

        String id = databaseReference.push().getKey();
        databaseReference.child(id).setValue(new doctorData(id,name, email, specs, contact, image));

        return true;
    }
}
