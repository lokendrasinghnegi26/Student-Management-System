package com.lokendrasingh.welcome;

public class model {
    String name, fatherName, email, gender;
    model()
    {
        //  if we will not make this blank constructor then our data will not go in recycler
    }

    public model(String name, String fatherName, String email, String gender) {
        this.name = name;
        this.fatherName = fatherName;
        this.email = email;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
