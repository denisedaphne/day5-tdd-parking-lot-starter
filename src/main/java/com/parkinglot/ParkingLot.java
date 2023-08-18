package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<ParkingTicket, Car> parkingMap;
    Map<Integer, Car> parkedCars;

    public ParkingLot(){
        this.parkedCars = new HashMap<>();
        parkingMap = new HashMap<>();

    }
    public ParkingTicket parkCar(Car car) {
        ParkingTicket ticket = new ParkingTicket();
        parkingMap.put(ticket, car);
        return ticket;
    }

    public Car fetchCar(ParkingTicket ticket) {
        return parkingMap.get(ticket);
    }
}
