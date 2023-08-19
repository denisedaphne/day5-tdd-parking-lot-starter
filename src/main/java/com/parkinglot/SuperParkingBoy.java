package com.parkinglot;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import com.parkinglot.exception.UnrecognizedTicketException;

public class SuperParkingBoy {
    private final List<ParkingLot> parkingLots;
    public SuperParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        return parkingLots.stream()
                .filter(ParkingLot::hasAvailableCapacity)
                .max(Comparator.comparingDouble(ParkingLot::getPosition))
                .stream().findFirst()
                .orElseThrow()
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
                .orElseThrow(UnrecognizedTicketException::new);
    }
}
