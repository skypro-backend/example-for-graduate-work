package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.store.entities.UserEntity;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UpdateUserDTO toUpdateUserDto(UserEntity userEntity);

    UserDTO toUserDTO(UserEntity userEntity);

    //(Обновление пароля)

    NewPasswordDTO userEntityToNewPasswordDto(UserEntity userEntity);

}
