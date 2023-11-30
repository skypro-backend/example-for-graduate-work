package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.entity.AdEntity;

@Mapper
public interface AdMapper {

//    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);
//
//    @Mapping(target = "author", ignore = true)
//    @Mapping(target = "image", ignore = true)
//    AdEntity createOrUpdateAdToAdEntity(CreateOrUpdateAd createOrUpdateAd);
//
//    @Mapping(target = "pk", ignore = true)
//    @Mapping(target = "author", ignore = true)
//    AdEntity updateAdEntityFromCreateOrUpdateAd(AdEntity existingAdEntity, CreateOrUpdateAd createOrUpdateAd);
//
//    @Mapping(target = "title", source = "title")
//    @Mapping(target = "price", source = "price")
//    @Mapping(target = "description", source = "description")
//    Ad createOrUpdateAdFromAdEntity(AdEntity adEntity);

    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);

    @Mapping(source = "id", target = "pkAdId")
    @Mapping(source = "author", target = "authorId")
    Ad adToAdDTO(AdEntity adEntity);

    @Mapping(source = "pkAdId", target = "id")
    @Mapping(source = "authorId", target = "author")
    @Mapping(target = "description", ignore = true)
    AdEntity adDTOToAd(Ad adDTO);
}
