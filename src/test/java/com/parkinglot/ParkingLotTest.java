package com.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ParkingLotTest {

    @Test
    void should_return_the_ticket_when_fetch_given_parking_lot_a_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
         //when
        ParkingTicket parkingTicket = parkingLot.parkCar(car);
         //then
        assertNotNull(parkingTicket);
    }

    @Test
    void should_return_parked_car_when_fetch_given_parking_lot_with_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        Car fetchCar = parkingLot.fetchCar(parkingTicket);
        //then
        assertEquals(car, fetchCar);
    }
    
    @Test
    void should_return_two_parked_cars_with_tickets_when_fetch_twice_given_parking_lot_two_cars_and_tickets() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car1 = new Car();
        Car car2 = new Car();
         //when
        ParkingTicket parkingTicket1 = parkingLot.parkCar(car1);
        ParkingTicket parkingTicket2 = parkingLot.parkCar(car2);
        Car fetchCar1 = parkingLot.fetchCar(parkingTicket1);
        Car fetchCar2 = parkingLot.fetchCar(parkingTicket2);
         //then
        assertEquals(car1, fetchCar1);
        assertEquals(car2, fetchCar2);
    }
    @Test
    void should_return_nothing_when_fetch_given_parking_lot_wrong_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket wrongTicket = new ParkingTicket();
         //when
        //then
        UnrecognizedTicketException exception = Assertions.assertThrows(UnrecognizedTicketException.class, () -> parkingLot.fetchCar(wrongTicket));
        assertEquals("Unrecognized parking ticket", exception.getMessage());
    }
    
    @Test
    void should_return_nothing_when_fetch_given_parking_lot_used_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
         //when
        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        parkingLot.fetchCar(parkingTicket);
        //then
        UnrecognizedTicketException exception = Assertions.assertThrows(UnrecognizedTicketException.class, () -> parkingLot.fetchCar(parkingTicket));
        assertEquals("Unrecognized parking ticket", exception.getMessage());

    }
    
    @Test
    void should_return_nothing_when_park_given_parking_lot_no_position() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        //when
        //then
        assertNotNull(parkingTicket);
        NoAvailablePositionException noAvailablePositionException = Assertions.assertThrows(NoAvailablePositionException.class, () -> parkingLot.parkCar(new Car()));
        assertEquals("No available position", noAvailablePositionException.getMessage());
    }
}
