package com.practice.lld.Logger;

public enum Loglevel {
    DEBUG(0),
    INFO(1),
    WARN(2),
    ERROR(3),
    FATAL(4);

    private final int level;

    Loglevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}