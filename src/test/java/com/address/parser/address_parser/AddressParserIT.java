package com.address.parser.address_parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AddressParserIT extends ITCommons {


    private ParserRequest request;

    @BeforeEach
    public void setup() {
        request = new ParserRequest();
    }

    @Test
    public void shouldReturn200WhenParsingSimpleCaseWithStreetNameAndHouseNumberSuccessfully() throws Exception {
        request.setAddress("Winterallee 3");

        Address address = Address.builder()
                .street("Winterallee")
                .houseNumber("3")
                .build();

        MvcResult result = performPostAndReturn(request, status().isOk());
        String content = result.getResponse().getContentAsString();

        var response = objectMapper.readValue(content, ParserResponse.class);
        var parsedAddress = objectMapper.readValue(objectMapper.writeValueAsString(response.getAddress()), Address.class);
        assertThat(parsedAddress).isNotNull();
        assertThat(parsedAddress).isEqualToComparingFieldByField(address);

    }

    @Test
    public void shouldReturn200WhenParsingSimpleCaseWithStreetNameAndHouseNumberWithoutLetterSuccessfully() throws Exception {
        request.setAddress("Blaufeldweg 123B");

        Address address = Address.builder()
                .street("Blaufeldweg")
                .houseNumber("123B")
                .build();

        MvcResult result = performPostAndReturn(request, status().isOk());
        String content = result.getResponse().getContentAsString();

        var response = objectMapper.readValue(content, ParserResponse.class);
        var parsedAddress = objectMapper.readValue(objectMapper.writeValueAsString(response.getAddress()), Address.class);
        assertThat(parsedAddress).isNotNull();
        assertThat(parsedAddress).isEqualToComparingFieldByField(address);

    }

    @Test
    public void shouldReturn200WhenParsingSimpleCaseWithStreetNameAndComposedNumberSuccessfully() throws Exception {
        request.setAddress("Musterstrasse 4");

        Address address = Address.builder()
                .street("Musterstrasse")
                .houseNumber("4")
                .build();

        MvcResult result = performPostAndReturn(request, status().isOk());
        String content = result.getResponse().getContentAsString();

        var response = objectMapper.readValue(content, ParserResponse.class);
        var parsedAddress = objectMapper.readValue(objectMapper.writeValueAsString(response.getAddress()), Address.class);
        assertThat(parsedAddress).isNotNull();
        assertThat(parsedAddress).isEqualToComparingFieldByField(address);

    }

    @Test
    public void shouldReturn200WhenParsingSimpleCaseWithStreetNameContainSpecialCharactersAndHouseNumberWithoutLetterSuccessfully() throws Exception {
        request.setAddress("KüstrinerStraße 30B");

        Address address = Address.builder()
                .street("KüstrinerStraße")
                .houseNumber("30B")
                .build();

        MvcResult result = performPostAndReturn(request, status().isOk());
        String content = result.getResponse().getContentAsString();

        var response = objectMapper.readValue(content, ParserResponse.class);
        var parsedAddress = objectMapper.readValue(objectMapper.writeValueAsString(response.getAddress()), Address.class);
        assertThat(parsedAddress).isNotNull();
        assertThat(parsedAddress).isEqualToComparingFieldByField(address);

    }

    @Test
    public void shouldReturn200WhenParsingSimpleCaseWithStreetNameAndHouseNumberWithoutLetterAndContainSpecialCharactersSuccessfully() throws Exception {
        request.setAddress("Blaufeldweg 123ä");

        Address address = Address.builder()
                .street("Blaufeldweg")
                .houseNumber("123ä")
                .build();

        MvcResult result = performPostAndReturn(request, status().isOk());
        String content = result.getResponse().getContentAsString();

        var response = objectMapper.readValue(content, ParserResponse.class);
        var parsedAddress = objectMapper.readValue(objectMapper.writeValueAsString(response.getAddress()), Address.class);
        assertThat(parsedAddress).isNotNull();
        assertThat(parsedAddress).isEqualToComparingFieldByField(address);

    }

    @Test
    public void shouldReturn200WhenParsingMoreComplicatedCaseWithStreetNameAndHouseNumberSuccessfully() throws Exception {
        request.setAddress("Am Bächle 23");

        Address address = Address.builder()
                .street("Am Bächle")
                .houseNumber("23")
                .build();

        MvcResult result = performPostAndReturn(request, status().isOk());
        String content = result.getResponse().getContentAsString();

        var response = objectMapper.readValue(content, ParserResponse.class);
        var parsedAddress = objectMapper.readValue(objectMapper.writeValueAsString(response.getAddress()), Address.class);
        assertThat(parsedAddress).isNotNull();
        assertThat(parsedAddress).isEqualToComparingFieldByField(address);

    }

    @Test
    public void shouldReturn200WhenParsingMoreComplicatedCaseWithReversedStreetNameAndHouseNumberSuccessfully() throws Exception {
        request.setAddress("200 Broadway Av");

        Address address = Address.builder()
                .street("Broadway Av")
                .houseNumber("200")
                .build();

        MvcResult result = performPostAndReturn(request, status().isOk());
        String content = result.getResponse().getContentAsString();

        var response = objectMapper.readValue(content, ParserResponse.class);
        var parsedAddress = objectMapper.readValue(objectMapper.writeValueAsString(response.getAddress()), Address.class);
        assertThat(parsedAddress).isNotNull();
        assertThat(parsedAddress).isEqualToComparingFieldByField(address);

    }

    @Test
    public void shouldReturn200WhenParsingMoreComplicatedCaseWithStreetNameAndHouseNumberContainsLetterSeparatedSuccessfully() throws Exception {
        request.setAddress("Auf der Vogelwiese 23 b");

        Address address = Address.builder()
                .street("Auf der Vogelwiese")
                .houseNumber("23 b")
                .build();

        MvcResult result = performPostAndReturn(request, status().isOk());
        String content = result.getResponse().getContentAsString();

        var response = objectMapper.readValue(content, ParserResponse.class);
        var parsedAddress = objectMapper.readValue(objectMapper.writeValueAsString(response.getAddress()), Address.class);
        assertThat(parsedAddress).isNotNull();
        assertThat(parsedAddress).isEqualToComparingFieldByField(address);

    }

    @Test
    public void shouldReturn200WhenParsingMoreComplicatedCaseWithStreetNameAndHouseNumberCommaSeparatedSuccessfully() throws Exception {
        request.setAddress("4, rue de la revolution");

        Address address = Address.builder()
                .street("rue de la revolution")
                .houseNumber("4")
                .build();

        MvcResult result = performPostAndReturn(request, status().isOk());
        String content = result.getResponse().getContentAsString();

        var response = objectMapper.readValue(content, ParserResponse.class);
        var parsedAddress = objectMapper.readValue(objectMapper.writeValueAsString(response.getAddress()), Address.class);
        assertThat(parsedAddress).isNotNull();
        assertThat(parsedAddress).isEqualToComparingFieldByField(address);

    }

    @Test
    public void shouldReturn200WhenParsingMoreComplicatedCaseWithStreetNameAndHouseNumberDotSeparatedSuccessfully() throws Exception {
        request.setAddress("4. rue de la revolution");

        Address address = Address.builder()
                .street("rue de la revolution")
                .houseNumber("4")
                .build();

        MvcResult result = performPostAndReturn(request, status().isOk());
        String content = result.getResponse().getContentAsString();

        var response = objectMapper.readValue(content, ParserResponse.class);
        var parsedAddress = objectMapper.readValue(objectMapper.writeValueAsString(response.getAddress()), Address.class);
        assertThat(parsedAddress).isNotNull();
        assertThat(parsedAddress).isEqualToComparingFieldByField(address);

    }

    @Test
    public void shouldReturn200WhenParsingMoreComplicatedCaseWithStreetNameAndHouseNumberCommaSeparatedAndLongTextSuccessfully() throws Exception {
        request.setAddress("rue de la revolution, 50");

        Address address = Address.builder()
                .street("rue de la revolution")
                .houseNumber("50")
                .build();

        MvcResult result = performPostAndReturn(request, status().isOk());
        String content = result.getResponse().getContentAsString();

        var response = objectMapper.readValue(content, ParserResponse.class);
        var parsedAddress = objectMapper.readValue(objectMapper.writeValueAsString(response.getAddress()), Address.class);
        assertThat(parsedAddress).isNotNull();
        assertThat(parsedAddress).isEqualToComparingFieldByField(address);

    }

    @Test
    public void shouldReturn200WhenParsingMoreComplicatedCaseWithShortStreetNameAndHouseNumberCommaSeparatedSuccessfully() throws Exception {
        request.setAddress("Calle Aduana, 29");

        Address address = Address.builder()
                .street("Calle Aduana")
                .houseNumber("29")
                .build();

        MvcResult result = performPostAndReturn(request, status().isOk());
        String content = result.getResponse().getContentAsString();

        var response = objectMapper.readValue(content, ParserResponse.class);
        var parsedAddress = objectMapper.readValue(objectMapper.writeValueAsString(response.getAddress()), Address.class);
        assertThat(parsedAddress).isNotNull();
        assertThat(parsedAddress).isEqualToComparingFieldByField(address);

    }

    @Test
    public void shouldReturn200WhenParsingComplexCaseWithStreetNameAndHouseNumberWithNoCapsKeySuccessfully() throws Exception {
        request.setAddress("Calle 39 No 1540");

        Address address = Address.builder()
                .street("Calle 39")
                .houseNumber("No 1540")
                .build();

        MvcResult result = performPostAndReturn(request, status().isOk());
        String content = result.getResponse().getContentAsString();

        var response = objectMapper.readValue(content, ParserResponse.class);
        var parsedAddress = objectMapper.readValue(objectMapper.writeValueAsString(response.getAddress()), Address.class);
        assertThat(parsedAddress).isNotNull();
        assertThat(parsedAddress).isEqualToComparingFieldByField(address);

    }

    @Test
    public void shouldReturn200WhenParsingComplexCaseWithStreetNameAndHouseNumberWithNoSmallLettersKeySuccessfully() throws Exception {
        request.setAddress("Calle 39 no 1540");

        Address address = Address.builder()
                .street("Calle 39")
                .houseNumber("no 1540")
                .build();

        MvcResult result = performPostAndReturn(request, status().isOk());
        String content = result.getResponse().getContentAsString();

        var response = objectMapper.readValue(content, ParserResponse.class);
        var parsedAddress = objectMapper.readValue(objectMapper.writeValueAsString(response.getAddress()), Address.class);
        assertThat(parsedAddress).isNotNull();
        assertThat(parsedAddress).isEqualToComparingFieldByField(address);

    }

    @Test
    public void shouldReturn200WhenParsingComplexCaseWithStreetNameAndHouseNumberWithNoKeyAllCapsSuccessfully() throws Exception {
        request.setAddress("Calle 39 NO 1540");

        Address address = Address.builder()
                .street("Calle 39")
                .houseNumber("NO 1540")
                .build();

        MvcResult result = performPostAndReturn(request, status().isOk());
        String content = result.getResponse().getContentAsString();

        var response = objectMapper.readValue(content, ParserResponse.class);
        var parsedAddress = objectMapper.readValue(objectMapper.writeValueAsString(response.getAddress()), Address.class);
        assertThat(parsedAddress).isNotNull();
        assertThat(parsedAddress).isEqualToComparingFieldByField(address);

    }

    @Test
    public void shouldReturn200WhenParsingComplexCaseWithStreetNameAndHouseNumberWithNumKeyAllCapsSuccessfully() throws Exception {
        request.setAddress("Calle 39 NUM 1540");

        Address address = Address.builder()
                .street("Calle 39")
                .houseNumber("NUM 1540")
                .build();

        MvcResult result = performPostAndReturn(request, status().isOk());
        String content = result.getResponse().getContentAsString();

        var response = objectMapper.readValue(content, ParserResponse.class);
        var parsedAddress = objectMapper.readValue(objectMapper.writeValueAsString(response.getAddress()), Address.class);
        assertThat(parsedAddress).isNotNull();
        assertThat(parsedAddress).isEqualToComparingFieldByField(address);

    }

    @Test
    public void shouldReturn200WhenParsingComplexCaseWithStreetNameAndHouseNumberWithNumKeyAllSmallSuccessfully() throws Exception {
        request.setAddress("Calle 39 num 1540");

        Address address = Address.builder()
                .street("Calle 39")
                .houseNumber("num 1540")
                .build();

        MvcResult result = performPostAndReturn(request, status().isOk());
        String content = result.getResponse().getContentAsString();

        var response = objectMapper.readValue(content, ParserResponse.class);
        var parsedAddress = objectMapper.readValue(objectMapper.writeValueAsString(response.getAddress()), Address.class);
        assertThat(parsedAddress).isNotNull();
        assertThat(parsedAddress).isEqualToComparingFieldByField(address);

    }

    @Test
    public void shouldReturn200WhenParsingComplexCaseWithStreetNameAndHouseNumberWithNumberKeyAllCapsSuccessfully() throws Exception {
        request.setAddress("Calle 39 NUMBER 1540");

        Address address = Address.builder()
                .street("Calle 39")
                .houseNumber("NUMBER 1540")
                .build();

        MvcResult result = performPostAndReturn(request, status().isOk());
        String content = result.getResponse().getContentAsString();

        var response = objectMapper.readValue(content, ParserResponse.class);
        var parsedAddress = objectMapper.readValue(objectMapper.writeValueAsString(response.getAddress()), Address.class);
        assertThat(parsedAddress).isNotNull();
        assertThat(parsedAddress).isEqualToComparingFieldByField(address);

    }

    @Test
    public void shouldReturn200WhenParsingComplexCaseWithStreetNameAndHouseNumberWithNumberKeyAllSmallSuccessfully() throws Exception {
        request.setAddress("Calle 39 number 1540");

        Address address = Address.builder()
                .street("Calle 39")
                .houseNumber("number 1540")
                .build();

        MvcResult result = performPostAndReturn(request, status().isOk());
        String content = result.getResponse().getContentAsString();

        var response = objectMapper.readValue(content, ParserResponse.class);
        var parsedAddress = objectMapper.readValue(objectMapper.writeValueAsString(response.getAddress()), Address.class);
        assertThat(parsedAddress).isNotNull();
        assertThat(parsedAddress).isEqualToComparingFieldByField(address);

    }

    @Test
    public void shouldReturn200WhenParsingComplexCaseWithStreetNameAndHouseNumberWithNumberFirstAndWithoutLetterSuccessfully() throws Exception {
        request.setAddress("Number 1540 Calle 39");

        Address address = Address.builder()
                .street("Calle 39")
                .houseNumber("Number 1540")
                .build();

        MvcResult result = performPostAndReturn(request, status().isOk());
        String content = result.getResponse().getContentAsString();

        var response = objectMapper.readValue(content, ParserResponse.class);
        var parsedAddress = objectMapper.readValue(objectMapper.writeValueAsString(response.getAddress()), Address.class);
        assertThat(parsedAddress).isNotNull();
        assertThat(parsedAddress).isEqualToComparingFieldByField(address);

    }

    @Test
    public void shouldReturn200WhenParsingComplexCaseWithStreetNameAndHouseNumberWithNumberFirstAndWithLetterSeparatedWithSpaceSuccessfully() throws Exception {
        request.setAddress("Number 1540 b Calle 39");

        Address address = Address.builder()
                .street("Calle 39")
                .houseNumber("Number 1540 b")
                .build();

        MvcResult result = performPostAndReturn(request, status().isOk());
        String content = result.getResponse().getContentAsString();

        var response = objectMapper.readValue(content, ParserResponse.class);
        var parsedAddress = objectMapper.readValue(objectMapper.writeValueAsString(response.getAddress()), Address.class);
        assertThat(parsedAddress).isNotNull();
        assertThat(parsedAddress).isEqualToComparingFieldByField(address);

    }

    @Test
    public void shouldReturn200WhenParsingComplexCaseWithStreetNameAndHouseNumberWithNumberFirstAndWithLetterSuccessfully() throws Exception {
        request.setAddress("Number 1540b Calle 39");

        Address address = Address.builder()
                .street("Calle 39")
                .houseNumber("Number 1540b")
                .build();

        MvcResult result = performPostAndReturn(request, status().isOk());
        String content = result.getResponse().getContentAsString();

        var response = objectMapper.readValue(content, ParserResponse.class);
        var parsedAddress = objectMapper.readValue(objectMapper.writeValueAsString(response.getAddress()), Address.class);
        assertThat(parsedAddress).isNotNull();
        assertThat(parsedAddress).isEqualToComparingFieldByField(address);

    }

    @Test
    public void shouldReturn400ForPassingNullAddressToParse() throws Exception {
        request.setAddress(null);
        performPostAndReturn(request, status().isBadRequest());

    }

    @Test
    public void shouldReturn400ForPassingEmptyAddressToParse() throws Exception {
        request.setAddress("");
        performPostAndReturn(request, status().isBadRequest());

    }
}