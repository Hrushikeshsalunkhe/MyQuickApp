package com.example.myquickappapplication.Models;

public class UserContact {
    String UserId;
    String UserName;
    String UserLastName;
    String UserPhoneNumber;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        this.UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        this.UserName = userName;
    }

    public String getUserLastName() {
        return UserLastName;
    }

    public void setUserLastName(String userLastName) {
        this.UserLastName = userLastName;
    }

    public String getUserPhoneNumber() {
        return UserPhoneNumber;
    }

    public void setUserPhoneNumber(String userLastName) {
        this.UserPhoneNumber = userLastName;
    }

}
