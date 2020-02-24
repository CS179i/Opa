package com.example.opa.models;

public class User {

    String name;
    String userId;
    String userType;

    public User(String userId, String name, String userType) {
        this.name = name;
        this.userId = userId;
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() { return  userId; }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
