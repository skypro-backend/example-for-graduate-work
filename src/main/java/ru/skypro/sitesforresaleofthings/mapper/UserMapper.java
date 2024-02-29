package ru.skypro.sitesforresaleofthings.mapper;

import org.springframework.stereotype.Service;
import ru.skypro.sitesforresaleofthings.dto.Register;
import ru.skypro.sitesforresaleofthings.dto.UserDTO;
import ru.skypro.sitesforresaleofthings.entity.User;

/**
 * Создаем сервис(маппер), который устанавливает соответствие(маппит) из сущности в DTO и обратно
 */
@Service
public class UserMapper {

    public User toEntity(Register register) {
        User user = new User();
        user.setUsername(register.getUsername());
        user.setEmail(register.getUsername());
        user.setPassword(register.getPassword());
        user.setFirstName(register.getFirstName());
        user.setLastName(register.getLastName());
        user.setPhone(register.getPhone());
        user.setRole(register.getRole());
        return user;
    }

    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhone(user.getPhone());
        if (user.getImage() != null) {
            userDTO.setImage(String.format("/users/image/%s", user.getImage()));
        } else {
            userDTO.setImage(null);
        }
        return userDTO;
    }
}