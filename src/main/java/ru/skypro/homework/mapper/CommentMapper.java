package ru.skypro.homework.mapper;

import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import ru.skypro.homework.dto.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.utilities.Utils;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CommentMapper {

    @Mapping(target = "author", source = "user.id")
    @Mapping(target = "authorImage", source = "user.image",
            qualifiedByName = "imageMapping")
    @Mapping(target = "authorFirstName", source = "user.firstName")
    @Mapping(target = "pk", source = "id")
    public abstract Comment commentEntityToComment(CommentEntity commentEntity);

    public CommentEntity createOrUpdateCommentToCommentEntity(CreateOrUpdateComment createOrUpdateComment) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setText(createOrUpdateComment.getText());
        commentEntity.setCreatedAt(Utils.getCurrentTime());
        return commentEntity;
    }

    protected String getImageUrl(Image image) {
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