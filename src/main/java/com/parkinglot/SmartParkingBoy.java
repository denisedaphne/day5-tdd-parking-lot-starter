package com.parkinglot;

import java.util.Comparator;
import java.util.List;

import com.parkinglot.exception.NoAvailablePositionException;

public class SmartParkingBoy {
    private final List<ParkingLot> parkingLots;
    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        return parkingLots.stream()
                .filter(ParkingLot::hasAvailableCapacity)
                .max(Comparator.comparingInt(ParkingLot::getAvailableCapacity))
                .orElseThrow(NoAvailablePositionException::new)
                .parkCar(car);
    }
}
