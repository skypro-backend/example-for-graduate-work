package ru.skypro.homework.mapper;
import org.mapstruct.Mapper;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.entity.CommentEntity;


@Mapper
public class CommentMapper {
    public Comment commentEntityToComment(CommentEntity commentEntity) {
        if ( commentEntity == null ) {
            throw new NullPointerException("Ошибка маппера при создании Comment! CommentEntity == null!");
        }
//        return Comment.builder()
//                .author(commentEntity.get)
//                .build();
        return null;
    }
}

