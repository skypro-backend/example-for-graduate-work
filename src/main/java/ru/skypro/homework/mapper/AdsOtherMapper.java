package ru.skypro.homework.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;

@Mapper(componentModel = "spring", uses = {ImageMapper.class})
public interface AdsOtherMapper {


  @Mapping(target = "description", source = "description")
  @Mapping(target = "price", source = "price")
  @Mapping(target = "title", source = "title")
  CreateAds toCreateAds(AdEntity adEntity);

  @Mapping(target = "pk", source = "id")
  @Mapping(target = "authorFirstName", source = "author.firstName")
  @Mapping(target = "authorLastName", source = "author.lastName")
  @Mapping(target = "description", source = "description")
  @Mapping(target = "email", source = "author.email")
  @Mapping(target = "image", expression = "java(setImage(adEntity.getImageEntities()))")
  @Mapping(target = "phone", source = "author.phone")
  @Mapping(target = "price", source = "price")
  @Mapping(target = "title", source = "title")
  FullAds toFullAds(AdEntity adEntity);

  default List<String> setImage(List<ImageEntity> imageEntities) {
    if (imageEntities == null || imageEntities.size() == 0) {
      return null;
    }
    return imageEntities
        .stream()
        .map(ImageEntity::getPath)
        .collect(Collectors.toList());
  }

}
