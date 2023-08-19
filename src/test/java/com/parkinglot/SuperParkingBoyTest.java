package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SuperParkingBoyTest {
    ParkingLot firstParkingLot = new ParkingLot();
    ParkingLot secondParkingLot = new ParkingLot();
    List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
    SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
    @Test
    void should_park_to_first_parking_lot_when_park_given_super_parking_boy_and_two_parking_lots_and_car() {
        //Given
        Car car = new Car();
        //When
        ParkingTicket parkingTicket = superParkingBoy.park(car);
        //Then
        assertNotNull(parkingTicket);
        assertEquals(9, firstParkingLot.getAvailableCapacity());
        assertEquals(20, secondParkingLot.getAvailableCapacity());
    }
}
