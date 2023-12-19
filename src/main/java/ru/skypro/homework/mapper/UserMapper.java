package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.RegisterDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.User;

import java.util.Optional;

@Component
public class UserMapper {

    public UserDTO mapUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhone(user.getPhone());
        Optional.ofNullable(user.getImage()).ifPresent(image -> userDTO.setImage(
                "/users/" + user.getImage().getId() + "/image"));
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    public User mapUserDTOtoUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhone(userDTO.getPhone());
        user.getImage().setId(Integer.valueOf(userDTO.getImage()));
        user.setRole(userDTO.getRole());
        return user;
    }
    public User mapRegisterDTOtoUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(registerDTO.getPassword());
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setPhone(registerDTO.getPhone());
        user.setRole(registerDTO.getRole());
        user.setEmail(registerDTO.getUsername());
        return user;
    }
}
