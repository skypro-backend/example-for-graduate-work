package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.entity.AdEntity;
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
}
