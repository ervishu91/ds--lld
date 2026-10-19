package com.practice.lld.Logger;

public class LogMessage {
    final Loglevel level;
    final String msg;
    final Long timestamp;

    public LogMessage(Loglevel level, String msg) {
        this.level = level;
        this.msg = msg;
        this.timestamp= System.currentTimeMillis();
    }
}
