package ru.skypro.homework.mapper;

import org.mapstruct.*;
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

    @Named("idToUrl")
    static String idToUrl(int id) {
        return "/image/" + id;
    }

    @Mapping(source = "author.pk", target = "author")
    @Mapping(source = "imageEntity.id", target = "image", qualifiedByName = "idToUrl")
    Ad adToAdDTO(AdEntity adEntity);

    AdEntity createOrUpdateAdDTOToAd(CreateOrUpdateAd createOrUpdateAd);

    List<Ad> listAdEntityToListAdDTO(List<AdEntity> adEntities);

    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "author.lastName", target = "authorLastName")
    @Mapping(source = "author.email", target = "email")
    @Mapping(source = "author.phone", target = "phone")
    @Mapping(source = "imageEntity.id", target = "image", qualifiedByName = "idToUrl")
    ExtendedAd adEntityToExtendedAdDTO(AdEntity adEntity);
}