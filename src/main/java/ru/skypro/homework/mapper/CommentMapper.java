package ru.skypro.homework.mapper;

import org.mapstruct.MappingConstants;
import ru.skypro.homework.dto.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.utilities.Utils;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {

    CommentMapper INSTANCE = getMapper(CommentMapper.class);

    @Mapping(target = "author", source = "user.id")
    @Mapping(target = "authorImage", source = "user.image")
    @Mapping(target = "authorFirstName", source = "user.firstName")
    @Mapping(target = "pk", source = "id")
    Comment commentEntityToComment(CommentEntity commentEntity);

    default CommentEntity createOrUpdateCommentToCommentEntity (CreateOrUpdateComment createOrUpdateComment) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setText(createOrUpdateComment.getText());
        commentEntity.setCreatedAt(Utils.getCurrentTime());
        return commentEntity;
    }

}