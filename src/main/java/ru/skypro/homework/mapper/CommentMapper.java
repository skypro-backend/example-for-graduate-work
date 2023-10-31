package ru.skypro.homework.mapper;

import lombok.extern.slf4j.Slf4j;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import ru.skypro.homework.dto.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.Image;

@Slf4j
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CommentMapper {

    @Mapping(target = "author", source = "user.id")
    @Mapping(target = "authorImage", source = "user.image",
            qualifiedByName = "imageMapping")
    @Mapping(target = "authorFirstName", source = "user.firstName")
    @Mapping(target = "pk", source = "id")
    public abstract Comment commentEntityToComment(CommentEntity commentEntity);

    @Mapping(target = "createdAt", expression = "java(System.currentTimeMillis())")
    public abstract CommentEntity createOrUpdateCommentToCommentEntity(CreateOrUpdateComment createOrUpdateComment);


    protected String getImageUrl(Image image) {
        if (image == null) {
            return null;
        }
        return "/images/" + image.getId();
    }

    @Named("imageMapping")
    public String imageMapping(Image image) {
        if (image == null) {
            return null;
        }
        return getImageUrl(image);
    }
}