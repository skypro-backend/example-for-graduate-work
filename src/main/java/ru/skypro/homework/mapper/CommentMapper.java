package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.comments.CommentDto;
import ru.skypro.homework.dto.comments.CreateOrUpdateComment;
import ru.skypro.homework.entity.Comment;

@Component
public class CommentMapper {

    public CommentDto commentToDto(Comment comment) {
        return CommentDto.builder()
                .pk(comment.getId())
                .author(comment.getAuthor().getId())
                .authorFirstName(comment.getAuthor().getFirstName())
                .authorImage(comment.getAuthor().getImage())
                .createdAt((int) comment.getCreatedAt().getTime())
                .text(comment.getText())
                .build();
       }

    public CreateOrUpdateComment updateComToDto(Comment comment) {
        return CreateOrUpdateComment.builder()
                .text(comment.getText())
                .build();

    }
}
