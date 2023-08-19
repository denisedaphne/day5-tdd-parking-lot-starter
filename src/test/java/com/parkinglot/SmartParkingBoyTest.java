package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.parkinglot.exception.UnrecognizedTicketException;
import com.parkinglot.exception.NoAvailablePositionException;

import static org.junit.jupiter.api.Assertions.*;

public class SmartParkingBoyTest {
    ParkingLot firstParkingLot = new ParkingLot();
    ParkingLot secondParkingLot = new ParkingLot();
    List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
    @Test
    void should_park_to_first_parking_lot_when_park_given_smart_parking_boy_and_two_parking_lots_and_car() {
        //Given
        Car car = new Car();
        //When
        ParkingTicket parkingTicket = smartParkingBoy.park(car);
        //Then
        assertNotNull(parkingTicket);
        assertEquals(9, firstParkingLot.getAvailableCapacity());
        assertEquals(10, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_park_to_second_parking_lot_when_park_given_smart_parking_boy_and_two_parking_lots_first_parking_lot_is_full_and_car() {
        //Given
        List<Car> cars = IntStream.range(0, 10)
                .mapToObj(i -> new Car())
                .collect(Collectors.toList());

        cars.forEach(car -> firstParkingLot.parkCar(car));

        //When
        ParkingTicket parkingTicket = smartParkingBoy.park(new Car());
        //Then
        assertNotNull(parkingTicket);
        assertEquals(0, firstParkingLot.getAvailableCapacity());
        assertEquals(9, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_park_to_second_parking_lot_when_park_given_smart_parking_boy_and_second_parking_lot_has_more_positions_and_car() {
        //Given
        List<Car> cars = IntStream.range(0, 5)
                .mapToObj(i -> new Car())
                .collect(Collectors.toList());

        cars.forEach(car -> firstParkingLot.parkCar(car));

        //When
        ParkingTicket parkingTicket = smartParkingBoy.park(new Car());
        //Then
        assertNotNull(parkingTicket);
        assertEquals(5, firstParkingLot.getAvailableCapacity());
        assertEquals(9, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_park_to_first_parking_lot_when_park_given_smart_parking_boy_and_first_parking_lot_has_more_positions_and_car() {
        //Given
        List<Car> cars = IntStream.range(0, 5)
                .mapToObj(i -> new Car())
                .collect(Collectors.toList());

        cars.forEach(secondParkingLot::parkCar);
        //When
        ParkingTicket parkingTicket = smartParkingBoy.park(new Car());
        //Then
        assertNotNull(parkingTicket);
        assertEquals(9, firstParkingLot.getAvailableCapacity());
        assertEquals(5, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_park_to_first_parking_lot_when_park_given_smart_parking_boy_and_both_parking_lots_has_equal_positions_and_car() {
        //Given
        List<Car> cars = IntStream.range(0, 5)
                .mapToObj(i -> new Car())
                .collect(Collectors.toList());

        cars.forEach(car -> {
            firstParkingLot.parkCar(car);
            secondParkingLot.parkCar(car);
        });

        //When
        ParkingTicket parkingTicket = smartParkingBoy.park(new Car());
        //Then
        assertNotNull(parkingTicket);
        assertEquals(4, firstParkingLot.getAvailableCapacity());
        assertEquals(5, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_return_the_right_car_for_each_ticket_when_fetch_car_given_smart_parking_boy_and_two_parking_lots_and_two_parking_tickets() {
        //Given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingTicket parkingTicket1 = smartParkingBoy.park(car1);
        ParkingTicket parkingTicket2 = smartParkingBoy.park(car2);
        //When
        Car fetchedCar1 = smartParkingBoy.fetch(parkingTicket1);
        Car fetchedCar2 = smartParkingBoy.fetch(parkingTicket2);
        //Then
        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }

    @Test
    void should_return_error_message_when_fetch_car_given_smart_parking_boy_and_two_parking_lots_and_unrecognized_ticket() {
        //Given
        ParkingTicket unrecognizedTicket = new ParkingTicket();
        //When
        //Then
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class, () -> {
            smartParkingBoy.fetch(unrecognizedTicket);
        });
        assertEquals("Unrecognized parking ticket", exception.getMessage());
    }

    @Test
    void should_return_error_message_when_park_car_given_smart_parking_boy_and_two_full_parking_lots_and_car() {
        //Given
        List<Car> cars = IntStream.range(0, 10)
                .mapToObj(i -> new Car())
                .collect(Collectors.toList());

        cars.forEach(car -> {
            firstParkingLot.parkCar(car);
            secondParkingLot.parkCar(car);
        });
        //When
        NoAvailablePositionException exception = assertThrows(NoAvailablePositionException.class, () -> {
            smartParkingBoy.park(new Car());
        });
        //Then
        assertEquals("No available position", exception.getMessage());
    }
}
