package com.practice.lld.RideMatchingService;

import java.util.List;

public class DriverMatchingService {
    public Driver assignDriver(Ride ride) {
        List<Driver> driverList = findMatchingDriver(ride.source);
        Driver driver = tryAssigningDriver(driverList);
        return driver;
    }

    private List<Driver> findMatchingDriver(Location source) {
        return null;
    }

    private Driver tryAssigningDriver(List<Driver> driverList) {
        return null;
    }
}
