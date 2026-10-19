package com.practice.lld.Logger;

import java.util.List;

public class LogHandler {

    List<Destination> destinations;

    public LogHandler(List<Destination> destinations) {
        this.destinations = destinations;
    }

    void log(LogMessage msg){
        for(Destination dest : destinations){
            if(dest.minLevel.getLevel() <= msg.level.getLevel()){
                String message = dest.format.format(msg);
                dest.sink.sink(message);
            }else{
                System.out.println("required log level not found");
            }
        }
    }

}
