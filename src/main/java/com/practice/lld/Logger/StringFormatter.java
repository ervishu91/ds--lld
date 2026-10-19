package com.practice.lld.Logger;

public class StringFormatter implements  Formatter{
    @Override
    public String format(LogMessage msg) {
        return msg.msg;
    }
}
