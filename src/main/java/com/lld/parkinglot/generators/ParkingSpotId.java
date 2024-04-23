package com.lld.parkinglot.generators;

import java.util.concurrent.atomic.AtomicLong;

public class ParkingSpotId {

    public static AtomicLong idCounter = new AtomicLong();

    public static Long getIdCounter() {
        return idCounter.getAndIncrement();
    }
}
