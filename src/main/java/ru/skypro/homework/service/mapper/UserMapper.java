package ru.skypro.homework.service.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.UserInfo;

/**
 * Класс, содержащий методы для преобразования класса {@link UserInfo} в DTO
 */
@Component
public class UserMapper {

    /**
     * Метод преобразует {@link UserInfo} в {@link UserDTO}
     * @param userInfo
     * @return {@link UserDTO}
     */
    public UserDTO userToDto(UserInfo userInfo) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userInfo.getId());
        userDTO.setEmail(userInfo.getEmail());
        userDTO.setFirstName(userInfo.getFirstName());
        userDTO.setLastName(userInfo.getLastName());
        userDTO.setPhone(userInfo.getPhone());
        userDTO.setRole(userInfo.getRole());
        if (userInfo.getImage() != null) {
            userDTO.setImage("/users/image/" + userInfo.getImage().getId());
        }
        return userDTO;
    }

    /**
     * Метод преобразует {@link UserInfo} в {@link UpdateUserDTO}
     * @param userInfo
     * @return {@link UpdateUserDTO}
     */
    public UpdateUserDTO userToUpdateUserDto(UserInfo userInfo) {
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setFirstName(userInfo.getFirstName());
        updateUserDTO.setLastName(userInfo.getLastName());
        updateUserDTO.setPhone(userInfo.getPhone());

        return updateUserDTO;
    }
}
