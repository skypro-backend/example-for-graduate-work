package ru.skypro.homework.service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.model.CommentEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(source = "id", target = "pk")
    Comment commentEntityToDTO(CommentEntity commentEntity);

    @Mappings({
            @Mapping(target = "count", expression = "java(commentsList.size()"),
            @Mapping(source = "commentsList", target = "results")
    })
    Comments commentToDTOCommentList(List<CommentEntity> commentsList);
}
