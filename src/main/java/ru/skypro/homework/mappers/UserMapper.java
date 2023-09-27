package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.store.entities.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UpdateUserDTO toUpdateUserDto(UserEntity userEntity);

//    //(Обновление информации об авторизованном пользователе)
//    UpdateUserDTO userEntityToUpdateuserDto(UserEntity userEntity);
//
//    //(Получение информации об авторизованном пользователе)
//    UserDTO userEntityToUserDto(UserEntity userEntity);
//
//    //(Обновление пароля)
//    NewPasswordDTO userEntityToNewPasswordDto(UserEntity userEntity);

}
