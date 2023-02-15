package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.entity.CommentEntity;

import java.util.Collection;

/**
 * маппер для {@link CommentEntity}
 * готовый dto {@link CommentDTO}
 */
@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentEntity toEntity(CommentDTO commentDTO);

    CommentDTO toDTO(CommentEntity commentEntity);

    Collection<CommentEntity> toEntityList(Collection<CommentDTO> CommentDTOS);

    Collection<CommentDTO> toDTOList(Collection<CommentEntity> CommentEntities);

}
