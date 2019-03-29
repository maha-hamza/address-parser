package com.address.parser.address_parser;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class AddressParserService {

    public static final String SPECIAL_CHARACTERS_RGX = "[;\\/:,\\/.*?\"\"<>|&']";
    public static final String ALPHABETIC_AND_SPECIAL_CHAR_RGX = "^[a-zA-Z]\\p{L}*$";
    public static final String ALPHANUMERIC_AND_SPECIAL_CHAR_RGX = "^([A-Za-z0-9\\u00C0-\\u00D6\\u00D8-\\u00f6\\u00f8-\\u00ff\\s]*)$";

    public Address parseAddress(String address) {
        var add = hasSpecialKeyWords(address);
        if (add != null) {
            return add;
        }

        var street = "";
        var houseNumber = "";

        var addressTokens = address.replaceAll(SPECIAL_CHARACTERS_RGX, "").split("\\s");

        for (var token : addressTokens)
            if (isAlphabetic(token)) {
                street = street.concat(token + " ");
            } else if (isAlphaNumericOrNumeric(token)) {
                houseNumber = houseNumber.concat(token + " ");
            }

        return Address.builder()
                .street(street.trim())
                .houseNumber(houseNumber.trim())
                .build();
    }

    private boolean isAlphabetic(String token) {
        return token != null && token.length() > 1 && token.matches(ALPHABETIC_AND_SPECIAL_CHAR_RGX);
    }

    private boolean isAlphaNumericOrNumeric(String token) {
        return token != null && ((token.matches(ALPHANUMERIC_AND_SPECIAL_CHAR_RGX)));
    }

    private Address hasSpecialKeyWords(String address) {
        var tokens = List.of(address.split("\\s"));

        int keywordPosition = IntStream.range(0, tokens.size())
                .filter(i -> "Number".equalsIgnoreCase(tokens.get(i)) || "Num".equalsIgnoreCase(tokens.get(i)) || "no".equalsIgnoreCase(tokens.get(i)))
                .findFirst()
                .orElse(-1);

        if (keywordPosition == 0) {
            if (tokens.size() > 4 && tokens.get(2).length() == 1) {

                return Address.builder()
                        .street(String.join(" ",
                                tokens.subList(3, tokens.size())))
                        .houseNumber(String.join(" ", tokens.subList(0, 3)))
                        .build();
            }
            return Address.builder().street(String.join(" ",
                    tokens.subList(2, tokens.size()))).houseNumber(String.join(" ", tokens.subList(0, 2))).build();
        }

        return keywordPosition != -1 ?
                Address.builder()
                        .street(String.join(" ", tokens.subList(0, keywordPosition)))
                        .houseNumber(String.join(" ", tokens.subList(keywordPosition, tokens.size())))
                        .build()
                : null;

    }
}
