package com.example.uberLikeAppWeb.Application;

import com.example.uberLikeAppWeb.Core.Driver;
import com.example.uberLikeAppWeb.Core.Offer;
import com.example.uberLikeAppWeb.Core.Ride;
import com.example.uberLikeAppWeb.Persistance.DriverPersistance;

public class DriverService implements IDriverService{
    private DriverPersistance model;
    private Driver driver;
    private Ride ride;

    public DriverService(){
        this.driver=new Driver();
        this.model =new DriverPersistance();
    }
    @Override
    public boolean setDriver(Driver driver){
        this.driver = driver;
        return true;
    }
    @Override
    public boolean register(Driver driver){
        model.insert(driver);
        return true;
    }
    @Override
    public Driver login(Driver driver){
        if(model.check(driver)){
            driver = model.getInfo(driver.getUserName());
            return driver;
        }else{
            return null;
        }
    }
    @Override
    public boolean addFavArea(String area){
        model.addFavArea(driver.getUserName(), area);
        return true;
    }
    @Override
    public boolean removeFavArea(String area){
        model.removeFavArea(driver.getUserName(), area);
        return true;
    }
    @Override
    public boolean createOffer(String customerName, String source, String destination, double price){
        Offer offer = new Offer(customerName, source, destination, driver, price);
        offer.publishOffer();
        return true;
    }
    @Override
    public String listRequests(){
        return model.listRequests(driver);
    }
    @Override
    public boolean clearNotifications(){
        model.clearNotifications(driver.getUserName());
        return true;
    }
    @Override
    public boolean setRide(Ride ride){
        this.ride = ride;
        return true;
    }
    @Override
    public boolean startRide(int offerID){
        Ride currentRide = new Ride();
        ride = currentRide.getInfo(offerID);
        RideService rideController = new RideService(ride);
        rideController.startRide();
        return true;
    }
    @Override
    public boolean endRide(){
        Ride currentRide = new Ride();
        ride = currentRide.getInfo(this.driver.getUserName());
        if(ride == null){
            return false;
        }else{
            RideService rideController = new RideService(ride);
            rideController.endRide();
            return true;
        }
    }
}
