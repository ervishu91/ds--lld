package com.practice.lld.parkinglot;

import java.util.*;

public class Parking {

    Map<Floor, Map<SlotType, List<ParkingSlot>>> occupiedSlot;

    List<Floor> floors;

    PricingStrategy pricingStr;
    SlotSearchStrategy searchStr;

    public Parking(SlotSearchStrategy searchStr, PricingStrategy pricingStr, List<Floor> floors) {
        this.searchStr = searchStr;
        this.pricingStr = pricingStr;
        this.floors = floors;

    }

    Ticket parkVehicle(String regNo, VehicleType vehType, ParkingSlot type){
        ParkingSlot slot = searchStr.findSlot(floors,type);
        if(slot!=null){
            slot.occupieSlot();
            return getTicket(regNo, vehType, slot);
        }
        occupiedSlot.computeIfAbsent(slot.floor,floorMap-> new HashMap<>()).computeIfAbsent(slot.type,slotList-> new ArrayList<>()).add(slot);
        return null;
    }

    private Ticket getTicket(String regNo, VehicleType type, ParkingSlot slot) {
        return new Ticket(UUID.randomUUID().toString(),regNo, type, slot, System.currentTimeMillis());
    }

    double exitVehicle(Ticket ticket){
        double amount = pricingStr.calculatePrice(ticket);
        ParkingSlot slot = ticket.slot;
        occupiedSlot.get(slot.floor).get(slot.type).remove(slot);
       ticket.slot.freeSlot();
       return amount;
    }

    boolean addParkingSlot(int floor, ParkingSlot slot){
        return true;

    }
}
