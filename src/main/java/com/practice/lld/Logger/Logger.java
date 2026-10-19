package com.practice.lld.Logger;

import java.util.ArrayList;
import java.util.List;

public class Logger {

    LogHandler handler;

    void debug(String msg){
        log(Loglevel.DEBUG,msg);
    }
    void info(String msg){
        log(Loglevel.INFO,msg);
    }
    void warn(String msg){
        log(Loglevel.WARN,msg);
    }
    void error(String msg){
        log(Loglevel.ERROR,msg);

    }

    private void log(Loglevel loglevel, String msg) {
       LogMessage message  = new LogMessage(loglevel,msg);
        handler.log(message);
    }

    public Logger(LogHandler handler) {
        this.handler = handler;
    }

    public static void main(String[] args) {


        Destination dest = new Destination(Loglevel.INFO,new ConsoleSink(),new StringFormatter());
        List<Destination> destList = new ArrayList<>();
        destList.add(dest);
        Logger logger= new Logger(new LogHandler(destList));


        logger.debug("this log in test");
    }
}
