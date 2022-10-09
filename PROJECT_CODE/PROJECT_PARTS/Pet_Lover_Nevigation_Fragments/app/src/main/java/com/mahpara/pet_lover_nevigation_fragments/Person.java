package com.mahpara.pet_lover_nevigation_fragments;

/**
 * Created by cell  spot on 8/30/2017.
 */

class Person {

    public String name;
    public String age;
    public String specie;
    public String favfood;
    public String timgs;

    public Person() {
    }

    public Person(String name, String age, String specie, String favfood, String timgs) {
        this.name = name;
        this.age=age;
        this.specie=specie;
        this.favfood=favfood;
        this.timgs=timgs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public String getFavfood() {
        return favfood;
    }

    public void setFavfood(String favfood) {
        this.favfood = favfood;
    }

    public String getTimgs() {
        return timgs;
    }

    public void setTimgs(String timgs) {
        this.timgs = timgs;
    }
}
