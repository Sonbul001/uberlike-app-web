package com.example.uberLikeAppWeb.Application;

import com.example.uberLikeAppWeb.Core.Ride;

public class RideService implements IRideService{
    private Ride ride;
    private RideStrategy strategy;

    public RideService(){
        ride = new Ride();
    }
    public RideService(Ride ride){
        this.ride = ride;
    }
    @Override
    public boolean setRide(Ride ride){
        this.ride = ride;
        return true;
    }
    @Override
    public String startRide(){
        return calcDistance();
    }
    @Override
    public String calcDistance(){
        return "Distance calculated";
    }
    @Override
    public boolean endRide(){
        ride.completed();
        return true;
    }
}
