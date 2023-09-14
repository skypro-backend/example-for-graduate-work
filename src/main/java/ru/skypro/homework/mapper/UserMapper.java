package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.entity.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "role", target = "role") // Если необходимо преобразовать enum Role
    UserDTO userToUserDTO(User user);

    @Mapping(source = "role", target = "role") // Если необходимо преобразовать enum Role
    User userDTOToUser(UserDTO userDTO);
}