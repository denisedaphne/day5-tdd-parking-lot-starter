package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;

public class ParkingLotTest {

    @Test
    void should_return_the_car_when_fetch_given_parking_lot_a_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

         //when
        ParkingTicket parkingTicket = parkingLot.parkCar(car);

         //then
        assertNotNull(parkingTicket);
    }

}
