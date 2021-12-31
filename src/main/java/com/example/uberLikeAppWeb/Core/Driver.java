package com.example.uberLikeAppWeb.Core;
public class Driver extends User {
    private String drivingLicense;
    private String nationalID;
    private double balance;

    public Driver(){
        userName = "";
        email = "";
        password = "";
        mobileNo = "";
        drivingLicense = null;
        nationalID = null;
        balance = 0;
    }
    Driver(String userName, String email, String password, String mobile, String drivingLicense, String nationalID){
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.mobileNo = mobile;
        this.drivingLicense = drivingLicense;
        this.nationalID = nationalID;
        this.balance = 0;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setMobileNo(String mobileNo){
        this.mobileNo = mobileNo;
    }
    public void setDrivingLicense(String drivingLicense){
        this.drivingLicense = drivingLicense;
    }
    public void setNationalID(String nationalID){
        this.nationalID = nationalID;
    }
    public void setBalance(double balance){
        this.balance = balance;
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
    public String getDrivingLicense(){
        return drivingLicense;
    }
    public String getNationalID(){
        return nationalID;
    }
    public double getBalance(){
        return balance;
    }
    public void registered() {
        this.isRegistered = true;
    }
}
