package com.practice.lld.VendingMachine;

public interface VendingState {
    VendingMachine  machine = null;
    void paymentState();
    void productSelectState();
    void dispense();
    void cancel();

}
