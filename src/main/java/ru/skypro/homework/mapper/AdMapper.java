package ru.skypro.homework.mapper;


import org.mapstruct.Mapper;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.entity.AdEntity;

/**
 * маппер для {@link AdEntity} готовый рекорд {@link AdsDTO}
 */
@Mapper(componentModel = "spring")
public interface AdMapper {

//  AdEntity toEntity(AdsDTO adDto);

//  AdsDTO toDTO(AdEntity adEntity);

//  FullAds toFullAds(AdEntity adEntity);

//  Collection<AdEntity> toEntityList(Collection<AdsDTO> adDTOS);

//  Collection<AdsDTO> toDTOList(Collection<AdEntity> adEntities);
}
