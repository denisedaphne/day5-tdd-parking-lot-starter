package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Map<ParkingTicket, Car> parkingMap;
    private static int CAPACITY = 10;

    public ParkingLot(){
        parkingMap = new HashMap<>();
    }
    public ParkingLot(int capacity){
        parkingMap = new HashMap<>();
        CAPACITY = capacity;
    }

    private boolean isFull(){
        return parkingMap.size() == CAPACITY;
    }
    public ParkingTicket parkCar(Car car) {
        if(isFull()){
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
