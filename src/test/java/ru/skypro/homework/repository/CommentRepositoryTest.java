package ru.skypro.homework.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByAdPk_CommentsExist_ReturnsComments() {
        UserEntity author = new UserEntity();
        author.setEmail("author@test.com");
        author.setPassword("pass");
        author.setRole(ru.skypro.homework.entity.Role.USER);
        author = userRepository.save(author);

        AdEntity ad = new AdEntity();
        ad.setTitle("Test Ad");
        ad.setPrice(100);
        ad.setAuthor(author);
        ad.setCreatedAt(LocalDateTime.now());
        ad = adRepository.save(ad);

        CommentEntity comment1 = new CommentEntity();
        comment1.setText("First comment");
        comment1.setAuthor(author);
        comment1.setAd(ad);
        comment1.setCreatedAt(LocalDateTime.now());

        CommentEntity comment2 = new CommentEntity();
        comment2.setText("Second comment");
        comment2.setAuthor(author);
        comment2.setAd(ad);
        comment2.setCreatedAt(LocalDateTime.now());

        commentRepository.save(comment1);
        commentRepository.save(comment2);

        List<CommentEntity> comments = commentRepository.findByAdPk(ad.getPk());

        assertNotNull(comments);
        assertEquals(2, comments.size());
        assertTrue(comments.stream().anyMatch(c -> c.getText().equals("First comment")));
        assertTrue(comments.stream().anyMatch(c -> c.getText().equals("Second comment")));
    }

    @Test
    void findByAdPk_NoComments_ReturnsEmptyList() {
        UserEntity author = new UserEntity();
        author.setEmail("author@test.com");
        author.setPassword("pass");
        author.setRole(ru.skypro.homework.entity.Role.USER);
        author = userRepository.save(author);

        AdEntity ad = new AdEntity();
        ad.setTitle("Test Ad");
        ad.setPrice(100);
        ad.setAuthor(author);
        ad.setCreatedAt(LocalDateTime.now());
        ad = adRepository.save(ad);

        List<CommentEntity> comments = commentRepository.findByAdPk(ad.getPk());

        assertNotNull(comments);
        assertTrue(comments.isEmpty());
    }
}