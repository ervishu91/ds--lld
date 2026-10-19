package com.practice.lld.NotificatonSystem;

import java.util.List;

public class Notification {
    User user;
    String message;
    List<NotificationType> channels;

    public Notification(User user, String message, List<NotificationType> channels) {
        this.user = user;
        this.message = message;
        this.channels = channels;
    }
}
