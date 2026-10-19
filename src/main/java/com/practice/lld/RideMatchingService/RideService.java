package com.practice.lld.RideMatchingService;

public class RideService {


    DriverMatchingService matchingService;

    public RideService(DriverMatchingService matchingService) {
        this.matchingService = matchingService;
    }


    public Ride requestRide(Rider rider,Location destination){
        return fiddRides(rider, destination);
    }

    public void bookRide(Ride ride){
        matchingService.assignDriver( ride);
    }

    private Ride fiddRides(Rider rider, Location destination) {
        return null;
    }

    public void cancelRide(Ride ride){

    }
    public void completeRide(Ride ride){

    }

}
