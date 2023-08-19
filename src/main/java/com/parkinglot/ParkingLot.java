package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedTicketException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Map<ParkingTicket, Car> parkingMap;
    private static int CAPACITY = 10;

    private int capacity;

    public ParkingLot(){
        parkingMap = new HashMap<>();
        this.capacity = CAPACITY;
    }

    public ParkingLot(int capacity){
        parkingMap = new HashMap<>();
        CAPACITY = capacity;
    }

    private boolean isFull(){
        return parkingMap.size() == capacity;
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

    public int getAvailableCapacity() {
        return capacity - parkingMap.size();
    }

    public boolean hasAvailableCapacity() {
        return !isFull();
    }

    public double getPosition() {
        return (double) parkingMap.size() / getCapacity();
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
