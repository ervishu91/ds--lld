package com.practice.lld.parkinglot;

import java.util.UUID;

public class Ticket {

    String ticketId;
    String regNo;
    VehicleType type;
    ParkingSlot slot;
    Long entryTime;

    public Ticket(String ticketId, String regNo, VehicleType type,  ParkingSlot slot, Long entryTime) {
        this.ticketId = ticketId;
        this.regNo = regNo;
        this.type = type;
        this.slot = slot;
        this.entryTime = entryTime;
    }
}
