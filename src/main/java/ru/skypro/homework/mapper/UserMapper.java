package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.Ad;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "user.adList", ignore = true)
    UserDto toDto(Ad ad);
//    Ad toModel(AdDto adDto);
}
