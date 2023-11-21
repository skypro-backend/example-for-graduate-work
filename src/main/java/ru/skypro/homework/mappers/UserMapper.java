package ru.skypro.homework.mappers;

import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.models.User;

public class UserMapper {

    public static User toUser(UserDto userDto) {

        if (userDto == null) {
            throw new NullPointerException("Tried to map null to User");
        }

        User user = new User();

        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        user.setRole(userDto.getRole());
        user.setImage(userDto.getImage());

        return user;
    }

    public static UserDto fromUser(User user) {
        if (user == null) {
            throw new NullPointerException("Tried to map null to User");
        }

        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhone(user.getPhone());
        userDto.setRole(user.getRole());
        userDto.setImage(user.getImage());

        return userDto;

    }
}
