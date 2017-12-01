package com.example.anti.proekt;

import java.io.Serializable;

/**
 * Created by Anti on 11/25/2017.
 */

public class User implements Serializable {

    String name;
    String lastname;
    String username;
    char gender;


    public User() {
    }

    public User(String name, String lastname, String username, char gender) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }


    @Override
    public String toString() {

        return username;
    }
}


