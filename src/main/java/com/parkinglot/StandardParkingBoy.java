package com.parkinglot;

import java.util.List;

public class StandardParkingBoy {
    private final List<ParkingLot> parkingLots;

    public StandardParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        return parkingLots.stream()
                .filter(ParkingLot::hasAvailableCapacity)
                .findFirst()
                .orElseThrow(NoAvailablePositionException::new)
                .parkCar(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLots.stream()
                .findFirst()
                .orElseThrow(UnrecognizedTicketException::new)
                .fetchCar(parkingTicket);
    }
}
