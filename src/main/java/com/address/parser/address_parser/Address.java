package com.address.parser.address_parser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    private String street;
    private String houseNumber;

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                '}';
    }
}
