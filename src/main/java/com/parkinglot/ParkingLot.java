package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Map<ParkingTicket, Car> parkingMap;
    private static final int CAPACITY = 10;

    public ParkingLot(){
        parkingMap = new HashMap<>();

    }
    public ParkingTicket parkCar(Car car) {
        if(parkingMap.size() >= CAPACITY){
            throw new NoAvailablePositionException();
        }
        ParkingTicket ticket = new ParkingTicket();
        parkingMap.put(ticket, car);
        return ticket;
    }

    public Car fetchCar(ParkingTicket ticket) {
        Car car = parkingMap.remove(ticket);
        if(car == null){
            throw new UnrecognizedTicketException();
        }
        return car;
    }
}
