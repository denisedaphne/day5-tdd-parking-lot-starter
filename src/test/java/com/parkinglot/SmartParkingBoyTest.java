package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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


}
