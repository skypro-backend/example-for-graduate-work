package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdMapper {

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", source = "author.id")
    Ad toDto(ru.skypro.homework.entity.Ad entity);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "phone", source = "author.phone")
    ExtendedAd toExtendedDto(ru.skypro.homework.entity.Ad entity);

    ru.skypro.homework.entity.Ad toEntity(CreateOrUpdateAd dto);
}
