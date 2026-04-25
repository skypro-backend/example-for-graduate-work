package ru.skypro.homework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.Role;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private UserRepository userRepository;

    private UserEntity user;
    private UserEntity admin;
    private AdEntity ad;
    private final String USER_EMAIL = "user@test.com";
    private final String ADMIN_EMAIL = "admin@test.com";

    private int phoneCounter = 10000;

    @BeforeEach
    void setUp() {
        commentRepository.deleteAll();
        adRepository.deleteAll();
        userRepository.deleteAll();
        phoneCounter = 10000; // сброс счётчика

        user = createUser(USER_EMAIL, "User", Role.USER);
        admin = createUser(ADMIN_EMAIL, "Admin", Role.ADMIN);

        ad = new AdEntity();
        ad.setTitle("Test Ad");
        ad.setPrice(100);
        ad.setDescription("Description");
        ad.setAuthor(user);
        ad.setCreatedAt(LocalDateTime.now());
        ad = adRepository.save(ad);
    }

    private UserEntity createUser(String email, String firstName, Role role) {
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setPassword("$2a$10$dwvtj/wz45H8SI7M3.7t.eoJw9pOx6oKq7r9qZ.zQnL3u1kZ9fWVW");
        user.setFirstName(firstName);
        user.setLastName("Test");
        user.setPhone("+7999" + (phoneCounter++)); // Уникальный номер
        user.setRole(role);
        return userRepository.save(user);
    }

    private void authenticate(String email, UserEntity user) {
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder().username(email).password(user.getPassword()).roles(user.getRole().name()).build();
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
    }

    @Test
    void getComments_AdExists_ReturnsComments() {
        CommentEntity comment1 = new CommentEntity();
        comment1.setText("Comment 1");
        comment1.setAuthor(user);
        comment1.setAd(ad);
        comment1.setCreatedAt(LocalDateTime.now());
        commentRepository.save(comment1);

        CommentEntity comment2 = new CommentEntity();
        comment2.setText("Comment 2");
        comment2.setAuthor(user);
        comment2.setAd(ad);
        comment2.setCreatedAt(LocalDateTime.now());
        commentRepository.save(comment2);

        Comments result = commentService.getComments(ad.getPk());

        assertNotNull(result);
        assertEquals(2, result.getCount());
        assertTrue(result.getResults().stream().anyMatch(c -> c.getText().equals("Comment 1")));
        assertTrue(result.getResults().stream().anyMatch(c -> c.getText().equals("Comment 2")));
    }

    @Test
    void addComment_ValidData_CreatesComment() {
        CreateOrUpdateComment dto = new CreateOrUpdateComment();
        dto.setText("New comment");

        Comment comment = new Comment();
        comment.setText(dto.getText());

        authenticate(USER_EMAIL, user);

        Comment added = commentService.addComment(ad.getPk(), comment, USER_EMAIL);

        assertNotNull(added);
        assertEquals("New comment", added.getText());
        assertEquals(user.getId(), added.getAuthor());
        assertTrue(commentRepository.findById(added.getPk()).isPresent());
    }

    @Test
    void updateComment_ByAuthor_UpdatesSuccessfully() {
        CommentEntity comment = new CommentEntity();
        comment.setText("Old text");
        comment.setAuthor(user);
        comment.setAd(ad);
        comment.setCreatedAt(LocalDateTime.now());
        comment = commentRepository.save(comment);

        Comment updatedComment = new Comment();
        updatedComment.setText("Updated text");

        authenticate(USER_EMAIL, user);

        Comment result = commentService.updateComment(ad.getPk(), comment.getPk(), updatedComment, USER_EMAIL);

        assertEquals("Updated text", result.getText());
        CommentEntity saved = commentRepository.findById(comment.getPk()).orElse(null);
        assertNotNull(saved);
        assertEquals("Updated text", saved.getText());
    }

    @Test
    void updateComment_ByAdmin_UpdatesSuccessfully() {
        CommentEntity comment = new CommentEntity();
        comment.setText("Old text");
        comment.setAuthor(user);
        comment.setAd(ad);
        comment.setCreatedAt(LocalDateTime.now());
        comment = commentRepository.save(comment);

        Comment updatedComment = new Comment();
        updatedComment.setText("Admin updated");

        authenticate(ADMIN_EMAIL, admin);

        Comment result = commentService.updateComment(ad.getPk(), comment.getPk(), updatedComment, ADMIN_EMAIL);

        assertEquals("Admin updated", result.getText());
    }
}