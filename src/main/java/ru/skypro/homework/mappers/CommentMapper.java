package ru.skypro.homework.mappers;

import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;

import java.time.format.DateTimeFormatter;
@Component

@AllArgsConstructor
public class CommentMapper {
    private static UserRepository userRepository;

     public CommentDto commentToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setPk(comment.getId());
        commentDto.setAuthor(comment.getAuthor().getId());
        commentDto.setAuthorImage("/image/" + comment.getAuthor().getImage().getId());
        commentDto.setAuthorFirstName(comment.getAuthor().getFirstName());
        commentDto.setCreatedAt(String.valueOf(comment.getCreatedAt()));
        commentDto.setText(comment.getText());
        return commentDto;
    }
/*    public CommentDto commentToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setPk(comment.getId());
        commentDto.setAuthor(comment.getUser().getId());
        commentDto.setAuthorImage("/avatars/" + comment.ggetId());etUser().
        commentDto.setAuthorFirstName(comment.getUser().getFirstName());
        commentDto.setCreatedAt(comment.getCreatedAt());
        commentDto.setText(comment.getText());
        return commentDto;
    }*/
    public Comment commentDtoToComment(CommentDto dto) {
        Comment comment = new Comment();
        comment.setId(dto.getPk());
        comment.setAuthor(userRepository.findById(dto.getAuthor()).orElse(null));
        comment.setText(dto.getText());
        return comment;
    }

    public CreateOrUpdateCommentDto createOrUpdateDtoFromComment(Comment comment) {
        CreateOrUpdateCommentDto dto = new CreateOrUpdateCommentDto();
        dto.setText(comment.getText());
        return dto;
    }

    public Comment createOrUpdateCommentFromDto(CreateOrUpdateCommentDto dto) {
        Comment comment = new Comment();
        comment.setText(dto.getText());
        return comment;
    }
}
