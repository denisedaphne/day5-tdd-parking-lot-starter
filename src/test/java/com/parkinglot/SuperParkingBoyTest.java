package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    
}
