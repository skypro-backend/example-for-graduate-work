package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.CreateUser;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {


    UserDto userToUserDto(User user);

    List<UserDto> userToUserDto(List<User> userList);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "adsList", ignore = true)
    @Mapping(target = "adsCommentList", ignore = true)
    @Mapping(target = "password", ignore = true)
    User userDtoToUser(UserDto userDto);

    List<User> userDtoToUser(List<UserDto> userDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "adsList", ignore = true)
    @Mapping(target = "adsCommentList", ignore = true)
    User createUserToUser(CreateUser createUser);
}
