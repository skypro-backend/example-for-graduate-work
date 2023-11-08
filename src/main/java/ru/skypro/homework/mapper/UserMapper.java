package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.*;

@Component
public abstract class UserMapper {
    public UserDto entityToUserDto(User entity) {
        return new UserDto(entity.getId(), entity.getEmail(), entity.getFirstName(),
                entity.getLastName(), entity.getPhone(), entity.getRole(), entity.getImagePath());
    }

    public User userDtoToEntity(UpdateUserDto user, User entity) {
        entity.setPhone(user.getPhone());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        return entity;
    }

    public User registerReqDtoToEntity(RegisterDto req) {
        return new User(req.getPassword(), req.getUsername(), req.getFirstName(),
                req.getLastName(), req.getPhone(), req.getRole());
    }

    public UpdateUserDto entityToUpdateUserDto(User entity) {
        return new UpdateUserDto(entity.getFirstName(),
                entity.getLastName(), entity.getPhone());
    }

    public abstract UserDto UsertoUserDto(User user);

    public abstract User UserDtoToUser(RegisterDto register);
}
