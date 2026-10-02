package ru.skypro.homework;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import ru.skypro.homework.api.dto.Comment;
import ru.skypro.homework.api.dto.CreateOrUpdateComment;
import ru.skypro.homework.db.model.AdModel;
import ru.skypro.homework.db.model.CommentModel;
import ru.skypro.homework.db.model.UserModel;
import ru.skypro.homework.db.model.UserRole;
import ru.skypro.homework.db.repository.AdRepository;
import ru.skypro.homework.db.repository.CommentRepository;
import ru.skypro.homework.db.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void userCanNotUpdateAnotherUserCommentButAdminCan() {
        UserModel owner = createUser("owner");
        UserModel anotherUser = createUser("another");
        AdModel ad = createAd(owner);
        CommentModel comment = createComment(ad, owner);
        CreateOrUpdateComment updateComment = createUpdateComment();

        assertThrows(AccessDeniedException.class,
                () -> commentService.updateComment(anotherUser.getEmail(), ad.getId(), comment.getId(), updateComment));

        Comment updatedComment = commentService.updateComment("admin@gmail.com", ad.getId(), comment.getId(), updateComment);

        assertEquals("Updated comment", updatedComment.getText());
    }

    @Test
    void userCanNotDeleteAnotherUserCommentButAdminCan() {
        UserModel owner = createUser("owner");
        UserModel anotherUser = createUser("another");
        AdModel ad = createAd(owner);
        CommentModel comment = createComment(ad, owner);

        assertThrows(AccessDeniedException.class,
                () -> commentService.deleteComment(anotherUser.getEmail(), ad.getId(), comment.getId()));

        commentService.deleteComment("admin@gmail.com", ad.getId(), comment.getId());

        assertEquals(0, commentService.getComments(ad.getId()).getCount());
    }

    private UserModel createUser(String prefix) {
        String unique = UUID.randomUUID().toString().substring(0, 8);
        return userRepository.save(UserModel.builder()
                .email(prefix + unique + "@gmail.com")
                .password("password")
                .firstName("User")
                .lastName("Userov")
                .phone("+7 999 111-22-33")
                .role(UserRole.USER)
                .build());
    }

    private AdModel createAd(UserModel owner) {
        return adRepository.save(AdModel.builder()
                .author(owner)
                .title("Test title")
                .price(1000)
                .description("Test description")
                .image("/ads/0/image")
                .build());
    }

    private CommentModel createComment(AdModel ad, UserModel author) {
        return commentRepository.save(CommentModel.builder()
                .ad(ad)
                .author(author)
                .text("Test comment")
                .createdAt(LocalDateTime.now())
                .build());
    }

    private CreateOrUpdateComment createUpdateComment() {
        CreateOrUpdateComment updateComment = new CreateOrUpdateComment();
        updateComment.setText("Updated comment");
        return updateComment;
    }
}
