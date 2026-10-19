package com.practice.lld.parkinglot;

import java.util.List;
import java.util.Map;

public class Floor {
    int floorNo;
    Map<SlotType, List<ParkingSlot>> availableSlot;

    public Floor(int floorNo, Map<SlotType, List<ParkingSlot>> availableSlot) {
        this.floorNo = floorNo;
        this.availableSlot = availableSlot;
    }
}
