package ru.skypro.homework.mapper;

import java.util.Collection;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;

/**
 * маппер для {@link AdEntity} готовый рекорд {@link AdsDTO}
 */
@Mapper(componentModel = "spring", uses = {UserEntity.class, CommentEntity.class, ImageEntity.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AdMapper {


  @Mapping(target = "UserEntity.setId()", source = "AdsDTO.getAuthor()")
  @Mapping(target = "ImageEntity.setPath()", source = "AdsDTO.getImage()")
  @Mapping(target = "AdsEntity.setPk()", source = "AdsDTO.getPk()")
  @Mapping(target = "AdsEntity.setPrice()", source = "AdsDTO.getPrice()")
  @Mapping(target = "AdsEntity.setTitle()", source = "AdsDTO.getTitle()")
  AdEntity toEntity(AdsDTO adDto);


  @Mapping(target = "author", source = "UserEntity.getId()")
  @Mapping(target = "image", source = "ImageEntity.getPath()")
  @Mapping(target = "pk", source = "CommentEntity.getId()")
  @Mapping(target = "price", source = "AdsEntity.getPrice()")
  @Mapping(target = "title", source = "AdsEntity.getTitle()")
  AdsDTO toDTO(AdEntity adEntity);

//  FullAds toFullAds(AdEntity adEntity);

  Collection<AdEntity> toEntityList(Collection<AdsDTO> adDTOS);

  Collection<AdsDTO> toDTOList(Collection<AdEntity> adEntities);
}
