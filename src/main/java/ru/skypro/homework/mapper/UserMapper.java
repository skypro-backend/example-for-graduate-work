package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

@Component
public class UserMapper {

    public User toDto(UserEntity entity) {
        User dto = new User();

        dto.setId(entity.getId());
        dto.setEmail(entity.getUsername());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhone(entity.getPhone());
        dto.setRole(entity.getRole());
        dto.setImage(entity.getImage());

        return dto;
    }
}