package ru.skypro.homework.mapper;

import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.entity.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "author", source = "user.id")
    @Mapping(target = "authorImage", source = "user.image", qualifiedByName = "imageMapping")
    @Mapping(target = "authorFirstName", source = "user.firstName")
    @Mapping(target = "pk", source = "id")
    Comment comment(Comment comment);

    @Named("imageMapping")
    default String imageMapping(Image image) {
        if (image == null) {
            return null;
        }
        return "/images/" + image.getFileName();
    }

}