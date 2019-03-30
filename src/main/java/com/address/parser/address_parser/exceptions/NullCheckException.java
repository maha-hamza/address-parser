package com.address.parser.address_parser.exceptions;

/**
 * This class is double checking for service layer , if calling the service through the controller we will not need the null check exception,
 * but considering that the service can be called through whatever other endpoint provided not using the annotation validation
 **/
public class NullCheckException extends RuntimeException {


    public NullCheckException() {
        super(String.format("Address Can't be null or empty"));
    }
}
