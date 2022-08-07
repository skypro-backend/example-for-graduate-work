package ru.skypro.homework.mapStruct;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.CreateUser;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {


    UserDto userToUserDto(User user);

    List<UserDto> userToUserDto(List<User> userList);

    User userDtoToUser(UserDto userDto);

    List<User> userDtoToUser(List<UserDto> userDto);

    User createUserToUser(CreateUser createUser);
}
