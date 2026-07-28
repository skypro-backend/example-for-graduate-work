package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.User;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;

class CommentMapperTest {

    private final CommentMapper commentMapper = new CommentMapperImpl();

    @Test
    void toDto_shouldMapAllFieldsIncludingFlattenedAuthorData() {
        User author = new User();
        author.setId(5);
        author.setFirstName("Petr");
        author.setImage("/images/user_5.png");

        LocalDateTime createdAt = LocalDateTime.of(2026, 1, 15, 12, 30, 0);

        ru.skypro.homework.entity.Comment entity = new ru.skypro.homework.entity.Comment();
        entity.setId(10);
        entity.setText("Отличное объявление!");
        entity.setAuthor(author);
        entity.setCreatedAt(createdAt);

        Comment dto = commentMapper.toDto(entity);

        assertThat(dto.getPk()).isEqualTo(10);
        assertThat(dto.getText()).isEqualTo("Отличное объявление!");
        assertThat(dto.getAuthor()).isEqualTo(5);
        assertThat(dto.getAuthorFirstName()).isEqualTo("Petr");
        assertThat(dto.getAuthorImage()).isEqualTo("/images/user_5.png");
        assertThat(dto.getCreatedAt())
                .isEqualTo(createdAt.toInstant(ZoneOffset.UTC).toEpochMilli());
    }

    @Test
    void toEntity_fromCreateOrUpdateComment_shouldMapTextOnly() {
        CreateOrUpdateComment dto = new CreateOrUpdateComment();
        dto.setText("Комментарий длиной больше восьми символов");

        ru.skypro.homework.entity.Comment entity = commentMapper.toEntity(dto);

        assertThat(entity.getText()).isEqualTo("Комментарий длиной больше восьми символов");
        assertThat(entity.getAuthor()).isNull();
        assertThat(entity.getAd()).isNull();
        assertThat(entity.getCreatedAt()).isNull();
    }
}