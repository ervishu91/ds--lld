package com.practice.lld.parkinglot;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/** Small starting point for a parking-lot low-level design exercise. */
public final class ParkingLot {
    private final int capacity;
    private final Map<String, Vehicle> parkedVehicles = new LinkedHashMap<>();

    public ParkingLot(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = capacity;
    }

    public boolean park(Vehicle vehicle) {
        if (parkedVehicles.size() == capacity || parkedVehicles.containsKey(vehicle.registrationNumber())) {
            return false;
        }
        parkedVehicles.put(vehicle.registrationNumber(), vehicle);
        return true;
    }

    public Optional<Vehicle> unpark(String registrationNumber) {
        return Optional.ofNullable(parkedVehicles.remove(registrationNumber));
    }

    public int availableSpaces() {
        return capacity - parkedVehicles.size();
    }
}
