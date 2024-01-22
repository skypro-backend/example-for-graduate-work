package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.model.Avatar;
import ru.skypro.homework.model.Comment;
/**
 * <b> Маппер комментариев </b> <p>
 * Контролируется MapStruct
 */
@Mapper(componentModel = "spring")
public interface CommentMapper {
    String address = "/users/image/";

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "id", source = "pk")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "ad", ignore = true)
    Comment commentDtoToComment(CommentDto dto);

    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(source = "id", target = "pk")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "authorImage", source = "author.avatar", qualifiedByName = "avatarToString")
    CommentDto commentToCommentDto(Comment entity);

    @Named("avatarToString")
    default String avatarToString(Avatar avatar) {
        if (avatar == null) {
            return null;
        }
        return address + avatar.getId();
    }
}
