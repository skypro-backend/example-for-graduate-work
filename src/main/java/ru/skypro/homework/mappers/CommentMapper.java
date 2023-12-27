package ru.skypro.homework.mappers;

import org.modelmapper.ModelMapper;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;

public class CommentMapper {
    private ModelMapper modelMapper;

    public CommentMapper() {
        this.modelMapper = new ModelMapper();
    }

    // Метод для маппинга UserDto в User
    public Comment mapToComment(CommentDto commentDto) {
        return new Comment(commentDto);
    }

    // Метод для маппинга User в UserDto
    public CommentDto mapToCommentDto(Comment comment) {
        return new CommentDto(comment);
    }
}
