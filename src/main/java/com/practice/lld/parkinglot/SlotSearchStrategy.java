package com.practice.lld.parkinglot;

import java.util.List;

public interface SlotSearchStrategy {

    ParkingSlot findSlot(List<Floor> floors, ParkingSlot type);
}
