package ru.skypro.homework.service;

import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.User;

public class UserMapper {

    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getUserId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhone(user.getPhone());
        userDTO.setRole(user.getRole());

        return userDTO;
    }

    public static User fromUserDTO(UserDTO userDTO) {
        User user = new User();

        user.setUserId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhone(userDTO.getPhone());
        user.setRole(userDTO.getRole());

        return user;
    }

    public static UpdateUserDTO toUpdateUserDTO(User user) {
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();

        updateUserDTO.setFirstName(user.getFirstName());
        updateUserDTO.setLastName(user.getLastName());
        updateUserDTO.setPhone(user.getPhone());

        return updateUserDTO;
    }

    public static User updateUserFromDTO(User user, UpdateUserDTO updateUserDTO) {
        user.setFirstName(updateUserDTO.getFirstName());
        user.setLastName(updateUserDTO.getLastName());
        user.setPhone(updateUserDTO.getPhone());

        return user;
    }
}
