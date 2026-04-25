package ru.skypro.homework.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.homework.service.AdService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ExceptionHandlerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdService adService;

    @Test
    void handleAccessDenied_Returns403() throws Exception {
        when(adService.getExtendedAd(999)).thenThrow(new AccessDeniedException("No access"));

        mockMvc.perform(get("/ads/999").principal(() -> "user")).andExpect(status().isForbidden());
    }

    @Test
    void handleBadCredentials_Returns401() throws Exception {
        when(adService.getExtendedAd(1)).thenThrow(new BadCredentialsException("Invalid"));

        mockMvc.perform(get("/ads/1").principal(() -> "invalid")).andExpect(status().isUnauthorized());
    }
}