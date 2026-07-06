package ru.skypro.homework.mapper;

import org.mapstruct.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.ad.Ad;
import ru.skypro.homework.ad.CreateOrUpdateAd;
import ru.skypro.homework.ad.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;

@Component
public interface AdMapper {

    @Mappings({
            @Mapping(source = "id", target = "pk"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "price", target = "price"),
            @Mapping(source = "imageUrl", target = "image"),
            @Mapping(source = "author.id", target = "author")
    })
    Ad entityToDto(AdEntity entity);

    @Mappings({
            @Mapping(source = "id", target = "pk"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "price", target = "price"),
            @Mapping(source = "imageUrl", target = "image"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "author.firstName", target = "authorFirstName"),
            @Mapping(source = "author.lastName", target = "authorLastName"),
            @Mapping(source = "author.username", target = "email"),
            @Mapping(source = "author.phone", target = "phone")
    })
    ExtendedAd entityToExtendedDto(AdEntity entity);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "author", ignore = true),
            @Mapping(target = "imageUrl", ignore = true)
    })
    AdEntity dtoToEntity(CreateOrUpdateAd dto);

    @InheritInverseConfiguration
    void updateFromDto(CreateOrUpdateAd source, @MappingTarget AdEntity dest);

    default void saveImage(AdEntity ad, MultipartFile file) {
        String url = "https://...";
        ad.setImageUrl(url);
    }
}