package ru.skypro.homework.mapper;

import org.mapstruct.*;
import ru.skypro.homework.dto.RegisterDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "image", source ="photo.path")
    UserDTO toUserDto(UserEntity user);

    UpdateUserDTO toUpdateUserDto(UserEntity user);

    @Mapping(target = "password", ignore = true)
    UserEntity fromUserRegisterDto(RegisterDTO registerDto);
}
