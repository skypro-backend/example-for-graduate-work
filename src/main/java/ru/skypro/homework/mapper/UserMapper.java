package ru.skypro.homework.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.LoginDTO;
import ru.skypro.homework.dto.RegisterDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.User;

import java.nio.CharBuffer;

@Service
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder encoder;
    //из model в dto
    public UserDTO mapToUserDto(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setImage(user.getImage());
        userDTO.setLastName(user.getLastName());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    public LoginDTO mapToLoginDto(User user){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername(user.getUsername());
        loginDTO.setPassword(user.getPassword());
        return loginDTO;
    }
    //из dto в model
    public User mapToUser(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setImage(userDTO.getImage());
        user.setLastName(userDTO.getLastName());
        user.setRole(userDTO.getRole());
        return user;
    }
    public User mapToUserLogin(LoginDTO loginDTO){
        User user = new User();
        user.setUsername(loginDTO.getUsername());
        user.setPassword(loginDTO.getPassword());
        return user;
    }

    public User mapToUser(RegisterDTO registerDTO){
        User user = new User();
        user.setUsername(registerDTO.getUsername());
//        user.setPassword(registerDTO.getPassword());
        user.setPassword(encoder.encode(CharBuffer.wrap(registerDTO.getPassword())));
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setPhone(registerDTO.getPhone());
        user.setRole(registerDTO.getRole());
        return user;
    }
}
