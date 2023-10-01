package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.User;

public class UserMapper {
    public static UserDTO fromUser(User user) {
        return new UserDTO()
                .setId(user.getId())
                .setImage(user.getImage())
                .setPhone(user.getPhone())
                .setRole(user.getRole())
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName());
    }
    public static User toDTO(UserDTO userDTO){
        return new User()
                .setId(userDTO.getId())
                .setImage(userDTO.getImage())
                .setPhone(userDTO.getPhone())
                .setRole(userDTO.getRole())
                .setEmail(userDTO.getEmail())
                .setFirstName(userDTO.getFirstName())
                .setLastName(userDTO.getLastName());
    }
}

