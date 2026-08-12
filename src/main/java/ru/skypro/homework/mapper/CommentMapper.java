package ru.skypro.homework.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.model.CommentEntity;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "pk", source = "id")
    @Mapping(target = "authorImage", expression = "java(commentEntity.getAuthor().getImage() != null ? \"/users/image/\" + commentEntity.getAuthor().getId() : null)")
    Comment toDto(CommentEntity commentEntity);
}
