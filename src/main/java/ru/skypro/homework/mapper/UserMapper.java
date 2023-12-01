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

    //из model в dto
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

    public LoginDTO mapToLoginDto(User user) {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername(user.getUsername());
        loginDTO.setPassword(user.getPassword());
        return loginDTO;
    }

    public UpdateUserDTO mapToUpdateUserDTO(User user) {
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setFirstName(user.getFirstName());
        updateUserDTO.setLastName(user.getLastName());
        updateUserDTO.setPhone(user.getPhone());
        return updateUserDTO;
    }

    //из dto в model
//    public User mapToUser(UserDTO userDTO) {
//        User user = new User();
//        user.setId(userDTO.getId());
//        user.setEmail(userDTO.getEmail());
//        user.setPhone(userDTO.getPhone());
//        user.setImage(userDTO.getImage());
//        user.setLastName(userDTO.getLastName());
//        user.setRole(userDTO.getRole());
//        user.setUsername(userDTO.getEmail());
//        return user;
//    }

    public User mapToUserLogin(LoginDTO loginDTO) {
        User user = new User();
        user.setUsername(loginDTO.getUsername());
        user.setPassword(loginDTO.getPassword());
        return user;
    }

    public User mapToUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
//        user.setPassword(registerDTO.getPassword());
        user.setPassword(encoder.encode(CharBuffer.wrap(registerDTO.getPassword())));
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setPhone(registerDTO.getPhone());
        user.setRole(registerDTO.getRole());
        user.setEmail(registerDTO.getUsername());
        return user;
    }
}
