package ru.skypro.homework.mapper;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;

/**
 * маппер для {@link AdEntity} готовый рекорд {@link AdsDTO}
 */
@Mapper(componentModel = "spring")
public interface AdMapper {


  @Mapping(target = "author.id", source = "author")
  @Mapping(target = "id", source = "pk")
  @Mapping(target = "description", constant = "Неполная реклама")
  @Mapping(target = "imageEntities", expression = "java(setImageEntities(adDto.getImage()))")
  AdEntity toEntity(AdsDTO adDto);


  @Mapping(target = "author", source = "author.id")
  @Mapping(target = "pk", source = "id")
  @Mapping(target = "image", expression = "java(setImage(adEntity.getImageEntities()))")
  AdsDTO toDTO(AdEntity adEntity);

  default List<String> setImage(List<ImageEntity> imageEntities) {
    if (imageEntities == null || imageEntities.size() == 0) {
      return null;
    }
    return imageEntities
        .stream()
        .map(ImageEntity::getPath)
        .collect(Collectors.toList());
  }

  default List<ImageEntity> setImageEntities(List<String> image) {
    if (image == null || image.size() == 0) {
      return null;
    }
    List<ImageEntity> imageEntities = new ArrayList<>();
    for (String s : image) {
      ImageEntity imageEntity = new ImageEntity();
      imageEntity.setPath(s);
      imageEntities.add(imageEntity);
    }
    return imageEntities;
  }


  FullAds toFullAds(AdEntity adEntity);

  Collection<AdEntity> toEntityList(Collection<AdsDTO> adDTOS);

  Collection<AdsDTO> toDTOList(Collection<AdEntity> adEntities);
}
