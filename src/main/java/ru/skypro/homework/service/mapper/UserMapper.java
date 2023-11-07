package ru.skypro.homework.service.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.UserInfo;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToDto(UserInfo userInfo);

    UserInfo userDtoToModel(UserDTO userDTO);
}
