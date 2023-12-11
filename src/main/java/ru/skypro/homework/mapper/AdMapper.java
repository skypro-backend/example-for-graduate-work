package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

@Mapper(componentModel = "spring")
public interface AdMapper {
    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);
    @Mapping(target = "author", source = "ad.author.id")
    @Mapping(target = "pk", source = "ad.id")
    @Mapping(target = "image", expression = "java(\"/image/\" + ad.getImage().getId())")
    AdDTO adToAdDTO(Ad ad);

    @Mapping(target = "author", source = "user")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "image", ignore = true)
    Ad createOrUpdateAdDTOToAd(CreateOrUpdateAdDTO createOrUpdateAdDTO, User user);

    @Mapping(target = "pk", source = "ad.id")
    @Mapping(target = "authorFirstName", source = "user.firstName")
    @Mapping(target = "authorLastName", source = "user.lastName")
    @Mapping(target = "image", expression = "java(\"/image/\" + ad.getImage().getId())")
    ExtendedAdDTO toExtendedAdDTO (Ad ad, User user);
}
