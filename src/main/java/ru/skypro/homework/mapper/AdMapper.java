package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;


@Mapper(componentModel = "spring")
public interface AdMapper {


    //Как клиент создаёт объект? → DTO → Entity - CreateOrUpdateAd

    AdEntity toAd(CreateOrUpdateAd dto);

    //Как клиент получает объект? → Entity → DTO - ExtendedAd, Ads, Ad

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "image", expression = "java(entity.getImage() != null ? \"/images/\" + entity.getImage() : null)")
    Ad toAdDto(AdEntity entity);


    @Mapping(target = "pk", source = "id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "image", expression = "java(entity.getImage() != null ? \"/images/\" + entity.getImage() : null)")
    ExtendedAd toExtendedAd(AdEntity entity);


    //Как клиент обновляет объект? → DTO → Entity (существующий) CreateOrUpdateAd

    void toUpdateAdFromDto(@MappingTarget AdEntity entity, CreateOrUpdateAd dto);
}



