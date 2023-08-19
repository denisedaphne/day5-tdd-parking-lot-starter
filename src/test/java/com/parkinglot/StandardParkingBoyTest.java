package com.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StandardParkingBoyTest {
    ParkingLot firstParkingLot = new ParkingLot();
    ParkingLot secondParkingLot = new ParkingLot();
    List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
    StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
    @Test
    void should_park_to_first_parking_lot_when_park_given_standard_parking_boy_and_two_parking_lots_and_car() {
        //Given
        Car car = new Car();
        //When
        ParkingTicket parkingTicket = standardParkingBoy.park(car);
        //Then
        Assertions.assertNotNull(parkingTicket);
        Assertions.assertEquals(9, firstParkingLot.getAvailableCapacity());
        Assertions.assertEquals(10, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_park_to_second_parking_lot_when_park_given_standard_parking_boy_and_two_parking_lots_first_parking_lot_is_full_and_car() {
        //Given
        List<Car> cars = IntStream.range(0, 10)
                .mapToObj(i -> new Car())
                .collect(Collectors.toList());

        cars.forEach(car -> firstParkingLot.parkCar(car));
        //When
        ParkingTicket parkingTicket = standardParkingBoy.park(new Car());
        //Then
        Assertions.assertNotNull(parkingTicket);
        Assertions.assertEquals(0, firstParkingLot.getAvailableCapacity());
        Assertions.assertEquals(9, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_return_the_right_car_for_each_ticket_when_fetch_car_given_standard_parking_boy_and_two_parking_lots_and_two_parking_tickets() {
        //Given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingTicket parkingTicket1 = standardParkingBoy.park(car1);
        ParkingTicket parkingTicket2 = standardParkingBoy.park(car2);
        //When
        Car fetchedCar1 = standardParkingBoy.fetch(parkingTicket1);
        Car fetchedCar2 = standardParkingBoy.fetch(parkingTicket2);
        //Then
        Assertions.assertEquals(car1, fetchedCar1);
        Assertions.assertEquals(car2, fetchedCar2);
    }
}
