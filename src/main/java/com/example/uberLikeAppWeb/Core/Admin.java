package com.example.uberLikeAppWeb.Core;

public class Admin {

    private String userName;
    private String password;

    public Admin(){
        userName = "admin";
        password = "admin";
    }
    public Admin(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
    public String getUserName(){
        return this.userName;
    }
    public String getPassword(){
        return this.password;
    }

}


