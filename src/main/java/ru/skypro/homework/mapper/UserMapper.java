package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.users.UpdateUserDto;
import ru.skypro.homework.dto.users.UserDto;
import ru.skypro.homework.entity.User;

@Component
public class UserMapper {

    public UserDto userToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .image(user.getImage())
                .role(user.getRole().name())
                .build();
    }

    public UpdateUserDto updateToDto(User user) {
        return UpdateUserDto.builder()
                .firstname(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .build();
    }
}
