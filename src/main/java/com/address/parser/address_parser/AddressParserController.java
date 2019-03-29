package com.address.parser.address_parser;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("${app.base-api}/address-parser")
@RequiredArgsConstructor
public class AddressParserController {

    private final AddressParserService service;

    @PostMapping
    public ParserResponse parseAddress(@RequestBody @Valid ParserRequest addressRequest) {
        return ParserResponse.builder()
                .address(service.parseAddress(addressRequest.getAddress()))
                .build();
    }
}
