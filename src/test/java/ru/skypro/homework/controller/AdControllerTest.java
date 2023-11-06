package ru.skypro.homework.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.model.ImageModel;
import ru.skypro.homework.model.Role;
import ru.skypro.homework.projections.Register;
import ru.skypro.homework.repository.AdRepo;
import ru.skypro.homework.repository.ImageRepo;
import ru.skypro.homework.repository.UserRepo;
import ru.skypro.homework.service.UserServiceSecurity;

import javax.imageio.IIOException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class AdControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    AdRepo adRepository;

    @Autowired
    ImageRepo imageRepository;

    @Autowired
    UserServiceSecurity userServiceSecurity;
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
            .withUsername("postgres")
            .withPassword("postgres");

    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    private String getAuthenticationHeader(String login, String password) {
        String encoding = Base64.getEncoder()
                .encodeToString((login + ":" + password).getBytes(StandardCharsets.UTF_8));
        return "Basic " + encoding;
    }

    private void addToDb() throws IOException {

        userServiceSecurity.createUser(new Register(
                "user@mail.ru",
                "password",
                "user name",
                "user surname",
                "+711111111",
                Role.USER));

        userServiceSecurity.createUser(new Register(
                "admin@mail.ru",
                "password",
                "admin name",
                "admin surname",
                "+72222222",
                Role.ADMIN));

        ImageModel image = new ImageModel();
        image.setId(UUID.randomUUID().toString());
        image.setBytes(Files.readAllBytes(Paths.get("ad-test.jpg")));
        imageRepository.save(image);

        AdModel adModel = new AdModel();
        adModel.setImage(image);
        adModel.setPrice(100);
        adModel.setTitle("Title1");
        adModel.setDescription("Description1");
        adModel.setUserModel(userRepository.findByUserName("user@mail.ru").orElse(null));
        adRepository.save(adModel);
    }

    @AfterEach
    public void cleanUserDataBase() {
        adRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void getAllAds() throws Exception {
        mockMvc.perform(get("/ads"))
                .andExpect(status().isOk());

        addToDb();

        mockMvc.perform(get("/ads"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(1));



    }

    @Test
    void addAd() {
    }

    @Test
    void getAds() {
    }

    @Test
    void updateAds() {
    }

    @Test
    void removeAd() {
    }

    @Test
    void getAdsMe() {
    }
}