package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExtendedAdMapper {
    ExtendedAdMapper INSTANCE = Mappers.getMapper(ExtendedAdMapper.class);
    @Mapping(source = "ad.pk", target = "pk")
    @Mapping(source = "user.firstName", target = "authorFirstName")
    @Mapping(source = "user.lastName", target = "authorLastName")
    @Mapping(source = "ad.description", target = "description")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "ad.image", target = "image")
    @Mapping(source = "user.phone", target = "phone")
    @Mapping(source = "ad.price", target = "prise")
    @Mapping(source = "ad.title", target = "title")
    ExtendedAdDto toDTO(Ad ad, User user);
}
