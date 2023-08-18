package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    Map<Integer, Car> parkedCars;
    int parkingTicket;

    public ParkingLot(){
        this.parkedCars = new HashMap<>();

    }
    public ParkingTicket parkCar(Car car) {
        return new ParkingTicket();
    }

}
