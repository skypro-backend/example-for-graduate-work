package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.entity.CommentEntity;

/**
 * маппер для {@link CommentEntity} готовый dto {@link CommentDTO}
 */
@Mapper(componentModel = "spring")
public interface CommentMapper {


  @Mapping(target = "createdAt", source = "createdAt", dateFormat = "dd-MM-yyyy HH:mm:ss")
  @Mapping(target = "author.id", source = "author")
  @Mapping(target = "id", source = "pk")
  CommentEntity toEntity(CommentDTO commentDTO);

//  CommentDTO toDTO(CommentEntity commentEntity);

//    CommentEntity toEntity(CommentDTO commentDTO);

    @Mapping(target = "createdAt", source = "createdAt", dateFormat = "dd-MM-yyyy HH:mm:ss")
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "pk", source = "id")
    CommentDTO toDTO(CommentEntity commentEntity);


//    Collection<CommentEntity> toEntityList(Collection<CommentDTO> CommentDTOS);

//    Collection<CommentDTO> toDTOList(Collection<CommentEntity> CommentEntities);

}
