package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.ImageDTO;
import ru.skypro.homework.entity.ImageEntity;

@Mapper(componentModel = "spring")
public interface ImageMapper {
  @Mapping(target = "path", source = "image")
  ImageEntity toEntity(ImageDTO imageDTO);
  @Mapping(target = "image", source = "path")
  ImageDTO toDTO(ImageEntity imageEntity);

}
