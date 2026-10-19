package com.practice.lld.parkinglot;

import java.util.List;

public class FirstSlotAvailableStrategy implements SlotSearchStrategy {
    @Override
    public ParkingSlot findSlot(List<Floor> floors, ParkingSlot type) {
        ParkingSlot slot=null;
        for(Floor floor : floors){

            List<ParkingSlot> slotList= floor.availableSlot.get(type);
            if(slotList!=null || !slotList.isEmpty()){
                 slot = slotList.removeFirst();
                break;

            }
        }
        return slot;
    }
}
