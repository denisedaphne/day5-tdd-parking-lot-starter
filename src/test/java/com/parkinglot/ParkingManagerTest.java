package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingManagerTest {
    private StandardParkingBoy standardParkingBoy;

    @BeforeEach
    public void setUp() {
        ParkingManager parkingManager = new ParkingManager();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(parkingLot1));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLot2));
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(List.of(parkingLot1, parkingLot2));
        parkingManager.addParkingBoy(standardParkingBoy);
        parkingManager.addParkingBoy(smartParkingBoy);
        parkingManager.addParkingBoy(superParkingBoy);
    }

    @Test
    void should_return_car_when_park_or_fetch_given_parking_manager_use_parking_boy_with_first_available_parking_lots() {
        Car car = new Car();
        ParkingTicket ticket = ParkingManager.parkCar(car, ParkingManager.getFirstAvailableParkingBoy());
        assertNotNull(ticket);

        Car fetchedCar = ParkingManager.fetchCar(ticket, ParkingManager.getFirstAvailableParkingBoy());
        assertEquals(car, fetchedCar);
    }


}
