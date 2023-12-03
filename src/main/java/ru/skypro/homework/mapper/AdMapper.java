package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;

import java.util.List;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface AdMapper {

    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);

    @Mapping(source = "author.pk", target = "author")
    Ad adToAdDTO(AdEntity adEntity);

    @Mapping(source = "author", target = "author.pk")
    AdEntity adDTOToAd(Ad adDTO);
    AdEntity createOrUpdateAdDTOToAd(CreateOrUpdateAd createOrUpdateAd);

    List<Ad> ListAdEntityToListAdDTO(List<AdEntity> adEntities);

    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "author.lastName", target = "authorLastName")
    @Mapping(source = "author.email", target = "email")
    @Mapping(source = "author.phone", target = "phone")
    ExtendedAd AdEntityToExtendedAdDTO(AdEntity adEntity);
}