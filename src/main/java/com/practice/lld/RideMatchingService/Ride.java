package com.practice.lld.RideMatchingService;

public class Ride {
    String id;
    String driverId;
    String riderId;
    RideStatus status;
    Location source;
    Location destination;
    Long createdAt;
    Long lastUpdated;

    public Ride(String id, String driverId, String riderId, RideStatus status, Location source, Location destination, Long createdAt, Long lastUpdated) {
        this.id = id;
        this.driverId = driverId;
        this.riderId = riderId;
        this.status = status;
        this.source = source;
        this.destination = destination;
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;
    }
    private synchronized boolean startRide(){
        if(this.status.equals(RideStatus.OFFERED)){
            this.status = RideStatus.START;
            return true;
        }
        return false;

    }
    private synchronized  void cancelRide(){
            this.status = RideStatus.CANCELLED;

    }
    private synchronized void markComplete(){

    }
}
