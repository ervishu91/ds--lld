package com.practice.lld.parkinglot;

/** Entry point for the parking-lot design exercise. */
public final class ParkingLotDemo {
    private ParkingLotDemo() {
    }

    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(2);
        Vehicle car = new Vehicle("KA-01-AB-1234", VehicleType.CAR);

        System.out.println("Parked: " + parkingLot.park(car));
        System.out.println("Available spaces: " + parkingLot.availableSpaces());
        System.out.println("Unparked: " + parkingLot.unpark(car.registrationNumber()).isPresent());
    }
}
