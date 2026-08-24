package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdMapper {

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "image", target = "image")
    Ad toDto(AdEntity adEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "comments", ignore = true)
    AdEntity toEntity(CreateOrUpdateAd createOrUpdateAd);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "comments", ignore = true)
    AdEntity updateAdEntity(CreateOrUpdateAd createOrUpdateAd, @MappingTarget AdEntity adEntity);

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "author.lastName", target = "authorLastName")
    @Mapping(source = "author.username", target = "email")
    @Mapping(source = "author.phone", target = "phone")
    @Mapping(source = "image", target = "image")
    ExtendedAd toExtendedDto(AdEntity adEntity);

    default AdEntity createAdEntity(CreateOrUpdateAd createOrUpdateAd, UserEntity author, String imagePath) {
        AdEntity adEntity = toEntity(createOrUpdateAd);
        adEntity.setAuthor(author);
        adEntity.setImage(imagePath);
        return adEntity;
    }
}