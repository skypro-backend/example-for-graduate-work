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
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comments", ignore = true)
    AdDTO adToAdDTO(Ad ad);

    @Mapping(target = "id", ignore = true)
    Ad CreateOrUpdateAdDTOToAd(CreateOrUpdateAdDTO createOrUpdateAdDTO);
    @Mapping(target = "pk", source = "ad.id")
    @Mapping(target = "authorFirstName", source = "user.firstName")
    @Mapping(target = "authorLastName", source = "user.lastName")
    @Mapping(target = "image", source = "ad.image")
    ExtendedAdDTO toExtendedAdDTO (Ad ad, User user);
}
