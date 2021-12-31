package com.example.uberLikeAppWeb.Core;

public class Customer extends User{
    public Customer(){
        this.userName = "";
        this.email = "";
        this.password = "";
        this.mobileNo = "";
    }
    public Customer(String userName, String email, String password, String mobileNo){
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.mobileNo = mobileNo;
        this.isRegistered = true;
    }
    public Customer(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
    public void setUserName(String username){
        userName = username;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setMobileNo(String mobileNo){
        this.mobileNo = mobileNo;
    }
    public void setPwd(String pwd){
        password = pwd;
    }
    public String getUserName(){
        return userName;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public String getMobileNo(){
        return mobileNo;
    }
    public void registered(){
        this.isRegistered = true;
    }
}

