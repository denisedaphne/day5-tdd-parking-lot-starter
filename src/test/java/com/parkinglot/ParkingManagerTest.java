package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import org.junit.jupiter.api.Assertions;
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

    @Test
    void should_return_car_when_park_given_parking_manager_use_parking_boy_without_available_parking_lots() {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setCapacity(0);

        Car car = new Car();

        ParkingTicket ticket = ParkingManager.parkCar(car, ParkingManager.getFirstAvailableParkingBoy());
        assertNotNull(ticket);

        NoAvailablePositionException noAvailablePositionException = Assertions.assertThrows(NoAvailablePositionException.class, () -> parkingLot.parkCar(new Car()));
        assertEquals("No available position", noAvailablePositionException.getMessage());
    }

}
