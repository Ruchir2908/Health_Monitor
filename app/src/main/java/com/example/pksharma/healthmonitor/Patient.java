package com.example.pksharma.healthmonitor;

import java.util.ArrayList;

class Patients{
    ArrayList<Patient> patients = new ArrayList<>();
}

public class Patient {

    String name;
    int age;
    String gender;
    String Disease;
    ArrayList<Symptom> Symptom=new ArrayList<Symptom>();


    public Patient(){

    }

    public  Patient(String name, int age,String gender,String Disease,ArrayList<Symptom> Symptom){
        this.name=name;
        this.age=age;
        this.Disease=Disease;
        this.gender=gender;
        this.Symptom=Symptom;

    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getDisease() {
        return Disease;
    }

    public ArrayList<com.example.pksharma.healthmonitor.Symptom> getSymptom() {
        return Symptom;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDisease(String disease) {
        Disease = disease;
    }

    public void setSymptom(ArrayList<com.example.pksharma.healthmonitor.Symptom> symptom) {
        Symptom = symptom;
    }
}


