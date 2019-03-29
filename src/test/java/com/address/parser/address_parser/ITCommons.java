package com.address.parser.address_parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public abstract class ITCommons {

    private final String BASIC_API = "/api/v1";
    private final String API_PATH = "/address-parser";

    @Autowired
    public ObjectMapper objectMapper;
    @Autowired
    public MockMvc mvc;

    protected MvcResult performPostAndReturn(final Object value, final ResultMatcher statusCodeMatcher) throws Exception {
        String content = value instanceof String ? value.toString() : objectMapper.writeValueAsString(value);

        return mvc.perform(post(BASIC_API + API_PATH).contentType(APPLICATION_JSON_UTF8_VALUE)
                .content(content))
                .andDo(print())
                .andExpect(statusCodeMatcher).andReturn();
    }
}
