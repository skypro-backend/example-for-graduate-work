package ru.skypro.homework.mapper;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;

@Service
public class UserMapper {
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
}
