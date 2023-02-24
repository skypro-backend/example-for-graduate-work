package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.ImageDTO;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.ImageEntity;
/**
 * маппер для {@link ImageEntity} готовый DTO {@link ImageDTO}
 */
@Mapper(componentModel = "spring")
public interface ImageMapper {
  @Mapping(target = "path", source = "image")
  ImageEntity toEntity(ImageDTO imageDTO);
  @Mapping(target = "image", source = "path")
  ImageDTO toDTO(ImageEntity imageEntity);

}
