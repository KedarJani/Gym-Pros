package com.example.gymproject;

public class User {
    public  String firstName,lastName,email,password,repPassword;

    public User(){}

    public User(String firstName, String lastName, String email, String password, String repPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.repPassword = repPassword;
    }
}
