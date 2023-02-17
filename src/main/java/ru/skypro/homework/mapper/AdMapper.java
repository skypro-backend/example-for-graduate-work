package ru.skypro.homework.mapper;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;

/**
 * маппер для {@link AdEntity} готовый рекорд {@link AdsDTO}
 */
@Mapper(componentModel = "spring", uses = {UserEntity.class, CommentEntity.class, ImageEntity.class})
public interface AdMapper {



//  @Mapping(target = "imageEntities", ignore = true)
//  AdEntity toEntity(AdsDTO adDto);
//
//
//  @Mapping(target = "pk", ignore = true)
//  AdsDTO toDTO(AdEntity adEntity);

//  @Mapping(target = "pk", source = "UserEntity.id", qualifiedBy = )
//  @Mapping(target = "phone", source = "UserEntity.phone")
//  @Mapping(target = "image", source = "ImageEntity.path")
//  @Mapping(target = "email", source = "email")
//  @Mapping(target = "price", source = "price")
//  @Mapping(target = "title", source = "title")
//  @Mapping(target = "description", source = "description")
//  @Mapping(target = "authorFirstName", source = "firstName")
//  @Mapping(target = "authorLastName", source = "lastName")
//  FullAds toFullAds(AdEntity adEntity);

  @Mapping(target = "description", source = "description")
  @Mapping(target = "price", source = "price")
  @Mapping(target = "title", source = "title")
  CreateAds toCreateAds(AdEntity adEntity);

//  Collection<AdEntity> toEntityList(Collection<AdsDTO> adDTOS);
//
//  Collection<AdsDTO> toDTOList(Collection<AdEntity> adEntities);

  @Mapping(target = "author.id", source = "author")
  @Mapping(target = "description", constant = "Неполная реклама")
  @Mapping(target = "imageEntities", expression = "java(setImageEntities(adDto.getImage()))")
  AdEntity toEntity(AdsDTO adDto);


  @Mapping(target = "author", source = "author.id")
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
