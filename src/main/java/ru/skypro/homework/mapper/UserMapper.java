package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

/**
 * Класс конвертирует модель UserEntity в User Dto и обратно.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "username", target = "email")
    @Mapping(source = "imageEntity.filePath", target = "image")
    User userEntityToUser(UserEntity userEntity);

}
