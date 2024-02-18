package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.entity.CommentEntity;

/**
 * CommentMapper
 * <br><i>содержит методы</i>
 * <br>- toDTO<i>(из {@link CommentEntity} в {@link Comment})</i>
 * <br>- toEntity<i>(из {@link Comment} в {@link CommentEntity})</i>
 */
@Mapper
public interface CommentMapper {
    
    Comment toDTO(CommentEntity commentEntity);

    CommentEntity toEntity(Comment commentDto);
}
