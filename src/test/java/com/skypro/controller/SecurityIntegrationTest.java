package com.skypro.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.skypro.dto.CreateOrUpdateAd;
import com.skypro.dto.CreateOrUpdateComment;
import com.skypro.dto.NewPassword;
import com.skypro.dto.Role;
import com.skypro.entity.AdEntity;
import com.skypro.entity.CommentEntity;
import com.skypro.entity.UserEntity;
import com.skypro.repository.AdRepository;
import com.skypro.repository.CommentRepository;
import com.skypro.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Интеграционные тесты для проверки безопасности, прав доступа
 * и корректности работы эндпоинтов.
 * <p>
 * Покрывают сценарии:
 * <ul>
 *   <li>Публичный доступ к GET /ads и GET /ads/{id}</li>
 *   <li>Авторизация для POST /ads (создание объявлений)</li>
 *   <li>Проверка прав на редактирование и удаление объявлений (владелец, другой пользователь, администратор)</li>
 *   <li>Аналогичные проверки для комментариев (добавление, редактирование, удаление)</li>
 *   <li>Смена пароля (правильный и неправильный старый пароль)</li>
 *   <li>Загрузка аватарки</li>
 *   <li>Получение картинок (существующих и несуществующих)</li>
 * </ul>
 * </p>
 * <p>
 * Для каждого теста создаются тестовые данные: владелец, другой пользователь,
 * администратор, одно объявление и один комментарий.
 * </p>
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class SecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    private UserEntity owner;
    private UserEntity otherUser;
    private UserEntity admin;
    private AdEntity ad;
    private CommentEntity comment;

    /**
     * Настраивает тестовые данные перед каждым тестом.
     * <p>
     * Создаёт:
     * <ul>
     *   <li>Владельца объявления и комментария (роль USER)</li>
     *   <li>Другого пользователя (роль USER)</li>
     *   <li>Администратора (роль ADMIN)</li>
     *   <li>Одно объявление (принадлежит владельцу)</li>
     *   <li>Один комментарий (принадлежит владельцу, относится к созданному объявлению)</li>
     * </ul>
     * </p>
     */
    @BeforeEach
    void setUp() {
        owner = userRepository.save(new UserEntity(null, "owner",
                passwordEncoder.encode("password"), "Owner", "User",
                "+79991112233", Role.USER, null));
        otherUser = userRepository.save(new UserEntity(null, "other",
                passwordEncoder.encode("password"), "Other", "User",
                "+79991112244", Role.USER, null));
        admin = userRepository.save(new UserEntity(null, "admin",
                passwordEncoder.encode("password"), "Admin", "User",
                "+79991112255", Role.ADMIN, null));

        ad = adRepository.save(new AdEntity(null, "Test Ad", 1000,
                "Test description", owner, null, System.currentTimeMillis()));

        comment = commentRepository.save(new CommentEntity(null, "Test comment",
                owner, ad, System.currentTimeMillis()));
    }

    //ТЕСТЫ ОБЪЯВЛЕНИЙ

    /**
     * Проверяет, что GET /ads доступен без авторизации.
     */
    @Test
    void getAdsWithoutAuth_shouldReturn200() throws Exception {
        mockMvc.perform(get("/ads"))
                .andExpect(status().isOk());
    }

    /**
     * Проверяет, что GET /ads/{id} доступен без авторизации.
     */
    @Test
    void getAdByIdWithoutAuth_shouldReturn200() throws Exception {
        mockMvc.perform(get("/ads/{id}", ad.getId()))
                .andExpect(status().isOk());
    }

    /**
     * Проверяет, что создание объявления без авторизации возвращает 401.
     */
    @Test
    void addAdWithoutAuth_shouldReturn401() throws Exception {
        MockMultipartFile image = new MockMultipartFile("image", "test.jpg",
                MediaType.IMAGE_JPEG_VALUE, new byte[0]);

        MockMultipartFile propertiesPart = new MockMultipartFile(
                "properties",
                "",
                "application/json",
                "{\"title\":\"Test\",\"price\":1000,\"description\":\"Test ad\"}".getBytes()
        );

        mockMvc.perform(multipart("/ads")
                        .file(image)
                        .file(propertiesPart))
                .andExpect(status().isUnauthorized());
    }

    /**
     * Проверяет, что авторизованный пользователь может создать объявление (статус 201).
     */
    @Test
    void addAdWithAuth_shouldReturn201() throws Exception {
        MockMultipartFile image = new MockMultipartFile("image", "test.jpg",
                MediaType.IMAGE_JPEG_VALUE, new byte[]{1, 2, 3});

        MockMultipartFile propertiesPart = new MockMultipartFile(
                "properties",
                "",
                "application/json",
                "{\"title\":\"Test\",\"price\":1000,\"description\":\"Test ad\"}".getBytes()
        );

        mockMvc.perform(multipart("/ads")
                        .file(image)
                        .file(propertiesPart)
                        .with(user("owner").password("password").roles("USER")))
                .andExpect(status().isCreated());
    }

    /**
     * Проверяет, что при невалидном JSON в поле properties возвращается 400.
     */
    @Test
    void addAdWithInvalidData_shouldReturn400() throws Exception {
        MockMultipartFile image = new MockMultipartFile("image", "test.jpg",
                MediaType.IMAGE_JPEG_VALUE, new byte[]{1, 2, 3});

        MockMultipartFile propertiesPart = new MockMultipartFile(
                "properties",
                "",
                "application/json",
                "invalid json".getBytes()
        );

        mockMvc.perform(multipart("/ads")
                        .file(image)
                        .file(propertiesPart)
                        .with(user("owner").password("password").roles("USER")))
                .andExpect(status().isBadRequest());
    }

    /**
     * Проверяет, что владелец может обновить своё объявление (статус 200).
     */
    @Test
    void updateAdAsOwner_shouldReturn200() throws Exception {
        CreateOrUpdateAd body = new CreateOrUpdateAd("Updated", 2000, "Updated desc");
        mockMvc.perform(patch("/ads/{id}", ad.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                        .with(user("owner").password("password").roles("USER")))
                .andExpect(status().isOk());
    }

    /**
     * Проверяет, что другой пользователь не может обновить чужое объявление (статус 403).
     */
    @Test
    void updateAdAsOtherUser_shouldReturn403() throws Exception {
        CreateOrUpdateAd body = new CreateOrUpdateAd("Updated", 2000, "Updated desc");
        mockMvc.perform(patch("/ads/{id}", ad.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                        .with(user("other").password("password").roles("USER")))
                .andExpect(status().isForbidden());
    }

    /**
     * Проверяет, что администратор может обновить любое объявление (статус 200).
     */
    @Test
    void updateAdAsAdmin_shouldReturn200() throws Exception {
        CreateOrUpdateAd body = new CreateOrUpdateAd("Updated", 2000, "Updated desc");
        mockMvc.perform(patch("/ads/{id}", ad.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                        .with(user("admin").password("password").roles("ADMIN")))
                .andExpect(status().isOk());
    }

    /**
     * Проверяет, что владелец может удалить своё объявление (статус 204).
     */
    @Test
    void deleteAdAsOwner_shouldReturn204() throws Exception {
        mockMvc.perform(delete("/ads/{id}", ad.getId())
                        .with(user("owner").password("password").roles("USER")))
                .andExpect(status().isNoContent());
    }

    /**
     * Проверяет, что другой пользователь не может удалить чужое объявление (статус 403).
     */
    @Test
    void deleteAdAsOtherUser_shouldReturn403() throws Exception {
        mockMvc.perform(delete("/ads/{id}", ad.getId())
                        .with(user("other").password("password").roles("USER")))
                .andExpect(status().isForbidden());
    }

    /**
     * Проверяет, что администратор может удалить любое объявление (статус 204).
     */
    @Test
    void deleteAdAsAdmin_shouldReturn204() throws Exception {
        mockMvc.perform(delete("/ads/{id}", ad.getId())
                        .with(user("admin").password("password").roles("ADMIN")))
                .andExpect(status().isNoContent());
    }

    //ТЕСТЫ КОММЕНТАРИЕВ

    /**
     * Проверяет, что авторизованный пользователь может добавить комментарий (статус 201).
     */
    @Test
    void addCommentAsUser_shouldReturn201() throws Exception {
        CreateOrUpdateComment body = new CreateOrUpdateComment("New comment for test");
        mockMvc.perform(post("/ads/{adId}/comments", ad.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                        .with(user("other").password("password").roles("USER")))
                .andExpect(status().isCreated());
    }

    /**
     * Проверяет, что владелец может обновить свой комментарий (статус 200).
     */
    @Test
    void updateCommentAsOwner_shouldReturn200() throws Exception {
        CreateOrUpdateComment body = new CreateOrUpdateComment("Updated comment text");
        mockMvc.perform(patch("/ads/{adId}/comments/{commentId}", ad.getId(), comment.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                        .with(user("owner").password("password").roles("USER")))
                .andExpect(status().isOk());
    }

    /**
     * Проверяет, что другой пользователь не может обновить чужой комментарий (статус 403).
     */
    @Test
    void updateCommentAsOtherUser_shouldReturn403() throws Exception {
        CreateOrUpdateComment body = new CreateOrUpdateComment("Updated comment text");
        mockMvc.perform(patch("/ads/{adId}/comments/{commentId}", ad.getId(), comment.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                        .with(user("other").password("password").roles("USER")))
                .andExpect(status().isForbidden());
    }

    /**
     * Проверяет, что администратор может обновить любой комментарий (статус 200).
     */
    @Test
    void updateCommentAsAdmin_shouldReturn200() throws Exception {
        CreateOrUpdateComment body = new CreateOrUpdateComment("Updated comment text");
        mockMvc.perform(patch("/ads/{adId}/comments/{commentId}", ad.getId(), comment.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                        .with(user("admin").password("password").roles("ADMIN")))
                .andExpect(status().isOk());
    }

    /**
     * Проверяет, что владелец может удалить свой комментарий (статус 204).
     */
    @Test
    void deleteCommentAsOwner_shouldReturn204() throws Exception {
        mockMvc.perform(delete("/ads/{adId}/comments/{commentId}", ad.getId(), comment.getId())
                        .with(user("owner").password("password").roles("USER")))
                .andExpect(status().isNoContent());
    }

    /**
     * Проверяет, что другой пользователь не может удалить чужой комментарий (статус 403).
     */
    @Test
    void deleteCommentAsOtherUser_shouldReturn403() throws Exception {
        mockMvc.perform(delete("/ads/{adId}/comments/{commentId}", ad.getId(), comment.getId())
                        .with(user("other").password("password").roles("USER")))
                .andExpect(status().isForbidden());
    }

    /**
     * Проверяет, что администратор может удалить любой комментарий (статус 204).
     */
    @Test
    void deleteCommentAsAdmin_shouldReturn204() throws Exception {
        mockMvc.perform(delete("/ads/{adId}/comments/{commentId}", ad.getId(), comment.getId())
                        .with(user("admin").password("password").roles("ADMIN")))
                .andExpect(status().isNoContent());
    }

    //ТЕСТЫ ПОЛЬЗОВАТЕЛЯ

    /**
     * Проверяет смену пароля при правильно введённом старом пароле (статус 200).
     */
    @Test
    void changePasswordWithCorrectOldPassword_shouldReturn200() throws Exception {
        NewPassword body = new NewPassword("password", "newPassword123");
        mockMvc.perform(post("/users/set_password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                        .with(user("owner").password("password").roles("USER")))
                .andExpect(status().isOk());
    }

    /**
     * Проверяет смену пароля при неверном старом пароле (статус 400).
     */
    @Test
    void changePasswordWithIncorrectOldPassword_shouldReturn400() throws Exception {
        NewPassword body = new NewPassword("wrongPassword", "newPassword123");
        mockMvc.perform(post("/users/set_password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                        .with(user("owner").password("password").roles("USER")))
                .andExpect(status().isBadRequest());
    }

    /**
     * Проверяет загрузку аватарки пользователем (статус 200).
     */
    @Test
    void updateAvatar_shouldReturn200() throws Exception {
        MockMultipartFile image = new MockMultipartFile("image", "avatar.jpg",
                MediaType.IMAGE_JPEG_VALUE, new byte[]{1, 2, 3, 4, 5});
        mockMvc.perform(multipart(HttpMethod.PATCH, "/users/me/image")
                        .file(image)
                        .with(user("owner").password("password").roles("USER")))
                .andExpect(status().isOk());
    }

    //ТЕСТЫ КАРТИНОК

    /**
     * Проверяет, что запрос к несуществующей картинке объявления возвращает 404.
     */
    @Test
    void getAdImage_shouldReturn404_forMissing() throws Exception {
        mockMvc.perform(get("/images/ads/nonexistent.jpg"))
                .andExpect(status().isNotFound());
    }

    /**
     * Проверяет, что запрос к несуществующей аватарке возвращает 404.
     */
    @Test
    void getUserImage_shouldReturn404_forMissing() throws Exception {
        mockMvc.perform(get("/images/users/nonexistent.jpg"))
                .andExpect(status().isNotFound());
    }

    /**
     * Проверяет полный цикл: создание объявления с картинкой,
     * получение имени файла из ответа и успешное получение картинки (статус 200).
     */
    @Test
    void getAdImage_shouldReturn200_withImage() throws Exception {
        MockMultipartFile image = new MockMultipartFile("image", "test.jpg",
                MediaType.IMAGE_JPEG_VALUE, new byte[]{1, 2, 3, 4, 5});

        MockMultipartFile propertiesPart = new MockMultipartFile(
                "properties",
                "",
                "application/json",
                "{\"title\":\"Test\",\"price\":1000,\"description\":\"Test ad\"}".getBytes()
        );

        MvcResult result = mockMvc.perform(multipart("/ads")
                        .file(image)
                        .file(propertiesPart)
                        .with(user("owner").password("password").roles("USER")))
                .andExpect(status().isCreated())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        String imagePath = JsonPath.read(responseBody, "$.image");
        String filename = imagePath.substring(imagePath.lastIndexOf('/') + 1);

        mockMvc.perform(get("/images/ads/{filename}", filename))
                .andExpect(status().isOk());
    }
}