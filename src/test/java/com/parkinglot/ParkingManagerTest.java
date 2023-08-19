package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


public class ParkingManagerTest {

    @BeforeEach
    public void setUp() {
        ParkingManager parkingManager = new ParkingManager();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingManager.addOwnedParkingLot(parkingLot1);
        parkingManager.addOwnedParkingLot(parkingLot2);
    }

    @Test
    public void should_return_true_when_added_parking_boy_given_parking_manager_management_list() {
        ParkingBoy parkingBoy1 = new StandardParkingBoy(new ArrayList<>());
        ParkingBoy parkingBoy2 = new SmartParkingBoy(new ArrayList<>());

        ParkingManager.addParkingBoy(parkingBoy1);
        ParkingManager.addParkingBoy(parkingBoy2);

        assertTrue(ParkingManager.addParkingBoy(parkingBoy1));
        assertTrue(ParkingManager.addParkingBoy(parkingBoy2));
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

    @Test
    void should_return_ticket_when_fetch_given_parking_manager_in_owned_parking_lot() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingBoy parkingBoy = new StandardParkingBoy(Arrays.asList(parkingLot1, parkingLot2));
        ParkingManager.addParkingBoy(parkingBoy);

        Car car = new Car();
        ParkingTicket ticket = ParkingManager.parkCar(car, parkingBoy);

        assertNotNull(ticket);
    }

}
