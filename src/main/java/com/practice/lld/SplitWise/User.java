package com.practice.lld.SplitWise;

import java.util.Map;

public class User {

    String name;
    String email;
    String id;
    UserLedger userLedger;
    Map<User, Integer> balancePending;

    public User(String name, String email, String id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    void addTransactionToledger(){

    }
}
