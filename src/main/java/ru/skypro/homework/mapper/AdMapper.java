package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;

@Mapper
public abstract class AdMapper {

    @Mapping(target = "authorFirstName", expression = "java(getUser(ad).getFirstName())")
    @Mapping(target = "authorLastName", expression = "java(getUser(ad).getLastName())")
    @Mapping(target = "email", expression = "java(getUser(ad).getLogin())")
    @Mapping(target = "phone", expression = "java(getUser(ad).getPhone())")
    @Mapping(target = "image", expression = "java(getImage(ad))")
    @Mapping(target = "pk", source = "id")
    public abstract ExtendedAdDto toDto(Ad ad);

    @Mapping(target = "image", expression = "java(getImage(ad))")
    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", expression = "java(getUser(ad).getId())")
    public abstract AdDto toAdDto(Ad ad);

    protected String getImage(Ad ad) {
        return ad.getImage().toString();
    }

    protected User getUser(Ad ad) {
        return ad.getUser();
    }


}
