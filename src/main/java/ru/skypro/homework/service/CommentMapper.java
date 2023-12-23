package ru.skypro.homework.service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.model.CommentEntity;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mappings({
            @Mapping(source = "id", target = "pk"),
            @Mapping(target = "author", expression = "java(commentEntity.getAuthor().getId())")
    })

    Comment commentEntityToDTO(CommentEntity commentEntity);

    default Comments commentToDTOCommentList(List<CommentEntity> commentsList){
        List<Comment> comList = commentsList.stream().map(this::commentEntityToDTO).collect(Collectors.toList());
        Comments result = new Comments();
        result.setResults(comList);
        result.setCount(comList.size());
        return result;
    };
}
