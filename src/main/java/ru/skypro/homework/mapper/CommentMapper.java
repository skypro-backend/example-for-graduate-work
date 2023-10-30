package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.*;
import org.mapstruct.Mapper;
import ru.skypro.homework.model.CommentModel;

import java.time.Instant;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = Instant.class)
public interface CommentMapper {

    @Mapping(target = "author", source = "author", qualifiedByName = "authorToInteger")
    @Mapping(target = "authorFirstName", source = "author", qualifiedByName = "authorFirstNameFromAuthor")
    @Mapping(target = "authorImage", source = "author", qualifiedByName = "authorImageToString")
    default CommentDto toCommentDto(Comment comment) {
        return null;
    }

//    List<CommentDto> toCommentsDto(List<Comment> comments);

/*
    @Mapping(target = "createdAt", expression = "java(Instant.now().toEpochMilli())")
    Comment toCommentEntityFromCreateOrUpdateComment(CreateOrUpdateCommentDto createOrUpdateCommentDto);
*/

    @Named("authorImageToString")
    default String authorImageToString(User user) {
        if (user.getImage() == null) {
            return null;
        }
        return "/users/image/" + user.getImage().getId();
    }

    @Named("authorToInteger")
    default Integer authorToInteger(User user) {
        return user.getId();
    }

    @Named("authorFirstNameFromAuthor")
    default String authorFirstNameFromAuthor(User author) {
        return author.getFirstName();
    }

}