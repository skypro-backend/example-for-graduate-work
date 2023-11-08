package ru.skypro.homework.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;

@Mapper
public interface CommentMapper {
    CommentDto INSTANCE = Mappers.getMapper(CommentDto.class);
    @Mapping(source = "user.id", target = "author")
    @Mapping(source = "user.image", target = "authorImage")
    @Mapping(source = "user.firstName", target = "authorFirstName")
    CommentDto toDto(Comment comment, User user);
    @Mapping(target = "user.id", source = "author")
    @Mapping(target = "user.image",  source= "authorImage")
    @Mapping(target = "user.firstName", source = "authorFirstName")
    Comment toModel(CommentDto commentDto);
}
