package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Map<ParkingTicket, Car> parkingMap;
    private final int capacity;

    public ParkingLot(int capacity){
        this.capacity = capacity;
        parkingMap = new HashMap<>();

    }
    public ParkingTicket parkCar(Car car) {
        if(parkingMap.size() >= capacity){
            return null;
        }
        ParkingTicket ticket = new ParkingTicket();
        parkingMap.put(ticket, car);
        return ticket;
    }

    public Car fetchCar(ParkingTicket ticket) {
        Car car = parkingMap.get(ticket);
        if(car != null){
            parkingMap.remove(ticket);
            return car;
        }
        return null;
    }
}
