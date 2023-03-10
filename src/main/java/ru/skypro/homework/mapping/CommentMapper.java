package ru.skypro.homework.mapping;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.entity.CommentEntity;


@Mapper(componentModel = "spring")
public interface  CommentMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "text", source = "text")
    @Mapping(target = "createdAt", source = "createdAt")
     CommentEntity CommentDtoToEntity(Comment comment);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "text", source = "text")
    @Mapping(target = "createdAt", source = "createdAt")
     Comment CommentEntityToDto(CommentEntity commentEntity);

}
