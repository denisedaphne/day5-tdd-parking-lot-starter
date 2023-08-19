package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.parkinglot.exception.UnrecognizedTicketException;
import com.parkinglot.exception.NoAvailablePositionException;

import static org.junit.jupiter.api.Assertions.*;


public class SuperParkingBoyTest {
    ParkingLot firstParkingLot = new ParkingLot();
    ParkingLot secondParkingLot = new ParkingLot();
    {
        secondParkingLot.setCapacity(20);
    }

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

    @Test
    void should_park_to_second_parking_lot_when_park_given_super_parking_boy_and_two_parking_lots_first_parking_lot_is_full_and_car() {
        //Given
        List<Car> cars = IntStream.range(0, 10)
                .mapToObj(i -> new Car())
                .collect(Collectors.toList());

        cars.forEach(firstParkingLot::parkCar);
        //When
        ParkingTicket parkingTicket = superParkingBoy.park(new Car());
        //Then
        assertNotNull(parkingTicket);
        assertEquals(0, firstParkingLot.getAvailableCapacity());
        assertEquals(19, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_park_to_second_parking_lot_when_park_given_super_parking_boy_and_second_parking_lot_has_larger_position_rate_and_car() {
        //Given
        List<Car> cars = IntStream.range(0, 5)
                .mapToObj(i -> new Car())
                .collect(Collectors.toList());

        List<Car> cars2 = IntStream.range(0, 14)
                .mapToObj(i -> new Car())
                .collect(Collectors.toList());

        cars.forEach(firstParkingLot::parkCar);
        cars2.forEach(secondParkingLot::parkCar);
        //When
        ParkingTicket parkingTicket = superParkingBoy.park(new Car());
        //Then
        assertNotNull(parkingTicket);
        assertEquals(5, firstParkingLot.getAvailableCapacity());
        assertEquals(5, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_park_to_first_parking_lot_when_park_given_super_parking_boy_and_first_parking_lot_has_larger_position_rate_and_car() {
        //Given
        List<Car> cars = IntStream.range(0, 5)
                .mapToObj(i -> new Car())
                .collect(Collectors.toList());

        List<Car> cars2 = IntStream.range(0, 8)
                .mapToObj(i -> new Car())
                .collect(Collectors.toList());

        cars.forEach(firstParkingLot::parkCar);
        cars2.forEach(secondParkingLot::parkCar);
        //When
        ParkingTicket parkingTicket = superParkingBoy.park(new Car());
        //Then
        assertNotNull(parkingTicket);
        assertEquals(4, firstParkingLot.getAvailableCapacity());
        assertEquals(12, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_return_the_right_car_for_each_ticket_when_fetch_car_given_super_parking_boy_and_two_parking_lots_and_two_parking_tickets() {
        //Given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingTicket parkingTicket1 = superParkingBoy.park(car1);
        ParkingTicket parkingTicket2 = superParkingBoy.park(car2);
        //When
        Car fetchedCar1 = superParkingBoy.fetch(parkingTicket1);
        Car fetchedCar2 = superParkingBoy.fetch(parkingTicket2);
        //Then
        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }

    @Test
    void should_return_error_message_when_fetch_car_given_super_parking_boy_and_two_parking_lots_and_unrecognized_ticket() {
        //Given
        ParkingTicket unrecognizedTicket = new ParkingTicket();
        //When
        //Then
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class, () -> superParkingBoy.fetch(unrecognizedTicket));
        assertEquals("Unrecognized parking ticket", exception.getMessage());
    }

    @Test
    void should_return_error_message_when_fetch_car_given_super_parking_boy_and_two_parking_lots_and_used_ticket() {
        //Given
        Car car = new Car();
        ParkingTicket ticket = superParkingBoy.park(car);
        superParkingBoy.fetch(ticket);
        //When
        //Then
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class, () -> {
            superParkingBoy.fetch(ticket);
        });
        assertEquals("Unrecognized parking ticket", exception.getMessage());
    }

    @Test
    void should_return_error_message_when_park_car_given_super_parking_boy_and_two_full_parking_lots_and_car() {
        //Given
        List<Car> cars = IntStream.range(0, 10)
                .mapToObj(i -> new Car())
                .collect(Collectors.toList());

        List<Car> cars2 = IntStream.range(0, 20)
                .mapToObj(i -> new Car())
                .collect(Collectors.toList());

        cars.forEach(firstParkingLot::parkCar);
        cars2.forEach(secondParkingLot::parkCar);
        //When
        NoAvailablePositionException exception = assertThrows(NoAvailablePositionException.class, () -> {
            superParkingBoy.park(new Car());
        });
        //Then
        assertEquals("No available position", exception.getMessage());
    }
}
