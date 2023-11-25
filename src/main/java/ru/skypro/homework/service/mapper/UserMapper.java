package ru.skypro.homework.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.UserInfo;

@Mapper(componentModel = "spring")
public interface UserMapper {

    //@Mapping(source = "imageModel", target = "image")
    UserDTO userToDto(UserInfo userInfo);

    UserInfo userDtoToModel(UserDTO userDTO);

    UpdateUserDTO userToUpdateUserDto(UserInfo userInfo);
}
