package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.store.entities.UserEntity;

@AllArgsConstructor
@Service
public class UserServiceImpl {
    private final UserMapper userMapper;


    public UpdateUserDTO updateUser(UserEntity userEntity) {
        if (userEntity != null) {
            userMapper.toUpdateUserDto(userEntity);
            return userMapper.toUpdateUserDto(userEntity);
        } else {
            throw new RuntimeException();
        }

    }
}
