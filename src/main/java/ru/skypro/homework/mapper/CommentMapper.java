package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.model.CommentEntity;

@Component
public class CommentMapper {

    public Comment toDto(CommentEntity entity) {
        if (entity == null) {
            return null;
        }

        Comment dto = new Comment();
        dto.setPk(entity.getId());
        dto.setText(entity.getText());
        dto.setCreatedAt(entity.getCreatedAt());

        if (entity.getAuthor() != null) {
            dto.setAuthor(entity.getAuthor().getId());
            dto.setAuthorFirstName(entity.getAuthor().getFirstName());
            dto.setAuthorImage(entity.getAuthor().getImage());
        }

        return dto;
    }

    public CommentEntity toEntity(CreateOrUpdateComment dto) {
        if (dto == null) {
            return null;
        }

        CommentEntity entity = new CommentEntity();
        entity.setText(dto.getText());
        entity.setCreatedAt(System.currentTimeMillis());

        return entity;
    }
}
