package ru.skypro.homework.filter;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class BasicAuthCorsFilterTest {

    private final BasicAuthCorsFilter filter = new BasicAuthCorsFilter();

    @Test
    public void doFilterInternal_AddsAccessControlAllowCredentialsHeader() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();

        filter.doFilterInternal(request, response, chain);

        assertEquals("true", response.getHeader("Access-Control-Allow-Credentials"));
    }
}