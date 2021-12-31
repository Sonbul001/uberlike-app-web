package com.example.uberLikeAppWeb.Application;

import com.example.uberLikeAppWeb.Core.Customer;
import com.example.uberLikeAppWeb.Core.RideRequest;
import com.example.uberLikeAppWeb.Persistance.CustomerPersistance;

public class CustomerService implements ICustomerService{
    private CustomerPersistance model;
    private Customer customer;

    public CustomerService(){
        this.customer = new Customer();
        this.model = new CustomerPersistance();
    }
    @Override
    public boolean setCustomer(Customer customer){
        this.customer = customer;
        return true;
    }
    @Override
    public boolean register(Customer customer){
        model.insert(customer);
        return true;
    }
    @Override
    public Customer login(Customer customer){
        if(model.check(customer)){
            customer = model.getInfo(customer.getUserName());
            return customer;
        }else{
            return null;
        }
    }
    @Override
    public boolean request(String source, String destination, Customer customer){
        RideRequest r = new RideRequest(source,destination,customer);
        r.publishRequest();
        return true;
    }
    @Override
    public String listOffers(){
        return model.listOffers(this.customer);
    }
    @Override
    public boolean selectOffer(int offerID){
        new DriverService().startRide(offerID);
        return true;
    }
    @Override
    public boolean rateCompletedRide(int rating){
        model.rateCompletedRide(rating,this.customer);
        return true;
    }
    @Override
    public boolean clearNotifications(){
        model.clearNotifications(customer.getUserName());
        return true;
    }
}
