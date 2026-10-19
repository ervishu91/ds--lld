package com.practice.lld.NotificatonSystem;

import java.util.Map;

public class NotificationFactory {

    Map<NotificationType,NotificationStrategy> typeMap;

    public NotificationFactory(Map<NotificationType, NotificationStrategy> typeMap) {
        this.typeMap = typeMap;
    }

    NotificationStrategy getNotificationType(NotificationType type) throws Exception {
        NotificationStrategy strat  = typeMap.get(type);
        if(strat==null)
            throw new Exception("type not found");
        return strat;
    }
}
