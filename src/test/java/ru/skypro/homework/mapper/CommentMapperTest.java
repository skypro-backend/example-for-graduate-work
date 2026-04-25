package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.entity.AdEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CommentMapperTest {

    @Autowired
    private CommentMapper commentMapper;

    @Test
    void toDto_ShouldMapCommentEntityToCommentDto() {
        UserEntity author = new UserEntity();
        author.setId(1);
        author.setFirstName("Ivan");
        author.setImagePath("/users/1/image");

        AdEntity ad = new AdEntity();
        ad.setPk(100);

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setPk(10);
        commentEntity.setText("Great deal!");
        commentEntity.setAuthor(author);
        commentEntity.setAd(ad);
        commentEntity.setCreatedAt(LocalDateTime.now());

        Comment dto = commentMapper.toDto(commentEntity);

        assertNotNull(dto);
        assertEquals(10, dto.getPk());
        assertEquals("Great deal!", dto.getText());
        assertEquals(1, dto.getAuthor());
        assertEquals("Ivan", dto.getAuthorFirstName());
        assertEquals("/users/1/image", dto.getAuthorImage());
        assertNotNull(dto.getCreatedAt());
    }
}