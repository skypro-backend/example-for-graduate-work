package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.entity.AdEntity;
@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface AdMapper {

    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);

    @Mapping(source = "author.pk", target = "author")
    Ad adToAdDTO(AdEntity adEntity);

    @Mapping(source = "author", target = "author.pk")
    AdEntity adDTOToAd(Ad adDTO);
}