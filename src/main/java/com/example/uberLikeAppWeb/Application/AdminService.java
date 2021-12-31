package com.example.uberLikeAppWeb.Application;

import com.example.uberLikeAppWeb.Core.Admin;
import com.example.uberLikeAppWeb.Persistance.AdminPersistance;
import com.example.uberLikeAppWeb.Persistance.DriverPersistance;

public class AdminService implements IAdminService{
    private AdminPersistance model;
    private Admin admin;
    private DriverPersistance pendingDrivers;

    public AdminService(){
        this.admin = new Admin();
        this.model = new AdminPersistance();
        pendingDrivers = new DriverPersistance();
    }
    @Override
    public boolean setAdmin(Admin admin){
        this.admin = admin;
        return true;
    }
    @Override
    public boolean register(Admin admin){
        model.insert(admin);
        return true;
    }
    @Override
    public boolean login(Admin admin){
        if(model.check(admin)){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public String listPendingDrivers(){
        String message = pendingDrivers.getPendingDrivers();
        return message;
    }
    @Override
    public String approveDriver(String driverUserName){
        pendingDrivers.insert("UPDATE Drivers SET isRegistered = true where driverID='" + driverUserName +"'");
        return (driverUserName + " is approved successfully");
    }
    @Override
    public String denyDriver(String driverUserName){
        pendingDrivers.remove(driverUserName);
        return (driverUserName + " is denied successfully");
    }
}
