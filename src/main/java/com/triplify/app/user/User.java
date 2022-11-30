package com.triplify.app.user;

import java.time.LocalDate;
import java.time.Period;

public class User {
    private int id;
    private String username;
    private String first_name;
    private String last_name;
    private LocalDate dob;
    private String gender;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String address;

    public User(){}

    public User(
            String username,
            String first_name,
            String last_name,
            LocalDate dob,
            String gender,
            String address) {
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob = dob;
        this.gender = gender;
        this.age = calculateAge(dob);
        this.address = address;
    }

    public User(
            int id,
            String username,
            String first_name,
            String last_name,
            LocalDate dob,
            String gender,
            String address)
    {
        this.id = id;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob = dob;
        this.gender = gender;
        this.age = calculateAge(dob);
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
    private int calculateAge(LocalDate dob){
        LocalDate curDate = LocalDate.now();
        if ((dob != null) && (curDate != null)){
            return Period.between(dob, curDate).getYears();
        }
        else{
            return 0;
        }
    }
}

