package com.parkinglot.exception;

public class UnrecognizedTicketException extends RuntimeException{
    public UnrecognizedTicketException(){
        super("Unrecognized parking ticket");
    }
}
