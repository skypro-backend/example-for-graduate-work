package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.entity.CommentEntity;

@Component
public class CommentMapper {

    public Comment toDto(CommentEntity entity) {
        Comment dto = new Comment();

        dto.setPk(entity.getId());
        dto.setAuthor(entity.getAuthor().getId());
        dto.setAuthorFirstName(entity.getAuthor().getFirstName());
        dto.setAuthorImage(entity.getAuthor().getImage());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setText(entity.getText());

        return dto;
    }
}