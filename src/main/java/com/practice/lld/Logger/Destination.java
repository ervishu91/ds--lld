package com.practice.lld.Logger;

public class Destination {

    Loglevel minLevel;
    Sink sink;
    Formatter format;

    public Destination(Loglevel minLevel, Sink sink, Formatter format) {
        this.minLevel = minLevel;
        this.sink = sink;
        this.format = format;
    }
}
