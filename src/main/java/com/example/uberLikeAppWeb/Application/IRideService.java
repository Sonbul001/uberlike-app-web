package com.example.uberLikeAppWeb.Application;

import com.example.uberLikeAppWeb.Core.Ride;

public interface IRideService {
    public boolean setRide(Ride ride);
    public String startRide();
    public String calcDistance();
    public boolean endRide();
}
