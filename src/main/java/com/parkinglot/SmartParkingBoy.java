package com.parkinglot;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedTicketException;

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

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLots.stream()
                .flatMap(parkingLot -> {
                    try {
                        return Stream.of(parkingLot.fetchCar(parkingTicket));
                    } catch (UnrecognizedTicketException ignored) {
                        return Stream.empty();
                    }
                })
                .findFirst()
                .orElseThrow();
    }
}
