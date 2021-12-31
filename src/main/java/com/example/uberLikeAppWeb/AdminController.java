package com.example.uberLikeAppWeb;

import com.example.uberLikeAppWeb.Application.AdminService;
import com.example.uberLikeAppWeb.Application.IAdminService;
import com.example.uberLikeAppWeb.Core.Admin;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {
    private IAdminService adminService;

    public AdminController(){
        adminService = new AdminService();
    }

    @PostMapping("/setAdmin")
    public boolean setAdmin(@RequestBody Admin admin){
        return adminService.setAdmin(admin);
    }

    @PostMapping("/registerAdmin")
    public boolean register(@RequestBody Admin admin){
        return adminService.register(admin);
    }

    @PostMapping("/loginAdmin")
    public boolean login(@RequestBody Admin admin){
        return adminService.login(admin);
    }

    @PostMapping("/listPendingDrivers")
    public String listPendingDrivers(){
        return adminService.listPendingDrivers();
    }

    @PostMapping("/approveDriver/{driverUserName}")
    public String approveDriver(@PathVariable String driverUserName){
        return adminService.approveDriver(driverUserName);
    }

    @PostMapping("/denyDriver/{driverUserName}")
    public String denyDriver(@PathVariable String driverUserName){
        return adminService.denyDriver(driverUserName);
    }
}
