package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedTicketException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<>();
    private static final int DEFAULT_CAPACITY = 10;

    private int capacity;

    public ParkingLot(){
        this.capacity = DEFAULT_CAPACITY;
    }

    private boolean isCapacityFull(){
        return parkingTicketCarMap.size() == capacity;
    }
    public ParkingTicket park(Car car) {
        if(isCapacityFull()){
            throw new NoAvailablePositionException();
        }
        ParkingTicket ticket = new ParkingTicket();
        parkingTicketCarMap.put(ticket, car);
        return ticket;
    }

    public Car fetch(ParkingTicket ticket) {
        Car car = parkingTicketCarMap.remove(ticket);
        if(car == null){
            throw new UnrecognizedTicketException();
        }
        return car;
    }

    public int getAvailableCapacity() {
        return capacity - parkingTicketCarMap.size();
    }

    public boolean hasAvailableCapacity() {
        return !isCapacityFull();
    }

    public double getPositionRate() {
        return (double) parkingTicketCarMap.size() / getCapacity();
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
