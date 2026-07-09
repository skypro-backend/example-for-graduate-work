package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Mapper(componentModel = "spring")
public interface CommentMapper {

    //Как клиент создаёт объект? → DTO → Entity - CreateOrUpdateComment

    CommentEntity toComment(CreateOrUpdateComment dto);


    //Как клиент получает объект? → Entity → DTO - CommentEntity , Comments

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorImage", expression = "java(entity.getAuthor().getImage() != null ? \"/images/\" + entity.getAuthor().getImage() : null)")
    Comment toCommentDto(CommentEntity entity);

    //Как клиент обновляет объект? → DTO → Entity (существующий) CreateOrUpdateComment

    void toUpdateCommentFromDto(@MappingTarget CommentEntity entity, CreateOrUpdateComment dto);

    default Long map(LocalDateTime dateTime) {
        return dateTime == null ? null : dateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
    }

}
