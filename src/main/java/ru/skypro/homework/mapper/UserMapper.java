package ru.skypro.homework.mapper;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;

@Service
@AllArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public UserDTO toUserDTO(User user) {
        Image image = user.getImage();
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getRole().toString(),
                image != null ? "/images/" + image.getId() : null
        );
    }

    public UpdateUser toUpdateUser(User user) {
        return new UpdateUser(
                user.getFirstName(),
                user.getLastName(),
                user.getPhone()
        );
    }

    public User toUser(Register dto) {
        User user = new User();
        user.setEmail(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhone(dto.getPhone());
        user.setRole(dto.getRole());
        return user;
    }
}
