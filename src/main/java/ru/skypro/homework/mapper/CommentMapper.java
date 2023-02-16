package ru.skypro.homework.mapper;

import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.entity.CommentEntity;

/**
 * маппер для {@link CommentEntity}
 * готовый dto {@link CommentDTO}
 */
@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "UserEntity.id", source = "author")
    @Mapping(target = "createdAt", source = "createdAt", dateFormat = "dd-MM-yyyy HH:mm:ss")
    @Mapping(target = "AdEntity.id", source = "pk")
    @Mapping(target = "text", source = "text")
    CommentEntity toEntity(CommentDTO commentDTO);
    @Mapping(target = "author", source = "UserEntity.id")
    @Mapping(target = "createdAt", source = "createdAt", dateFormat = "dd-MM-yyyy HH:mm:ss")
    @Mapping(target = "pk", source = "AdEntity.id")
    @Mapping(target = "text", source = "text")
    CommentDTO toDTO(CommentEntity commentEntity);

    Collection<CommentEntity> toEntityList(Collection<CommentDTO> CommentDTOS);

    Collection<CommentDTO> toDTOList(Collection<CommentEntity> CommentEntities);

}
