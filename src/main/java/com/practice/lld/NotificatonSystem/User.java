package com.practice.lld.NotificatonSystem;

public class User {
    String name;
    String email;
    String mob;
    String fcmToken;

    public User(String name, String email, String mob, String fcmToken) {
        this.name = name;
        this.email = email;
        this.mob = mob;
        this.fcmToken = fcmToken;
    }
}
