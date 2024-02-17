package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.skypro.homework.entities.UserEntity;
import ru.skypro.homework.dto.User;

@Component
public class UsersMapper {
    //    из Entity в DTO
    public User mapToUserDTO(UserEntity userEntity) {
        User user = new User();
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setPhone(userEntity.getPhone());
        return user;
    }

    //    из DTO в Entity
    public UserEntity mapToUserEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPhone(user.getPhone());
        return userEntity;
    }
}