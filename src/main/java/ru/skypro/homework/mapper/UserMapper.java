package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.Avatar;
import ru.skypro.homework.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    String address = "/users/image/";

    @Mapping(target = "avatar", source = "avatar", qualifiedByName = "avatarToString")
    UserDto userToUserDto(User entity);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "avatar", ignore = true)
    @Mapping(target = "role", ignore = true)
    User userDtoToUser(UserDto dto);

    @Mapping(target = "role", defaultValue = "USER")
    @Mapping(source = "username", target = "email")
    User registerToUser(Register dto);

    @Named("avatarToString")
    default String avatarToString(Avatar avatar) {
        if (avatar == null) {
            return null;
        }
        return address + avatar.getId();
    }
}
