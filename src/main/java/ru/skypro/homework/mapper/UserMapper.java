package ru.skypro.homework.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.LoginDTO;
import ru.skypro.homework.dto.RegisterDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;

import java.nio.CharBuffer;

@Service
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder encoder;

    /**
     * Маппинг пользователя в объект DTO.
     */
    public UserDTO mapToUserDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getUsername());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhone(user.getPhone());
        userDTO.setRole(user.getRole());
        Image image = user.getImage();
        if (image != null) {
            userDTO.setImage("/image/" + image.getId());
        } else {
            userDTO.setImage(null);
        }
        return userDTO;
    }

    public UpdateUserDTO mapToUpdateUserDTO(User user) {
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setFirstName(user.getFirstName());
        updateUserDTO.setLastName(user.getLastName());
        updateUserDTO.setPhone(user.getPhone());
        return updateUserDTO;
    }

    /**
     * Маппинг пользователя из объекта DTO.
     */

    public User mapToUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(encoder.encode(CharBuffer.wrap(registerDTO.getPassword())));
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setPhone(registerDTO.getPhone());
        user.setRole(registerDTO.getRole());
        user.setEmail(registerDTO.getUsername());
        return user;
    }
}
