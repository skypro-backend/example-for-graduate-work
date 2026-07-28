package ru.skypro.homework.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.Role;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

import java.time.LocalDateTime;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class CommentsControllerIntegrationTest {

    private static final String RAW_PASSWORD = "password123";

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

    private User owner;
    private User otherUser;
    private User admin;
    private Ad ad;

    @BeforeEach
    void setUp() {
        owner = createUser("owner@mail.com", Role.USER);
        otherUser = createUser("other@mail.com", Role.USER);
        admin = createUser("admin@mail.com", Role.ADMIN);
        ad = createAd(otherUser);
    }

    @Test
    void getComments_withoutAuthentication_shouldReturn200() throws Exception {
        mockMvc.perform(get("/ads/{id}/comments", ad.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void addComment_withoutAuthentication_shouldReturn401() throws Exception {
        mockMvc.perform(post("/ads/{id}/comments", ad.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"text\": \"Комментарий длиной больше 8 символов\"}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void deleteComment_asAnotherUser_shouldReturn403() throws Exception {
        Comment comment = createComment(owner, ad);

        mockMvc.perform(delete("/ads/{adId}/comments/{commentId}", ad.getId(), comment.getId())
                        .with(httpBasic(otherUser.getEmail(), RAW_PASSWORD)))
                .andExpect(status().isForbidden());
    }

    @Test
    void deleteComment_asOwner_shouldReturn200() throws Exception {
        Comment comment = createComment(owner, ad);

        mockMvc.perform(delete("/ads/{adId}/comments/{commentId}", ad.getId(), comment.getId())
                        .with(httpBasic(owner.getEmail(), RAW_PASSWORD)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteComment_asAdmin_shouldReturn200() throws Exception {
        Comment comment = createComment(owner, ad);

        mockMvc.perform(delete("/ads/{adId}/comments/{commentId}", ad.getId(), comment.getId())
                        .with(httpBasic(admin.getEmail(), RAW_PASSWORD)))
                .andExpect(status().isOk());
    }

    @Test
    void updateComment_asAnotherUser_shouldReturn403() throws Exception {
        Comment comment = createComment(owner, ad);

        mockMvc.perform(patch("/ads/{adId}/comments/{commentId}", ad.getId(), comment.getId())
                        .with(httpBasic(otherUser.getEmail(), RAW_PASSWORD))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"text\": \"Изменённый текст комментария\"}"))
                .andExpect(status().isForbidden());
    }

    private User createUser(String email, Role role) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(RAW_PASSWORD));
        user.setFirstName("Test");
        user.setLastName("User");
        user.setRole(role);
        return userRepository.save(user);
    }

    private Ad createAd(User author) {
        Ad ad = new Ad();
        ad.setTitle("Тестовое объявление");
        ad.setPrice(1000);
        ad.setDescription("Описание тестового объявления");
        ad.setAuthor(author);
        ad.setImage("/images/ad_test.jpg");
        return adRepository.save(ad);
    }

    private Comment createComment(User author, Ad ad) {
        Comment comment = new Comment();
        comment.setText("Исходный текст комментария");
        comment.setAuthor(author);
        comment.setAd(ad);
        comment.setCreatedAt(LocalDateTime.now());
        return commentRepository.save(comment);
    }
}
