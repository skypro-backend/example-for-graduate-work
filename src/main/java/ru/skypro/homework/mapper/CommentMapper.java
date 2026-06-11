package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.comment.CommentDto;
import ru.skypro.homework.dto.comment.CommentsDto;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDto;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentMapper {

    public CommentDto toDto(Comment comment) {
        if (comment == null) {
            return null;
        }

        CommentDto dto = new CommentDto();
        dto.setPk(toInt(comment.getId()));
        dto.setText(comment.getText());

        if (comment.getCreatedAt() != null) {
            dto.setCreatedAt(comment.getCreatedAt().toEpochMilli());
        }

        User author = comment.getAuthor();
        if (author != null) {
            dto.setAuthor(toInt(author.getId()));
            dto.setAuthorFirstName(author.getFirstName());
            dto.setAuthorImage(author.getImage());
        }

        return dto;
    }

    public List<CommentDto> toDtoList(List<Comment> comments) {
        if (comments == null) {
            return null;
        }
        return comments.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Comment toEntity(CreateOrUpdateCommentDto dto) {
        if (dto == null) {
            return null;
        }

        Comment comment = new Comment();
        comment.setText(dto.getText());
        return comment;
    }

    public void updateEntity(CreateOrUpdateCommentDto dto, Comment comment) {
        if (dto == null || comment == null) {
            return;
        }
        comment.setText(dto.getText());
    }

    public CommentsDto toCommentsDto(List<Comment> comments) {
        List<CommentDto> commentDtos = toDtoList(comments);
        Integer count = commentDtos != null ? commentDtos.size() : 0;
        return new CommentsDto(count, commentDtos);
    }

    private Integer toInt(Long value) {
        return value == null ? null : value.intValue();
    }
}
