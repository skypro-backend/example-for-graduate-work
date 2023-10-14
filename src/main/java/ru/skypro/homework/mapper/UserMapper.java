package ru.skypro.homework.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * Метод связывает поля UserEntity с dto User
     * @param userEntity сущность пользователя
     * @return пользователя
     */
    @Mapping(source = "username", target = "email")
    User userEntityToUser(UserEntity userEntity);

    /**
     * Метод связывает поля dto User с UserEntity
     * @param user dto пользователя
     * @return dto пользователя
     */
    @InheritInverseConfiguration
    UserEntity userToUserEntity(User user);

}
