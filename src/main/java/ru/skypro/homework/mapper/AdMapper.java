package ru.skypro.homework.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.AdEntity;


import java.util.List;

@Mapper(componentModel = "spring")
public interface AdMapper {

    @Mapping(target = "author", source = "adEntity.author.id")
    @Mapping(target = "pk", source = "adEntity.id")
    @Mapping(target = "image", expression = "java(\"/ads/image/\" + adEntity.getId())")
    Ad toDto(AdEntity adEntity);

    @Mapping(target = "authorFirstName", source = "adEntity.author.firstName")
    @Mapping(target = "authorLastName", source = "adEntity.author.lastName")
    @Mapping(target = "email", source = "adEntity.author.email")
    @Mapping(target = "phone", source = "adEntity.author.phone")
    @Mapping(target = "pk", source = "adEntity.id")
    @Mapping(target = "image", expression = "java(\"/ads/image/\" + adEntity.getId())")
    ExtendedAd toExtendedDto(AdEntity adEntity);
    List<Ad> toAdDtoList(List<AdEntity> adEntities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "image", ignore = true)
    AdEntity toEntity(ru.skypro.homework.dto.CreateOrUpdateAd dto);
}
