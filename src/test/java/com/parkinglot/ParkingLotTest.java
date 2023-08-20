package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ParkingLotTest {
    ParkingLot parkingLot = new ParkingLot();
    Car car = new Car();

    @Test
    void should_return_the_ticket_when_fetch_given_parking_lot_a_car() {
        //when
        ParkingTicket parkingTicket = parkingLot.park(car);
        //then
        assertNotNull(parkingTicket);
    }

    @Test
    void should_return_parked_car_when_fetch_given_parking_lot_with_ticket() {
        //when
        ParkingTicket parkingTicket = parkingLot.park(car);
        Car fetchCar = parkingLot.fetch(parkingTicket);
        //then
        assertEquals(car, fetchCar);
    }
    
    @Test
    void should_return_two_parked_cars_with_tickets_when_fetch_twice_given_parking_lot_two_cars_and_tickets() {
        //given
        Car car2 = new Car();
        //when
        ParkingTicket parkingTicket1 = parkingLot.park(car);
        ParkingTicket parkingTicket2 = parkingLot.park(car2);
        Car fetchCar1 = parkingLot.fetch(parkingTicket1);
        Car fetchCar2 = parkingLot.fetch(parkingTicket2);
        //then
        assertEquals(car, fetchCar1);
        assertEquals(car2, fetchCar2);
    }
    @Test
    void should_return_error_message_when_fetch_given_parking_lot_wrong_ticket() {
        //given
        ParkingTicket wrongTicket = new ParkingTicket();
         //when
        //then
        UnrecognizedTicketException exception = Assertions.assertThrows(UnrecognizedTicketException.class, () -> parkingLot.fetch(wrongTicket));
        assertEquals("Unrecognized parking ticket", exception.getMessage());
    }
    
    @Test
    void should_return_error_message_when_fetch_given_parking_lot_used_ticket() {
        //given
        Car car = new Car();
         //when
        ParkingTicket parkingTicket = parkingLot.park(car);
        parkingLot.fetch(parkingTicket);
        //then
        UnrecognizedTicketException exception = Assertions.assertThrows(UnrecognizedTicketException.class, () -> parkingLot.fetch(parkingTicket));
        assertEquals("Unrecognized parking ticket", exception.getMessage());

    }
    
    @Test
    void should_return_error_message_when_park_given_parking_lot_no_position() {
        //given
        List<Car> cars = IntStream.range(0, 10)
                .mapToObj(i -> new Car())
                .collect(Collectors.toList());

        cars.forEach(parkingLot::park);
        //when
        //then
        NoAvailablePositionException noAvailablePositionException = Assertions.assertThrows(NoAvailablePositionException.class, () -> parkingLot.park(new Car()));
        assertEquals("No available position", noAvailablePositionException.getMessage());
    }
}
