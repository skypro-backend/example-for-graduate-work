package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.service.entities.UserEntity;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UpdateUserDTO toUpdateUserDto(UserEntity userEntity);

    UserDTO toUserDto(UserEntity userEntity);


}
