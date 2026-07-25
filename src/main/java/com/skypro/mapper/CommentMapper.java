package com.skypro.mapper;

import com.skypro.dto.Comment;
import com.skypro.dto.CreateOrUpdateComment;
import com.skypro.entity.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * MapStruct-маппер для преобразования между сущностью {@link CommentEntity}
 * и DTO {@link Comment} и {@link CreateOrUpdateComment}.
 * <p>
 * Используется в сервисном слое для преобразования данных комментариев.
 * </p>
 */
@Mapper(componentModel = "spring")
public interface CommentMapper {

    /**
     * Преобразует {@link CommentEntity} в {@link Comment}.
     * <p>
     * Маппинг полей:
     * <ul>
     *   <li>{@code author.id} → {@code author} (ID автора комментария)</li>
     * </ul>
     * </p>
     *
     * @param commentEntity сущность комментария
     * @return DTO {@link Comment}
     */
    @Mapping(source = "author.id", target = "author")
    Comment toComment(CommentEntity commentEntity);

    /**
     * Преобразует {@link CreateOrUpdateComment} в {@link CommentEntity}.
     * <p>
     * Поля {@code id}, {@code author}, {@code ad} и {@code createdAt}
     * игнорируются, так как они устанавливаются сервисным слоем.
     * </p>
     *
     * @param createOrUpdateComment DTO с данными для создания/обновления
     * @return сущность {@link CommentEntity}
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "ad", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    CommentEntity toEntity(CreateOrUpdateComment createOrUpdateComment);
}
