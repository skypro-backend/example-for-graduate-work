package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.model.CommentEntity;

import java.util.Optional;

@Component
public class CommentMapper {

    public Optional<Comment> toDto(CommentEntity entity) {
        return Optional.ofNullable(entity)
                .map(e -> {
                    Comment dto = new Comment();
                    dto.setPk(e.getId());
                    dto.setText(e.getText());
                    dto.setCreatedAt(e.getCreatedAt());
                    if (e.getAuthor() != null) {
                        dto.setAuthor(e.getAuthor().getId());
                        dto.setAuthorFirstName(e.getAuthor().getFirstName());
                        dto.setAuthorImage(e.getAuthor().getImage());
                    }
                    return dto;
                });
    }


    public Optional<CommentEntity> toEntity(CreateOrUpdateComment dto) {
        return Optional.ofNullable(dto)
                .map(d -> {
                    CommentEntity entity = new CommentEntity();
                    entity.setText(d.getText());
                    entity.setCreatedAt(System.currentTimeMillis());
                    return entity;
                });
    }
}
