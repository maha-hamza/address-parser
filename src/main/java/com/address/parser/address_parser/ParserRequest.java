package com.address.parser.address_parser;

import com.address.parser.address_parser.validation.NullCheckConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParserRequest {
    
    @NullCheckConstraint
    private String address;
}
