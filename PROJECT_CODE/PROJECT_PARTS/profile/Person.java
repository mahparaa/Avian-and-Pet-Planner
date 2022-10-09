package com.mahpara.pet_profile;

/**
 * Created by cell  spot on 8/25/2017.
 */

class Person {


    public String name;
    public String email;
    public String password;
    public String phone;
    public String address;

    public Person(String name,String email,String password,String phone,String address)
    {
        this.name=name;
        this.email=email;
        this.password=password;
        this.phone=phone;
        this.address=address;

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
