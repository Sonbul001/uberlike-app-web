package com.example.uberLikeAppWeb;

import com.example.uberLikeAppWeb.Application.CustomerService;
import com.example.uberLikeAppWeb.Application.ICustomerService;
import com.example.uberLikeAppWeb.Core.Customer;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    private ICustomerService customerService;

    public CustomerController(){
        customerService = new CustomerService();
    }
    @PostMapping("/setCustomer")
    public boolean setCustomer(@RequestBody Customer customer){
        return customerService.setCustomer(customer);
    }
    @PostMapping("/customerRegister")
    public boolean register(@RequestBody Customer customer){
        return customerService.register(customer);
    }
    @PostMapping("/customerLogin")
    public Customer login(@RequestBody Customer customer){
        return customerService.login(customer);
    }
    @PostMapping("/customerRequest/{source}/{destination}")
    public boolean request(@PathVariable String source, @PathVariable String destination, @RequestBody Customer customer){
        return customerService.request(source,destination,customer);
    }
    @PostMapping("/customerListOffers")
    public String listOffers(){
        return customerService.listOffers();
    }
    @PostMapping("/customerSelectOffer/{offerID}")
    public boolean selectOffer(@PathVariable int offerID){
        return customerService.selectOffer(offerID);
    }
    @PostMapping("/customerRateRide/{rating}")
    public boolean rateCompletedRide(@PathVariable int rating){
        return customerService.rateCompletedRide(rating);
    }
    @PostMapping("/customerClearNotifications")
    public boolean clearNotifications(){
        return customerService.clearNotifications();
    }
}
