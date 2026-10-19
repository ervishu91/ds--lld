package com.practice.lld.parkinglot;

public class ParkingSlot {

    int spotId;
    SlotStatus status;
    SlotType type;
    Floor floor;

    public ParkingSlot(SlotStatus status, int spotId,Floor floor) {
        this.status = status;
        this.spotId = spotId;
        this.floor = floor;
    }

    synchronized boolean  occupieSlot(){

        this.status = SlotStatus.BUSY;
        return true;

    }
    synchronized boolean freeSlot(){
        this.status = SlotStatus.FREE;
        return true;
    }

}
