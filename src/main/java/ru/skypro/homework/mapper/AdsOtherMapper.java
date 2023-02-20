package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.entity.AdEntity;

@Mapper(componentModel = "spring", uses = {ImageMapper.class})
public interface AdsOtherMapper {

  @Mapping(target = "description", source = "description")
  @Mapping(target = "price", source = "price")
  @Mapping(target = "title", source = "title")
  CreateAds toCreateAds(AdEntity adEntity);

  @Mapping(target = "pk", source = "adEntity.id")
  @Mapping(target = "authorFirstName", source = "author.firstName")
  @Mapping(target = "authorLastName", source = "author.lastName")
  @Mapping(target = "description", source = "adEntity.description")
  @Mapping(target = "email", source = "author.email")
  @Mapping(target = "image", source = "imageEntities")
  @Mapping(target = "phone", source = "author.phone")
  @Mapping(target = "price", source = "adEntity.price")
  @Mapping(target = "title", source = "adEntity.title")
  FullAds toFullAds(AdEntity adEntity);


}
