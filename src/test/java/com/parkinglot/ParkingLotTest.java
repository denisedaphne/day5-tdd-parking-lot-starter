package com.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingLotTest {

    @Test
    void should_return_the_car_when_fetch_given_parking_lot_a_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

         //when
        ParkingTicket parkingTicket = parkingLot.parkCar(car);

         //then
        Assertions.assertNotNull(parkingTicket);
    }

    @Test
    void should_return_parked_car_with_ticket_when_fetch_given_parking_lot() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        //when
        Car fetchCar = parkingLot.fetchCar(parkingTicket);

        //then
        Assertions.assertEquals(car, fetchCar);
    }
    
    @Test
    void should_return_parked_car_with_ticket_when_fetched_given_two_cars() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingTicket parkingTicket1 = parkingLot.parkCar(car1);
        ParkingTicket parkingTicket2 = parkingLot.parkCar(car2);
         //when

        Car fetchCar1 = parkingLot.fetchCar(parkingTicket1);
        Car fetchCar2 = parkingLot.fetchCar(parkingTicket2);
         //then
        Assertions.assertEquals(car1, fetchCar1);
        Assertions.assertEquals(car2, fetchCar2);
    }
    @Test
    void should_return_nothing_when_fetch_given_parking_lot_wrong_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket wrongTicket = new ParkingTicket();
         //when
        Car fetchCar = parkingLot.fetchCar(wrongTicket);
         
         //then
        Assertions.assertNull(fetchCar);
    }
    
    @Test
    void should_return_nothing_when_fetch_given_parking_lot_used_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.parkCar(car);
         //when
        Car fetchCar = parkingLot.fetchCar(parkingTicket);
         //then
        Assertions.assertNull(fetchCar);
    }

}
