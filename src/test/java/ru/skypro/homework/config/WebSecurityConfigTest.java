package ru.skypro.homework.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.homework.config.WebSecurityConfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@Import(WebSecurityConfig.class)
public class WebSecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    void getAllAds_Unauthorized_ShouldBePermitted() throws Exception {
        mockMvc.perform(get("/ads")).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void getUserProfile_Unauthorized_ShouldBeForbidden() throws Exception {
        mockMvc.perform(get("/users/me")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void getUserProfile_Authenticated_ShouldBeOk() throws Exception {
        mockMvc.perform(get("/users/me")).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void addAd_Unauthorized_ShouldBeForbidden() throws Exception {
        mockMvc.perform(get("/ads").param("title", "test")).andExpect(status().isOk()); // GET /ads разрешён
    }
}