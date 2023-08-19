package com.parkinglot;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedTicketException;

public class SuperParkingBoy extends ParkingBoy{
    public SuperParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public ParkingTicket park(Car car) {
        return parkingLots.stream()
                .filter(ParkingLot::hasAvailableCapacity)
                .max(Comparator.comparingDouble(ParkingLot::getPositionRate))
                .stream().findFirst()
                .orElseThrow(NoAvailablePositionException::new)
                .park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLots.stream()
                .flatMap(parkingLot -> {
                    try {
                        return Stream.of(parkingLot.fetch(parkingTicket));
                    } catch (UnrecognizedTicketException ignored) {
                        return Stream.empty();
                    }
                })
                .findFirst()
                .orElseThrow(UnrecognizedTicketException::new);
    }
}
