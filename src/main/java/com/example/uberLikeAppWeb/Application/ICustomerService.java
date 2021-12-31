package com.example.uberLikeAppWeb.Application;

import com.example.uberLikeAppWeb.Core.Customer;

public interface ICustomerService {
    public boolean setCustomer(Customer customer);
    public boolean register(Customer customer);
    public Customer login(Customer customer);
    public boolean request(String source, String destination, Customer customer);
    public String listOffers();
    public boolean selectOffer(int offerID);
    public boolean rateCompletedRide(int rating);
    public boolean clearNotifications();
}
