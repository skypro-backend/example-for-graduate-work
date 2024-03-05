package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.entity.Avatar;
import ru.skypro.homework.entity.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    String address = "/users/image/";

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "id", source = "pk")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "listing", ignore = true)
    Comment commentDtoToComment(CommentDTO dto);

    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(source = "id", target = "pk")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "authorImage", source = "author.avatar", qualifiedByName = "avatarToString")
    CommentDTO commentToCommentDto(Comment entity);

    @Named("avatarToString")
    default String avatarToString(Avatar avatar) {
        if (avatar == null) {
            return null;
        }
        return address + avatar.getId();
    }
}
