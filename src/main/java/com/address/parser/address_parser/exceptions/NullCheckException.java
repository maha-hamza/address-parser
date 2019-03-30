package com.address.parser.address_parser.exceptions;

import java.util.UUID;

public class NullCheckException extends RuntimeException {


    public NullCheckException() {
        super(String.format("Address Can't be null or empty"));
    }
}
