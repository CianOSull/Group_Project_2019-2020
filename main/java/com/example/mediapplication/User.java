package com.example.mediapplication;

public class User {
    private String Fname;
    private String Lname;
    private String Age;
    private String Email;
    private String UID;
    private String GP_ID;
    private String PhoneNum;
    private String Address;
    private String IP_ID;

    public String getIP_ID() {
        return IP_ID;
    }

    public void setIP_ID(String IP_ID) {
        this.IP_ID = IP_ID;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public User() {
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getGP_ID() {
        return GP_ID;
    }

    public void setGP_ID(String GP_ID) {
        this.GP_ID = GP_ID;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }
}
