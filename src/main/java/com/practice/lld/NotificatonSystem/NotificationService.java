package com.practice.lld.NotificatonSystem;

public class NotificationService {

    NotificationFactory factory;


    boolean sendNotification(Notification notification){
        for(NotificationType type : notification.channels){
            try {
                factory.getNotificationType(type).send(notification);
            } catch (Exception e) {
                System.out.println(type+"not found");
            }

        }

        return true;

    }
}
