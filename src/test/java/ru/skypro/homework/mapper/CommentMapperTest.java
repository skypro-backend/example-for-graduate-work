package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = CommentMapperImpl.class)
class CommentMapperTest {

    @Autowired
    private CommentMapper mapper;

    @Test
    void toDto_shouldMapCommentEntityToCommentDto() {
        // Given
        UserEntity author = new UserEntity();
        author.setId(1);
        author.setFirstName("Иван");

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setPk(100);
        commentEntity.setText("Отличное объявление!");
        commentEntity.setCreatedAt(LocalDateTime.of(2023, 10, 1, 12, 0, 0));
        commentEntity.setAuthor(author);

        // When
        Comment result = mapper.toDto(commentEntity);

        // Then
        assertThat(result.getPk()).isEqualTo(100);
        assertThat(result.getText()).isEqualTo("Отличное объявление!");
        assertThat(result.getAuthor()).isEqualTo(1);
        assertThat(result.getAuthorFirstName()).isEqualTo("Иван");
        assertThat(result.getAuthorImage()).isEqualTo("/users/1/image");

        long expectedTimestamp = commentEntity.getCreatedAt().atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli();
        assertThat(result.getCreatedAt()).isEqualTo(expectedTimestamp);
    }

    @Test
    void toEntity_shouldMapCommentDtoToCommentEntity() {
        // Given
        Comment comment = new Comment();
        comment.setText("Хочу купить!");

        // When
        CommentEntity result = mapper.toEntity(comment);

        // Then
        assertThat(result.getText()).isEqualTo("Хочу купить!");
        assertThat(result.getPk()).isNull(); // игнорируется, генерируется БД
        assertThat(result.getCreatedAt()).isNotNull(); // устанавливается через expression = "java(LocalDateTime.now())"
        assertThat(result.getAd()).isNull(); // устанавливается сервисом
        assertThat(result.getAuthor()).isNull(); // устанавливается из контекста аутентификации
    }
}