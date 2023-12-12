package ru.skypro.homework.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.entity.CommentEntity;

import java.util.List;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface CommentMapper {

   CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Named("idToUrl")
    static String idToUrl(int id) {
        return "/image/" + id;
    }

    @Mapping(source = "author.pk", target = "author")
    @Mapping(source = "authorImage.id", target = "authorImage", qualifiedByName = "idToUrl")
    Comment commentToCommentDTO(CommentEntity commentEntity);

    List<Comment> listCommentToListCommentDTO(List<CommentEntity> commentEntityList);
}