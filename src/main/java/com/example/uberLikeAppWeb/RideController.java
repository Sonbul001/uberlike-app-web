package com.example.uberLikeAppWeb;

import com.example.uberLikeAppWeb.Application.IRideService;
import com.example.uberLikeAppWeb.Application.RideService;
import com.example.uberLikeAppWeb.Core.Ride;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RideController {
    private IRideService rideService;

    public RideController(){
        rideService = new RideService();
    }

    @PostMapping("/setRide")
    public boolean setRide(@RequestBody Ride ride){
        return rideService.setRide(ride);
    }
    @PostMapping("/rideStartRide")
    public String startRide(){
        return rideService.startRide();
    }
    @PostMapping("/rideCalcDistance")
    public String calcDistance(){
        return rideService.calcDistance();
    }
    @PostMapping("/rideEndRide")
    public boolean endRide(){
        return rideService.endRide();
    }
}
