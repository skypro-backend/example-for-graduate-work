package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.entity.Avatar;
import ru.skypro.homework.entity.Comment;

@Mapper
public interface CommentMapper {
    String USER_AVATAR = "/users/avatar/";
    CommentMapper INSTANSE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "id", source = "pk")
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "ads", ignore = true)
    Comment toEntity(CommentDto dto);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorImage", source = "author.avatar", qualifiedByName = "avatarMapping")
    @Mapping(target = "createdAt", source = "createdAt")
    CommentDto toDto(Comment entity);

    @Named("avatarMapping")
    default String avatarMapping(Avatar avatar) {
        if (avatar == null) {
            return null;
        }
        return USER_AVATAR + avatar.getId();
    }

}
