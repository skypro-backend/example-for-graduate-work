package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.UserModel;

public class UserMapper {


    public static UserDTO mapToUserModel(UserModel userModel) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userModel.getId());
        userDTO.setEmail(userModel.getEmail());
        userDTO.setFirstName(userModel.getFirstName());
        userDTO.setLastName(userModel.getLastName());
        userDTO.setPhone(userModel.getPhone());
        userDTO.setRole(userDTO.getRole());
        userDTO.setImage(userDTO.getImage());
        return userDTO;
    }

    public static UserModel mapToUserDto(UserDTO userDTO) {
        UserModel userModel = new UserModel();
        userModel.setId(userDTO.getId());
        userModel.setEmail(userDTO.getEmail());
        userModel.setFirstName(userDTO.getFirstName());
        userModel.setLastName(userDTO.getLastName());
        userModel.setPhone(userDTO.getPhone());
        return userModel;
    }
}

