package com.example.uberLikeAppWeb;

import com.example.uberLikeAppWeb.Application.DriverService;
import com.example.uberLikeAppWeb.Application.IDriverService;
import com.example.uberLikeAppWeb.Core.Driver;
import com.example.uberLikeAppWeb.Core.Ride;
import org.springframework.web.bind.annotation.*;

@RestController
public class DriverController {
    private IDriverService driverService;

    public DriverController() {
        driverService = new DriverService();
    }
    @PostMapping("/setDriver")
    public boolean setDriver(@RequestBody Driver driver){
        return driverService.setDriver(driver);
    }
    @PostMapping("/driverRegister")
    public boolean register(@RequestBody Driver driver){
        return driverService.register(driver);
    }
    @PostMapping("/driverLogin")
    public Driver login(@RequestBody Driver driver){
        return driverService.login(driver);
    }
    @PostMapping("/driverAddArea/{area}")
    public boolean addFavArea(@PathVariable String area){
        return driverService.addFavArea(area);
    }
    @PostMapping("/driverRemoveArea/{area}")
    public boolean removeFavArea(@PathVariable String area){
        return driverService.removeFavArea(area);
    }
    @PostMapping("/driverCreateOffer/{customerName}/{source}/{destination}/{price}")
    public boolean createOffer(@PathVariable String customerName, @PathVariable String source, @PathVariable String destination,@PathVariable double price){
        return driverService.createOffer(customerName,source,destination,price);
    }
    @PostMapping("/driverListRequests")
    public String listRequests(){
        return driverService.listRequests();
    }
    @PostMapping("/driverClearNotifications")
    public boolean clearNotifications(){
        return driverService.clearNotifications();
    }
    @PostMapping("/driverSetRide")
    public boolean setRide(@RequestBody Ride ride){
        return driverService.setRide(ride);
    }
    @PostMapping("/driverStartRide/{offerID}")
    public boolean startRide(@PathVariable int offerID){
        return driverService.startRide(offerID);
    }
    @PostMapping("/driverEndRide")
    public boolean endRide(){
        return driverService.endRide();
    }
}
