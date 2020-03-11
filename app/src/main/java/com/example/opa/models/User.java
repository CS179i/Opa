package com.example.opa.models;

public class User {

    String name;
    //String userId;
    String userType;
    String company;
    String phone;

    public User(String name, String userType, String company, String phone) {
        this.name = name;
        //this.userId = userId;
        this.userType = userType;
        this.company = company;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String name) {
        this.phone = phone;
    }

    //public String getUserId() { return  userId; }

    //public void setUserId(String userId) {
    //    this.userId = userId;
    //}

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
