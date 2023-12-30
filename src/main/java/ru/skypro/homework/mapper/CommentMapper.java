package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.Image;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    String address = "/users/image/";

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "id", source = "pk")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "ad", ignore = true)
    Comment toEntity(CommentDTO dto);

    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(source = "id", target = "pk")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "authorImage", source = "author.image", qualifiedByName = "imageToString")
    CommentDTO toDto(Comment entity);

    @Named("imageToString")
    default String imageToString(Image image) {
        if (image == null) {
            return null;
        }
        return address + image.getId();
    }
}
