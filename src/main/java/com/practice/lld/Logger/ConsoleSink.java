package com.practice.lld.Logger;

public class ConsoleSink implements  Sink{
    @Override
    synchronized public void sink(String message) {
        System.out.println(message);

    }
}
