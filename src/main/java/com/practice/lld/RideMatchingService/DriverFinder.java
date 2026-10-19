package com.practice.lld.RideMatchingService;

import java.util.List;

public interface DriverFinder {
    List<Driver> findDriver(Ride ride);
}
