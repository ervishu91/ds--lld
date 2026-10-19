package com.practice.lld.NotificatonSystem;

public class EmailSender implements NotificationStrategy{

    @Override
    public boolean send(Notification notification) {
        System.out.println("email sent to user"+ notification.user.name+"on email:"+notification.user.email);
        return true;
    }
}
