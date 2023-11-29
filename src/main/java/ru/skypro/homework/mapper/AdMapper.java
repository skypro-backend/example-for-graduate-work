package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.entity.AdEntity;

@Mapper
public interface AdMapper {

    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);

    @Mapping(source = "id", target = "pkAdId")
    @Mapping(source = "author", target = "authorId")
    Ad adToAdDTO(AdEntity adEntity);

    @Mapping(source = "pkAdId", target = "id")
    @Mapping(source = "authorId", target = "author")
    AdEntity adDTOToAd(Ad adDTO);
}
