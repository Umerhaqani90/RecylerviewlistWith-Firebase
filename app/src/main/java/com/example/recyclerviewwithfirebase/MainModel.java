package com.example.recyclerviewwithfirebase;

public class MainModel {
    String name,course,email,tUrl;
    // constructor for firebase
    MainModel(){

    }

// constructors
    public MainModel(String name, String course, String email, String tUrl) {
        this.name = name;
        this.course = course;
        this.email = email;
        this.tUrl = tUrl;
    }
// getter and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String gettUrl() {
        return tUrl;
    }

    public void settUrl(String tUrl) {
        this.tUrl = tUrl;
    }
}
