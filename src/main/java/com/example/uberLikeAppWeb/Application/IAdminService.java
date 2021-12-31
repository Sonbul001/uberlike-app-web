package com.example.uberLikeAppWeb.Application;

import com.example.uberLikeAppWeb.Core.Admin;

public interface IAdminService {
    public boolean setAdmin(Admin admin);
    public boolean register(Admin admin);
    public boolean login(Admin admin);
    public String listPendingDrivers();
    public String approveDriver(String driverUserName);
    public String denyDriver(String driverUserName);
}
