package com.address.parser.address_parser;

import com.address.parser.address_parser.exceptions.NullCheckException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class AddressParserServiceTest {

    @Autowired
    private AddressParserService service;
    private String address;

    @Test
    public void shouldPassWhenParsingSimpleCaseWithStreetNameAndHouseNumberSuccessfully() throws Exception {
        address = "Winterallee 3";

        Address expected = Address.builder()
                .street("Winterallee")
                .houseNumber("3")
                .build();

        callParserAndValidate(address, expected);

    }

    @Test
    public void shouldPasWhenParsingSimpleCaseWithStreetNameAndHouseNumberWithoutLetterSuccessfully() throws Exception {
        address = "Blaufeldweg 123B";

        Address expected = Address.builder()
                .street("Blaufeldweg")
                .houseNumber("123B")
                .build();

        callParserAndValidate(address, expected);

    }

    @Test
    public void shouldPasWhenParsingSimpleCaseWithStreetNameAndComposedNumberSuccessfully() throws Exception {
        address = "Musterstrasse 4";

        Address expected = Address.builder()
                .street("Musterstrasse")
                .houseNumber("4")
                .build();

        callParserAndValidate(address, expected);
    }

    @Test
    public void shouldPasWhenParsingSimpleCaseWithStreetNameContainSpecialCharactersAndHouseNumberWithoutLetterSuccessfully() throws Exception {
        address = "KüstrinerStraße 30B";

        Address expected = Address.builder()
                .street("KüstrinerStraße")
                .houseNumber("30B")
                .build();

        callParserAndValidate(address, expected);

    }

    @Test
    public void shouldPasWhenParsingSimpleCaseWithStreetNameAndHouseNumberWithLetterContainSpecialCharactersSuccessfully() throws Exception {
        address = "Blaufeldweg 123ä";

        Address expected = Address.builder()
                .street("Blaufeldweg")
                .houseNumber("123ä")
                .build();

        callParserAndValidate(address, expected);

    }

    @Test
    public void shouldPasWhenParsingSimpleCaseWithStreetNameAndHouseNumberWithLetterAndContainSpecialCharactersWithSpacingSuccessfully() throws Exception {
        address = "Blaufeldweg 123 ä";

        Address expected = Address.builder()
                .street("Blaufeldweg")
                .houseNumber("123 ä")
                .build();

        callParserAndValidate(address, expected);

    }

    @Test
    public void shouldPasWhenParsingMoreComplicatedCaseWithStreetNameAndHouseNumberSuccessfully() throws Exception {
        address = "Am Bächle 23";

        Address expected = Address.builder()
                .street("Am Bächle")
                .houseNumber("23")
                .build();

        callParserAndValidate(address, expected);

    }

    @Test
    public void shouldPasWhenParsingMoreComplicatedCaseWithReversedStreetNameAndHouseNumberSuccessfully() throws Exception {
        address = "200 Broadway Av";

        Address expected = Address.builder()
                .street("Broadway Av")
                .houseNumber("200")
                .build();

        callParserAndValidate(address, expected);

    }

    @Test
    public void shouldPasWhenParsingMoreComplicatedCaseWithStreetNameAndHouseNumberContainsLetterSeparatedSuccessfully() throws Exception {
        address = "Auf der Vogelwiese 23 b";

        Address expected = Address.builder()
                .street("Auf der Vogelwiese")
                .houseNumber("23 b")
                .build();

        callParserAndValidate(address, expected);

    }

    @Test
    public void shouldPasWhenParsingMoreComplicatedCaseWithStreetNameAndHouseNumberCommaSeparatedSuccessfully() throws Exception {
        address = "4, rue de la revolution";

        Address expected = Address.builder()
                .street("rue de la revolution")
                .houseNumber("4")
                .build();

        callParserAndValidate(address, expected);

    }

    @Test
    public void shouldPasWhenParsingMoreComplicatedCaseWithStreetNameAndHouseNumberDotSeparatedSuccessfully() throws Exception {
        address = "4. rue de la revolution";

        Address expected = Address.builder()
                .street("rue de la revolution")
                .houseNumber("4")
                .build();

        callParserAndValidate(address, expected);

    }

    @Test
    public void shouldPasWhenParsingMoreComplicatedCaseWithStreetNameAndHouseNumberCommaSeparatedAndLongTextSuccessfully() throws Exception {
        address = "rue de la revolution, 50";

        Address expected = Address.builder()
                .street("rue de la revolution")
                .houseNumber("50")
                .build();

        callParserAndValidate(address, expected);

    }

    @Test
    public void shouldPasWhenParsingMoreComplicatedCaseWithShortStreetNameAndHouseNumberCommaSeparatedSuccessfully() throws Exception {
        address = "Calle Aduana, 29";

        Address expected = Address.builder()
                .street("Calle Aduana")
                .houseNumber("29")
                .build();

        callParserAndValidate(address, expected);

    }

    @Test
    public void shouldPasWhenParsingComplexCaseWithStreetNameAndHouseNumberWithNoCapsKeySuccessfully() throws Exception {
        address = "Calle 39 No 1540";

        Address expected = Address.builder()
                .street("Calle 39")
                .houseNumber("No 1540")
                .build();

        callParserAndValidate(address, expected);

    }

    @Test
    public void shouldPasWhenParsingComplexCaseWithStreetNameAndHouseNumberWithNoSmallLettersKeySuccessfully() throws Exception {
        address = "Calle 39 no 1540";

        Address expected = Address.builder()
                .street("Calle 39")
                .houseNumber("no 1540")
                .build();

        callParserAndValidate(address, expected);

    }

    @Test
    public void shouldPasWhenParsingComplexCaseWithStreetNameAndHouseNumberWithNoKeyAllCapsSuccessfully() throws Exception {
        address = "Calle 39 NO 1540";

        Address expected = Address.builder()
                .street("Calle 39")
                .houseNumber("NO 1540")
                .build();

        callParserAndValidate(address, expected);

    }

    @Test
    public void shouldPasWhenParsingComplexCaseWithStreetNameAndHouseNumberWithNumKeyAllCapsSuccessfully() throws Exception {
        address = "Calle 39 NUM 1540";

        Address expected = Address.builder()
                .street("Calle 39")
                .houseNumber("NUM 1540")
                .build();

        callParserAndValidate(address, expected);

    }

    @Test
    public void shouldPasWhenParsingComplexCaseWithStreetNameAndHouseNumberWithNumKeyAllSmallSuccessfully() throws Exception {
        address = "Calle 39 num 1540";

        Address expected = Address.builder()
                .street("Calle 39")
                .houseNumber("num 1540")
                .build();

        callParserAndValidate(address, expected);

    }

    @Test
    public void shouldPasWhenParsingComplexCaseWithStreetNameAndHouseNumberWithNumberKeyAllCapsSuccessfully() throws Exception {
        address = "Calle 39 NUMBER 1540";

        Address expected = Address.builder()
                .street("Calle 39")
                .houseNumber("NUMBER 1540")
                .build();

        callParserAndValidate(address, expected);

    }

    @Test
    public void shouldPasWhenParsingComplexCaseWithStreetNameAndHouseNumberWithNumberKeyAllSmallSuccessfully() throws Exception {
        address = "Calle 39 number 1540";

        Address expected = Address.builder()
                .street("Calle 39")
                .houseNumber("number 1540")
                .build();

        callParserAndValidate(address, expected);

    }

    @Test
    public void shouldPasWhenParsingComplexCaseWithStreetNameAndHouseNumberWithNumberFirstAndWithoutLetterSuccessfully() throws Exception {
        address = "Number 1540 Calle 39";

        Address expected = Address.builder()
                .street("Calle 39")
                .houseNumber("Number 1540")
                .build();

        callParserAndValidate(address, expected);

    }

    @Test
    public void shouldPasWhenParsingComplexCaseWithStreetNameAndHouseNumberWithNumberFirstAndWithLetterSeparatedWithSpaceSuccessfully() throws Exception {
        address = "Number 1540 b Calle 39";

        Address expected = Address.builder()
                .street("Calle 39")
                .houseNumber("Number 1540 b")
                .build();

        callParserAndValidate(address, expected);

    }

    @Test
    public void shouldPasWhenParsingComplexCaseWithStreetNameAndHouseNumberWithNumberFirstAndWithLetterSuccessfully() throws Exception {
        address = "Number 1540b Calle 39";

        Address expected = Address.builder()
                .street("Calle 39")
                .houseNumber("Number 1540b")
                .build();

        callParserAndValidate(address, expected);

    }

    @Test
    public void shouldReturn400ForPassingNullAddressToParse() throws Exception {
        address = (null);
        callParserAndValidate(address);

    }

    @Test
    public void shouldReturn400ForPassingEmptyAddressToParse() throws Exception {
        address = ("");
        callParserAndValidate(address);

    }

    private void callParserAndValidate(String address) throws Exception {
        NullCheckException thrown =
                assertThrows(NullCheckException.class,
                        () -> service.parseAddress(address),
                        "Expected NullCheckException() to throw");

        assertTrue(thrown.getMessage().contains("Address Can't be null or empty"));
    }

    private void callParserAndValidate(String address, Address expectedAddress) throws Exception {

        var result = service.parseAddress(address);
        assertNotNull(result);
        assertEquals(expectedAddress, result);
    }
}