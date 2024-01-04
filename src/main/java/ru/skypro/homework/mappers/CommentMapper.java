package ru.skypro.homework.mappers;

import org.modelmapper.ModelMapper;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;

import java.time.format.DateTimeFormatter;

public class CommentMapper {
    private static UserRepository userRepository;

    public CommentMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static CommentDto commentToCommentDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setPk(comment.getId());
        dto.setAuthor(comment.getAuthor().getId());
        dto.setAuthorFirstName(comment.getAuthor().getFirstName());
        dto.setAuthorImage(comment.getAuthor().getImageUrl());
        dto.setCreatedAt(comment.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        dto.setText(comment.getText());
        return dto;
    }
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
