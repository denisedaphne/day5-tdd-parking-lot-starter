package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;

import java.util.ArrayList;
import java.util.List;

public class ParkingManager {
    private static List<ParkingBoy> parkingBoys;
    public final List<ParkingLot> ownedParkingLots;

    public ParkingManager() {
        parkingBoys = new ArrayList<>();
        this.ownedParkingLots = new ArrayList<>();
    }

    public static boolean addParkingBoy(ParkingBoy parkingBoy) {
        parkingBoys.add(parkingBoy);
        return true;
    }

    public static ParkingTicket parkCar(Car car, ParkingBoy parkingBoy) {
        return parkingBoy.park(car);
    }

    public void addOwnedParkingLot(ParkingLot parkingLot) {
        ownedParkingLots.add(parkingLot);
    }

    public static Car fetchCar(ParkingTicket ticket, ParkingBoy parkingBoy) {
        return parkingBoy.fetch(ticket);
    }

    public static ParkingBoy getFirstAvailableParkingBoy() {
        return parkingBoys.stream()
                .filter(ParkingBoy::hasAvailableCapacity)
                .findFirst()
                .orElseThrow(NoAvailablePositionException::new);
    }
}
