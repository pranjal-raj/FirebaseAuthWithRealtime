package com.example.firebaseauthwithrealtime;

public class User {
    String name, email, pswd;

    public User()
    {

    }

    public User(String name, String email, String pswd) {
        this.name = name;
        this.email = email;
        this.pswd = pswd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }
}
