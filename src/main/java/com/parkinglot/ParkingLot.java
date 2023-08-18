package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<ParkingTicket, Car> parkingMap;

    public ParkingLot(){
        parkingMap = new HashMap<>();

    }
    public ParkingTicket parkCar(Car car) {
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
