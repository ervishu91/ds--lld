package com.practice.lld.parkinglot;

public class HourlyparkingPricing  implements  PricingStrategy{

    double  HOURLY_RATE = 40.0;
    @Override
    public double calculatePrice(Ticket ticket) {
        double hour = (double) (System.currentTimeMillis() - ticket.entryTime) /1_000;

        return hour*HOURLY_RATE;
    }
}
