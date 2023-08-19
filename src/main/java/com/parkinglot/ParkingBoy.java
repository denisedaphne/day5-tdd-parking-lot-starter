package com.parkinglot;

import java.util.List;

public abstract class ParkingBoy {
    protected final List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public abstract ParkingTicket park(Car car);

    public abstract Car fetch(ParkingTicket parkingTicket);

    public boolean hasAvailableCapacity() {
        return parkingLots.stream().anyMatch(ParkingLot::hasAvailableCapacity);
    }
}
