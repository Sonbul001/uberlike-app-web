package com.example.uberLikeAppWeb.Application;

import com.example.uberLikeAppWeb.Core.Driver;
import com.example.uberLikeAppWeb.Core.Ride;

public interface IDriverService {
    public boolean setDriver(Driver driver);
    public boolean register(Driver driver);
    public Driver login(Driver driver);
    public boolean addFavArea(String area);
    public boolean removeFavArea(String area);
    public boolean createOffer(String customerName, String source, String destination, double price);
    public String listRequests();
    public boolean clearNotifications();
    public boolean setRide(Ride ride);
    public boolean startRide(int offerID);
    public boolean endRide();
}
